DROP DATABASE IF EXISTS LosSantosCustoms;
CREATE DATABASE IF NOT EXISTS LosSantosCustoms;

USE LosSantosCustoms;

/*Creamos las tablas*/
CREATE TABLE IF NOT EXISTS Usuario (
    ID VARCHAR(255) PRIMARY KEY,
    Nombre VARCHAR(255),
    Apellidos VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Trabajador (
    ID_trabajador VARCHAR(255) PRIMARY KEY,
    Puesto VARCHAR(255),
    F_contratacion DATE,
    TipoTrabajador ENUM('Mecanico', 'Recepcionista', 'Pintor', 'Gerente'),
    FOREIGN KEY (ID_trabajador) REFERENCES Usuario(ID)
);

CREATE TABLE IF NOT EXISTS Cliente (
   ID_cliente VARCHAR(255) PRIMARY KEY,
   Direccion VARCHAR(50),
   Num_tlf INTEGER,
   FOREIGN KEY (ID_cliente) REFERENCES Usuario (ID)
);

CREATE TABLE IF NOT EXISTS Realiza (
	ID_venta VARCHAR(255) PRIMARY KEY,
    ID_trabajador VARCHAR(255),
    FOREIGN KEY (ID_trabajador) REFERENCES Trabajador (ID_trabajador)
);

CREATE TABLE IF NOT EXISTS Vehiculo (
    ID VARCHAR(255) PRIMARY KEY,
    Marca VARCHAR(255),
    Modelo VARCHAR(255),
    Color VARCHAR(255),
    Matricula VARCHAR(255),
    N_plazas INTEGER,
    Potencia INTEGER,
    Anho INTEGER,
    Precio DECIMAL(10,2)
);

CREATE TABLE IF NOT EXISTS Hace (
    ID_cliente VARCHAR(255),
    ID_transaccion VARCHAR(255),
    PRIMARY KEY (ID_cliente, ID_transaccion),
    FOREIGN KEY (ID_cliente) REFERENCES Cliente(ID_cliente)
);

CREATE TABLE IF NOT EXISTS Vende (
	ID_trabajador VARCHAR(255) PRIMARY KEY,
    ID_vehiculo VARCHAR(255),
    FOREIGN KEY (ID_vehiculo) REFERENCES Vehiculo (ID)
);

CREATE TABLE IF NOT EXISTS Transaccion (
    ID VARCHAR(255) PRIMARY KEY,
    F_transaccion DATE,
    TipoTransaccion ENUM('Reparacion', 'Devolucion')
);

CREATE TABLE IF NOT EXISTS Establecimiento (
    ID VARCHAR(255) PRIMARY KEY,
    Nombre VARCHAR(255),
    Direccion VARCHAR(255),
    Ciudad VARCHAR(255),
    Horario VARCHAR(255),
    ID_vehiculo VARCHAR(255),
    FOREIGN KEY (ID_vehiculo) REFERENCES Vehiculo(ID)
);

CREATE TABLE IF NOT EXISTS Venta (
    ID VARCHAR(255) PRIMARY KEY,
    ID_cliente VARCHAR(255),
    ID_vehiculo VARCHAR(255),
    ID_trabajador VARCHAR(255),
    fecha_venta DATE,
    ID_establecimiento VARCHAR(255),
    FOREIGN KEY (ID_establecimiento) REFERENCES Establecimiento(ID),
    FOREIGN KEY (ID_trabajador) REFERENCES Trabajador(ID_trabajador)
);

CREATE TABLE IF NOT EXISTS Se_realizan (
	ID_venta VARCHAR(255) PRIMARY KEY,
    ID_establecimiento VARCHAR(255),
    FOREIGN KEY (ID_establecimiento) REFERENCES Establecimiento (ID)
);

CREATE TABLE IF NOT EXISTS Se_Encuentra (
    ID_establecimiento VARCHAR(255),
    ID_usuario VARCHAR(255),
    PRIMARY KEY (ID_establecimiento, ID_usuario),
    FOREIGN KEY (ID_establecimiento) REFERENCES Establecimiento(ID),
    FOREIGN KEY (ID_usuario) REFERENCES Usuario(ID)
);


CREATE TABLE IF NOT EXISTS Tiene (
    ID_vehiculo VARCHAR(255),
    ID_establecimiento VARCHAR(255),
    PRIMARY KEY (ID_vehiculo, ID_establecimiento),
    FOREIGN KEY (ID_vehiculo) REFERENCES Vehiculo(ID),
    FOREIGN KEY (ID_establecimiento) REFERENCES Establecimiento(ID)
);



CREATE TABLE IF NOT EXISTS Pertenece (
    ID_vehiculo VARCHAR(255),
    ID_venta VARCHAR(255),
    PRIMARY KEY (ID_vehiculo, ID_venta),
    FOREIGN KEY (ID_vehiculo) REFERENCES Vehiculo(ID),
    FOREIGN KEY (ID_venta) REFERENCES Venta(ID)
);


CREATE TABLE IF NOT EXISTS Conforma (
	ID_cliente VARCHAR(255) PRIMARY KEY,
    ID_venta VARCHAR(255),
    FOREIGN KEY (ID_venta) REFERENCES Venta (ID)
);

#Insertamos valores

INSERT INTO Usuario (ID, Nombre, Apellidos) VALUES 
('1', 'Juan', 'González'),
('2', 'María', 'López'),
('3', 'Pedro', 'Martínez');

INSERT INTO Trabajador (ID_trabajador, Puesto, F_contratacion, TipoTrabajador) VALUES 
('1', 'Mecánico', '2023-01-15', 'Mecanico'),
('2', 'Recepcionista', '2022-06-20', 'Recepcionista'),
('3', 'Pintor', '2023-03-10', 'Pintor');

INSERT INTO Cliente (ID_cliente, Direccion, Num_tlf) VALUES 
('1', 'Calle 123', 123456789),
('2', 'Avenida 456', 987654321),
('3', 'Plaza 789', 987123456);

INSERT INTO Realiza (ID_venta, ID_trabajador) VALUES 
('1001', '1'),
('1002', '2'),
('1003', '3');

INSERT INTO Vehiculo (ID, Marca, Modelo, Color, Matricula, N_plazas, Potencia, Anho, Precio) VALUES 
('V001', 'Toyota', 'Corolla', 'Azul', 'ABC123', 5, 120, 2023, 20000.00),
('V002', 'Ford', 'Focus', 'Negro', 'DEF456', 5, 110, 2022, 18000.00),
('V003', 'Honda', 'Civic', 'Rojo', 'GHI789', 5, 130, 2023, 22000.00);

INSERT INTO Hace (ID_cliente, ID_transaccion) VALUES 
('1', '1001'),
('2', '1002'),
('3', '1003');

INSERT INTO Vende (ID_trabajador, ID_vehiculo) VALUES 
('1', 'V001'),
('2', 'V002'),
('3', 'V003');

INSERT INTO Transaccion (ID, F_transaccion, TipoTransaccion) VALUES 
('T001', '2024-04-15', 'Reparacion'),
('T002', '2024-04-20', 'Devolucion'),
('T003', '2024-04-25', 'Reparacion');

INSERT INTO Establecimiento (ID, Nombre, Direccion, Ciudad, Horario, ID_vehiculo) VALUES 
('E001', 'AutoStore', 'Calle Principal 123', 'Ciudad A', 'Lunes a Viernes 9am-6pm', 'V001'),
('E002', 'CarShop', 'Avenida Central 456', 'Ciudad B', 'Lunes a Sábado 10am-8pm', 'V002'),
('E003', 'MotoWorld', 'Plaza Central 789', 'Ciudad C', 'Lunes a Domingo 8am-10pm', 'V003');

INSERT INTO Venta (ID, ID_cliente, ID_vehiculo, ID_trabajador, fecha_venta, ID_establecimiento) VALUES 
('1001', '101', 'V001', '1', '2024-04-10', 'E001'),
('1002', '102', 'V002', '2', '2024-04-15', 'E002'),
('1003', '103', 'V003', '3', '2024-04-20', 'E003');


INSERT INTO Se_realizan (ID_venta, ID_establecimiento) VALUES 
('1001', 'E001'),
('1002', 'E002'),
('1003', 'E003');

INSERT INTO Se_Encuentra (ID_establecimiento, ID_usuario) VALUES 
('E001', '1'),
('E002', '2'),
('E003', '3');

INSERT INTO Tiene (ID_vehiculo, ID_establecimiento) VALUES 
('V001', 'E001'),
('V002', 'E002'),
('V003', 'E003');

INSERT INTO Pertenece (ID_vehiculo, ID_venta) VALUES 
('V001', '1001'),
('V002', '1002'),
('V003', '1003');

INSERT INTO Conforma (ID_cliente, ID_venta) VALUES 
('101', '1001'),
('102', '1002'),
('103', '1003');



