package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Contrato;
import logico.Controladora;
import logico.Plan;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListaContratos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private Object[] row;
	private JButton btnEliminar;
	private String id = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaContratos dialog = new ListaContratos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaContratos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListaContratos.class.getResource("/recursos/portapapeles.png")));
		setTitle("Listado de Contratos");
		setResizable(false);
		setBounds(100, 100, 787, 404);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 248, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 763, 325);
		contentPanel.add(scrollPane);
		model = new DefaultTableModel();
		String[] header = {"ID", "Cliente", "Fecha", "Total", "Despachado por", "Activo"};
		model.setColumnIdentifiers(header);
		table = new JTable();
		table.setBackground(new Color(255,245,238));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow()>-1) {
					int index = table.getSelectedRow();
					btnEliminar.setEnabled(true);
					id = String.valueOf(table.getValueAt(index, 0));
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
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!id.equals("")) {
//							Contrato aux = Controladora.getInstance().findContratoById(id);
						}
					}
				});
				btnEliminar.setEnabled(false);
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadContratos();
	}
	public void loadContratos() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Contrato contract : Controladora.getInstance().getContratos()) {
			row[0] = contract.getId();
			row[1] = contract.getClient().getNombre()+" "+contract.getClient().getApellido();
			row[2] = contract.getFechaApertura();
			row[3] = contract.getTotal();
			row[4] = contract.getUser();
			if(contract.isActivo()==true) {
				row[5] = "Si";
			}else {
				row[5] = "No";
			}
			model.addRow(row);
		}
	}
}
