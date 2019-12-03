package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
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
	private String nombre = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaPlanes dialog = new ListaPlanes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
		contentPanel.add(scrollPane);
		model = new DefaultTableModel();
		String[] header = {"Nombre","Bajada","Subida","Canales","Pqte Adulto","Pqte HBO","Pqte Deportes","Minutos","VoiceMail","Doble Linea","Ilimitado"};
		model.setColumnIdentifiers(header);
		table = new JTable();
		table.setBackground(new Color(255, 245, 238));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
			
			JButton btnModificar_1 = new JButton("Modificar");
			buttonPane.add(btnModificar_1);
			{
				JButton btnEliminar = new JButton("Eliminar");
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
			}
			{
				JButton cancelButton = new JButton("Cancel");
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
			row[0] = plan.getNombre();
			if (plan.getAnchoBandaDescarga() !=0) {
				row[1] = plan.getAnchoBandaDescarga();
			}
			else {
				row[1] = "";
			}
			if (plan.getAnchoBandaSubida() !=0) {
				row[2] = plan.getAnchoBandaSubida();
			}
			else {
				row[2] = "";
			}
			if (plan.getCantCanales() !=0) {
				row[3] = plan.getCantCanales();
			}
			else {
				row[3] = "";
			}
		    if (plan.isAdultos()) {
		    	row[4] = plan.isAdultos();
			}
		    else {
		    	row[4] = "";
			}
		    if (plan.isHbo()) {
		    	row[5] = plan.isHbo();
			}
		    else {
		    	row[5] = "";
			}
		    if (plan.isDeportes()) {
		    	row[6] = plan.isDeportes();
			}
		    else {
		    	row[6] = "";
			}
		    if (plan.getCantCanales() !=0) {
		    	row[7] = plan.getCantCanales();
			}
		    else {
				row[7] = "";
			}
		    if (plan.isVoicemail()) {
		    	row[8] = plan.isVoicemail();
			}
		    else {
				row[8] = "";
			}
		    if (plan.isIlimitado()) {
		    	row[9] = plan.isIlimitado();
			}
		    else {
				row[9] = "";
			}
		    
		   
		    
			model.addRow(row);
		}
			
	}
}
