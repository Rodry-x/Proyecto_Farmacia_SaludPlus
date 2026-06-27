CREATE DATABASE manul
USE manul

-- ============================================================
--  TABLAS INDEPENDIENTES (sin FK)
-- ============================================================

CREATE TABLE ROLES (
    id_rol     INT IDENTITY(1,1) PRIMARY KEY,
    nombre_rol VARCHAR(50) NOT NULL
);

CREATE TABLE CATEGORIAS (
    id_categoria     INT IDENTITY(1,1) PRIMARY KEY,
    nombre_categoria VARCHAR(100) NOT NULL
);

CREATE TABLE IMPUESTOS (
    id_impuesto     INT IDENTITY(1,1) PRIMARY KEY,
    nombre_impuesto VARCHAR(50)   NOT NULL,
    porcentaje      DECIMAL(5,2)  NOT NULL,
    CONSTRAINT CK_IMPUESTOS_PORCENTAJE CHECK (porcentaje >= 0 AND porcentaje <= 100)
);

CREATE TABLE METODO_PAGO (
    id_metodopago INT IDENTITY(1,1) PRIMARY KEY,
    nombre        VARCHAR(50) NOT NULL
);

Create TABLE GENEROS(
    id_genero INT IDENTITY(1,1) PRIMARY KEY,
    nombre_genero VARCHAR(100) NOT NULL
)
CREATE TABLE CLIENTES (
    id_cliente INT IDENTITY(1,1) PRIMARY KEY,
    nombre     VARCHAR(100) NOT NULL,
    apellido   VARCHAR(100) NOT NULL,
    dni        CHAR(8)      NOT NULL UNIQUE,
    id_genero  INT NOT NULL,
    fecha_nacimiento DATE,
    FOREIGN KEY (id_genero) REFERENCES GENEROS(id_genero)
);

CREATE TABLE PROVEEDOR (
    id_proveedor INT IDENTITY(1,1) PRIMARY KEY,
    ruc          CHAR(11)     NOT NULL UNIQUE,
    nombre       VARCHAR(150) NOT NULL,
    direccion    VARCHAR(200)
);
Create Table ESTADOS_COMPRA (
    id_estado INT IDENTITY (1,1) PRIMARY KEY,
    nombre_estado VARCHAR(100) NOT NULL
)
-- ============================================================
--  TABLAS CON DEPENDENCIAS DE 1er NIVEL
-- ============================================================

CREATE TABLE USUARIOS (
    id_usuario INT IDENTITY(1,1) PRIMARY KEY,
    nombre     VARCHAR(100) NOT NULL,
    apellido   VARCHAR(100) NOT NULL,
    username   VARCHAR(50)  NOT NULL UNIQUE,
    password   VARCHAR(25) NOT NULL,
    id_rol     INT NOT NULL,
    FOREIGN KEY (id_rol) REFERENCES ROLES(id_rol)
);

CREATE TABLE PRODUCTOS (
    id_producto   INT IDENTITY(1,1) PRIMARY KEY,
    nombre        VARCHAR(150)  NOT NULL,
    descripcion   VARCHAR(MAX),
    id_categoria  INT           NOT NULL,
    stock_minimo  INT           NOT NULL DEFAULT 0,
    stock_general INT           NOT NULL DEFAULT 0,
    id_impuesto   INT           NOT NULL,
    precio_venta  DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES CATEGORIAS(id_categoria),
    FOREIGN KEY (id_impuesto)  REFERENCES IMPUESTOS(id_impuesto),
    CONSTRAINT CK_PRODUCTOS_STOCK_MINIMO CHECK (stock_minimo >= 0),
    CONSTRAINT CK_PRODUCTOS_STOCK_GENERAL CHECK (stock_general >= 0),
    CONSTRAINT CK_PRODUCTOS_PRECIO_VENTA CHECK (precio_venta > 0)
);

