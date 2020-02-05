insert into CARGOS 
(id,  name, weight, cargoType, expiratonDate, storeTemperature)
values
(1, 'Banana', 123, 'FOOD', parsedatetime('20-09-2025 18:47', 'dd-MM-yyyy hh:mm'), 15),
(2, 'Apple', 222, 'FOOD', parsedatetime('20-09-2025 18:47', 'dd-MM-yyyy hh:mm'), 15);

insert into CARGOS 
(id,  name, weight, cargoType, size, material)
values
(3, 'Dress', 321, 'CLOTHES', 'M', 'cotton'),
(4, 'Skirt', 213, 'CLOTHES', 'S', 'wool');

insert into CARGOS 
(id, name, weight, cargoType, description)
values
(5, 'HP', 111, 'COMPUTERS', 'this is a computer 1'),
(6, 'Asus', 241, 'COMPUTERS', 'this is a computer 2');


insert into CARRIERS 
(id, name, address, carrierType)
Values
(1, 'Fast delivery', 'Moscow', 'SHIP'),
(2, 'Slow delivery', 'Kiev', 'TRAIN'),
(3, 'Super delivery', 'Rostov', 'PLANE');

insert into TRANSPORTATIONS 
(id, id_cargo, id_carrier, description, billTo, date)
values
(1, 1, 1, 'this transportation 1', 'P. N. Ivanov', parsedatetime('20-09-2025 18:47', 'dd-MM-yyyy hh:mm')),
(2, 2, 2, 'this transportation 2', 'U. N. Romanov', parsedatetime('20-09-2025 18:47', 'dd-MM-yyyy hh:mm')),
(3, 3, 3, 'this transportation 3', 'I. D. Titov', parsedatetime('20-09-2025 18:47', 'dd-MM-yyyy hh:mm')),
(4, 4, 1, 'this transportation 4', 'P. N. Ivanov', parsedatetime('20-09-2025 18:47', 'dd-MM-yyyy hh:mm')),
(5, 5, 2, 'this transportation 5', 'U. N. Romanov', parsedatetime('20-09-2025 18:47', 'dd-MM-yyyy hh:mm')),
(6, 6, 3, 'this transportation 6', 'I. D. Titov', parsedatetime('20-09-2025 18:47', 'dd-MM-yyyy hh:mm'));