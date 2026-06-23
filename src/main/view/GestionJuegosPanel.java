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

import juegos.control.ControllerJuegos;
import juegos.model.Juego;
import usuario.control.ControllerUsu;
import usuario.model.Usuario;

/**
 * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
 *
 * Interfaz gráfica para acceder a las posibles opciones de gestión de 
 * un juego en el casino.
 * 
 */
public class GestionJuegosPanel extends JPanel {

	private DefaultTableModel _tablaModel;
	private JTable _tabla;
	private ArrayList<Juego> listaJuegos;
	private String[] _headers = { "ID", "PROBABILIDAD" };
	private ControllerJuegos _ctrl;
	
	private String id;
	private int probabilidad;

	GestionJuegosPanel(ControllerJuegos c) {
		_ctrl = c;
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
				MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(GestionJuegosPanel.this);
				mainWindow.showJuegosPanelTrabajador();
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
		
		//mainPanel.add(new JLabel(new ImageIcon("icons/consulta.png")),BorderLayout.CENTER);
		mainPanel.add(Box.createRigidArea(new Dimension(100,100)));
		JPanel campos = new JPanel();
		campos.setLayout(new BoxLayout(campos, BoxLayout.LINE_AXIS));
		campos.setBackground(Color.BLACK);

		_tablaModel = new DefaultTableModel();
		_tablaModel.setColumnIdentifiers(_headers);
		_tablaModel.setColumnCount(_headers.length);
	
		listaJuegos = _ctrl.getJuegos();
		borrarTabla();
		for (Juego j : listaJuegos) {
			Object[] row = new Object[_headers.length];
			row[0] = j.getId();
			row[1] = j.getProb();
			_tablaModel.addRow(row);
		}
		_tablaModel.fireTableStructureChanged();

		mainPanel.add(campos);
		mainPanel.add(Box.createRigidArea(new Dimension(100,100)));
		//mainPanel.add(Box.createRigidArea(getPreferredSize()));
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
		
		_tabla = new JTable(_tablaModel);
		
		JScrollPane scroll = new JScrollPane(_tabla);
		
		mainPanel.add(scroll);
		this.add(mainPanel);
		
		JPanel botones = new JPanel();
		
		botones.setBackground(Color.BLACK);

		JButton botonMod = crearBoton("Cambiar Probabilidad");

		botonMod.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < _tablaModel.getRowCount(); i++) {
					_ctrl.setProbabilidad(_tablaModel.getValueAt(i, 0).toString(), Integer.parseInt(_tablaModel.getValueAt(i, 1).toString()));
				}
				actualizarTabla();
			}

		});

		
		JButton botonRemove = crearBoton("Eliminar");
		
		botonRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_ctrl.darBajaJuego(_tablaModel.getValueAt(_tabla.getSelectedRow(), 0).toString());
				actualizarTabla();
			}
			
		});

		botones.add(botonMod);
		botones.add(botonRemove);
		
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
	
	public void actualizarTabla() {
		listaJuegos = _ctrl.getJuegos();
		borrarTabla();
		for (Juego j : listaJuegos) {
			Object[] row = new Object[_headers.length];
			row[0] = j.getId();
			row[1] = j.getProb();
			_tablaModel.addRow(row);
		}
	}
	
}
