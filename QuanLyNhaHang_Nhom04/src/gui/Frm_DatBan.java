package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import entity.PhieuDatBan;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

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
	private JComboBox cmb_nhanVien;

    public Frm_DatBan() {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout(15, 5));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        JPanel pnl_W = new JPanel(new GridLayout(8, 1, 0, 0));
        pnl_W.setBackground(Color.WHITE); 

        // Nhân viên
        
        JPanel pnl_nhanVien = new JPanel(new BorderLayout());
        pnl_nhanVien.setBackground(Color.WHITE); 
        pnl_nhanVien.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Nhân viên", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        String[] nv = {"Nguyễn Văn A", "Trần Văn B"};
        cmb_nhanVien = new JComboBox<>(nv);
        cmb_nhanVien.setFont(new Font("Arial", Font.BOLD, 20));
        pnl_nhanVien.add(cmb_nhanVien, BorderLayout.CENTER);
        pnl_W.add(pnl_nhanVien);
        // Khu vực
        
        JPanel pnl_khuVuc = new JPanel(new BorderLayout());
        pnl_khuVuc.setBackground(Color.WHITE); 
        pnl_khuVuc.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Khu vực", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        String[] khuVuc = {"Khu vực 1", "Khu vực 2", "Tầng 1", "Tầng 2"};
        cmb_khuVuc = new JComboBox<>(khuVuc);
        cmb_khuVuc.setFont(new Font("Arial", Font.BOLD, 20));
        pnl_khuVuc.add(cmb_khuVuc, BorderLayout.CENTER);
        pnl_W.add(pnl_khuVuc);
        
     // Bàn
        JPanel pnl_Ban = new JPanel(new BorderLayout());
        pnl_Ban.setBackground(Color.WHITE);
        pnl_Ban.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Bàn", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));

        String[] soBan = new String[25]; 


        for (int i = 0; i < 20; i++) {
            soBan[i] = "Bàn " + (i + 1);
        }

        soBan[20] = "Phòng VIP 1";
        soBan[21] = "Phòng VIP 2";
        soBan[22] = "Phòng VIP 3";
        soBan[23] = "Phòng Karaoke 1";
        soBan[24] = "Phòng Karaoke 2";

        cmb_ban = new JComboBox<>(soBan);
        cmb_ban.setFont(new Font("Arial", Font.BOLD, 20));
        cmb_ban.setForeground(Color.BLACK);
        pnl_Ban.add(cmb_ban, BorderLayout.CENTER);
        pnl_W.add(pnl_Ban);
        
        
      //Mã phiếu
        JPanel pnl_maPhieu = new JPanel(new BorderLayout());
        pnl_maPhieu.setBackground(Color.WHITE); // White
        pnl_maPhieu.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Mã Phiếu", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        txt_maPhieu = new JTextField(30);
        txt_maPhieu.setFont(new Font("Roboto", Font.BOLD, 16));
        pnl_maPhieu.add(txt_maPhieu, BorderLayout.CENTER);
        pnl_W.add(pnl_maPhieu);
        
        //Tên
        JPanel pnl_hoTen = new JPanel(new BorderLayout());
        pnl_hoTen.setBackground(Color.WHITE); // White
        pnl_hoTen.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Họ tên khách hàng", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        txt_hoTen = new JTextField(30);
        txt_hoTen.setFont(new Font("Roboto", Font.BOLD, 16));
        pnl_hoTen.add(txt_hoTen, BorderLayout.CENTER);
        pnl_W.add(pnl_hoTen);

        // SDT
        JPanel pnl_SDT = new JPanel(new BorderLayout());
        pnl_SDT.setBackground(Color.WHITE); // White
        pnl_SDT.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "SĐT", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        txt_sdt = new JTextField(30);
        txt_sdt.setFont(new Font("Arial", Font.BOLD, 16));
        pnl_SDT.add(txt_sdt, BorderLayout.CENTER);
        pnl_W.add(pnl_SDT);

     // Địa chỉ
        JPanel pnl_diaChi = new JPanel(new BorderLayout());
        pnl_diaChi.setBackground(Color.WHITE); // White
        pnl_diaChi.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Địa Chỉ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        txt_diaChi = new JTextField(30);
        txt_diaChi.setFont(new Font("Arial", Font.BOLD, 16));
        pnl_diaChi.add(txt_diaChi, BorderLayout.CENTER);
        pnl_W.add(pnl_diaChi);
        
        //Buttons
        JPanel pnl_btn = new JPanel(new GridLayout(2, 3));
        pnl_btn.setBackground(Color.white);
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
        lbl_Tim.setFont(new Font("Arial", Font.BOLD, 15));
        txt_Tim = new JTextField(10);
        btn_Tim = new JButton("Tìm kiếm");

        pnl_btn.add(btn_Them);
        pnl_btn.add(btn_Xoa);
        pnl_btn.add(btn_xoaRong);
        pnl_btn.add(lbl_Tim);
        pnl_btn.add(txt_Tim);
        pnl_btn.add(btn_Tim);


        Font bFont = new Font("Arial", Font.BOLD, 16);
        btn_Them.setFont(bFont);
        btn_Xoa.setFont(bFont);
        btn_xoaRong.setFont(bFont);
        btn_Tim.setFont(bFont);
        pnl_W.setPreferredSize(new Dimension(400,550));
        pnl_W.add(pnl_btn);

        mainPanel.add(pnl_W, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new BorderLayout());

        // Table
        String[] columnNames = {"Mã", "Khu vực", "Số bàn", "SL","Ngày đặt","Ngày lập","Giờ đặt","Họ Tên", "SDT", "Địa chỉ", "Nhân viên"};

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

        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	     table.getColumnModel().getColumn(0).setPreferredWidth(100);
	     table.getColumnModel().getColumn(1).setPreferredWidth(130);
	     table.getColumnModel().getColumn(2).setPreferredWidth(120);
	     table.getColumnModel().getColumn(3).setPreferredWidth(50);
	     table.getColumnModel().getColumn(4).setPreferredWidth(120);
	     table.getColumnModel().getColumn(5).setPreferredWidth(120);
	     table.getColumnModel().getColumn(6).setPreferredWidth(90);
	     table.getColumnModel().getColumn(7).setPreferredWidth(160);
	     table.getColumnModel().getColumn(8).setPreferredWidth(120);
	     table.getColumnModel().getColumn(9).setPreferredWidth(320);
	     table.getColumnModel().getColumn(9).setPreferredWidth(120);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      
        rightPanel.add(tableScrollPane, BorderLayout.CENTER);
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        mainPanel.add(rightPanel, BorderLayout.CENTER);


        JPanel pnl_N = new JPanel(new GridLayout(1, 3, 0, 10));
        pnl_N.setBackground(Color.WHITE); 
        pnl_N.setBorder(new EmptyBorder(5, 0, 10, 0));

        // Ngày tháng năm
        JPanel pnl_ngayThang = new JPanel(new BorderLayout());
        pnl_ngayThang.setBackground(Color.WHITE);
        pnl_ngayThang.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black, 2), "Ngày đặt", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        SpinnerDateModel dateModel = new SpinnerDateModel();
        spn_ngayThang = new JSpinner(dateModel);
        spn_ngayThang.setEditor(new JSpinner.DateEditor(spn_ngayThang, "dd/MM/yyyy"));
        spn_ngayThang.setFont(new Font("Arial", Font.BOLD, 20));
        pnl_ngayThang.add(spn_ngayThang, BorderLayout.CENTER);
        pnl_N.add(pnl_ngayThang);

        // Giờ đặt
        JPanel pnl_gioDat = new JPanel(new BorderLayout());
        pnl_gioDat.setBackground(Color.WHITE); 
        pnl_gioDat.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Giờ đặt", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        String[] timeOptions = {"10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM"};
        cmb_gioDat = new JComboBox<>(timeOptions);
        cmb_gioDat.setFont(new Font("Arial", Font.BOLD, 20));
        pnl_gioDat.add(cmb_gioDat, BorderLayout.CENTER);
        pnl_N.add(pnl_gioDat);

        // Số lượng người
        JPanel pnl_soLuongNguoi = new JPanel(new BorderLayout());
        pnl_soLuongNguoi.setBackground(Color.WHITE); 
        pnl_soLuongNguoi.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Số lượng người", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        Integer[] peopleOptions = {1, 2, 3, 4, 5};
        cmb_soLuongNguoi = new JComboBox<>(peopleOptions);
        cmb_soLuongNguoi.setFont(new Font("Arial", Font.BOLD, 20));
        pnl_soLuongNguoi.add(cmb_soLuongNguoi, BorderLayout.CENTER);
        pnl_N.add(pnl_soLuongNguoi);
        pnl_W.add(pnl_N);
        mainPanel.add(pnl_N, BorderLayout.NORTH);

        add(mainPanel, BorderLayout.CENTER);
        
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.WHITE);
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
		    String ngayThang = formatter.format(selectedDate);
			String gio = String.valueOf(cmb_gioDat.getSelectedItem());
			Integer soLuong = (Integer) cmb_soLuongNguoi.getSelectedItem();
			String hoTen = txt_hoTen.getText();
			String sdt = txt_sdt.getText();
			String diaChi = txt_diaChi.getText();
			String khuVuc = String.valueOf(cmb_khuVuc.getSelectedItem());
			String ma = txt_maPhieu.getText();
			String soBan = String.valueOf(cmb_ban.getSelectedItem());
			LocalDate ngayLap = LocalDate.now();
			
			PhieuDatBan p = new PhieuDatBan(ma, khuVuc, soBan, soLuong, ngayThang,ngayLap,gio,hoTen,sdt,diaChi);
			
			if(!listPhieu.themPhieu(p)) {
				JOptionPane.showMessageDialog(this, "Trùng mã");
			}
			else {
				modelPhieu.addRow(new Object[] {p.getMaPhieu(), p.getKhuVuc(),p.getSoBan(),p.getSoLuongNguoi(),
				 p.getNgayThang(),p.getNgayLap(),p.getGioDat(),p.getHoTen(),p.getHoTen(),p.getDiaChi()
				});
			}
		}
		if (o.equals(btn_Xoa)) {
            int r = table.getSelectedRow();
            if (r != -1) {
                String maPhieu = (String) modelPhieu.getValueAt(r, 0);
                modelPhieu.removeRow(r);
                p_dao.xoaPhong(maPhong);
            }
        }

			if (o.equals(btn_xoaRong)) {
			    txt_maPhieu.setText("");
			    txt_hoTen.setText("");
			    txt_diaChi.setText("");
			    txt_sdt.setText("");

			    spn_ngayThang.setValue(new Date());

			    cmb_gioDat.setSelectedIndex(0);
			    cmb_soLuongNguoi.setSelectedIndex(0);
			    cmb_khuVuc.setSelectedIndex(0);
			    cmb_ban.setSelectedIndex(0);
			}
		
	}
    
 	
}
