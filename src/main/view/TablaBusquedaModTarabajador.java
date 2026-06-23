package main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import usuario.control.ControllerUsu;
import usuario.model.Usuario;

/**
 * @author Alejandro Remiro, Adrian Valverde
 */
public class TablaBusquedaModTarabajador extends JPanel {

	private DefaultTableModel _tablaModel;
	private JTable _tabla;
	private List<Usuario> listaUsuarios;
	private String[] _headers = { "DNI", "NOMBRE", "APELLIDOS", "EDAD", "FICHAS", "ROL" };
	private ControllerUsu _ctrl;
	
	private String nombre;
	private String apellidos;
	private int edad = -1;
	private int fichas = -1;

	TablaBusquedaModTarabajador(ControllerUsu c) {
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
				MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(TablaBusquedaModTarabajador.this);
				mainWindow.showControlPanel();
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

		JLabel lNombre = new JLabel("NOMBRE: ");
		lNombre.setFont(new Font("Arial", Font.BOLD, 30));
		lNombre.setForeground(Color.WHITE);
		campos.add(lNombre);

		JTextField tNombre = new JTextField();
		tNombre.setSize(new Dimension(200, 200));
		tNombre.setFont(new Font("Arial", Font.BOLD, 30));
		campos.add(tNombre);

		JLabel lApellidos = new JLabel("APELLIDOS: ");
		lApellidos.setFont(new Font("Arial", Font.BOLD, 30));
		lApellidos.setForeground(Color.WHITE);
		campos.add(lApellidos);

		JTextField tApellidos = new JTextField();
		tApellidos.setSize(new Dimension(200, 200));
		tApellidos.setFont(new Font("Arial", Font.BOLD, 30));
		campos.add(tApellidos);

		JLabel lEdad = new JLabel("EDAD: ");
		lEdad.setFont(new Font("Arial", Font.BOLD, 30));
		lEdad.setForeground(Color.WHITE);
		campos.add(lEdad);

		JTextField tEdad = new JTextField();
		tEdad.setSize(new Dimension(200, 200));
		tEdad.setFont(new Font("Arial", Font.BOLD, 30));
		campos.add(tEdad);

		JLabel lFichas = new JLabel("FICHAS: ");
		lFichas.setFont(new Font("Arial", Font.BOLD, 30));
		lFichas.setForeground(Color.WHITE);
		campos.add(lFichas);

		JTextField tFichas = new JTextField();
		tFichas.setSize(new Dimension(200, 200));
		tFichas.setFont(new Font("Arial", Font.BOLD, 30));
		campos.add(tFichas);

		JButton botonBuscar = crearBoton("Buscar");

		botonBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				edad = -1;
				fichas = -1;
				nombre = tNombre.getText();
				apellidos = tApellidos.getText();
				String aux;
				if (!tEdad.getText().isEmpty()) {
					try {
						edad = Integer.parseInt(tEdad.getText());
					} catch (Exception ex) {
						ViewUtils.showErrorMsg("Edad no válida. (Introduzca un numero natural mayor o igual que '18')");
						return;
					}
				}
				if (!tFichas.getText().isEmpty()) {
					try {
						fichas = Integer.parseInt(tFichas.getText());
					} catch (Exception ex) {
						ViewUtils.showErrorMsg("Número de fichas no valido. (Introduzca un numero natural mayor o igual que '0')");
						return;
					}
				}
				if (nombre.isEmpty()) {
					nombre = null;
				}
				if (apellidos.isEmpty()) {
					apellidos = null;
				}
				listaUsuarios = _ctrl.busquedaUsuario(nombre, apellidos, edad, fichas);
				borrarTabla();
				if(!listaUsuarios.isEmpty()) {
					for (Usuario u : listaUsuarios) {
						Object[] row = new Object[_headers.length];
						row[0] = u.getDni();
						row[1] = u.getNombre();
						row[2] = u.getApellidos();
						row[3] = u.getEdad();
						row[4] = u.getFichas();
						row[5] = u.getRol();
						_tablaModel.addRow(row);
					}
				}else {
					ViewUtils.showErrorMsg("No hay usuarios con esas características... ");
				}
				_tablaModel.fireTableStructureChanged();
			}

		});

		campos.add(botonBuscar);

		mainPanel.add(campos);
		mainPanel.add(Box.createRigidArea(new Dimension(100,100)));
		//mainPanel.add(Box.createRigidArea(getPreferredSize()));
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
		_tablaModel = new DefaultTableModel();
		_tablaModel.setColumnIdentifiers(_headers);
		_tablaModel.setColumnCount(_headers.length);
		_tabla = new JTable(_tablaModel);
		
		JScrollPane scroll = new JScrollPane(_tabla);
		
		mainPanel.add(scroll);
		this.add(mainPanel);
		
		JPanel botones = new JPanel();
		
		botones.setBackground(Color.BLACK);

		JButton botonMod = crearBoton("Modificar");

		botonMod.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < _tablaModel.getRowCount(); i++) {
					_ctrl.modificarUsuario(_tablaModel.getValueAt(i, 0).toString(), _tablaModel.getValueAt(i, 1).toString(),
							_tablaModel.getValueAt(i, 2).toString(), Integer.parseInt(_tablaModel.getValueAt(i, 3).toString()), Integer.parseInt(_tablaModel.getValueAt(i, 4).toString()),
							Integer.parseInt(_tablaModel.getValueAt(i, 5).toString()));
				}
				actualizarTabla();
			}

		});

		
		JButton botonRemove = crearBoton("Eliminar");
		
		botonRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_ctrl.bajaUsuario(_tablaModel.getValueAt(_tabla.getSelectedRow(), 0).toString());
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
	
	private void actualizarTabla() {
		listaUsuarios = _ctrl.busquedaUsuario(nombre, apellidos, edad, fichas);
		borrarTabla();
		for (Usuario u : listaUsuarios) {
			Object[] row = new Object[_headers.length];
			row[0] = u.getDni();
			row[1] = u.getNombre();
			row[2] = u.getApellidos();
			row[3] = u.getEdad();
			row[4] = u.getFichas();
			row[5] = u.getRol();
			_tablaModel.addRow(row);
		}
	}
	
}
