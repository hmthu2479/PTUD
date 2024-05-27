package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import dao.BanDAO;
import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.KhuVucDAO;
import dao.NhanVienDAO;
import dao.PhongDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuVuc;
import entity.Mon;
import entity.NhanVien;

public class Frm_DanhSachHoaDon extends JPanel implements ActionListener{
	  private static final long serialVersionUID = 1L;
	    private JTable table;
	    private JButton btnNewButton;
	    private Frm_DanhSachHoaDon inhoadon;
	    private JTable table_1;
		private HoaDonDAO hd_dao;
		private DefaultTableModel tablemodel;
		private DefaultTableModel cthdModel;
		private ArrayList<HoaDon> HoaDon;
		private ChiTietHoaDonDAO cthd_dao;
		private KhuVucDAO khuvuc_dao;
		private ArrayList<KhuVuc> KhuVuc;
		private PhongDAO phong_dao;
		private NhanVienDAO nv_dao;
		private BanDAO ban_dao;
		private KhachHangDAO kh_dao;
		private JDateChooser dateNgayLap;
		private JButton btnTimHoaDon;
		private JButton btnLamMoi;


	    /**
	     * Create the panel.
	     */
	    public Frm_DanhSachHoaDon() {
	    	setSize(2050,1050);
	    	setBackground(new Color(255,182,193));
	        setBounds(100, 100, 1304, 750);
	        setLayout(null);


			phong_dao = new PhongDAO();
			ban_dao = new BanDAO();
			nv_dao = new NhanVienDAO();
			kh_dao = new KhachHangDAO();
	        hd_dao = new HoaDonDAO();
	        cthd_dao = new ChiTietHoaDonDAO();
	        khuvuc_dao = new KhuVucDAO();
	        
	        JScrollPane scrollPane = new JScrollPane();
	        scrollPane.setBounds(0, 192, 1537, 179);
	        add(scrollPane);
	        
			String[] header1 = {"Mã hóa đơn", "Tổng tiền", "Khu vực", "Phòng", "Bàn ăn", "Nhân viên", "Ngày lập","Khách hàng ","Ngày đặt"};
			tablemodel = new DefaultTableModel(header1, 0);
	        table = new JTable(tablemodel);
	        JTableHeader tableHeader = table.getTableHeader();
	        tableHeader.setFont(new Font("Arial", Font.BOLD, 20));
	        scrollPane.setViewportView(table);
	    	table.setFont(new Font("Arial", Font.PLAIN, 18));
			table.setRowHeight(20);
	        table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int r = table.getSelectedRow();
					String MaHoaDon = tablemodel.getValueAt(r, 0).toString();
					docDuLieuCTHD(MaHoaDon);

				}
			});
	        
	        JLabel lblNewLabel = new JLabel("Danh sách hóa đơn");
	        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 32));
	        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNewLabel.setBounds(590, 11, 353, 38);
	        add(lblNewLabel);
	        
	        JLabel lblChiTitHa = new JLabel("Chi tiết hóa đơn");
	        lblChiTitHa.setHorizontalAlignment(SwingConstants.CENTER);
	        lblChiTitHa.setFont(new Font("Arial", Font.BOLD, 32));
	        lblChiTitHa.setBounds(585, 382, 353, 38);
	        add(lblChiTitHa);
	        
	        JScrollPane scrollPane_1 = new JScrollPane();
	        scrollPane_1.setBounds(0, 431, 1950, 237);
	        add(scrollPane_1);
	        

			String[] header = {
		    		"Mã hóa đơn","Tên món","Số lượng", "Đơn giá", "Thành tiền"};
			cthdModel = new DefaultTableModel(header, 0);
	        
	        table_1 = new JTable(cthdModel);
	        scrollPane_1.setViewportView(table_1);
	        JTableHeader Header = table_1.getTableHeader();
	        Header.setFont(new Font("Arial", Font.BOLD, 20));
	        table_1.setFont(new Font("Arial", Font.PLAIN, 18));
	        table_1.setRowHeight(20);
	        
	        JPanel panel = new JPanel();
	        panel.setBounds(0, 49, 2050, 125);
	        add(panel);
	        panel.setLayout(null);
	        
	        JPanel panel_1 = new JPanel();
	        panel_1.setBackground(new Color(192, 192, 192));
	        panel_1.setBounds(0, 0, 181, 49);
	        panel.add(panel_1);
	        panel_1.setLayout(null);
	        
	        JLabel lblNewLabel_1 = new JLabel("Tìm hóa đơn");
	        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
	        lblNewLabel_1.setBounds(10, 11, 161, 27);
	        panel_1.add(lblNewLabel_1);
	        
