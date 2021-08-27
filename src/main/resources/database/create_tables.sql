CREATE TABLE tariff
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(100) NOT NULL,
    minute_cost FLOAT        NOT NULL,
    mb_cost     FLOAT        NOT NULL,
    sms_cost    FLOAT        NOT NULL
);

CREATE TABLE device
(
    id    SERIAL PRIMARY KEY,
    model VARCHAR(100) NOT NULL
);

CREATE TABLE sms
(
    id           SERIAL PRIMARY KEY,
    text         VARCHAR(256) NOT NULL,
    user_id      INTEGER      NOT NULL,
    interlocutor VARCHAR(30)  NOT NULL,
    created      TIMESTAMP    NOT NULL
);

CREATE TABLE call
(
    id           SERIAL PRIMARY KEY,
    duration     INTEGER     NOT NULL,
    user_id      INTEGER     NOT NULL,
    interlocutor VARCHAR(30) NOT NULL,
    created      TIMESTAMP   NOT NULL
);

CREATE TABLE traffic
(
    id      SERIAL PRIMARY KEY,
    traffic INTEGER   NOT NULL,
    user_id INTEGER   NOT NULL,
    created TIMESTAMP NOT NULL
);

CREATE TABLE "user"
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    tariff_id  INTEGER     NOT NULL,
    device_id  INTEGER
);
