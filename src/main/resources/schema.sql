DROP table if exists CUSTOMER;
DROP table if exists TRANSACTION;
CREATE table CUSTOMER (
    customerId int primary key,
    customerName VARCHAR2(50) );
CREATE TABLE TRANSACTION (transactionId int,customerId int ,transactionDate DATE,amount int);
COMMIT;