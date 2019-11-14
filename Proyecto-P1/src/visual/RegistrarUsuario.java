package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegistrarUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtnombre;
	private JTextField txtapellido;
	private JTextField txtuser;
	private JTextField txtpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarUsuario dialog = new RegistrarUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarUsuario() {
		setTitle("Registrar Usuario");
		setResizable(false);
		setBounds(100, 100, 450, 253);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Registro de informaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(10, 22, 56, 13);
			contentPanel.add(lblNombre);
		}
		{
			JLabel lblApellido = new JLabel("Apellido:");
			lblApellido.setBounds(227, 22, 56, 13);
			contentPanel.add(lblApellido);
		}
		{
			JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
			lblNombreDeUsuario.setBounds(10, 67, 117, 13);
			contentPanel.add(lblNombreDeUsuario);
		}
		{
			JLabel lblConstrasea = new JLabel("Constrase\u00F1a:");
			lblConstrasea.setBounds(10, 107, 76, 13);
			contentPanel.add(lblConstrasea);
		}
		
		txtnombre = new JTextField();
		txtnombre.setBounds(76, 19, 141, 19);
		contentPanel.add(txtnombre);
		txtnombre.setColumns(10);
		
		txtapellido = new JTextField();
		txtapellido.setBounds(293, 19, 141, 19);
		contentPanel.add(txtapellido);
		txtapellido.setColumns(10);
		{
			txtuser = new JTextField();
			txtuser.setBounds(137, 64, 299, 19);
			contentPanel.add(txtuser);
			txtuser.setColumns(10);
		}
		{
			txtpass = new JTextField();
			txtpass.setBounds(138, 104, 296, 19);
			contentPanel.add(txtpass);
			txtpass.setColumns(10);
		}
		{
			JLabel lblTipo = new JLabel("Tipo:");
			lblTipo.setBounds(10, 147, 46, 13);
			contentPanel.add(lblTipo);
		}
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione el tipo de cuenta>", "Comercial", "Administrativo"}));
		comboBox.setBounds(137, 143, 141, 21);
		contentPanel.add(comboBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnregistrar = new JButton("Registrar");
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
}
