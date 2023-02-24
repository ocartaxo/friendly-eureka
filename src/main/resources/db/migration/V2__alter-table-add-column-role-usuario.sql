ALTER TABLE usuario add role VARCHAR(10);
UPDATE usuario set role = 'user';