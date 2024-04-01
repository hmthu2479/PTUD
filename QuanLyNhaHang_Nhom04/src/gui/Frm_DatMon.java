package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class Frm_DatMon  extends JPanel  implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField txtMaMon;
	private JTextField txtSoLuongTonKho;
	private JComboBox<String> cbxDanhMuc;
	private DefaultTableModel model;
	private JButton btnLamMoi;
	private JButton btnThem;

	private JButton btnTim;
	private JButton btnXoa;
	private JButton btnSua;
	private JTextField textField;
	private JTable table_1;
	private JTextField textField_1;
	private Object btnHuy;
	
	public Frm_DatMon() {
		//setSize
		setMaximumSize(new Dimension(1500, 1030));
		setMinimumSize(new Dimension(1500, 1030));
		setMaximumSize(new Dimension(1500, 1030));

		setSize(new Dimension(1540, 852));
		setLayout(null);
		//Tiêu đề
		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(new Color(0, 206, 209));
		panelTitle.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		JLabel lblTitLe = new JLabel("ĐẶT MÓN");
		lblTitLe.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitLe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitLe.setFont(new Font("Arial", Font.BOLD, 35));
		lblTitLe.setForeground(Color.RED);

		panelTitle.add(lblTitLe);
		panelTitle.setBounds(0, 0, 1540, 60);

		add(panelTitle);


		JPanel panelThongTin = new JPanel();
		panelThongTin.setBackground(new Color(0, 128, 128));
		panelThongTin.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panelThongTin.setBounds(0, 70, 1540, 235);
		add(panelThongTin);
		panelThongTin.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBounds(0, 0, 180, 35);
		panelThongTin.add(panel);
		//Nhập thông tin
		JLabel lbl_titlle_panel_1 = new JLabel("Thông tin món");
		panel.add(lbl_titlle_panel_1);
		lbl_titlle_panel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titlle_panel_1.setFont(new Font("Arial", Font.BOLD, 16));
		// Mã Món
		JLabel lblMaMon = new JLabel("Mã Món");
		lblMaMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaMon.setBounds(448, 75, 100, 40);
		panelThongTin.add(lblMaMon);

		txtMaMon= new JTextField();
		txtMaMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaMon.setBounds(535, 125, 200, 40);
		panelThongTin.add(txtMaMon);
		txtMaMon.setColumns(10);
		
		// Tên Món
	
		JLabel lblTenMon = new JLabel("Tên Món");
		lblTenMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenMon.setBounds(448, 125, 100, 40);
		panelThongTin.add(lblTenMon);
		
		//Đơn giá
		JLabel lblDonGia = new JLabel("Đơn giá");
		lblDonGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDonGia.setBounds(804, 125, 100, 40);
		panelThongTin.add(lblDonGia);
		//Số lượng tồn kho
		JLabel lblSoLuongTonKho = new JLabel("Số Lượng tồn kho");
		lblSoLuongTonKho.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSoLuongTonKho.setBounds(80, 45, 100, 40);
		panelThongTin.add(lblDonGia);

		txtSoLuongTonKho= new JTextField();
		txtSoLuongTonKho.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoLuongTonKho.setBounds(535, 75, 200, 40);
		panelThongTin.add(txtSoLuongTonKho);
		txtSoLuongTonKho.setColumns(10);
		//Loại Món
		JLabel lblLoaiMon = new JLabel("Loại Món");
		lblLoaiMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLoaiMon.setBounds(804, 75, 86, 40);
		panelThongTin.add(lblLoaiMon);
		//Danh Mục
		JLabel lblDanhMuc = new JLabel("Số Lượng ");
		lblDanhMuc.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDanhMuc.setBounds(1152, 75, 86, 40);
		panelThongTin.add(lblDanhMuc);
		cbxDanhMuc = new JComboBox<String>();
		cbxDanhMuc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxDanhMuc.setBounds(200, 76, 200, 39);
		cbxDanhMuc.setEditable(false);
		cbxDanhMuc.addItem("Tất cả");
		panelThongTin.add(cbxDanhMuc);
		
		/*
		Nút tìm kiếm
		 */
		btnTim = new JButton("Tìm kiếm");
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTim.setBounds(692, 175, 200, 50);
		panelThongTin.add(btnTim);
		//nút thêm
		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThem.setBounds(36, 175, 200, 50);
		panelThongTin.add(btnThem);
		// nút xóa
		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoa.setBounds(373, 175, 200, 50);
		panelThongTin.add(btnXoa);
		//nút xóa rỗng
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoi.setBounds(1330, 175, 200, 50);
		panelThongTin.add(btnLamMoi);
		// nút cập nhật
		btnSua = new JButton("Cập nhật");
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSua.setBounds(1014, 175, 200, 50);
		panelThongTin.add(btnSua);
		String[] Header = {"Mã Món", "Tên Món", "Đơn Giá", "Số Lượng ", "Loại Món"};
		model = new DefaultTableModel(Header, 0);

		JLabel lblTieuDeTable = new JLabel("DANH SÁCH MÓN ĐÃ ĐẶT");
		lblTieuDeTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDeTable.setFont(new Font("Arial", Font.BOLD, 20));
		lblTieuDeTable.setBounds(10, 305, 1002, 44);
		add(lblTieuDeTable);
		
		textField = new JTextField();
		textField.setBounds(900, 128, 200, 40);
		panelThongTin.add(textField);
		textField.setColumns(10);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(1262, 88, 30, 20);
		panelThongTin.add(spinner);
		
		JLabel lblBn = new JLabel("Bàn");
		lblBn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBn.setBounds(90, 120, 86, 40);
		panelThongTin.add(lblBn);
		
		JComboBox<String> cbxDanhMuc_2 = new JComboBox<String>();
		cbxDanhMuc_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxDanhMuc_2.setEditable(false);
		cbxDanhMuc_2.setBounds(200, 121, 200, 39);
		panelThongTin.add(cbxDanhMuc_2);
		
		JComboBox<String> cbxDanhMuc_3 = new JComboBox<String>();
		cbxDanhMuc_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxDanhMuc_3.setEditable(false);
		cbxDanhMuc_3.setBounds(900, 76, 200, 39);
		panelThongTin.add(cbxDanhMuc_3);
		
		JLabel lblKhuVc = new JLabel("Khu vực");
		lblKhuVc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKhuVc.setBounds(90, 70, 86, 40);
		panelThongTin.add(lblKhuVc);



		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLamMoi.addActionListener(this);
		
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(-178, 294, 1002, 490);
		add(scrollPane_2);
		String[] Header1 = {"Mã Món", "Tên Món", "Đơn Giá", "Số Lượng ", "Loại Món"};
		model = new DefaultTableModel(Header, 0);
		JTable table1 = new JTable(model);
		table1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table1.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane_2.setViewportView(table1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_1.setBounds(1014, 342, 526, 493);
		add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_2.setBounds(0, 0, 154, 43);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lbl_titlle_panel_1_1 = new JLabel("Thanh toán");
		lbl_titlle_panel_1_1.setBackground(new Color(0, 128, 128));
		lbl_titlle_panel_1_1.setBounds(21, 10, 110, 19);
		panel_2.add(lbl_titlle_panel_1_1);
		lbl_titlle_panel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titlle_panel_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblNhnVinThanh = new JLabel("Nhân viên thanh toán");
		lblNhnVinThanh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNhnVinThanh.setBounds(34, 53, 194, 40);
		panel_1.add(lblNhnVinThanh);
		
		JComboBox<String> cbxDanhMuc_1 = new JComboBox<String>();
		cbxDanhMuc_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxDanhMuc_1.setEditable(false);
		cbxDanhMuc_1.setBounds(34, 92, 425, 39);
		panel_1.add(cbxDanhMuc_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3.setBounds(34, 161, 115, 43);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lbl_titlle_panel_1_1_1 = new JLabel("Tổng tiền");
		lbl_titlle_panel_1_1_1.setVerticalAlignment(SwingConstants.TOP);
		lbl_titlle_panel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titlle_panel_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_titlle_panel_1_1_1.setBounds(0, 10, 110, 19);
		panel_3.add(lbl_titlle_panel_1_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(171, 161, 288, 43);
		panel_1.add(textField_1);
		
		JButton btntn = new JButton("Đặt đơn");
		btntn.setForeground(new Color(0, 0, 0));
		btntn.setBackground(new Color(0, 255, 255));
		btntn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btntn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btntn.setBounds(51, 275, 145, 50);
		panel_1.add(btntn);
		
		JButton btnThanhTon = new JButton("Thanh toán");
		btnThanhTon.setBackground(new Color(255, 128, 0));
		btnThanhTon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThanhTon.setBounds(283, 275, 145, 50);
		panel_1.add(btnThanhTon);
		
		JButton btnHuy = new JButton("Hủy");
		btnHuy.setBackground(new Color(255, 0, 0));
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHuy.setBounds(167, 353, 145, 50);
		panel_1.add(btnHuy);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == btnThem) {
	        // Xử lý thêm món ăn
	    } else if (e.getSource() == btnXoa) {
	        // Xử lý xóa món ăn
	    } else if (e.getSource() == btnSua) {
	        // Xử lý cập nhật món ăn
	    } else if (e.getSource() == btnTim) {
	        // Xử lý tìm kiếm món ăn
	    } else if (e.getSource() == btnLamMoi) {
	        // Xử lý làm mới
	    } else if (e.getSource() == btnHuy) {
	        // Xử lý hủy thao tác
	    }
	}
}
