package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class Frm_QuanLyMonAn extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public Frm_QuanLyMonAn() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thông tin món ăn");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(36, 28, 226, 43);
		add(lblNewLabel);
		
		JLabel lblDanhSchMn = new JLabel("Danh sách món ăn");
		lblDanhSchMn.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblDanhSchMn.setBounds(464, 28, 226, 43);
		add(lblDanhSchMn);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(430, 28, 24, 440);
		add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Mã món:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_1.setBounds(20, 103, 93, 29);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên món:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_1_1.setBounds(20, 189, 111, 29);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nhóm món:");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_1_2.setBounds(20, 288, 128, 29);
		add(lblNewLabel_1_2);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textField.setBounds(167, 103, 211, 28);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(167, 189, 211, 28);
		add(textField_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Món nướng", "Món lâu", "Món tráng miệng"}));
		comboBox.setBounds(167, 288, 211, 29);
		add(comboBox);
		
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setBounds(20, 365, 89, 29);
		add(btnNewButton);
		
		JButton btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXa.setBounds(119, 365, 89, 29);
		add(btnXa);
		
		JButton btnHy = new JButton("Hủy");
		btnHy.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnHy.setBounds(218, 365, 89, 29);
		add(btnHy);
		
		JButton btnThot = new JButton("Thoát");
		btnThot.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThot.setBounds(317, 365, 89, 29);
		add(btnThot);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(447, 81, 439, 387);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"M\u00E3 m\u00F3n", "T\u00EAn m\u00F3n", "Nh\u00F3m m\u00F3n"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(table);
	}
}
