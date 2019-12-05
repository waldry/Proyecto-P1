package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import logico.Controladora;
import logico.Factura;
import logico.Plan;

import javax.swing.JList;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Simulador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField entry;
	private JTextField nombre_cliente;
	private JLabel fecha_ahora;
	private Calendar calendario = new GregorianCalendar();
	private JLabel fecha_simulada;
	private Factura facturaFound;
	private ArrayList<Plan> planesToLoad = new ArrayList<Plan>();
	private DefaultListModel<String> planesToShow = new DefaultListModel<>();
	private float mora= 0;
	private JList main;
	private JLabel cargo_actual;
	private JLabel mora_actual;
	private JLabel total_simulado;
	private JLabel meses_mostrar;
	private int mesesAtrasados = 0;
	private float aux = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Simulador dialog = new Simulador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Simulador() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Simulador.class.getResource("/recursos/fact.png")));
		setResizable(false);
		
		
		String fechaUsandoGregorian = formato();
		setTitle("Simulador de Facturas");
		setBounds(100, 100, 470, 512);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("ID Contrato: ");
		lblNewLabel.setBounds(10, 22, 90, 14);
		contentPanel.add(lblNewLabel);
		
		entry = new JTextField();
		entry.setBounds(86, 19, 86, 20);
		contentPanel.add(entry);
		entry.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(Simulador.class.getResource("/recursos/lupa16.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Factura aux : Controladora.getInstance().getFacturas()) {
					if (entry.getText().equalsIgnoreCase(aux.getId())) {
						facturaFound = aux;
						for (Plan plan : facturaFound.getPlanCliente()) {
							planesToLoad.add(plan);
						}
						updatePlanes();
						nombre_cliente.setText(aux.getCliente().getNombre());
					}
				}
			}
		});
		btnBuscar.setBounds(182, 18, 101, 23);
		contentPanel.add(btnBuscar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informacion - Contrato", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 84, 424, 287);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente: ");
		lblCliente.setBounds(10, 33, 59, 14);
		panel.add(lblCliente);
		
		nombre_cliente= new JTextField();
		nombre_cliente.setEditable(false);
		nombre_cliente.setBounds(64, 30, 86, 20);
		panel.add(nombre_cliente);
		nombre_cliente.setColumns(10);
		
		main = new JList<>(planesToShow);
		main.setEnabled(false);
		main.setBounds(10, 80, 151, 196);
		panel.add(main);
		
		JLabel lblServicios = new JLabel("Servicios");
		lblServicios.setBounds(62, 61, 66, 14);
		panel.add(lblServicios);
		
		JButton btnActualizarCargos = new JButton("Agregar mes");
		btnActualizarCargos.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				
				calendario.add(Calendar.DATE, 30);
				DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				fecha_simulada.setText(formato.format(calendario.getTime()));
				
				try {
					mesesAtrasados = diferenciaMeses();
				} catch (IOException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DefaultListModel<String> db = ((DefaultListModel<String>)main.getModel());
				
				for (int i = 0; i < db.getSize(); i++) {
					String s = (String)db.getElementAt(i);
					String[] separador = s.split("_", 2);
					String costo = separador[1];
					aux = aux + Float.parseFloat(costo);
					mora = (float) ((mesesAtrasados*aux)*0.0314);
					cargo_actual.setText(String.valueOf(aux));
					mora_actual.setText(String.valueOf(mora));
					total_simulado.setText(String.valueOf(aux+mora));
					meses_mostrar.setText(String.valueOf(mesesAtrasados));
					
				}
			}
		});
		btnActualizarCargos.setBounds(222, 41, 124, 19);
		panel.add(btnActualizarCargos);
		
		JLabel lblFechaActual = new JLabel("Fecha actual:");
		lblFechaActual.setBounds(223, 100, 118, 14);
		panel.add(lblFechaActual);
		
		JLabel lblFechaEstimada = new JLabel("Fecha estimada: ");
		lblFechaEstimada.setBounds(222, 129, 119, 14);
		panel.add(lblFechaEstimada);
		
		fecha_ahora = new JLabel("0/0/0");
		fecha_ahora.setBounds(346, 99, 68, 14);
		panel.add(fecha_ahora);
		fecha_ahora.setText(fechaUsandoGregorian);
		
		fecha_simulada = new JLabel("0/0/0");
		fecha_simulada.setBounds(346, 128, 68, 14);
		panel.add(fecha_simulada);
		
		JLabel lblCargosTotalActual = new JLabel("Cargos Total actual: ");
		lblCargosTotalActual.setBounds(183, 182, 135, 14);
		panel.add(lblCargosTotalActual);
		
		JLabel lblMoraDeLos = new JLabel("Mora de los Planes: ");
		lblMoraDeLos.setBounds(183, 207, 135, 14);
		panel.add(lblMoraDeLos);
		
		JLabel lblNuevoCargosTotales = new JLabel("Nuevo Cargos Totales: ");
		lblNuevoCargosTotales.setBounds(183, 232, 131, 14);
		panel.add(lblNuevoCargosTotales);
		
		cargo_actual = new JLabel("0.00");
		cargo_actual.setBounds(347, 182, 46, 14);
		panel.add(cargo_actual);
		
		mora_actual = new JLabel("0.00");
		mora_actual.setBounds(346, 207, 46, 14);
		panel.add(mora_actual);
		
		total_simulado= new JLabel("0.00");
		total_simulado.setBounds(346, 232, 46, 14);
		panel.add(total_simulado);
		
		JLabel lblMesesDeAtraso = new JLabel("Meses de atraso simulado");
		lblMesesDeAtraso.setBounds(183, 257, 158, 14);
		panel.add(lblMesesDeAtraso);
		
		meses_mostrar = new JLabel("0");
		meses_mostrar.setBounds(347, 257, 46, 14);
		panel.add(meses_mostrar);
		
		JButton btnDisminuirMes = new JButton("Disminuir mes");
		btnDisminuirMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mesesAtrasados--;
				actualizarInfo();
			}
		});
		btnDisminuirMes.setBounds(222, 77, 124, 19);
		panel.add(btnDisminuirMes);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	@SuppressWarnings("unchecked")
	protected void actualizarInfo() {
		DefaultListModel<String> db = ((DefaultListModel<String>)main.getModel());
		
		for (int i = 0; i < db.getSize(); i++) {
			String s = (String)db.getElementAt(i);
			String[] separador = s.split("_", 2);
			String costo = separador[1];
			aux = aux - Float.parseFloat(costo);
			mora = mora -(float) ((mesesAtrasados*aux)*0.0314);
			cargo_actual.setText(String.valueOf(Math.abs(aux)));
			mora_actual.setText(String.valueOf(Math.abs(mora)));
			total_simulado.setText(String.valueOf(aux-mora));
			meses_mostrar.setText(String.valueOf(mesesAtrasados));
			
			
		}
		
	}

	protected void updatePlanes() {
		planesToShow.clear();
		for (Plan aux : planesToLoad) {
			planesToShow.addElement(aux.getNombre()+"_"+aux.getCosto());
		}
		
	}

	private int diferenciaMeses() throws IOException, ParseException {
		Calendar inicio = new GregorianCalendar();
		Calendar fin = new GregorianCalendar();
		Date fechaActual = new Date();
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String hoy = formato.format(fechaActual);
			fin.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(fecha_simulada.getText()));
			inicio.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(hoy));
			int diffYear = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
			int diffM = diffYear * 12 + fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH);
		
		return diffM;
	}
	private String formato() {
		String mes = "";
		String dias = "";
		String year = "";
		String fecha;
		mes = String.valueOf(calendario.get(GregorianCalendar.MONTH)+1);
		dias = String.valueOf(calendario.get(GregorianCalendar.DAY_OF_MONTH));
		year = String.valueOf(calendario.get(GregorianCalendar.YEAR));
		fecha = dias+"/"+mes+"/"+year;
		return fecha;
	}
}
