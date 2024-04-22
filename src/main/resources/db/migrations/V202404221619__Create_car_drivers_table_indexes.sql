-- Create car_drivers table indexes

CREATE INDEX IF NOT EXISTS car_drivers_driver_id_idx ON car_drivers USING btree (driver_id);
CREATE INDEX IF NOT EXISTS car_drivers_car_id_idx ON car_drivers USING btree (car_id);