package consola;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;

import eventos.Evento;
import eventos.Localidad;
import eventos.Venue;
import eventos.VenueNumerado;
import tiquetes.Tiquete;
import tiquetes.TiqueteBasico;
import tiquetes.TiqueteDeluxe;
import tiquetes.TiqueteGrupal;
import tiquetes.TiqueteNumerado;
import usuarios.Cliente;
import usuarios.Organizador;
import usuarios.Administrador;

public class app extends JFrame {

    private JPanel panelContenedor;
    private CardLayout cardLayout;
    private Aplicacion logica;

    private static final String LOGIN = "LOGIN";
    private static final String REGISTRO = "REGISTRO";
    private static final String CLIENTE = "CLIENTE";
    private static final String ORGANIZADOR = "ORGANIZADOR";
    private static final String ADMIN = "ADMIN";

    private Cliente clienteActual;
    private Organizador organizadorActual;

    public app() {
        logica = new Aplicacion();
        logica.cargarTodo();

        setTitle("BoletMaster");
        setSize(800, 560);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);

        panelContenedor.add(pantallaLogin(), LOGIN);
        panelContenedor.add(pantallaRegistro(), REGISTRO);
        panelContenedor.add(pantallaCliente(), CLIENTE);
        panelContenedor.add(pantallaOrganizador(), ORGANIZADOR);
        panelContenedor.add(new AdminMenu(logica), ADMIN);

