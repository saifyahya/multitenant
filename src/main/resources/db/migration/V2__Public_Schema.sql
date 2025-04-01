CREATE TABLE public.user (
                              id BIGSERIAL PRIMARY KEY,  -- Using BIGSERIAL for auto-increment
                              username VARCHAR(255) NOT NULL,
                              password VARCHAR(255) NOT NULL,
                              email VARCHAR(255),
                              phone VARCHAR(255)
);