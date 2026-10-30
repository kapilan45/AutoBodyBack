# AutoBodyBack

Backend API for the AutoBody marketplace.

## Runtime

- Spring Boot 4.1.2 (latest stable release selected for this migration)
- Java 17 or newer
- Maven 3.6.3 or newer
- PostgreSQL

Build and run with:

```powershell
mvn test
mvn spring-boot:run
```

The application requires a PostgreSQL database and the environment variables below. There are no embedded credentials or JWT signing-key defaults.

| Variable | Required | Default | Purpose |
| --- | --- | --- | --- |
| `DB_URL` | No | `jdbc:postgresql://localhost:5432/autobody` | JDBC connection URL |
| `DB_USER` | Yes | empty | PostgreSQL user |
| `DB_PASSWORD` | Yes | empty | PostgreSQL password |
| `AUTOBODY_JWT_SECRET` | Yes | none | Base64-encoded random key of at least 32 bytes |
| `AUTOBODY_JWT_EXPIRATION` | No | `PT1H` | JWT lifetime as an ISO-8601 duration |
| `AUTOBODY_CORS_ALLOWED_ORIGINS` | No | empty | Comma-separated exact browser origins; wildcard is rejected |
| `AUTOBODY_IMAGE_MAX_SIZE` | No | `5MB` | Multipart request and file limit |
| `AUTOBODY_IMAGE_MAX_BYTES` | No | `5242880` | Image byte limit; keep aligned with `AUTOBODY_IMAGE_MAX_SIZE` |

Create a secret outside source control, for example with `openssl rand -base64 32`, and provide it through the runtime secret manager. Do not commit it or put it in application properties. Rotating the key invalidates all existing access tokens; users must log in again. Tokens expire after one hour by default.

For local frontend development, set `AUTOBODY_CORS_ALLOWED_ORIGINS=http://localhost:4200`. Production must set the exact HTTPS frontend origin(s). CORS credentials are disabled; authentication uses the `Authorization` header.

## Database setup and migrations

Create an empty PostgreSQL database, configure `DB_URL`, `DB_USER`, and `DB_PASSWORD`, then start the application. Flyway applies `src/main/resources/db/migration/V1__baseline_and_secure_images.sql`; Hibernate is configured to validate rather than silently change the schema. The migration creates the legacy schema on a new database and applies additive/data-preserving updates when it finds the existing schema. Do not run the old `create.sql` script.

For an existing deployment:

1. Back up the database and rehearse the migration against a recent restored copy before production.
2. Check duplicate role names, usernames, and non-empty email addresses case-insensitively. For the role-name preflight, query the existing `role` column (or `role_name` on the original `create.sql` schema). Also check that legacy string values in `annonce.first_hand` are one of `true/false`, `t/f`, `1/0`, or `yes/no` (`y/n`). V1 aborts without deleting or merging records if those preconditions fail.
3. Deploy the new application with a database account that can run Flyway DDL and data migrations. Existing non-empty schemas are baselined at version 0, then V1 is applied.
4. Review the Flyway startup output, especially the notice reporting legacy images that have no identifiable owner. Linked images are assigned the owning announcement user's ID; images not linked to an announcement remain stored but unowned and are not exposed through user/public image retrieval.

V1 also copies legacy `t_role.role_name` values into `t_role.role`, widens the `t_role.id` / `t_user.role_id` integer columns to `bigint` to match the `Long` JPA identifier, and rebuilds the foreign key without changing role IDs. It converts `annonce.options` from legacy text or Hibernate OID-backed text to PostgreSQL `text` (decoding OID contents as UTF-8; invalid legacy values abort the migration), and advances the shared Hibernate sequence and existing identity sequences past their current maximum IDs. Large-object records are not explicitly unlinked.

Useful preflight/review queries:

```sql
SELECT lower(username), count(*) FROM t_user
WHERE username IS NOT NULL GROUP BY lower(username) HAVING count(*) > 1;

SELECT lower(mail), count(*) FROM t_user
WHERE mail IS NOT NULL AND btrim(mail) <> ''
GROUP BY lower(mail) HAVING count(*) > 1;

-- Use role_name here if the existing database has not yet been migrated to role.
SELECT lower(role), count(*) FROM t_role
WHERE role IS NOT NULL GROUP BY lower(role) HAVING count(*) > 1;

SELECT DISTINCT first_hand FROM annonce
WHERE first_hand IS NOT NULL
  AND lower(btrim(first_hand::text)) NOT IN ('true', 'false', 't', 'f', '1', '0', 'yes', 'no', 'y', 'n');

SELECT count(*) AS unowned_legacy_images FROM image WHERE owner_id IS NULL;
```

Migrations are forward-only and do not delete image bytes or user records. If V1 fails, retain the backup, resolve the reported precondition without merging accounts, and retry. For rollback, redeploy the previous application only if its schema compatibility has been verified; otherwise restore the database backup. Do not use `flyway clean` or manually drop the new columns/tables in production. Add future schema changes as new `V2__...sql` migrations.

The `ROLE_USER` role is created by V1. Public registration always assigns it and ignores submitted role/status values. No administrator account or password is created automatically; provision the first administrator through a controlled, audited database operation, then grant `ROLE_ADMIN`.

## API and compatibility

- `POST /login` still returns a `UserDto` response body and the JWT in the `cache-control` response header. The header is exposed to configured browser origins.
- Send that JWT to protected endpoints in `Authorization`; both the legacy raw-token form and `Bearer <token>` are accepted.
- Public announcement/filter reads and images already attached to public announcements remain available without login. Uploads, account operations, and announcement create/update/delete require authentication. Users can only modify their own announcements and attach images they uploaded.
- `POST /api/upload/image` and the legacy `POST /api/upload/file` alias accept the multipart field `image`. Both store the bytes in PostgreSQL. JPEG, PNG, and GIF are accepted after decoding and content-type verification; other formats and oversized images are rejected. The upload response includes the image ID for later attachment to an announcement. Unattached images are retained in PostgreSQL; there is no image deletion endpoint or automatic cleanup.
- `GET /api/upload/image/mine` lists the authenticated user's own uploaded images, including unattached uploads. `DELETE /api/upload/image/{id}` removes only an unattached image belonging to the current user; detach an image from its announcement before deleting it.
- The legacy public `GET /api/upload/image/get/{user}` route now returns only that user's images attached to public announcements. It does not expose unattached uploads or another user's private upload list.
- Old tokens signed with the previously hardcoded key are not accepted after this migration. The frontend contract for new login responses and announcement `Authorization` headers is unchanged.

## Tests

Run the focused unit/security tests and full Maven test lifecycle with `mvn test`. The automated test suite does not require a running PostgreSQL server; migration rehearsals should use a disposable PostgreSQL database restored from a backup or initialized from empty.
