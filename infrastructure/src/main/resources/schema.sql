CREATE TABLE users (
  id          INTEGER PRIMARY KEY,
  name        VARCHAR(20) NOT NULL,
  lastName    VARCHAR(40),
  email       VARCHAR(20) NOT NULL,
  enabled     BOOLEAN DEFAULT FALSE);
