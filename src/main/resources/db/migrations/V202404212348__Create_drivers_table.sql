-- Create cars table

CREATE TABLE IF NOT EXISTS drivers (
    id VARCHAR(36) NOT NULL,
    name VARCHAR(150) NOT NULL,
    document VARCHAR(150) NOT NULL,
    birthdate DATE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,

    CONSTRAINT drivers_pkey PRIMARY KEY (id)
)