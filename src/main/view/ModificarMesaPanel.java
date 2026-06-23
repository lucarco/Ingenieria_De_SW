package main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import mesas.control.ControllerMesas;
import mesas.model.Mesa;
import usuario.control.ControllerUsu;
import usuario.model.Usuario;

/**
 * Panel para modificar mesas en el casino.
 * Permite buscar mesas por juego y modificar o eliminar las mesas encontradas.
 * Autor: Alejandro Ramos Yañez
 */
public class ModificarMesaPanel extends JPanel {

    private DefaultTableModel _tablaModel;
    private JTable _tabla;
    private List<Mesa> listaMesas;
    private String[] _headers = { "ID", "juego" };
    private ControllerMesas _ctrl;

    private String id;
    private String juego;

    /**
     * Constructor de la clase ModificarMesaPanel.
     * @param c El controlador de mesas que se utilizará para realizar las operaciones de modificación y eliminación.
     */
    ModificarMesaPanel(ControllerMesas c) {
        _ctrl = c;
        initGUI();
    }

    /**
     * Inicializa la interfaz gráfica de usuario para el panel de modificación de mesa.
     */
    public void initGUI() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        // Creación de la barra de herramientas superior
        JToolBar tb = crearToolBar();
        JButton botonAtras = new JButton();

        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(ModificarMesaPanel.this);
                mainWindow.showMesasTrabajador();
            }
        });

        botonAtras.setIcon(new ImageIcon("icons/atras.png"));
        botonAtras.setBackground(new Color(0, 9, 60));
        botonAtras.setBorder(null);
        botonAtras.setBorderPainted(false);
        botonAtras.setOpaque(false);

        tb.add(botonAtras, BorderLayout.WEST);
        this.add(tb, BorderLayout.PAGE_START);

        // Creación del panel principal que contiene los elementos de la interfaz
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Panel de búsqueda de mesas
        JPanel campos = new JPanel();
        campos.setLayout(new BoxLayout(campos, BoxLayout.LINE_AXIS));
        campos.setBackground(Color.BLACK);

        JLabel ljuego = new JLabel("juego: ");
        ljuego.setFont(new Font("Arial", Font.BOLD, 30));
        ljuego.setForeground(Color.WHITE);
        campos.add(ljuego);

        JTextField tjuego = new JTextField();
        tjuego.setSize(new Dimension(200, 200));
        tjuego.setFont(new Font("Arial", Font.BOLD, 30));
        campos.add(tjuego);

        JButton botonBuscar = crearBoton("Buscar");
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                juego = tjuego.getText();

                if (juego.isEmpty()) {
                    juego = null;
                }

                listaMesas = _ctrl.busquedaMesa(juego);
                borrarTabla();
                for (Mesa m : listaMesas) {
                    Object[] row = new Object[_headers.length];
                    row[0] = m.getId();
                    row[1] = m.getJuego();
                    _tablaModel.addRow(row);
                }
                _tablaModel.fireTableStructureChanged();
            }
        });

        campos.add(botonBuscar);
        mainPanel.add(campos);

        // Tabla que muestra las mesas encontradas
        _tablaModel = new DefaultTableModel();
        _tablaModel.setColumnIdentifiers(_headers);
        _tablaModel.setColumnCount(_headers.length);
        _tabla = new JTable(_tablaModel);
        JScrollPane scroll = new JScrollPane(_tabla);
        mainPanel.add(scroll);

        // Botones de modificación y eliminación de mesas
        JPanel botones = new JPanel();
        botones.setBackground(Color.BLACK);

        JButton botonMod = crearBoton("Modificar");
        botonMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < _tablaModel.getRowCount(); i++) {
                    _ctrl.modificarMesa(_tablaModel.getValueAt(i, 0).toString(), _tablaModel.getValueAt(i, 1).toString(), null);
                }
                actualizarTabla();
            }
        });

        JButton botonRemove = crearBoton("Eliminar");
        botonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrl.bajaMesa(_tablaModel.getValueAt(_tabla.getSelectedRow(), 0).toString());
                actualizarTabla();
            }
        });

        botones.add(botonMod);
        botones.add(botonRemove);
        mainPanel.add(botones);

        this.add(mainPanel);
    }

    /**
     * Crea y configura un botón con el texto especificado.
     * @param texto El texto a mostrar en el botón.
     * @return El botón configurado.
     */
    private JButton crearBoton(String texto) {
        JButton b = new JButton(texto);
        b.setBackground(new Color(59, 89, 182));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Tahoma", Font.BOLD, 60));
        return b;
    }

    /**
     * Crea y configura la barra de herramientas superior del panel.
     * @return La barra de herramientas configurada.
     */
    private JToolBar crearToolBar() {
        JToolBar toolbar = new JToolBar();
        toolbar.setLayout(new BorderLayout());

        JLabel titulo = new JLabel();

        titulo.setText("Casino ");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 60));
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        toolbar.setBackground(new Color(0, 9, 128));
        toolbar.setFloatable(false);
        toolbar.add(titulo, BorderLayout.EAST);
        toolbar.setBorderPainted(false);
        return toolbar;
    }

    /**
     * Borra los datos de la tabla.
     */
    private void borrarTabla() {
        _tablaModel.getDataVector().clear();
    }

    /**
     * Actualiza los datos de la tabla con las mesas encontradas en la búsqueda.
     */
    private void actualizarTabla() {
        listaMesas = _ctrl.busquedaMesa(juego);
        borrarTabla();
        for (Mesa m : listaMesas) {
            Object[] row = new Object[_headers.length];
            row[0] = m.getId();
            row[1] = m.getJuego();
            _tablaModel.addRow(row);
        }
    }
}
