package visual;

import java.awt.BorderLayout;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Controladora;
import logico.Plan;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;

import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;
import java.awt.Toolkit;

public class RegistrarServicio extends JDialog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField name_txt;
	private JTextField ID_txt;
	private JPanel internet_panel;
	private JPanel telefono_panel;
	private JPanel cable_panel;
	private JSpinner canales_spn;
	private JCheckBox internet_chbx;
	private JCheckBox cable_chbx;
	private JCheckBox telefono_chbx;
	private JSpinner subida_spn;
	private JSpinner bajada_spn;
	private JLabel lblAnchoDeBanda;
	private JLabel lblAnchoDeBajada;
	private JSpinner cant_min_spn;
	private JLabel lblCantidadCanales;
	private JCheckBox adultos_chbx;
	private JCheckBox hbo_chbx;
	private JCheckBox deportes_chbx;
	private JLabel lblCantidadCanales_1;
	private JCheckBox voicemail_chbx;
	private JCheckBox doble_linea_chbx;
	private JCheckBox ilimitado_chbx;
	private Plan item = null;
	private String codigoPlan;
	
	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.
	 * @param aux 
	 * @param aux 
	 */
	public RegistrarServicio(Plan aux) {
		setTitle("Registro de Plan");
		this.item = aux;
		if (item == null) {
			setTitle("Registrar Plan");
		}
		else {
			setTitle("Modificar: " +item.getNombre());
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarServicio.class.getResource("/recursos/check-list.png")));
		setResizable(false);
		
		setBounds(100, 100, 500, 540);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 248, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		{

			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(240, 248, 255));
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Informacion del Servicio", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNombre = new JLabel("Nombre: ");
			lblNombre.setBounds(10, 31, 57, 14);
			panel.add(lblNombre);
			
			JLabel lblId = new JLabel("Id:");
			lblId.setBounds(358, 31, 29, 14);
			panel.add(lblId);
			
			name_txt = new JTextField();
			name_txt.setBounds(77, 29, 148, 20);
			panel.add(name_txt);
			name_txt.setColumns(10);
			
			ID_txt = new JTextField();
			ID_txt.setEditable(false);
			ID_txt.setBounds(404, 29, 57, 20);
			panel.add(ID_txt);
			ID_txt.setColumns(10);
			ID_txt.setText("P-"+Controladora.getInstance().getGenCodPlan());
			
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(new Color(240, 248, 255));
			panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Tipo de Servicio", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
			panel_1.setBounds(10, 56, 451, 45);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			
			
			internet_chbx = new JCheckBox("Internet");
			internet_chbx.setBackground(new Color(240, 248, 255));
			internet_chbx.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (internet_chbx.isSelected()) {
						internet_panel.setEnabled(true);
						subida_spn.setEnabled(true);
						bajada_spn.setEnabled(true);
						lblAnchoDeBajada.setEnabled(true);
						lblAnchoDeBanda.setEnabled(true);
						
						
					}else {
						internet_panel.setEnabled(false);
						subida_spn.setEnabled(false);
						bajada_spn.setEnabled(false);
						lblAnchoDeBajada.setEnabled(false);
						lblAnchoDeBanda.setEnabled(false);
						subida_spn.setValue(0);
						bajada_spn.setValue(0);
					}
				}
			});
			internet_chbx.setBounds(40, 15, 97, 23);
			panel_1.add(internet_chbx);
			
			cable_chbx = new JCheckBox("Cable");
			cable_chbx.setBackground(new Color(240, 248, 255));
			cable_chbx.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (cable_chbx.isSelected()) {
						cable_panel.setEnabled(true);
						lblCantidadCanales.setEnabled(true);
						adultos_chbx.setEnabled(true);
						hbo_chbx.setEnabled(true);
						deportes_chbx.setEnabled(true);
						canales_spn.setEnabled(true);
						canales_spn.setValue(100);
					}else {
						cable_panel.setEnabled(false);
						lblCantidadCanales.setEnabled(false);
						adultos_chbx.setEnabled(false);
						adultos_chbx.setSelected(false);
						hbo_chbx.setEnabled(false);
						hbo_chbx.setSelected(false);
						deportes_chbx.setEnabled(false);
						deportes_chbx.setSelected(false);
						canales_spn.setEnabled(false);
						canales_spn.setValue(0);
					}
				}
			});
			cable_chbx.setBounds(177, 15, 97, 23);
			panel_1.add(cable_chbx);
			
			telefono_chbx = new JCheckBox("Telefono");
			telefono_chbx.setBackground(new Color(240, 248, 255));
			telefono_chbx.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (telefono_chbx.isSelected()) {
						telefono_panel.setEnabled(true);
						lblCantidadCanales_1.setEnabled(true);
						cant_min_spn.setEnabled(true);
						doble_linea_chbx.setEnabled(true);
						ilimitado_chbx.setEnabled(true);
						voicemail_chbx.setEnabled(true);
						cant_min_spn.setValue(400);
					}else {
						telefono_panel.setEnabled(false);
						lblCantidadCanales_1.setEnabled(false);
						cant_min_spn.setEnabled(false);
						doble_linea_chbx.setEnabled(false);
						doble_linea_chbx.setSelected(false);
						ilimitado_chbx.setEnabled(false);
						ilimitado_chbx.setSelected(false);
						voicemail_chbx.setEnabled(false);
						voicemail_chbx.setSelected(false);
						cant_min_spn.setValue(0);
					}
				}
			});
			telefono_chbx.setBounds(314, 15, 97, 23);
			panel_1.add(telefono_chbx);
			
			
			internet_panel = new JPanel();
			internet_panel.setBackground(new Color(240, 248, 255));
			internet_chbx.setSelected(true);
			internet_panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Internet", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
			internet_panel.setBounds(10, 101, 451, 106);
			panel.add(internet_panel);
			internet_panel.setLayout(null);
			
			lblAnchoDeBanda = new JLabel("Ancho de banda Subida");
			lblAnchoDeBanda.setBounds(10, 27, 145, 14);
			internet_panel.add(lblAnchoDeBanda);
			
			lblAnchoDeBajada= new JLabel("Ancho de banda Bajada");
			lblAnchoDeBajada.setBounds(10, 64, 145, 14);
			internet_panel.add(lblAnchoDeBajada);
			
			subida_spn = new JSpinner();
			subida_spn.setBounds(165, 27, 68, 20);
			internet_panel.add(subida_spn);
			
			bajada_spn = new JSpinner();
			bajada_spn.setBounds(165, 64, 68, 20);
			internet_panel.add(bajada_spn);
			
			cable_panel = new JPanel();
			cable_panel.setBackground(new Color(240, 248, 255));
			cable_panel.setBounds(10, 218, 451, 106);
			panel.add(cable_panel);
			cable_panel.setLayout(null);
			cable_panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cable", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
			cable_panel.setEnabled(false);
			
			lblCantidadCanales= new JLabel("Cantidad Canales: ");
			lblCantidadCanales.setBounds(10, 27, 114, 14);
			cable_panel.add(lblCantidadCanales);
			lblCantidadCanales.setEnabled(false);
			
			canales_spn = new JSpinner();
			canales_spn.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
			canales_spn.setBounds(138, 24, 68, 20);
			cable_panel.add(canales_spn);
			canales_spn.setEnabled(false);
			
			adultos_chbx= new JCheckBox("Paquete +18");
			adultos_chbx.setBackground(new Color(240, 248, 255));
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
			adultos_chbx.setEnabled(false);
			
			hbo_chbx= new JCheckBox("Paquete HBO");
			hbo_chbx.setBackground(new Color(240, 248, 255));
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
			hbo_chbx.setEnabled(false);
			
			deportes_chbx = new JCheckBox("Paquete Deportes");
			deportes_chbx.setBackground(new Color(240, 248, 255));
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
			deportes_chbx.setEnabled(false);
			
			telefono_panel = new JPanel();
			telefono_panel.setBackground(new Color(240, 248, 255));
			telefono_panel.setBounds(10, 335, 451, 106);
			panel.add(telefono_panel);
			telefono_panel.setLayout(null);
			telefono_panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Telefono", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
			telefono_panel.setEnabled(false);
			
			lblCantidadCanales_1= new JLabel("Cantidad Minutos: ");
			lblCantidadCanales_1.setBounds(10, 27, 130, 14);
			telefono_panel.add(lblCantidadCanales_1);
			lblCantidadCanales_1.setEnabled(false);
			
			cant_min_spn= new JSpinner();
			cant_min_spn.setBounds(138, 24, 68, 20);
			telefono_panel.add(cant_min_spn);
			cant_min_spn.setEnabled(false);
			
			voicemail_chbx = new JCheckBox("Correo de Voz");
			voicemail_chbx.setBackground(new Color(240, 248, 255));
			
			voicemail_chbx.setBounds(32, 61, 127, 23);
			telefono_panel.add(voicemail_chbx);
			voicemail_chbx.setEnabled(false);
			
			doble_linea_chbx = new JCheckBox("Doble Linea");
			doble_linea_chbx.setBackground(new Color(240, 248, 255));
			doble_linea_chbx.setBounds(191, 61, 97, 23);
			telefono_panel.add(doble_linea_chbx);
			doble_linea_chbx.setEnabled(false);
			
			ilimitado_chbx = new JCheckBox("Ilimitado");
			ilimitado_chbx.setBackground(new Color(240, 248, 255));
			ilimitado_chbx.setBounds(320, 61, 97, 23);
			telefono_panel.add(ilimitado_chbx);
			ilimitado_chbx.setEnabled(false);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(240, 248, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton send_btn = new JButton("Registrar");
				send_btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						float aux = 0;
						if (internet_panel.isEnabled()) {
							aux += 1000 + (((int)bajada_spn.getValue()*10) + ((int)subida_spn.getValue()*15));
						}
						if (cable_panel.isEnabled()) {
							aux += 7.5*(int)canales_spn.getValue();
							if(adultos_chbx.isSelected()) {
								aux += 200;
							}
							if(deportes_chbx.isSelected()) {
								aux += 150;
							}
							if(hbo_chbx.isSelected()) {
								aux += 300;
							}
						}
						if (telefono_panel.isEnabled()) {
							aux += 2.5*(int)cant_min_spn.getValue();
							if(doble_linea_chbx.isSelected()) {
								aux += 100;
							}
							if(voicemail_chbx.isSelected()) {
								aux += 75;
							}
							if(ilimitado_chbx.isSelected()) {
								aux += 125;
							}
						}
							Plan planToAdd = new Plan(codigoPlan,name_txt.getText(),(int)bajada_spn.getValue(),(int)subida_spn.getValue(),(int)cant_min_spn.getValue(),(int)canales_spn.getValue(),ilimitado_chbx.isSelected(),voicemail_chbx.isSelected(),doble_linea_chbx.isSelected(),hbo_chbx.isSelected(),adultos_chbx.isSelected(),deportes_chbx.isSelected(), aux);
							Controladora.getInstance().agregarPlan(planToAdd);
							JOptionPane.showMessageDialog(null, "Plan agregado satisfactoriamente.", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
							ID_txt.setText("P-"+Controladora.getInstance().getGenCodPlan());
					}
				});
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
