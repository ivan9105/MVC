CREATE TABLE `TEST_AUTHORS` (
  `ID`         VARCHAR(255) NOT NULL,
  `LASTNAME`   VARCHAR(50) DEFAULT NULL,
  `middlename` VARCHAR(50) DEFAULT NULL,
  `NAME`       VARCHAR(50)  NOT NULL,
  PRIMARY KEY (`ID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `TEST_BOOKS` (
  `ID`        VARCHAR(255) NOT NULL,
  `NAME`      VARCHAR(50)  NOT NULL,
  `YEAR`      INT(11)      NOT NULL,
  `AUTHOR_ID` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK7A7694BDFCECE03A` (`AUTHOR_ID`),
  CONSTRAINT `FK7A7694BDFCECE03A` FOREIGN KEY (`AUTHOR_ID`) REFERENCES `TEST_AUTHORS` (`ID`),
  CONSTRAINT `FKh2voxnppe2d3gf5fwm50dpbxn` FOREIGN KEY (`AUTHOR_ID`) REFERENCES `TEST_AUTHORS` (`ID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `SHOP_CATEGORY` (
  `ID`          VARCHAR(255) NOT NULL,
  `NAME_`       TEXT         DEFAULT NULL,
  `DESCRIPTION` TEXT         DEFAULT NULL,
  `LEVEL_`      INT(11)      NOT NULL,
  `PARENT_ID`   VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK1A7694BDFCECF034` (`PARENT_ID`),
  CONSTRAINT `FK1A7694BDFCECF034` FOREIGN KEY (`PARENT_ID`) REFERENCES `SHOP_CATEGORY` (`ID`),
  CONSTRAINT `FKh2voxnppe2d4gf3fwm11dpbxn` FOREIGN KEY (`PARENT_ID`) REFERENCES `SHOP_CATEGORY` (`ID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `SHOP_ITEM` (
  `ID`          VARCHAR(255) NOT NULL,
  `NAME_`       TEXT         DEFAULT NULL,
  `DESCRIPTION` TEXT         DEFAULT NULL,
  `COUNT_`      INT(11)      NOT NULL,
  `CATEGORY_ID` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK7A7694BDFCECF034` (`CATEGORY_ID`),
  CONSTRAINT `FK7A7694BDFCECF034` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `SHOP_CATEGORY` (`ID`),
  CONSTRAINT `FKh2voxnppe2d4gf3fwm10dpbxn` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `SHOP_CATEGORY` (`ID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `SHOP_USER` (
  `ID`         VARCHAR(255) NOT NULL,
  `NAME_`      TEXT DEFAULT NULL,
  `SURNAME`    TEXT DEFAULT NULL,
  `MIDDLENAME` TEXT DEFAULT NULL,
  `LOGIN`      TEXT DEFAULT NULL,
  `PASSWORD`   TEXT DEFAULT NULL,
  PRIMARY KEY (`ID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `shop_category` VALUES
  ('05d8a44b-e144-4f78-9fd4-c4b73a57379b','','Док-станции для накопителей',2,'42cb8ed0-f4b6-4a38-bed4-1858e7e31dca'),
  ('0a939034-0df2-4ece-81ab-902e3e0500c2','','Защитные стекла для планшетов',2,'857c7224-f080-4eb9-bb02-6eade07c308e'),
  ('22e5086f-1b2d-426e-95ce-99d9d9d99e87','','Офис и делопроизводство',2,'9826e934-4f73-4206-b8c0-63ad3cea2112'),
  ('23454b1c-df72-4369-a071-546fbf78066c','','Комплектующие для ПК',0,NULL),
  ('2f84e9da-feae-4c6a-bdeb-75e5f3b7625f','','Сумки для планшетов и нетбуков',2,'857c7224-f080-4eb9-bb02-6eade07c308e'),
  ('42cb8ed0-f4b6-4a38-bed4-1858e7e31dca','','Аксессуары для накопителей',1,'23454b1c-df72-4369-a071-546fbf78066c'),
  ('44e791d4-eb03-4905-b9c8-e0ccf8efd954','','Компьютеры и периферия',0,NULL),
  ('51cbc3e4-ff05-4d1b-9ab8-d50ed54fbc83','','Чехлы для планшетов',2,'857c7224-f080-4eb9-bb02-6eade07c308e'),
  ('52d39665-74c6-42f7-aa8b-8d4c54d8d554','','Антивирусы',2,'9826e934-4f73-4206-b8c0-63ad3cea2112'),
  ('5edcb8b9-ab3b-46a6-907a-320e1cc7546a','','Внешние боксы для накопителей',2,'42cb8ed0-f4b6-4a38-bed4-1858e7e31dca'),
  ('7bb74ea2-daa8-4adf-a2ae-4c9093c9eb83','','Салазки для накопителей',1,'23454b1c-df72-4369-a071-546fbf78066c'),
  ('857c7224-f080-4eb9-bb02-6eade07c308e','','Аксессуары для планшетов',1,'e37c64c8-837b-494a-9dca-f8c7b850486c'),
  ('8699be40-9dc0-41e9-89bc-fcf373cdf521','','Планшеты',1,'e37c64c8-837b-494a-9dca-f8c7b850486c'),
  ('96b37291-2de5-483f-ab48-af5229fb93ba','','Процессоры',1,'23454b1c-df72-4369-a071-546fbf78066c'),
  ('9826e934-4f73-4206-b8c0-63ad3cea2112','','Программное обеспечение',1,'44e791d4-eb03-4905-b9c8-e0ccf8efd954'),
  ('bdd94748-fbc5-4926-a3fa-4c90670f7c9a','','Ноутбуки',1,'e37c64c8-837b-494a-9dca-f8c7b850486c'),
  ('ca56bda5-5435-4257-9af2-f6abfdbf5b17','','Системные блоки',2,'e0093c32-fcf4-42e2-b7ff-4031b8fad198'),
  ('cdd3cef3-863f-4ce8-aa40-d262bf8283e3','','Блоки питания',1,'23454b1c-df72-4369-a071-546fbf78066c'),
  ('d9b78cf3-984d-4cbe-8bf9-8bd61c8c7168','','Микрокомпьютеры',2,'e0093c32-fcf4-42e2-b7ff-4031b8fad198'),
  ('db90474f-30a5-4068-ab71-6c8cde85c381','','Карты памяти для планшетов',2,'857c7224-f080-4eb9-bb02-6eade07c308e'),
  ('dbfcc03c-00a8-4c07-b8a8-e1fc55a83c80','','Мультимедиа и прочее ПО',2,'9826e934-4f73-4206-b8c0-63ad3cea2112'),
  ('dca27972-7e2e-4236-96c3-f2a50389e2f5','','Защитные пленки для планшетов',2,'857c7224-f080-4eb9-bb02-6eade07c308e'),
  ('e0093c32-fcf4-42e2-b7ff-4031b8fad198','','Компьютеры',1,'44e791d4-eb03-4905-b9c8-e0ccf8efd954'),
  ('e37c64c8-837b-494a-9dca-f8c7b850486c','','Ноутбуки и Планшеты',0,NULL),
  ('e47e1038-9d5b-4f6f-941b-3ddc8d19e31e','','Видеокарты',1,'23454b1c-df72-4369-a071-546fbf78066c'),
  ('ec823785-31a6-49c6-9c6a-946716415312','','SSD накопители',1,'23454b1c-df72-4369-a071-546fbf78066c'),
  ('ed5de5db-c339-4e4c-93aa-9f64a7f38a4f','','Жесткие диски 3.5',1,'23454b1c-df72-4369-a071-546fbf78066c'),
  ('fb5d101a-f649-45d3-bd62-08f90098618b','','Операционные системы',2,'9826e934-4f73-4206-b8c0-63ad3cea2112'),
  ('fed9d391-7e99-4739-94b4-7468cdfb05a9','','Моноблоки',2,'e0093c32-fcf4-42e2-b7ff-4031b8fad198'),
  ('ff6d0e66-89ce-4dcf-977e-eca530388702','','Клавиатуры для планшетов',2,'857c7224-f080-4eb9-bb02-6eade07c308e');
