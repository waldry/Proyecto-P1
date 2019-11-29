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
import java.awt.Color;
import java.awt.Toolkit;

public class ListaPlanes extends JDialog {

	
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
		Internet auxInt = null;
		Cable auxCable = null;
		Telefono auxTel = null;
		row = new Object[model.getColumnCount()];
		for (int i = 0; i < Controladora.getInstance().getPlanes().size(); i++) {
			row[0] = Controladora.getInstance().getPlanes().get(i).getNombre();
			if (Controladora.getInstance().getPlanes().get(i).getServicios().get(i) instanceof Internet) {
				auxInt = (Internet) Controladora.getInstance().getPlanes().get(i).getServicios().get(i);
				row[1] = auxInt.getAnchoBandaDescarga();
				row[2] = auxInt.getAnchoBandaSubida();
				row[3] = "";
				row[4] = "";
				row[5] = "";
				row[6] = "";
				row[7] = "";
				row[8] = "";
				row[9] = "";
				row[10] = "";
			}
			if (Controladora.getInstance().getPlanes().get(i).getServicios().get(i) instanceof Cable) {
				auxCable = (Cable) Controladora.getInstance().getPlanes().get(i).getServicios().get(i);
				row[1] = "";
				row[2] = "";
				row[3] = auxCable.getCantCanales();
				row[4] = auxCable.isAdultos();
				row[5] = auxCable.isHbo();
				row[6] = auxCable.isDeportes();
				row[7] = "";
				row[8] = "";
				row[9] = "";
				row[10] = "";
			}
			if (Controladora.getInstance().getPlanes().get(i).getServicios().get(i) instanceof Telefono) {
				auxTel = (Telefono) Controladora.getInstance().getPlanes().get(i).getServicios().get(i);
				row[1] = "";
				row[2] = "";
				row[3] = "";
				row[4] = "";
				row[5] = "";
				row[6] = "";
				row[7] = auxTel.getCantMinutos();
				row[8] = auxTel.isVoicemail();
				row[9] = auxTel.isDoblelinea();
				row[10] = auxTel.isIlimitado();
			}
			model.addRow(row);
		}
	}
}
