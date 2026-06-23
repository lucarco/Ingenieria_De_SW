package main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import juegos.control.ControllerJuegos;

/**
 * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
 *
 * Interfaz gráfica para dar de baja (eliminar) un juego en el casino.
 * 
 */
public class BajaJuegoPanel extends JPanel{
	JTextField id;
	ControllerJuegos control;
	
	public BajaJuegoPanel(ControllerJuegos c) {
		control = c;
		initGUI();
	}
	
	private void initGUI() {
		id = new JTextField();
		
		this.setLayout(new BorderLayout());
		JToolBar toolbar =  crearToolBar();
		JButton botonAtras = new JButton();
		
		botonAtras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(BajaJuegoPanel.this);
				mainWindow.showJuegosPanelTrabajador();
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
		//central2.add(new JLabel(new ImageIcon("icons/cocheP.png")), BorderLayout.CENTER);

		JPanel central3 = new JPanel();

		central.setBackground(Color.BLACK);

		central3.setLayout(new BoxLayout(central3, BoxLayout.Y_AXIS));

		central3.setBackground(Color.BLACK);

		JLabel labelID = new JLabel("ID JUEGO");

		labelID.setForeground(Color.WHITE);

		labelID.setFont(new Font("Arial", Font.BOLD, 40));

		central3.add(labelID);

		id.setFont(new Font("Arial", Font.BOLD, 40));

		central3.add(id);

		central2.add(central3, BorderLayout.PAGE_END);
		central.add(Box.createRigidArea(new Dimension(500, 200)), BorderLayout.EAST);
		central.add(Box.createRigidArea(new Dimension(500, 200)), BorderLayout.WEST);
		JPanel panelAuxB = new JPanel();
		panelAuxB.setLayout(new BorderLayout());
		panelAuxB.setBackground(Color.BLACK);
		panelAuxB.add(Box.createRigidArea(new Dimension(0, 60)), BorderLayout.PAGE_START);
		panelAuxB.add(crearBoton("Dar Baja Juego"), BorderLayout.PAGE_END);
		central.add(panelAuxB, BorderLayout.PAGE_END);
		central.add(central2, BorderLayout.CENTER);
		this.add(central, BorderLayout.CENTER);
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

	private JButton crearBoton(String texto) {
		JButton b = new JButton(texto);
		b.setBackground(new Color(59, 89, 182));
		b.setForeground(Color.WHITE);
		b.setFocusPainted(false);
		b.setFont(new Font("Tahoma", Font.BOLD, 60));
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!id.getText().isEmpty()) {
					if(control.darBajaJuego(id.getText())) {
						ViewUtils.showSuccessMsg("Juego borrado");
					}else {
						ViewUtils.showErrorMsg("Operacion no valida");
					}
				}else ViewUtils.showErrorMsg("Datos Incorrectos");
				
			}
		});
			
		return b;
	}
}
