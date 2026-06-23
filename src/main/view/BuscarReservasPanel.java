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

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import parking.control.ControladorParking;
import parking.model.Reserva;

/**
 * Muestra la interfaz para la función de búsqueda de reservas e implementa su lógica
 * @author Marta Hernández
 *
 */
public class BuscarReservasPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField dniTextField;
	private JTextField marcaTextField;
	private JTextField colorTextField;
	private ControladorParking control;
	
	private DefaultTableModel dataTableModel;
	
	JComboBox<String> yearComboBox;
	JComboBox<String> monthComboBox;
	JComboBox<String> dayComboBox;
	JComboBox<String> hourComboBox;
	JComboBox<String> minuteComboBox;
	JComboBox<String> secondComboBox;
	JButton okButton;
	private  JTable dataTable;

	
	/**
	 * Constructor de la clase, inicializa la variable de tipo ControladorParking y llama a initGUI()
	 * @author Marta Hernández
	 */
	public BuscarReservasPanel() {
		control = new ControladorParking();
		initGUI();
	}
	
	/**
	 * Inicializa la parte de la interfaz gráfica y las tablas.
	 * @author Marta Hernández
	 */
	private void initGUI() {
		
		 

		this.setLayout(new BorderLayout());
		JToolBar toolbar =  crearToolBar();
		JButton botonAtras = new JButton();
		
		botonAtras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(BuscarReservasPanel.this);
				mainWindow.showParkingPanel();
			}
			
		});
		
		botonAtras.setIcon(new ImageIcon("icons/atras.png"));
		botonAtras.setBackground(new Color(0, 9, 60));
		botonAtras.setBorder(null);
		botonAtras.setBorderPainted(false);
		botonAtras.setOpaque(false);
		toolbar.add(botonAtras, BorderLayout.LINE_START);
		this.add(toolbar, BorderLayout.PAGE_START);
		this.setBackground(Color.BLACK);
		JPanel central = new JPanel();
		central.setLayout(new BorderLayout());
		central.setBackground(Color.BLACK);
		JPanel central2 = new JPanel();
		central2.setBackground(Color.BLACK);
		central2.setLayout(new BorderLayout());
		

		JPanel central3 = new JPanel();

		central.setBackground(Color.BLACK);

		central3.setLayout(new BoxLayout(central3, BoxLayout.Y_AXIS));

		central3.setBackground(Color.BLACK);

		//TABLITAS
		String[] columnHeaders = {"DNI", "matricula", "marca", "color"};
		
		dataTableModel = new DefaultTableModel(columnHeaders,0);
		 dataTableModel.setColumnCount(4);
		dataTable = new JTable(dataTableModel);
		
		 JScrollPane scrollPane = new JScrollPane(dataTable);
		 central3.add(scrollPane);
		
        // Agregar JComboBoxes y botón OK
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setBackground(Color.WHITE);

        yearComboBox = new JComboBox<>(new String[]{"2022", "2023", "2024"});
        comboBoxPanel.add(new JLabel("Year:"));
        comboBoxPanel.add(yearComboBox);
        

        monthComboBox = new JComboBox<>(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "010", "011", "012"});
        comboBoxPanel.add(new JLabel("Month:"));
        comboBoxPanel.add(monthComboBox);

        dayComboBox = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
        comboBoxPanel.add(new JLabel("Day:"));
        comboBoxPanel.add(dayComboBox);

        hourComboBox = new JComboBox<>(new String[]{"null","0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"});
        comboBoxPanel.add(new JLabel("Hour:"));
        comboBoxPanel.add(hourComboBox);

        minuteComboBox = new JComboBox<>(new String[]{"null","00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", 
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", 
                "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", 
                "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"});
        comboBoxPanel.add(new JLabel("Minute:"));
        comboBoxPanel.add(minuteComboBox);

        secondComboBox = new JComboBox<>(new String[]{"null","00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", 
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", 
                "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", 
                "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"});
        comboBoxPanel.add(new JLabel("Second:"));
        comboBoxPanel.add(secondComboBox);

        central3.add(comboBoxPanel);

        

		
        	//añadir los otros 
        
        dniTextField = new JTextField("null");
        dniTextField.setPreferredSize(new Dimension(200, 30));
        marcaTextField = new JTextField("null");
        marcaTextField.setPreferredSize(new Dimension(200, 30));
        colorTextField = new JTextField("null");
        colorTextField.setPreferredSize(new Dimension(200, 30));
        
        JPanel textFieldsPanel = new JPanel();
        textFieldsPanel.setBackground(Color.WHITE);
        textFieldsPanel.add(new JLabel("DNI:"));
        textFieldsPanel.add(dniTextField);
        textFieldsPanel.add(new JLabel("Marca:"));
        textFieldsPanel.add(marcaTextField);
        textFieldsPanel.add(new JLabel("Color:"));
        textFieldsPanel.add(colorTextField);
        central3.add(textFieldsPanel);
        

		central2.add(central3, BorderLayout.PAGE_END);
		central.add(Box.createRigidArea(new Dimension(500, 200)), BorderLayout.EAST);
		central.add(Box.createRigidArea(new Dimension(500, 200)), BorderLayout.WEST);
		JPanel panelAuxB = new JPanel();
		panelAuxB.setLayout(new BorderLayout());
		panelAuxB.setBackground(Color.BLACK);
		panelAuxB.add(Box.createRigidArea(new Dimension(0, 60)), BorderLayout.PAGE_START);
		panelAuxB.add(crearBoton("Buscar Reservas"), BorderLayout.PAGE_END);
		central.add(panelAuxB, BorderLayout.PAGE_END);
		central.add(central2, BorderLayout.CENTER);
		central.add(new JLabel(new ImageIcon("icons/consulta.png")), BorderLayout.PAGE_START);
		this.add(central, BorderLayout.CENTER);
	}

	/**
	 * Crea la toolbar que tiene por defecto el programa
	 * @author Marta Hernández
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
	 * Funcion encargada de la obtención de los datos seleccionados dentro de los comboboxes, así como de los textfields. 
	 * Además llama a la funcion del controlador ('busquedaReservas(date,dni,time,marca,color)') y añade las reservas a la lista de reservas que coincidan con los parámetros.
	 * Contiene una función encargada de actualizar los clientes
	 * @author Marta Hernández
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
					 String yearString = (String) yearComboBox.getSelectedItem();
				     String monthString = (String) monthComboBox.getSelectedItem();
				     String dayString = (String) dayComboBox.getSelectedItem();
				     String hourString = (String) hourComboBox.getSelectedItem();
				     String minuteString = (String) minuteComboBox.getSelectedItem();
				     String secondString = (String) secondComboBox.getSelectedItem();

				     int hour ;
				     int minute;
				     int second;
				     if(hourString == "null") {
				    	  hour = -1;
				     }
				     else {
				    	  hour = Integer.parseInt(hourString);
				     }
				     if(minuteString == "null") {
				    	 minute = -1;
				     }
				     else {
				    	 minute = Integer.parseInt(hourString);
				     }
				     
				     if(secondString == "null") {
				    	 second =-1;
				     }
				     else {
				    	 second = Integer.parseInt(hourString);
				     }
				     
				     
				     
				    int year = Integer.parseInt(yearString);
				    int month = Integer.parseInt(monthString);
				    int day = Integer.parseInt(dayString);
				    
				   
				    String dni = dniTextField.getText();
				    String marca = marcaTextField.getText();
				    String color = colorTextField.getText();
				    
				    
				    LocalDate date = LocalDate.of(year, month, day);
				    LocalTime time = null;
				    if(hour!= -1  && minute != -1 && second != -1) {
					     time = LocalTime.of(hour, minute, second);

				    }
				    if (dni == null && marca == null && color == null && time == null && date == null) {
				    	ViewUtils.showErrorMsg("Selecciones un campo de búsqueda");
				    }
		            ArrayList<Reserva> lista = control.busquedaReservas(date,dni,time,marca,color);
		            if (lista == null) {
						ViewUtils.showErrorMsg("No se encuentran reservas con los datos especificados");
		            }
		            actualizarClientes(lista);
					
				}

			private void actualizarClientes(ArrayList<Reserva> lista ) {
				dataTableModel.setRowCount(0);
				for(Reserva reserva : lista) {
					 String dni = reserva.getDni();
				     String matricula = reserva.getCoche().getMatricula();
				     String marca = reserva.getCoche().getMarca();
				     String color = reserva.getCoche().getColor();

				     dataTableModel.addRow(new Object[]{dni, matricula, marca,color});
				}
			}
				
				
				
			
		});
			
		return b;
	}
}
