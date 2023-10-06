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
    plan_id         INTEGER                 NOT NULL,
    date            DATE                    NOT NULL,
    daily_positions JSONB                   NOT NULL,
    PRIMARY KEY (plan_id, date),
    CONSTRAINT fk_daily_trip_plan FOREIGN KEY (plan_id) REFERENCES plans (plan_id) ON DELETE CASCADE
);

CREATE TABLE fav_positions
(
    backend_user_id INTEGER     PRIMARY KEY NOT NULL,
    fav_positions   JSONB                   NOT NULL,
    CONSTRAINT fk_fav_position_user FOREIGN KEY (backend_user_id) REFERENCES users (backend_user_id) ON DELETE CASCADE
);