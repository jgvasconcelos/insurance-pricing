-- Create customers table

CREATE TABLE IF NOT EXISTS customers (
    id VARCHAR(36) NOT NULL,
    name VARCHAR(150) NOT NULL,
    driver_id VARCHAR(36) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,

    CONSTRAINT customers_drivers_fkey FOREIGN KEY (driver_id) REFERENCES drivers(id),
    CONSTRAINT customers_pkey PRIMARY KEY (id)
)