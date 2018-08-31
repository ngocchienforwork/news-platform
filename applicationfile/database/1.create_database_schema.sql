--psql -U postgres:	access root account postgres
CREATE USER news WITH PASSWORD '123456';
CREATE DATABASE news OWNER news ENCODING = 'UTF8';
--psql -U emer -d emer (pass: 123456)
CREATE SCHEMA news;

