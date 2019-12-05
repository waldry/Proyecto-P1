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
import logico.Factura;

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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Toolkit;

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
	private JLabel cambio;
	private JLabel total_para_pagar;
	private Factura auxFact;
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
	@SuppressWarnings("unchecked")
	public PagoFactura() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PagoFactura.class.getResource("/recursos/expediente.png")));
		setTitle("Pago de Factura");
		setResizable(false);
		setBounds(100, 100, 714, 368);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 248, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Informaci\u00F3n de Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(10, 10, 331, 204);
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
						JOptionPane.showMessageDialog(null, "Cédula inválida. Por favor escriba la cédula correctamente", "Notificación", JOptionPane.INFORMATION_MESSAGE);
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
		panel_1.setBounds(351, 10, 336, 204);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		contratos = new JList<>(idContratos);
		contratos.setBounds(10, 25, 316, 134);
		panel_1.add(contratos);
		
		JButton btnPasarACaja = new JButton("Pasar a Caja");
		btnPasarACaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int posicion : contratos.getSelectedIndices()) {
					String linea = idContratos.getElementAt(posicion);
					for (Factura jodete : Controladora.getInstance().getFacturas()) {
						auxFact = jodete;
						if (linea.equalsIgnoreCase(jodete.getId())) {
							total_para_pagar.setText(String.valueOf(jodete.getMonto()));
						}
					}
				}
			}
		});
		btnPasarACaja.setBounds(10, 170, 115, 23);
		panel_1.add(btnPasarACaja);
		
		total_para_pagar = new JLabel("0.00");
		total_para_pagar.setBounds(115, 232, 46, 14);
		contentPanel.add(total_para_pagar);
		
		JLabel lblValorAPagar = new JLabel("Valor a pagar: ");
		lblValorAPagar.setBounds(23, 232, 89, 14);
		contentPanel.add(lblValorAPagar);
		
		JLabel lblMontoRecibido = new JLabel("Monto recibido: ");
		lblMontoRecibido.setBounds(23, 252, 84, 14);
		contentPanel.add(lblMontoRecibido);
		
		JLabel lblCambio = new JLabel("Cambio: ");
		lblCambio.setBounds(23, 277, 59, 14);
		contentPanel.add(lblCambio);
		
		cambio = new JLabel("0.00");
		cambio.setBounds(115, 277, 46, 14);
		contentPanel.add(cambio);
		
		JSpinner monto_recibido = new JSpinner();
		monto_recibido.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		monto_recibido.setBounds(115, 250, 59, 17);
		contentPanel.add(monto_recibido);
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
						float aux = Float.parseFloat(total_para_pagar.getText());
						int recibido = (Integer)monto_recibido.getValue();
						float diff = recibido-aux;
						if (recibido >= auxFact.getMonto()) {
							for (Factura lol : Controladora.getInstance().getFacturas()) {
								if (lol.getId().equalsIgnoreCase(auxFact.getId())) {
									lol.setActiva(false);
									lol.setMonto(0);
									JOptionPane.showMessageDialog(null, "Factura pagada exitosamente", "Notificación", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
						cambio.setText(String.valueOf(diff));
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
				idContratos.addElement(contract.getId());
			}
		}
	}
}
