create table payment
(
    id bigserial NOT NULL PRIMARY KEY,
    inquiryRefId BIGINT,
    amount NUMERIC(5,2) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    transactionRefId BIGINT NOT NULL,
    status VARCHAR(255),
    createdAt TIMESTAMP,
    updatedAT TIMESTAMP)