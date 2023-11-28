DELETE FROM RUB_FINANCIAL_OPERATIONS;
DELETE FROM CURRENCIES;

ALTER SEQUENCE hibernate_sequence RESTART WITH 1000;

INSERT INTO RUB_FINANCIAL_OPERATIONS (id, date_at, description, amount) VALUES (1, '2023-11-01', 'Income', 72000);
INSERT INTO RUB_FINANCIAL_OPERATIONS (id, date_at, description, amount) VALUES (2, '2023-11-01', 'Income', 48000);
INSERT INTO RUB_FINANCIAL_OPERATIONS (id, date_at, description, amount) VALUES (3, '2023-11-01', 'Income', 210000);
INSERT INTO RUB_FINANCIAL_OPERATIONS (id, date_at, description, amount) VALUES (4, '2023-11-03', 'Income', 840000);
INSERT INTO RUB_FINANCIAL_OPERATIONS (id, date_at, description, amount) VALUES (5, '2023-11-04', 'Income', 3894000);
INSERT INTO RUB_FINANCIAL_OPERATIONS (id, date_at, description, amount) VALUES (6, '2023-11-05', 'Income', 72000);
INSERT INTO RUB_FINANCIAL_OPERATIONS (id, date_at, description, amount) VALUES (7, '2023-11-09', 'Income', 384000);
INSERT INTO RUB_FINANCIAL_OPERATIONS (id, date_at, description, amount) VALUES (8, '2023-11-09', 'Income', 570000);

INSERT INTO CURRENCIES (id, vname, vnom, vcurs, vcode, vch_code, vunit_rate, date_at) VALUES (1,  'Доллар США', 1, 92.0226, 840, 'USD', 92.0226, '2023-11-01');
INSERT INTO CURRENCIES (id, vname, vnom, vcurs, vcode, vch_code, vunit_rate, date_at) VALUES (2,  'Евро',       1, 97.9345, 978, 'EUR', 97.9345, '2023-11-01');
INSERT INTO CURRENCIES (id, vname, vnom, vcurs, vcode, vch_code, vunit_rate, date_at) VALUES (3,  'Доллар США', 1, 93.2801, 840, 'USD', 93.2801, '2023-11-02');
INSERT INTO CURRENCIES (id, vname, vnom, vcurs, vcode, vch_code, vunit_rate, date_at) VALUES (4,  'Евро',       1, 98.4365, 978, 'EUR', 98.4365, '2023-11-02');
INSERT INTO CURRENCIES (id, vname, vnom, vcurs, vcode, vch_code, vunit_rate, date_at) VALUES (5,  'Доллар США', 1, 93.1730, 840, 'USD', 93.1730, '2023-11-03');
INSERT INTO CURRENCIES (id, vname, vnom, vcurs, vcode, vch_code, vunit_rate, date_at) VALUES (6,  'Евро',       1, 98.9995, 978, 'EUR', 98.9995, '2023-11-03');
INSERT INTO CURRENCIES (id, vname, vnom, vcurs, vcode, vch_code, vunit_rate, date_at) VALUES (7,  'Доллар США', 1, 93.0351, 840, 'USD', 93.0351, '2023-11-04');
INSERT INTO CURRENCIES (id, vname, vnom, vcurs, vcode, vch_code, vunit_rate, date_at) VALUES (8,  'Евро',       1, 99.0111, 978, 'EUR', 99.0111, '2023-11-04');
INSERT INTO CURRENCIES (id, vname, vnom, vcurs, vcode, vch_code, vunit_rate, date_at) VALUES (9,  'Доллар США', 1, 92.4151, 840, 'USD', 92.4151, '2023-11-08');
INSERT INTO CURRENCIES (id, vname, vnom, vcurs, vcode, vch_code, vunit_rate, date_at) VALUES (10, 'Евро',       1, 98.7863, 978, 'EUR', 98.7863, '2023-11-08');
INSERT INTO CURRENCIES (id, vname, vnom, vcurs, vcode, vch_code, vunit_rate, date_at) VALUES (11, 'Доллар США', 1, 92.1973, 840, 'USD', 92.1973, '2023-11-09');
INSERT INTO CURRENCIES (id, vname, vnom, vcurs, vcode, vch_code, vunit_rate, date_at) VALUES (12, 'Евро',       1, 98.4403, 978, 'EUR', 98.4403, '2023-11-09');
