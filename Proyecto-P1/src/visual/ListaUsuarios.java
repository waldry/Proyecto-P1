package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Controladora;
import logico.Personal;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class ListaUsuarios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private Object[] row;
	private JButton btneliminar;
	private JButton btnModificar;
	private String username = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaUsuarios dialog = new ListaUsuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaUsuarios() {
		setTitle("Lista de Usuarios");
		setResizable(false);
		setBounds(100, 100, 528, 329);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Listado de Usuarios", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 504, 239);
		contentPanel.add(scrollPane);
		model = new DefaultTableModel();
		String[] header = {"Username", "Nombre", "Apellido", "Oficina", "Tipo", "lastlogin"};
		model.setColumnIdentifiers(header);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow()>-1) {
					int index = table.getSelectedRow();
					btneliminar.setEnabled(true);
					btnModificar.setEnabled(true);
					username = String.valueOf(table.getValueAt(index, 0));
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
			{
				JButton btnModificar = new JButton("Modificar");
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!username.equals("")) {
							Personal aux = Controladora.getInstance().findPersonalByUsername(username);
							int option = JOptionPane.showConfirmDialog(null, "Está seguro que desea eliminar el Usuario: " + aux.getNombre(),"Información",JOptionPane.WARNING_MESSAGE);
							if(option == JOptionPane.OK_OPTION) {
								Controladora.getInstance().eliminarUsuario(aux);
								loadUsuarios();
								btneliminar.setEnabled(false);
								btnModificar.setEnabled(false);
							}
						}
					}
				});
				buttonPane.add(btnEliminar);
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
		loadUsuarios();
	}
	public void loadUsuarios() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (int i = 0; i < Controladora.getInstance().getEmpleados().size(); i++) {
			row[0] = Controladora.getInstance().getEmpleados().get(i).getUsername();
			row[1] = Controladora.getInstance().getEmpleados().get(i).getNombre();
			row[2] = Controladora.getInstance().getEmpleados().get(i).getApellido();
			row[3] = Controladora.getInstance().getEmpleados().get(i).getOficina();
			row[4] = Controladora.getInstance().getEmpleados().get(i).getTipo();
			row[5] = Controladora.getInstance().getEmpleados().get(i).getLastLogin();
			model.addRow(row);
		}
	}
}
