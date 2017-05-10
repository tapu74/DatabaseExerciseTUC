CREATE TABLE pizzen	(
	pizzaId	integer PRIMARY KEY,
	pizzaName varchar(100) UNIQUE,
	pizzaSize varchar(30) NOT NULL,
  basePrice numeric(8,2) NOT NULL
);

INSERT INTO pizzen VALUES(1,'Pizza Salami Supreme, Classic','25 cm','4.70');
INSERT INTO pizzen VALUES(2,'Pizza Tuna, Classic','25 cm','4.95');
INSERT INTO pizzen VALUES(3,'Pizza Athena, Classic','25 cm','4.95');
INSERT INTO pizzen VALUES(4,'Pizza GrÃ¼ner Garten, Classic','25 cm','5.40');
INSERT INTO pizzen VALUES(5,'Pizza Italia, Classic','25 cm','5.80');
INSERT INTO pizzen VALUES(6,'Pizza Lucca, Classic','25 cm','5.80');
INSERT INTO pizzen VALUES(7,'Pizza Mediterrana, Classic','25 cm','6.95');
INSERT INTO pizzen VALUES(8,'Pizza Bombay, Classic','25 cm','5.80');
INSERT INTO pizzen VALUES(9,'Pizza Dutchman, Classic','25 cm','5.80');
INSERT INTO pizzen VALUES(10,'Pizza Olymp, Classic','25 cm','6.65');
INSERT INTO pizzen VALUES(11,'Pizza Azzurri, Classic','25 cm','6.65');