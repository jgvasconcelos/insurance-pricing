-- Create accidents table indexes

CREATE INDEX IF NOT EXISTS accidents_driver_id_idx ON accidents USING btree (driver_id);
CREATE INDEX IF NOT EXISTS accidents_car_id_idx ON accidents USING btree (car_id);