CREATE TABLE TELEFONO_CLIENTES(
    id_telefono INT IDENTITY(1,1) PRIMARY KEY,
    id_cliente INT NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES CLIENTES(id_cliente),
    CONSTRAINT UQ_TELEFONO_CLIENTE UNIQUE(id_cliente, telefono)
)
CREATE TABLE CORREO_CLIENTES(
    id_correo INT IDENTITY(1,1) PRIMARY KEY,
    id_cliente INT NOT NULL,
    correo VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES CLIENTES(id_cliente),
    CONSTRAINT UQ_CORREO_CLIENTE UNIQUE(id_cliente, correo)
)
CREATE TABLE TELEFONO_PROVEEDOR(
    id_telefono INT IDENTITY(1,1) PRIMARY KEY,
    id_proveedor INT NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    FOREIGN KEY (id_proveedor) REFERENCES PROVEEDOR(id_proveedor),
    CONSTRAINT UQ_TELEFONO_PROVEEDOR UNIQUE(id_proveedor, telefono)
)
CREATE TABLE CORREO_PROVEEDOR(
    id_correo INT IDENTITY(1,1) PRIMARY KEY,
    id_proveedor INT NOT NULL,
    correo VARCHAR (100) NOT NULL,
    FOREIGN KEY (id_proveedor) REFERENCES PROVEEDOR(id_proveedor),
    CONSTRAINT UQ_CORREO_PROVEEDOR UNIQUE(id_proveedor, correo)
)


-- ============================================================
--  TABLAS DE TRANSACCIONES
-- ============================================================
CREATE TABLE COMPRA (
    id_compra    INT IDENTITY(1,1) PRIMARY KEY,
    id_usuario   INT           NOT NULL,
    id_proveedor INT           NOT NULL,
    fecha        DATETIME      NOT NULL DEFAULT GETDATE(),
    subtotal     DECIMAL(10,2) NOT NULL,
    igv_total    DECIMAL(10,2) NOT NULL,
    total_compra DECIMAL(10,2) NOT NULL,
    id_estado_compra INT NOT NULL DEFAULT 1,
    FOREIGN KEY (id_usuario)   REFERENCES USUARIOS(id_usuario),
    FOREIGN KEY (id_proveedor) REFERENCES PROVEEDOR(id_proveedor),
    FOREIGN KEY (id_estado_compra) REFERENCES ESTADOS_COMPRA(id_estado),
    CONSTRAINT CK_COMPRA_SUBTOTAL CHECK (subtotal >= 0),
    CONSTRAINT CK_COMPRA_IGV CHECK (igv_total >= 0),
    CONSTRAINT CK_COMPRA_TOTAL CHECK (total_compra >= 0)
);

CREATE TABLE DETALLE_COMPRA (
    id_detalle      INT IDENTITY(1,1) PRIMARY KEY,
    id_compra       INT           NOT NULL,
    id_producto     INT           NOT NULL,
    stock_comprado  INT           NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal        DECIMAL(10,2) NOT NULL,
    igv             DECIMAL(10,2) NOT NULL,
    total_detalle   DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_compra)   REFERENCES COMPRA(id_compra),
    FOREIGN KEY (id_producto) REFERENCES PRODUCTOS(id_producto),
    CONSTRAINT CK_DETALLE_COMPRA_STOCK CHECK (stock_comprado > 0),
    CONSTRAINT CK_DETALLE_COMPRA_PRECIO CHECK (precio_unitario > 0)
);
CREATE TABLE LOTES (
    id_lote           INT IDENTITY(1,1) PRIMARY KEY,
    id_detalle_compra INT         NOT NULL,
    stock_entrante    INT         NOT NULL,
    stock_actual      INT         NOT NULL,
    fecha_ingreso     DATE        NOT NULL DEFAULT GETDATE(),
    fecha_vencimiento DATE,
    FOREIGN KEY (id_detalle_compra) REFERENCES DETALLE_COMPRA(id_detalle),
    CONSTRAINT CK_LOTES_STOCK_ENTRANTE CHECK (stock_entrante > 0),
    CONSTRAINT CK_LOTES_STOCK_ACTUAL CHECK (stock_actual >= 0)
);
CREATE TABLE VENTA (
    id_venta      INT IDENTITY(1,1) PRIMARY KEY,
    id_cliente    INT           NOT NULL,
    id_usuario    INT           NOT NULL,
    id_metodopago INT           NOT NULL,
    fecha         DATETIME      NOT NULL DEFAULT GETDATE(),
    subtotal      DECIMAL(10,2) NOT NULL,
    igv_total     DECIMAL(10,2) NOT NULL,
    total_pagar   DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_cliente)    REFERENCES CLIENTES(id_cliente),
    FOREIGN KEY (id_usuario)    REFERENCES USUARIOS(id_usuario),
    FOREIGN KEY (id_metodopago) REFERENCES METODO_PAGO(id_metodopago),
    CONSTRAINT CK_VENTA_SUBTOTAL CHECK (subtotal >= 0),
    CONSTRAINT CK_VENTA_IGV CHECK (igv_total >= 0),
    CONSTRAINT CK_VENTA_TOTAL CHECK (total_pagar >= 0)
);

