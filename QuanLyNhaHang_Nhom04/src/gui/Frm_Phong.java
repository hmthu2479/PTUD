package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connectDB.ConnectDB;
import dao.KhuVucDAO;
import dao.PhongDAO;
import entity.KhuVuc;

import entity.Phong;

public class Frm_Phong extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTable table;
	private DefaultTableModel modelPhong;
	private JButton btn_them;
	private JButton btn_xoa;
	private JLabel lbl_tim;
	private JTextField txt_tim;
	private JButton btn_tim;
	private JLabel lbl_maPhong;
	private JTextField txt_maPhong;
	private JLabel lbl_tenPhong;
	private JTextField txt_tenPhong;
	private KhuVucDAO kv_dao;
	private PhongDAO phong_dao;
	private JComboBox<String> cmb_khuVuc;
	private JButton btn_luu;

    public Frm_Phong() {
    	try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kv_dao = new KhuVucDAO();
		phong_dao = new PhongDAO();
		
        setTitle("Chi tiết phòng");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 255, 255));
        titlePanel.setPreferredSize(new Dimension(100, 35)); 

        JLabel titleLabel = new JLabel("Chi tiết phòng");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        

        String[] columnNames = {"Mã Phòng","Tên Phòng", "Thuộc"};

        modelPhong = new DefaultTableModel(columnNames, 0);
		table = new JTable(modelPhong);
        table.setFont(new Font("Arial", Font.BOLD, 16));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setRowHeight(30);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
        Color lightBlue = new Color(173, 216, 230); // Light blue color
        header.setBackground(lightBlue);
        header.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel pnlButton1 = new JPanel();
        pnlButton1.setLayout(new BoxLayout(pnlButton1, BoxLayout.X_AXIS));
        pnlButton1.setBackground(new Color(255, 255, 255));
        pnlButton1.setPreferredSize(new Dimension(100, 35));

        
        lbl_maPhong = new JLabel("Nhập mã phòng: ");
        lbl_maPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
        cmb_khuVuc = new JComboBox<String>();
        cmb_khuVuc.setEditable(false);	
		
		ArrayList<KhuVuc> listKV = kv_dao.layThongTin() ;
		for (KhuVuc kv : listKV) {
			cmb_khuVuc.addItem(kv.getTenKhuVuc());
		}
		cmb_khuVuc.setFont(new Font("Tahoma", Font.BOLD, 15));
        txt_maPhong = new JTextField();
        txt_maPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
        pnlButton1.add(lbl_maPhong);
        pnlButton1.add(Box.createHorizontalStrut(3));
        pnlButton1.add(txt_maPhong);
        pnlButton1.add(Box.createHorizontalStrut(6));
        lbl_tenPhong = new JLabel("Nhập tên phòng: ");
        lbl_tenPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        txt_tenPhong = new JTextField();
        txt_tenPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
        btn_them = new JButton("Thêm");
        btn_them.setFont(new Font("Tahoma", Font.BOLD, 16));
        pnlButton1.add(lbl_tenPhong);
        pnlButton1.add(Box.createHorizontalStrut(3));
        pnlButton1.add(txt_tenPhong);
        pnlButton1.add(Box.createHorizontalStrut(6));
        pnlButton1.add(cmb_khuVuc);
        pnlButton1.add(Box.createHorizontalStrut(12));
        pnlButton1.add(btn_them);
        
        JPanel pnlButton2 = new JPanel();
        pnlButton2.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        pnlButton2.add(Box.createHorizontalStrut(30));
        lbl_tim = new JLabel("Nhập mã phòng cần tìm: ");
        lbl_tim.setFont(new Font("Tahoma", Font.BOLD, 14));
        txt_tim = new JTextField();
        txt_tim.setFont(new Font("Tahoma", Font.BOLD, 15));
        btn_tim = new JButton("Tìm");
        btn_tim.setFont(new Font("Tahoma", Font.BOLD, 15));
        pnlButton2.add(lbl_tim);
        pnlButton2.add(txt_tim);
        pnlButton2.add(Box.createHorizontalStrut(5));
        pnlButton2.add(btn_tim);
        pnlButton2.add(Box.createHorizontalStrut(12));
        
        pnlButton2.setLayout(new BoxLayout(pnlButton2, BoxLayout.X_AXIS));
        pnlButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlButton2.setBackground(new Color(255, 255, 255));
        pnlButton2.setPreferredSize(new Dimension(100, 35));

        btn_xoa = new JButton("Xóa phòng");
        btn_xoa.setFont(new Font("Tahoma", Font.BOLD, 15));
        pnlButton2.add(btn_xoa);
        pnlButton2.add(Box.createHorizontalStrut(8));
        btn_luu = new JButton("Lưu");
        btn_luu.setFont(new Font("Tahoma", Font.BOLD, 15));
        pnlButton2.add(btn_luu);
        pnlButton2.add(Box.createHorizontalStrut(30));

        JPanel pnlButton = new JPanel();
        pnlButton.setBackground(new Color(255, 255, 255));
        pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.Y_AXIS));
        pnlButton.add(pnlButton1);
        pnlButton.add(pnlButton2);
        
 
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
        setSize(790, 465);
        
        btn_them.addActionListener(this);
        btn_xoa.addActionListener(this);
        btn_tim.addActionListener(this);
        btn_luu.addActionListener(this);
        docDuLieuDBVaoTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	Object o = e.getSource();
    	  	
    	if (o.equals(btn_them)) {
    	    String maPhong = txt_maPhong.getText();
    	    String tenPhong = txt_tenPhong.getText();
    	    String khuVuc = String.valueOf(cmb_khuVuc.getSelectedItem());

    	    KhuVucDAO khuVucDAO = new KhuVucDAO();
    	    ArrayList<KhuVuc> dsKV = khuVucDAO.layThongTin();

    	    for (KhuVuc kv : dsKV) {
    	        if (khuVuc.equalsIgnoreCase(kv.getTenKhuVuc())) {
    	            Phong p = new Phong(maPhong, tenPhong, kv);
    	                if (phong_dao.themPhong(p)) {
    	                    modelPhong.addRow(new Object[]{p.getMaPhong(), p.getTenPhong(), kv.getTenKhuVuc()});
    	                } else {
    	                    JOptionPane.showMessageDialog(null, "Trùng mã", "Lỗi", JOptionPane.ERROR_MESSAGE);
    	                }
    	                return;
    	            } 
    	        }
    	   }


        if (o.equals(btn_xoa)) {
            int r = table.getSelectedRow();
            if (r != -1) {
                String maPhong = (String) modelPhong.getValueAt(r, 0);
                modelPhong.removeRow(r);
                phong_dao.xoaPhong(maPhong);
            }
        }
        
        if (o.equals(btn_tim)) {
            String maPhong = txt_tim.getText();
            for (int i = 0; i < modelPhong.getRowCount(); i++) {
                Object maPhongRow = modelPhong.getValueAt(i, 0);
                if (maPhong.equals(maPhongRow.toString())) {
                    table.setRowSelectionInterval(i, i);
                    table.scrollRectToVisible(table.getCellRect(i, 0, true));
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Không tìm thấy mã");
        }
        if (o.equals(btn_luu)) {
            int rowCount = modelPhong.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                String maPhong = (String) modelPhong.getValueAt(i, 0);
                String tenPhong = (String) modelPhong.getValueAt(i, 1);
                String kv = (String) modelPhong.getValueAt(i, 2); 
                KhuVuc khuVuc = new KhuVuc(kv); 
                Phong p = new Phong(maPhong, tenPhong, khuVuc);
                
                try {
                    phong_dao.capNhatPhong(p);
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
		
		List<Phong> listPhong = phong_dao.layThongTin();
		for (Phong p : listPhong ) {
			modelPhong.addRow(new Object [] {p.getMaPhong(), p.getTenPhong(),p.getKhuVuc().getMaKhuVuc()});
			
		}
	}
	
    
}


