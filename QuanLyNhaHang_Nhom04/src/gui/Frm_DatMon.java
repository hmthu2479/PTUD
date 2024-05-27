package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.BanDAO;
import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.KhuVucDAO;
import dao.MonAnDao;
import dao.MonNuocDao;
import dao.NhanVienDAO;
import dao.PhieuDatBanDAO;
import dao.PhongDAO;
import entity.Ban;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuVuc;
import entity.Phong;
import entity.MonAn;
import entity.MonNuoc;
import entity.NhanVien;
import entity.PhieuDatBan;

import javax.swing.border.LineBorder;

public class Frm_DatMon extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField  txtDonGia, txtsoLuong, txtTongTien;
	private DefaultTableModel model;
	private JButton btnThem,  btnXoa, btnLamMoi,btnTaoDon;
	private JComboBox<String> cmbLoaiMon, cmbKhuVuc, cmbBan, cmbPhong, cmbTenMon;
	private JTable table;
	private KhuVucDAO kv_dao;
	private PhongDAO phong_dao;
	private BanDAO ban_dao;
	private NhanVienDAO nv_dao;
	private KhachHangDAO kh_dao;
	private HoaDonDAO hd_dao;
	private ChiTietHoaDonDAO cthd_dao;
	private MonAnDao ma_dao;
	private MonNuocDao mn_dao;
	private JComboBox<String> cmbNhanVien;
	private JButton btnDatTruoc;
	private JLabel lblKhachHang;
	private JLabel lblNhanVien;
	private PhieuDatBanDAO phieu_dao;
	private JComboBox<String> cmbMaPhieu;
	private JLabel lblMaPhieu;
	private JTextField txtKhachHang;

	

	public Frm_DatMon() {
		kv_dao = new KhuVucDAO();
		phong_dao = new PhongDAO();
		ban_dao = new BanDAO();
		nv_dao = new NhanVienDAO();
		kh_dao = new KhachHangDAO();
		ma_dao = new MonAnDao();
		mn_dao = new MonNuocDao();
		hd_dao = new HoaDonDAO();
		cthd_dao = new ChiTietHoaDonDAO();
		phieu_dao = new PhieuDatBanDAO();
		
		setLayout(null);
		// Tiêu đề
		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(new Color(0, 206, 209));
		panelTitle.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		JLabel lblTitLe = new JLabel("ĐẶT MÓN");
		lblTitLe.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitLe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitLe.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblTitLe.setForeground(Color.DARK_GRAY);
		panelTitle.add(lblTitLe);
		panelTitle.setBounds(0, 0, 1540, 60);
		add(panelTitle);

		// Khung thông tin món ăn
		JPanel panelThongTin = new JPanel();
		panelThongTin.setBackground(new Color(0, 128, 128));
		panelThongTin.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panelThongTin.setBounds(0, 68, 1540, 295);
		add(panelThongTin);
		panelThongTin.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBounds(0, 0, 180, 35);
		panelThongTin.add(panel);

		JLabel lbl_titlle_panel_1 = new JLabel("Thông tin món");
		panel.add(lbl_titlle_panel_1);
		lbl_titlle_panel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titlle_panel_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		// Loại Món
		JLabel lblLoaiMon = new JLabel("Loại Món:");
		lblLoaiMon.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLoaiMon.setBounds(451, 125, 86, 40);
		panelThongTin.add(lblLoaiMon);

		cmbLoaiMon = new JComboBox<>();
		cmbLoaiMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbLoaiMon.setEditable(false);
		cmbLoaiMon.setBounds(558, 125, 200, 40);
		cmbLoaiMon.addItem("Món Nước");
		cmbLoaiMon.addItem("Lẩu");
		cmbLoaiMon.addItem("Cơm");
		cmbLoaiMon.addItem("Món ăn khác");
		cmbLoaiMon.addItem("Nước ngọt");
		cmbLoaiMon.addItem("Bia");
		cmbLoaiMon.addItem("Nước uống khác");
		panelThongTin.add(cmbLoaiMon);


		// Nhãn "Tên Món"
		JLabel lblTenMon = new JLabel("Tên Món:");

		lblTenMon.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTenMon.setBounds(804, 75, 100, 40);
		panelThongTin.add(lblTenMon);

		// Tên Món
		cmbTenMon = new JComboBox<String>();
		cmbTenMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbTenMon.setBounds(900, 75, 200, 40);
		cmbTenMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbTenMon.setEditable(false);
		ArrayList<MonAn> list = ma_dao.layThongTin() ;
		for (MonAn mon : list) {
			cmbTenMon.addItem(mon.getTenMonAn());
		}
		ArrayList<MonNuoc> listMonNuoc = mn_dao.layThongTin() ;
		for (MonNuoc mon : listMonNuoc) {
			cmbTenMon.addItem(mon.getTenMonNuoc());
		}

		cmbLoaiMon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chonMon = (String) cmbLoaiMon.getSelectedItem();
                capNhatCmbTheoLoaiMon(chonMon);
            }
        });
		panelThongTin.add(cmbTenMon);
		

		// Đơn giá
		JLabel lblDonGia = new JLabel("Đơn giá:");
		lblDonGia.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDonGia.setBounds(807, 125, 100, 40);
		panelThongTin.add(lblDonGia);

		txtDonGia = new JTextField();
		txtDonGia.setEditable(false);
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDonGia.setBounds(900, 125, 200, 40);
		txtDonGia.setBackground(Color.LIGHT_GRAY);
		panelThongTin.add(txtDonGia);
		txtDonGia.setColumns(10);



		// Khu Vực
		JLabel lblKhuVuc = new JLabel("Khu Vực:");
		lblKhuVuc.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblKhuVuc.setBounds(90, 70, 86, 40);
		panelThongTin.add(lblKhuVuc);

		cmbKhuVuc = new JComboBox<String>();
		cmbKhuVuc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbKhuVuc.setEditable(false);
		cmbKhuVuc.setBounds(200, 76, 200, 39);
		cmbKhuVuc.addItem("Chọn khu vực");
		panelThongTin.add(cmbKhuVuc);
		ArrayList<KhuVuc> listKV = kv_dao.layThongTin() ;
		for (KhuVuc kv : listKV) {
			cmbKhuVuc.addItem(kv.getTenKhuVuc());
		}

        cmbKhuVuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chonKV = (String) cmbKhuVuc.getSelectedItem();
                capNhatCmbTheoKhuVuc(chonKV);
            }
        });

     // Bàn
        JLabel lblBan = new JLabel("Bàn:");
        lblBan.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblBan.setBounds(90, 125, 86, 40);
        panelThongTin.add(lblBan);

        cmbBan = new JComboBox<>();
        cmbBan.setFont(new Font("Tahoma", Font.PLAIN, 16));
        cmbBan.setEditable(false);

        ArrayList<Ban> listBan = ban_dao.layThongTin();
        for (Ban b : listBan) {
            cmbBan.addItem(b.getSoBan());
        }

        cmbBan.setBounds(200, 125, 200, 40);
        panelThongTin.add(cmbBan);

		//Phòng
		JLabel lblPhong = new JLabel("Phòng:");
		lblPhong.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPhong.setBounds(462, 75, 86, 40);
		panelThongTin.add(lblPhong);

		cmbPhong = new JComboBox<String>();
		cmbPhong.setEditable(false);
		cmbPhong.setBounds(558, 76, 200, 39);
		cmbPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
    	cmbPhong.addItem("Chọn phòng");
        ArrayList<Phong> listPhong = phong_dao.layThongTin();
        for (Phong phong : listPhong) {
            cmbPhong.addItem(phong.getTenPhong());
        }

        cmbPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chonPhong = (String) cmbPhong.getSelectedItem();
                String chonKhuVuc = (String) cmbKhuVuc.getSelectedItem();
                capNhatCmbBanTheoPhong(chonPhong, chonKhuVuc);
                
            }
        });

		panelThongTin.add(cmbPhong);

		//Nhân Viên
		lblNhanVien = new JLabel("Nhân viên:");
		lblNhanVien.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNhanVien.setBounds(1152, 125, 100, 40);
		panelThongTin.add(lblNhanVien);

		cmbNhanVien = new JComboBox<String>();
		cmbNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbNhanVien.setEditable(false);
        ArrayList<NhanVien> listNV = nv_dao.layThongTin();
        for (NhanVien nv : listNV) { 
        	cmbNhanVien.addItem(nv.getHoTenNV());
        }
        cmbNhanVien.setBounds(1260, 125, 200, 40);
		panelThongTin.add(cmbNhanVien);
		
		//Khách hàng
		lblKhachHang = new JLabel("Khách hàng:");
		lblKhachHang.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblKhachHang.setBounds(787, 175, 110, 40);
		panelThongTin.add(lblKhachHang);
		lblKhachHang.setVisible(false);
	
		txtKhachHang = new JTextField();
		txtKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtKhachHang.setColumns(10);
		txtKhachHang.setBounds(900, 175, 200, 40);
		txtKhachHang.setVisible(false);
		txtKhachHang.setEditable(false);
		txtKhachHang.setBackground(Color.LIGHT_GRAY);
		panelThongTin.add(txtKhachHang);
	
		

		lblMaPhieu = new JLabel("Mã Phiếu ĐB:");
		lblMaPhieu.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMaPhieu.setBounds(1132, 175, 110, 40);
		panelThongTin.add(lblMaPhieu);
		lblMaPhieu.setVisible(false);
		cmbMaPhieu = new JComboBox<String>();
		cmbMaPhieu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbMaPhieu.setEditable(false);
        ArrayList<PhieuDatBan> listPhieu = phieu_dao.layThongTin();
        for (PhieuDatBan phieu : listPhieu) { 
        	cmbMaPhieu.addItem(phieu.getMaPhieu());
        }
        cmbMaPhieu.setBounds(1260, 175, 200, 40);
		panelThongTin.add(cmbMaPhieu);
		cmbMaPhieu.setVisible(false);
		cmbMaPhieu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String chonPhieu = (String) cmbMaPhieu.getSelectedItem();
				capNhatCmbTheoMaPhieDB(chonPhieu);
			}
		});
		
		// Số lượng
		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSoLuong.setBounds(1152, 75, 100, 40);
		panelThongTin.add(lblSoLuong);

		txtsoLuong = new JTextField();
		txtsoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtsoLuong.setColumns(10);
		txtsoLuong.setBounds(1260, 75, 200, 40);
		panelThongTin.add(txtsoLuong);

		// Sự kiện

		// Nút thêm
		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnThem.setBounds(40, 230, 130, 45);
		panelThongTin.add(btnThem);

		// Nút xóa
		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnXoa.setBounds(205, 230, 130, 45);
		panelThongTin.add(btnXoa);

		// Nút làm mới
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLamMoi.setBounds(380, 230, 130, 45);
		panelThongTin.add(btnLamMoi);
		
		// Nút đặt trước
		btnDatTruoc = new JButton("Đặt trước");
		btnDatTruoc.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDatTruoc.setBounds(580, 230, 150, 45);
		panelThongTin.add(btnDatTruoc);


		// Thêm sự kiện cho các nút
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLamMoi.addActionListener(this);

		// Khung bảng dữ liệu
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 373, 1057, 427);
		add(scrollPane_2);

		String[] Header = {"Tên Món","Đơn Giá", "Số Lượng"};
		model = new DefaultTableModel(Header, 0);
		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane_2.setViewportView(table);

		// Khung tạo đơn
		JPanel panelTaoDon = new JPanel();
		panelTaoDon.setBackground(new Color(255, 255, 204));
		panelTaoDon.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panelTaoDon.setBounds(1067, 373, 473, 427);
		add(panelTaoDon);
		panelTaoDon.setLayout(null);

		// Nhãn "Tạo đơn"
		JLabel lblThanhToan = new JLabel("Tạo đơn");
		lblThanhToan.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblThanhToan.setBounds(179, 21, 193, 60);
		panelTaoDon.add(lblThanhToan);

		// Nhãn "Tổng tiền"
		JLabel lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setForeground(Color.RED);
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTongTien.setBounds(30, 129, 100, 30);
		panelTaoDon.add(lblTongTien);

		// Ô nhập liệu tổng tiền
		txtTongTien = new JTextField();
		txtTongTien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTongTien.setEditable(false);
		txtTongTien.setBounds(139, 119, 284, 49);
		panelTaoDon.add(txtTongTien);
		txtTongTien.setColumns(10);

		// Nút tạo đơn
		btnTaoDon = new JButton("Tạo Đơn");
		btnTaoDon.setForeground(new Color(0, 139, 139));
		btnTaoDon.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTaoDon.setBounds(170, 240, 152, 60);
		panelTaoDon.add(btnTaoDon);

		// Thêm sự kiện cho các nút
		btnTaoDon.addActionListener(this);
		btnDatTruoc.addActionListener(this);

		
		// Hiển thị giao diện
		setVisible(true);
	}

	// Xử lý sự kiện cho các nút
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		// Nút thêm
		if (source == btnThem) {
			try {
				
				String tenMon = cmbTenMon.getSelectedItem().toString();
				String donGiaString = txtDonGia.getText().trim();
				String soLuongString = txtsoLuong.getText().trim();

				if (tenMon.isEmpty() || donGiaString.isEmpty() || soLuongString.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin món ăn!");
					return;
				}

				double donGia = Double.parseDouble(donGiaString);
				int soLuong = Integer.parseInt(soLuongString);

				// Thêm dữ liệu vào bảng
				model.addRow(new Object[]{tenMon, donGia, soLuong});

				// Cập nhật tổng tiền
				capNhatTongTien();

				// Xóa dữ liệu trong các ô nhập liệu
				cmbTenMon.setSelectedIndex(0);;
				txtDonGia.setText("");
				txtsoLuong.setText("");
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Đơn giá và số lượng phải là số!");
			}
		}

		// Nút đặt trước
		if (source == btnDatTruoc) {
			// TODO: Implement search functionality
			txtKhachHang.setVisible(true);
			lblKhachHang.setVisible(true);
			cmbMaPhieu.setVisible(true);
			lblMaPhieu.setVisible(true);
		}

		
		// Nút xóa
		if (source == btnXoa) {
			// Get the selected row indices from the table
			int[] selectedRows = table.getSelectedRows();

			// Check if at least one row is selected
			if (selectedRows.length > 0) {
				// Confirm the deletion with the user
				int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa các dòng đã chọn?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					// Remove the selected rows from the table
					for (int i = selectedRows.length - 1; i >= 0; i--) {
						model.removeRow(selectedRows[i]);
					}

					// Recalculate the total price
					capNhatTongTien();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn một hoặc nhiều dòng để xóa!");
			}
		}

		// Nút làm mới
		if (source == btnLamMoi) {
			// Xóa tất cả dữ liệu khỏi bảng
			model.setRowCount(0);

			// Cập nhật tổng tiền thành 0
			txtTongTien.setText("0");

			// Xóa dữ liệu trong các ô nhập liệu
			cmbTenMon.setSelectedIndex(0);
			cmbKhuVuc.setSelectedIndex(0);
			cmbPhong.setSelectedIndex(0);
			cmbNhanVien.setSelectedIndex(0);
			txtKhachHang.setVisible(false);
			lblKhachHang.setVisible(false);
			cmbNhanVien.setVisible(true);
			lblNhanVien.setVisible(true);
			cmbMaPhieu.setVisible(false);
			lblMaPhieu.setVisible(false);
			txtDonGia.setText("");
			txtsoLuong.setText("");
		}

		// Nút tạo đơn
		if (source == btnTaoDon) {
		    // TODO: Implement payment functionality
		    int model_count = model.getRowCount();
		    if (model_count == 0) {
		        JOptionPane.showMessageDialog(null, "Bạn chưa có sản phẩm để đặt hàng, vui lòng thêm sản phẩm để đặt hàng");
		    } else if (cmbNhanVien.getSelectedItem() == null) {
		        JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên thanh toán");
		    } else {
		        String maHD = maTangDan();
		        // lấy ra mã nhân viên
		        NhanVien nv = new NhanVien();
		        ArrayList<NhanVien> dsnv = nv_dao.layThongTin();
		        for (NhanVien ab : dsnv) {
		            if (ab.getHoTenNV().equalsIgnoreCase(cmbNhanVien.getSelectedItem().toString())) {
		                nv = new NhanVien(ab.getMaNV());
		            }
		        }
		        Date ngayLap = Calendar.getInstance().getTime();
		        double tongTien = Double.parseDouble(txtTongTien.getText());
		        String khuVuc = cmbKhuVuc.getSelectedItem().toString();
		        String phong = cmbPhong.getSelectedItem().toString().equals("Chọn phòng") ? null : cmbPhong.getSelectedItem().toString();
		        String ban = cmbBan.getSelectedItem().toString();


		        KhuVucDAO khuVucDAO = new KhuVucDAO();
		        ArrayList<KhuVuc> dsKV = khuVucDAO.layThongTin();
		        PhongDAO phongDao = new PhongDAO();
		        ArrayList<Phong> dsPhong = phongDao.layThongTin();
		        BanDAO banDao = new BanDAO();
		        ArrayList<Ban> dsBan = banDao.layThongTin();
		        NhanVienDAO nvDao = new NhanVienDAO();
		        ArrayList<NhanVien> dsNV = nvDao.layThongTin();
		        KhachHangDAO khDao = new KhachHangDAO();
		        ArrayList<KhachHang> dsKH = khDao.layThongTin();

		        KhuVuc kv = timTenKhuVuc(dsKV, khuVuc);
		        Phong ph = null;
		        if (phong != null && !phong.trim().equalsIgnoreCase("Chọn phòng")) {
		            ph = timTenPhong(dsPhong, phong);
		        }
		        Ban tenBan = timTenBan(dsBan, ban);
		        NhanVien nhanVien = timTenNhanVien(dsNV, cmbNhanVien.getSelectedItem().toString());
		        
		        KhachHang kh = null;
		        String KhachHang = (String) txtKhachHang.getText();
		        if (txtKhachHang.isVisible() && KhachHang != null) {
		            kh = timTenKhachHang(dsKH, KhachHang);
		        }
		        
		        String ngayDat = null;
		        String phieu = (String) cmbMaPhieu.getSelectedItem().toString();
		        if (cmbMaPhieu.isVisible() && phieu != null) {
			        ArrayList<PhieuDatBan> pdb = phieu_dao.layPhieuTheoMa(phieu);
			        
			        ngayDat = layNgayDatTuMaPhieu(pdb, cmbMaPhieu);
			        System.out.println(ngayDat);
		        }

;

		        //Chuyển từ String sang Date     
		        java.util.Date ngayDatDate = null;
		        if (ngayDat != null) {
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		            try {
		                ngayDatDate = sdf.parse(ngayDat);
		            } catch (ParseException e1) {
		                e1.printStackTrace();
		                // Handle the exception as needed
		                return;
		            }
		        }
		        HoaDon hd = new HoaDon(maHD, tongTien, kv, ph, tenBan, nhanVien, ngayLap, kh, ngayDatDate);
		        if (hd_dao.timHoaDon(maHD).isEmpty()) {
		            if (hd_dao.themHoaDon(hd)) {
		                // lấy thông tin trong bảng
		                int row = model.getRowCount();
		                for (int j = 0; j < row; j++) {
		                    int soLuong = (int) model.getValueAt(j, 2);
		                    String mon = model.getValueAt(j, 0).toString();
		                    double donGia = (double) model.getValueAt(j, 1);
		                    double thanhTien = soLuong * donGia;
		                    ChiTietHoaDon cthd = new ChiTietHoaDon(hd, mon, soLuong, donGia, thanhTien);
		                    cthd_dao.themDon(cthd);
		                }

		                JOptionPane.showMessageDialog(null, "Tạo đơn thành công");
		                txtTongTien.setText("");
		                // xóa hết dữ liệu trong bảng đơn hàng
		                int rowCount = model.getRowCount();
		                for (int t = rowCount - 1; t >= 0; t--) {
		                    model.removeRow(t);
		                }
		                
		            }
		        }
		    }
		}
	}
	



		
	
	private KhuVuc timTenKhuVuc(ArrayList<KhuVuc> dsKV, String tenKhuVuc) {
	    for (KhuVuc kv : dsKV) {
	        if (tenKhuVuc.equalsIgnoreCase(kv.getTenKhuVuc())) {
	            return kv;
	        }
	    }
	    return null;
	}

	private Phong timTenPhong(ArrayList<Phong> dsPhong, String tenPhong) {
	    for (Phong ph : dsPhong) {
	        if (tenPhong.equalsIgnoreCase(ph.getTenPhong())) {
	            return ph;
	        }
	    }
	    return null;
	}

	private Ban timTenBan(ArrayList<Ban> dsBan, String soBan) {
	    for (Ban ban : dsBan) {
	        if (soBan.equalsIgnoreCase(ban.getSoBan())) {
	            return ban;
	        }
	    }
	    return null;
	}

	private NhanVien timTenNhanVien(ArrayList<NhanVien> dsNV, String tenNV) {
	    for (NhanVien nv : dsNV) {
	        if (tenNV.equalsIgnoreCase(nv.getHoTenNV())) {
	            return nv;
	        }
	    }
	    return null;
	}

	private KhachHang timTenKhachHang(ArrayList<KhachHang> dsKH, String tenKH) {
	    for (KhachHang kh : dsKH) {
	        if (tenKH.equalsIgnoreCase(kh.getTenKH())) {
	            return kh;
	        }
	    }
	    return null;
	}
	
	
	// Lấy tên khách hàng đã lấy được từ ds Phiếu so sánh với tên trên cmb để lấy ngày đặt phiếu
	private String layNgayDatTuMaPhieu(ArrayList<PhieuDatBan> dsPDB, JComboBox<String> cmbMaPhieu) {
		if (cmbMaPhieu.isVisible()) {
			String chonMa = (String) cmbMaPhieu.getSelectedItem();
		    for (PhieuDatBan pdb : dsPDB) {
		        String ma = pdb.getMaPhieu();
		        if (ma.equals(chonMa)) {
	                return pdb.getNgayDat();
	            }
	        }
		}
		return null;
	}
	
	
	
	
	private void capNhatCmbTheoLoaiMon(String loaiMon) {
	    cmbTenMon.removeAllItems(); 
	    txtDonGia.setText("");

	    // Kiểm tra loại món đã chọn có phải là món ăn không
	    if (isLoaiMonInMonAn(loaiMon)) {
	        // Nếu đúng lấy danh sách món ăn từ loại món đó
	        ArrayList<MonAn> listMonAn = ma_dao.layThongTin();
	        for (MonAn mon : listMonAn) {
	            if (mon.getLoaiMonAn().equals(loaiMon)) {
	                cmbTenMon.addItem(mon.getTenMonAn());
	                txtDonGia.setText(String.valueOf(mon.getDonGia())); 
	            }
	        }
	    } else {
	        // Chọn nước uống từ database
	        // Lấy danh sách nước uống từ loại món đó
	        ArrayList<MonNuoc> listMonNuoc = mn_dao.layThongTin();
	        for (MonNuoc mon : listMonNuoc) {
	            if (mon.getLoaiMonNuoc().equals(loaiMon)) {
	                cmbTenMon.addItem(mon.getTenMonNuoc());
	                txtDonGia.setText(String.valueOf(mon.getDonGia())); 
	            }
	        }
	    }
	}

	private boolean isLoaiMonInMonAn(String loaiMon) {
	    ArrayList<MonAn> listMonAn = ma_dao.layThongTin();
	    for (MonAn mon : listMonAn) {
	        if (mon.getLoaiMonAn().equals(loaiMon)) {
	            return true;
	        }
	    }
	    return false;
	}
	private int count = 0;

	private String maTangDan() {
	    String ma = hd_dao.layMaMoiNhat();
	 // Lấy phần số của mã bàn (bỏ đi ký tự "HD") và tăng giá trị lên 1
	    count = Integer.parseInt(ma.substring(2)) + 1; 
	    return String.format("HD%03d", count);
	}
	private void capNhatCmbTheoMaPhieDB(String maPhieu) {
	    List<PhieuDatBan> phieuDatBanList = phieu_dao.layPhieuTheoMa(maPhieu);
	    

	    // Xóa các giá trị trong cmb
	    cmbKhuVuc.removeAllItems();
	    cmbPhong.removeAllItems();
	    cmbBan.removeAllItems();
	    txtKhachHang.setText("");

	    // Thêm các giá trị duy nhất từ các đối tượng Phieudatban được truy xuất
	    Set<String> khachHangSet = new HashSet<>();
	    Set<String> khuVucSet = new HashSet<>();
	    Set<String> phongSet = new HashSet<>();
	    Set<String> banSet = new HashSet<>();

	    for (PhieuDatBan phieuDatBan : phieuDatBanList) {
	        khuVucSet.add(phieuDatBan.getKhuVuc().getMaKhuVuc());
	        phongSet.add(phieuDatBan.getPhong().getMaPhong());
	        banSet.add(phieuDatBan.getTenBan().getMaBan());
	        khachHangSet.add(phieuDatBan.getKhachHang().getMaKH());
	    }

	    // Thêm giá trị vào combobox
	    for (String khuVuc : khuVucSet) {
	        cmbKhuVuc.addItem(khuVuc);
	    }
	    cmbPhong.removeAllItems();
	    for (String phong : phongSet) {
	    	if (phong == null ) {
	            cmbPhong.addItem("Chọn phòng");
	        } else {
	            cmbPhong.addItem(phong);
	        }    	
	    }
	    for (String ban : banSet) {
	        cmbBan.addItem(ban);
	    }
	    for(String kH : khachHangSet) {
	    	txtKhachHang.setText(kH);
	    	break;
	    }

	    
	}
	private void capNhatCmbTheoKhuVuc(String chonKV) {
	    cmbPhong.removeAllItems();
	    cmbBan.removeAllItems();
	    cmbPhong.addItem("Chọn phòng");
	    ArrayList<Phong> listPhong = phong_dao.layThongTinPhongTheoKhuVuc(chonKV);
	    for (Phong phong : listPhong) { 
	        cmbPhong.addItem(phong.getTenPhong());
	    }

	    ArrayList<Ban> listBan = ban_dao.layThongTinTheoKhuVuc(chonKV);
	    for (Ban ban : listBan) {
	    	cmbBan.addItem(ban.getSoBan());
	    }
	}

	private void capNhatCmbBanTheoPhong(String chonPhong, String chonKhuVuc) {
		cmbBan.removeAllItems();
	    if (chonPhong == null || chonPhong.equals("Chọn phòng")) {
	        ArrayList<Ban> listBan = ban_dao.layThongTinTheoPhong(chonPhong, chonKhuVuc);
	        for (Ban b : listBan) {
	            if (b.getPhong() == null) {
	            	cmbBan.addItem(b.getSoBan());
	            	
	            }
	        }
	    } 
	    if (chonPhong != null) {
	        ArrayList<Ban> listBan = ban_dao.layThongTinTheoPhong(chonPhong,chonKhuVuc);
	        for (Ban b : listBan) {
	            if (b.getPhong() != null) {
	            	cmbBan.addItem(b.getSoBan());
	            }
	        }
	    } 
	}
	// Cập nhật tổng tiền
	public double capNhatTongTien() {
	    double tongTien = 0;
	    for (int i = 0; i < model.getRowCount(); i++) {
	        double donGia = (double) model.getValueAt(i, 1);
	        int soLuong = (int) model.getValueAt(i, 2);
	        tongTien += donGia * soLuong;
	    }
	    txtTongTien.setText(String.format("%.2f", tongTien));
	    return tongTien;
	}

	// Get the table model
	public DefaultTableModel getModel() {
		return model;
	}
}



