DROP TABLE IF EXISTS car_models CASCADE;
DROP TABLE IF EXISTS cars CASCADE;

CREATE TABLE  car_models (
    model_id SERIAL PRIMARY KEY,
    model text NOT NULL,
    manufacturer text NOT NULL
);

CREATE TABLE cars (
    reg_id SERIAL PRIMARY KEY,
    model_id integer NOT NULL REFERENCES car_models(model_id),
    embedded_systems text[],
    upholstery text,
    color text NOT NULL,
    seat_type text NOT NULL,
    extra_interior_components text[],
    last_tech_inspection timestamp NOT NULL,
    mileage integer,
    price decimal,
    test_drive_clients integer[]
);
