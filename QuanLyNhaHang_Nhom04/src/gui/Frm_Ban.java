package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import dao.BanDAO;
import entity.KhuVuc;
import entity.Ban;
import entity.Phong;

public class Frm_Ban extends JDialog implements ActionListener{

    private static final long serialVersionUID = 1L;
    private JTable table;
	private DefaultTableModel modelBan;
	private JButton btn_them;
	private JButton btn_xoa;
	private JLabel lbl_tim;
	private JTextField txt_tim;
	private JButton btn_tim;
	private JLabel lbl_maBan;
	private JTextField txt_maBan;
	private JLabel lbl_tenBan;
	private JTextField txt_tenBan;
	private KhuVucDAO kv_dao;
	private PhongDAO phong_dao;
	private JComboBox<String> cmb_khuVuc;
	private BanDAO ban_dao;
	private JLabel lbl_soGhe;
	private JTextField txt_soGhe;
	private JButton btn_luu;
	private JComboBox<String> cmb_Phong;
	private JLabel lbl_khuVuc;
	private JLabel lbl_phong;

    public Frm_Ban() {
    	try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kv_dao = new KhuVucDAO();
		phong_dao = new PhongDAO();
		ban_dao = new BanDAO();
		
        setTitle("Chi tiết bàn");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 255, 255));
        titlePanel.setPreferredSize(new Dimension(100, 35)); 

        JLabel titleLabel = new JLabel("Chi tiết Bàn");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        

        String[] columnNames = {"Mã Bàn","Số Bàn","Số ghế", "Khu Vực","Phòng"};

        modelBan = new DefaultTableModel(columnNames, 0);
		table = new JTable(modelBan);
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
        pnlButton1.setPreferredSize(new Dimension(800, 35));

        
        lbl_maBan = new JLabel("Nhập mã bàn: ");
        lbl_maBan.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        /*        DefaultComboBoxModel<String> thuoc = new DefaultComboBoxModel<>();
        cmb_khuVuc = new JComboBox<>(thuoc);
        cmb_khuVuc.setEditable(false);

        ArrayList<KhuVuc> listKV = kv_dao.layThongTin();
        for (KhuVuc kv : listKV) {
        	thuoc.addElement(kv.getTenKhuVuc());
        }

        ArrayList<Phong> listPhong = phong_dao.layThongTin();
        for (Phong p : listPhong) {
        	thuoc.addElement(p.getTenPhong());
        }*/
        cmb_khuVuc = new JComboBox<String>();
        cmb_khuVuc.setEditable(false);	
		
		ArrayList<KhuVuc> listKV = kv_dao.layThongTin() ;
		for (KhuVuc kv : listKV) {
			cmb_khuVuc.addItem(kv.getTenKhuVuc());
		}
        cmb_Phong = new JComboBox<String>();
		ArrayList<Phong> listPhong = phong_dao.layThongTin() ;
		for (Phong p : listPhong) {
			cmb_Phong.addItem(p.getTenPhong());
		}
		cmb_khuVuc.setFont(new Font("Tahoma", Font.BOLD, 15));
		cmb_Phong.setFont(new Font("Tahoma", Font.BOLD, 15));
        txt_maBan = new JTextField(10);
        txt_maBan.setFont(new Font("Tahoma", Font.BOLD, 15));
        pnlButton1.add(lbl_maBan);
        pnlButton1.add(Box.createHorizontalStrut(3));
        pnlButton1.add(txt_maBan);
        pnlButton1.add(Box.createHorizontalStrut(6));
        lbl_khuVuc = new JLabel("Chọn khu vực: ");
        lbl_khuVuc.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl_phong = new JLabel("Chọn phòng: ");
        lbl_phong.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl_tenBan = new JLabel("Nhập tên bàn: ");
        lbl_tenBan.setFont(new Font("Tahoma", Font.BOLD, 14));
        txt_tenBan = new JTextField(10);
        txt_tenBan.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbl_soGhe = new JLabel("Nhập số ghế: ");
        lbl_soGhe.setFont(new Font("Tahoma", Font.BOLD, 14));
        txt_soGhe = new JTextField(10);
        txt_soGhe.setFont(new Font("Tahoma", Font.BOLD, 15));
        btn_them = new JButton("Thêm");
        btn_them.setFont(new Font("Tahoma", Font.BOLD, 16));
        btn_xoa = new JButton("Xóa");
        btn_xoa.setFont(new Font("Tahoma", Font.BOLD, 16));
        pnlButton1.add(lbl_tenBan);
        pnlButton1.add(Box.createHorizontalStrut(3));
        pnlButton1.add(txt_tenBan);
        pnlButton1.add(Box.createHorizontalStrut(3));
        pnlButton1.add(lbl_soGhe);
        pnlButton1.add(Box.createHorizontalStrut(3));
        pnlButton1.add(txt_soGhe);
        pnlButton1.add(Box.createHorizontalStrut(6));
        pnlButton1.add(lbl_khuVuc);
        pnlButton1.add(cmb_khuVuc);
        pnlButton1.add(Box.createHorizontalStrut(6));
        pnlButton1.add(lbl_phong);
        pnlButton1.add(cmb_Phong);
        pnlButton1.add(Box.createHorizontalStrut(12));
        JPanel pnlBtnThemXoa = new JPanel();
        pnlBtnThemXoa.setLayout(new BoxLayout(pnlBtnThemXoa, BoxLayout.X_AXIS));

        pnlBtnThemXoa.add(btn_them);
        pnlBtnThemXoa.add(Box.createHorizontalStrut(10));
        pnlBtnThemXoa.add(btn_xoa);
        pnlBtnThemXoa.setPreferredSize(new Dimension(155, 55));
        pnlBtnThemXoa.setBackground(Color.white);
        pnlButton1.add(pnlBtnThemXoa);
        pnlButton1.setPreferredSize(new Dimension(150, 23));
        
        JPanel pnlButton2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlButton2.setBorder(BorderFactory.createEmptyBorder(7, 0, 7, 0));

        lbl_tim = new JLabel("Nhập tên bàn cần tìm: ");
        lbl_tim.setFont(new Font("Tahoma", Font.BOLD, 14));

        txt_tim = new JTextField();
        txt_tim.setFont(new Font("Tahoma", Font.BOLD, 15));
        txt_tim.setPreferredSize(new Dimension(150, 25)); 
        btn_tim = new JButton("Tìm");
        btn_tim.setFont(new Font("Tahoma", Font.BOLD, 15));
        btn_luu = new JButton("Lưu");
        btn_luu.setFont(new Font("Tahoma", Font.BOLD, 16));

        pnlButton2.add(lbl_tim);
        pnlButton2.add(txt_tim);
        pnlButton2.add(btn_tim);
        pnlButton2.add(Box.createHorizontalStrut(10));
        pnlButton2.add(btn_luu);

        pnlButton2.setBackground(new Color(255, 255, 255));
        pnlButton2.setPreferredSize(new Dimension(120, 55));
        
        pnlButton2.add(Box.createHorizontalStrut(30));

        JPanel pnlButton = new JPanel();
        pnlButton.setBackground(new Color(255, 255, 255));
        pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.Y_AXIS));
        pnlButton.add(pnlButton1);
        pnlButton.add(pnlButton2);
        pnlButton.setPreferredSize(new Dimension(200, 75));
        
 
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
        setSize(1290, 635);
        
        btn_them.addActionListener(this);
        btn_xoa.addActionListener(this);
        btn_tim.addActionListener(this);
        btn_luu.addActionListener(this);
        docDuLieuDBVaoTable();
    }
    
    	  		
	public void docDuLieuDBVaoTable() {
		
		List<Ban> listBan = ban_dao.layThongTin();
		for (Ban b : listBan ) {
			modelBan.addRow(new Object [] {b.getMaBan(), b.getSoBan(),b.getSoGhe(),b.getKhuVuc().getMaKhuVuc(),b.getPhong().getMaPhong()});
			
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
	  	
		if (o.equals(btn_them)) {
		    String maBan = txt_maBan.getText();
		    String soBan = txt_tenBan.getText();
		    int soGhe = Integer.parseInt(txt_soGhe.getText().trim());
		    String khuVuc = String.valueOf(cmb_khuVuc.getSelectedItem());
		    String phong = String.valueOf(cmb_Phong.getSelectedItem());

		    KhuVucDAO khuVucDAO = new KhuVucDAO();
		    ArrayList<KhuVuc> dsKV = khuVucDAO.layThongTin();
		    PhongDAO phongDao = new PhongDAO();
		    ArrayList<Phong> dsPhong = phongDao.layThongTin();

		    for (KhuVuc kv : dsKV) {
		        if (khuVuc.equalsIgnoreCase(kv.getTenKhuVuc())) {
		            for (Phong p : dsPhong) {
		                if (phong.equalsIgnoreCase(p.getTenPhong())) {
		                    Ban b = new Ban(maBan, soBan, soGhe, kv, p);
		                    if (ban_dao.themBan(b)) {
		                        modelBan.addRow(new Object[]{b.getMaBan(), b.getSoBan(), b.getSoGhe(), kv.getTenKhuVuc(), p.getTenPhong()});
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Trùng mã", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                    }
		                    return;
		                }
		            }
		        }
		    }
		}


        if (o.equals(btn_xoa)) {
            int r = table.getSelectedRow();
            if (r != -1) {
                String maBan = (String) modelBan.getValueAt(r, 0);
                modelBan.removeRow(r);
                ban_dao.xoaBan(maBan);
            }
        }
        
        if (o.equals(btn_tim)) {
            String maPhong = txt_tim.getText();
            for (int i = 0; i < modelBan.getRowCount(); i++) {
                Object maPhongRow = modelBan.getValueAt(i, 0);
                if (maPhong.equals(maPhongRow.toString())) {
                    table.setRowSelectionInterval(i, i);
                    table.scrollRectToVisible(table.getCellRect(i, 0, true));
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Không tìm thấy mã");
        }
        /*if (o.equals(btn_luu)) {
            int rowCount = modelBan.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                String maBan = (String) modelBan.getValueAt(i, 0);
                String tenBan = (String) modelBan.getValueAt(i, 1);
                String kv = (String) modelBan.getValueAt(i, 2); 
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
        }*/

	}
	
    
}


