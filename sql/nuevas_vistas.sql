-- ============================================================
--  VISTAS PARA REPORTES Y ESTADISTICAS
--  Creadas para centralizar logica SQL y simplificar DAOs
--  Ejecutar en la base de datos Aprobaremos (Azure SQL)
-- ============================================================

-- ============================================================
--  VISTA 1: Ventas con datos del cajero (una fila por venta)
--  Usada en: ReportesDAO.obtenerVentasPorFecha()
--            ReportesDAO.obtenerResumenFinanciero()
-- ============================================================
CREATE VIEW V_VENTAS AS
SELECT
    v.id_venta,
    v.id_cliente,
    v.id_usuario,
    CONCAT(u.nombre, ' ', u.apellido) AS cajero,
    v.fecha,
    v.subtotal,
    v.igv_total,
    v.total_pagar
FROM VENTA v
INNER JOIN USUARIOS u ON v.id_usuario = u.id_usuario;
GO

-- ============================================================
--  VISTA 2: Detalle de venta con costo de compra
--  Usada en: ReportesDAO.obtenerResumenFinanciero()
-- ============================================================
CREATE VIEW V_DETALLE_VENTA_COSTO AS
SELECT
    dv.id_detalle,
    dv.id_venta,
    dv.id_producto,
    dv.id_lote,
    dv.cantidad,
    dv.precio_unitario AS precio_venta,
    dc.precio_unitario AS precio_compra,
    (dv.cantidad * dc.precio_unitario) AS costo_total
FROM DETALLE_VENTA dv
INNER JOIN LOTES l ON dv.id_lote = l.id_lote
INNER JOIN DETALLE_COMPRA dc ON l.id_detalle_compra = dc.id_detalle;
GO

-- ============================================================
--  VISTA 3: Ranking de productos mas/menos vendidos
--  Usada en: ReportesDAO.obtenerRankingProductos()
-- ============================================================
CREATE VIEW V_RANKING_PRODUCTOS AS
SELECT
    p.id_producto,
    p.nombre AS producto,
    SUM(dv.cantidad) AS total_vendido
FROM DETALLE_VENTA dv
INNER JOIN PRODUCTOS p ON dv.id_producto = p.id_producto
GROUP BY p.id_producto, p.nombre;
GO

-- ============================================================
--  VISTA 4: Ranking de cajeros por ventas
--  Usada en: ReportesDAO.obtenerRankingCajeros()
-- ============================================================
CREATE VIEW V_RANKING_CAJEROS AS
SELECT
    u.id_usuario,
    CONCAT(u.nombre, ' ', u.apellido) AS cajero,
    COUNT(v.id_venta) AS cantidad_ventas,
    COALESCE(SUM(v.total_pagar), 0) AS total_ventas
FROM VENTA v
INNER JOIN USUARIOS u ON v.id_usuario = u.id_usuario
GROUP BY u.id_usuario, u.nombre, u.apellido;
GO

-- ============================================================
--  VISTA 5: Productos con fecha del lote mas proximo a vencer
--  Usada en: ProductoDAO.listarConStock()
-- ============================================================
CREATE VIEW V_PRODUCTOS_STOCK AS
SELECT
    p.id_producto,
    p.nombre,
    p.id_categoria,
    p.precio_venta,
    p.stock_general,
    p.stock_minimo,
    ISNULL(CONVERT(VARCHAR, l.fecha_vencimiento, 23), 'Sin lote') AS fecha_vencimiento
FROM PRODUCTOS p
LEFT JOIN LOTES l ON l.id_lote = (
    SELECT TOP 1 id_lote
    FROM LOTES
    WHERE id_detalle_compra IN (
        SELECT id_detalle FROM DETALLE_COMPRA WHERE id_producto = p.id_producto
    )
    ORDER BY fecha_vencimiento ASC
);
GO
