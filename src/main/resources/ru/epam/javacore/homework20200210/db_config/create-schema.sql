drop table if exists transportations;
drop table if exists cargos;
drop table if exists carriers;

create table if not exists cargos(
id                  BIGINT         NOT null,
name                varchar(50)    NOT null,
weight              INT            NOT null,
cargoType           varchar(50)    NOT null,
size                varchar(50),
material            varchar(50),
description         varchar(200),
expiratonDate       timestamp,
storeTemperature    int,
primary key (id)
);


create table if not exists c(
id              BIGINT NOT null,
name            varchar(50) NOT null,
address         varchar(50) NOT null,
carrierType     varchar(50) NOT null,
primary key (id)
);

create table if not exists transportations(
id              BIGINT NOT null,
id_cargo        BIGINT NOT null,
id_carrier      BIGINT NOT null,
description     varchar(300),
billTo          varchar(100),
date            timestamp NOT null,
primary key (id),
foreign key (id_cargo) references CARGOS(id),
foreign key (id_carrier) references CARRIERS(id)
);