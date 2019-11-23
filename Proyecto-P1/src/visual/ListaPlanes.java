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

import logico.Cable;
import logico.Controladora;
import logico.Internet;
import logico.Telefono;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 600, 355);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Listado de Planes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(574, 253);
		scrollPane.setLocation(10, 22);
		contentPanel.add(scrollPane);
		model = new DefaultTableModel();
		String[] header = {"Nombre","Bajada","Subida","Canales","Pqte Adulto","Pqte HBO","Pqte Deportes","Minutos","VoiceMail","Doble Linea","Ilimitado"};
		model.setColumnIdentifiers(header);
		table = new JTable();
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
		for (int i = 0; i < Controladora.getInstance().getPlanes().size(); i++) {
			row[0] = Controladora.getInstance().getPlanes().get(i).getNombre();
			if (Controladora.getInstance().getPlanes().get(i).getServicios().get(i) instanceof Internet) {
				Internet aux = (Internet) Controladora.getInstance().getPlanes().get(i).getServicios().get(i);
				row[1] = aux.getAnchoBandaDescarga();
				row[2] = aux.getAnchoBandaSubida();
			}
			if (Controladora.getInstance().getPlanes().get(i).getServicios().get(i) instanceof Cable) {
				Cable aux = (Cable) Controladora.getInstance().getPlanes().get(i).getServicios().get(i);
				row[3] = aux.getCantCanales();
				row[4] = aux.isAdultos();
				row[5] = aux.isHbo();
				row[6] = aux.isDeportes();
			}
			if (Controladora.getInstance().getPlanes().get(i).getServicios().get(i) instanceof Telefono) {
				Telefono aux = (Telefono) Controladora.getInstance().getPlanes().get(i).getServicios().get(i);
				row[7] = aux.getCantMinutos();
				row[8] = aux.isVoicemail();
				row[9] = aux.isDoblelinea();
				row[10] = aux.isIlimitado();
			}
			model.addRow(row);
		}
	}
}
