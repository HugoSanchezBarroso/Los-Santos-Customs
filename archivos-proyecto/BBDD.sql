DROP DATABASE IF EXISTS LosSantosCustoms;
CREATE DATABASE IF NOT EXISTS LosSantosCustoms;

USE LosSantosCustoms;

/*Creamos las tablas*/
CREATE TABLE IF NOT EXISTS Usuario (
    ID INT(8) AUTO_INCREMENT,
    Nombre VARCHAR(255),
    Apellidos VARCHAR(255),
    Correo VARCHAR(255),
    Contrasenia VARCHAR(255),
    Permiso ENUM('limit', 'admin') DEFAULT 'limit',
    PRIMARY KEY (ID)
);


CREATE TABLE IF NOT EXISTS Vehiculo (
    ID INT(8) PRIMARY KEY AUTO_INCREMENT,
    Marca VARCHAR(255),
    Modelo VARCHAR(255),
    Color VARCHAR(255),
    Matricula VARCHAR(255),
    N_plazas INTEGER,
    Potencia INTEGER,
    Anho INTEGER,
    Precio DECIMAL(10,2)
);


CREATE TABLE IF NOT EXISTS Establecimiento (
    ID INT(8) PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(255),
    Direccion VARCHAR(255),
    Ciudad VARCHAR(255),
    Horario VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Venta (
    ID INT(8) PRIMARY KEY KEY AUTO_INCREMENT,
    ID_cliente INT(8),
    ID_vehiculo INT(8),
    ID_trabajador INT(8),
    fecha_venta DATE,
    FOREIGN KEY (ID_vehiculo) REFERENCES Vehiculo(ID) ON DELETE SET NULL,
    FOREIGN KEY (ID_trabajador) REFERENCES Usuario(ID) ON DELETE SET NULL,
    FOREIGN KEY (ID_cliente) REFERENCES Usuario(ID) ON DELETE SET NULL
);


CREATE TABLE IF NOT EXISTS Inventario (
    ID_establecimiento INT(8),
    ID_vehiculo INT(8),
    ID_venta INT(8) DEFAULT NULL,
    PRIMARY KEY (ID_establecimiento, ID_vehiculo),
    FOREIGN KEY (ID_establecimiento) REFERENCES Establecimiento(ID) ON DELETE CASCADE,
    FOREIGN KEY (ID_vehiculo) REFERENCES Vehiculo(ID) ON DELETE CASCADE,
	FOREIGN KEY (ID_venta) REFERENCES Venta(ID) ON DELETE SET NULL
);



#Insertamos valores
        
INSERT INTO Usuario (Nombre, Apellidos, Correo, Contrasenia) VALUES 
('Antonio', 'Garcia Lopez', 'antoniogalo@gmail.com', 'passwdAntonio'),
('Alberto', 'Carvajal Moreno', 'albertcarmor@gmail.com', 'passwdAlberto'),
('Ana', 'Perez Lopez', 'anaperezlop@gmail.com', 'passwdAna');

INSERT INTO Usuario (Nombre, Apellidos, Correo, Contrasenia, Permiso) VALUES 
('Hugo', 'Garcia Lopez', 'hugogalo@gmail.com', 'passwdHugo', 'admin'),
('Pepe', 'Prescott Alexander', 'prescottalex@gmail.com', 'passwdPepe', 'admin');


INSERT INTO Vehiculo (Marca, Modelo, Color, Matricula, N_plazas, Potencia, Anho, Precio) VALUES 
('Toyota', 'Corolla', 'Azul', 'ABC123', 5, 120, 2023, 20000.00),
('Ford', 'Focus', 'Negro', 'DEF456', 5, 110, 2022, 18000.00),
('Honda', 'Civic', 'Rojo', 'GHI789', 5, 130, 2023, 22000.00);


INSERT INTO Establecimiento (Nombre, Direccion, Ciudad, Horario) VALUES 
('AutoStore', 'Calle Principal 123', 'Ciudad A', 'Lunes a Viernes 9am-6pm'),
('CarShop', 'Avenida Central 456', 'Ciudad B', 'Lunes a Sábado 10am-8pm'),
('MotoWorld', 'Plaza Central 789', 'Ciudad C', 'Lunes a Domingo 8am-10pm');


INSERT INTO Venta (ID_vehiculo, ID_cliente, ID_trabajador, fecha_venta) VALUES 
(3, 1, 5, '2024-05-01'),
(2, 2, 4, '2023-02-25');


INSERT INTO Inventario (ID_establecimiento, ID_vehiculo, ID_venta) VALUES 
(2, 3, 1),
(1, 2, 2);

INSERT INTO Inventario (ID_establecimiento, ID_vehiculo) VALUES 
(1, 1);