        add(panelContenedor);
    }

    private JPanel pantallaLogin() {
        JPanel panel = new JPanel(null);

        JLabel t = new JLabel("Iniciar Sesión");
        t.setBounds(320, 20, 200, 28);
        t.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(t);

        JLabel lr = new JLabel("Rol:");
        lr.setBounds(140, 90, 100, 20);
        panel.add(lr);

        String[] roles = {"Cliente", "Organizador", "Administrador"};
        JComboBox<String> rol = new JComboBox<>(roles);
        rol.setBounds(230, 90, 300, 25);
        panel.add(rol);

        JLabel lu = new JLabel("Usuario:");
        lu.setBounds(140, 140, 100, 20);
        panel.add(lu);

        JTextField user = new JTextField();
        user.setBounds(230, 140, 300, 25);
        panel.add(user);

        JLabel lp = new JLabel("Contraseña:");
        lp.setBounds(140, 190, 100, 20);
        panel.add(lp);

        JPasswordField pass = new JPasswordField();
        pass.setBounds(230, 190, 300, 25);
        panel.add(pass);

        JButton entrar = new JButton("Entrar");
        entrar.setBounds(230, 240, 140, 34);
        panel.add(entrar);

        JButton registrar = new JButton("Registrarse");
        registrar.setBounds(390, 240, 140, 34);
        panel.add(registrar);

        entrar.addActionListener(e -> {
            String r = rol.getSelectedItem().toString();
            String u = user.getText();
            String p = new String(pass.getPassword());

            Object encontrado = null;

            if (r.equals("Cliente"))
                for (Cliente c : logica.clientes)
                    if (c.getLog().equals(u)) encontrado = c;

            if (r.equals("Organizador"))
                for (Organizador o : logica.organizadores)
                    if (o.getLog().equals(u)) encontrado = o;

            if (r.equals("Administrador"))
                for (Administrador a : logica.staff)
                    if (a.getLog().equals(u)) encontrado = a;

            if (encontrado == null) {
                JOptionPane.showMessageDialog(panel, "Usuario no encontrado");
                return;
            }

            String real = null;
            if (encontrado instanceof Cliente) real = ((Cliente) encontrado).getContraseña();
            if (encontrado instanceof Organizador) real = ((Organizador) encontrado).getContraseña();
            if (encontrado instanceof Administrador) real = ((Administrador) encontrado).getContraseña();

            if (!p.equals(real)) {
                JOptionPane.showMessageDialog(panel, "Contraseña incorrecta");
                return;
            }

            if (encontrado instanceof Cliente) {
                clienteActual = (Cliente) encontrado;
                refreshClientePanels();
                cardLayout.show(panelContenedor, CLIENTE);
            }
            if (encontrado instanceof Organizador) {
                organizadorActual = (Organizador) encontrado;
                cardLayout.show(panelContenedor, ORGANIZADOR);
            }
            if (encontrado instanceof Administrador) cardLayout.show(panelContenedor, ADMIN);
        });

        registrar.addActionListener(e -> cardLayout.show(panelContenedor, REGISTRO));

        return panel;
    }

    private JPanel pantallaRegistro() {
        JPanel panel = new JPanel(null);

        JLabel t = new JLabel("Registro");
        t.setBounds(320, 20, 200, 28);
        t.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(t);

        JLabel lr = new JLabel("Rol:");
        lr.setBounds(140, 90, 100, 20);
        panel.add(lr);

        String[] roles = {"Cliente", "Organizador", "Administrador"};
        JComboBox<String> rol = new JComboBox<>(roles);
        rol.setBounds(230, 90, 300, 25);
        panel.add(rol);

        JLabel lu = new JLabel("Usuario:");
        lu.setBounds(140, 140, 100, 20);
        panel.add(lu);

        JTextField user = new JTextField();
        user.setBounds(230, 140, 300, 25);
        panel.add(user);

        JLabel lp = new JLabel("Contraseña:");
        lp.setBounds(140, 190, 100, 20);
        panel.add(lp);

        JPasswordField pass = new JPasswordField();
        pass.setBounds(230, 190, 300, 25);
        panel.add(pass);

        JLabel lm = new JLabel("Clave Admin:");
        lm.setBounds(140, 240, 100, 20);
        panel.add(lm);

        JTextField master = new JTextField();
        master.setBounds(230, 240, 300, 25);
        panel.add(master);

        lm.setVisible(false);
        master.setVisible(false);

        rol.addActionListener(e -> {
            boolean esAdmin = rol.getSelectedItem().equals("Administrador");
            lm.setVisible(esAdmin);
            master.setVisible(esAdmin);
        });

        JButton crear = new JButton("Registrar");
        crear.setBounds(230, 290, 140, 34);
        panel.add(crear);

        JButton volver = new JButton("Volver");
        volver.setBounds(390, 290, 140, 34);
        panel.add(volver);

        volver.addActionListener(e -> cardLayout.show(panelContenedor, LOGIN));

        crear.addActionListener(e -> {
            String r = rol.getSelectedItem().toString();
            String u = user.getText();
            String p = new String(pass.getPassword());

            if (u.isBlank() || p.isBlank()) {
                JOptionPane.showMessageDialog(panel, "Completa todos los campos");
                return;
            }

            if (r.equals("Cliente")) {
                Cliente c = new Cliente(u, p);
                logica.clientes.add(c);
                logica.guardarCliente("clientes.json", c);
            }

            if (r.equals("Organizador")) {
                Organizador o = new Organizador(u, p);
                logica.organizadores.add(o);
                logica.guardarOrg("organizadores.json", o);
            }

            if (r.equals("Administrador")) {
                if (!master.getText().equals("LlaveMaestra")) {
                    JOptionPane.showMessageDialog(panel, "Clave maestra incorrecta");
                    return;
                }
                Administrador a = new Administrador(u, p);
                logica.staff.add(a);
                logica.guardarAdmin("staff.json", a);
            }

            logica.guardarTodo();
            JOptionPane.showMessageDialog(panel, "Registro exitoso");
            cardLayout.show(panelContenedor, LOGIN);
        });

        return panel;
    }

    private JPanel pantallaCliente() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel left = new JPanel();
        left.setLayout(new GridLayout(6, 1, 8, 8));
        left.setPreferredSize(new Dimension(220, 0));

        JButton btnBuscarEventos = new JButton("Buscar Eventos");
        JButton btnMisCompras = new JButton("Mis Compras");
        JButton btnPonerReventa = new JButton("Poner en Reventa");
        JButton btnConsultarSaldo = new JButton("Consultar Saldo");
        JButton btnCerrarSesion = new JButton("Cerrar sesión");

        left.add(new JLabel("Menu Cliente", SwingConstants.CENTER));
        left.add(btnBuscarEventos);
        left.add(btnMisCompras);
        left.add(btnPonerReventa);
        left.add(btnConsultarSaldo);
        left.add(btnCerrarSesion);

        panel.add(left, BorderLayout.WEST);

        JPanel center = new JPanel(new CardLayout());
        center.setName("CENTER_PANEL");

        JPanel buscarEventosPanel = new JPanel(new BorderLayout());
        JPanel eventoCard = new JPanel();
        eventoCard.setLayout(new BoxLayout(eventoCard, BoxLayout.Y_AXIS));
        eventoCard.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblNombre = new JLabel("", SwingConstants.CENTER);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 18));
        JLabel lblOrganizador = new JLabel("", SwingConstants.CENTER);
        JLabel lblFecha = new JLabel("", SwingConstants.CENTER);
        JLabel lblHorario = new JLabel("", SwingConstants.CENTER);
        JLabel lblCap = new JLabel("", SwingConstants.CENTER);
        JLabel lblPrecio = new JLabel("", SwingConstants.CENTER);
        JTextArea taDescripcion = new JTextArea();
        taDescripcion.setLineWrap(true);
        taDescripcion.setWrapStyleWord(true);
        taDescripcion.setEditable(false);
        JScrollPane descScroll = new JScrollPane(taDescripcion);
        descScroll.setPreferredSize(new Dimension(300, 140));

        eventoCard.add(lblNombre);
        eventoCard.add(Box.createRigidArea(new Dimension(0, 8)));
        eventoCard.add(lblOrganizador);
        eventoCard.add(lblFecha);
        eventoCard.add(lblHorario);
        eventoCard.add(lblCap);
        eventoCard.add(lblPrecio);
        eventoCard.add(Box.createRigidArea(new Dimension(0, 8)));
        eventoCard.add(descScroll);

        buscarEventosPanel.add(eventoCard, BorderLayout.CENTER);

        JPanel botonesEvento = new JPanel();
        JButton btnAnteriorEv = new JButton("Anterior");
        JButton btnSiguienteEv = new JButton("Siguiente");
        JButton btnComprarEv = new JButton("Comprar");
        botonesEvento.add(btnAnteriorEv);
        botonesEvento.add(btnComprarEv);
        botonesEvento.add(btnSiguienteEv);

        buscarEventosPanel.add(botonesEvento, BorderLayout.SOUTH);

        JPanel misComprasPanel = new JPanel(new BorderLayout());
        DefaultListModel<String> comprasModel = new DefaultListModel<>();
        JList<String> comprasList = new JList<>(comprasModel);
        misComprasPanel.add(new JScrollPane(comprasList), BorderLayout.CENTER);
        JButton btnRefrescarCompras = new JButton("Refrescar");
        misComprasPanel.add(btnRefrescarCompras, BorderLayout.SOUTH);

        JPanel reventaPanel = new JPanel(new BorderLayout());
        DefaultListModel<String> reventaModel = new DefaultListModel<>();
        JList<String> reventaList = new JList<>(reventaModel);
        reventaPanel.add(new JScrollPane(reventaList), BorderLayout.CENTER);
        JPanel reventaBot = new JPanel();
        JTextField txtPrecioReventa = new JTextField(8);
        JButton btnPublicarReventa = new JButton("Publicar oferta");
        reventaBot.add(new JLabel("Precio:"));
        reventaBot.add(txtPrecioReventa);
        reventaBot.add(btnPublicarReventa);
        reventaPanel.add(reventaBot, BorderLayout.SOUTH);

        JPanel saldoPanel = new JPanel(new BorderLayout());
        JLabel lblSaldo = new JLabel("", SwingConstants.CENTER);
        saldoPanel.add(lblSaldo, BorderLayout.CENTER);
        JPanel saldoBot = new JPanel();
        JButton btnRecargar = new JButton("Recargar saldo");
        saldoBot.add(btnRecargar);
        saldoPanel.add(saldoBot, BorderLayout.SOUTH);

        center.add(buscarEventosPanel, "BUSCAR");
        center.add(misComprasPanel, "COMPRAS");
        center.add(reventaPanel, "REVENTA");
        center.add(saldoPanel, "SALDO");

        panel.add(center, BorderLayout.CENTER);

        final int[] idxEvento = {0};

        Runnable mostrarEvento = () -> {
            if (logica.eventosProx.isEmpty()) {
                lblNombre.setText("No hay eventos disponibles");
                lblOrganizador.setText("");
                lblFecha.setText("");
                lblHorario.setText("");
                lblCap.setText("");
                lblPrecio.setText("");
                taDescripcion.setText("");
                return;
            }
            if (idxEvento[0] < 0) idxEvento[0] = 0;
            if (idxEvento[0] >= logica.eventosProx.size()) idxEvento[0] = logica.eventosProx.size() - 1;
            Evento ev = logica.eventosProx.get(idxEvento[0]);
            lblNombre.setText(ev.getNombre());
            lblOrganizador.setText("Organizador: " + (ev.getOrganizador() != null ? ev.getOrganizador().getLog() : "N/A"));
            lblFecha.setText("Fecha: " + ev.getFecha());
            lblHorario.setText("Horario: " + ev.getHoraIni() + " - " + ev.getHoraFin());
            lblCap.setText("Capacidad: " + ev.getCapacidadTotalEvento());
            lblPrecio.setText("Precio base: " + ev.getPrecioBase());
        };

        btnSiguienteEv.addActionListener(e -> {
            if (!logica.eventosProx.isEmpty()) {
                idxEvento[0]++;
                if (idxEvento[0] >= logica.eventosProx.size()) idxEvento[0] = 0;
                mostrarEvento.run();
            }
        });

        btnAnteriorEv.addActionListener(e -> {
            if (!logica.eventosProx.isEmpty()) {
                idxEvento[0]--;
                if (idxEvento[0] < 0) idxEvento[0] = logica.eventosProx.size() - 1;
                mostrarEvento.run();
            }
        });

        btnComprarEv.addActionListener(e -> {
            if (clienteActual == null) {
                JOptionPane.showMessageDialog(this, "Inicia sesión como cliente para comprar.");
                return;
            }
            if (logica.eventosProx.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay eventos para comprar.");
                return;
            }
            Evento ev = logica.eventosProx.get(idxEvento[0]);
            java.util.List<Localidad> locs = ev.getLocalidades();
            if (locs == null || locs.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay localidades definidas para este evento.");
                return;
            }
            Vector<String> opciones = new Vector<>();
            for (int i = 0; i < locs.size(); i++) {
                Localidad l = locs.get(i);
                opciones.add(i + " - " + l.getNombre() + " (cap:" + l.getCapacidad() + ", +" + l.getPorcentaje() + "%)");
            }
            String sel = (String) JOptionPane.showInputDialog(this, "Selecciona localidad:", "Localidades",
                    JOptionPane.PLAIN_MESSAGE, null, opciones.toArray(), opciones.get(0));
            if (sel == null) return;
            int selIndex = Integer.parseInt(sel.split(" - ")[0]);
            Localidad lieu = locs.get(selIndex);
            String sPeuple = JOptionPane.showInputDialog(this, "¿Cuántas personas?");
            if (sPeuple == null) return;
            int peuple;
            try {
                peuple = Integer.parseInt(sPeuple);
                if (peuple <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Número inválido");
                return;
            }
            double costoUnit = ev.getPrecioBase() + (ev.getPrecioBase() * (lieu.getPorcentaje() / 100.0));
            double costoTotal = costoUnit * peuple;
            String[] opcionesPago = {"Pago por saldo", "Pago por terceros", "Cancelar"};
            int pago = JOptionPane.showOptionDialog(this, "Costo total: " + costoTotal + "\nElige método de pago:",
                    "Pago", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcionesPago, opcionesPago[0]);
            if (pago == 2 || pago == JOptionPane.CLOSED_OPTION) return;
            if (pago == 0) {
                if (clienteActual.getSaldo() < costoTotal) {
                    JOptionPane.showMessageDialog(this, "Saldo insuficiente.");
                    return;
                }
            }
            if (ev.getVenue() instanceof VenueNumerado) {
                for (int i = 0; i < peuple; i++) {
                    TiqueteNumerado tn = new TiqueteNumerado(ev.getPrecioBase(), lieu, ev, clienteActual);
                    clienteActual.getTiqVi().add(tn);
                    ev.getTiqRes().add(tn);
                    if (pago == 0) clienteActual.setSaldo(clienteActual.getSaldo() - tn.getCosto());
                }
            } else {
                if (peuple > 1) {
                    TiqueteGrupal tg = new TiqueteGrupal(peuple, lieu, ev, clienteActual);
                    clienteActual.getTiqVi().add(tg);
                    ev.getTiqRes().add(tg);
                    if (pago == 0) clienteActual.setSaldo(clienteActual.getSaldo() - tg.getCosto());
                } else {
                    TiqueteBasico tb = new TiqueteBasico(lieu, ev, clienteActual);
                    clienteActual.getTiqVi().add(tb);
                    ev.getTiqRes().add(tb);
                    if (pago == 0) clienteActual.setSaldo(clienteActual.getSaldo() - tb.getCosto());
                }
            }
            logica.guardarTodo();
            JOptionPane.showMessageDialog(this, "Compra realizada.");
            refreshMisCompras(comprasModel, comprasList);
            refreshReventaList(reventaModel);
            lblSaldo.setText("Saldo: " + clienteActual.getSaldo());
        });

        btnRefrescarCompras.addActionListener(e -> refreshMisCompras(comprasModel, comprasList));

        btnPublicarReventa.addActionListener(e -> {
            int selIndex = reventaList.getSelectedIndex();
            if (selIndex == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un tiquete para publicar.");
                return;
            }
            Tiquete seleccionado = clienteActual.getTiqVi().get(selIndex);
            String pp = txtPrecioReventa.getText();
            if (pp.isBlank()) {
                JOptionPane.showMessageDialog(this, "Inserta un precio válido.");
                return;
            }
            double precio;
            try {
                precio = Double.parseDouble(pp);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio inválido.");
                return;
            }
            String key = clienteActual.getLog() + seleccionado.getIdentificador();
            logica.marketplace.publicarOferta(key, clienteActual, seleccionado, precio);
            logica.guardarTodo();
            JOptionPane.showMessageDialog(this, "Oferta publicada en marketplace.");
            txtPrecioReventa.setText("");
        });

        btnRecargar.addActionListener(e -> {
            String s = JOptionPane.showInputDialog(this, "Monto a acreditar:");
            if (s == null) return;
            double m;
            try {
                m = Double.parseDouble(s);
                if (m <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Monto inválido.");
                return;
            }
            clienteActual.setSaldo(clienteActual.getSaldo() + m);
            logica.guardarTodo();
            lblSaldo.setText("Saldo: " + clienteActual.getSaldo());
            JOptionPane.showMessageDialog(this, "Saldo acreditado.");
        });

        btnBuscarEventos.addActionListener(e -> ((CardLayout) center.getLayout()).show(center, "BUSCAR"));
        btnMisCompras.addActionListener(e -> {
            refreshMisCompras(comprasModel, comprasList);
            ((CardLayout) center.getLayout()).show(center, "COMPRAS");
        });
        btnPonerReventa.addActionListener(e -> {
            refreshReventaList(reventaModel);
            ((CardLayout) center.getLayout()).show(center, "REVENTA");
        });
        btnConsultarSaldo.addActionListener(e -> {
            lblSaldo.setText("Saldo: " + (clienteActual != null ? clienteActual.getSaldo() : 0.0));
            ((CardLayout) center.getLayout()).show(center, "SALDO");
        });

        btnCerrarSesion.addActionListener(e -> {
            clienteActual = null;
            cardLayout.show(panelContenedor, LOGIN);
        });

        mostrarEvento.run();

        return panel;
    }

    private void refreshMisCompras(DefaultListModel<String> comprasModel, JList<String> comprasList) {
        comprasModel.clear();
        if (clienteActual == null) return;
        for (Tiquete t : clienteActual.getTiqVi()) {
            comprasModel.addElement(t.getEvento().getNombre() + " | " + t.getTipo() + " | id:" + t.getIdentificador());
        }
        comprasList.setModel(comprasModel);
    }

    private void refreshReventaList(DefaultListModel<String> reventaModel) {
        reventaModel.clear();
        if (clienteActual == null) return;
        for (Tiquete t : clienteActual.getTiqVi()) {
            if (!t.getTipo().equals("Deluxe")) {
                reventaModel.addElement(t.getEvento().getNombre() + " | " + t.getTipo() + " | id:" + t.getIdentificador());
            }
        }
    }

    private JPanel pantallaOrganizador() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel left = new JPanel(new GridLayout(6, 1, 8, 8));
        left.setPreferredSize(new Dimension(240, 0));

        JButton btnCrearEvento = new JButton("Crear Evento");
        JButton btnCrearVenue = new JButton("Crear Venue");
        JButton btnMenuCliente = new JButton("Acceder a menú Cliente");
        JButton btnRevisarPasados = new JButton("Revisar eventos pasados");
        JButton btnCerrarSesionOrg = new JButton("Cerrar sesión");

        left.add(new JLabel("Menu Organizador", SwingConstants.CENTER));
        left.add(btnCrearEvento);
        left.add(btnCrearVenue);
        left.add(btnMenuCliente);
        left.add(btnRevisarPasados);
        left.add(btnCerrarSesionOrg);

        panel.add(left, BorderLayout.WEST);

        JPanel center = new JPanel(new CardLayout());
        center.setName("ORG_CENTER");

        JPanel placeholder = new JPanel(new BorderLayout());
        placeholder.add(new JLabel("Usa las opciones de la izquierda para crear venues y eventos." , SwingConstants.CENTER), BorderLayout.CENTER);

        center.add(placeholder, "PLACEHOLDER");

        panel.add(center, BorderLayout.CENTER);

        btnCrearVenue.addActionListener(e -> crearVenueDialog());
        btnCrearEvento.addActionListener(e -> crearEventoDialog());
        btnMenuCliente.addActionListener(e -> {
            if (organizadorActual != null) {
                clienteActual = organizadorActual;
                refreshClientePanels();
                cardLayout.show(panelContenedor, CLIENTE);
            } else {
                JOptionPane.showMessageDialog(this, "Inicia sesión como organizador para acceder al menú de cliente.");
            }
        });

        btnRevisarPasados.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Inserta el nombre del evento:");
            if (nombre == null) return;
            Evento encontrado = null;
            for (Evento ev : logica.eventosPas) {
                if (ev.getNombre().equals(nombre)) {
                    encontrado = ev;
                    break;
                }
            }
            if (encontrado == null) {
                JOptionPane.showMessageDialog(this, "No se encontró el evento: " + nombre);
                return;
            }
            double suma = 0;
            for (Tiquete t : encontrado.getTiqPros()) {
                suma += t.getCosto() * encontrado.tasa;
            }
            JOptionPane.showMessageDialog(this, "Ganancias totales: " + suma);
        });

        btnCerrarSesionOrg.addActionListener(e -> {
            organizadorActual = null;
            cardLayout.show(panelContenedor, LOGIN);
        });

        return panel;
    }

    private void crearVenueDialog() {
        JDialog d = new JDialog(this, "Crear Venue", true);
        d.setSize(420, 380);
        d.setLocationRelativeTo(this);
        d.setLayout(new BorderLayout());

        JPanel form = new JPanel(null);

        JLabel lNom = new JLabel("Nombre:");
        lNom.setBounds(20, 20, 100, 25);
        JTextField tfNom = new JTextField();
        tfNom.setBounds(130, 20, 240, 25);

        JLabel lDir = new JLabel("Dirección:");
        lDir.setBounds(20, 60, 100, 25);
        JTextField tfDir = new JTextField();
        tfDir.setBounds(130, 60, 240, 25);

        JLabel lCap = new JLabel("Capacidad total:");
        lCap.setBounds(20, 100, 100, 25);
        JTextField tfCap = new JTextField();
        tfCap.setBounds(130, 100, 240, 25);

        form.add(lNom);
        form.add(tfNom);
        form.add(lDir);
        form.add(tfDir);
        form.add(lCap);
        form.add(tfCap);

        DefaultListModel<String> locModel = new DefaultListModel<>();
        JList<String> locList = new JList<>(locModel);
        JScrollPane sp = new JScrollPane(locList);
        sp.setPreferredSize(new Dimension(380, 120));

        JPanel bottom = new JPanel();
        JButton btnAddLoc = new JButton("Añadir Localidad");
        JButton btnFinish = new JButton("Finalizar y Guardar");
        bottom.add(btnAddLoc);
        bottom.add(btnFinish);

        d.add(form, BorderLayout.NORTH);
        d.add(sp, BorderLayout.CENTER);
        d.add(bottom, BorderLayout.SOUTH);

        ArrayList<String[]> localidadesTemp = new ArrayList<>();

        btnAddLoc.addActionListener(e -> {
            String nomb = JOptionPane.showInputDialog(d, "Ingresa el nombre de la localidad:");
            if (nomb == null) return;
            String porStr = JOptionPane.showInputDialog(d, "Ingresa el porcentaje de aumento de precio (0 para la base):");
            if (porStr == null) return;
            int porcentaje;
            try {
                porcentaje = (int) Double.parseDouble(porStr);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(d, "Porcentaje inválido");
                return;
            }
            String capStr = JOptionPane.showInputDialog(d, "Ingresa capacidad máxima (per cápita):");
            if (capStr == null) return;
            int perCap;
            try {
                perCap = Integer.parseInt(capStr);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(d, "Capacidad inválida");
                return;
            }
            localidadesTemp.add(new String[]{nomb, String.valueOf(porcentaje), String.valueOf(perCap)});
            locModel.addElement(nomb + " | +" + porcentaje + "% | cap:" + perCap);
        });

        btnFinish.addActionListener(e -> {
            String nombre = tfNom.getText();
            String dir = tfDir.getText();
            String capS = tfCap.getText();
            if (nombre.isBlank() || dir.isBlank() || capS.isBlank()) {
                JOptionPane.showMessageDialog(d, "Completa todos los campos");
                return;
            }
            int cap;
            try {
                cap = Integer.parseInt(capS);
                if (cap <= 0) throw new NumberFormatException();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(d, "Capacidad inválida");
                return;
            }
            Venue v = new Venue(dir, cap, nombre);
            for (String[] arr : localidadesTemp) {
                String nomb = arr[0];
                int por = Integer.parseInt(arr[1]);
                int perCap = Integer.parseInt(arr[2]);
                v.agregarLocalidad(nomb, por, perCap);
            }
            logica.venues.add(v);
            logica.guardarVenue("venues.json", v);
            logica.guardarTodo();
            JOptionPane.showMessageDialog(d, "Venue creado exitosamente");
            d.dispose();
        });

        d.setVisible(true);
    }

    private void crearEventoDialog() {
        if (organizadorActual == null) {
            JOptionPane.showMessageDialog(this, "Inicia sesión como organizador para crear eventos.");
            return;
        }
        JDialog d = new JDialog(this, "Crear Evento", true);
        d.setSize(560, 520);
        d.setLocationRelativeTo(this);
        d.setLayout(null);

        JLabel lNom = new JLabel("Nombre:");
        lNom.setBounds(20, 20, 120, 25);
        JTextField tfNom = new JTextField();
        tfNom.setBounds(160, 20, 360, 25);

        JLabel lCap = new JLabel("Capacidad (evento):");
        lCap.setBounds(20, 60, 140, 25);
        JTextField tfCap = new JTextField();
        tfCap.setBounds(160, 60, 120, 25);

        JLabel lTipo = new JLabel("Tipo:");
        lTipo.setBounds(20, 100, 120, 25);
        JTextField tfTipo = new JTextField();
        tfTipo.setBounds(160, 100, 200, 25);

        JLabel lFecha = new JLabel("Fecha:");
        lFecha.setBounds(20, 140, 120, 25);
        JTextField tfFecha = new JTextField();
        tfFecha.setBounds(160, 140, 200, 25);

        JLabel lIni = new JLabel("Hora inicio:");
        lIni.setBounds(20, 180, 120, 25);
        JTextField tfIni = new JTextField();
        tfIni.setBounds(160, 180, 120, 25);

        JLabel lFin = new JLabel("Hora fin:");
        lFin.setBounds(300, 180, 120, 25);
        JTextField tfFin = new JTextField();
        tfFin.setBounds(380, 180, 140, 25);

        JLabel lPrecio = new JLabel("Precio base:");
        lPrecio.setBounds(20, 220, 120, 25);
        JTextField tfPrecio = new JTextField();
        tfPrecio.setBounds(160, 220, 120, 25);

        JLabel lBenef = new JLabel("Beneficios deluxe:");
        lBenef.setBounds(20, 260, 140, 25);
        JTextField tfBenef = new JTextField();
        tfBenef.setBounds(160, 260, 360, 25);

        JLabel lVenue = new JLabel("Venue:");
        lVenue.setBounds(20, 300, 100, 25);
        JComboBox<String> cbVenues = new JComboBox<>();
        for (Venue v : logica.venues) {
            cbVenues.addItem(v.getNombre());
        }
        cbVenues.setBounds(160, 300, 260, 25);

        JButton btnAgregarLocalidad = new JButton("Añadir localidad");
        btnAgregarLocalidad.setBounds(160, 340, 160, 30);
        JButton btnFinalizar = new JButton("Finalizar (Enviar a pendientes)");
        btnFinalizar.setBounds(340, 420, 200, 30);

        DefaultListModel<String> listaLocModel = new DefaultListModel<>();
        JList<String> listaLoc = new JList<>(listaLocModel);
        JScrollPane spLoc = new JScrollPane(listaLoc);
        spLoc.setBounds(20, 380, 300, 100);

        d.add(lNom);
        d.add(tfNom);
        d.add(lCap);
        d.add(tfCap);
        d.add(lTipo);
        d.add(tfTipo);
        d.add(lFecha);
        d.add(tfFecha);
        d.add(lIni);
        d.add(tfIni);
        d.add(lFin);
        d.add(tfFin);
        d.add(lPrecio);
        d.add(tfPrecio);
        d.add(lBenef);
        d.add(tfBenef);
        d.add(lVenue);
        d.add(cbVenues);
        d.add(btnAgregarLocalidad);
        d.add(spLoc);
        d.add(btnFinalizar);

        ArrayList<Localidad> localidadesElegidas = new ArrayList<>();

        btnAgregarLocalidad.addActionListener(e -> {
            int sel = cbVenues.getSelectedIndex();
            if (sel == -1) {
                JOptionPane.showMessageDialog(d, "Selecciona un venue primero");
                return;
            }
            Venue v = logica.venues.get(sel);
            if (v.getLocalidades() == null || v.getLocalidades().isEmpty()) {
                JOptionPane.showMessageDialog(d, "El venue no tiene localidades definidas");
                return;
            }
            Vector<String> opciones = new Vector<>();
            for (int i = 0; i < v.getLocalidades().size(); i++) {
                Localidad loc = v.getLocalidades().get(i);
                opciones.add(i + " - " + loc.getNombre() + " (+" + loc.getPorcentaje() + "%)");
            }
            String chosen = (String) JOptionPane.showInputDialog(d, "Selecciona localidad del venue:", "Localidades",
                    JOptionPane.PLAIN_MESSAGE, null, opciones.toArray(), opciones.get(0));
            if (chosen == null) return;
            int idx = Integer.parseInt(chosen.split(" - ")[0]);
            Localidad plantilla = v.getLocalidades().get(idx);
            String capStr = JOptionPane.showInputDialog(d, "Indica la capacidad máxima para esta localidad (per cápita):");
            if (capStr == null) return;
            int capLocal;
            try {
                capLocal = Integer.parseInt(capStr);
                if (capLocal <= 0) throw new NumberFormatException();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(d, "Capacidad inválida");
                return;
            }
            boolean ya = false;
            for (Localidad L : localidadesElegidas) {
                if (L.getNombre().equalsIgnoreCase(plantilla.getNombre())) {
                    ya = true;
                    break;
                }
            }
            if (ya) {
                JOptionPane.showMessageDialog(d, "Esta localidad ya fue añadida");
                return;
            }
            int sumaActual = 0;
            for (Localidad L : localidadesElegidas) sumaActual += L.getCapacidad();
            int capEvento;
            try {
                capEvento = Integer.parseInt(tfCap.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(d, "Define la capacidad del evento primero (número válido)");
                return;
            }
            if (sumaActual + capLocal > capEvento || sumaActual + capLocal > v.getCapMax()) {
                JOptionPane.showMessageDialog(d, "La suma de capacidades excede la capacidad del evento o del venue");
                return;
            }
            if (localidadesElegidas.isEmpty() && plantilla.getPorcentaje() != 0) {
                JOptionPane.showMessageDialog(d, "La primera localidad añadida debe ser la base (porcentaje = 0)");
                return;
            }
            Localidad nueva = new Localidad(plantilla.getNombre(), plantilla.getPorcentaje(), plantilla.getVenue(), capLocal);
            localidadesElegidas.add(nueva);
            listaLocModel.addElement(nueva.getNombre() + " | +" + nueva.getPorcentaje() + "% | cap:" + nueva.getCapacidad());
        });

        btnFinalizar.addActionListener(e -> {
            String nombre = tfNom.getText();
            String capS = tfCap.getText();
            String tipo = tfTipo.getText();
            String fecha = tfFecha.getText();
            String ini = tfIni.getText();
            String fin = tfFin.getText();
            String precioS = tfPrecio.getText();
            String benef = tfBenef.getText();
            int venueIndex = cbVenues.getSelectedIndex();
            if (nombre.isBlank() || capS.isBlank() || tipo.isBlank() || fecha.isBlank() || ini.isBlank() || fin.isBlank() || precioS.isBlank() || venueIndex == -1) {
                JOptionPane.showMessageDialog(d, "Completa todos los campos");
                return;
            }
            int capEvento;
            double precio;
            try {
                capEvento = Integer.parseInt(capS);
                precio = Double.parseDouble(precioS);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(d, "Capacidad o precio inválidos");
                return;
            }
            Venue v = logica.venues.get(venueIndex);
            Evento ev = new Evento(organizadorActual, capEvento, nombre, tipo, fecha, ini, fin, precio, v, benef);
            for (Localidad L : localidadesElegidas) ev.getLocalidades().add(L);
            if (ev.getLocalidades().isEmpty()) {
                JOptionPane.showMessageDialog(d, "Debes añadir al menos una localidad");
                return;
            }
            ev.localidadBasica = ev.getLocalidades().get(0);
            logica.pendientes.add(ev);
            logica.guardarEvento("pendientes.json", ev);
            logica.guardarTodo();
            JOptionPane.showMessageDialog(d, "Solicitud de evento creada correctamente");
            d.dispose();
        });

        d.setVisible(true);
    }

    public class AdminMenu extends JPanel {

        private Aplicacion app;
        private int index;
        private ArrayList<Evento> listaActual;

        private JLabel nombre, organizador, fecha, horaIni, horaFin, capacidad, precio, tasa, descripcion;

        public AdminMenu(Aplicacion app) {
            this.app = app;
            setLayout(new BorderLayout());

            JPanel top = new JPanel(new GridLayout(1, 3));
            JButton pendientesBtn = new JButton("Pendientes");
            JButton proximosBtn = new JButton("Próximos");
            JButton pasadosBtn = new JButton("Pasados");
            top.add(pendientesBtn);
            top.add(proximosBtn);
            top.add(pasadosBtn);
            add(top, BorderLayout.NORTH);

            JPanel content = new JPanel();
            content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

            nombre = new JLabel();
            organizador = new JLabel();
            fecha = new JLabel();
            horaIni = new JLabel();
            horaFin = new JLabel();
            capacidad = new JLabel();
            precio = new JLabel();
            tasa = new JLabel();
            descripcion = new JLabel();

            content.add(nombre);
            content.add(organizador);
            content.add(fecha);
            content.add(horaIni);
            content.add(horaFin);
            content.add(capacidad);
            content.add(precio);
            content.add(tasa);
            content.add(descripcion);

            add(content, BorderLayout.CENTER);

            JPanel bottom = new JPanel(new GridLayout(1, 4));
            JButton anteriorBtn = new JButton("Anterior");
            JButton siguienteBtn = new JButton("Siguiente");
            JButton aprobarBtn = new JButton("Aprobar");
            JButton cancelarBtn = new JButton("Cancelar");

            bottom.add(anteriorBtn);
            bottom.add(siguienteBtn);
            bottom.add(aprobarBtn);
            bottom.add(cancelarBtn);
            add(bottom, BorderLayout.SOUTH);

            JButton salirBtn = new JButton("Salir");
            add(salirBtn, BorderLayout.WEST);

            pendientesBtn.addActionListener(e -> cargarLista(app.pendientes, aprobarBtn));
            proximosBtn.addActionListener(e -> cargarLista(app.eventosProx, aprobarBtn));
            pasadosBtn.addActionListener(e -> cargarLista(app.eventosPas, aprobarBtn));

            anteriorBtn.addActionListener(e -> mostrar(index - 1, aprobarBtn));
            siguienteBtn.addActionListener(e -> mostrar(index + 1, aprobarBtn));

            aprobarBtn.addActionListener(e -> aprobar(aprobarBtn));
            cancelarBtn.addActionListener(e -> cancelar());

            salirBtn.addActionListener(e ->
                ((CardLayout) panelContenedor.getLayout()).show(panelContenedor, LOGIN)
            );
        }

        private void cargarLista(ArrayList<Evento> lista, JButton aprobarBtn) {
            listaActual = lista;
            index = 0;
            mostrar(index, aprobarBtn);
        }

        private void mostrar(int i, JButton aprobarBtn) {
            if (listaActual == null || listaActual.isEmpty()) return;
            if (i < 0 || i >= listaActual.size()) return;
            index = i;
            Evento ev = listaActual.get(index);

            nombre.setText("Nombre: " + ev.getNombre());
            organizador.setText("Organizador: " + ev.getOrganizador().getLog());
            fecha.setText("Fecha: " + ev.getFecha());
            horaIni.setText("Hora inicio: " + ev.getHoraIni());
            horaFin.setText("Hora fin: " + ev.getHoraFin());
            capacidad.setText("Capacidad: " + ev.getCapMax());
            precio.setText("Precio base: " + ev.getPrecioBase());
            tasa.setText("Tasa: " + ev.tasa);

            aprobarBtn.setVisible(listaActual == app.pendientes);
        }

        private void aprobar(JButton btn) {
            if (listaActual != app.pendientes) return;
            if (listaActual.isEmpty()) return;

            Evento ev = listaActual.get(index);

            app.eventosProx.add(ev);
            app.pendientes.remove(ev);

            cargarLista(app.pendientes, btn);
        }

        private void cancelar() {
            if (listaActual == null || listaActual.isEmpty()) return;

            Evento ev = listaActual.get(index);

            listaActual.remove(ev);
            app.cancelados.add(ev);

            cargarLista(listaActual, new JButton());
        }
    }

    private void refreshClientePanels() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            app a = new app();
            a.setVisible(true);
        });
    }
}
