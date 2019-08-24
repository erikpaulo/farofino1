/**
* Localhost  -- \i /Users/eriklacerda/Dev-Projects/savefy/postgresdb/helper/db-import-local.sql
*/

COPY USER_GROUP FROM '/Users/eriklacerda/Dev-Projects/savefy/postgresdb/dump/USER_GROUP.csv' DELIMITER ',' CSV;
COPY USER_ACCOUNT FROM '/Users/eriklacerda/Dev-Projects/savefy/postgresdb/dump/user_account.csv' DELIMITER ',' CSV;
COPY USER_ROLE FROM '/Users/eriklacerda/Dev-Projects/savefy/postgresdb/dump/user_role.csv' DELIMITER ',' CSV;


