package main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import juegos.control.ControllerJuegos;
import juegos.model.Juego;
import mesas.control.ControllerMesas;
import mesas.model.Mesa;

public class SeleccionarMesaPanel extends JPanel {

	private DefaultTableModel _tablaModel;
	private JTable _tabla;
	private List<Mesa> listaMesas;
	private String[] _headers = { "ID","juego" };
	private ControllerMesas _ctrlMesas;
	private ControllerJuegos _ctrlJuegos;
	JComboBox<String> JuegoComboBox;
	DefaultComboBoxModel<String> comboBoxModel;
	
	private String juego;

	SeleccionarMesaPanel(ControllerMesas m, ControllerJuegos j) {
		_ctrlMesas = m;
		_ctrlJuegos = j;
		initGUI();
	}

	public void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.BLACK);
/////////////////////////////////////////////////////////////////TOOLBAR//////////////////////////////////////////////////////
		JToolBar tb = crearToolBar();
		JButton botonAtras = new JButton();

		botonAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(SeleccionarMesaPanel.this);
				mainWindow.showControlPanelUsuarios();
			}

		});

		botonAtras.setIcon(new ImageIcon("icons/atras.png"));
		botonAtras.setBackground(new Color(0, 9, 60));
		botonAtras.setBorder(null);
		botonAtras.setBorderPainted(false);
		botonAtras.setOpaque(false);

		tb.add(botonAtras, BorderLayout.WEST);
		this.add(tb, BorderLayout.PAGE_START);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
		
		
		

/////////////////////////////////////////////////////////////////BOTONES DE BUSQUEDA/////////////////////////////////////////
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLACK);

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		mainPanel.add(new JLabel(new ImageIcon("icons/consulta.png")),BorderLayout.CENTER);
		mainPanel.add(Box.createRigidArea(new Dimension(100,100)));
		JPanel campos = new JPanel();
		campos.setLayout(new BoxLayout(campos, BoxLayout.LINE_AXIS));
		campos.setBackground(Color.BLACK);
		

		mainPanel.add(campos);
		mainPanel.add(Box.createRigidArea(new Dimension(100,100)));
		//mainPanel.add(Box.createRigidArea(getPreferredSize()));
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
		
		JPanel juegoPanel = new JPanel();
		juegoPanel.setBackground(Color.WHITE);
		
		Vector<String> juegos = getNombreJuegos();
		comboBoxModel = new DefaultComboBoxModel<>(juegos);
		JuegoComboBox = new JComboBox<String>(comboBoxModel);
		juegoPanel.add(new JLabel("Juego:"));
		juegoPanel.add(JuegoComboBox);
		
		JButton juegoSeleccionado = new JButton("Seleccionar Juego");
		juegoPanel.add(juegoSeleccionado);
		
		juegoSeleccionado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarTabla((String) JuegoComboBox.getSelectedItem());
			}

		});
		
		mainPanel.add(juegoPanel);
		
		
		_tablaModel = new DefaultTableModel();
		_tablaModel.setColumnIdentifiers(_headers);
		_tablaModel.setColumnCount(_headers.length);
		_tabla = new JTable(_tablaModel);
		
		JScrollPane scroll = new JScrollPane(_tabla);
		
		mainPanel.add(scroll);
		this.add(mainPanel);
		
		JPanel botones = new JPanel();
		
		botones.setBackground(Color.BLACK);
	
		
		JButton botonBus = crearBoton("entrar");

		botonBus.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = _tabla.getSelectedRow();
				if (selectedRow == -1)
					ViewUtils.showErrorMsg("Ninguna mesa seleccionada");
				else {
					MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(SeleccionarMesaPanel.this);
					String idMesa = (String) _tabla.getValueAt(selectedRow, 0);
					mainWindow.showApostarPanel(idMesa);
					_ctrlMesas.anadirJugador(Credentials.getDni(), idMesa);
				}
			}

		});

		botones.add(botonBus);
		mainPanel.add(botones);
	}

	private JButton crearBoton(String texto) {
		JButton b = new JButton(texto);
		b.setBackground(new Color(59, 89, 182));
		b.setForeground(Color.WHITE);
		b.setFocusPainted(false);
		b.setFont(new Font("Tahoma", Font.BOLD, 60));
		return b;
	}

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

	private void borrarTabla() {
		_tablaModel.getDataVector().clear();
	}
	
	public void actualizarJuegos() {
		Vector<String> juegos = getNombreJuegos();
		comboBoxModel.removeAllElements();
		for (String j : juegos)
			comboBoxModel.addElement(j);
	}
	
	private void actualizarTabla(String juego) {
		listaMesas = _ctrlMesas.busquedaMesa(juego);
		borrarTabla();
		for (Mesa m: listaMesas) {
			Object[] row = new Object[_headers.length];
			row[0] = m.getId();
			row[1] = m.getJuego();
			
			_tablaModel.addRow(row);
		}
	}
	
	private Vector<String> getNombreJuegos() {
		Vector<String> ret = new Vector<String>();
		ArrayList<Juego> lista = _ctrlJuegos.getJuegos();
		for (int i = 0; i < lista.size(); i++) {
			ret.add(lista.get(i).getId());
		}
		return ret;
	}
	
	
}




