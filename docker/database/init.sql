CREATE USER productdbu WITH PASSWORD 'admin%';
CREATE DATABASE productdb;
GRANT pg_read_all_data TO productdbu;
GRANT pg_write_all_data TO productdbu;