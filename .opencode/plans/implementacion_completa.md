# Plan de Implementación Completa

## 1. SQL: Agregar Billetera Digital a METODO_PAGO

**Archivo:** `sql/schema_farmacia.sql:225`

Agregar `('Billetera Digital'),` después de `('Plin'),`.

## 2. NUEVO: `src/clases/Genero.java`

```java
package clases;

public class Genero {
    private int id_genero;
    private String nombre_genero;

    public Genero() {}

    public Genero(int id_genero, String nombre_genero) {
        this.id_genero = id_genero;
        this.nombre_genero = nombre_genero;
    }

    public int getId_genero() { return id_genero; }
    public void setId_genero(int id_genero) { this.id_genero = id_genero; }
    public String getNombre_genero() { return nombre_genero; }
    public void setNombre_genero(String nombre_genero) { this.nombre_genero = nombre_genero; }

    @Override
    public String toString() {
        return nombre_genero;
    }
}
```

## 3. NUEVO: `src/clases/GeneroDAO.java`

```java
package clases;

import database.ConectarBaseDatos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO {

    public List<Genero> listarTodos() {
        List<Genero> lista = new ArrayList<>();
        String sql = "SELECT id_genero, nombre_genero FROM GENEROS";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Genero(rs.getInt("id_genero"), rs.getString("nombre_genero")));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar generos: " + e.getMessage());
        }
        return lista;
    }
}
```

## 4. NUEVO: `src/clases/ClienteService.java`

```java
package clases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ClienteService {

    public static boolean esDNIValido(String dni) {
        return dni != null && dni.matches("\\d{8}");
    }

    public static boolean esNombreValido(String texto) {
        return texto != null && !texto.trim().isEmpty() && texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+");
    }

    public static boolean esApellidoValido(String texto) {
        return texto != null && !texto.trim().isEmpty() && texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+");
    }

    public static boolean esTelefonoValido(String telefono) {
        return telefono != null && telefono.matches("\\d{9}");
    }

    public static boolean esFechaValida(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) return true;
        try {
            LocalDate.parse(fecha.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalDate parseFecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) return null;
        return LocalDate.parse(fecha.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static boolean esSoloNumeros(String texto) {
        return texto != null && texto.matches("\\d*");
    }
}
```

## 5. NUEVO: `src/clases/VentaService.java`

```java
package clases;

import Pantallas_Inicio_Cajero.FilaCarrito;
import java.time.LocalDateTime;
import java.util.List;

public class VentaService {

    public static class TotalesVenta {
        public final double subtotal;
        public final double igv;
        public final double total;

        public TotalesVenta(double subtotal, double igv, double total) {
            this.subtotal = subtotal;
            this.igv = igv;
            this.total = total;
        }
    }

    public static TotalesVenta calcularTotales(List<FilaCarrito> productos) {
        ProductoDAO productoDAO = new ProductoDAO();
        double subtotal = 0.0;
        double igv = 0.0;

        for (FilaCarrito prod : productos) {
            double precio = prod.getPrecioUnitario();
            int cantidad = prod.getCantidad();
            double base = precio * cantidad;
            double porcentaje = productoDAO.obtenerPorcentajeImpuesto(prod.getIdProducto());
            subtotal += base;
            igv += base * (porcentaje / 100.0);
        }

        double total = subtotal + igv;
        return new TotalesVenta(subtotal, igv, total);
    }

    public static Venta crearVenta(int idCliente, int idUsuario, int idMetodo,
                                    double subtotal, double igv, double total) {
        return new Venta(0, idCliente, idUsuario, idMetodo,
                LocalDateTime.now(), subtotal, igv, total);
    }
}
```

## 6. ProductoDAO.java: Agregar obtenerPorcentajeImpuesto()

**Archivo:** `src/clases/ProductoDAO.java`

Agregar después del último método:

```java
    public double obtenerPorcentajeImpuesto(int idProducto) {
        String sql = "SELECT i.porcentaje FROM PRODUCTOS p "
                   + "JOIN IMPUESTOS i ON p.id_impuesto = i.id_impuesto "
                   + "WHERE p.id_producto = ?";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("porcentaje");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en obtenerPorcentajeImpuesto: " + e.getMessage());
        }
        return 0.0;
    }
```

