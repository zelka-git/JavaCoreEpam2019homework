insert into CARGOTYPE
(id, name)
VALUES
(1, 'FOOD'),
(2, 'CLOTHES'),
(3, 'COMPUTERS');

insert into CARGOS 
(id, name, weight, cargoType)
Values
(1, 'Banana', 123, 1),
(2, 'Dress', 321, 2),
(3, 'HP', 111, 3),
(4, 'Apple', 222, 1),
(5, 'Skirt', 213, 2),
(6, 'Asus', 241, 3);

insert into CLOTHES 
(id, id_cargo, size, material)
Values
(1, 1, 'M', 'cotton'),
(2, 4, 'S', 'wool');

insert into COMPUTER 
(id, id_cargo, description)
Values
(1, 2, 'this is a computer 1'),
(2, 5, 'this is a computer 2');

insert into FOOD 
(id, id_cargo, expiratonDate, storeTemperature)
Values
(1, 3, '2013-10-25', 15),
(2, 6, '2015-10-25', 15);

insert into CARRIERTYPE 
(id, name)
VALUES
(1, 'SHIP'),
(2, 'PLANE'),
(3, 'CAR'),
(4, 'TRAIN');

insert into CARRIERS 
(id, name, address, carrierType)
Values
(1, 'Fast delivery', 'Moscow', 1),
(2, 'Slow delivery', 'Kiev', 2),
(3, 'Super delivery', 'Rostov', 3);

insert into TRANSPORTATIONS 
(id, id_cargo, id_carrier, description, billTo, date)
Values
(1, 1, 1, 'this transportation 1', 'P. N. Ivanov', '2009-10-25'),
(2, 2, 2, 'this transportation 2', 'U. N. Romanov', '2010-10-25'),
(3, 3, 3, 'this transportation 3', 'I. D. Titov', '2012-10-25'),
(4, 4, 1, 'this transportation 4', 'P. N. Ivanov', '2009-10-25'),
(5, 5, 2, 'this transportation 5', 'U. N. Romanov', '2010-10-25'),
(6, 6, 3, 'this transportation 6', 'I. D. Titov', '2012-10-25');