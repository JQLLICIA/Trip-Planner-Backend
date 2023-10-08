DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS plans CASCADE;
DROP TABLE IF EXISTS daily_trips CASCADE;
DROP TABLE IF EXISTS fav_positions CASCADE;

CREATE TABLE users
(
    backend_user_id SERIAL      PRIMARY KEY NOT NULL,
    shown_user_id   TEXT                    NOT NULL,
    email           TEXT        UNIQUE      NOT NULL,
    password        TEXT                    NOT NULL
);

CREATE TABLE plans
(
    plan_id         SERIAL      PRIMARY KEY NOT NULL,
    backend_user_id INTEGER                 NOT NULL,
    start_date      DATE                    NOT NULL,
    end_date        DATE                    NOT NULL,
    city            JSONB                   NOT NULL,
    CONSTRAINT fk_plan_user FOREIGN KEY (backend_user_id) REFERENCES users (backend_user_id) ON DELETE CASCADE
);

CREATE TABLE daily_trips
(
    daily_trip_id   SERIAL      PRIMARY KEY NOT NULL,
    plan_id         INTEGER                 NOT NULL,
    date            DATE                    NOT NULL,
    daily_positions JSONB                   NOT NULL,
    CONSTRAINT fk_daily_trip_plan FOREIGN KEY (plan_id) REFERENCES plans (plan_id) ON DELETE CASCADE
);

CREATE TABLE fav_positions
(
    backend_user_id INTEGER     PRIMARY KEY NOT NULL,
    fav_positions   JSONB                   NOT NULL,
    CONSTRAINT fk_fav_position_user FOREIGN KEY (backend_user_id) REFERENCES users (backend_user_id) ON DELETE CASCADE
);

INSERT INTO users (shown_user_id, email, password)
VALUES ('J', 'email@gmail.com', 'password'),
       ('J1', 'email1@gmail.com', 'password1'),
       ('J2', 'email2@gmail.com', 'password2');

INSERT INTO plans (backend_user_id, start_date, end_date, city)
VALUES (1, '2023-10-01', '2023-10-02', '{}'::jsonb),
       (1, '2023-10-01', '2023-10-03', '{}'::jsonb),
       (2, '2023-11-01', '2023-12-02', '{}'::jsonb),
       (2, '2023-11-01', '2023-12-02', '{}'::jsonb);

INSERT INTO daily_trips (plan_id, date, daily_positions)
VALUES (1, '2023-10-01', '{}'::jsonb),
       (1, '2023-10-02', '{}'::jsonb),
       (2, '2023-10-01', '{}'::jsonb),
       (2, '2023-10-02', '{}'::jsonb),
       (2, '2023-10-03', '{}'::jsonb);

INSERT INTO fav_positions (backend_user_id, fav_positions)
VALUES (1, '{}'::jsonb),
       (2, '{}'::jsonb);