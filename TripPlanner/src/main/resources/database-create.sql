CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS plans CASCADE;
DROP TABLE IF EXISTS daily_trips CASCADE;
DROP TABLE IF EXISTS fav_positions CASCADE;
DROP TABLE IF EXISTS authorities CASCADE;

CREATE TABLE users
(
    username        TEXT        PRIMARY KEY     NOT NULL,
    enabled         BOOLEAN     DEFAULT TRUE    NOT NULL,
    email           TEXT        UNIQUE          NOT NULL    DEFAULT uuid_generate_v4(),
    password        TEXT                        NOT NULL
);

CREATE TABLE plans
(
    plan_id         SERIAL      PRIMARY KEY     NOT NULL,
    username        TEXT                        NOT NULL,
    start_date      DATE                        NOT NULL,
    end_date        DATE                        NOT NULL,
    city            TEXT                        NOT NULL,
    CONSTRAINT fk_plan_user FOREIGN KEY (username) REFERENCES users (username) ON DELETE CASCADE
);

CREATE TABLE daily_trips
(
    daily_trip_id   SERIAL      PRIMARY KEY     NOT NULL,
    plan_id         INTEGER                     NOT NULL,
    date            DATE                        NOT NULL,
    daily_positions TEXT                        NOT NULL,
    CONSTRAINT fk_daily_trip_plan FOREIGN KEY (plan_id) REFERENCES plans (plan_id) ON DELETE CASCADE
);

CREATE TABLE fav_positions
(
    username        TEXT        PRIMARY KEY     NOT NULL,
    fav_positions   TEXT                        NOT NULL,
    CONSTRAINT fk_fav_position_user FOREIGN KEY (username) REFERENCES users (username) ON DELETE CASCADE
);

CREATE TABLE authorities
(
    username        TEXT        PRIMARY KEY     NOT NULL,
    authority       TEXT                        NOT NULL,
    CONSTRAINT fk_authorities_user FOREIGN KEY (username) REFERENCES users (username) ON DELETE CASCADE
);

INSERT INTO users (username, email, password)
VALUES ('J', 'email@gmail.com', 'password'),
       ('J1', 'email1@gmail.com', 'password1'),
       ('J2', 'email2@gmail.com', 'password2');

INSERT INTO users (username, password)
VALUES ('T', 'password-t');

INSERT INTO plans (username, start_date, end_date, city)
VALUES ('J', '2023-10-01', '2023-10-02', '{}'),
       ('J', '2023-10-01', '2023-10-03', '{}'),
       ('J1', '2023-11-01', '2023-12-02', '{}'),
       ('J1', '2023-11-01', '2023-12-02', '{}');

INSERT INTO daily_trips (plan_id, date, daily_positions)
VALUES (1, '2023-10-01', '{}'),
       (1, '2023-10-02', '{}'),
       (2, '2023-10-01', '{}'),
       (2, '2023-10-02', '{}'),
       (2, '2023-10-03', '{}');

INSERT INTO fav_positions (username, fav_positions)
VALUES ('J', '{}'),
       ('J1', '{}');