## 7. PantallaCajero.java — Cambios

**Archivo:** `src/Pantallas_Inicio_Cajero/PantallaCajero.java`

### a) Agregar imports al inicio:
```java
import clases.VentaService;
```

### b) Reemplazar `actualizarTotalesGenerales()` (línea 729-743):

```java
public void actualizarTotalesGenerales() {
    if (productosEnCarrito.isEmpty()) {
        this.montoTotalActual = 0.0;
        lblSubtotal.setText("S/. 0.00");
        lblIGV.setText("S/. 0.00");
        lblTotalVenta.setText("S/. 0.00");
        jLabel3.setText("0");
        return;
    }

    VentaService.TotalesVenta totales = VentaService.calcularTotales(
        new java.util.ArrayList<>(productosEnCarrito.values()));

    this.montoTotalActual = totales.total;
    lblSubtotal.setText("S/. " + String.format("%.2f", totales.subtotal));
    lblIGV.setText("S/. " + String.format("%.2f", totales.igv));
    lblTotalVenta.setText("S/. " + String.format("%.2f", totales.total));
    jLabel3.setText(String.valueOf(productosEnCarrito.size()));
}
```

### c) Agregar método `abrirVentanaPago(String)` después de `actualizarTotalesGenerales()`:

```java
private void abrirVentanaPago(String tipoPago) {
    if (productosEnCarrito.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "El carrito está vacío.");
        return;
    }

    int idMetodo = obtenerIdMetodo(tipoPago);
    VentanaPago modal = new VentanaPago(
        this, true, this.montoTotalActual, tipoPago, this.idUsuario,
        this.idClienteSeleccionado, idMetodo,
        new java.util.ArrayList<>(productosEnCarrito.values())
    );
    modal.setLocationRelativeTo(this);
    modal.setNumeroVenta(lblNumeroVenta.getText());
    modal.setVisible(true);
    if (modal.isExitosa()) {
        limpiarCarrito();
    }
}
```

### d) Reemplazar los 4 handlers de pago (líneas 614-707) con:

```java
private void btnDdebitoActionPerformed(java.awt.event.ActionEvent evt) {
    abrirVentanaPago("Tarjeta de Debito");
}

private void btnBilleterDigitalActionPerformed(java.awt.event.ActionEvent evt) {
    abrirVentanaPago("Billetera Digital");
}

private void btnCreditoActionPerformed(java.awt.event.ActionEvent evt) {
    abrirVentanaPago("Tarjeta de Credito");
}

private void btnEfectivoActionPerformed(java.awt.event.ActionEvent evt) {
    abrirVentanaPago("Efectivo");
}
```

### e) Actualizar el label en initComponents (línea 299):
Cambiar `"IGV (18%):"` a `"IGV:"`

### f) Actualizar texto de botones (líneas 287-290):
```java
btnCredito.setText("Tarjeta Credito");
btnDdebito.setText("Tarjeta Debito");
```

## 8. VentanaPago.java — Cambios

**Archivo:** `src/Metodos_de_pago/VentanaPago.java`

### a) Agregar imports:
```java
import clases.VentaService;
import javax.swing.JComboBox;
```

### b) Agregar campo para combo Yape/Plin:
```java
private JComboBox<String> cmbBilletera;
```

### c) Agregar en `configurarLayaoutSegunTipo()`, dentro del else if de Billetera Digital:
```java
} else if (tipoPago.equals("Billetera Digital")) {
    lblInfo.setText("Seleccione tipo:");
    cmbBilletera = new JComboBox<>(new String[]{"Yape", "Plin"});
    // Posicionar cmbBilletera en el layout (entre lblInfo y txtEntrada)
    // ... (ajuste manual del layout o agregar un panel)
}
```

**Alternativa más simple:** Reemplazar el `else` genérico con:

En `configurarLayaoutSegunTipo()`, después de `comboCuotas.setVisible(false);`, cambiar:

```java
} else if (tipoPago.equals("Billetera Digital")) {
    lblInfo.setText("Elija plataforma:");
    cmbBilletera = new JComboBox<>(new String[]{"Yape", "Plin"});
    cmbBilletera.setVisible(true);
} else {
    lblInfo.setText("Nro de Operación / Celular:");
}
```

Y agregar `cmbBilletera` a la declaración de variables y al layout.