CREATE TABLE DETALLE_VENTA (
    id_detalle      INT IDENTITY(1,1) PRIMARY KEY,
    id_venta        INT           NOT NULL,
    id_producto     INT           NOT NULL,
    id_lote         INT           NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    cantidad        INT           NOT NULL,
    subtotal        DECIMAL(10,2) NOT NULL,
    igv             DECIMAL(10,2) NOT NULL,
    total_producto  DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_venta)    REFERENCES VENTA(id_venta),
    FOREIGN KEY (id_producto) REFERENCES PRODUCTOS(id_producto),
    FOREIGN KEY (id_lote)     REFERENCES LOTES(id_lote),
    CONSTRAINT CK_DETALLE_VENTA_CANTIDAD CHECK (cantidad > 0),
    CONSTRAINT CK_DETALLE_VENTA_PRECIO CHECK (precio_unitario > 0),
    CONSTRAINT CK_DETALLE_VENTA_SUBTOTAL CHECK (subtotal >= 0),
    CONSTRAINT CK_DETALLE_VENTA_IGV CHECK (igv >= 0),
    CONSTRAINT CK_DETALLE_VENTA_TOTAL CHECK (total_producto >= 0)
);

INSERT INTO ESTADOS_COMPRA (nombre_estado) VALUES
        ('PENDIENTE'),
        ('RECIBIDO');



INSERT INTO ROLES (nombre_rol) VALUES
    ('Administrador'),
    ('Cajero');

INSERT INTO CATEGORIAS (nombre_categoria) VALUES
    ('Analgesicos'),
    ('Antibioticos'),
    ('Antigripales y Resfrio'),
    ('Antiinflamatorios'),
    ('Vitaminas y Suplementos'),
    ('Dermatologicos'),
    ('Gastrointestinales'),
    ('Cuidado Personal');

INSERT INTO IMPUESTOS (nombre_impuesto, porcentaje) VALUES
    ('IGV', 18.00),
    ('Exonerado', 0.00);

INSERT INTO METODO_PAGO (nombre) VALUES
    ('Efectivo'),
    ('Tarjeta de Credito'),
    ('Tarjeta de Debito'),
    ('Yape'),
    ('Plin'),
    ('Billetera Digital');

INSERT INTO GENEROS (nombre_genero) VALUES
    ('Masculino'),
    ('Femenino'),
    ('Otro');

INSERT INTO CLIENTES (nombre, apellido, dni, id_genero, fecha_nacimiento) VALUES
    ('Maria Fernanda',    'Torres Quispe',   '45123678', 2, '1990-05-12'),
    ('Carlos Alberto',    'Mendoza Ruiz',    '41789234', 1, '1985-11-23'),
    ('Lucia Isabel',      'Ramos Vargas',    '47892156', 2, '1995-02-08'),
    ('Jose Manuel',       'Flores Castillo', '42567891', 1, '1978-07-19'),
    ('Ana Patricia',      'Huaman Soto',     '48123459', 2, '2000-09-30'),
    ('Jorge Luis',        'Paredes Rojas',   '43678912', 1, '1992-12-05'),
    ('Rosa Elena',        'Chavez Nahui',    '46912378', 2, '1988-03-17'),
    ('Miguel Angel',      'Sanchez Vega',    '44567823', 1, '1975-06-25'),
    ('Daniela Alexandra', 'Cruz Medina',     '49234567', 2, '1998-10-14'),
    ('Renato Sebastian',  'Lopez Diaz',      '40123987', 3, '1993-01-29');

