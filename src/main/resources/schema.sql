CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255),
                       email VARCHAR(255),
                       password VARCHAR(255),
                       phone VARCHAR(50),
                       home_address VARCHAR(255),
                       office_address VARCHAR(255),
                       preferred_arrival_start TIME,
                       preferred_arrival_end TIME,
                       flexibility_minutes INTEGER,
                       flexibility_km DOUBLE PRECISION,
                       role VARCHAR(255)
);

CREATE TABLE driver (
                        id INTEGER PRIMARY KEY REFERENCES users(id),
                        available_seats INTEGER,
                        cost_per100kmeur DOUBLE PRECISION
);

CREATE TABLE passenger (
                           id INTEGER PRIMARY KEY REFERENCES users(id)
);