### d) Reemplazar cálculo de IGV en `btnConfirmarActionPerformed` (líneas 218-222):

```java
VentaService.TotalesVenta totales = VentaService.calcularTotales(this.listaProductos);
Venta nuevaVenta = VentaService.crearVenta(
    this.idCliente, this.idUsuario, this.idMetodo,
    totales.subtotal, totales.igv, totales.total);
```

### e) Al construir el voucher, pasar subtotal e igv:
```java
VentanaVoucher voucher = new VentanaVoucher(
    (java.awt.Frame) this.getParent(), true, listaVoucher, this.total
);
voucher.setSubtotal(totales.subtotal);
voucher.setIgv(totales.igv);
```

## 9. VentanaVoucher.java — Agregar setters subtotal e igv

**Archivo:** `src/Metodos_de_pago/VentanaVoucher.java`

### a) Agregar campos:
```java
private double subtotal = 0.0;
private double igv = 0.0;
```

### b) Agregar setters:
```java
public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
public void setIgv(double igv) { this.igv = igv; }
```

### c) Reemplazar en `construirHTML()` (líneas 37-38):

```java
double subtotal = this.subtotal > 0 ? this.subtotal : total / 1.18;
double igv = this.igv > 0 ? this.igv : total - subtotal;
```

## 10. VentanaRegistrarCliente.java — Refactor completo

**Archivo:** `src/Pantallas_Inicio_Cajero/VentanaRegistrarCliente.java`

Reemplazar TODO el contenido con:

```java
package Pantallas_Inicio_Cajero;

import clases.ClienteDAO;
import clases.Cliente;
import clases.ClienteService;
import clases.Genero;
import clases.GeneroDAO;
import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class VentanaRegistrarCliente extends javax.swing.JDialog {

    private PantallaCajero pantallaPadre;

    public VentanaRegistrarCliente(PantallaCajero parent, boolean modal, String dniInicial) {
        super(parent, modal);
        initComponents();
        this.pantallaPadre = parent;

        cargarGeneros();

        if (dniInicial != null && !dniInicial.isEmpty()) {
            txtDni.setText(dniInicial);
        }
    }

    private void cargarGeneros() {
        cmbGenero.removeAllItems();
        GeneroDAO dao = new GeneroDAO();
        List<Genero> lista = dao.listarTodos();
        for (Genero g : lista) {
            cmbGenero.addItem(g);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        txtDni = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtFechaNac = new javax.swing.JTextField();
        cmbGenero = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 14));
        jLabel1.setText("Registrar Cliente");

        jLabel3.setText("Nombres:");
        jLabel5.setText("Apellidos:");
        jLabel4.setText("Teléfono:");
        jLabel6.setText("Género:");
        jLabel7.setText("Fecha Nac. (YYYY-MM-DD):");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        txtDni.setToolTipText("DNI: 8 dígitos");
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != '\b' && c != 127) {
                    evt.consume();
                }
                if (txtDni.getText().length() >= 8 && c != '\b' && c != 127) {
                    evt.consume();
                }
            }
        });

        txtTelefono.setToolTipText("9 dígitos");
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != '\b' && c != 127) {
                    evt.consume();
                }
                if (txtTelefono.getText().length() >= 9 && c != '\b' && c != 127) {
                    evt.consume();
                }
            }
        });

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isLetter(c) && c != ' ' && c != '\b' && c != 127
                    && c != 'á' && c != 'é' && c != 'í' && c != 'ó' && c != 'ú'
                    && c != 'Á' && c != 'É' && c != 'Í' && c != 'Ó' && c != 'Ú'
                    && c != 'ñ' && c != 'Ñ') {
                    evt.consume();
                }
            }
        });

        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isLetter(c) && c != ' ' && c != '\b' && c != 127
                    && c != 'á' && c != 'é' && c != 'í' && c != 'ó' && c != 'ú'
                    && c != 'Á' && c != 'É' && c != 'Í' && c != 'Ó' && c != 'Ú'
                    && c != 'ñ' && c != 'Ñ') {
                    evt.consume();
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnCancelar)
                        .addGap(55, 55, 55)
                        .addComponent(btnGuardar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDni)
                            .addComponent(txtNombre)
                            .addComponent(txtApellidos)
                            .addComponent(txtTelefono)
                            .addComponent(txtFechaNac)
                            .addComponent(cmbGenero, 0, 186, Short.MAX_VALUE))))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        String dni = txtDni.getText().trim();
        String nombres = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String fechaNac = txtFechaNac.getText().trim();
        Genero genero = (Genero) cmbGenero.getSelectedItem();

        if (dni.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos obligatorios deben estar llenos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!ClienteService.esDNIValido(dni)) {
            JOptionPane.showMessageDialog(this, "El DNI debe tener exactamente 8 dígitos numéricos.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!ClienteService.esNombreValido(nombres)) {
            JOptionPane.showMessageDialog(this, "Los nombres solo deben contener letras y espacios.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!ClienteService.esApellidoValido(apellidos)) {
            JOptionPane.showMessageDialog(this, "Los apellidos solo deben contener letras y espacios.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!ClienteService.esTelefonoValido(telefono)) {
            JOptionPane.showMessageDialog(this, "El teléfono debe tener exactamente 9 dígitos numéricos.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!fechaNac.isEmpty() && !ClienteService.esFechaValida(fechaNac)) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use YYYY-MM-DD.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (genero == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un género.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDate fecha = ClienteService.parseFecha(fechaNac);
        Cliente nuevoCliente = new Cliente(0, nombres, apellidos, dni, genero.getId_genero(), fecha);
        ClienteDAO controlador = new ClienteDAO();

        btnGuardar.setEnabled(false);

        new Thread(() -> {
            try {
                int idGenerado = controlador.insertar(nuevoCliente);

                if (idGenerado != -1 && !telefono.isEmpty()) {
                    controlador.insertarTelefono(idGenerado, telefono);
                }

                int finalId = idGenerado;
                SwingUtilities.invokeLater(() -> {
                    btnGuardar.setEnabled(true);
                    if (finalId != -1) {
                        JOptionPane.showMessageDialog(this, "¡Cliente registrado con éxito!");
                        if (pantallaPadre != null) {
                            pantallaPadre.actualizarInterfazClienteRegistrado(finalId, nombres + " " + apellidos);
                        }
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al guardar. Verifique si el DNI ya existe.");
                    }
                });
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> {
                    btnGuardar.setEnabled(true);
                    JOptionPane.showMessageDialog(this, "Error crítico: " + e.getMessage());
                });
            }
        }).start();
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<Genero> cmbGenero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtFechaNac;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
}
```

