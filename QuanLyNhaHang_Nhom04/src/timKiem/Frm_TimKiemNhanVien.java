package timKiem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JComponent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import connectDB.ConnectDB;
import dao.NhanVienDAO;
import entity.NhanVien;

public class Frm_TimKiemNhanVien extends JPanel implements ActionListener {
    private JPanel jpS;
    private JTextField txtNhap;
    private JButton tim;
    private DefaultTableModel modelNV;
    private JTable tableNhanVien;
    private NhanVienDAO nv_dao;
    private JLabel lbNhap;
    private JLabel lbTitle;

    public Frm_TimKiemNhanVien() {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nv_dao = new NhanVienDAO();
        
        setBorder(new EmptyBorder(30, 30, 30, 30));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(173, 216, 230));

        lbTitle = new JLabel("Tìm kiếm nhân viên");
        lbTitle.setFont(new Font("Arial", Font.BOLD, 40));
        lbTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lbTitle.setForeground(Color.darkGray);
        lbTitle.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        add(lbTitle);

        String[] columns = {
                "Mã NV", "Họ", "Phái", "Tuổi", "SĐT"
        };
        modelNV = new DefaultTableModel(columns, 0);
        tableNhanVien = new JTable(modelNV);
        tableNhanVien.setPreferredScrollableViewportSize(new Dimension(940, 570));
        tableNhanVien.setRowHeight(30);
        tableNhanVien.setFont(new Font("Arial", Font.BOLD, 18));

        JTableHeader header = tableNhanVien.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));
        header.setFont(new Font("Arial", Font.BOLD, 20));

        String[] luaChon = {"Nam", "Nữ"};
        TableColumn phaiColumn = tableNhanVien.getColumnModel().getColumn(2); 
        phaiColumn.setCellEditor(new DefaultCellEditor(new JComboBox<>(luaChon)));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < tableNhanVien.getColumnCount(); i++) {
			tableNhanVien.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
        
        JScrollPane scrollPane = new JScrollPane(tableNhanVien);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(scrollPane);

        jpS = new JPanel();
        jpS.setBorder(new EmptyBorder(30, 400, 30, 400));
        jpS.setLayout(new BoxLayout(jpS, BoxLayout.X_AXIS));
        jpS.setBackground(new Color(173, 216, 230));

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
		    for (int i = 0; i < modelNV.getRowCount(); i++) {
		        Object maNVRow = modelNV.getValueAt(i, 0); 
		        if (maNV.equals(maNVRow)) { 
		            tableNhanVien.setRowSelectionInterval(i, i);
		            tableNhanVien.scrollRectToVisible(tableNhanVien.getCellRect(i, 0, true));
		            return;
		        }
		    }
		    JOptionPane.showMessageDialog(this, "Không tìm thấy mã");
		}
	}

	public void docDuLieuDBVaoTable() {
		List<NhanVien> listNV = nv_dao.layThongTin();
		for (NhanVien nv : listNV ) {
			modelNV.addRow(new Object [] {nv.getMaNV(), nv.getHoTenNV(),nv.getPhai(),
			        nv.getTuoi(), nv.getSdt()});
		}
	}
}
