package main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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

/**
 * @author Alejandro Remiro, Lucas Arranz, Alejandro Ramos
 */
public class MesasTrabajadorPanel extends JPanel{
	
	public MesasTrabajadorPanel() {
		initGUI();
	}
	
	
	
	private void initGUI() {
		
		this.setLayout(new BorderLayout());
		JToolBar toolbar =  crearToolBar();
		
		this.setBackground(Color.BLACK);
		
		this.add(toolbar, BorderLayout.NORTH);
		
		
		JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBackground(Color.BLACK);
		
		JButton altaMesaButton = new JButton("Alta Mesa");
		altaMesaButton.setFont(new Font("Arial", Font.BOLD, 60));
		altaMesaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(MesasTrabajadorPanel.this);
				mainWindow.ShowaltaMesaPanel();
            }
        });
		
		
        buttonPanel.add(altaMesaButton);
        
        JButton bajaMesaButton = new JButton("Baja Mesa");
        bajaMesaButton.setFont(new Font("Arial", Font.BOLD, 60));
        bajaMesaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(MesasTrabajadorPanel.this);
				mainWindow.ShowbajaMesaPanel();
            }
        });
        buttonPanel.add(bajaMesaButton);

        
        JButton modificarMesaButton = new JButton("Modificar Mesa");
        modificarMesaButton.setFont(new Font("Arial", Font.BOLD, 60));
        modificarMesaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(MesasTrabajadorPanel.this);
				mainWindow.showmodificarMesaPanel();
            }
        });
        buttonPanel.add(modificarMesaButton);
        
        

        JButton consultaMesaButton = new JButton("Consulta Mesa");
        consultaMesaButton.setFont(new Font("Arial", Font.BOLD, 60));
        consultaMesaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(MesasTrabajadorPanel.this);
				mainWindow.showconsultaMesaPanel();
            }
        });
        buttonPanel.add(consultaMesaButton);
	    
	    
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

		JButton botonAtras = new JButton();
		
		botonAtras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(MesasTrabajadorPanel.this);
				mainWindow.showControlPanel();;
			}
			
		});
		
		botonAtras.setIcon(new ImageIcon("icons/atras.png"));
		botonAtras.setBackground(new Color(0, 9, 60));
		botonAtras.setBorder(null);
		botonAtras.setBorderPainted(false);
		botonAtras.setOpaque(false);
		
		toolbar.add(botonAtras, BorderLayout.WEST);
		
		return toolbar;}
		
		
	
	
	

	private JButton crearBoton(String texto) {
		JButton b = new JButton(texto);
		b.setBackground(new Color(59, 89, 182));
		b.setForeground(Color.WHITE);
		b.setFocusPainted(false);
		b.setFont(new Font("Tahoma", Font.BOLD, 60));
		return b;
	}
	
	
	
	
	
}