INSERT INTO PROVEEDOR (ruc, nombre, direccion) VALUES
    ('20100123456', 'Drogueria Inkafarma Distribucion S.A.C.', 'Av. Republica de Panama 3651, San Isidro, Lima'),
    ('20109072177', 'Quimica Suiza S.A.',                       'Av. Los Frutales 220, Ate, Lima'),
    ('20100127609', 'Laboratorios Bago del Peru S.A.',          'Av. Argentina 3093, Callao'),
    ('20100154291', 'Distribuidora Albis S.A.',                 'Jr. Cusco 689, Lima'),
    ('20512345678', 'Corporacion Medco S.A.C.',                 'Av. Colonial 1245, Lima');



INSERT INTO USUARIOS (nombre, apellido, username, password, id_rol) VALUES
    ('Patricia', 'Gomez Linares',  'pgomez',     'Admin#2025',  1),
    ('Luis',     'Fernandez Castro','lfernandez','Caja#2025a',  2),
    ('Carmen',   'Vidal Soria',    'cvidal',     'Caja#2025b',  2),
    ('Andres',   'Salazar Ponce',  'asalazar',   'Caja#2025c',  2),
    ('Fiorella', 'Navarro Quiroz', 'fnavarro',   'Caja#2025d',  2);

INSERT INTO PRODUCTOS (nombre, descripcion, id_categoria, stock_minimo, stock_general, id_impuesto, precio_venta) VALUES
    ('Paracetamol 500mg x100 tab',          'Analgesico y antipiretico, caja x100 tabletas',        1, 20, 182, 2, 12.50),
    ('Ibuprofeno 400mg x30 tab',             'Antiinflamatorio no esteroideo, caja x30 tabletas',    1, 15, 125, 2, 15.90),
    ('Amoxicilina 500mg x12 cap',            'Antibiotico de amplio espectro, caja x12 capsulas',    2, 15, 58,  2, 18.00),
    ('Azitromicina 500mg x3 tab',            'Antibiotico macrolido, caja x3 tabletas',              2, 10, 54,  2, 22.50),
    ('Loratadina 10mg x10 tab',              'Antihistaminico, caja x10 tabletas',                   3, 15, 95,  2, 9.90),
    ('Paracetamol + Fenilefrina x10 tab',    'Antigripal combinado, caja x10 tabletas',              3, 15, 0,   2, 11.50),
    ('Diclofenaco 50mg x20 tab',             'Antiinflamatorio, caja x20 tabletas',                  4, 20, 85,  2, 14.00),
    ('Naproxeno 250mg x20 tab',              'Antiinflamatorio, caja x20 tabletas',                  4, 15, 90,  2, 16.50),
    ('Complejo B x30 cap',                   'Suplemento vitaminico, caja x30 capsulas',             5, 10, 45,  1, 25.00),
    ('Vitamina C 1g x10 tab efervescentes',  'Suplemento vitaminico efervescente',                   5, 10, 0,   1, 19.90),
    ('Multivitaminico Adulto x60 cap',       'Suplemento multivitaminico, frasco x60 capsulas',      5, 10, 0,   1, 45.00),
    ('Crema Hidratante Corporal 200ml',      'Crema dermatologica hidratante',                       6, 10, 35,  1, 28.00),
    ('Clotrimazol Crema 20g',                'Antifungico topico',                                   6, 10, 0,   2, 13.50),
    ('Protector Solar SPF50 100ml',          'Protector solar de amplio espectro',                   6, 10, 28,  1, 55.00),
    ('Omeprazol 20mg x14 cap',               'Protector gastrico, caja x14 capsulas',                7, 15, 69,  2, 17.00),
    ('Sales de Rehidratacion Oral x1 sobre', 'Rehidratante oral, sobre individual',                  7, 30, 0,   2, 3.50),
    ('Simeticona 40mg x20 tab',              'Antiflatulento, caja x20 tabletas',                    7, 15, 0,   2, 12.00),
    ('Alcohol Etilico 70 grados 250ml',      'Antiseptico de uso externo',                           8, 20, 95,  1, 6.50),
    ('Jabon Antibacterial 90g',              'Jabon de tocador antibacterial',                       8, 20, 0,   1, 4.80),
    ('Mascarillas Quirurgicas x50 unid',     'Mascarillas descartables, caja x50 unidades',          8, 20, 0,   1, 22.00);