//	        
	        btnTimHoaDon = new JButton("Tìm");
	        btnTimHoaDon.setFont(new Font("Arial", Font.BOLD, 23));
	        btnTimHoaDon.setBounds(956, 47, 106, 38);
	        panel.add(btnTimHoaDon);
	        
			btnLamMoi = new JButton("Làm mới");
			btnLamMoi.setBounds(1156, 47, 130, 38);
			btnLamMoi.setFont(new Font("Arial", Font.BOLD, 20));
			panel.add(btnLamMoi);
	        
			JLabel lblNgayLap = new JLabel("Ngày Lập");
			lblNgayLap.setHorizontalAlignment(SwingConstants.LEFT);
			lblNgayLap.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNgayLap.setBounds(462, 45, 157, 43);
			panel.add(lblNgayLap);

			dateNgayLap = new JDateChooser();
			panel.add(dateNgayLap);
			dateNgayLap.setDateFormatString("yyyy-MM-dd");
			dateNgayLap.setFont(new Font("Tahoma", Font.PLAIN, 20));
			dateNgayLap.setBounds(609, 50, 307, 35);
			
	                        
	        btnNewButton = new JButton("In hóa đơn");
	        btnNewButton.setBounds(1166, 685, 138, 43);
	        add(btnNewButton);
	        btnNewButton.setBackground(new Color(135, 149, 248));
	        btnNewButton.setForeground(new Color(0, 0, 0));
	        btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));   
	        
	        btnNewButton.addActionListener(this);
	        btnLamMoi.addActionListener(this);
	        btnTimHoaDon.addActionListener(this);
	        docDuLieuHD();
	    }

	    public void docDuLieuHD() {
	        ArrayList<HoaDon> dsHD = hd_dao.getAllHoaDon();
	        tablemodel.setRowCount(0);
	        for (HoaDon hoaDon : dsHD) {
	            tablemodel.addRow(new Object[] {
	                    hoaDon.getMaHoaDon(),
	                    hoaDon.getTongTien(),
	                    hoaDon.getKhuVuc().getMaKhuVuc(),
	                    hoaDon.getPhong() != null ? hoaDon.getPhong().getMaPhong(): "",
	                    hoaDon.getBanAn().getMaBan(),
	                    hoaDon.getNhanVien().getMaNV(),
	                    hoaDon.getNgayLap(),
	                    hoaDon.getKhachHang() != null ? hoaDon.getKhachHang().getMaKH() : "",
	                    hoaDon.getNgayDat()
	            });
	        }
	    }
	    public void docDuLieuCTHD(String maHoaDon) {
	        ArrayList<ChiTietHoaDon> dsCTHD = cthd_dao.TimHoaDon(maHoaDon);
	        cthdModel.setRowCount(0);

	        for (ChiTietHoaDon hoaDon : dsCTHD) {
	        	cthdModel.addRow(new Object[] {
	                    hoaDon.getHoaDon().getMaHoaDon(),
	                    hoaDon.getMon(),
	                    hoaDon.getSoLuong(),
	                    hoaDon.getDonGia(),
	                    hoaDon.getThanhTien()
	                    

	            });
	        }
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	Object o = e.getSource();
	    	if (o.equals(btnTimHoaDon)) {
			    String ngayLap = "";

			    if (dateNgayLap.getDate() != null) {
			        ngayLap = dateNgayLap.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
			    }

			    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tablemodel);
			    table.setRowSorter(sorter);
			    List<RowFilter<Object,Object>> filters = new ArrayList<>();
			    
			    if (!ngayLap.isEmpty()) {
			        filters.add(RowFilter.regexFilter(ngayLap, 6));
			    }
			    
			    RowFilter<Object,Object> filter = RowFilter.andFilter(filters);
			    sorter.setRowFilter(filter);
			}

			if(o.equals(btnLamMoi)){
				// xóa dữ liệu trong các ô text
				dateNgayLap.setDate(null);
				table.setRowSorter(null);
				docDuLieuHD();
			}
	}
}
