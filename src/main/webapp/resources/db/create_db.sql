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
  `PRICE`       DECIMAL      NOT NULL,
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
  ('05d8a44b-e144-4f78-9fd4-c4b73a57379b', '', 'Док-станции для накопителей', 2,
   '42cb8ed0-f4b6-4a38-bed4-1858e7e31dca'),
  ('0a939034-0df2-4ece-81ab-902e3e0500c2', '', 'Защитные стекла для планшетов', 2, '857c7224-f080-4eb9-bb02-6eade07c308e'),
  ('22e5086f-1b2d-426e-95ce-99d9d9d99e87', '', 'Офис и делопроизводство', 2, '9826e934-4f73-4206-b8c0-63ad3cea2112'),
  ('23454b1c-df72-4369-a071-546fbf78066c', '', 'Комплектующие для ПК', 0, NULL),
  ('2f84e9da-feae-4c6a-bdeb-75e5f3b7625f', '', 'Сумки для планшетов и нетбуков', 2, '857c7224-f080-4eb9-bb02-6eade07c308e'),
  ('42cb8ed0-f4b6-4a38-bed4-1858e7e31dca', '', 'Аксессуары для накопителей', 1, '23454b1c-df72-4369-a071-546fbf78066c'),
  ('44e791d4-eb03-4905-b9c8-e0ccf8efd954', '', 'Компьютеры и периферия', 0, NULL),
  ('51cbc3e4-ff05-4d1b-9ab8-d50ed54fbc83', '', 'Чехлы для планшетов', 2, '857c7224-f080-4eb9-bb02-6eade07c308e'),
  ('52d39665-74c6-42f7-aa8b-8d4c54d8d554', '', 'Антивирусы', 2, '9826e934-4f73-4206-b8c0-63ad3cea2112'),
  ('5edcb8b9-ab3b-46a6-907a-320e1cc7546a', '', 'Внешние боксы для накопителей', 2, '42cb8ed0-f4b6-4a38-bed4-1858e7e31dca'),
  ('7bb74ea2-daa8-4adf-a2ae-4c9093c9eb83', '', 'Салазки для накопителей', 1, '23454b1c-df72-4369-a071-546fbf78066c'),
  ('857c7224-f080-4eb9-bb02-6eade07c308e', '', 'Аксессуары для планшетов', 1, 'e37c64c8-837b-494a-9dca-f8c7b850486c'),
  ('8699be40-9dc0-41e9-89bc-fcf373cdf521', '', 'Планшеты', 1, 'e37c64c8-837b-494a-9dca-f8c7b850486c'),
  ('96b37291-2de5-483f-ab48-af5229fb93ba', '', 'Процессоры', 1, '23454b1c-df72-4369-a071-546fbf78066c'),
  ('9826e934-4f73-4206-b8c0-63ad3cea2112', '', 'Программное обеспечение', 1, '44e791d4-eb03-4905-b9c8-e0ccf8efd954'),
  ('bdd94748-fbc5-4926-a3fa-4c90670f7c9a', '', 'Ноутбуки', 1, 'e37c64c8-837b-494a-9dca-f8c7b850486c'),
  ('ca56bda5-5435-4257-9af2-f6abfdbf5b17', '', 'Системные блоки', 2, 'e0093c32-fcf4-42e2-b7ff-4031b8fad198'),
  ('cdd3cef3-863f-4ce8-aa40-d262bf8283e3', '', 'Блоки питания', 1, '23454b1c-df72-4369-a071-546fbf78066c'),
  ('d9b78cf3-984d-4cbe-8bf9-8bd61c8c7168', '', 'Микрокомпьютеры', 2, 'e0093c32-fcf4-42e2-b7ff-4031b8fad198'),
  ('db90474f-30a5-4068-ab71-6c8cde85c381', '', 'Карты памяти для планшетов', 2, '857c7224-f080-4eb9-bb02-6eade07c308e'),
  ('dbfcc03c-00a8-4c07-b8a8-e1fc55a83c80', '', 'Мультимедиа и прочее ПО', 2, '9826e934-4f73-4206-b8c0-63ad3cea2112'),
  ('dca27972-7e2e-4236-96c3-f2a50389e2f5', '', 'Защитные пленки для планшетов', 2,
   '857c7224-f080-4eb9-bb02-6eade07c308e'),
  ('e0093c32-fcf4-42e2-b7ff-4031b8fad198', '', 'Компьютеры', 1, '44e791d4-eb03-4905-b9c8-e0ccf8efd954'),
  ('e37c64c8-837b-494a-9dca-f8c7b850486c', '', 'Ноутбуки и Планшеты', 0, NULL),
  ('e47e1038-9d5b-4f6f-941b-3ddc8d19e31e', '', 'Видеокарты', 1, '23454b1c-df72-4369-a071-546fbf78066c'),
  ('ec823785-31a6-49c6-9c6a-946716415312', '', 'SSD накопители', 1, '23454b1c-df72-4369-a071-546fbf78066c'),
  ('ed5de5db-c339-4e4c-93aa-9f64a7f38a4f', '', 'Жесткие диски 3.5', 1, '23454b1c-df72-4369-a071-546fbf78066c'),
  ('fb5d101a-f649-45d3-bd62-08f90098618b', '', 'Операционные системы', 2, '9826e934-4f73-4206-b8c0-63ad3cea2112'),
  ('fed9d391-7e99-4739-94b4-7468cdfb05a9', '', 'Моноблоки', 2, 'e0093c32-fcf4-42e2-b7ff-4031b8fad198'),
  ('ff6d0e66-89ce-4dcf-977e-eca530388702', '', 'Клавиатуры для планшетов', 2, '857c7224-f080-4eb9-bb02-6eade07c308e');

