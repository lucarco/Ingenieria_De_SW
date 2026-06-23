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

import usuario.control.ControllerUsu;
import usuario.model.Usuario;

public class PerfilUsuarioPanel extends JPanel {
	
	ControllerUsu control;
	JTextField jt;

	public PerfilUsuarioPanel(ControllerUsu c) {
		control = c;
		jt  = new JTextField();
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		
		JToolBar tb = crearToolBar();
		
		
		this.setBackground(Color.BLACK);
		
		JButton botonAtras = new JButton();
		
		botonAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(PerfilUsuarioPanel.this);
				mainWindow.showControlPanelUsuarios();
			}
			
		});
		
		botonAtras.setIcon(new ImageIcon("icons/atras.png"));
		botonAtras.setBackground(new Color(0, 9, 60));
		botonAtras.setBorder(null);
		botonAtras.setBorderPainted(false);
		botonAtras.setOpaque(false);
		
		tb.add(botonAtras, BorderLayout.WEST);
		
		this.add(tb, BorderLayout.NORTH);
		
		JButton botonmas = new JButton();
		
		botonmas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(!control.comprarFichas(Credentials.getDni(),Integer.parseInt(PerfilUsuarioPanel.this.jt.getText()))) ViewUtils.showErrorMsg("Error al comprar las fichas");
				}catch(NumberFormatException ex) {
					ViewUtils.showErrorMsg("Introduzca un numero sin decimales.");
				}
			}
			
		});
		
		botonmas.setIcon(new ImageIcon("icons/botonmas.png"));
		botonmas.setBackground(new Color(0, 9, 60));
		botonmas.setBorder(null);
		botonmas.setBorderPainted(false);
		botonmas.setOpaque(false);
		
		JButton botonmenos = new JButton();
		
		botonmenos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(!control.venderFichas(Credentials.getDni() ,Integer.parseInt(PerfilUsuarioPanel.this.jt.getText()))) ViewUtils.showErrorMsg("Error al vender las fichas");
				}catch(NumberFormatException ex) {
					ViewUtils.showErrorMsg("Introduzca un numero sin decimales.");
				}
			}
			
		});
		
		botonmenos.setIcon(new ImageIcon("icons/botonmenos.png"));
		botonmenos.setBackground(new Color(0, 9, 60));
		botonmenos.setBorder(null);
		botonmenos.setBorderPainted(false);
		botonmenos.setOpaque(false);
		
		JPanel textoFichas = new JPanel();
		
		textoFichas.setLayout(new BoxLayout(textoFichas, BoxLayout.LINE_AXIS));
		
		textoFichas.setBackground(Color.BLACK);
		
		jt = new JTextField();
		
		jt.setFont(new Font("Arial", Font.BOLD, 60));
		
		this.add(Box.createRigidArea(new Dimension(500,500)), BorderLayout.WEST);
		this.add(Box.createRigidArea(new Dimension(500,500)), BorderLayout.EAST);
		
		JLabel ft = new JLabel();
		
		ft.setFont(new Font("Arial", Font.BOLD, 60));
		
		ft.setText("Fichas(0): ");
		
		ft.setForeground(Color.WHITE);
		
		textoFichas.add(ft);
		textoFichas.add(jt); 
		textoFichas.add(botonmas);
		textoFichas.add(botonmenos);
		
		JPanel centro = new JPanel();
		
		centro.setBackground(Color.BLACK);
		
		centro.setLayout(new BorderLayout());
		
		centro.add(Box.createRigidArea(new Dimension(200,400)), BorderLayout.NORTH);
		
		JPanel abajo = new JPanel();
		abajo.setLayout(new BorderLayout());
		
		abajo.setBackground(Color.BLACK);
		
		abajo.add(Box.createRigidArea(new Dimension(200,200)), BorderLayout.NORTH);
		
		JButton botonEliminar = crearBoton("BORRAR CUENTA");
		
		botonEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(control.bajaUsuario(Credentials.getDni())) {
					MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(PerfilUsuarioPanel.this);
					mainWindow.showInicioSesionPanel();
				}else ViewUtils.showErrorMsg("No se ha podido borrar la cuenta");
			}
			
		});
		
		abajo.add(botonEliminar, BorderLayout.CENTER);
		
		abajo.add(Box.createRigidArea(new Dimension(200,100)), BorderLayout.SOUTH);
		centro.add(abajo,BorderLayout.SOUTH);
		centro.add(textoFichas, BorderLayout.CENTER);
		
		Thread hilo = new Thread() {
			
			public void run() {
				while(true) {
					Usuario usu = control.consultaUsuario(Credentials.getDni());
					if(usu != null) ft.setText("Fichas(" + usu.getFichas() + "): ");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
					
				}
			}
			
		};
		
		hilo.start();
		
		this.add(centro, BorderLayout.CENTER);
		
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
		return b;
	}
	
	public void mostrarFichas() {
		jt.setText(control.consultaUsuario(Credentials.getDni()).getFichas() + "");
	}

}
