CREATE DATABASE @db.name@;

CREATE USER @db.user@ PASSWORD '@db.password@';

GRANT ALL ON DATABASE @db.name@ TO postgres;
GRANT ALL ON DATABASE @db.name@ TO @db.user@;
GRANT ALL ON DATABASE @db.name@ TO michalek;

