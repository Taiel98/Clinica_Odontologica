DROP TABLE IF EXISTS ODONTOLOGOS;

CREATE TABLE ODONTOLOGOS (ID INT AUTO_INCREMENT PRIMARY KEY, MATRICULA INT NOT NULL, NOMBRE VARCHAR(50) NOT NULL, APELLIDO VARCHAR(50) NOT NULL);


-- para test --
INSERT INTO ODONTOLOGOS(MATRICULA, NOMBRE, APELLIDO) VALUES (123, 'Homero', 'Simpson'), (456, 'Lisa', 'Simpson');

