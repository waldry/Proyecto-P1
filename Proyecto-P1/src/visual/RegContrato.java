package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import logico.Cliente;
import logico.Contrato;
import logico.Controladora;
import logico.Plan;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.awt.print.PrinterIOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;

public class RegContrato extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField cedula_txt;
	private JTextField nombre_txt;
	private JTextField apellido_txt;
	private JTextField address_Txt;
	private JTextField tel_txt;
	private JCheckBox activo_chbx;
	private JList<String> main;
	private JList<String> second;
	private Cliente clientelito;
	private ArrayList<Plan> planesACotizar = new ArrayList<Plan>();
	private ArrayList<String> idPlanes = new ArrayList<String>();
	DefaultListModel<String> dbPlanes = new DefaultListModel<>();
	DefaultListModel<String> planesSelected = new DefaultListModel<String>();
	private JLabel subtotal_lbl;
	private JLabel itbis_lbl;
	private JLabel total_lbl;
	private String idContrato = "C-"+Controladora.getInstance().getGenCodContrato();
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
			RegContrato dialog = new RegContrato();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegContrato() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegContrato.class.getResource("/recursos/check-list.png")));
		setTitle("Registro de contrato");
		setResizable(false);
		setBounds(100, 100, 1038, 702);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 248, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Informacion - Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(20, 11, 368, 221);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JButton btn_search = new JButton("Buscar");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cedula_txt.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Se necesita datos del cliente para poder procesar el contrato.", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					for (Cliente client : Controladora.getInstance().getClientes()) {
						if(cedula_txt.getText().equals(client.getCedula())) {
							clientelito = client;
							JOptionPane.showMessageDialog(null, "Cliente existente", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
							cedula_txt.setEnabled(false);
							cedula_txt.setText(client.getCedula());
							nombre_txt.setEnabled(false);
							nombre_txt.setText(client.getNombre());
							apellido_txt.setEnabled(false);
							apellido_txt.setText(client.getApellido());
							address_Txt.setEnabled(false);
							address_Txt.setText(client.getDireccion());
							tel_txt.setEnabled(false);
							tel_txt.setText(client.getTelefono());
							activo_chbx.setEnabled(false);
							activo_chbx.setSelected(client.isActivo());
						}
						else {
							JOptionPane.showMessageDialog(null, "El cliente no existe. Registrelo", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
							RegistrarCliente newClient = new RegistrarCliente();
							newClient.setModal(true);
							newClient.setVisible(true);
						}
					}
				}
			}
		});
		btn_search.setBounds(263, 14, 89, 23);
		panel.add(btn_search);
		
		JLabel lblNombre = new JLabel("Cedula: ");
		lblNombre.setBounds(20, 18, 58, 14);
		panel.add(lblNombre);
		
		JLabel lblNombre_1 = new JLabel("Nombre: ");
		lblNombre_1.setBounds(20, 50, 58, 14);
		panel.add(lblNombre_1);
		
		JLabel lblApellido = new JLabel("Apellido: ");
		lblApellido.setBounds(20, 82, 58, 14);
		panel.add(lblApellido);
		
		JLabel lblDireccion = new JLabel("Direccion: ");
		lblDireccion.setBounds(20, 114, 66, 14);
		panel.add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono: ");
		lblTelefono.setBounds(20, 146, 58, 14);
		panel.add(lblTelefono);
		
		activo_chbx= new JCheckBox("Activo");
		activo_chbx.setBackground(new Color(240, 248, 255));
		activo_chbx.setBounds(10, 178, 68, 23);
		panel.add(activo_chbx);
		
		cedula_txt = new JFormattedTextField(mascara());
		cedula_txt.setBounds(88, 16, 167, 20);
		panel.add(cedula_txt);
		cedula_txt.setColumns(10);
		
		nombre_txt = new JTextField();
		nombre_txt.setBounds(88, 48, 167, 20);
		panel.add(nombre_txt);
		nombre_txt.setColumns(10);
		
		apellido_txt = new JTextField();
		apellido_txt.setBounds(88, 80, 167, 20);
		panel.add(apellido_txt);
		apellido_txt.setColumns(10);
		
		address_Txt = new JTextField();
		address_Txt.setBounds(88, 112, 167, 20);
		panel.add(address_Txt);
		address_Txt.setColumns(10);
		
		tel_txt = new JTextField();
		tel_txt.setBounds(88, 144, 167, 20);
		panel.add(tel_txt);
		tel_txt.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Informacion - Plan", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_1.setBounds(20, 243, 992, 376);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		main = new JList<>(dbPlanes);
		main.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		main.setBounds(86, 50, 268, 315);
		panel_1.add(main);
		updatePlanes();
		
		JLabel lblPlanesDisponibles = new JLabel("Planes Disponibles");
		lblPlanesDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPlanesDisponibles.setBounds(151, 22, 139, 30);
		panel_1.add(lblPlanesDisponibles);
		
		JButton agregar_btn = new JButton("->");
		agregar_btn.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<String> db = ((DefaultListModel<String>)main.getModel());
				DefaultListModel<String> carrito = ((DefaultListModel<String>)second.getModel());
				for (int item : main.getSelectedIndices()) {
					carrito.addElement(db.getElementAt(item));
					db.removeElement(db.getElementAt(item));
					float aux = 0;
					for(int i = 0; i<carrito.getSize(); i++) {
					String s = (String)carrito.getElementAt(i);
					String[] separador = s.split("_", 5);
					String id = separador[0];
					String costo = separador[2];
					idPlanes.add(id);
					aux = aux+Float.parseFloat(costo); 
					subtotal_lbl.setText(String.valueOf(aux));
					itbis_lbl.setText(String.valueOf(aux*0.28));
					total_lbl.setText(String.valueOf(aux+(0.28*aux)));
					}
				}
			}
		});
		agregar_btn.setFont(new Font("Tahoma", Font.BOLD, 20));
		agregar_btn.setBounds(440, 84, 111, 49);
		panel_1.add(agregar_btn);
		
		JButton eliminar_btn = new JButton("<-");
		eliminar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<String> db = ((DefaultListModel<String>)main.getModel());
				DefaultListModel<String> carrito = ((DefaultListModel<String>)second.getModel());
				for (int item : second.getSelectedIndices()) {
					db.addElement(carrito.getElementAt(item));
					carrito.removeElement(carrito.getElementAt(item));
					float aux = 0;
					for (int i = 0; i < carrito.getSize(); i++) {
						String s = (String)carrito.getElementAt(i);
						String[] separador = s.split("_", 5);
						String id = separador[0];
						String costo = separador[2];
						idPlanes.remove(id);
						aux = aux-Float.parseFloat(costo);
						subtotal_lbl.setText(String.valueOf(Math.abs(aux)));
						
					}
				}
				if (second.getModel().getSize()==0) {
					subtotal_lbl.setText("0.00");
				}
			}
		});
		eliminar_btn.setFont(new Font("Tahoma", Font.BOLD, 20));
		eliminar_btn.setBounds(440, 258, 111, 49);
		panel_1.add(eliminar_btn);
		
		second= new JList<>(planesSelected);
		second.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		second.setBounds(637, 50, 268, 315);
		panel_1.add(second);
		
		JLabel lblPlanesEnEl = new JLabel("Planes en el Contrato");
		lblPlanesEnEl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPlanesEnEl.setBounds(685, 22, 173, 30);
		panel_1.add(lblPlanesEnEl);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 248, 255));
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Importes del Contrato", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_2.setBounds(398, 11, 449, 86);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setBounds(19, 34, 56, 14);
		panel_2.add(lblSubtotal);
		
		JLabel lblItbis = new JLabel("ITBIS:");
		lblItbis.setBounds(169, 34, 46, 14);
		panel_2.add(lblItbis);
		
		JLabel lblTotal= new JLabel("Total:");
		lblTotal.setBounds(309, 34, 46, 14);
		panel_2.add(lblTotal);
		
		subtotal_lbl = new JLabel("0.00");
		subtotal_lbl.setBackground(new Color(255, 255, 255));
		subtotal_lbl.setBounds(94, 34, 56, 14);
		panel_2.add(subtotal_lbl);
		
		itbis_lbl = new JLabel("0.00");
		itbis_lbl.setBackground(new Color(255, 255, 255));
		itbis_lbl.setBounds(234, 34, 56, 14);
		panel_2.add(itbis_lbl);
		
		total_lbl = new JLabel("0.00");
		total_lbl.setBackground(new Color(255, 255, 255));
		total_lbl.setBounds(374, 34, 56, 14);
		panel_2.add(total_lbl);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(RegContrato.class.getResource("/recursos/expediente128.png")));
		label.setBounds(871, 31, 141, 181);
		contentPanel.add(label);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setBackground(new Color(240, 248, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Date fechaGeneracion = new Date();
						DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
						for (String id : idPlanes) {
						planesACotizar.add(Controladora.getInstance().findPlanByID(id));
						}
//						for (Plan plan : planesACotizar) {
//							Controladora.getInstance().eliminarPlan(plan);
//						}
						String vendedor = "pepe";
						float totalEnviar = Float.parseFloat(subtotal_lbl.getText());
						Contrato contratoToAdd = new Contrato(idContrato,vendedor,planesACotizar,clientelito,formato.format(fechaGeneracion),totalEnviar,true);
						Controladora.getInstance().agregarContrato(contratoToAdd);
						JOptionPane.showMessageDialog(null, "Contrato Registrado.", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
						Controladora.getInstance().empezarFacturar();
						dispose();
						
					}
				});
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

	private void updatePlanes() {
		dbPlanes.clear();
		for (Plan aux : Controladora.getInstance().getPlanes()) {
			dbPlanes.addElement(aux.getId()+"_"+aux.getNombre()+"_"+String.valueOf(aux.getCosto()));
		}
		
	}
}
