CREATE TABLE IF NOT EXISTS accounts (
    id              BIGINT          NOT NULL    AUTO_INCREMENT,
    email           VARCHAR(64)     NOT NULL    UNIQUE,
    password        VARCHAR(64)     NOT NULL,
    name 	        VARCHAR(64)     NOT NULL,
    role            VARCHAR(32)     NOT NULL,
    enabled         BOOLEAN         DEFAULT FALSE,
    created_date    TIMESTAMP       NOT NULL,
    last_modified   TIMESTAMP       NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS persistent_logins (
    username    VARCHAR(64)     NOT NULL,
    series      VARCHAR(64)     NOT NULL,
    token       VARCHAR(64)     NOT NULL,
    last_used   TIMESTAMP       NOT NULL,
    PRIMARY KEY (series)
);

-- sia 5th P.62