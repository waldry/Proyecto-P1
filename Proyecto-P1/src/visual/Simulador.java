package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import logico.Controladora;
import logico.Factura;

import javax.swing.JList;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

public class Simulador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField entry;
	private JTextField textField_1;
	private JLabel fecha_ahora;
	private Calendar calendario = new GregorianCalendar();
	private JLabel fecha_simulada;

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
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Factura aux : Controladora.getInstance().getFacturas()) {
					if (entry.getText().equalsIgnoreCase(aux.getId())) {
						
					}
				}
			}
		});
		btnBuscar.setBounds(182, 18, 89, 23);
		contentPanel.add(btnBuscar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informacion - Contrato", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 84, 424, 287);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente: ");
		lblCliente.setBounds(10, 33, 59, 14);
		panel.add(lblCliente);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(64, 30, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JList list = new JList();
		list.setEnabled(false);
		list.setBounds(10, 80, 151, 196);
		panel.add(list);
		
		JLabel lblServicios = new JLabel("Servicios");
		lblServicios.setBounds(62, 61, 66, 14);
		panel.add(lblServicios);
		
		JButton btnActualizarCargos = new JButton("Actualizar Cargos");
		btnActualizarCargos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				calendario.add(Calendar.DATE, 30);
				DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				fecha_simulada.setText(formato.format(calendario.getTime()));
				int mesesAtrasados = 0;
				try {
					mesesAtrasados = diferenciaMeses();
				} catch (IOException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(mesesAtrasados);
			}
		});
		btnActualizarCargos.setBounds(222, 41, 157, 31);
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
