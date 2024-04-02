package timKiem;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import connectDB.ConnectDB;
import dao.KhachHangDAO;
import entity.KhachHang;

public class FrmTimKiemKhachHang extends JPanel implements ActionListener {
    private JPanel jpS;
    private JTextField txtNhap;
    private JButton tim;
    private DefaultTableModel modelKH;
    private JTable tableKhachHang;
    private JLabel lbNhap;
    private JLabel lbTitle;
	private KhachHangDAO kh_dao;

    public FrmTimKiemKhachHang() {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        kh_dao = new KhachHangDAO();
        
        setBorder(new EmptyBorder(30, 30, 30, 30));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(204, 235, 150));

        lbTitle = new JLabel("Tìm kiếm khách hàng");
        lbTitle.setFont(new Font("Arial", Font.BOLD, 40));
        lbTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lbTitle.setForeground(Color.darkGray);
        lbTitle.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        add(lbTitle);

        String[] columns = {
                "Mã KH", "Họ tên", "Phái", "SĐT", "Địa chỉ"
        };
        modelKH = new DefaultTableModel(columns, 0);
        tableKhachHang = new JTable(modelKH);
        tableKhachHang.setPreferredScrollableViewportSize(new Dimension(940, 570));
        tableKhachHang.setRowHeight(30);
        tableKhachHang.setFont(new Font("Arial", Font.BOLD, 18));

        JTableHeader header = tableKhachHang.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));
        header.setFont(new Font("Arial", Font.BOLD, 20));

        String[] luaChon = {"Nam", "Nữ"};
        TableColumn phaiColumn = tableKhachHang.getColumnModel().getColumn(2); // Changed column index from 3 to 2
        phaiColumn.setCellEditor(new DefaultCellEditor(new JComboBox<>(luaChon)));

        JScrollPane scrollPane = new JScrollPane(tableKhachHang);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(scrollPane);

        jpS = new JPanel();
        jpS.setBorder(new EmptyBorder(30, 400, 30, 400));
        jpS.setLayout(new BoxLayout(jpS, BoxLayout.X_AXIS));
        jpS.setBackground(new Color(204, 235, 150));

        lbNhap = new JLabel("Nhập mã số cần tìm: ");
        lbNhap.setFont(new Font("Arial", Font.BOLD, 20)); 
        txtNhap = new JTextField(10);
        txtNhap.setFont(new Font("Arial", Font.PLAIN, 20)); 
        tim = new JButton("Tìm");
        tim.setFont(new Font("Arial", Font.BOLD, 20)); 

        jpS.add(lbNhap);
        jpS.add(txtNhap);
        jpS.add(tim);

        add(jpS);

        tim.addActionListener(this);
        docDuLieuDBVaoTable();
        setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();	
		if (o.equals(tim)) {
		    String maNV = txtNhap.getText();
		    for (int i = 0; i < modelKH.getRowCount(); i++) {
		        Object maNVRow = modelKH.getValueAt(i, 0); 
		        if (maNV.equals(maNVRow)) { 
		            tableKhachHang.setRowSelectionInterval(i, i);
		            tableKhachHang.scrollRectToVisible(tableKhachHang.getCellRect(i, 0, true));
		            return;
		        }
		    }
		    JOptionPane.showMessageDialog(this, "Không tìm thấy mã");
		}
	}

	public void docDuLieuDBVaoTable() {
		List<KhachHang> listKH = kh_dao.layThongTin();
		for (KhachHang kh : listKH ) {
			modelKH.addRow(new Object [] {kh.getMaKH(), kh.getTenKH(),kh.getPhai(),
			         kh.getSdt(),kh.getDiaChi()});
		}
	}
}
