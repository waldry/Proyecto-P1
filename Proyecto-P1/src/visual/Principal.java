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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.text.DateFormatter;

import org.jfree.data.general.DefaultPieDataset;

import logico.Controladora;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.TitledBorder;

public class Principal extends JFrame implements Serializable{

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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream empresa2;
				ObjectOutputStream empresaWrite;
				try {
					empresa2 = new  FileOutputStream("empresa.dat");
					empresaWrite = new ObjectOutputStream(empresa2);
					empresaWrite.writeObject(Controladora.getInstance());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
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
		
		JPanel panelGrafico1 = new JPanel();
		panelGrafico1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Gr\u00E1fico 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGrafico1.setBounds(10, 10, 623, 395);
		contentPane.add(panelGrafico1);
		/*Recordar recopilar datos para hacer un grafico aqui
		 * 
		 * 
		 */
		DefaultPieDataset datos = new DefaultPieDataset();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Gr\u00E1fico 2", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
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
