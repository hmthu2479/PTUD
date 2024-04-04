package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connectDB.ConnectDB;
import dao.BanDAO;
import dao.KhachHangDAO;
import dao.KhuVucDAO;
import dao.NhanVienDAO;
import dao.PhieuDatBanDAO;
import dao.PhongDAO;
import entity.Ban;
import entity.KhachHang;
import entity.KhuVuc;
import entity.NhanVien;
import entity.PhieuDatBan;
import entity.Phong;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Frm_DatBan extends JPanel implements ActionListener {
    private JSpinner spn_ngayThang;
    private JComboBox<String> cmb_gioDat;
    private JComboBox<Integer> cmb_soLuongNguoi;
    private JTextField txt_hoTen;
    private JTextField txt_sdt;
    private JTextField  txt_diaChi;
    private JComboBox<String> cmb_khuVuc;
    private JComboBox<String> cmb_ban;
	private JTextField txtMess;
	private JButton btn_Them,btn_Tim, btn_Xoa, btn_xoaRong;
	private JTextField txt_maPhieu;
	private DefaultTableModel modelPhieu;
	private JTable table;
	private JTextField txt_Tim;
	private JLabel lbl_Tim;
	private JComboBox <String> cmb_nhanVien;
	private KhuVucDAO kv_dao;
	private PhongDAO phong_dao;
	private BanDAO ban_dao;
	private NhanVienDAO nv_dao;
	private PhieuDatBanDAO phieu_dao;
	private JComboBox<String> cmb_khachHang;
	private KhachHangDAO kh_dao;

    public Frm_DatBan() {
    	try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kv_dao = new KhuVucDAO();
		phong_dao = new PhongDAO();
		ban_dao = new BanDAO();
		nv_dao = new NhanVienDAO();
		phieu_dao = new PhieuDatBanDAO();
		kh_dao = new KhachHangDAO();
		
        setLayout(new BorderLayout());
        

        JPanel mainPanel = new JPanel(new BorderLayout(15, 5));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(160, 210, 180));

        JPanel pnl_N = new JPanel(new GridLayout(3, 4, 0, 10));
        pnl_N.setBackground(new Color(160, 210, 180)); 
        pnl_N.setBorder(new EmptyBorder(5, 0, 10, 0));

        // Ngày tháng năm
        JPanel pnl_ngayThang = new JPanel(new BorderLayout());
        pnl_ngayThang.setBackground(new Color(160, 210, 180));
        pnl_ngayThang.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black, 2), "Ngày đặt", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        SpinnerDateModel dateModel = new SpinnerDateModel();
        spn_ngayThang = new JSpinner(dateModel);
        spn_ngayThang.setEditor(new JSpinner.DateEditor(spn_ngayThang, "dd/MM/yyyy"));
        spn_ngayThang.setFont(new Font("Arial", Font.BOLD, 20));
        pnl_ngayThang.add(spn_ngayThang, BorderLayout.CENTER);
        pnl_N.add(pnl_ngayThang);

        // Giờ đặt
        JPanel pnl_gioDat = new JPanel(new BorderLayout());
        pnl_gioDat.setBackground(new Color(160, 210, 180)); 
        pnl_gioDat.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Giờ đặt", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        String[] timeOptions = {"10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM"};
        cmb_gioDat = new JComboBox<>(timeOptions);
        cmb_gioDat.setFont(new Font("Arial", Font.BOLD, 20));
        pnl_gioDat.add(cmb_gioDat, BorderLayout.CENTER);
        pnl_N.add(pnl_gioDat);

        // Số lượng người
        JPanel pnl_soLuongNguoi = new JPanel(new BorderLayout());
        pnl_soLuongNguoi.setBackground(new Color(160, 210, 180)); 
        pnl_soLuongNguoi.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Số lượng người", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        Integer[] peopleOptions = {1, 2, 3, 4, 5};
        cmb_soLuongNguoi = new JComboBox<>(peopleOptions);
        cmb_soLuongNguoi.setFont(new Font("Arial", Font.BOLD, 20));
        pnl_soLuongNguoi.add(cmb_soLuongNguoi, BorderLayout.CENTER);
        pnl_N.add(pnl_soLuongNguoi);
        // Nhân viên
        
        JPanel pnl_nhanVien = new JPanel(new BorderLayout());
        pnl_nhanVien.setBackground(new Color(160, 210, 180)); 
        pnl_nhanVien.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Nhân viên", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        cmb_nhanVien = new JComboBox<String>();
        cmb_nhanVien.setEditable(false);	
		
		ArrayList<NhanVien> listNV = nv_dao.layThongTin() ;
		for (NhanVien nv : listNV) {
			cmb_nhanVien.addItem(nv.getHoTenNV());
		}
        cmb_nhanVien.setFont(new Font("Arial", Font.BOLD, 20));
        pnl_nhanVien.add(cmb_nhanVien, BorderLayout.CENTER);
        pnl_N.add(pnl_nhanVien);
 
        // Khách hàng
        
        JPanel pnl_khachHang = new JPanel(new BorderLayout());
        pnl_khachHang.setBackground(new Color(160, 210, 180)); 
        pnl_khachHang.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Khách hàng", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        cmb_khachHang = new JComboBox<String>();
        cmb_khachHang.setEditable(false);	
		
		ArrayList<KhachHang> listKH = kh_dao.layThongTin() ;
		for (KhachHang kh : listKH) {
			cmb_khachHang.addItem(kh.getTenKH());
		}
		cmb_khachHang.setFont(new Font("Arial", Font.BOLD, 20));
		pnl_khachHang.add(cmb_khachHang, BorderLayout.CENTER);
        pnl_N.add(pnl_khachHang);

        // Khu vực
        
        JPanel pnl_khuVuc = new JPanel(new BorderLayout());
        pnl_khuVuc.setBackground(new Color(160, 210, 180)); 
        pnl_khuVuc.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Khu vực", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        cmb_khuVuc = new JComboBox<String>();
        cmb_khuVuc.setEditable(false);	
		
		ArrayList<KhuVuc> listKV = kv_dao.layThongTin() ;
		for (KhuVuc kv : listKV) {
			cmb_khuVuc.addItem(kv.getTenKhuVuc());
		}
        cmb_khuVuc.setFont(new Font("Arial", Font.BOLD, 20));
        pnl_khuVuc.add(cmb_khuVuc, BorderLayout.CENTER);
        pnl_N.add(pnl_khuVuc);
        
        
     // Bàn
        JPanel pnl_Ban = new JPanel(new BorderLayout());
        pnl_Ban.setBackground(new Color(160, 210, 180));
        pnl_Ban.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Bàn", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));

        cmb_ban = new JComboBox<String>();
        cmb_ban.setEditable(false);	

        ArrayList<Ban> listBan = ban_dao.layThongTin();
        for (Ban b : listBan) {
            Phong phong = b.getPhong();
            String tenPhong = phong.getMaPhong();
            String soBan = b.getSoBan(); 
            String tenBan = soBan + " " + tenPhong; 
            cmb_ban.addItem(tenBan);
        }

        cmb_ban.setFont(new Font("Arial", Font.BOLD, 20));
        cmb_ban.setForeground(Color.BLACK);
        pnl_Ban.add(cmb_ban, BorderLayout.CENTER);
        pnl_N.add(pnl_Ban);
        
        
      //Mã phiếu
        JPanel pnl_maPhieu = new JPanel(new BorderLayout());
        pnl_maPhieu.setBackground(new Color(160, 210, 180)); // White
        pnl_maPhieu.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Mã Phiếu", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        txt_maPhieu = new JTextField(30);
        txt_maPhieu.setFont(new Font("Roboto", Font.BOLD, 16));
        pnl_maPhieu.add(txt_maPhieu, BorderLayout.CENTER);
        pnl_N.add(pnl_maPhieu);
        
        
        //Buttons
        JPanel pnl_btn = new JPanel(new GridLayout(2, 3));
        pnl_btn.setBackground(new Color(160, 210, 180));
        pnl_btn.setBorder(new EmptyBorder(5, 2, 0, 2));

        int horizontalGap = 5; 
        int verticalGap = 5; 

        GridLayout gridLayout = (GridLayout) pnl_btn.getLayout();
        gridLayout.setHgap(horizontalGap);
        gridLayout.setVgap(verticalGap);

        btn_Them = new JButton("Thêm");
        btn_Xoa = new JButton("Xóa");
        btn_xoaRong = new JButton("Xóa rỗng");
        lbl_Tim = new JLabel("Nhập mã cần tìm:");
        lbl_Tim.setFont(new Font("Arial", Font.BOLD, 16));
        txt_Tim = new JTextField(12);
        btn_Tim = new JButton("Tìm kiếm");

        pnl_btn.add(btn_Them);
        pnl_btn.add(btn_Xoa);
        pnl_btn.add(btn_xoaRong);
        JPanel pnl_tim = new JPanel();
        pnl_tim.setBackground(new Color(160, 210, 180));
        pnl_tim.setLayout(new FlowLayout(FlowLayout.CENTER, 7, 7));
        pnl_tim.add(lbl_Tim);
        txt_Tim.setPreferredSize(new Dimension(200, 23)); 
        pnl_tim.add(txt_Tim);
        pnl_tim.add(btn_Tim);

        Font bFont = new Font("Arial", Font.BOLD, 16);
        btn_Them.setFont(bFont);
        btn_Xoa.setFont(bFont);
        btn_xoaRong.setFont(bFont);
        btn_Tim.setFont(bFont);
        pnl_N.add(pnl_tim);
        pnl_N.add(pnl_btn);

        mainPanel.add(pnl_N, BorderLayout.NORTH);

        add(mainPanel);

        JPanel rightPanel = new JPanel(new BorderLayout());

        // Table
        String[] columnNames = {"Mã", "Khu vực", "Số bàn", "SL","Ngày đặt","Ngày lập","Giờ đặt","Khách hàng", "Nhân viên"};

        modelPhieu = new DefaultTableModel(columnNames, 0);
		table = new JTable(modelPhieu);
        table.setFont(new Font("Arial", Font.BOLD, 16));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        table.setRowHeight(30);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
        Color lightBlue = new Color(173, 216, 230); 
        header.setBackground(lightBlue);
        header.setFont(new Font("Arial", Font.BOLD, 18));

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        rightPanel.add(tableScrollPane, BorderLayout.CENTER);

        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        mainPanel.add(rightPanel, BorderLayout.CENTER);


                
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(160, 210, 180));
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); 
        btnPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        
        btnPanel.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 15));
        
        JButton btnChonMon = new JButton("Chọn món");
        JButton btnLuu = new JButton("Lưu");
        JButton btnQuayLai = new JButton("Quay lại");

        Font btnFont = new Font("Arial", Font.BOLD, 17); 
        btnChonMon.setFont(btnFont);
        btnLuu.setFont(btnFont);
        btnQuayLai.setFont(btnFont);

       
        Dimension buttonSize = new Dimension(130, 35);
        btnChonMon.setPreferredSize(buttonSize);
        btnLuu.setPreferredSize(buttonSize);
        btnQuayLai.setPreferredSize(buttonSize);
        
        btnPanel.add(btnChonMon);
        btnPanel.add(btnLuu);
        btnPanel.add(btnQuayLai);

        mainPanel.add(btnPanel, BorderLayout.SOUTH);
                
        btn_Them.addActionListener(this );
        btn_Xoa.addActionListener(this);
        btn_xoaRong.addActionListener(this);
        btn_Tim.addActionListener(this);
        
        
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btn_Them)) {
		    Date selectedDate = (Date) spn_ngayThang.getValue();
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		    String ngayDat = formatter.format(selectedDate);
		    String gio = String.valueOf(cmb_gioDat.getSelectedItem());
		    int soLuong = (int) cmb_soLuongNguoi.getSelectedItem();
		    String khuVuc = String.valueOf(cmb_khuVuc.getSelectedItem());
		    String khachHang = String.valueOf(cmb_khachHang.getSelectedItem());
		    String ma = txt_maPhieu.getText();
		    String tenBan = String.valueOf(cmb_ban.getSelectedItem());
		    String nhanVien = String.valueOf(cmb_nhanVien.getSelectedItem());
		    LocalDate ngayLap = LocalDate.now();
		    
		    KhuVuc kv =new KhuVuc(khuVuc);
		    NhanVien nv = new NhanVien(nhanVien);
		    KhachHang kh = new KhachHang(khachHang);
		    Ban b = new Ban(tenBan);
		    
		    PhieuDatBan p = new PhieuDatBan(ma, kv, b, soLuong, ngayDat, ngayLap, gio,kh, nv);
		    
		    if(phieu_dao.themPhieu(p)) {
		        JOptionPane.showMessageDialog(this, "Trùng mã");
		    }
		    else {
		        modelPhieu.addRow(new Object[] {p.getMaPhieu(), p.getKhuVuc().getMaKhuVuc(), p.getSoBan().getMaBan(), p.getSoLuongNguoi(),
		         p.getNgayDat(), p.getNgayLap(), p.getGioDat(), p.getKhachHang().getMaKH(), p.getNhanVien().getMaNV()
		        });
		    }
		}

		if (o.equals(btn_Xoa)) {
            int r = table.getSelectedRow();
            if (r != -1) {
                String maPhieu = (String) modelPhieu.getValueAt(r, 0);
                modelPhieu.removeRow(r);
                phieu_dao.xoaPhieu(maPhieu);
            }
        }

		if (o.equals(btn_xoaRong)) {
		    txt_maPhieu.setText("");
		    txt_hoTen.setText("");
		    txt_diaChi.setText("");
		    txt_sdt.setText("");

		    // Assuming spn_ngayThang is a JSpinner, set its value properly
		    spn_ngayThang.setValue(new Date());

		    cmb_gioDat.setSelectedIndex(0);
		    cmb_soLuongNguoi.setSelectedIndex(0);
		    cmb_khuVuc.setSelectedIndex(0);
		    cmb_ban.setSelectedIndex(0);
		}
	}

		public void docDuLieuDBVaoTable() {
		    List<PhieuDatBan> listPhieu = phieu_dao.layThongTin();
		    for (PhieuDatBan p : listPhieu ) {
		        modelPhieu.addRow(new Object[] {p.getMaPhieu(), p.getKhuVuc(), p.getSoBan(), p.getSoLuongNguoi(),
		             p.getNgayDat(), p.getNgayLap(), p.getGioDat(), p.getKhachHang().getMaKH(), p.getNhanVien().getMaNV()});
		    }
		}

}