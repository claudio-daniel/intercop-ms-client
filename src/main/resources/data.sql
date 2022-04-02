DROP TABLE IF EXISTS clients;

CREATE TABLE clients (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  birth_date DATE NOT NULL
);

INSERT INTO clients (first_name, last_name,  birth_date) VALUES
  ('Aliko', 'Dangote', '1993-06-03'),
  ('Bill', 'Gates', '1993-06-03'),
  ('Folrunsho', 'Alakija', '1993-06-03');