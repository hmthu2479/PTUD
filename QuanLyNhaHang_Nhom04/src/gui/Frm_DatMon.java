package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.border.LineBorder;

public class Frm_DatMon extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtMaMon, txtTenMon, txtDonGia, txtsoLuong, txtTongTien;
	private DefaultTableModel model;
	private JButton btnThem, btnTim, btnXoa, btnSua, btnLamMoi,btnThanhToan,btnThoat;
	private JComboBox<String> cmbLoaiMon, cmbKhuVuc, cmbBan, cmbPhong;
	private JTable table;
	

	public Frm_DatMon() {
		
		setLayout(null);
		// Tiêu đề
		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(new Color(0, 206, 209));
		panelTitle.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		JLabel lblTitLe = new JLabel("ĐẶT MÓN");
		lblTitLe.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitLe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitLe.setFont(new Font("Arial", Font.BOLD, 35));
		lblTitLe.setForeground(Color.RED);
		panelTitle.add(lblTitLe);
		panelTitle.setBounds(0, 42, 1540, 60);
		add(panelTitle);

		// Khung thông tin món ăn
		JPanel panelThongTin = new JPanel();
		panelThongTin.setBackground(new Color(0, 128, 128));
		panelThongTin.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panelThongTin.setBounds(0, 128, 1540, 235);
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
		lbl_titlle_panel_1.setFont(new Font("Arial", Font.BOLD, 16));

		// Mã Món
		JLabel lblMaMon = new JLabel("Mã Món:");
		lblMaMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaMon.setBounds(804, 125, 100, 40);
		panelThongTin.add(lblMaMon);

		txtMaMon = new JTextField();
		txtMaMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaMon.setBounds(900, 125, 200, 40);
		panelThongTin.add(txtMaMon);
		txtMaMon.setColumns(10);

		// Nhãn "Tên Món"
		JLabel lblTenMon = new JLabel("Tên Món:");

		lblTenMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenMon.setBounds(804, 75, 100, 40);
		panelThongTin.add(lblTenMon);

		// Ô nhập liệu cho tên móns
		txtTenMon = new JTextField();
		txtTenMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenMon.setBounds(900, 75, 200, 40);
		panelThongTin.add(txtTenMon);
		txtTenMon.setColumns(10);

		// Đơn giá
		JLabel lblDonGia = new JLabel("Đơn giá:");
		lblDonGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDonGia.setBounds(1152, 125, 100, 40);
		panelThongTin.add(lblDonGia);

		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDonGia.setBounds(1260, 125, 200, 40);
		panelThongTin.add(txtDonGia);
		txtDonGia.setColumns(10);

		// Loại Món
		JLabel lblLoaiMon = new JLabel("Loại Món:");
		lblLoaiMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLoaiMon.setBounds(451, 125, 86, 40);
		panelThongTin.add(lblLoaiMon);

		cmbLoaiMon = new JComboBox<>();
		cmbLoaiMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbLoaiMon.setEditable(false);
		cmbLoaiMon.setBounds(528, 125, 200, 40);
		cmbLoaiMon.addItem("Món Nướng");
		cmbLoaiMon.addItem("Món Lẩu");
		cmbLoaiMon.addItem("Món tráng miệng");
		cmbLoaiMon.addItem("Thức uống");
		panelThongTin.add(cmbLoaiMon);

		// Khu Vực
		JLabel lblKhuVuc = new JLabel("Khu Vực:");
		lblKhuVuc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKhuVuc.setBounds(90, 70, 86, 40);
		panelThongTin.add(lblKhuVuc);

		cmbKhuVuc = new JComboBox<>();
		cmbKhuVuc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbKhuVuc.setEditable(false);
		cmbKhuVuc.setBounds(200, 76, 200, 39);
		cmbKhuVuc.addItem("Khu vực 1");
		cmbKhuVuc.addItem("Khu vực 2");
		cmbKhuVuc.addItem("Khu vực VIP");
		panelThongTin.add(cmbKhuVuc);

		// Bàn
		JLabel lblBan = new JLabel("Bàn:");
		lblBan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBan.setBounds(462, 75, 86, 40);
		panelThongTin.add(lblBan);

		cmbBan = new JComboBox<>();
		cmbBan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbBan.setEditable(false);
		cmbBan.setBounds(528, 76, 200, 39);
		panelThongTin.add(cmbBan);

		//Phòng
		JLabel lblPhong = new JLabel("Phòng:");
		lblPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPhong.setBounds(90, 125, 86, 40);
		panelThongTin.add(lblPhong);

		cmbPhong = new JComboBox<>();
		cmbPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbPhong.setEditable(false);
		cmbPhong.setBounds(200, 125, 200, 40);
		cmbPhong.addItem("Phòng 1");
		cmbPhong.addItem("Phòng 2");
		cmbPhong.addItem("Phòng 3");
		panelThongTin.add(cmbPhong);

		// Thêm các lựa chọn cho danh sách bàn theo khu vực
		cmbKhuVuc.addActionListener(e -> {
			if (cmbKhuVuc.getSelectedItem().equals("Khu vực 1")) {
				cmbBan.removeAllItems();
				cmbBan.addItem("Bàn 1");
				cmbBan.addItem("Bàn 2");
				cmbBan.addItem("Bàn 3");
			} else if (cmbKhuVuc.getSelectedItem().equals("Khu vực 2")) {
				cmbBan.removeAllItems();
				cmbBan.addItem("Bàn 4");
				cmbBan.addItem("Bàn 5");
				cmbBan.addItem("Bàn 6");
			} else if (cmbKhuVuc.getSelectedItem().equals("Khu vực VIP")) {
				cmbBan.removeAllItems();
				cmbBan.addItem("Bàn VIP 1");
				cmbBan.addItem("Bàn VIP 2");
				cmbBan.addItem("Bàn VIP 3");
			}
		});

		// Số lượng
		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSoLuong.setBounds(1152, 75, 100, 40);
		panelThongTin.add(lblSoLuong);

		txtsoLuong = new JTextField();
		txtsoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtsoLuong.setColumns(10);
		txtsoLuong.setBounds(1260, 75, 200, 40);
		panelThongTin.add(txtsoLuong);

		// Sự kiện
		btnTim = new JButton("Tìm kiếm");
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTim.setBounds(657, 175, 200, 50);
		panelThongTin.add(btnTim);

		// Nút thêm
		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThem.setBounds(100, 175, 200, 50);
		panelThongTin.add(btnThem);

		// Nút xóa
		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoa.setBounds(375, 175, 200, 50);
		panelThongTin.add(btnXoa);

		// Nút làm mới
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoi.setBounds(1270, 175, 200, 50);
		panelThongTin.add(btnLamMoi);

		// Nút cập nhật
		btnSua = new JButton("Cập nhật");
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSua.setBounds(976, 175, 200, 50);
		panelThongTin.add(btnSua);

		// Thêm sự kiện cho các nút
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLamMoi.addActionListener(this);

		// Khung bảng dữ liệu
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 373, 1057, 427);
		add(scrollPane_2);

		String[] Header = {"Khu Vực", "Phòng", "Bàn", "Mã Món", "Tên Món", "Loại Món","Đơn Giá", "Số Lượng"};
		model = new DefaultTableModel(Header, 0);
		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane_2.setViewportView(table);

		// Khung thanh toán
		JPanel panelThanhToan = new JPanel();
		panelThanhToan.setBackground(new Color(255, 255, 204));
		panelThanhToan.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panelThanhToan.setBounds(1067, 373, 473, 427);
		add(panelThanhToan);
		panelThanhToan.setLayout(null);

		// Nhãn "Thành toán"
		JLabel lblThanhToan = new JLabel("Thanh Toán");
		lblThanhToan.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblThanhToan.setBounds(179, 21, 193, 60);
		panelThanhToan.add(lblThanhToan);

		// Nhãn "Tổng tiền"
		JLabel lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setForeground(Color.RED);
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTongTien.setBounds(10, 129, 100, 30);
		panelThanhToan.add(lblTongTien);

		// Ô nhập liệu tổng tiền
		txtTongTien = new JTextField();
		txtTongTien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTongTien.setEditable(false);
		txtTongTien.setBounds(119, 121, 344, 49);
		panelThanhToan.add(txtTongTien);
		txtTongTien.setColumns(10);

		// Nút thanh toán
		JButton btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setForeground(new Color(0, 139, 139));
		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThanhToan.setBounds(39, 240, 152, 60);
		panelThanhToan.add(btnThanhToan);

		// Nút thoát
		JButton btnThoat = new JButton("Thoát");
		btnThoat.setForeground(new Color(255, 0, 0));
		btnThoat.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThoat.setBounds(311, 240, 152, 60);
		panelThanhToan.add(btnThoat);

		// Thêm sự kiện cho các nút
		btnThanhToan.addActionListener(this);
		btnThoat.addActionListener(this);

		
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
				String maMon = txtMaMon.getText().trim();
				String tenMon = txtTenMon.getText().trim();
				String donGiaString = txtDonGia.getText().trim();
				String soLuongString = txtsoLuong.getText().trim();

				if (maMon.isEmpty() || tenMon.isEmpty() || donGiaString.isEmpty() || soLuongString.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin món ăn!");
					return;
				}

				double donGia = Double.parseDouble(donGiaString);
				int soLuong = Integer.parseInt(soLuongString);

				// Thêm dữ liệu vào bảng
				model.addRow(new Object[]{cmbKhuVuc.getSelectedItem(), cmbPhong.getSelectedItem(), cmbBan.getSelectedItem(), maMon, tenMon,cmbLoaiMon.getSelectedItem(), donGia, soLuong});

				// Cập nhật tổng tiền
				capNhatTongTien();

				// Xóa dữ liệu trong các ô nhập liệu
				txtMaMon.setText("");
				txtTenMon.setText("");
				txtDonGia.setText("");
				txtsoLuong.setText("");
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Đơn giá và số lượng phải là số!");
			}
		}

		// Nút tìm kiếm
		if (source == btnTim) {
			// TODO: Implement search functionality
		}

		if (source == btnSua) {
			// Get the selected row index from the table
			int selectedRow = table.getSelectedRow();

			// Check if a row is selected
			if (selectedRow >= 0) {
				// Get the values from the selected row
				String khuVuc = (String) model.getValueAt(selectedRow, 0);
				String phong = (String) model.getValueAt(selectedRow, 1);
				String ban = (String) model.getValueAt(selectedRow, 2);
				String maMon = (String) model.getValueAt(selectedRow, 3);
				String tenMon = (String) model.getValueAt(selectedRow, 4);
				String loaiMon = (String) model.getValueAt(selectedRow, 5);
				double donGia = (double) model.getValueAt(selectedRow, 6);
				int soLuong = (int) model.getValueAt(selectedRow, 7);


				// Update the table with the edited values
				model.setValueAt(khuVuc, selectedRow, 0);
				model.setValueAt(phong, selectedRow, 1);
				model.setValueAt(ban, selectedRow, 2);
				model.setValueAt(maMon, selectedRow, 3);
				model.setValueAt(tenMon, selectedRow, 4);
				model.setValueAt(loaiMon, selectedRow, 5);
				model.setValueAt(donGia, selectedRow, 6);
				model.setValueAt(soLuong, selectedRow, 7);

				// Recalculate the total price
				capNhatTongTien();
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa!");
			}
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
			txtMaMon.setText("");
			txtTenMon.setText("");
			txtDonGia.setText("");
			txtsoLuong.setText("");
		}

		// Nút thanh toán
		if (source == btnThanhToan) {
			// TODO: Implement payment functionality
		}



		// Nút thoát
		if (source == btnThoat) {
			if (JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			}
		}
	}

	// Cập nhật tổng tiền
	public void capNhatTongTien() {
		double tongTien = 0;
		for (int i = 0; i < model.getRowCount(); i++) {
			double donGia = (double) model.getValueAt(i, 6);
			int soLuong = (int) model.getValueAt(i, 7);
			tongTien += donGia * soLuong;
		}

		txtTongTien.setText(String.format("%.2f", tongTien));
	}


	// Get the table model
	public DefaultTableModel getModel() {
		return model;
	}
}



