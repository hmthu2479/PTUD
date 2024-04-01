package gui;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connectDB.ConnectDB;
import dao.KhuVucDAO;
import entity.KhuVuc;
import entity.NhanVien;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

public class Frm_KhuVuc extends JDialog implements ActionListener{

	    private static final long serialVersionUID = 1L;
	    private JTable table;
		private DefaultTableModel modelKV;
		private JButton btn_them;
		private JButton btn_xoa;
		private JLabel lbl_maKV;
		private JTextField txt_maKV;
		private JLabel lbl_tenKV;
		private JTextField txt_tenKV;
		private KhuVucDAO kv_dao;
		private JButton btn_luu;

	    public Frm_KhuVuc() {
	    	
	    	try {
				ConnectDB.getInstance().connect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			kv_dao = new KhuVucDAO();
			
	        setTitle("Chi tiết khu vực");
	        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        JPanel titlePanel = new JPanel();
	        titlePanel.setBackground(new Color(255, 255, 255));
	        titlePanel.setPreferredSize(new Dimension(100, 35)); 

	        JLabel titleLabel = new JLabel("Chi tiết khu vực");
	        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
	        titlePanel.add(titleLabel);
	        

	        String[] columnNames = {"Mã khu vực", "Tên khu vực"};

	        modelKV = new DefaultTableModel(columnNames, 0);
			table = new JTable(modelKV);
	        table.setFont(new Font("Arial", Font.BOLD, 16));

	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
	        table.setRowHeight(30);

	        JTableHeader header = table.getTableHeader();
	        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
	        Color lightBlue = new Color(173, 216, 230); // Light blue color
	        header.setBackground(lightBlue);
	        header.setFont(new Font("Arial", Font.BOLD, 18));

	        JPanel pnlButton = new JPanel();
	        pnlButton.setBackground(new Color(255, 255, 255));
	        pnlButton.setPreferredSize(new Dimension(100, 35));
	        pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.X_AXIS));

	        lbl_maKV = new JLabel("Nhập mã khu vực: ");
	        lbl_maKV.setFont(new Font("Tahoma", Font.BOLD, 15));
	        txt_maKV = new JTextField();
	        txt_maKV.setFont(new Font("Tahoma", Font.BOLD, 15));
	        pnlButton.add(lbl_maKV);
	        pnlButton.add(txt_maKV);
	        pnlButton.add(Box.createHorizontalStrut(6));
	        lbl_tenKV = new JLabel("Nhập tên khu vực: ");
	        lbl_tenKV.setFont(new Font("Tahoma", Font.BOLD, 15));
	        txt_tenKV = new JTextField();
	        txt_tenKV.setFont(new Font("Tahoma", Font.BOLD, 15));
	        btn_them = new JButton("Thêm KV");
	        btn_them.setFont(new Font("Tahoma", Font.BOLD, 15));
	        pnlButton.add(lbl_tenKV);
	        pnlButton.add(txt_tenKV);
	        pnlButton.add(Box.createHorizontalStrut(6));
	        pnlButton.add(btn_them);
	        pnlButton.add(Box.createHorizontalStrut(15));
	        btn_xoa = new JButton("Xóa KV");
	        btn_xoa.setFont(new Font("Tahoma", Font.BOLD, 15));
	        pnlButton.add(btn_xoa);
	        pnlButton.add(Box.createHorizontalStrut(6));
	        btn_luu = new JButton("Lưu");
	        btn_luu.setFont(new Font("Tahoma", Font.BOLD, 15));
	        pnlButton.add(btn_luu);
	        pnlButton.add(Box.createHorizontalStrut(6));


	        
	        JPanel pnlContainer = new JPanel();
	        pnlContainer.setBackground(new Color(255, 255, 255));
	        pnlContainer.setLayout(new BoxLayout(pnlContainer, BoxLayout.Y_AXIS));
	        pnlContainer.add(titlePanel, BorderLayout.NORTH);
	        pnlContainer.add(Box.createVerticalStrut(10));
	        pnlContainer.add(new JScrollPane(table), BorderLayout.CENTER);
	        pnlContainer.add(Box.createVerticalStrut(10));
	        pnlContainer.add(pnlButton, BorderLayout.SOUTH);
	        add(pnlContainer);
	        pnlContainer.setBorder(new EmptyBorder(7, 15, 7, 15));
	        setSize(730, 465);
	        
	        btn_them.addActionListener(this);
	        btn_xoa.addActionListener(this);
	        btn_luu.addActionListener(this);
	        docDuLieuDBVaoTable();
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if (o.equals(btn_them)){
				String maKV = txt_maKV.getText();
				String tenKV = txt_tenKV.getText();
				KhuVuc kv = new KhuVuc(maKV, tenKV);
				try {
				    ;
				    kv_dao.themKhuVuc(kv);
				    modelKV.addRow(new Object[] { kv.getMaKhuVuc(), kv.getTenKhuVuc()
				    });
				} catch (Exception e2) {
				    e2.printStackTrace(); 
				    JOptionPane.showMessageDialog(this, "Trùng mã");
				}
			}
			if (o.equals(btn_xoa)) {
			    int r = table.getSelectedRow();
			    if (r != -1) {
			        String maKV = (String) modelKV.getValueAt(r, 0);
			        modelKV.removeRow(r);
			        kv_dao.xoaKhuVuc(maKV);
			    }
			}
		
			if (o.equals(btn_luu)) {
		    int rowCount = modelKV.getRowCount();
		    for (int i = 0; i < rowCount; i++) {
		        String maKV = (String) modelKV.getValueAt(i, 0);
		        String tenKV = (String) modelKV.getValueAt(i, 1);

		        KhuVuc kv = new KhuVuc(maKV, tenKV);
		        
		        try {
		            kv_dao.capNhatKhuVuc(kv);
		        } catch (Exception e2) {
		            e2.printStackTrace(); 
		            JOptionPane.showMessageDialog(this, "Lỗi khi lưu dữ liệu vào cơ sở dữ liệu");
		            return; 
		        }
		    }
		    JOptionPane.showMessageDialog(this, "Dữ liệu đã được lưu thành công");
		}
	}

		public void docDuLieuDBVaoTable() {
			List<KhuVuc> listKV = kv_dao.layThongTin();
			for (KhuVuc kv : listKV ) {
				modelKV.addRow(new Object [] {kv.getMaKhuVuc(),kv.getTenKhuVuc()});
			}
		}
	    
	
}
