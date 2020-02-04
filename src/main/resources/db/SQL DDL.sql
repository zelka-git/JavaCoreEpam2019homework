
CREATE table CARGOS
(
id INT NOT null,
name varchar(50) NOT null,
weight INT NOT null,
cargoType varchar(50) NOT null, 
size varchar(50), 
material varchar(50),
description varchar(200), 
expiratonDate date, 
storeTemperature int,
primary key (id)
);


CREATE table CARRIERS
(
id INT NOT null,
name varchar(50) NOT null,
address varchar(50) NOT null,
carrierType varchar(50) NOT null,  
primary key (id)
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