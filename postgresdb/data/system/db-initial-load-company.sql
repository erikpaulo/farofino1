/**
* Criação da aplicação.
* heroku pg:psql postgresql-convex-43609 --app savefy-staging < /Users/eriklacerda/Dev-Projects/savefy/postgresdb/database-savefy-0.1.0.sql
* Localhost  -- \i /Users/eriklacerda/Dev-Projects/farofino/postgresdb/data/system/db-initial-load-company.sql
*/

 INSERT INTO COMPANY_UNDER_COVER VALUES (DEFAULT, 'MRV Engenharia', 'MRV Engenharia', 'MRVE3','Construção e Engenharia', 'Construção Civil');
 INSERT INTO COMPANY_UNDER_COVER VALUES (DEFAULT, 'Itausa Investimentos', 'Itaúsa', 'ITSA4','Financeiros', 'Bancos');
 INSERT INTO COMPANY_UNDER_COVER VALUES (DEFAULT, 'M.DIASBRANCO', 'MDiasBranco', 'MDIA3','Alimentos', 'Alimentos Diversos');