package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
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

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class Frm_DatMon extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextField txtMaMon, txtTenMon, txtDonGia;
    private DefaultTableModel model;
    private JButton btnThem, btnTim, btnXoa, btnSua, btnLamMoi;
    private JSpinner spinner;
    private JComboBox<String> cbxLoaiMon, cbxKhuVuc, cbxBan, cbxPhong;
	private JTable table1;


	public Frm_DatMon() {
		txtMaMon = new JTextField();
        txtTenMon = new JTextField();
        txtDonGia = new JTextField();
        cbxLoaiMon = new JComboBox<String>();
        cbxKhuVuc = new JComboBox<String>();
        cbxBan = new JComboBox<String>();
        cbxPhong = new JComboBox<String>();
        SpinnerNumberModel numberModel = new SpinnerNumberModel();
        spinner = new JSpinner(numberModel);
        setMaximumSize(new Dimension(1500, 1030));
        setMinimumSize(new Dimension(1500, 1030));
        setPreferredSize(new Dimension(1500, 1030));

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
		// Nhập thông tin
		JLabel lbl_titlle_panel_1 = new JLabel("Thông tin món");
		panel.add(lbl_titlle_panel_1);
		lbl_titlle_panel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titlle_panel_1.setFont(new Font("Arial", Font.BOLD, 16));
		// Mã Món
		JLabel lblMaMon = new JLabel("Mã Món:");
		lblMaMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaMon.setBounds(804, 125, 100, 40);
		panelThongTin.add(lblMaMon);
		// Ô nhập liệu cho mã món

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
		txtTenMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenMon.setBounds(900, 75, 200, 40);
		panelThongTin.add(txtTenMon);
		txtTenMon.setColumns(10);

		// Đơn giá
		JLabel lblDonGia = new JLabel("Đơn giá:");
		lblDonGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDonGia.setBounds(1152, 125, 100, 40);
		panelThongTin.add(lblDonGia);

		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDonGia.setBounds(1260, 125, 200, 40);
		panelThongTin.add(txtDonGia);
		txtDonGia.setColumns(10);

		// Loại Món
		JLabel lblLoaiMon = new JLabel("Loại Món:");
		lblLoaiMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLoaiMon.setBounds(451, 125, 86, 40);
		panelThongTin.add(lblLoaiMon);
		
		
		cbxLoaiMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxLoaiMon.setEditable(false);
		cbxLoaiMon.setBounds(535, 75, 200, 40);
		cbxLoaiMon.addItem("Món Nướng");
		cbxLoaiMon.addItem("Món Lẩu");
		cbxLoaiMon.addItem("Món tráng miệng");
		cbxLoaiMon.addItem("Thức uống");

		panelThongTin.add(cbxLoaiMon);

		// Khu Vực
		JLabel lblKhuVuc = new JLabel("Khu Vực:");
		lblKhuVuc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKhuVuc.setBounds(90, 70, 86, 40);
		panelThongTin.add(lblKhuVuc);

		cbxKhuVuc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxKhuVuc.setEditable(false);
		cbxKhuVuc.setBounds(200, 76, 200, 39);
		cbxKhuVuc.addItem("Khu vực 1");
		cbxKhuVuc.addItem("Khu vực 2");
		cbxKhuVuc.addItem("Khu vực VIP");
		panelThongTin.add(cbxKhuVuc);

		// Bàn
		JLabel lblBan = new JLabel("Bàn:");
		lblBan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBan.setBounds(462, 75, 86, 40);
		panelThongTin.add(lblBan);


		cbxBan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxBan.setEditable(false);
		cbxBan.setBounds(200, 121, 200, 39);



		//Phòng
		JLabel lblPhong = new JLabel("Phòng:");
		lblPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPhong.setBounds(90, 125, 86, 40);
		panelThongTin.add(lblPhong);

		cbxPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxPhong.setEditable(false);
		cbxPhong.setBounds(535, 125, 200, 40);
		cbxPhong.addItem("Phòng 1");
		cbxPhong.addItem("Phòng 2");
		cbxPhong.addItem("Phòng 3");
		panelThongTin.add(cbxPhong);
		// Thêm các lựa chọn cho danh sách bàn theo khu vực
		if (cbxKhuVuc.getSelectedItem().equals("Khu vực 1")) {
			cbxBan.addItem("Bàn 1");
			cbxBan.addItem("Bàn 2");
			cbxBan.addItem("Bàn 3");
		} else if (cbxKhuVuc.getSelectedItem().equals("Khu vực 2")) {
			cbxBan.addItem("Bàn 4");
			cbxBan.addItem("Bàn 5");
			cbxBan.addItem("Bàn 6");
		} else if (cbxKhuVuc.getSelectedItem().equals("Khu vực VIP")) {
			cbxBan.addItem("Bàn VIP 1");
			cbxBan.addItem("Bàn VIP 2");
			cbxBan.addItem("Bàn VIP 3");
		}
		panelThongTin.add(cbxBan);
		// Số lượng

		spinner.setBounds(1262, 88, 30, 20);
		panelThongTin.add(spinner);

		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSoLuong.setBounds(1152, 75, 100, 40);
		panelThongTin.add(lblSoLuong);

		// Nút tìm kiếm
		btnTim = new JButton("Tìm kiếm");
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTim.setBounds(561, 175, 200, 50);
		panelThongTin.add(btnTim);
		// Nút thêm
		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThem.setBounds(36, 175, 200, 50);
		panelThongTin.add(btnThem);
		// Nút xóa
		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoa.setBounds(298, 176, 200, 50);
		panelThongTin.add(btnXoa);
		// Nút làm mới
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoi.setBounds(1107, 175, 200, 50);
		panelThongTin.add(btnLamMoi);
		// Nút cập nhật
		btnSua = new JButton("Cập nhật");
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSua.setBounds(842, 175, 200, 50);
		panelThongTin.add(btnSua);

		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLamMoi.addActionListener(this);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 373, 1057, 427);
		add(scrollPane_2);
		String[] Header1 = {"Khu Vực","Phòng", "Bàn", "Mã Món", "Tên Món", "Đơn Giá", "Số Lượng ", "Loại Món"};
		model = new DefaultTableModel(Header1, 0);
		JTable table1 = new JTable(model);
		table1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table1.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane_2.setViewportView(table1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_1.setBounds(1067, 373, 463, 427);
		add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_2.setBounds(-36, -11, 154, 43);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JLabel lbl_titlle_panel_1_1 = new JLabel("Thanh toán");
		lbl_titlle_panel_1_1.setBounds(37, 11, 117, 33);
		panel_2.add(lbl_titlle_panel_1_1);
		lbl_titlle_panel_1_1.setBackground(new Color(0, 128, 128));
		lbl_titlle_panel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titlle_panel_1_1.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel lblNhanVienThanhToan = new JLabel("Nhân viên thanh toán");
		lblNhanVienThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNhanVienThanhToan.setBounds(34, 53, 194, 40);
		panel_1.add(lblNhanVienThanhToan);

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


		JButton btnDatDon = new JButton("Đặt đơn");
		btnDatDon.setForeground(new Color(0, 0, 0));
		btnDatDon.setBackground(new Color(0, 255, 255));
		btnDatDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDatDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDatDon.setBounds(34, 215, 145, 50);
		panel_1.add(btnDatDon);

		JButton btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setBackground(new Color(255, 128, 0));
		btnThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThanhToan.setBounds(288, 215, 145, 50);
		panel_1.add(btnThanhToan);

		JButton btnHuy = new JButton("Hủy");
		btnHuy.setBackground(new Color(255, 0, 0));
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHuy.setBounds(164, 276, 145, 50);
		panel_1.add(btnHuy);
		
		
		
		
		btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnSua.addActionListener(this);
        btnTim.addActionListener(this);
        btnLamMoi.addActionListener(this);

	}

	 @Override
	 public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == btnThem) {
	            themMonAn();
	        } else if (e.getSource() == btnXoa) {
	            xoaMonAn();
	        } else if (e.getSource() == btnSua) {
	            capNhatMonAn();
	        } else if (e.getSource() == btnTim) {
	            timKiemMonAn();
	        } else if (e.getSource() == btnLamMoi) {
	            lamMoiActions();
	        }
	    }

	    private void themMonAn() {
	        try {
	            String maMon = txtMaMon.getText();
	            String tenMon = txtTenMon.getText();
	            double donGia = Double.parseDouble(txtDonGia.getText());
	            String loaiMon = (String) cbxLoaiMon.getSelectedItem();
	            int soLuong = (int) spinner.getValue();

	            if (maMon.isEmpty() || tenMon.isEmpty()) {
	                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin món.", "Thông báo",
	                        JOptionPane.WARNING_MESSAGE);
	                return;
	            }

	            // Your logic for adding a new item to the list and updating the table model...

	            lamMoiActions();
	        } catch (NumberFormatException | HeadlessException ex) {
	            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	    private void xoaMonAn() {
	        int selectedRow = table1.getSelectedRow();
	        if (selectedRow != -1) {
	            // Xóa hàng được chọn từ bảng
	            model.removeRow(selectedRow);
	        } else {
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa.", "Thông báo",
	                    JOptionPane.WARNING_MESSAGE);
	        }
	    }

	    private void capNhatMonAn() {
	        int selectedRow = table1.getSelectedRow();
	        if (selectedRow != -1) {
	            String maMon = txtMaMon.getText();
	            String tenMon = txtTenMon.getText();
	            double donGia = Double.parseDouble(txtDonGia.getText());
	            String loaiMon = (String) cbxLoaiMon.getSelectedItem();
	            int soLuong = (int) spinner.getValue();

	            // Cập nhật thông tin món vào hàng được chọn trong bảng
	            model.setValueAt(maMon, selectedRow, 0);
	            model.setValueAt(tenMon, selectedRow, 1);
	            model.setValueAt(donGia, selectedRow, 2);
	            model.setValueAt(soLuong, selectedRow, 3);
	            model.setValueAt(loaiMon, selectedRow, 4);
	        } else {
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để cập nhật.", "Thông báo",
	                    JOptionPane.WARNING_MESSAGE);
	        }
	    }

	    private void timKiemMonAn() {
	        String keyword = JOptionPane.showInputDialog(this, "Nhập từ khóa tìm kiếm:", "Tìm kiếm món ăn",
	                JOptionPane.PLAIN_MESSAGE);
	        if (keyword != null && !keyword.isEmpty()) {
	            for (int row = 0; row < model.getRowCount(); row++) {
	                String tenMon = (String) model.getValueAt(row, 1);
	                if (tenMon.toLowerCase().contains(keyword.toLowerCase())) {
	                    table1.getSelectionModel().setSelectionInterval(row, row);
	                    table1.scrollRectToVisible(table1.getCellRect(row, 0, true));
	                    return;
	                }
	            }
	            JOptionPane.showMessageDialog(this, "Không tìm thấy món ăn phù hợp.", "Thông báo",
	                    JOptionPane.INFORMATION_MESSAGE);
	        }
	    }

	    private void lamMoiActions() {
	        txtMaMon.setText("");
	        txtTenMon.setText("");
	        txtDonGia.setText("");
	        cbxLoaiMon.setSelectedIndex(0);
	        spinner.setValue(0);
	    }
	}



