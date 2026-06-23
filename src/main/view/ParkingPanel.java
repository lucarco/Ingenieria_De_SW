package main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * @author Alejandro Parra, Marta Hernandez
 */
public class ParkingPanel extends JPanel {

    public ParkingPanel() {
    	initGUI();
    }
    private void initGUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JToolBar toolbar = crearToolBar();
		JButton botonAtras = new JButton();
		
		botonAtras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(ParkingPanel.this);
				mainWindow.showControlPanel();
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBackground(Color.BLACK);

        JButton ocuparPlazaButton = new JButton("Ocupar Plaza");
        ocuparPlazaButton.setFont(new Font("Arial", Font.BOLD, 60));
        ocuparPlazaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(ParkingPanel.this);
				mainWindow.showOcupaPlazaPanel();
            }
        });
        buttonPanel.add(ocuparPlazaButton);

        JButton desocuparPlazaButton = new JButton("Desocupar Plaza");
        desocuparPlazaButton.setFont(new Font("Arial", Font.BOLD, 60));
        desocuparPlazaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(ParkingPanel.this);
				mainWindow.showdesOcupaPlazaPanel();
            }
        });
        buttonPanel.add(desocuparPlazaButton);

        JButton buscaReservasButton = new JButton("Buscar Reservas");
        buscaReservasButton.setFont(new Font("Arial", Font.BOLD, 60));
        buscaReservasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(ParkingPanel.this);
				mainWindow.showBuscarReservasParking();
            }
        });
        buttonPanel.add(buscaReservasButton);

        JButton consultaReservaButton = new JButton("Consultar Reserva");
        consultaReservaButton.setFont(new Font("Arial", Font.BOLD, 60));
        consultaReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(ParkingPanel.this);
				mainWindow.showConsultaReservaPanel();
            }
        });
        buttonPanel.add(consultaReservaButton);


        add(buttonPanel, BorderLayout.CENTER);
        
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
}
