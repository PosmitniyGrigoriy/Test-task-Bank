DELETE FROM RUB_FINANCIAL_OPERATIONS;
DELETE FROM CURRENCIES;

ALTER SEQUENCE hibernate_sequence RESTART WITH 1000;

INSERT INTO RUB_FINANCIAL_OPERATIONS (id, date, description, amount) VALUES (1, '2023-11-01', 'Income', 72000);
INSERT INTO RUB_FINANCIAL_OPERATIONS (id, date, description, amount) VALUES (2, '2023-11-01', 'Income', 48000);
INSERT INTO RUB_FINANCIAL_OPERATIONS (id, date, description, amount) VALUES (3, '2023-11-01', 'Income', 210000);
INSERT INTO RUB_FINANCIAL_OPERATIONS (id, date, description, amount) VALUES (4, '2023-11-03', 'Income', 840000);
INSERT INTO RUB_FINANCIAL_OPERATIONS (id, date, description, amount) VALUES (5, '2023-11-03', 'Income', 3894000);
INSERT INTO RUB_FINANCIAL_OPERATIONS (id, date, description, amount) VALUES (6, '2023-11-05', 'Income', 72000);
INSERT INTO RUB_FINANCIAL_OPERATIONS (id, date, description, amount) VALUES (7, '2023-11-09', 'Income', 384000);
INSERT INTO RUB_FINANCIAL_OPERATIONS (id, date, description, amount) VALUES (8, '2023-11-09', 'Income', 570000);

INSERT INTO CURRENCIES (id, vname, vnom, vcurs, vcode, vch_code, vunit_rate) VALUES (1, 'Доллар США', 1, 91.9266, 840, 'USD', 91.9266);
INSERT INTO CURRENCIES (id, vname, vnom, vcurs, vcode, vch_code, vunit_rate) VALUES (2, 'Евро',      1, 98.4076, 978, 'EUR', 98.4076);
