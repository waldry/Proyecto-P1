package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


import logico.Controladora;
import logico.Plan;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class ListaPlanes extends JDialog implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private Object[] row;
	private JButton btneliminar;
	private JButton btnModificar;
	private String id;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public ListaPlanes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListaPlanes.class.getResource("/recursos/portapapeles.png")));
		setTitle("Lista de Planes");
		setResizable(false);
		setBounds(100, 100, 1017, 428);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 248, 255));
		contentPanel.setBorder(new TitledBorder(null, "Listado de Planes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setSize(993, 337);
		scrollPane.setLocation(10, 22);
		scrollPane.setViewportView(table);
		contentPanel.add(scrollPane);
		model = new DefaultTableModel();
		String[] header = {"ID","Nombre","Bajada","Subida","Canales","Pqte Adulto","Pqte HBO","Pqte Deportes","Minutos","VoiceMail","Doble Linea","Ilimitado","Costo"};
		model.setColumnIdentifiers(header);
		table = new JTable();
		table.setBackground(new Color(255, 245, 238));
		table.addMouseListener(new MouseAdapter() {
			@Override
				public void mouseClicked(MouseEvent arg0) {
					if (table.getSelectedRow()>-1) {
						int index = table.getSelectedRow();
						btneliminar.setEnabled(true);
						btnModificar.setEnabled(true);
						id = String.valueOf(table.getValueAt(index,0));
					}
				}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model);
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(240, 248, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!id.equals("")) {
						Plan aux = Controladora.getInstance().findPlanByID(id);
						RegistrarServicio regPlan = new RegistrarServicio(aux);
						regPlan.setModal(true);
						regPlan.setVisible(true);
					}
				}
				
			});
			btnModificar.setEnabled(false);
			buttonPane.add(btnModificar);
			{
				btneliminar = new JButton("Eliminar");
				btneliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (!id.equals("")) {
							Plan aux = Controladora.getInstance().findPlanByID(id);
							int option = JOptionPane.showConfirmDialog(null, "Est� seguro que desea eliminar el Suministrador: " + aux.getNombre(),"Informaci�n",JOptionPane.WARNING_MESSAGE);
							if (option == JOptionPane.OK_OPTION) {
								Controladora.getInstance().eliminarPlan(aux);
								loadPlanes();
								btneliminar.setEnabled(false);
								btnModificar.setEnabled(false);
							}
						}
					}
				});
				btneliminar.setEnabled(false);
				buttonPane.add(btneliminar);
				getRootPane().setDefaultButton(btneliminar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadPlanes();
	}
	public void loadPlanes() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Plan plan : Controladora.getInstance().getPlanes()) {
			row[0] = plan.getId();
			row[1] = plan.getNombre();
			if (plan.getAnchoBandaDescarga() !=0) {
				row[2] = plan.getAnchoBandaDescarga();
			}
			else {
				row[2] = "";
			}
			if (plan.getAnchoBandaSubida() !=0) {
				row[3] = plan.getAnchoBandaSubida();
			}
			else {
				row[3] = "";
			}
			if (plan.getCantCanales() !=0) {
				row[4] = plan.getCantCanales();
			}
			else {
				row[4] = "";
			}
		    if (plan.isAdultos()) {
		    	row[5] = plan.isAdultos();
			}
		    else {
		    	row[5] = "";
			}
		    if (plan.isHbo()) {
		    	row[6] = plan.isHbo();
			}
		    else {
		    	row[6] = "";
			}
		    if (plan.isDeportes()) {
		    	row[7] = plan.isDeportes();
			}
		    else {
		    	row[7] = "";
			}
		    if (plan.getCantMinutos() !=0) {
		    	row[8] = plan.getCantMinutos();
			}
		    else {
				row[8] = "";
			}
		    if (plan.isVoicemail()) {
		    	row[9] = plan.isVoicemail();
			}
		    else {
				row[9] = "";
			}
		    if (plan.isDoblelinea()) {
		    	row[10] = plan.isDoblelinea();
			}
		    else {
				row[10] = "";
			}
		    if (plan.isIlimitado()) {
				row[11] = plan.isIlimitado();
			}
		    else {
				row[11] = "";
			}
		    if(plan.getCosto() != 0) {
		    	row[12] = plan.getCosto();
		    }
		    else {
		    	row[12] = "";
		    }
			model.addRow(row);
		}
			
	}
}