INSERT INTO TELEFONO_CLIENTES (id_cliente, telefono) VALUES
    (1, '987654321'),
    (2, '956123478'),
    (3, '978456123'),
    (4, '945678912'),
    (5, '967891234'),
    (6, '998123456'),
    (6, '956741230'),
    (7, '923456789'),
    (8, '934567812'),
    (9, '912345678'),
    (10,'989765432');

INSERT INTO CORREO_CLIENTES (id_cliente, correo) VALUES
    (1, 'maria.torres90@gmail.com'),
    (2, 'carlos.mendoza85@hotmail.com'),
    (3, 'lucia.ramos95@gmail.com'),
    (4, 'jose.flores78@outlook.com'),
    (5, 'ana.huaman00@gmail.com'),
    (6, 'jorge.paredes92@gmail.com'),
    (7, 'rosa.chavez88@yahoo.com'),
    (8, 'miguel.sanchez75@gmail.com'),
    (9, 'daniela.cruz98@gmail.com'),
    (10,'renato.lopez93@gmail.com');

INSERT INTO TELEFONO_PROVEEDOR (id_proveedor, telefono) VALUES
    (1, '014780123'),
    (1, '945123789'),
    (2, '016196060'),
    (3, '014113434'),
    (4, '012191919'),
    (5, '017654321');

INSERT INTO CORREO_PROVEEDOR (id_proveedor, correo) VALUES
    (1, 'ventas@inkafarmadist.com.pe'),
    (2, 'contacto@quimicasuiza.com.pe'),
    (3, 'pedidos@bago.com.pe'),
    (4, 'info@albis.com.pe'),
    (5, 'ventas@medco.com.pe');



INSERT INTO COMPRA (id_usuario, id_proveedor, fecha, subtotal, igv_total, total_compra, id_estado_compra) VALUES
    (4, 1, '2025-01-10', 3525.00, 0.00,   3525.00, 2),  -- compra 1
    (5, 2, '2025-02-14', 2470.00, 135.00, 2605.00, 2),  -- compra 2
    (4, 3, '2025-03-05', 2965.00, 0.00,   2965.00, 2),  -- compra 3
    (5, 4, '2025-04-20', 2170.00, 390.60, 2560.60, 2),  -- compra 4
    (4, 5, '2025-05-15', 2700.00, 486.00, 3186.00, 1);  -- compra 5 (pendiente, aun no llega)

INSERT INTO DETALLE_COMPRA (id_compra, id_producto, stock_comprado, precio_unitario, subtotal, igv, total_detalle) VALUES
    -- compra 1 (Inkafarma)
    (1, 1,  200, 7.50,  1500.00, 0.00,   1500.00),
    (1, 2,  150, 9.50,  1425.00, 0.00,   1425.00),
    (1, 5,  100, 6.00,  600.00,  0.00,   600.00),
    -- compra 2 (Quimica Suiza)
    (2, 3,  80,  11.00, 880.00,  0.00,   880.00),
    (2, 4,  60,  14.00, 840.00,  0.00,   840.00),
    (2, 9,  50,  15.00, 750.00,  135.00, 885.00),
    -- compra 3 (Bago)
    (3, 7,  120, 8.50,  1020.00, 0.00,   1020.00),
    (3, 8,  100, 10.00, 1000.00, 0.00,   1000.00),
    (3, 15, 90,  10.50, 945.00,  0.00,   945.00),
    -- compra 4 (Albis)
    (4, 12, 40,  18.00, 720.00,  129.60, 849.60),
    (4, 14, 30,  35.00, 1050.00, 189.00, 1239.00),
    (4, 18, 100, 4.00,  400.00,  72.00,  472.00),
    -- compra 5 (Medco - pendiente)
    (5, 19, 200, 3.00,  600.00,  108.00, 708.00),
    (5, 20, 150, 14.00, 2100.00, 378.00, 2478.00);


