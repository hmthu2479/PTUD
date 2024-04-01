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
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;


public class Frm_ThongKe extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel model;
    private static final String[] COLUMN_NAMES = {"Mã hóa đơn", "Tên món ăn", "Tổng tiền", "Bàn ăn", "Nhân viên", "Ngày lập", "Ngày đặt"};
    private JTable table;
    private JButton btnNewButton;
    private Frm_DanhSachHoaDon inhoadon;
    private JTable table_1;
    private JTextField textField;


    /**
     * Create the panel.
     */
    public Frm_ThongKe() {
    	setSize(2050,1050);
    	setBackground(new Color(255, 128, 192));
        setBounds(100, 100, 1304, 750);
        setLayout(null);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 192, 1950, 179);
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
                        "Mã hóa đơn", "Bàn ăn", "Tổng tiền", "Khách hàng", "Ngày lập", "Ngày đặt"
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
        
        JLabel lblNewLabel = new JLabel("Danh sách hóa đơn");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(590, 11, 353, 38);
        add(lblNewLabel);
        
        JLabel lblChiTitHa = new JLabel("Chi tiết hóa đơn");
        lblChiTitHa.setHorizontalAlignment(SwingConstants.CENTER);
        lblChiTitHa.setFont(new Font("Times New Roman", Font.BOLD, 32));
        lblChiTitHa.setBounds(585, 382, 353, 38);
        add(lblChiTitHa);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(0, 431, 1950, 237);
        add(scrollPane_1);
        
        table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"M\u00E3 h\u00F3a \u0111\u01A1n", "T\u00EAn m\u00F3n", "T\u1ED5ng ti\u1EC1n", "B\u00E0n \u0103n", "Kh\u00E1ch h\u00E0ng", "Nh\u00E2n vi\u00EAn", "Ng\u00E0y \u0111\u1EB7t"
        	}
        ));
        scrollPane_1.setViewportView(table_1);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 49, 2050, 125);
        add(panel);
        panel.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(192, 192, 192));
        panel_1.setBounds(0, 0, 181, 49);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Tìm hóa đơn");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel_1.setBounds(10, 11, 161, 27);
        panel_1.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Mã hóa đơn:");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblNewLabel_2.setBounds(332, 45, 157, 43);
        panel.add(lblNewLabel_2);
        
        textField = new JTextField();
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        textField.setBounds(499, 50, 247, 35);
        panel.add(textField);
        textField.setColumns(10);
        
        JButton btnNewButton_1 = new JButton("Tìm");
        btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 23));
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnNewButton_1.setBounds(1116, 47, 106, 38);
        panel.add(btnNewButton_1);
        
        JLabel lblNewLabel_2_1 = new JLabel("Ngày đặt:");
        lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblNewLabel_2_1.setBounds(792, 45, 106, 43);
        panel.add(lblNewLabel_2_1);
        
                        
        btnNewButton = new JButton("In hóa đơn");
        btnNewButton.setBounds(1166, 685, 138, 43);
        add(btnNewButton);
        btnNewButton.setBackground(new Color(135, 149, 248));
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnNewButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                     new Frm_DanhSachHoaDon().setVisible(true);
         }
             });
                                
         btnNewButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
}
}