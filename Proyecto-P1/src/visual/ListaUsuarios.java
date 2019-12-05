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
import java.io.Serializable;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ListaUsuarios extends JDialog implements Serializable{

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListaUsuarios.class.getResource("/recursos/portapapeles.png")));
		setTitle("Lista de Usuarios");
		setResizable(false);
		setBounds(100, 100, 867, 430);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 248, 255));
		contentPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Listado de Usuarios", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 843, 340);
		contentPanel.add(scrollPane);
		model = new DefaultTableModel();
		String[] header = {"Username", "Nombre", "Apellido", "Oficina", "Tipo"};
		model.setColumnIdentifiers(header);
		table = new JTable();
		table.setBackground(new Color(255, 245, 238));
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
			buttonPane.setBackground(new Color(240, 248, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnModificar = new JButton("Modificar");
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton btnEliminar = new JButton("Eliminar");
				btnEliminar.setEnabled(false);
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!username.equals("")) {
							Personal aux = Controladora.getInstance().findPersonalByUsername(username);
							int option = JOptionPane.showConfirmDialog(null, "Está seguro de que desea eliminar el Usuario: " + aux.getNombre()+"?","Información",JOptionPane.WARNING_MESSAGE);
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
		for (Personal user : Controladora.getInstance().getEmpleados()) {
			row[0] = user.getUsername();
			row[1] = user.getNombre();
			row[2] = user.getApellido();
			row[3] = user.getOficina();
			row[4] = user.getTipo();
			model.addRow(row);
		}
	}
}
