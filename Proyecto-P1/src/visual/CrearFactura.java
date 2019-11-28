package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import logico.Cliente;
import logico.Controladora;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.ImageIcon;

public class CrearFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtnombre;
	private JTextField txtapellido;
	private JFormattedTextField ftxtcedula;
	private JTextField txtdireccion;
	private JFormattedTextField ftxtTel;

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
	private MaskFormatter mascaraTel() {
		MaskFormatter mask = new MaskFormatter();
		try {
			mask = new MaskFormatter("(###)-###-####");
			mask.setPlaceholderCharacter('_');
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mask;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearFactura dialog = new CrearFactura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearFactura() {
		setResizable(false);
		setTitle("Facturaci\u00F3n");
		setBounds(100, 100, 668, 424);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Datos del cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(10, 10, 634, 124);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCdula = new JLabel("C\u00E9dula:");
		lblCdula.setBounds(10, 21, 46, 13);
		panel.add(lblCdula);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 55, 56, 13);
		panel.add(lblNombre);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(222, 55, 61, 13);
		panel.add(lblDireccin);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(222, 89, 56, 13);
		panel.add(lblTelfono);
		
		ftxtcedula = new JFormattedTextField(mascara());
		ftxtcedula.setBounds(76, 18, 136, 19);
		panel.add(ftxtcedula);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(76, 52, 136, 19);
		panel.add(txtnombre);
		txtnombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(10, 89, 56, 13);
		panel.add(lblApellido);
		
		txtapellido = new JTextField();
		txtapellido.setBounds(76, 86, 136, 19);
		panel.add(txtapellido);
		txtapellido.setColumns(10);
		
		txtdireccion = new JTextField();
		txtdireccion.setBounds(293, 52, 300, 19);
		panel.add(txtdireccion);
		txtdireccion.setColumns(10);
		
		ftxtTel = new JFormattedTextField(mascaraTel());
		ftxtTel.setBounds(293, 86, 136, 19);
		panel.add(ftxtTel);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(CrearFactura.class.getResource("/recursos/lupa16.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente aux = Controladora.getInstance().findClienteById(ftxtcedula.getText());
				if(aux == null) {
					JOptionPane.showMessageDialog(null, "Este cliente no existe. Favor de escribir una cédula de cliente válida.", "Notificación", JOptionPane.INFORMATION_MESSAGE);
					ftxtcedula.setText("");
				}else {
					JOptionPane.showMessageDialog(null, "Cliente Encontrado", "Notificación", JOptionPane.INFORMATION_MESSAGE);
					txtnombre.setText(aux.getNombre());
					txtapellido.setText(aux.getApellido());
					txtdireccion.setText(aux.getDireccion());
					ftxtTel.setText(aux.getTelefono());
				}
				
			}
		});
		btnBuscar.setBounds(222, 17, 85, 21);
		panel.add(btnBuscar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnfacturar = new JButton("Facturar");
				btnfacturar.setActionCommand("OK");
				buttonPane.add(btnfacturar);
				getRootPane().setDefaultButton(btnfacturar);
			}
			{
				JButton btncancelar = new JButton("Cancelar");
				btncancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btncancelar.setActionCommand("Cancel");
				buttonPane.add(btncancelar);
			}
		}
	}
}
