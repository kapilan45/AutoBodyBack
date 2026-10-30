CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS t_role (
    id bigint PRIMARY KEY,
    role varchar(65),
    role_name varchar(65)
);

ALTER TABLE t_role ADD COLUMN IF NOT EXISTS role varchar(65);
DO $$
BEGIN
    IF EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_schema = current_schema()
          AND table_name = 't_role'
          AND column_name = 'role_name'
    ) THEN
        EXECUTE 'UPDATE t_role SET role = role_name WHERE role IS NULL';
    END IF;
END;
$$;

CREATE TABLE IF NOT EXISTS t_user (
    id bigint PRIMARY KEY,
    mail varchar(150),
    password varchar(75),
    stat boolean,
    username varchar(75),
    role_id bigint REFERENCES t_role(id)
);

DO $$
DECLARE
    constraint_name text;
BEGIN
    FOR constraint_name IN
        SELECT conname FROM pg_constraint
        WHERE conrelid = 't_user'::regclass
          AND confrelid = 't_role'::regclass
          AND contype = 'f'
    LOOP
        EXECUTE format('ALTER TABLE t_user DROP CONSTRAINT %I', constraint_name);
    END LOOP;
    ALTER TABLE t_role ALTER COLUMN id TYPE bigint USING id::bigint;
    ALTER TABLE t_user ALTER COLUMN role_id TYPE bigint USING role_id::bigint;
    ALTER TABLE t_user ADD CONSTRAINT fk_t_user_role
        FOREIGN KEY (role_id) REFERENCES t_role(id);
END;
$$;

CREATE TABLE IF NOT EXISTS make (
    id bigserial PRIMARY KEY,
    make varchar(255) UNIQUE
);

CREATE TABLE IF NOT EXISTS model (
    id bigserial PRIMARY KEY,
    model varchar(255),
    make_id bigint REFERENCES make(id)
);

CREATE TABLE IF NOT EXISTS category (
    id bigserial PRIMARY KEY,
    category varchar(255),
    model_id bigint REFERENCES model(id)
);

CREATE TABLE IF NOT EXISTS image (
    id bigserial PRIMARY KEY,
    path bytea,
    content_type varchar(64),
    owner_id bigint,
    CONSTRAINT fk_image_owner FOREIGN KEY (owner_id) REFERENCES t_user(id)
);
ALTER TABLE image ADD COLUMN IF NOT EXISTS content_type varchar(64);
ALTER TABLE image ADD COLUMN IF NOT EXISTS owner_id bigint;

CREATE TABLE IF NOT EXISTS annonce (
    id bigint PRIMARY KEY,
    air_admission varchar(255),
    air_filter varchar(255),
    announce_status integer,
    drive_type varchar(255),
    dump_valve varchar(255),
    energy varchar(255),
    exaust varchar(255),
    first_hand varchar(255),
    fiscal_horse_power integer,
    fuel_economy varchar(255),
    fuel_economy_since_the_latest_modification varchar(255),
    gearbox varchar(255),
    high_performance_tuning_company varchar(255),
    horse_power integer,
    horse_power_since_the_latest_modification integer,
    in_side_color varchar(255),
    intercooler varchar(255),
    localisation varchar(255),
    max_speed integer,
    mileage bigint,
    mileage_since1st_modification integer,
    number_of_door integer,
    number_of_seats integer,
    options text,
    out_side_color varchar(255),
    price double precision,
    published_date timestamp,
    reinforced_clutch boolean,
    stage integer,
    torque integer,
    torque_since_the_latest_modification integer,
    trim varchar(255),
    turbo varchar(255),
    year timestamp,
    category_id bigint REFERENCES category(id),
    make_id bigint REFERENCES make(id),
    model_id bigint REFERENCES model(id),
    user_id bigint REFERENCES t_user(id)
);

DO $$
DECLARE
    options_type text;
BEGIN
    SELECT udt_name INTO options_type
    FROM information_schema.columns
    WHERE table_schema = current_schema()
      AND table_name = 'annonce'
      AND column_name = 'options';
    IF options_type = 'oid' THEN
        ALTER TABLE annonce ALTER COLUMN options TYPE text
            USING convert_from(lo_get(options), 'UTF8');
    ELSE
        ALTER TABLE annonce ALTER COLUMN options TYPE text
            USING options::text;
    END IF;
END;
$$;
DO $$
BEGIN
    IF EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_schema = current_schema()
          AND table_name = 'annonce'
          AND column_name = 'first_hand'
          AND data_type <> 'boolean'
    ) THEN
        IF EXISTS (
            SELECT 1 FROM annonce
            WHERE first_hand IS NOT NULL
              AND lower(btrim(first_hand::text)) NOT IN ('true', 'false', 't', 'f', '1', '0', 'yes', 'no', 'y', 'n')
        ) THEN
            RAISE EXCEPTION 'Cannot convert annonce.first_hand: unsupported legacy values exist';
        END IF;
        ALTER TABLE annonce ALTER COLUMN first_hand TYPE boolean
            USING CASE lower(btrim(first_hand::text))
                WHEN 'true' THEN true WHEN 't' THEN true WHEN '1' THEN true
                WHEN 'yes' THEN true WHEN 'y' THEN true
                WHEN 'false' THEN false WHEN 'f' THEN false WHEN '0' THEN false
                WHEN 'no' THEN false WHEN 'n' THEN false
                ELSE NULL
            END;
    END IF;
