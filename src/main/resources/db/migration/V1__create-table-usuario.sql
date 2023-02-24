CREATE TABLE usuario
(
    id        SERIAL NOT NULL,
    firstname VARCHAR(255),
    lastname  VARCHAR(255),
    email     VARCHAR(255),
    password  VARCHAR(255),
    CONSTRAINT pk_usuario PRIMARY KEY (id)
);