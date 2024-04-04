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
	private JButton btnthem;
	private JButton btnxoa;
	private JLabel lbltim;
	private JTextField txttim;
	private JButton btntim;
	private JLabel lblmaBan;
	private JTextField txtmaBan;
	private JLabel lbltenBan;
	private JTextField txttenBan;
	private KhuVucDAO kvdao;
	private PhongDAO phongdao;
	private JComboBox<String> cmbkhuVuc;
	private BanDAO bandao;
	private JLabel lblsoGhe;
	private JTextField txtsoGhe;
	private JButton btnluu;
	private JComboBox<String> cmbPhong;
	private JLabel lblkhuVuc;
	private JLabel lblphong;

    public Frm_Ban() {
    	try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kvdao = new KhuVucDAO();
		phongdao = new PhongDAO();
		bandao = new BanDAO();
		
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

        
        lblmaBan = new JLabel("Nhập mã bàn: ");
        lblmaBan.setFont(new Font("Tahoma", Font.BOLD, 14));

        cmbkhuVuc = new JComboBox<String>();
        cmbkhuVuc.setEditable(false);	
		
		ArrayList<KhuVuc> listKV = kvdao.layThongTin() ;
		for (KhuVuc kv : listKV) {
			cmbkhuVuc.addItem(kv.getTenKhuVuc());
		}
        cmbPhong = new JComboBox<String>();
		ArrayList<Phong> listPhong = phongdao.layThongTin() ;
		for (Phong p : listPhong) {
			cmbPhong.addItem(p.getTenPhong());
		}
		cmbkhuVuc.setFont(new Font("Tahoma", Font.BOLD, 15));
		cmbPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
        txtmaBan = new JTextField(10);
        txtmaBan.setFont(new Font("Tahoma", Font.BOLD, 15));
        pnlButton1.add(lblmaBan);
        pnlButton1.add(Box.createHorizontalStrut(3));
        pnlButton1.add(txtmaBan);
        pnlButton1.add(Box.createHorizontalStrut(6));
        lblkhuVuc = new JLabel("Chọn khu vực: ");
        lblkhuVuc.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblphong = new JLabel("Chọn phòng: ");
        lblphong.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbltenBan = new JLabel("Nhập tên bàn: ");
        lbltenBan.setFont(new Font("Tahoma", Font.BOLD, 14));
        txttenBan = new JTextField(10);
        txttenBan.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblsoGhe = new JLabel("Nhập số ghế: ");
        lblsoGhe.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtsoGhe = new JTextField(10);
        txtsoGhe.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnthem = new JButton("Thêm");
        btnthem.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnxoa = new JButton("Xóa");
        btnxoa.setFont(new Font("Tahoma", Font.BOLD, 16));
        pnlButton1.add(lbltenBan);
        pnlButton1.add(Box.createHorizontalStrut(3));
        pnlButton1.add(txttenBan);
        pnlButton1.add(Box.createHorizontalStrut(3));
        pnlButton1.add(lblsoGhe);
        pnlButton1.add(Box.createHorizontalStrut(3));
        pnlButton1.add(txtsoGhe);
        pnlButton1.add(Box.createHorizontalStrut(6));
        pnlButton1.add(lblkhuVuc);
        pnlButton1.add(cmbkhuVuc);
        pnlButton1.add(Box.createHorizontalStrut(6));
        pnlButton1.add(lblphong);
        pnlButton1.add(cmbPhong);
        pnlButton1.add(Box.createHorizontalStrut(12));
        JPanel pnlBtnThemXoa = new JPanel();
        pnlBtnThemXoa.setLayout(new BoxLayout(pnlBtnThemXoa, BoxLayout.X_AXIS));

        pnlBtnThemXoa.add(btnthem);
        pnlBtnThemXoa.add(Box.createHorizontalStrut(10));
        pnlBtnThemXoa.add(btnxoa);
        pnlBtnThemXoa.setPreferredSize(new Dimension(155, 55));
        pnlBtnThemXoa.setBackground(Color.white);
        pnlButton1.add(pnlBtnThemXoa);
        pnlButton1.setPreferredSize(new Dimension(150, 23));
        
        JPanel pnlButton2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlButton2.setBorder(BorderFactory.createEmptyBorder(7, 0, 7, 0));

        lbltim = new JLabel("Nhập tên bàn cần tìm: ");
        lbltim.setFont(new Font("Tahoma", Font.BOLD, 14));

        txttim = new JTextField();
        txttim.setFont(new Font("Tahoma", Font.BOLD, 15));
        txttim.setPreferredSize(new Dimension(150, 25)); 
        btntim = new JButton("Tìm");
        btntim.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnluu = new JButton("Lưu");
        btnluu.setFont(new Font("Tahoma", Font.BOLD, 16));

        pnlButton2.add(lbltim);
        pnlButton2.add(txttim);
        pnlButton2.add(btntim);
        pnlButton2.add(Box.createHorizontalStrut(10));
        pnlButton2.add(btnluu);

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
        
        btnthem.addActionListener(this);
        btnxoa.addActionListener(this);
        btntim.addActionListener(this);
        btnluu.addActionListener(this);
        docDuLieuDBVaoTable();
    }
    
    	  		
	public void docDuLieuDBVaoTable() {
		
		List<Ban> listBan = bandao.layThongTin();
		for (Ban b : listBan ) {
			modelBan.addRow(new Object [] {b.getMaBan(), b.getSoBan(),b.getSoGhe(),b.getKhuVuc().getMaKhuVuc(),b.getPhong().getMaPhong()});
			
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
	  	
		if (o.equals(btnthem)) {
		    String maBan = txtmaBan.getText();
		    String soBan = txttenBan.getText();
		    int soGhe = Integer.parseInt(txtsoGhe.getText().trim());
		    String khuVuc = String.valueOf(cmbkhuVuc.getSelectedItem());
		    String phong = String.valueOf(cmbPhong.getSelectedItem());

		    KhuVucDAO khuVucDAO = new KhuVucDAO();
		    ArrayList<KhuVuc> dsKV = khuVucDAO.layThongTin();
		    PhongDAO phongDao = new PhongDAO();
		    ArrayList<Phong> dsPhong = phongDao.layThongTin();

		    for (KhuVuc kv : dsKV) {
		        if (khuVuc.equalsIgnoreCase(kv.getTenKhuVuc())) {
		            for (Phong p : dsPhong) {
		                if (phong.equalsIgnoreCase(p.getTenPhong())) {
		                    Ban b = new Ban(maBan, soBan, soGhe, kv, p);
		                    if (bandao.themBan(b)) {
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


        if (o.equals(btnxoa)) {
            int r = table.getSelectedRow();
            if (r != -1) {
                String maBan = (String) modelBan.getValueAt(r, 0);
                modelBan.removeRow(r);
                bandao.xoaBan(maBan);
            }
        }
        
        if (o.equals(btntim)) {
            String maPhong = txttim.getText();
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
        /*if (o.equals(btnluu)) {
            int rowCount = modelBan.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                String maBan = (String) modelBan.getValueAt(i, 0);
                String tenBan = (String) modelBan.getValueAt(i, 1);
                String kv = (String) modelBan.getValueAt(i, 2); 
                KhuVuc khuVuc = new KhuVuc(kv);
                
                Phong p = new Phong(maPhong, tenPhong, khuVuc);
                
                try {
                    phongdao.capNhatPhong(p);
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


