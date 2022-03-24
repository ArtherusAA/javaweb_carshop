DROP TABLE IF EXISTS clients CASCADE;

CREATE TABLE clients (
    client_id SERIAL PRIMARY KEY,
    name text NOT NULL,
    address text NOT NULL,
    phone text,
    email text,
    orders integer[]
);