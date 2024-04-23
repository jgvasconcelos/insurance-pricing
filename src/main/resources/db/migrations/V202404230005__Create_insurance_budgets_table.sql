-- Create insurance_budgets table

CREATE TABLE IF NOT EXISTS insurance_budgets (
    id VARCHAR(36) NOT NULL,
    driver_id VARCHAR(36) NOT NULL,
    car_id VARCHAR(36) NOT NULL,
    is_active BOOLEAN NOT NULL,
    budget_amount NUMERIC(12, 2) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,

    CONSTRAINT insurance_budgets_pkey PRIMARY KEY (id),
    CONSTRAINT insurance_budgets_drivers_fkey FOREIGN KEY (driver_id) REFERENCES drivers(id),
    CONSTRAINT insurance_budgets_car_fkey FOREIGN KEY (car_id) REFERENCES cars(id)
)