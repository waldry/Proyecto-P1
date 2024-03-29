package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import logico.Cliente;
import logico.Controladora;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.Serializable;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Toolkit;

public class RegistrarCliente extends JDialog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtnombre;
	private JTextField txtapellido;
	private JTextField txtdireccion;
	private JFormattedTextField ftxtcedula = new JFormattedTextField();
	private JFormattedTextField ftxttelefono = new JFormattedTextField();
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
			RegistrarCliente dialog = new RegistrarCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			MaskFormatter mascara = new MaskFormatter("###-#######-#");
			mascara.setPlaceholderCharacter('_');
//			dialog.setLayout(new FlowLayout());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarCliente.class.getResource("/recursos/check-list.png")));
		setTitle("Registro de Cliente");
		setResizable(false);
		setBounds(100, 100, 375, 292);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 248, 255));
		contentPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registro de Informaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCdula = new JLabel("C\u00E9dula:");
		lblCdula.setBounds(10, 28, 54, 13);
		contentPanel.add(lblCdula);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 69, 54, 13);
		contentPanel.add(lblNombre);
		{
			JLabel lblApellido = new JLabel("Apellido:");
			lblApellido.setBounds(10, 110, 54, 13);
			contentPanel.add(lblApellido);
		}
		{
			JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
			lblDireccin.setBounds(10, 151, 64, 13);
			contentPanel.add(lblDireccin);
		}
		{
			JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
			lblTelfono.setBounds(10, 192, 54, 13);
			contentPanel.add(lblTelfono);
		}
		
		txtnombre = new JTextField();
		txtnombre.setBounds(84, 65, 152, 19);
		contentPanel.add(txtnombre);
		txtnombre.setColumns(10);
		
		txtapellido = new JTextField();
		txtapellido.setBounds(84, 107, 152, 19);
		contentPanel.add(txtapellido);
		txtapellido.setColumns(10);
		
		txtdireccion = new JTextField();
		txtdireccion.setBounds(84, 149, 262, 19);
		contentPanel.add(txtdireccion);
		txtdireccion.setColumns(10);
		
		ftxtcedula = new JFormattedTextField(mascara());
		ftxtcedula.setBounds(84, 23, 152, 19);
		contentPanel.add(ftxtcedula);
		
		ftxttelefono = new JFormattedTextField(mascaraTel());
		ftxttelefono.setBounds(84, 191, 152, 19);
		contentPanel.add(ftxttelefono);
		setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(240, 248, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnregistrar = new JButton("Registrar");
				btnregistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(ftxtcedula.getText().equals("___-_______-_") || txtnombre.getText().isEmpty() || txtapellido.getText().isEmpty() || txtdireccion.getText().isEmpty() || ftxttelefono.getText().equals("(___)-___-____")) {
							JOptionPane.showMessageDialog(null, "Cliente no registrado. Faltan datos", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
						}else {
							Cliente client = new Cliente(ftxtcedula.getText(), txtnombre.getText(), txtapellido.getText(), txtdireccion.getText(), ftxttelefono.getText(), true);
							Controladora.getInstance().registrarCliente(client);
							JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
							clean();
						}
					}
				});
				btnregistrar.setActionCommand("OK");
				buttonPane.add(btnregistrar);
				getRootPane().setDefaultButton(btnregistrar);
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
	public void clean() {
		ftxtcedula.setText("");
		txtapellido.setText("");
		txtnombre.setText("");
		ftxttelefono.setText("");
		txtdireccion.setText("");
		
	}
}