END;
$$;

CREATE TABLE IF NOT EXISTS annonce_images (
    annonce_entity_id bigint NOT NULL REFERENCES annonce(id),
    images_id bigint NOT NULL UNIQUE REFERENCES image(id),
    PRIMARY KEY (annonce_entity_id, images_id)
);

UPDATE image AS img
SET owner_id = annonce.user_id
FROM annonce_images
JOIN annonce ON annonce.id = annonce_images.annonce_entity_id
WHERE img.id = annonce_images.images_id
  AND img.owner_id IS NULL
  AND annonce.user_id IS NOT NULL;

DO $$
DECLARE
    unowned_images bigint;
BEGIN
    SELECT count(*) INTO unowned_images FROM image WHERE owner_id IS NULL;
    RAISE NOTICE 'Unlinked legacy images without an identifiable owner: %', unowned_images;
END;
$$;

CREATE TABLE IF NOT EXISTS make_model (
    make_entity_id bigint NOT NULL REFERENCES make(id),
    model_id bigint NOT NULL UNIQUE REFERENCES model(id),
    PRIMARY KEY (make_entity_id, model_id)
);

CREATE TABLE IF NOT EXISTS model_category (
    model_entity_id bigint NOT NULL REFERENCES model(id),
    category_id bigint NOT NULL UNIQUE REFERENCES category(id),
    PRIMARY KEY (model_entity_id, category_id)
);

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pg_constraint
        WHERE conname = 'fk_image_owner'
          AND conrelid = 'image'::regclass
    ) THEN
        ALTER TABLE image ADD CONSTRAINT fk_image_owner
            FOREIGN KEY (owner_id) REFERENCES t_user(id);
    END IF;
END;
$$;

CREATE INDEX IF NOT EXISTS idx_image_owner_id ON image(owner_id);

DO $$
BEGIN
    IF EXISTS (
        SELECT lower(role) FROM t_role
        WHERE role IS NOT NULL
        GROUP BY lower(role) HAVING count(*) > 1
    ) THEN
        RAISE EXCEPTION 'Cannot enforce unique roles: duplicate role names exist; resolve them before retrying Flyway V1';
    END IF;
    IF EXISTS (
        SELECT lower(username) FROM t_user
        WHERE username IS NOT NULL
        GROUP BY lower(username) HAVING count(*) > 1
    ) THEN
        RAISE EXCEPTION 'Cannot enforce unique usernames: duplicate usernames exist; resolve them before retrying Flyway V1';
    END IF;
    IF EXISTS (
        SELECT lower(mail) FROM t_user
        WHERE mail IS NOT NULL AND btrim(mail) <> ''
        GROUP BY lower(mail) HAVING count(*) > 1
    ) THEN
        RAISE EXCEPTION 'Cannot enforce unique email addresses: duplicates exist; resolve them before retrying Flyway V1';
    END IF;
END;
$$;

CREATE UNIQUE INDEX IF NOT EXISTS uq_t_role_role_lower
    ON t_role (lower(role))
    WHERE role IS NOT NULL;
CREATE UNIQUE INDEX IF NOT EXISTS uq_t_user_username_lower
    ON t_user (lower(username))
    WHERE username IS NOT NULL;
CREATE UNIQUE INDEX IF NOT EXISTS uq_t_user_mail_lower
    ON t_user (lower(mail))
    WHERE mail IS NOT NULL AND btrim(mail) <> '';

INSERT INTO t_role (id, role)
SELECT COALESCE((SELECT max(id) FROM t_role), 0) + 1, 'ROLE_USER'
WHERE NOT EXISTS (SELECT 1 FROM t_role WHERE role = 'ROLE_USER');

SELECT setval(
    'hibernate_sequence',
    GREATEST(
        COALESCE((SELECT max(id)::bigint FROM annonce), 0),
        COALESCE((SELECT max(id)::bigint FROM t_user), 0),
        COALESCE((SELECT max(id)::bigint FROM t_role), 0)
    ) + 1,
    false
);

DO $$
DECLARE
    table_name text;
    sequence_name text;
    maximum_id bigint;
BEGIN
    FOREACH table_name IN ARRAY ARRAY['image', 'category', 'make', 'model'] LOOP
        sequence_name := pg_get_serial_sequence(table_name, 'id');
        IF sequence_name IS NOT NULL THEN
            EXECUTE format('SELECT max(id) FROM %I', table_name) INTO maximum_id;
            IF maximum_id IS NULL THEN
                PERFORM setval(sequence_name, 1, false);
            ELSE
                PERFORM setval(sequence_name, maximum_id, true);
            END IF;
        END IF;
    END LOOP;
END;
$$;
