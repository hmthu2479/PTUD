package gui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Frm_CapNhatMonAn extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTable table;
    private JComboBox<String> comboBox;
    private JTextField textField_1;

    /**
     * Create the panel.
     */
    public Frm_CapNhatMonAn() {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Thông tin món ăn");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
        lblNewLabel.setBounds(28, 38, 318, 71);
        add(lblNewLabel);

        JLabel lblDanhSchMn = new JLabel("Danh sách món ăn");
        lblDanhSchMn.setFont(new Font("Times New Roman", Font.BOLD, 35));
        lblDanhSchMn.setBounds(619, 38, 318, 71);
        add(lblDanhSchMn);

        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setBounds(485, 84, 15, 409);
        add(separator);

        JLabel lblNewLabel_1 = new JLabel("Mã món:");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 27));
        lblNewLabel_1.setBounds(10, 119, 190, 38);
        add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Tên món:");
        lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 27));
        lblNewLabel_1_1.setBounds(10, 235, 190, 38);
        add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("Loại món:");
        lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 27));
        lblNewLabel_1_2.setBounds(10, 363, 190, 38);
        add(lblNewLabel_1_2);

        textField = new JTextField();
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        textField.setBounds(203, 119, 252, 34);
        add(textField);
        textField.setColumns(10);

        comboBox = new JComboBox<>(new String[]{"Nước ngọt", "Nước ép", "Bia"});
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Món nướng", "Món lẩu", "Món tráng miệng"}));
        comboBox.setFont(new Font("Times New Roman", Font.BOLD, 18));
        comboBox.setSize(245, 39);
        comboBox.setLocation(210, 362);

        add(comboBox);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(499, 118, 535, 375);
        add(scrollPane);

        table = new JTable();
        table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Mã món nước", "Tên món nước", "Loại món nước"
                }
        ));

        scrollPane.setViewportView(table);

        JButton btnNewButton = new JButton("Thêm");
        btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        btnNewButton.setBounds(40, 429, 85, 38);
        add(btnNewButton);

        JButton btnXoa = new JButton("Xóa");
        btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        btnXoa.setBounds(185, 429, 85, 38);
        add(btnXoa);

        JButton btnThoat = new JButton("Thoát");
        btnThoat.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        btnThoat.setBounds(330, 429, 85, 38);
        add(btnThoat);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        textField_1.setColumns(10);
        textField_1.setBounds(203, 235, 252, 34);
        add(textField_1);

        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFood();
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedRow();
            }
        });

        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void addFood() {
        String maMonAn = textField.getText();
        String tenMonAn = textField_1.getText();
        String loaiMon = (String) comboBox.getSelectedItem();

        if (!maMonAn.isEmpty() && !tenMonAn.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.addRow(new Object[]{maMonAn, tenMonAn, loaiMon});
            // DatabaseConnector.themMonAnVaoDatabase(maMonAn, tenMonAn, loaiMon); // Thêm vào cơ sở dữ liệu
            // Clear text fields after adding
            textField.setText("");
            textField_1.setText("");
            comboBox.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin món ăn.");
        }
    }

    private void deleteSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
}
