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
import java.util.Date;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;

public class RegContrato extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField entry_txt;
	private JFormattedTextField cedula_txt;
	private JFormattedTextField ftxt_buscarCedula;
	private JTextField nombre_txt;
	private JTextField apellido_txt;
	private JTextField address_Txt;
	private JTextField tel_txt;
	private JCheckBox activo_chbx;
	private JList<String> main;
	private JList<String> second;
	private Cliente clientelito;
	private ArrayList<Plan> planesACotizar = new ArrayList<Plan>();
	private ArrayList<String> nombresplanes = new ArrayList<String>();
	DefaultListModel<String> dbPlanes = new DefaultListModel<>();
	DefaultListModel<String> planesSelected = new DefaultListModel<String>();
	private JLabel subtotal_lbl;
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
		setTitle("Registro de contraro");
		setResizable(false);
		setBounds(100, 100, 1038, 702);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informacion - Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 11, 407, 221);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		@SuppressWarnings("rawtypes")
		JComboBox cbxSearch = new JComboBox();
		cbxSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxSearch.getSelectedIndex()==1) {
					entry_txt.setVisible(false);
					ftxt_buscarCedula.setVisible(true);
					
				} else if(cbxSearch.getSelectedIndex()==2) {
					entry_txt.setVisible(true);
					ftxt_buscarCedula.setVisible(false);
				} else if(cbxSearch.getSelectedIndex()==0) {
					entry_txt.setVisible(false);
					ftxt_buscarCedula.setVisible(false);
				}
			}
		});
		cbxSearch.setModel(new DefaultComboBoxModel(new String[] {"<Buscar Por>", "<Cedula>", "<Nombre>"}));
		cbxSearch.setBounds(10, 26, 107, 20);
		panel.add(cbxSearch);
		
		entry_txt = new JTextField();
		entry_txt.setVisible(false);
		entry_txt.setBounds(127, 27, 153, 20);
		panel.add(entry_txt);
		entry_txt.setColumns(10);
		
		JButton btn_search = new JButton("Buscar");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (entry_txt.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Se necesita datos del cliente para poder procesar el contrato.", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					for (Cliente client : Controladora.getInstance().getClientes()) {
						if (entry_txt.getText().equalsIgnoreCase(client.getNombre())) {
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
						else if(ftxt_buscarCedula.getText().equals(client.getCedula())) {
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
		btn_search.setBounds(308, 25, 89, 23);
		panel.add(btn_search);
		
		JLabel lblNombre = new JLabel("Cedula: ");
		lblNombre.setBounds(20, 57, 58, 14);
		panel.add(lblNombre);
		
		JLabel lblNombre_1 = new JLabel("Nombre: ");
		lblNombre_1.setBounds(20, 82, 58, 14);
		panel.add(lblNombre_1);
		
		JLabel lblApellido = new JLabel("Apellido: ");
		lblApellido.setBounds(20, 110, 58, 14);
		panel.add(lblApellido);
		
		JLabel lblDireccion = new JLabel("Direccion: ");
		lblDireccion.setBounds(20, 138, 66, 14);
		panel.add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono: ");
		lblTelefono.setBounds(20, 163, 58, 14);
		panel.add(lblTelefono);
		
		activo_chbx= new JCheckBox("Activo");
		activo_chbx.setBounds(10, 184, 68, 23);
		panel.add(activo_chbx);
		
		cedula_txt = new JFormattedTextField(mascara());
		cedula_txt.setBounds(113, 55, 167, 20);
		panel.add(cedula_txt);
		cedula_txt.setColumns(10);
		
		nombre_txt = new JTextField();
		nombre_txt.setBounds(113, 80, 167, 20);
		panel.add(nombre_txt);
		nombre_txt.setColumns(10);
		
		apellido_txt = new JTextField();
		apellido_txt.setBounds(113, 108, 167, 20);
		panel.add(apellido_txt);
		apellido_txt.setColumns(10);
		
		address_Txt = new JTextField();
		address_Txt.setBounds(113, 136, 167, 20);
		panel.add(address_Txt);
		address_Txt.setColumns(10);
		
		tel_txt = new JTextField();
		tel_txt.setBounds(113, 161, 167, 20);
		panel.add(tel_txt);
		tel_txt.setColumns(10);
		
		ftxt_buscarCedula = new JFormattedTextField(mascara());
		ftxt_buscarCedula.setVisible(false);
		ftxt_buscarCedula.setBounds(127, 27, 153, 20);
		panel.add(ftxt_buscarCedula);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacion - Plan", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(20, 243, 992, 376);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		main = new JList<>(dbPlanes);
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
					String[] separador = s.split("_", 2);
					String second_p = separador[1];
					String nombre = separador[0];
					nombresplanes.add(nombre);
//					planesACotizar.add(Controladora.getInstance().findPlanByName(nombre));
					aux = aux+Float.parseFloat(second_p); 
					subtotal_lbl.setText(String.valueOf(aux));
					}
				}
			}
		});
		agregar_btn.setFont(new Font("Tahoma", Font.BOLD, 20));
		agregar_btn.setBounds(440, 50, 111, 49);
		panel_1.add(agregar_btn);
		
		JButton eliminar_btn = new JButton("<-");
		eliminar_btn.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<String> db = ((DefaultListModel<String>)main.getModel());
				DefaultListModel<String> carrito = ((DefaultListModel<String>)second.getModel());
				for (int item : second.getSelectedIndices()) {
					db.addElement(carrito.getElementAt(item));
					carrito.removeElement(carrito.getElementAt(item));
					float aux = 0;
					for (int i = 0; i < carrito.getSize(); i++) {
						String s = (String)carrito.getElementAt(i);
						String[] separador = s.split("_", 2);
						String second_p = separador[1];
						String nombre = separador[0];
						nombresplanes.remove(nombre);
//						planesACotizar.remove(Controladora.getInstance().findPlanByName(nombre));
						aux = Math.abs(aux-Float.parseFloat(second_p));
						subtotal_lbl.setText(String.valueOf(aux));
					}
				}
				if (second.getModel().getSize()==0) {
					subtotal_lbl.setText("0.00");
				}
			}
		});
		eliminar_btn.setFont(new Font("Tahoma", Font.BOLD, 20));
		eliminar_btn.setBounds(440, 289, 111, 49);
		panel_1.add(eliminar_btn);
		
		second= new JList<>(planesSelected);
		second.setBounds(637, 50, 268, 315);
		panel_1.add(second);
		
		JLabel lblPlanesEnEl = new JLabel("Planes en el Contrato");
		lblPlanesEnEl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPlanesEnEl.setBounds(685, 22, 173, 30);
		panel_1.add(lblPlanesEnEl);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Importes del Contrato", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(680, 11, 319, 221);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setBounds(10, 34, 56, 14);
		panel_2.add(lblSubtotal);
		
		JLabel lblItbis = new JLabel("ITBIS:");
		lblItbis.setBounds(10, 59, 46, 14);
		panel_2.add(lblItbis);
		
		JLabel lblTotal= new JLabel("Total:");
		lblTotal.setBounds(10, 88, 46, 14);
		panel_2.add(lblTotal);
		
		subtotal_lbl = new JLabel("0.00");
		subtotal_lbl.setBounds(76, 34, 46, 14);
		panel_2.add(subtotal_lbl);
		
		JLabel itbis_lbl = new JLabel("0.00");
		itbis_lbl.setBounds(76, 59, 46, 14);
		panel_2.add(itbis_lbl);
		
		JLabel total_lbl = new JLabel("0.00");
		total_lbl.setBounds(76, 88, 46, 14);
		panel_2.add(total_lbl);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Date fechaGeneracion = new Date();
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						for (String string : nombresplanes) {
							System.out.println("El nombre del plan" + string);
							planesACotizar.add(Controladora.getInstance().findPlanByID(string));
						}
						for (Plan plan : planesACotizar) {
							Controladora.getInstance().deletePlan(plan.getNombre());
						}
						Contrato aux = new Contrato(1,"pepe",planesACotizar,clientelito,dateFormat.format(fechaGeneracion),0,Float.parseFloat(subtotal_lbl.getText()));
						Controladora.getInstance().agregarContrato(aux);
						JOptionPane.showMessageDialog(null, "Contrato Registrado.", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
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
			dbPlanes.addElement(aux.getNombre()+"_"+String.valueOf(aux.getCosto()));
		}
		
	}
}