INSERT INTO `shop_item` VALUES ('00f24a0e-a972-403f-997d-0670cc33df0f',5,'','Док-станция для накопителей DEXP BS-HD07 U3','05d8a44b-e144-4f78-9fd4-c4b73a57379b',2550),
  ('01830181-a3fe-4f00-beef-57f5d3bdec74',5,'','15.6\" Ноутбук Lenovo Ideapad 110-15ACL черный','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',14999),
  ('04df61c0-3c2e-4ce5-9771-e24bd5762500',5,'','11.6\" Ноутбук KREZ Ninja 1103 черный','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',14499),
  ('0955446f-4d2b-40de-a0d3-4948925fcc52',5,'','Док-станция для накопителей Agestar 3UBT3','05d8a44b-e144-4f78-9fd4-c4b73a57379b',2850),
  ('0aeeb388-edb9-4f06-8995-5164ae323fe5',5,'','Док-станция для накопителей Orico 6619US3-BK','05d8a44b-e144-4f78-9fd4-c4b73a57379b',2650),
  ('10f4276f-401e-4f78-af0f-ac51ac5fb0ce',5,'','Защитное стекло для планшета RoverPad Tesla Neon 7.0, RoverPad Impulse 7.0, RoverPad Quad','0a939034-0df2-4ece-81ab-902e3e0500c2',350),
  ('11022d99-a12a-4c4b-957f-b161ed4a6eae',5,'','Защитное стекло для планшета Samsung Galaxy Tab 4','0a939034-0df2-4ece-81ab-902e3e0500c2',350),
  ('11bebb1a-1c4e-452c-b96f-1cfeda5a6dda',5,'','Док-станция для накопителей DEXP BS-HD06','05d8a44b-e144-4f78-9fd4-c4b73a57379b',2250),
  ('13273bef-be0f-411b-bacb-290dbb00377a',5,'','11.6\" Ноутбук DELL Inspiron 3162-3041 черный','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',13999),
  ('18e80852-aabe-4121-a86a-bcd1057aa297',5,'','Док-станция для накопителей DEXP BS-U3HD01','05d8a44b-e144-4f78-9fd4-c4b73a57379b',1499),
  ('21339d8d-91e6-4629-a67c-7c186f5781d7',5,'','ПО LeaderTask Персональный Органайзер Стандарт 7','22e5086f-1b2d-426e-95ce-99d9d9d99e87',590),
  ('2c208233-8ce9-4b58-9d99-031c6bb24f00',5,'','14\" Ноутбук DEXP Athena T131 черный','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',14999),
  ('2efe853f-0b3e-4654-b121-9330f4d727de',5,'','11.6\" Ноутбук KREZ Ninja 1103 белый','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',14499),
  ('2f3b6516-03ab-4053-ad0d-60bd6bcd022e',5,'','ПО \"Office 365 Персональный\" + \"Kaspersky Internet Security\" 1ПК на 1 год','22e5086f-1b2d-426e-95ce-99d9d9d99e87',2299),
  ('31388f41-6dc0-44e6-b729-40f6f9ee65ae',5,'','11.6\" Ноутбук KREZ Ninja черный','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',14499),
  ('34b6854b-2762-48ed-a5ad-404880b30ad6',5,'','15.6\" Ноутбук Acer Aspire ES1-522-27BB черный','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',12999),
  ('3b665bb7-ac36-43b3-bf0a-a29150d51847',5,'','10.1\" Ноутбук Irbis NB10 черный','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',8599),
  ('3dadda3f-e0bf-4f07-9e02-a26cd0bc28ee',5,'','11.6\" Ноутбук DELL Inspiron 3162-3058 красный','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',13999),
  ('473a2f74-eb33-46a4-be77-b67736696b43',5,'','10.1\" Ноутбук Irbis NB20 черный','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',9199),
  ('5048ea77-6710-4478-8385-7f71ceeb8732',5,'','11.6\" Ноутбук Prestigio Smartbook 116A черный','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',12990),
  ('50c426b5-dd21-4d54-a008-bb7279acbfe7',5,'','14\" Ноутбук KREZ N1401 Cloudbook бирюзовый','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',14299),
  ('51aa4de1-cb71-4cd9-a806-fbc4cec14412',5,'','Сумка Case Logic LNEO-10','2f84e9da-feae-4c6a-bdeb-75e5f3b7625f',930),
  ('5b225ce6-3a6c-4190-a4cb-21354cdff734',5,'','Док-станция для накопителей Agestar SCBT4','05d8a44b-e144-4f78-9fd4-c4b73a57379b',1250),
  ('6035576a-18a6-4ec9-bc7e-a5696f815084',5,'','Защитное стекло для планшета DEXP Z180','0a939034-0df2-4ece-81ab-902e3e0500c2',320),
  ('629352cb-1628-4a82-874b-ee008632a085',5,'','15.6\" Ноутбук HP 255 G4 черный','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',14499),
  ('62c597e5-b07f-4320-ba7f-a83a13fa6c46',5,'','Док-станция для накопителей Agestar 3UBT','05d8a44b-e144-4f78-9fd4-c4b73a57379b',2250),
  ('65eca5d2-6c07-4065-bb6b-029579b6dd3f',5,'','ПО Microsoft Office 365 персональный','22e5086f-1b2d-426e-95ce-99d9d9d99e87',1799),
  ('664ebe49-f975-4349-a718-2a01b4c3e18a',5,'','14.1\" Ноутбук Prestigio Smartbook 141A черный','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',13499),
  ('7b433fcb-67cb-4188-90da-5601b8723681',5,'','15.6\" Ноутбук Lenovo B5010 черный','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',14299),
  ('7bf724cd-38a0-4273-a5bb-5dbc5196be4f',5,'','11.6\" Ноутбук DEXP Athena T113 серебристый','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',14499),
  ('86f63637-77d1-4bdc-9f3c-008aba1ccbf6',5,'','Док-станция для накопителей DEXP BS-HD08','05d8a44b-e144-4f78-9fd4-c4b73a57379b',1599),
  ('a0635171-6f4c-46e6-8fc9-7626e3627daa',5,'','Док-станция для накопителей Agestar SUBT','05d8a44b-e144-4f78-9fd4-c4b73a57379b',1550),
  ('a1a3d0b7-57a0-45eb-b8f2-5e2e9170c355',5,'','Док-станция для накопителей DEXP AT-HA002','05d8a44b-e144-4f78-9fd4-c4b73a57379b',590),
  ('ab8f2282-afb5-43fe-9ce9-8a378b824795',5,'','Защитное стекло для планшета Asus ZenPad S 8.0 Z580CA, Asus ZenPad S 8.0 Z580C','0a939034-0df2-4ece-81ab-902e3e0500c2',390),
  ('b133787d-bf04-44df-ad9a-d8308ab9254d',5,'','15.6\" Ноутбук Acer Aspire ES1-531-C6H4 черный','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',14499),
  ('b6608c2b-8d30-4d58-8158-896354e40940',5,'','Защитное стекло для планшета Lenovo A3500','0a939034-0df2-4ece-81ab-902e3e0500c2',350),
  ('bc7033b1-0ad1-4aa1-b4bf-f6f7489eb97c',5,'','ПО Microsoft Office 365 персональный','22e5086f-1b2d-426e-95ce-99d9d9d99e87',2250),
  ('be852c96-3fad-4eaf-acc4-9ce032de468d',5,'','11.6\" Ноутбук DEXP Athena T145 золотистый','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',13999),
  ('c4944c4b-262d-432a-8f0e-359d97747a48',5,'','14\" Ноутбук Irbis NB41 белый','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',13490),
  ('d14d1eb2-94ab-4fe6-9a05-a719a4935003',5,'','Защитное стекло для планшета Samsung Galaxy Tab 3 Lite 7.0','0a939034-0df2-4ece-81ab-902e3e0500c2',350),
  ('d64c0192-d4c7-4442-a24b-1771c907f853',5,'','Док-станция для накопителей Agestar SСBT','05d8a44b-e144-4f78-9fd4-c4b73a57379b',2250),
  ('d9843306-7b28-4080-9ab8-4ed107067f4f',5,'','Док-станция для накопителей DEXP AT-HA008','05d8a44b-e144-4f78-9fd4-c4b73a57379b',1190),
  ('df2f8470-77ab-4ba7-bf13-96d5c88ca067',5,'','14\" Ноутбук DEXP Athena T142 черный','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',14999),
  ('e21a4224-f014-44f8-a406-0119fd9f6293',5,'','Защитное стекло для планшета Samsung Galaxy Tab 3 10.1','0a939034-0df2-4ece-81ab-902e3e0500c2',290),
  ('f1dd11a4-a8dd-46e6-ac42-9597518535d0',5,'','Док-станция для накопителей AgeStar 3UBTFT','05d8a44b-e144-4f78-9fd4-c4b73a57379b',2350),
  ('f50da65a-7905-4772-9a59-3b538ec45788',5,'','Лицензия Microsoft Office Home and Student 2013 32/64-bit Russian','22e5086f-1b2d-426e-95ce-99d9d9d99e87',2799),
  ('f8d78043-c4c9-43d1-86b8-cab510bfcd4f',5,'','Сумка Riva 8211','2f84e9da-feae-4c6a-bdeb-75e5f3b7625f',1150),
  ('fae607f7-9c82-465f-b856-5662fda4b8a5',5,'','ПО Microsoft Office 365 для студентов','22e5086f-1b2d-426e-95ce-99d9d9d99e87',2099),
  ('fb6879bb-3050-4b1b-9bd0-8a70fe788864',5,'','14\" Ноутбук DELL Inspiron 3452-9855 черный','bdd94748-fbc5-4926-a3fa-4c90670f7c9a',14999);