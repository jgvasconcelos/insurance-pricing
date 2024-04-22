-- Create drivers table indexes

CREATE INDEX IF NOT EXISTS drivers_document_idx ON drivers USING btree (document);