CREATE table CARGOTYPE
(
id INT NOT null,
name varchar(50) NOT null, 
primary key (id)
);

CREATE table CARGOS
(
id INT NOT null,
name varchar(50) NOT null,
weight INT NOT null,
cargoType INT NOT null, 
primary key (id),
foreign key (cargoType) references CARGOTYPE(id)
);

CREATE table CLOTHES
(
id INT NOT null,
id_cargo INT NOT null,
size varchar(50) NOT null, 
material varchar(50) NOT null,
primary key (id),
foreign key (id_cargo) references CARGOS(id)
);

CREATE table COMPUTER
(
id INT NOT null,
id_cargo INT NOT null,
description varchar(200) NOT null, 
primary key (id),
foreign key (id_cargo) references CARGOS(id)
);

CREATE table FOOD
(
id INT NOT null,
id_cargo INT NOT null,
expiratonDate date NOT null, 
storeTemperature int NOT null,
primary key (id),
foreign key (id_cargo) references CARGOS(id)
);


CREATE table CARRIERTYPE
(
id INT NOT null,
name varchar(50) NOT null,
primary key (id)
);

CREATE table CARRIERS
(
id INT NOT null,
name varchar(50) NOT null,
address varchar(50) NOT null,
carrierType INT NOT null,  
primary key (id),
foreign key (carrierType) references CARRIERTYPE(id)
);

CREATE table TRANSPORTATIONS
(
id INT NOT null,
id_cargo INT NOT null,
id_carrier INT NOT null, 
description varchar(300),
billTo varchar(100),
date date NOT null,
primary key (id),
foreign key (id_cargo) references CARGOS(id),
foreign key (id_carrier) references CARRIERS(id)
);