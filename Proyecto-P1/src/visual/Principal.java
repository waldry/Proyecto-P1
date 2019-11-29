package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.text.DateFormatter;

import logico.Controladora;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Dimension dim;
//	private Date fecha = new Date();
	private Calendar fecha = new GregorianCalendar();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setTitle("Menu Principal");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		dim = super.getToolkit().getScreenSize();
		super.setSize(dim.width, dim.height-100);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(240, 248, 255));
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Clientes");
		mnNewMenu.setIcon(new ImageIcon(Principal.class.getResource("/recursos/habla.png")));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmRegistrarCliente = new JMenuItem("Registrar Cliente");
		mntmRegistrarCliente.setIcon(new ImageIcon(Principal.class.getResource("/recursos/que-hacer.png")));
		mntmRegistrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarCliente regCliente = new RegistrarCliente();
				regCliente.setModal(true);
				regCliente.setVisible(true);
			}
		});
		mnNewMenu.add(mntmRegistrarCliente);
		
		JMenuItem mntmListadoDeClientes = new JMenuItem("Listado de Clientes");
		mntmListadoDeClientes.setIcon(new ImageIcon(Principal.class.getResource("/recursos/lista.png")));
		mntmListadoDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaClientes listCliente = new ListaClientes();
				listCliente.setModal(true);
				listCliente.setVisible(true);
			}
		});
		mnNewMenu.add(mntmListadoDeClientes);
		
		JMenu mnPlanesYServicios = new JMenu("Planes y Servicios");
		mnPlanesYServicios.setIcon(new ImageIcon(Principal.class.getResource("/recursos/cliente.png")));
		menuBar.add(mnPlanesYServicios);
		
		JMenuItem mntmRegistrarNuevoPlan = new JMenuItem("Registrar Plan");
		mntmRegistrarNuevoPlan.setIcon(new ImageIcon(Principal.class.getResource("/recursos/que-hacer.png")));
		mntmRegistrarNuevoPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarServicio RegServ = new RegistrarServicio();
				RegServ.setModal(true);
				RegServ.setVisible(true);
			}
		});
		mnPlanesYServicios.add(mntmRegistrarNuevoPlan);
		
		JMenuItem mntmListarPlanes = new JMenuItem("Listado de Planes");
		mntmListarPlanes.setIcon(new ImageIcon(Principal.class.getResource("/recursos/lista.png")));
		mntmListarPlanes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaPlanes listPlan = new ListaPlanes();
				listPlan.setModal(true);
				listPlan.setVisible(true);
			}
		});
		
		mnPlanesYServicios.add(mntmListarPlanes);
		
		JMenu mnAdministrativo = new JMenu("Administrativo");
		mnAdministrativo.setIcon(new ImageIcon(Principal.class.getResource("/recursos/apoyar.png")));
		menuBar.add(mnAdministrativo);
		
		JMenuItem mntmAgregarUsuario = new JMenuItem("Agregar Usuario");
		mntmAgregarUsuario.setIcon(new ImageIcon(Principal.class.getResource("/recursos/que-hacer.png")));
		mntmAgregarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarUsuario regUser = new RegistrarUsuario();
				regUser.setModal(true);
				regUser.setVisible(true);
			}
		});
		mnAdministrativo.add(mntmAgregarUsuario);
		
		JMenuItem mntmListaDeUsuarios = new JMenuItem("Listado de Usuarios");
		mntmListaDeUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaUsuarios listUsu = new ListaUsuarios();
				listUsu.setModal(true);
				listUsu.setVisible(true);
			}
		});
		mntmListaDeUsuarios.setIcon(new ImageIcon(Principal.class.getResource("/recursos/lista.png")));
		mnAdministrativo.add(mntmListaDeUsuarios);
		
		JMenu mnFacturas = new JMenu("Facturacion");
		mnFacturas.setIcon(new ImageIcon(Principal.class.getResource("/recursos/fact.png")));
		menuBar.add(mnFacturas);
		
		JMenuItem mntmFacturarPlan = new JMenuItem("Facturar Plan");
		mntmFacturarPlan.setIcon(new ImageIcon(Principal.class.getResource("/recursos/archivo.png")));
		mntmFacturarPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearFactura fact = new CrearFactura();
				fact.setModal(true);
				fact.setVisible(true);
			}
		});
		mnFacturas.add(mntmFacturarPlan);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 623, 395);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(643, 10, 623, 395);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 415, 623, 395);
		contentPane.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(643, 415, 623, 395);
		contentPane.add(panel_3);
		setLocationRelativeTo(null);
	}
}
