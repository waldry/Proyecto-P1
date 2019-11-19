package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cliente;
import logico.Controladora;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class ListaClientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private Object[] row;
	private JButton btneliminar;
	private JButton btnModificar;
	private String cedula = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaClientes dialog = new ListaClientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaClientes() {
		setTitle("Lista de Clientes");
		setResizable(false);
		setBounds(100, 100, 545, 360);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Listado de clientes", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 521, 253);
		contentPanel.add(scrollPane);
		model = new DefaultTableModel();
		String[] header = {"Cédula", "Nombre", "Apellido", "Dirección", "Teléfono", "Activo"};
		model.setColumnIdentifiers(header);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow()>-1) {
					int index = table.getSelectedRow();
					btneliminar.setEnabled(true);
					btnModificar.setEnabled(true);
					cedula = String.valueOf(table.getValueAt(index, 0));
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model);
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnModificar = new JButton("Modificar");
			btnModificar.setEnabled(false);
			buttonPane.add(btnModificar);
			{
				btneliminar = new JButton("Eliminar");
				btneliminar.setEnabled(false);
				btneliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!cedula.equals("")) {
							Cliente aux = Controladora.getInstance().findClienteById(cedula);
							int option = JOptionPane.showConfirmDialog(null, "Está seguro que desea eliminar el Suministrador: " + aux.getNombre(),"Información",JOptionPane.WARNING_MESSAGE);
								if(option == JOptionPane.OK_OPTION) {
									Controladora.getInstance().eliminarCliente(aux);
									loadClientes();
									btneliminar.setEnabled(false);
									btnModificar.setEnabled(false);
								}
						}
					}
				});
				btneliminar.setActionCommand("OK");
				buttonPane.add(btneliminar);
				getRootPane().setDefaultButton(btneliminar);
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
		loadClientes();
	}
	public void loadClientes() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (int i = 0; i < Controladora.getInstance().getClientes().size(); i++) {
			row[0] = Controladora.getInstance().getClientes().get(i).getCedula();
			row[1] = Controladora.getInstance().getClientes().get(i).getNombre();
			row[2] = Controladora.getInstance().getClientes().get(i).getApellido();
			row[3] = Controladora.getInstance().getClientes().get(i).getDireccion();
			row[4] = Controladora.getInstance().getClientes().get(i).getTelefono();
			if(Controladora.getInstance().getClientes().get(i).isActivo() == true) {
				row[5] = "Si";
			}else {
				row[5] = "No";
			}
			model.addRow(row);
		}
	}
}
