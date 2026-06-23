package main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import juegos.control.ControllerJuegos;
import mesas.control.ControllerMesas;
import parking.control.ControladorParking;
import usuario.control.ControllerUsu;

/**
 * @author Marta Hernandez, Alejandro Remiro, Alejandro Mammeri, Daniel Menendez, Alejandro Parra, Adrian Valverde, Lucas Arranz, Alejandro Ramos
 */
public class MainWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = -2152809841584076425L;
	private ControlPanel ctrPanel;
	private InicioSesionPanel inicioSesionPanel;
	private ParkingPanel parkingPanel;
	private ControlPanelUsuario controlUsuario;
	private RegistroUsuarioPanel registroPanel;
	private PerfilUsuarioPanel panelPerfilUsuario;
	private OcupaPlazaPanel ocupaPanel;
	private DesOcupaPlazaPanel desOcupaPanel;
	private BuscarReservasPanel buscaReservas;
	private TablaBusquedaModTarabajador busquedaModTrabajador;
	private ConsultaReservaPanel consultaPanel;
	private MesasTrabajadorPanel mesaTrabajador;
	private JuegoPanelTrabajador juegosTrabajador;
    private AltaJuegoPanel altaJuegoPanel;
    private BajaJuegoPanel bajaJuegoPanel;
    private BajaMesaPanel bajaMesaPanel;
    private AltaMesaPanel altaMesaPanel;
    private ConsultaMesaPanel consultaMesaPanel;
    private ModificarMesaPanel modificarMesaPanel;
    private SeleccionarMesaPanel seleccionarMesaPanel;
    private ApostarPanel apostarPanel;
    private GestionJuegosPanel gestionJuegosPanel;
    private ControllerJuegos j;
    private ControllerMesas m;
    private ControllerUsu u;
    
	public MainWindow(ControllerJuegos j, ControllerUsu u, ControladorParking p, ControllerMesas m) {
		super("Casino LSD😎");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		registroPanel = new RegistroUsuarioPanel(u);
		inicioSesionPanel = new InicioSesionPanel(u);
		parkingPanel = new ParkingPanel();
		ctrPanel = new ControlPanel();
		controlUsuario = new ControlPanelUsuario();
		panelPerfilUsuario = new PerfilUsuarioPanel(u);
		ocupaPanel = new OcupaPlazaPanel(p);
		desOcupaPanel = new DesOcupaPlazaPanel(p);
		buscaReservas = new BuscarReservasPanel();
		busquedaModTrabajador = new TablaBusquedaModTarabajador(u);
		consultaPanel = new ConsultaReservaPanel(p);
		mesaTrabajador = new MesasTrabajadorPanel();
		juegosTrabajador = new JuegoPanelTrabajador();
		altaJuegoPanel = new AltaJuegoPanel(j);
		bajaJuegoPanel = new BajaJuegoPanel(j);
		bajaMesaPanel = new BajaMesaPanel(m);
		altaMesaPanel = new AltaMesaPanel(m);
		consultaMesaPanel = new ConsultaMesaPanel(m);
		modificarMesaPanel = new ModificarMesaPanel(m);
		seleccionarMesaPanel = new SeleccionarMesaPanel(m, j);
		gestionJuegosPanel = new GestionJuegosPanel(j);
		this.m = m;
		this.j = j;
		this.u = u;
		initGUI();
	}

	public void initGUI() {
		this.setSize(new Dimension(800, 600));
		JPanel mainPanel = new JPanel(new BorderLayout());
		showInicioSesionPanel();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                
                if (apostarPanel != null && apostarPanel.isVisible()) {
                	String idMesa = apostarPanel.getIdMesa();
                	m.quitarJugador(Credentials.getDni(), idMesa);
                }
                System.exit(0); // O cierra la aplicaci�n
            }
        });
		
		setBounds(200, 200, 800, 600);
		setVisible(true);
	}

	public void showControlPanel() {
		ocultarTodas();
		ctrPanel.setVisible(true);
		add(ctrPanel, BorderLayout.CENTER);
	}

	public void showInicioSesionPanel() {
		ocultarTodas();
		inicioSesionPanel.vaciarString();
		inicioSesionPanel.setVisible(true);
		add(inicioSesionPanel, BorderLayout.CENTER);
	}

	public void showRegistroPanel() {
		ocultarTodas();
		registroPanel.setVisible(true);
		add(registroPanel, BorderLayout.CENTER);
	}

	public void showControlPanelUsuarios() {
		ocultarTodas();
		controlUsuario.setVisible(true);
		add(controlUsuario, BorderLayout.CENTER);
	}

	public void showBuscarReservasParking() {
		ocultarTodas();
		buscaReservas.setVisible(true);
		add(buscaReservas, BorderLayout.CENTER);
	}

	public void showParkingPanel() {
		ocultarTodas();
		parkingPanel.setVisible(true);

		add(parkingPanel, BorderLayout.CENTER);
	}

	public void showPerfilClientePanel() {
		ocultarTodas();
		panelPerfilUsuario.setVisible(true);
		add(panelPerfilUsuario, BorderLayout.CENTER);
	}

	public void showOcupaPlazaPanel() {
		ocultarTodas();
		ocupaPanel.setVisible(true);
		add(ocupaPanel, BorderLayout.CENTER);
	}

	public void showdesOcupaPlazaPanel() {
		ocultarTodas();
		desOcupaPanel.setVisible(true);
		add(desOcupaPanel, BorderLayout.CENTER);
	}

	public void showBusquedaModPanel() {
		ocultarTodas();
		busquedaModTrabajador.setVisible(true);
		add(busquedaModTrabajador, BorderLayout.CENTER);
	}

	public void showConsultaReservaPanel() {
		ocultarTodas();
		consultaPanel.setVisible(true);
		add(consultaPanel, BorderLayout.CENTER);
	}

	public void showMesasTrabajador() {
		ocultarTodas();
		mesaTrabajador.setVisible(true);
		add(mesaTrabajador, BorderLayout.CENTER);
	}

	public void showJuegosPanelTrabajador() {
    	ocultarTodas();
    	juegosTrabajador.setVisible(true);
    	add(juegosTrabajador, BorderLayout.CENTER);
	}
    
	public void showAltaJuegoPanel() {
		ocultarTodas();
		altaJuegoPanel.setVisible(true);
		add(altaJuegoPanel, BorderLayout.CENTER);
	}
	    
	public void showBajaJuegoPanel() {
		ocultarTodas();
		bajaJuegoPanel.setVisible(true);
		add(bajaJuegoPanel, BorderLayout.CENTER);
	}
    	
    public void ShowbajaMesaPanel() {
		ocultarTodas();
		bajaMesaPanel.setVisible(true);
		add(bajaMesaPanel, BorderLayout.CENTER);
    }
    	
    	
    public void ShowaltaMesaPanel() {
		ocultarTodas();
		altaMesaPanel.setVisible(true);
		add(altaMesaPanel, BorderLayout.CENTER);
    }
    	
    	
    public void showconsultaMesaPanel() {
		ocultarTodas();
		consultaMesaPanel.setVisible(true);
		add(consultaMesaPanel, BorderLayout.CENTER);
    }
    
    public void showmodificarMesaPanel() {
		ocultarTodas();
		modificarMesaPanel.setVisible(true);
		add(modificarMesaPanel, BorderLayout.CENTER);
    }
	
	public void showSeleccionarMesaPanel() {
		ocultarTodas();
		seleccionarMesaPanel.setVisible(true);
		seleccionarMesaPanel.actualizarJuegos();
		add(seleccionarMesaPanel, BorderLayout.CENTER);
	}
	
	public void showApostarPanel(String idMesa) {
		ocultarTodas();
		apostarPanel = new ApostarPanel(idMesa, m, j, u);
		apostarPanel.setVisible(true);
		add(apostarPanel, BorderLayout.CENTER);
	}
	
	public void showGestionJuegosPanel() {
		ocultarTodas();
		gestionJuegosPanel.setVisible(true);
		gestionJuegosPanel.actualizarTabla();
		add(gestionJuegosPanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	private void ocultarTodas() {
		registroPanel.setVisible(false);
		inicioSesionPanel.setVisible(false);
		parkingPanel.setVisible(false);
		ctrPanel.setVisible(false);
		controlUsuario.setVisible(false);
		panelPerfilUsuario.setVisible(false);
		ocupaPanel.setVisible(false);
		desOcupaPanel.setVisible(false);
		buscaReservas.setVisible(false);
		busquedaModTrabajador.setVisible(false);
		consultaPanel.setVisible(false);
		mesaTrabajador.setVisible(false);
		mesaTrabajador.setVisible(false);
		juegosTrabajador.setVisible(false);
    	altaJuegoPanel.setVisible(false);
    	bajaJuegoPanel.setVisible(false);
    	bajaMesaPanel.setVisible(false);
    	altaMesaPanel.setVisible(false);
    	consultaMesaPanel.setVisible(false);
    	modificarMesaPanel.setVisible(false);
    	seleccionarMesaPanel.setVisible(false);
    	gestionJuegosPanel.setVisible(false);
    	if (apostarPanel != null)
    		apostarPanel.setVisible(false);
	}

}
