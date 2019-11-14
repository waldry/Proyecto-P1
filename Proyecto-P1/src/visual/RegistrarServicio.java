package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;

public class RegistrarServicio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField name_txt;
	private JTextField price_txt;
	private JPanel internet_panel;
	private JPanel telefono_panel;
	private JPanel cable_panel;
	private JRadioButton rdbtnCable;
	private JRadioButton rdbtnInternet;
	private JRadioButton rdbtnTelefono;
	private JRadioButton rdbtnMiscelaneos;
	private JSpinner canales_spn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarServicio dialog = new RegistrarServicio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarServicio() {
		setTitle("Registro de Servicio");
		setBounds(100, 100, 499, 305);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Informacion del Servicio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNombre = new JLabel("Nombre: ");
			lblNombre.setBounds(10, 31, 86, 14);
			panel.add(lblNombre);
			
			JLabel lblCosto = new JLabel("Costo: ");
			lblCosto.setBounds(170, 31, 46, 14);
			panel.add(lblCosto);
			
			name_txt = new JTextField();
			name_txt.setBounds(60, 28, 86, 20);
			panel.add(name_txt);
			name_txt.setColumns(10);
			
			price_txt = new JTextField();
			price_txt.setBounds(207, 28, 86, 20);
			panel.add(price_txt);
			price_txt.setColumns(10);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "Tipo de Servicio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(10, 56, 451, 45);
			panel.add(panel_1);
			panel_1.setLayout(null);
			ButtonGroup bg = new ButtonGroup();
			
			rdbtnInternet = new JRadioButton("Internet");
			rdbtnInternet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnInternet.setSelected(true);
					rdbtnCable.setSelected(false);
					rdbtnTelefono.setSelected(false);
					rdbtnMiscelaneos.setSelected(false);
					telefono_panel.setVisible(false);
					cable_panel.setVisible(false);
					internet_panel.setVisible(true);
				}
			});
			rdbtnInternet.setBounds(3, 15, 109, 23);
			panel_1.add(rdbtnInternet);
			
			rdbtnCable = new JRadioButton("Cable");
			rdbtnCable.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnCable.setSelected(true);
					rdbtnInternet.setSelected(false);
					rdbtnTelefono.setSelected(false);
					rdbtnMiscelaneos.setSelected(false);
					internet_panel.setVisible(false);
					telefono_panel.setVisible(false);
					cable_panel.setVisible(true);
				}
			});
			rdbtnCable.setBounds(115, 15, 109, 23);
			panel_1.add(rdbtnCable);
			 
			rdbtnTelefono = new JRadioButton("Telefono");
			rdbtnTelefono.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnTelefono.setSelected(true);
					rdbtnCable.setSelected(false);
					rdbtnInternet.setSelected(false);
					rdbtnMiscelaneos.setSelected(false);
					internet_panel.setVisible(false);
					cable_panel.setVisible(false);
					telefono_panel.setVisible(true);
				}
			});
			rdbtnTelefono.setBounds(227, 15, 109, 23);
			panel_1.add(rdbtnTelefono);
			
			
			
			rdbtnMiscelaneos = new JRadioButton("Miscelaneos");
			rdbtnMiscelaneos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnMiscelaneos.setSelected(true);
					rdbtnInternet.setSelected(false);
					rdbtnCable.setSelected(false);
					rdbtnTelefono.setSelected(false);
					internet_panel.setVisible(false);
					cable_panel.setVisible(false);
					telefono_panel.setVisible(false);
				}
			});
			rdbtnMiscelaneos.setBounds(339, 15, 109, 23);
			panel_1.add(rdbtnMiscelaneos);
			bg.add(rdbtnInternet);
			bg.add(rdbtnCable);
			bg.add(rdbtnTelefono);
			bg.add(rdbtnMiscelaneos);
			rdbtnInternet.setSelected(true);
			 
			internet_panel = new JPanel();
			internet_panel.setBorder(new TitledBorder(null, "Internet", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			internet_panel.setBounds(10, 101, 451, 106);
			panel.add(internet_panel);
			internet_panel.setLayout(null);
			
			JLabel lblAnchoDeBanda = new JLabel("Ancho de banda Subida");
			lblAnchoDeBanda.setBounds(10, 27, 145, 14);
			internet_panel.add(lblAnchoDeBanda);
			
			JLabel lblAnchoDeBanda_1 = new JLabel("Ancho de banda Bajada");
			lblAnchoDeBanda_1.setBounds(10, 64, 145, 14);
			internet_panel.add(lblAnchoDeBanda_1);
			
			JSpinner subida_spn = new JSpinner();
			subida_spn.setBounds(165, 27, 29, 20);
			internet_panel.add(subida_spn);
			
			JSpinner bajada_spn = new JSpinner();
			bajada_spn.setBounds(165, 64, 29, 20);
			internet_panel.add(bajada_spn);
			
			cable_panel = new JPanel();
			cable_panel.setBounds(10, 101, 451, 106);
			panel.add(cable_panel);
			cable_panel.setLayout(null);
			cable_panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cable", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			cable_panel.setVisible(false);
			
			JLabel lblCantidadCanales = new JLabel("Cantidad Canales: ");
			lblCantidadCanales.setBounds(10, 27, 114, 14);
			cable_panel.add(lblCantidadCanales);
			
			canales_spn = new JSpinner();
			canales_spn.setModel(new SpinnerNumberModel(new Integer(100), null, null, new Integer(1)));
			canales_spn.setBounds(138, 24, 68, 20);
			cable_panel.add(canales_spn);
			
			JCheckBox adultos_chbx = new JCheckBox("Paquete +18");
			adultos_chbx.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if (adultos_chbx.isSelected()) {
						int cant_actual = (int) canales_spn.getValue() + 10;
						canales_spn.setValue(Integer.valueOf(cant_actual));
					}
					else {
						int cant_actual = (int) canales_spn.getValue() - 10;
						canales_spn.setValue(Integer.valueOf(cant_actual));
					}
					
				}
			});
			adultos_chbx.setBounds(16, 59, 114, 23);
			cable_panel.add(adultos_chbx);
			
			JCheckBox hbo_chbx = new JCheckBox("Paquete HBO");
			hbo_chbx.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (hbo_chbx.isSelected()) {
						int cant_actual = (int) canales_spn.getValue() + 7;
						canales_spn.setValue(Integer.valueOf(cant_actual));
					}
					else {
						int cant_actual = (int) canales_spn.getValue() - 7;
						canales_spn.setValue(Integer.valueOf(cant_actual));
					}
				}
			});
			hbo_chbx.setBounds(146, 59, 124, 23);
			cable_panel.add(hbo_chbx);
			
			JCheckBox deportes_chbx = new JCheckBox("Paquete Deportes");
			deportes_chbx.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (deportes_chbx.isSelected()) {
						int cant_actual = (int) canales_spn.getValue() + 15;
						canales_spn.setValue(Integer.valueOf(cant_actual));
					}
					else {
						int cant_actual = (int) canales_spn.getValue() - 15;
						canales_spn.setValue(Integer.valueOf(cant_actual));
					}
				}
			});
			deportes_chbx.setBounds(286, 59, 147, 23);
			cable_panel.add(deportes_chbx);
			
			telefono_panel = new JPanel();
			telefono_panel.setBounds(10, 101, 451, 106);
			panel.add(telefono_panel);
			telefono_panel.setLayout(null);
			telefono_panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Telefono", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			telefono_panel.setVisible(false);
			
			JLabel lblCantidadCanales_1 = new JLabel("Cantidad Minutos: ");
			lblCantidadCanales_1.setBounds(10, 27, 130, 14);
			telefono_panel.add(lblCantidadCanales_1);
			
			JSpinner cant_min_spn = new JSpinner();
			cant_min_spn.setBounds(138, 24, 29, 20);
			telefono_panel.add(cant_min_spn);
			
			JCheckBox voicemail_chbx = new JCheckBox("Correo de Voz");
			
			voicemail_chbx.setBounds(32, 61, 127, 23);
			telefono_panel.add(voicemail_chbx);
			
			JCheckBox doble_linea_chbx = new JCheckBox("Doble Linea");
			doble_linea_chbx.setBounds(191, 61, 97, 23);
			telefono_panel.add(doble_linea_chbx);
			
			JCheckBox ilimitado_chbx = new JCheckBox("Ilimitado");
			ilimitado_chbx.setBounds(320, 61, 97, 23);
			telefono_panel.add(ilimitado_chbx);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton send_btn = new JButton("Registrar");
				send_btn.setActionCommand("OK");
				buttonPane.add(send_btn);
				getRootPane().setDefaultButton(send_btn);
			}
			{
				JButton cancel_btn = new JButton("Cancelar");
				cancel_btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancel_btn.setActionCommand("Cancel");
				buttonPane.add(cancel_btn);
			}
		}
	}
}
