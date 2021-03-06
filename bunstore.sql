CREATE TABLE Orders
 (ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  FIO VARCHAR(200) NOT NULL,
  Phone VARCHAR(20) NOT NULL,
  Address VARCHAR(200) NOT NULL,
  IsDone BOOLEAN,
  PRIMARY KEY (ID));

CREATE TABLE Reserve
 (ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  WareID INTEGER NOT NULL,
  OrderID INTEGER NOT NULL,
  Quantity INTEGER NOT NULL,
  PRIMARY KEY (ID));

CREATE TABLE Ware
 (ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  Code VARCHAR(20) NOT NULL,
  Name VARCHAR(50) NOT NULL,
  Description VARCHAR(200),
  PRIMARY KEY (ID));

CREATE TABLE Warehouse
 (ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  WareID INTEGER NOT NULL,
  Quantity INTEGER NOT NULL,
  PRIMARY KEY (ID));