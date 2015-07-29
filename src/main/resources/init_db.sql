CREATE DATABASE Logistics;

CREATE TABLE cargo (
  id int NOT NULL UNIQUE AUTO_INCREMENT,
  name VARCHAR (15),
  volume INT  (4) NOT NULL,
  destination VARCHAR(15) NOT NULL,
  state VARCHAR(10),
  way_bill_id INT,
  PRIMARY KEY (id)
);

CREATE TABLE drivers (
  id int NOT NULL UNIQUE AUTO_INCREMENT,
  full_name VARCHAR (15),
  car_volume INT  (4) NOT NULL,
  busy BOOLEAN,
  PRIMARY KEY (id)
);

CREATE TABLE operators (
  id int NOT NULL UNIQUE AUTO_INCREMENT,
  full_name VARCHAR (15),
  pass VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE way_bills (
  id int NOT NULL UNIQUE AUTO_INCREMENT,
  driver_id INT,

  PRIMARY KEY (id)
);