INSERT INTO LOTES (id_detalle_compra, stock_entrante, stock_actual, fecha_ingreso, fecha_vencimiento) VALUES
    (1,  200, 182, '2025-01-10', '2027-01-10'),  -- Paracetamol
    (2,  150, 125, '2025-01-10', '2027-01-10'),  -- Ibuprofeno
    (3,  100, 95,  '2025-01-10', '2026-12-10'),  -- Loratadina
    (4,  80,  58,  '2025-02-14', '2026-08-14'),  -- Amoxicilina
    (5,  60,  54,  '2025-02-14', '2026-08-14'),  -- Azitromicina
    (6,  50,  45,  '2025-02-14', '2027-02-14'),  -- Complejo B
    (7,  120, 85,  '2025-03-05', '2027-03-05'),  -- Diclofenaco
    (8,  100, 90,  '2025-03-05', '2027-03-05'),  -- Naproxeno
    (9,  90,  69,  '2025-03-05', '2027-03-05'),  -- Omeprazol
    (10, 40,  35,  '2025-04-20', '2028-04-20'),  -- Crema Hidratante
    (11, 30,  28,  '2025-04-20', '2028-04-20'),  -- Protector Solar
    (12, 100, 95,  '2025-04-20', '2028-04-20');  -- Alcohol Etilico


-- ============================================================
--  VENTAS  (metodo_pago: 1 Efectivo, 2 Tarj.Credito, 3 Tarj.Debito, 4 Yape, 5 Plin)
-- ============================================================

INSERT INTO VENTA (id_cliente, id_usuario, id_metodopago, fecha, subtotal, igv_total, total_pagar) VALUES
    (1,  2, 1, '2025-01-15', 174.50, 0.00,  174.50),  -- venta 1
    (2,  3, 2, '2025-01-20', 266.00, 9.00,  275.00),  -- venta 2
    (3,  2, 4, '2025-02-02', 280.00, 0.00,  280.00),  -- venta 3
    (4,  3, 1, '2025-02-18', 476.50, 0.00,  476.50),  -- venta 4
    (5,  2, 3, '2025-03-10', 194.00, 34.92, 228.92),  -- venta 5
    (6,  3, 5, '2025-03-22', 300.00, 0.00,  300.00),  -- venta 6
    (7,  2, 1, '2025-04-05', 132.50, 5.85,  138.35),  -- venta 7
    (8,  3, 2, '2025-04-25', 194.00, 13.50, 207.50),  -- venta 8
    (9,  2, 4, '2025-05-08', 390.00, 0.00,  390.00),  -- venta 9
    (10, 3, 1, '2025-05-30', 215.00, 10.08, 225.08);  -- venta 10

INSERT INTO DETALLE_VENTA (id_venta, id_producto, id_lote, precio_unitario, cantidad, subtotal, igv, total_producto) VALUES
    -- venta 1
    (1, 1,  1,  12.50, 10, 125.00, 0.00,  125.00),
    (1, 5,  3,  9.90,  5,  49.50,  0.00,  49.50),
    -- venta 2
    (2, 3,  4,  18.00, 12, 216.00, 0.00,  216.00),
    (2, 9,  6,  25.00, 2,  50.00,  9.00,  59.00),
    -- venta 3
    (3, 7,  7,  14.00, 20, 280.00, 0.00,  280.00),
    -- venta 4
    (4, 2,  2,  15.90, 15, 238.50, 0.00,  238.50),
    (4, 15, 9,  17.00, 14, 238.00, 0.00,  238.00),
    -- venta 5
    (5, 12, 10, 28.00, 3,  84.00,  15.12, 99.12),
    (5, 14, 11, 55.00, 2,  110.00, 19.80, 129.80),
    -- venta 6
    (6, 4,  5,  22.50, 6,  135.00, 0.00,  135.00),
    (6, 8,  8,  16.50, 10, 165.00, 0.00,  165.00),
    -- venta 7
    (7, 1,  1,  12.50, 8,  100.00, 0.00,  100.00),
    (7, 18, 12, 6.50,  5,  32.50,  5.85,  38.35),
    -- venta 8
    (8, 9,  6,  25.00, 3,  75.00,  13.50, 88.50),
    (8, 15, 9,  17.00, 7,  119.00, 0.00,  119.00),
    -- venta 9
    (9, 7,  7,  14.00, 15, 210.00, 0.00,  210.00),
    (9, 3,  4,  18.00, 10, 180.00, 0.00,  180.00),
    -- venta 10
    (10, 2,  2,  15.90, 10, 159.00, 0.00,  159.00),
    (10, 12, 10, 28.00, 2,  56.00,  10.08, 66.08);

SELECT * FROM USUARIOS;
