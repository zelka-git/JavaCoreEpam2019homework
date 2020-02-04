insert into CARGOS 
(id,  name, weight, cargoType, expiratonDate, storeTemperature)
Values
(1, 'Banana', 123, 'FOOD', '2013-10-25', 15),
(2, 'Apple', 222, 'FOOD', '2015-10-25', 15);

insert into CARGOS 
(id,  name, weight, cargoType, size, material)
Values
(3, 'Dress', 321, 'CLOTHES', 'M', 'cotton'),
(4, 'Skirt', 213, 'CLOTHES', 'S', 'wool');

insert into CARGOS 
(id, name, weight, cargoType, description)
Values
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
Values
(1, 1, 1, 'this transportation 1', 'P. N. Ivanov', '2009-10-25'),
(2, 2, 2, 'this transportation 2', 'U. N. Romanov', '2010-10-25'),
(3, 3, 3, 'this transportation 3', 'I. D. Titov', '2012-10-25'),
(4, 4, 1, 'this transportation 4', 'P. N. Ivanov', '2009-10-25'),
(5, 5, 2, 'this transportation 5', 'U. N. Romanov', '2010-10-25'),
(6, 6, 3, 'this transportation 6', 'I. D. Titov', '2012-10-25');