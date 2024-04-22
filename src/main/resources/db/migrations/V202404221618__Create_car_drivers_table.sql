-- Create car_drivers table

CREATE TABLE IF NOT EXISTS car_drivers (
    id VARCHAR(36) NOT NULL,
    driver_id VARCHAR(36) NOT NULL,
    car_id VARCHAR(36) NOT NULL,
    is_main_driver BOOLEAN NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,

    CONSTRAINT car_drivers_pkey PRIMARY KEY (id),
    CONSTRAINT car_drivers_drivers_fkey FOREIGN KEY (driver_id) REFERENCES drivers(id),
    CONSTRAINT car_drivers_car_fkey FOREIGN KEY (car_id) REFERENCES cars(id)
)