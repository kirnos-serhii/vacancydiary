ALTER TABLE users
    ADD COLUMN IF NOT EXISTS role varchar(50);

ALTER TABLE users ALTER COLUMN role SET NOT NULL;