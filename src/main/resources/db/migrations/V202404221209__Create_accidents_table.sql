-- Create accidents table

CREATE TABLE IF NOT EXISTS accidents (
    id VARCHAR(36) NOT NULL,
    driver_id VARCHAR(36) NOT NULL,
    car_id VARCHAR(36) NOT NULL,
    accident_date DATE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,

    CONSTRAINT accidents_pkey PRIMARY KEY (id),
    CONSTRAINT accidents_drivers_fkey FOREIGN KEY (driver_id) REFERENCES drivers(id),
    CONSTRAINT accidents_car_fkey FOREIGN KEY (car_id) REFERENCES cars(id)
)