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
import logico.Internet;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListaPlanes extends JDialog {

	
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
		setTitle("Lista de Planes");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Listado de Planes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
//	public void loadPlanes() {
//		model.setRowCount(0);
//		row = new Object[model.getColumnCount()];
//		for (int i = 0; i < Controladora.getInstance().getPlanes().size(); i++) {
//			if (Controladora.getInstance().getPlanes().get(i).getServicios().get(i) instanceof Internet) {
//				
//			}
//			row[0] = Controladora.getInstance().getClientes().get(i).getCedula();
//			row[1] = Controladora.getInstance().getClientes().get(i).getNombre();
//			row[2] = Controladora.getInstance().getClientes().get(i).getApellido();
//			row[3] = Controladora.getInstance().getClientes().get(i).getDireccion();
//			row[4] = Controladora.getInstance().getClientes().get(i).getTelefono();
//			if(Controladora.getInstance().getClientes().get(i).isActivo() == true) {
//				row[5] = "Si";
//			}else {
//				row[5] = "No";
//			}
//			model.addRow(row);
//		}
//	}
}
