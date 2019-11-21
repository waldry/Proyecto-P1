package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private ImageIcon img1;
	private Icon icono;

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
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Clientes");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmRegistrarCliente = new JMenuItem("Registrar Cliente");
		mntmRegistrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarCliente regCliente = new RegistrarCliente();
				regCliente.setModal(true);
				regCliente.setVisible(true);
			}
		});
		mnNewMenu.add(mntmRegistrarCliente);
		
		JMenuItem mntmListadoDeClientes = new JMenuItem("Listado de Clientes");
		mntmListadoDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaClientes listCliente = new ListaClientes();
				listCliente.setModal(true);
				listCliente.setVisible(true);
			}
		});
		mnNewMenu.add(mntmListadoDeClientes);
		
		JMenu mnPlanesYServicios = new JMenu("Planes y Servicios");
		menuBar.add(mnPlanesYServicios);
		
		JMenuItem mntmRegistrarNuevoPlan = new JMenuItem("Registrar  Plan");
		mntmRegistrarNuevoPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarServicio RegServ = new RegistrarServicio();
				RegServ.setModal(true);
				RegServ.setVisible(true);
			}
		});
		mnPlanesYServicios.add(mntmRegistrarNuevoPlan);
		
		JMenuItem mntmListarPlanes = new JMenuItem("Listar Planes");
		mntmListarPlanes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaPlanes listPlan = new ListaPlanes();
				listPlan.setModal(true);
				listPlan.setVisible(true);
			}
		});
		
		mnPlanesYServicios.add(mntmListarPlanes);
		
		JMenu mnAdministrativo = new JMenu("Administrativo");
		mnAdministrativo.setIcon(new ImageIcon("C:\\Iconos\\apoyar.png"));
		menuBar.add(mnAdministrativo);
		
		JMenuItem mntmAgregarUsuario = new JMenuItem("Agregar Usuario");
		mnAdministrativo.add(mntmAgregarUsuario);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
	}
}
