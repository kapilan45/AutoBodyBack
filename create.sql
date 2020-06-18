create sequence hibernate_sequence start 1 increment 1
create table annonce (id int8 not null, air_admission varchar(255), air_filter varchar(255), announce_status int4, drive_type varchar(255), dump_valve varchar(255), energy varchar(255), exaust varchar(255), first_hand varchar(255), fiscal_horse_power int4, fuel_economy varchar(255), fuel_economy_since_the_latest_modification varchar(255), gearbox varchar(255), high_performance_tuning_company varchar(255), horse_power int4, horse_power_since_the_latest_modification int4, in_side_color varchar(255), intercooler varchar(255), localisation varchar(255), max_speed int4, mileage int8, mileage_since1st_modification int4, number_of_door int4, number_of_seats int4, options varchar(255), out_side_color varchar(255), price float8, published_date timestamp, reinforced_clutch boolean, stage int4, torque int4, torque_since_the_latest_modification int4, trim varchar(255), turbo varchar(255), year timestamp, category_id int8, make_id int8, model_id int8, user_id int8, primary key (id))
create table annonce_images (annonce_entity_id int8 not null, images_id int8 not null)
create table category (id  bigserial not null, category varchar(255), model_id int8, primary key (id))
create table image (id  bigserial not null, path bytea, primary key (id))
create table make (id  bigserial not null, make varchar(255), primary key (id))
create table make_model (make_entity_id int8 not null, model_id int8 not null)
create table model (id  bigserial not null, model varchar(255), make_id int8, primary key (id))
create table model_category (model_entity_id int8 not null, category_id int8 not null)
create table t_role (id int4 not null, role_name varchar(65), primary key (id))
create table t_user (id int8 not null, mail varchar(150), password varchar(75), stat boolean, username varchar(75), role_id int4, primary key (id))
alter table if exists annonce_images add constraint UK_dc4q7sq2rg59adwxiv0hnbl17 unique (images_id)
alter table if exists make add constraint UK_bhchb4ccpotdvm73orf5dvl3q unique (make)
alter table if exists make_model add constraint UK_mi3fkmgtsly3xsipa577lqgju unique (model_id)
alter table if exists model_category add constraint UK_jmf9gmhbqd9rl474330lt0iwf unique (category_id)
alter table if exists annonce add constraint FKhplwwwg5ajvl7b8h10be2t30c foreign key (category_id) references category
alter table if exists annonce add constraint FK15yscu47ptx3q05lq5c2a4qi7 foreign key (make_id) references make
alter table if exists annonce add constraint FK5mrtprfjnshwt0wwxbj7mr6o9 foreign key (model_id) references model
alter table if exists annonce add constraint FKqi5we07rp0jykp6f47o48bjca foreign key (user_id) references t_user
alter table if exists annonce_images add constraint FKm7q51usx6aa21kvdirkr2gd4c foreign key (images_id) references image
alter table if exists annonce_images add constraint FKmsqs93vn9ba4y01s00ghodcqt foreign key (annonce_entity_id) references annonce
alter table if exists category add constraint FKa31lh10mrhoy9qbtxgk4q6uyg foreign key (model_id) references model
alter table if exists make_model add constraint FKbfmk7leea6padb6kvvl8eiitt foreign key (model_id) references model
alter table if exists make_model add constraint FK35vbcpav2x7cdus23t5ni5th9 foreign key (make_entity_id) references make
alter table if exists model add constraint FKfvdrvechg4dtwo64ut3g01hu4 foreign key (make_id) references make
alter table if exists model_category add constraint FKohwi77517ul8iydr4ysfpqwft foreign key (category_id) references category
alter table if exists model_category add constraint FKevek1mulswqhr28o1fkdrecyr foreign key (model_entity_id) references model
alter table if exists t_user add constraint FK76cs7cu4h4y8vc1vd4qyw36rt foreign key (role_id) references t_role
create sequence hibernate_sequence start 1 increment 1
create table annonce (id int8 not null, air_admission varchar(255), air_filter varchar(255), announce_status int4, drive_type varchar(255), dump_valve varchar(255), energy varchar(255), exaust varchar(255), first_hand varchar(255), fiscal_horse_power int4, fuel_economy varchar(255), fuel_economy_since_the_latest_modification varchar(255), gearbox varchar(255), high_performance_tuning_company varchar(255), horse_power int4, horse_power_since_the_latest_modification int4, in_side_color varchar(255), intercooler varchar(255), localisation varchar(255), max_speed int4, mileage int8, mileage_since1st_modification int4, number_of_door int4, number_of_seats int4, options varchar(255), out_side_color varchar(255), price float8, published_date timestamp, reinforced_clutch boolean, stage int4, torque int4, torque_since_the_latest_modification int4, trim varchar(255), turbo varchar(255), year timestamp, category_id int8, make_id int8, model_id int8, user_id int8, primary key (id))
create table annonce_images (annonce_entity_id int8 not null, images_id int8 not null)
create table category (id  bigserial not null, category varchar(255), model_id int8, primary key (id))
create table image (id  bigserial not null, path bytea, primary key (id))
create table make (id  bigserial not null, make varchar(255), primary key (id))
create table make_model (make_entity_id int8 not null, model_id int8 not null)
create table model (id  bigserial not null, model varchar(255), make_id int8, primary key (id))
create table model_category (model_entity_id int8 not null, category_id int8 not null)
create table t_role (id int4 not null, role_name varchar(65), primary key (id))
create table t_user (id int8 not null, mail varchar(150), password varchar(75), stat boolean, username varchar(75), role_id int4, primary key (id))
alter table if exists annonce_images add constraint UK_dc4q7sq2rg59adwxiv0hnbl17 unique (images_id)
alter table if exists make add constraint UK_bhchb4ccpotdvm73orf5dvl3q unique (make)
alter table if exists make_model add constraint UK_mi3fkmgtsly3xsipa577lqgju unique (model_id)
alter table if exists model_category add constraint UK_jmf9gmhbqd9rl474330lt0iwf unique (category_id)
alter table if exists annonce add constraint FKhplwwwg5ajvl7b8h10be2t30c foreign key (category_id) references category
alter table if exists annonce add constraint FK15yscu47ptx3q05lq5c2a4qi7 foreign key (make_id) references make
alter table if exists annonce add constraint FK5mrtprfjnshwt0wwxbj7mr6o9 foreign key (model_id) references model
alter table if exists annonce add constraint FKqi5we07rp0jykp6f47o48bjca foreign key (user_id) references t_user
alter table if exists annonce_images add constraint FKm7q51usx6aa21kvdirkr2gd4c foreign key (images_id) references image
alter table if exists annonce_images add constraint FKmsqs93vn9ba4y01s00ghodcqt foreign key (annonce_entity_id) references annonce
alter table if exists category add constraint FKa31lh10mrhoy9qbtxgk4q6uyg foreign key (model_id) references model
alter table if exists make_model add constraint FKbfmk7leea6padb6kvvl8eiitt foreign key (model_id) references model
alter table if exists make_model add constraint FK35vbcpav2x7cdus23t5ni5th9 foreign key (make_entity_id) references make
alter table if exists model add constraint FKfvdrvechg4dtwo64ut3g01hu4 foreign key (make_id) references make
alter table if exists model_category add constraint FKohwi77517ul8iydr4ysfpqwft foreign key (category_id) references category
alter table if exists model_category add constraint FKevek1mulswqhr28o1fkdrecyr foreign key (model_entity_id) references model
alter table if exists t_user add constraint FK76cs7cu4h4y8vc1vd4qyw36rt foreign key (role_id) references t_role
