package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Frm_ThongKe extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel model;
    private static final String[] COLUMN_NAMES = {"Mã hóa đơn", "Tên món ăn", "Tổng tiền", "Bàn ăn", "Nhân viên", "Ngày lập", "Ngày đặt"};
    private JTable table;
    private JButton btnNewButton;
    private Frm_DanhSachHoaDon inhoadon;
    private JLabel lblNewLabel;
    private JTextField textField;

    /**
     * Create the panel.
     */
    public Frm_ThongKe() {
        setBounds(100, 100, 805, 365);
        setLayout(null);

        btnNewButton = new JButton("In hóa đơn");
        btnNewButton.setBackground(new Color(135, 149, 248));
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnNewButton.setBounds(565, 224, 138, 43);
        add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Frm_DanhSachHoaDon().setVisible(true);
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 769, 179);
        add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                },
                new String[]{
                        "Mã hóa đơn", "Tên món", "Tổng tiền", "Bàn ăn", "Khách hàng", "Ngày lập", "Ngày đặt"
                }
        ) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[]{
                    String.class, String.class, String.class, String.class, String.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(4).setResizable(false);
        table.getColumnModel().getColumn(5).setResizable(false);
        table.getColumnModel().getColumn(6).setResizable(false);
        scrollPane.setViewportView(table);

        lblNewLabel = new JLabel("Tìm kiếm:");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel.setBounds(199, 230, 100, 30);
        add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Times New Roman", Font.BOLD, 17));
        textField.setBounds(293, 231, 236, 30);
        add(textField);
        textField.setColumns(10);

        btnNewButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}