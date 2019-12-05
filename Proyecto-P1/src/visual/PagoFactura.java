package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import logico.Cliente;
import logico.Contrato;
import logico.Controladora;

import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class PagoFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private DefaultListModel<String> idContratos = new DefaultListModel<String>();
	private JList<String> contratos;
	private JFormattedTextField ftxtCedula;
	private Cliente clientelito;
	private MaskFormatter mascara() {
		MaskFormatter mascara = new MaskFormatter();
		try {
			mascara = new MaskFormatter("###-#######-#");
			mascara.setPlaceholderCharacter('_');
			} catch(Exception ex){
			ex.printStackTrace();
		}
		return mascara;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PagoFactura dialog = new PagoFactura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PagoFactura() {
		setTitle("Pago de Factura");
		setResizable(false);
		setBounds(100, 100, 633, 276);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 248, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Informaci\u00F3n de Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(10, 10, 331, 189);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCdula = new JLabel("C\u00E9dula:");
		lblCdula.setBounds(10, 20, 46, 13);
		panel.add(lblCdula);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 53, 56, 13);
		panel.add(lblNombre);
		
		ftxtCedula = new JFormattedTextField(mascara());
		ftxtCedula.setBounds(76, 15, 127, 19);
		panel.add(ftxtCedula);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(10, 86, 56, 13);
		panel.add(lblApellido);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(10, 119, 62, 13);
		panel.add(lblDireccin);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(10, 152, 56, 13);
		panel.add(lblTelfono);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(76, 49, 127, 19);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(76, 83, 127, 19);
		panel.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(76, 117, 127, 19);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(76, 151, 127, 19);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Cliente client : Controladora.getInstance().getClientes()) {
					if(ftxtCedula.getText().equals(client.getCedula())) {
						JOptionPane.showMessageDialog(null, "Cliente existente", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
						clientelito = client;
						txtNombre.setText(client.getNombre());
						txtApellido.setText(client.getApellido());
						txtDireccion.setText(client.getDireccion());
						txtTelefono.setText(client.getTelefono());
						txtNombre.setEditable(false);
						txtApellido.setEditable(false);
						txtDireccion.setEditable(false);
						txtTelefono.setEditable(false);
						updateContratos();
					}else {
						JOptionPane.showMessageDialog(null, "C�dula inv�lida. Por favor escriba la c�dula correctamente", "Notificaci�n", JOptionPane.INFORMATION_MESSAGE);
						ftxtCedula.setText("");
					}
				}
			}
		});
		btnBuscar.setIcon(new ImageIcon(PagoFactura.class.getResource("/recursos/lupa16.png")));
		btnBuscar.setBounds(213, 14, 108, 21);
		panel.add(btnBuscar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Contrato(s) del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_1.setBounds(351, 10, 268, 189);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		contratos = new JList<>(idContratos);
		contratos.setEnabled(false);
		contratos.setBounds(10, 25, 246, 154);
		panel_1.add(contratos);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(240, 248, 255));
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnPago = new JButton("Pagar");
				btnPago.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnPago.setActionCommand("OK");
				buttonPane.add(btnPago);
				getRootPane().setDefaultButton(btnPago);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		updateContratos();
	}
	public void updateContratos() {
		idContratos.clear();
		for (Contrato contract : Controladora.getInstance().getContratos()) {
			if(contract.getClient() == clientelito) {
				idContratos.addElement(contract.getId()+". Precio: "+contract.getTotal());
			}
		}
	}
}
