package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Controladora;
import logico.Personal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;
import java.awt.Color;

public class RegistrarUsuario extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtnombre;
	private JTextField txtapellido;
	private JTextField txtuser;
	private JTextField txtpass;
	private JComboBox cbxtipo;
	private JComboBox cbxoficina;
	private Date fechaActual;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarUsuario.class.getResource("/recursos/check-list.png")));
		setTitle("Registrar Usuario");
		setResizable(false);
		setBounds(100, 100, 450, 299);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 248, 255));
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
		
		cbxtipo = new JComboBox();
		cbxtipo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione el tipo de cuenta>", "Comercial", "Administrativo"}));
		cbxtipo.setBounds(137, 143, 141, 21);
		contentPanel.add(cbxtipo);
		
		JLabel lblOficina = new JLabel("Oficina:");
		lblOficina.setBounds(10, 191, 62, 13);
		contentPanel.add(lblOficina);
		
		cbxoficina = new JComboBox();
		cbxoficina.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione la oficina del empleado>", "Las Colinas", "Calle del Sol", "Padre de las Casas"}));
		cbxoficina.setBounds(137, 187, 141, 21);
		contentPanel.add(cbxoficina);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(240, 248, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnregistrar = new JButton("Registrar");
				btnregistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Personal empleado = new Personal(txtnombre.getText(), txtapellido.getText(), cbxoficina.getSelectedItem().toString(), cbxtipo.getSelectedItem().toString(), txtuser.getText(), txtpass.getText());
						Controladora.getInstance().registrarUsuario(empleado);
						JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
						clean();
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
		txtnombre.setText("");
		txtapellido.setText("");
		txtuser.setText("");
		txtpass.setText("");
		cbxtipo.setSelectedIndex(0);
		cbxoficina.setSelectedIndex(0);
	}
}