> **Nota:** Este refactor ELIMINA `cmbTipoDocumento` (solo DNI), cambia `txtDniRuc` → `txtDni`, agrega `cmbGenero`, `txtFechaNac`, validaciones con `KeyAdapter` en tiempo real y `ClienteService` en el guardado.

## 11. MetodoPagoDAO.buscarPorNombre — Ya funciona

El método actual ya hace `WHERE nombre = ?`. Como ahora los strings en PantallaCajero coinciden exactamente con la BD (`"Tarjeta de Credito"`, `"Tarjeta de Debito"`, `"Billetera Digital"`, `"Efectivo"`), no necesita cambios.

---

## Resumen de archivos

| # | Acción | Archivo |
|---|---|---|
| 1 | ✏️ Editar | `sql/schema_farmacia.sql` — agregar `Billetera Digital` |
| 2 | 🆕 Crear | `src/clases/Genero.java` |
| 3 | 🆕 Crear | `src/clases/GeneroDAO.java` |
| 4 | 🆕 Crear | `src/clases/ClienteService.java` |
| 5 | 🆕 Crear | `src/clases/VentaService.java` |
| 6 | ✏️ Editar | `src/clases/ProductoDAO.java` — agregar `obtenerPorcentajeImpuesto()` |
| 7 | ✏️ Editar | `src/Pantallas_Inicio_Cajero/PantallaCajero.java` — fix IGV, unificar handlers |
| 8 | ✏️ Editar | `src/Metodos_de_pago/VentanaPago.java` — fix IGV, combo Yape/Plin |
| 9 | ✏️ Editar | `src/Metodos_de_pago/VentanaVoucher.java` — setters subtotal e igv |
| 10 | ✏️ Editar | `src/Pantallas_Inicio_Cajero/VentanaRegistrarCliente.java` — refactor completo |
