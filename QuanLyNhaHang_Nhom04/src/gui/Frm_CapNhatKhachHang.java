package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JComponent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import connectDB.ConnectDB;
import dao.KhachHangDAO;
import dao.NhanVienDAO;
import entity.KhachHang;
import entity.NhanVien;

public class Frm_CapNhatKhachHang extends JPanel implements ActionListener {
	private JLabel lbtitle;
	private JLabel lbMaKH;
	private JTextField txtMaKH;
	private JLabel lbhoTen;
	private JTextField txthoTen;
	private JLabel lbdiaChi;
	private JTextField txtdiaChi;
	private JLabel lbsdt;
	private JTextField txtsdt;
	private JRadioButton nu;
	private JPanel jpS;
	private JPanel bTrai;
	private JPanel bPhai;
	private JLabel lbNhap;
	private JTextField txtNhap;
	private JButton tim;
	private JButton them;
	private JButton xoaTrang;
	private JButton xoa;
	private JButton luu;
	private JPanel jpN;
	private JLabel phai;
	private DefaultTableModel modelKH;
	private JTable tableKhachHang;
	private JRadioButton nam;
	private KhachHangDAO kh_dao;

	public Frm_CapNhatKhachHang() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kh_dao = new KhachHangDAO();
		
		setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		setBackground(new Color(204, 235, 150)); 
		jpN = new JPanel();
		jpN.setLayout(new BoxLayout(jpN, BoxLayout.Y_AXIS));
		jpN.setPreferredSize(new Dimension(520, 602));
		jpN.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        Color lightBlue = new Color(173, 216, 230); // Light blue colorS
		lbtitle = new JLabel("THÔNG TIN KHÁCH HÀNG");
		lbtitle.setFont(new Font("Arial", Font.BOLD, 35));
		lbtitle.setForeground(Color.black);

		lbMaKH = new JLabel("Mã khách hàng: ");
		txtMaKH = new JTextField();
		lbhoTen = new JLabel("Họ tên: ");
		txthoTen = new JTextField();
		phai = new JLabel("Phái: ");
		nu = new JRadioButton("Nữ");
		nam = new JRadioButton("Nam");
		ButtonGroup gr = new ButtonGroup();
		gr.add(nam);
		gr.add(nu);
		lbsdt = new JLabel("Số điện thoại: ");
		txtsdt = new JTextField();
		lbdiaChi = new JLabel("Địa chỉ: ");
		txtdiaChi = new JTextField();
		

		JPanel jpFields = new JPanel(new GridLayout(0, 1));
		jpFields.add(lbMaKH);
		jpFields.add(txtMaKH);
		jpFields.add(lbhoTen);
		jpFields.add(txthoTen);
		jpFields.add(lbsdt);
		jpFields.add(txtsdt);
		jpFields.add(lbdiaChi);
		jpFields.add(txtdiaChi);

		JPanel jpPhai = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jpPhai.add(phai);
		jpPhai.add(nu);
		jpPhai.add(nam);

		for (Component component : jpFields.getComponents()) {
		    if (component instanceof JLabel || component instanceof JTextField) {
		        ((JComponent) component).setAlignmentX(Component.CENTER_ALIGNMENT);
		    }
		}
		
		Dimension textFieldSize = new Dimension(200, 30);
		Font textFieldFont = new Font("Arial", Font.BOLD, 18); 
		JTextField[] textFields = {txtMaKH, txthoTen, txtdiaChi, txtsdt};

		for (JTextField textField : textFields) {
		    textField.setPreferredSize(textFieldSize);
		    textField.setHorizontalAlignment(SwingConstants.CENTER);
		    textField.setFont(textFieldFont);
		}
		
		lbMaKH.setHorizontalAlignment(JLabel.CENTER);
		lbhoTen.setHorizontalAlignment(JLabel.CENTER);
		lbdiaChi.setHorizontalAlignment(JLabel.CENTER);
		lbsdt.setHorizontalAlignment(JLabel.CENTER);
		jpPhai.setAlignmentX(Component.CENTER_ALIGNMENT);

		lbtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

		jpN.add(Box.createVerticalGlue()); 
		jpN.add(lbtitle);
		jpN.add(Box.createVerticalStrut(10)); 
		jpN.add(jpFields);
		jpN.add(Box.createVerticalStrut(10)); 
		jpN.add(jpPhai);
		jpN.add(Box.createVerticalGlue());

		add(jpN, BorderLayout.WEST);
		JPanel emptyPanel = new JPanel();
		emptyPanel.setPreferredSize(new Dimension(15, 490));
		emptyPanel.setBackground(new Color(204, 235, 150)); 
		add(emptyPanel,BorderLayout.CENTER);

        lbMaKH.setFont(lbMaKH.getFont().deriveFont(Font.BOLD, 20));
        lbhoTen.setFont(lbhoTen.getFont().deriveFont(Font.BOLD, 20));
        lbdiaChi.setFont(lbdiaChi.getFont().deriveFont(Font.BOLD, 20));
        lbsdt.setFont(lbsdt.getFont().deriveFont(Font.BOLD, 20));

		nam.setFont(lbsdt.getFont().deriveFont(Font.BOLD, 20));
		nu.setFont(lbsdt.getFont().deriveFont(Font.BOLD, 20));
		phai.setFont(lbsdt.getFont().deriveFont(Font.BOLD, 20));

		
		String[] columns = {
			"Mã KH", "Họ", "Phái", "SĐT" , "Địa chỉ"	
		};
		modelKH = new DefaultTableModel(columns, 0);
		tableKhachHang = new JTable(modelKH);
		tableKhachHang.setPreferredScrollableViewportSize(new Dimension(940, 570));
		tableKhachHang.setRowHeight(30);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < tableKhachHang.getColumnCount(); i++) {
		    tableKhachHang.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		tableKhachHang.setFont(new Font("Arial", Font.BOLD, 18));
		

        JTableHeader header = tableKhachHang.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));

        header.setBackground(lightBlue);
        header.setFont(new Font("Arial", Font.BOLD, 20));
		
		String[] luaChon = {"Nam", "Nữ"};
        TableColumn phaiColumn = tableKhachHang.getColumnModel().getColumn(3);
        phaiColumn.setCellEditor(new DefaultCellEditor(new JComboBox<>(luaChon)));
        
        JScrollPane scrollPane = new JScrollPane(tableKhachHang);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(scrollPane, BorderLayout.EAST);
		
				
				
		jpS = new JPanel();
		jpS.setBorder(new EmptyBorder(10, 0, 0, 0));
		jpS.add(bTrai = new JPanel());
                
		bTrai.setLayout(new BoxLayout(bTrai, BoxLayout.X_AXIS));
		bTrai.setBackground(new Color(204, 235, 150));
			bTrai.add(lbNhap = new JLabel("Nhập mã số cần tìm: "));
			bTrai.add(txtNhap = new JTextField(15));
			bTrai.add(tim = new JButton("Tìm"));
			lbNhap.setFont(textFieldFont);
			txthoTen.setFont(textFieldFont);
			tim.setFont(textFieldFont);
			
				
		jpS.add(bPhai = new JPanel());
		jpS.setBackground(new Color(204, 235, 150));
		bPhai.setLayout(new BoxLayout(bPhai, BoxLayout.X_AXIS));
		bPhai.setBackground(new Color(204, 235, 150));
		bPhai.setLayout(new BoxLayout(bPhai, BoxLayout.X_AXIS));

		bPhai.add(Box.createHorizontalStrut(30));
		bPhai.add(them = new JButton("Thêm"));
		bPhai.add(Box.createHorizontalStrut(10));
		bPhai.add(xoaTrang = new JButton("Xóa trắng"));
		bPhai.add(Box.createHorizontalStrut(10));
		bPhai.add(xoa = new JButton("Xóa"));
		bPhai.add(Box.createHorizontalStrut(10));
		bPhai.add(luu = new JButton("Lưu"));

		bPhai.add(Box.createHorizontalGlue());
			them.setFont(textFieldFont);
			xoaTrang.setFont(textFieldFont);
			xoa.setFont(textFieldFont);
			luu.setFont(textFieldFont);
		add(jpS,BorderLayout.SOUTH);
	
	
		xoaTrang.addActionListener(this);
		them.addActionListener(this);
		xoa.addActionListener(this);
		tim.addActionListener(this);
		luu.addActionListener(this);
		docDuLieuDBVaoTable();
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(them)) {
			String maKH = txtMaKH.getText();
			String tenKH = txthoTen.getText();
			String phai = nam.isSelected()?"Nam" : nu.isSelected()?"Nữ":"";
			String diaChi = txtdiaChi.getText();
			String soDienThoai = txtsdt.getText();
			KhachHang kh = new KhachHang(maKH,tenKH,phai,soDienThoai,diaChi);

			try {
			    kh_dao.themKhachHang(kh);

			    modelKH.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(),kh.getPhai(),
			    		 kh.getSdt(),kh.getDiaChi()
			    });
			} catch (Exception e2) {
			    e2.printStackTrace(); 
			    JOptionPane.showMessageDialog(this, "Trùng mã");
			}
		}if (o.equals(luu)) {
		    int rowCount = modelKH.getRowCount();
		    for (int i = 0; i < rowCount; i++) {
		        String maKH = (String) modelKH.getValueAt(i, 0);
		        String tenKH = (String) modelKH.getValueAt(i, 1);
		        String phai = (String) modelKH.getValueAt(i, 2);
		        String soDienThoai = (String) modelKH.getValueAt(i, 3);
		        String diaChi = (String) modelKH.getValueAt(i, 4);

		        KhachHang kh = new KhachHang(maKH, tenKH, phai, soDienThoai, diaChi);
		        
		        try {
		            kh_dao.capNhatKhachHang(kh);
		        } catch (Exception e2) {
		            e2.printStackTrace(); 
		            JOptionPane.showMessageDialog(this, "Lỗi khi lưu dữ liệu vào cơ sở dữ liệu");
		            return;
		        }
		    }
		    
		    JOptionPane.showMessageDialog(this, "Dữ liệu đã được lưu thành công");
		}

		
		if (o.equals(xoa)) {
		    int r = tableKhachHang.getSelectedRow();
		    if (r != -1) {
		        String maKH = (String) modelKH.getValueAt(r, 0);
		        modelKH.removeRow(r);
		        kh_dao.xoaKhachHang(maKH);
		    }
		}
		if(o.equals(xoaTrang)) {
			txtMaKH.setText("");
			txthoTen.setText("");
			nam.setSelected(false);
			nu.setSelected(false);
			txtdiaChi.setText("");
			txtsdt.setText("");
			//đặt lại phương thức xóa trắng
    		ButtonGroup gr = new ButtonGroup();
			gr.add(nam);
			gr.add(nu);
			gr.clearSelection();
		}
		
		if (o.equals(tim)) {
		    String maKH = txtNhap.getText();
		    for (int i = 0; i < modelKH.getRowCount(); i++) {
		        Object maKHRow = modelKH.getValueAt(i, 0); 
		        if (maKH.equals(maKHRow)) { 
		            tableKhachHang.setRowSelectionInterval(i, i);
		            tableKhachHang.scrollRectToVisible(tableKhachHang.getCellRect(i, 0, true));
		            return;
		        }
		    }
		    JOptionPane.showMessageDialog(this, "Không tìm thấy mã");
		}
	}

	public void docDuLieuDBVaoTable() {

	    List<KhachHang> listKH = kh_dao.layThongTin();
	    for (KhachHang kh : listKH) {
	        modelKH.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getPhai(),
	        		kh.getSdt(), kh.getDiaChi() });
	    }
	}

}
