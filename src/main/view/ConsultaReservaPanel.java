package main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

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
import javax.swing.table.DefaultTableModel;

import parking.control.ControladorParking;
import parking.model.Reserva;

/**
 * Clase encargada de la interfaz que realiza la función de consultar reservas
 * @author Alejandro Parra
 *
 */
public class ConsultaReservaPanel extends JPanel{
	JTextField matricula;
	ControladorParking control;
	private DefaultTableModel dataTableModel;
	
	/**
	 * Constructor de la clase, inicializa la variable c (de tipo ControladorParking) y llama a initGUI()
	 * @author Alejandro Parra
	 * @param c
	 */
	public ConsultaReservaPanel(ControladorParking c) {
		control = c;
		initGUI();
	}
	
	/**
	 * Añade los botones y paneles necesarios para la interfaz principal de esta clase, así como la tabla que mostrará las reservas a consultar
	 * @author Alejandro Parra
	 */
	private void initGUI() {
		matricula = new JTextField();
		
		this.setLayout(new BorderLayout());
		JToolBar toolbar =  crearToolBar();
		JButton botonAtras = new JButton();
		
		botonAtras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(ConsultaReservaPanel.this);
				mainWindow.showParkingPanel();
			}
			
		});
		
		botonAtras.setIcon(new ImageIcon("icons/atras.png"));
		botonAtras.setBackground(new Color(0, 9, 60));
		botonAtras.setBorder(null);
		botonAtras.setBorderPainted(false);
		botonAtras.setOpaque(false);
		
		this.setBackground(Color.BLACK);
		toolbar.add(botonAtras, BorderLayout.WEST);
		this.add(toolbar, BorderLayout.NORTH);
		JPanel central = new JPanel();
		central.setLayout(new BorderLayout());
		central.setBackground(Color.BLACK);
		JPanel central2 = new JPanel();
		central2.setBackground(Color.BLACK);
		central2.setLayout(new BorderLayout());
		
		JPanel panelFotos = new JPanel();
		panelFotos.setLayout(new BoxLayout(panelFotos, BoxLayout.X_AXIS));
		//central2.add(new JLabel(new ImageIcon("icons/cocheP2.png")), BorderLayout.CENTER);
		central2.add(new JLabel(new ImageIcon("icons/consulta.png")), BorderLayout.PAGE_START);
		
		JPanel central3 = new JPanel();

		central.setBackground(Color.BLACK);

		central3.setLayout(new BoxLayout(central3, BoxLayout.Y_AXIS));

		central3.setBackground(Color.BLACK);

		String[] columnHeaders = {"Matricula", "Marca", "Color", "Fecha", "Hora", "DNI"};
		
		dataTableModel = new DefaultTableModel(columnHeaders,0);
		dataTableModel.setColumnCount(6);
		JTable dataTable = new JTable(dataTableModel);
		
		JScrollPane scrollPane = new JScrollPane(dataTable);
		central2.add(scrollPane, BorderLayout.CENTER);
		

		JLabel labelMatricula = new JLabel("MATRICULA");

		labelMatricula.setForeground(Color.WHITE);

		labelMatricula.setFont(new Font("Arial", Font.BOLD, 40));

		central3.add(labelMatricula);

		matricula.setFont(new Font("Arial", Font.BOLD, 40));

		central3.add(matricula);

		central2.add(central3, BorderLayout.PAGE_END);
		central.add(Box.createRigidArea(new Dimension(500, 200)), BorderLayout.EAST);
		central.add(Box.createRigidArea(new Dimension(500, 200)), BorderLayout.WEST);
		central.add(Box.createRigidArea(new Dimension(500, 25)), BorderLayout.NORTH);
		JPanel panelAuxB = new JPanel();
		panelAuxB.setLayout(new BorderLayout());
		panelAuxB.setBackground(Color.BLACK);
		panelAuxB.add(Box.createRigidArea(new Dimension(0, 60)), BorderLayout.PAGE_START);
		panelAuxB.add(crearBoton("Consultar Reservas"), BorderLayout.PAGE_END);
		central.add(panelAuxB, BorderLayout.PAGE_END);
		central.add(central2, BorderLayout.CENTER);
		this.add(central, BorderLayout.CENTER);
	}

	/**
	 * Crea la toolbar siguiendo el mismo estilo que en los demás paneles 
	 * @author Alejandro Parra
	 * @return toolbar
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
	 * Crea el botón que llama a la función del controlador para mostrar la reserva en la tabla de cuya matrícula se introduce
	 * @author Alejandro Parra
	 * @param texto
	 * @return b
	 */
	private JButton crearBoton(String texto) {
		JButton b = new JButton(texto);
		b.setBackground(new Color(59, 89, 182));
		b.setForeground(Color.WHITE);
		b.setFocusPainted(false);
		b.setFont(new Font("Tahoma", Font.BOLD, 60));
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!matricula.getText().isEmpty()||(matricula.getText().length() != 7) ) {
					Reserva r = control.consultaReserva(matricula.getText());
					if (r != null) {
						actualizarClientes(r);
					}
					else {
						 ViewUtils.showErrorMsg("No existe ninguna reserva con los datos especificados");
					}
				}else ViewUtils.showErrorMsg("Por favor, rellene los datos correctamente");
				
			}
		});
			
		return b;
	}
	/**
	 * Añade una fila con los datos de la reserva a la tabla creada
	 * @author Alejandro Parra
	 * @param r
	 */
	private void actualizarClientes(Reserva r) {
			String dni = r.getDni();
		    String matricula = r.getCoche().getMatricula();
		    String marca = r.getCoche().getMarca();
		    String color = r.getCoche().getColor();
		    LocalDate fecha = r.getFecha();
		    LocalTime hora = r.getHora();
		    if (matricula != null)
		    	dataTableModel.addRow(new Object[]{matricula, marca, color,fecha, hora, dni});
		    else ViewUtils.showErrorMsg("Matricula no existente");
		}
	}
