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
import java.util.Timer;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.text.DateFormatter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import logico.Cliente;
import logico.Contrato;
import logico.Controladora;
import logico.Personal;
import logico.Plan;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.Toolkit;

public class Principal extends JFrame implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Dimension dim;
//	private Date fecha = new Date();
	private Calendar fecha = new GregorianCalendar();
	private int a;
	private int b;
	private String fecha1;
	private String fecha2;
	private int x;
	private int y;
	private JPanel panelGrafico1;
	private JPanel panelGrafico2;
	

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/recursos/Logo_de_Tricom.png")));
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
		valores();
//		contratosPorUsuario();
		setTitle("Menu Principal");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1289, 590);
		
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
				RegistrarServicio RegServ = new RegistrarServicio(null);
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
//		if(!Controladora.getLoggedUser().getTipo().equalsIgnoreCase("Administrativo")) {
//			mnAdministrativo.setEnabled(false);
//		}
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
		
		JMenuItem mntmFacturarPlan = new JMenuItem("Simular Factura");
		mntmFacturarPlan.setIcon(new ImageIcon(Principal.class.getResource("/recursos/archivo.png")));
		mntmFacturarPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Simulador fact = new Simulador();
				fact.setModal(true);
				fact.setVisible(true);
			}
		});
		mnFacturas.add(mntmFacturarPlan);
		
		JMenuItem mntmPagoFactura = new JMenuItem("Pago Factura");
		mntmPagoFactura.setIcon(new ImageIcon(Principal.class.getResource("/recursos/lista.png")));
		mntmPagoFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PagoFactura pagar = new PagoFactura();
				pagar.setVisible(true);
				pagar.setModal(true);
			}
		});
		mnFacturas.add(mntmPagoFactura);
		
		JMenu mnContrato = new JMenu("Contrato");
		mnContrato.setIcon(new ImageIcon(Principal.class.getResource("/recursos/contrato.png")));
		menuBar.add(mnContrato);
		
		JMenuItem mntmNuevoContrato = new JMenuItem("Nuevo Contrato");
		mntmNuevoContrato.setIcon(new ImageIcon(Principal.class.getResource("/recursos/archivo.png")));
		mntmNuevoContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegContrato aux = new RegContrato();
				aux.setVisible(true);
				aux.setModal(true);
			}
		});
		mnContrato.add(mntmNuevoContrato);
		
		JMenuItem mntmListarPlanes_1 = new JMenuItem("Listar Planes");
		mntmListarPlanes_1.setIcon(new ImageIcon(Principal.class.getResource("/recursos/lista.png")));
		mntmListarPlanes_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaContratos aux = new ListaContratos();
				aux.setVisible(true);
				aux.setModal(true);
			}
		});
		mnContrato.add(mntmListarPlanes_1);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelGrafico1 = new JPanel();
		panelGrafico1.setBorder(null);
		panelGrafico1.setBounds(10, 10, 690, 430);
		contentPane.add(panelGrafico1);
		//Datos grafico
		
		panelGrafico2 = new JPanel();
		panelGrafico2.setBorder(null);
		panelGrafico2.setBounds(710, 10, 690, 430);
		contentPane.add(panelGrafico2);
		setLocationRelativeTo(null);
		init1();
//		init2();
	}
	private void init1() {
		valores();
		DefaultPieDataset datos = new DefaultPieDataset();
		datos.setValue("Clientes Activos", x);
		datos.setValue("Clientes Inactivos", y+3);
		
		JFreeChart chart = ChartFactory.createPieChart("Clientes Activos vs Clientes Inactivos", datos, true, true, false);
		ChartPanel chartPanel = new ChartPanel(chart);
		panelGrafico1.add(chartPanel);
	}
	
	public void valores() {
		for (Cliente client : Controladora.getInstance().getClientes()) {
			if(client.isActivo()) {
				x += 1;
			}
			if(!client.isActivo()) {
				y += 1;
			}
		}
	}
	private void init2() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
	}
//	public String planMasVendido() {
//		String aux = "";
//		for (Contrato contract : Controladora.getInstance().getContratos()) {
//			int mayor = 0;
//			for (Plan auxPlan : contract.getPlanes()) {
//				auxPlan.getNombre();
//			}
//		}
//	}
}
