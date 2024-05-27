package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import entity.MonAn;
import dao.MonAnDao;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Font;

public class Frm_CapNhatMonAn extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtMa;
    private JTable table;
    private JComboBox<String> comboBox;
    private JTextField txtTenMon;
	private JTextField txtDonGia;
	private MonAnDao ma_dao;
	private DefaultTableModel model;

    /**
     * Create the panel.
     */
    public Frm_CapNhatMonAn() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ma_dao = new MonAnDao();
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Thông tin món ăn");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 35));
        lblNewLabel.setBounds(88, 38, 318, 71);
        add(lblNewLabel);

        JLabel lblDanhSchMn = new JLabel("Danh sách món ăn");
        lblDanhSchMn.setFont(new Font("Arial", Font.BOLD, 35));
        lblDanhSchMn.setBounds(819, 38, 318, 71);
        add(lblDanhSchMn);

        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setBounds(485, 84, 15, 409);
        add(separator);

        JLabel lblNewLabel_1 = new JLabel("Mã món ăn:");
        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 24));
        lblNewLabel_1.setBounds(30, 119, 190, 38);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_2 = new JLabel("Loại món ăn:");
        lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 24));
        lblNewLabel_1_2.setBounds(30, 363, 190, 38);
        add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_1 = new JLabel("Tên món ăn:");
        lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 24));
        lblNewLabel_1_1.setBounds(30, 235, 190, 38);
        add(lblNewLabel_1_1);

        JLabel lblDonGia = new JLabel("Đơn giá:");
        lblDonGia.setFont(new Font("Arial", Font.BOLD, 24));
        lblDonGia.setBounds(30, 463, 190, 38);
        add(lblDonGia);

        txtMa = new JTextField();
        txtMa.setFont(new Font("Arial", Font.PLAIN, 16));
        txtMa.setBounds(203, 119, 252, 34);
        add(txtMa);
        txtMa.setColumns(10);
        

        comboBox = new JComboBox<>(new String[]{"Món nước", "Cơm","Lẩu", "Món ăn khác"});
        comboBox.setFont(new Font("Arial", Font.BOLD, 18));
        comboBox.setBounds(210, 362, 245, 39);
        add(comboBox);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(529, 118, 935, 475);
        add(scrollPane);

   
        String[] columnNames = {"Mã món ăn", "Loại món ăn", "Tên món ăn", "Đơn giá"};

        model = new DefaultTableModel(columnNames, 0);
		table = new JTable(model);
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		table.setRowHeight(20);
        scrollPane.setViewportView(table);

        JButton btnNewButton = new JButton("Thêm");
        btnNewButton.setFont(new Font("Arial", Font.BOLD, 18));
        btnNewButton.setBounds(40, 529, 85, 38);
        add(btnNewButton);

        JButton btnXoa = new JButton("Xóa");
        btnXoa.setFont(new Font("Arial", Font.BOLD, 18));
        btnXoa.setBounds(185, 529, 85, 38);
        add(btnXoa);

        JButton btnThoat = new JButton("Thoát");
        btnThoat.setFont(new Font("Arial", Font.BOLD, 18));
        btnThoat.setBounds(330, 529, 95, 38);
        add(btnThoat);

        txtTenMon = new JTextField();
        txtTenMon.setFont(new Font("Arial", Font.PLAIN, 16));
        txtTenMon.setColumns(10);
        txtTenMon.setBounds(203, 235, 252, 34);
        add(txtTenMon);
        
        txtDonGia = new JTextField();
        txtDonGia.setFont(new Font("Arial", Font.PLAIN, 16));
        txtDonGia.setColumns(10);
        txtDonGia.setBounds(203, 465, 252, 34);
        add(txtDonGia);


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
        docDuLieuDBVaoTable();
    }

    public void docDuLieuDBVaoTable() {
	    List<MonAn> list = ma_dao.layThongTin();
	    for (MonAn mon : list) {
	        model.addRow(new Object[]{mon.getMaMonAn(), mon.getLoaiMonAn(),mon.getTenMonAn(),mon.getDonGia()});
	    }
	}
    private void addFood() {
        String maMonAn = txtMa.getText();
        String tenMonAn = txtTenMon.getText();
        String loaiMon = (String) comboBox.getSelectedItem();
        double donGia = Double.parseDouble(txtDonGia.getText()) ;
        
        MonAn mn = new MonAn(maMonAn, loaiMon, tenMonAn, donGia);
        if (!maMonAn.isEmpty() && !tenMonAn.isEmpty()) {
            model.addRow(new Object[]{maMonAn, tenMonAn, loaiMon,donGia});
            
            ma_dao.themMonAn(mn) ;// Thêm vào cơ sở dữ liệu
            // Clear text fields after adding
            txtMa.setText("");
            txtTenMon.setText("");
            txtDonGia.setText("");
            comboBox.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin món ăn.");
        }
    }

    // Method to delete selected row from the table
    private void deleteSelectedRow() {
    	int r = table.getSelectedRow();
        if (r != -1) {
            String maMon = (String) model.getValueAt(r, 0);
            
            int rs = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa món này?");
            if (rs == JOptionPane.YES_OPTION) {
                model.removeRow(r);
                ma_dao.xoaMonAn(maMon);
            }
        }
    }
}
