drop database vending_machine;
CREATE DATABASE vending_machine;
use vending_machine;


CREATE TABLE IF NOT EXISTS items (
    itemId INT NOT NULL AUTO_INCREMENT,
    itemName VARCHAR(30) NOT NULL,
    itemPrice INT NOT NULL,
    PRIMARY KEY (itemId)
);

CREATE TABLE IF NOT EXISTS inventory (
    inventoryId INT NOT NULL AUTO_INCREMENT,
    itemId INT NOT NULL,
    inventoryLevel INT,
    PRIMARY KEY (inventoryId)
);

ALTER TABLE inventory 
ADD CONSTRAINT fk_itemId FOREIGN KEY (itemId) REFERENCES items (itemId);

INSERT INTO items (itemName, itemPrice) VALUES ('APPLE',3);
insert into items (itemName, itemPrice) VALUES ('JACKFRUIT',2);
insert into items (itemName, itemPrice) VALUES ('PAPAYA',6);
insert into items (itemName, itemPrice) VALUES ('MANGO',1);
insert into items (itemName, itemPrice) VALUES ('FUZZYKIWI',3);
insert into items (itemName, itemPrice) VALUES ('POMEGRANATE',1);
insert into items (itemName, itemPrice) VALUES ('APRICOT',2);
insert into items (itemName, itemPrice) VALUES ('AVOCADO',4);
insert into items (itemName, itemPrice) VALUES ('WATERMELON',5);


INSERT INTO inventory (itemId, inventoryLevel) VALUES (1,300);
INSERT INTO inventory (itemId, inventoryLevel) VALUES (2,470);
INSERT INTO inventory (itemId, inventoryLevel) VALUES (3,200);
INSERT INTO inventory (itemId,inventoryLevel) VALUES (4,200);
INSERT INTO inventory (itemId,inventoryLevel) VALUES (5,200);
INSERT INTO inventory (itemId,inventoryLevel) VALUES (6,200);
INSERT INTO inventory (itemId,inventoryLevel) VALUES (7,200);
INSERT INTO inventory (itemId,inventoryLevel) VALUES (8,200);
INSERT INTO inventory (itemId,inventoryLevel) VALUES (9,200);


-- test data base
drop database  vending_machine_test;
CREATE DATABASE vending_machine_test;
use vending_machine_test;

CREATE TABLE IF NOT EXISTS items (
    itemId INT NOT NULL AUTO_INCREMENT,
    itemName VARCHAR(30) NOT NULL,
    itemPrice INT NOT NULL,
    PRIMARY KEY (itemId)
);

CREATE TABLE IF NOT EXISTS inventory (
    inventoryId INT NOT NULL AUTO_INCREMENT,
    itemId INT NOT NULL,
    inventoryLevel INT,
    PRIMARY KEY (inventoryId)
);

ALTER TABLE inventory 
ADD CONSTRAINT fk_itemId FOREIGN KEY (itemId) REFERENCES items (itemId);
INSERT INTO items (itemName, itemPrice) VALUES ('APPLE',3);
insert into items (itemName, itemPrice) VALUES ('JACKFRUIT',2);
insert into items (itemName, itemPrice) VALUES ('PAPAYA',6);
INSERT INTO inventory (itemId, inventoryLevel) VALUES (1,3);
INSERT INTO inventory (itemId, inventoryLevel) VALUES (2,4);
INSERT INTO inventory (itemId, inventoryLevel) VALUES (3,6);