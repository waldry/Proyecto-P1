package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Controladora;
import logico.Personal;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JTextField;
import java.awt.Toolkit;

public class LoginProject extends JDialog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private TextField txtUser;
	private TextField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream tricom;
				FileOutputStream empresa2;
				ObjectInputStream empresaRead;
				ObjectOutputStream empresaWrite;
				try {
					tricom = new FileInputStream ("empresa.dat");
					empresaRead = new ObjectInputStream(tricom);
					Controladora temp = (Controladora)empresaRead.readObject();
					Controladora.setCont(temp);
					tricom.close();
					empresaRead.close();
				} catch (FileNotFoundException e) {
					try {
						empresa2 = new  FileOutputStream("empresa.dat");
						empresaWrite = new ObjectOutputStream(empresa2);
						Personal aux = new Personal("Ad", "min", "Central", "Administrativo", "Admin", "Admin");
						Controladora.getInstance().registrarUsuario(aux);
						empresaWrite.writeObject(Controladora.getInstance());
						empresa2.close();
						empresaWrite.close();
					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				} catch (IOException e) {
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					LoginProject dialog = new LoginProject();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public LoginProject() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginProject.class.getResource("/recursos/formato-de-archivo-de-registro.png")));
		setTitle("Login");
		setResizable(false);
		setBounds(100, 100, 479, 215);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(LoginProject.class.getResource("/recursos/Logo_de_Tricom.png")));
		label.setBounds(-25, 22, 202, 133);
		contentPanel.add(label);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(187, 54, 77, 13);
		contentPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(187, 99, 77, 13);
		contentPanel.add(lblPassword);
		
		txtUser = new TextField();
		txtUser.setBounds(274, 51, 176, 19);
		contentPanel.add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new TextField();
		txtPass.setEchoChar('*');
		txtPass.setBounds(274, 96, 176, 19);
		contentPanel.add(txtPass);
		txtPass.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAcceder = new JButton("Acceder");
				btnAcceder.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(Controladora.getInstance().confirmLogin(txtUser.getText(), txtPass.getText())) {
							Controladora.setLoggedUser(Controladora.getInstance().getUsuario(txtUser.getText(), txtPass.getText()));
							JOptionPane.showMessageDialog(null, "Ha accedido con �xito", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
							Principal frame = new Principal();
							
							dispose();
							frame.setVisible(true);
						}else {
							JOptionPane.showMessageDialog(null, "Favor digitar datos nuevamente de forma correcta", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
							txtUser.setText("");
							txtPass.setText("");
						}
					}
				});
				btnAcceder.setActionCommand("OK");
				buttonPane.add(btnAcceder);
				getRootPane().setDefaultButton(btnAcceder);
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
	}
}
