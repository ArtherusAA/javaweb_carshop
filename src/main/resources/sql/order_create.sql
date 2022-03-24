DROP TABLE IF EXISTS orders CASCADE;

DROP TYPE IF EXISTS order_status CASCADE;

CREATE TYPE order_status AS ENUM(
    'PROCESSING',
    'WAITING_SUPPLY',
    'IN_LOT',
    'TEST_DRIVE',
    'DONE'
);

CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    date_time timestamp NOT NULL,
    client integer NOT NULL REFERENCES clients(client_id),
    car_id integer NOT NULL REFERENCES cars(reg_id),
    is_test_drive_neccesary boolean NOT NULL,
    status order_status NOT NULL
);