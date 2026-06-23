package main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import juegos.control.ControllerJuegos;
import mesas.control.ControllerMesas;
import mesas.model.Mesa;
import usuario.control.ControllerUsu;
import usuario.model.Usuario;

public class ApostarPanel extends JPanel{

	private BufferedImage imgFondo;
	private JTextField campoApuesta;
    private JButton botonSubmit;
    private JLabel centerText;
    private JLabel textoFichas;
    
    private ControllerUsu _ctrlUsu;
    private ControllerMesas _ctrlMesas;
    private ControllerJuegos _ctrlJuegos;
    private Mesa mesa;
    private Usuario usu;
    
/**
 * @author Alejandro Alba Mammeri,  Daniel Menéndez Crespo y  Alejandro Remiro
 *
 * Interfaz gráfica para apostar dentro de un juego en el casino.
 * Visualizando la mesa, el juego, las fichas y el ressultado de la apuesta.
 * 
 */
	
    public ApostarPanel(String idMesa, ControllerMesas m, ControllerJuegos j, ControllerUsu u) {
    	
    	_ctrlMesas = m;
    	_ctrlJuegos = j;
    	_ctrlUsu = u;
    	
    	mesa = _ctrlMesas.consultaMesa(idMesa);
    	usu = _ctrlUsu.consultaUsuario(Credentials.getDni());
    	
    	centerText = new JLabel("| " + "VACIA" + " | NºJugadores: " + 0 + " | Mesa: " + 0 + " | ");
    	centerText.setAlignmentX(CENTER_ALIGNMENT);
    	centerText.setForeground(Color.WHITE);
    	centerText.setFont(new Font("Arial", Font.BOLD, 60));
    	
    	textoFichas = new JLabel("NºFichas: 0 ");
    	textoFichas.setAlignmentX(CENTER_ALIGNMENT);
    	textoFichas.setForeground(Color.WHITE);
    	textoFichas.setFont(new Font("Arial", Font.BOLD, 60));
    	
    	
    	this.setLayout(new BorderLayout());
    	
    	this.add(crearToolBar(), BorderLayout.NORTH);
    	
        try {
        	imgFondo = ImageIO.read(new File("icons/fondoCasino.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        campoApuesta = new JTextField(10);
        
        campoApuesta.setFont(new Font("Arial", Font.BOLD, 60));
        
        botonSubmit = crearBoton("Apostar");
        
        JPanel panelInferior = new JPanel();
        
        panelInferior.add(textoFichas);
        panelInferior.add(campoApuesta);
        panelInferior.add(botonSubmit);
        
        panelInferior.setBackground(Color.BLACK);
        
        add(panelInferior, BorderLayout.SOUTH);
        
        Thread hilo1 = new Thread() {
        	
        	public void run(){
        		while(true) {
        			centerText.setText("| " + mesa.getJuego().toUpperCase() + " | NºJugadores: " + m.obtenerJugadores(idMesa).size() + " | Mesa: " + idMesa + " | ");
        			try {
        				Thread.sleep(2000);
        				usu = _ctrlUsu.consultaUsuario(Credentials.getDni());
        				if(usu != null) textoFichas.setText("NºFichas: " + usu.getFichas() + " ");
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        	}
        	
        };
        
        hilo1.start();
        
        botonSubmit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int fichas = Integer.parseInt(campoApuesta.getText());
				
				if (fichas > usu.getFichas())
					ViewUtils.showErrorMsg("No hay suficientes fichas");
				else {
					try {
	                    if(_ctrlMesas.simularJuego(_ctrlJuegos.consultarJuego(mesa.getJuego()).getProb())) {
	                    	_ctrlJuegos.apostar(Credentials.getDni(), fichas * 
	                    			(100 / _ctrlJuegos.consultarJuego(mesa.getJuego()).getProb()));
	                    	ViewUtils.showSuccessMsg("Ya ves, keep gambling");
	                    }
	                    else {
	                    	_ctrlJuegos.apostar(Credentials.getDni(), (fichas * -1));
	                    	ViewUtils.showErrorMsg("Solo hay winners y quitters. Keep gambling");
	                    }     
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(ApostarPanel.this, "Apuesta no válida", "Error", JOptionPane.ERROR_MESSAGE);
	                }
				}
				
				usu = _ctrlUsu.consultaUsuario(Credentials.getDni());
				if(usu != null) textoFichas.setText("NºFichas: " + usu.getFichas() + " ");
				
			}
        });
        
        
        /*JLabel labelJugadores = new JLabel(_ctrlMesas.obtenerJugadores(mesa.getId()).size() + " Jugadores");
        labelJugadores.setForeground(Color.WHITE);
        labelJugadores.setFont(new Font("Arial", Font.BOLD, 30));

        JPanel panelJugadores = new JPanel();
        panelJugadores.add(labelJugadores);

        setLayout(new BorderLayout());
        add(panelJugadores, BorderLayout.SOUTH);*/
        
        
	}
    
    public String getIdMesa() {
    	return this.mesa.getId();
    }
    

    @Override
    protected void paintComponent(Graphics g) {
    	
        super.paintComponent(g);

        if (imgFondo != null) {
            Image scaledImage = imgFondo.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, 0, 0, null);
        }
    }

    public void setBackgroundImage(String imagePath) {
        try {
        	imgFondo = ImageIO.read(new File(imagePath));
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

		JButton botonAtras = new JButton();

		botonAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(ApostarPanel.this);
				mainWindow.showSeleccionarMesaPanel();
				_ctrlMesas.quitarJugador(Credentials.getDni(), mesa.getId());
			}

		});

		botonAtras.setIcon(new ImageIcon("icons/atras.png"));
		botonAtras.setBackground(new Color(0, 9, 60));
		botonAtras.setBorder(null);
		botonAtras.setBorderPainted(false);
		botonAtras.setOpaque(false);

		toolbar.add(centerText, BorderLayout.CENTER);
		toolbar.add(botonAtras, BorderLayout.WEST);
		this.add(toolbar, BorderLayout.NORTH);

		return toolbar;

	}
}
