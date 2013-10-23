REVOKE ALL ON DATABASE @db.name@ FROM @db.use

DROP USER IF EXISTS @db.user@;

DROP DATABASE @db.name@;