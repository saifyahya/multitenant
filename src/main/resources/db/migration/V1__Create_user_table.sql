CREATE SCHEMA tenant1;
CREATE SCHEMA tenant2;

CREATE TABLE tenant1.user (
                              id BIGSERIAL PRIMARY KEY,  -- Using BIGSERIAL for auto-increment
                              username VARCHAR(255) NOT NULL,
                              password VARCHAR(255) NOT NULL,
                              email VARCHAR(255),
                              phone VARCHAR(255)
);

CREATE TABLE tenant2.user (
                              id BIGSERIAL PRIMARY KEY,  -- Using BIGSERIAL for auto-increment
                              username VARCHAR(255) NOT NULL,
                              password VARCHAR(255) NOT NULL,
                              email VARCHAR(255),
                              phone VARCHAR(255)
);
