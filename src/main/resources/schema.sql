DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS money_transfer_transaction;

CREATE TABLE account (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  number UUID NOT NULL,
  balance DECIMAL NOT NULL
);

CREATE TABLE money_transfer_transaction (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  origin UUID NOT NULL,
  destination UUID NOT NULL,
  amount DECIMAL NOT NULL,
  status VARCHAR NOT NULL,
  CONSTRAINT FK_A_O FOREIGN KEY (origin) REFERENCES account(number),
  CONSTRAINT FK_A_D FOREIGN KEY (destination) REFERENCES account(number)
);
