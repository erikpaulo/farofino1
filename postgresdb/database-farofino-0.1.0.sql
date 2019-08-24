/**
* Criação da aplicação.
* heroku pg:psql postgresql-convex-43609 --app savefy-staging < /Users/eriklacerda/Dev-Projects/savefy/postgresdb/database-savefy-0.1.0.sql
* Localhost  -- \i /Users/eriklacerda/Dev-Projects/farofino/postgresdb/database-farofino-0.1.0.sql
*/

 DROP TABLE IF EXISTS user_role;
 DROP TABLE IF EXISTS user_account;
 DROP TABLE IF EXISTS remember_me_token;
 DROP TABLE IF EXISTS WATCH_LIST;
 DROP TABLE IF EXISTS QUARTERLY_FINANCIAL_STATEMENT;
 DROP TABLE IF EXISTS QFS_RELEASE_CALENDAR;
 DROP TABLE IF EXISTS COMPANY_UNDER_COVER;
 DROP TABLE IF EXISTS USER_GROUP;


CREATE TABLE remember_me_token (
	id 			SERIAL PRIMARY KEY,
	date 		TIMESTAMP,
	series 		VARCHAR(255),
	token_value VARCHAR(255),
	username 	VARCHAR(255)
);

CREATE TABLE USER_GROUP (
	ID   SERIAL PRIMARY KEY,
	NAME VARCHAR(255)
);

CREATE TABLE user_account (
	id 				SERIAL PRIMARY KEY,
	account_locked 	BOOLEAN,
	display_name 	VARCHAR(255),
	email 			VARCHAR(255) UNIQUE,
	image_url 		VARCHAR(255),
	password 		VARCHAR(64),
	trusted_account BOOLEAN,
	google_id       VARCHAR(255) UNIQUE,
	web_site 		VARCHAR(255),
	GROUP_ID        INTEGER REFERENCES USER_GROUP(ID)
);

CREATE TABLE user_role (
	user_id INTEGER REFERENCES user_account(id),
	role    VARCHAR(255),
	PRIMARY KEY (user_id, role)
);

CREATE TABLE COMPANY_UNDER_COVER (
    ID              SERIAL PRIMARY KEY,
    NAME            VARCHAR(50) NOT NULL,
    NICKNAME        VARCHAR(50) NOT NULL,
    CODE            VARCHAR(5)  NOT NULL,
    SECTOR          VARCHAR(50) NOT NULL,
    SUB_SECTOR      VARCHAR(50) NOT NULL
);

CREATE TABLE QFS_RELEASE_CALENDAR (
    ID              SERIAL PRIMARY KEY,
    COMPANY_ID      INTEGER NOT NULL REFERENCES COMPANY_UNDER_COVER(ID),
    DATE            TIMESTAMP NOT NULL,
    QUARTER         VARCHAR(5),
    CONFIRMED       BOOLEAN,
    PROCESSED       BOOLEAN
);

CREATE TABLE QUARTERLY_FINANCIAL_STATEMENT (
    ID                      SERIAL PRIMARY KEY,
    COMPANY_ID              INTEGER NOT NULL REFERENCES COMPANY_UNDER_COVER(ID),
    QUARTER                 VARCHAR(5),
    RELEASE_DATE            TIMESTAMP NOT NULL,
    MARKET_VALUE            BIGINT,
    SHARES_QUANTITY         BIGINT,
    P_L                     DECIMAL,
    L_PA                    DECIMAL,
    P_VP                    DECIMAL,
    VP_A                    DECIMAL,
    P_EBIT                  DECIMAL,
    MARGEM_BRUTA            DECIMAL,
    PSR                     DECIMAL,
    MARGEM_EBIT             DECIMAL,
    P_ATIVOS                DECIMAL,
    MARGEM_LIQUIDA          DECIMAL,
    P_CAPITAL_GIRO          DECIMAL,
    EBIT_ATIVO              DECIMAL,
    P_ATIVO_CIRCULANTE_LIQ  DECIMAL,
    ROIC                    DECIMAL,
    DIVIDEND_YELD           DECIMAL,
    ROE                     DECIMAL,
    EV_EBIT                 DECIMAL,
    LIQUIDEZ_CORRENTE       DECIMAL,
    GIRO_ATIVOS             DECIMAL,
    DB_PL                   DECIMAL,
    CRESCIMENTO_RECEITA_5A  DECIMAL,
    ATIVOS                  BIGINT,
    DIVIDA_BRUTA            BIGINT,
    CAIXA                   BIGINT,
    DIVIDA_LIQUIDA          BIGINT,
    ATIVO_CIRCULANTE        BIGINT,
    PATRIMONIO_LIQUIDO      BIGINT,
    RECEITA_LIQUIDA_12M     BIGINT,
    RECEITA_LIQUIDA_3M      BIGINT,
    EBIT_12M                BIGINT,
    EBIT_3M                 BIGINT,
    LUCRO_LIQUIDO_12M       BIGINT,
    LUCRO_LIQUIDO_3M        BIGINT
);

CREATE TABLE WATCH_LIST (
    ID              SERIAL PRIMARY KEY,
    COMPANY_ID      INTEGER NOT NULL REFERENCES COMPANY_UNDER_COVER(ID),
    USER_GROUP_ID   INTEGER NOT NULL REFERENCES USER_GROUP(id),

    CONSTRAINT WATCH_LIST_01 UNIQUE (USER_GROUP_ID,COMPANY_ID)
);
