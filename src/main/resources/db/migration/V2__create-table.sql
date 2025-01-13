CREATE TABLE provincias (
        id   bigint PRIMARY KEY NOT NULL,
        designation varchar(50) not null
);

CREATE TABLE distritos (
        id bigint PRIMARY KEY NOT NULL,
        designation varchar(60) NOT NULL,
        province_id bigint NOT NULL,
        FOREIGN KEY (province_id) REFERENCES provincias (id)
);