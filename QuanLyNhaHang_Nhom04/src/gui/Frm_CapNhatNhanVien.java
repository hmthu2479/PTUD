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
import dao.NhanVienDAO;
import entity.NhanVien;

public class Frm_CapNhatNhanVien extends JPanel implements ActionListener {
	private JLabel lbtitle;
	private JLabel lbMaNV;
	private JTextField txtMaNV;
	private JLabel lbho;
	private JTextField txtho;
	private JLabel lbtuoi;
	private JTextField txtTuoi;
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
	private DefaultTableModel modelNV;
	private JTable tableNhanVien;
	private JRadioButton nam;
	private NhanVienDAO nv_dao;

	public Frm_CapNhatNhanVien() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nv_dao = new NhanVienDAO();
		
		setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		setBackground(new Color(173, 216, 230));
		jpN = new JPanel();
		jpN.setLayout(new BoxLayout(jpN, BoxLayout.Y_AXIS));
		jpN.setPreferredSize(new Dimension(520, 602));
		jpN.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        Color lightBlue = new Color(173, 216, 230); // Light blue colorS
		lbtitle = new JLabel("THÔNG TIN NHÂN VIÊN");
		lbtitle.setFont(new Font("Arial", Font.BOLD, 35));
		lbtitle.setForeground(Color.black);

		lbMaNV = new JLabel("Mã nhân viên: ");
		txtMaNV = new JTextField();
		lbho = new JLabel("Họ tên: ");
		txtho = new JTextField();
		lbtuoi = new JLabel("Tuổi: ");
		txtTuoi = new JTextField();
		lbsdt = new JLabel("Số điện thoại: ");
		txtsdt = new JTextField();
		phai = new JLabel("Phái: ");
		nu = new JRadioButton("Nữ");
		nam = new JRadioButton("Nam");
		ButtonGroup gr = new ButtonGroup();
		gr.add(nam);
		gr.add(nu);

		JPanel jpFields = new JPanel(new GridLayout(0, 1));
		jpFields.add(lbMaNV);
		jpFields.add(txtMaNV);
		jpFields.add(lbho);
		jpFields.add(txtho);
		jpFields.add(lbtuoi);
		jpFields.add(txtTuoi);
		jpFields.add(lbsdt);
		jpFields.add(txtsdt);

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
		JTextField[] textFields = {txtMaNV, txtho, txtTuoi, txtsdt};

		for (JTextField textField : textFields) {
		    textField.setPreferredSize(textFieldSize);
		    textField.setHorizontalAlignment(SwingConstants.CENTER);
		    textField.setFont(textFieldFont);
		}
		
		lbMaNV.setHorizontalAlignment(JLabel.CENTER);
		lbho.setHorizontalAlignment(JLabel.CENTER);
		lbtuoi.setHorizontalAlignment(JLabel.CENTER);
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
		emptyPanel.setPreferredSize(new Dimension(15, 390));
		emptyPanel.setBackground(new Color(173, 216, 230));
		add(emptyPanel,BorderLayout.CENTER);

        lbMaNV.setFont(lbMaNV.getFont().deriveFont(Font.BOLD, 20));
        lbho.setFont(lbho.getFont().deriveFont(Font.BOLD, 20));
        lbtuoi.setFont(lbtuoi.getFont().deriveFont(Font.BOLD, 20));
        lbsdt.setFont(lbsdt.getFont().deriveFont(Font.BOLD, 20));

		nam.setFont(lbsdt.getFont().deriveFont(Font.BOLD, 20));
		nu.setFont(lbsdt.getFont().deriveFont(Font.BOLD, 20));
		phai.setFont(lbsdt.getFont().deriveFont(Font.BOLD, 20));

		
		String[] columns = {
			"Mã NV", "Họ tên", "Phái","Tuổi", "SĐT" 	
		};
		modelNV = new DefaultTableModel(columns, 0);
		tableNhanVien = new JTable(modelNV);
		tableNhanVien.setPreferredScrollableViewportSize(new Dimension(940, 570));
		tableNhanVien.setRowHeight(30);

		tableNhanVien.setFont(new Font("Arial", Font.BOLD, 18));
		

        JTableHeader header = tableNhanVien.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));

        header.setBackground(lightBlue);
        header.setFont(new Font("Arial", Font.BOLD, 20));
		
		String[] luaChon = {"Nam", "Nữ"};
        TableColumn phaiColumn = tableNhanVien.getColumnModel().getColumn(2);
        phaiColumn.setCellEditor(new DefaultCellEditor(new JComboBox<>(luaChon)));
        
        JScrollPane scrollPane = new JScrollPane(tableNhanVien);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(scrollPane, BorderLayout.EAST);
		
				
				
		jpS = new JPanel();
		jpS.setBorder(new EmptyBorder(10, 0, 0, 0));
		jpS.add(bTrai = new JPanel());
                
		bTrai.setLayout(new BoxLayout(bTrai, BoxLayout.X_AXIS));
		bTrai.setBackground(new Color(173, 216, 230));
			bTrai.add(lbNhap = new JLabel("Nhập mã số cần tìm: "));
			bTrai.add(txtNhap = new JTextField(15));
			bTrai.add(tim = new JButton("Tìm"));
			lbNhap.setFont(textFieldFont);
			txtho.setFont(textFieldFont);
			tim.setFont(textFieldFont);
			
				
		jpS.add(bPhai = new JPanel());
		jpS.setBackground(new Color(173, 216, 230));
		bPhai.setLayout(new BoxLayout(bPhai, BoxLayout.X_AXIS));
		bPhai.setBackground(new Color(173, 216, 230));
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
			String maNV = txtMaNV.getText().trim();
			String tenNV = txtho.getText().trim();
			String phai = nam.isSelected() ? "Nam" : nu.isSelected() ? "Nu" : "";
			int tuoi = Integer.parseInt(txtTuoi.getText().trim());
			String soDienThoai = txtsdt.getText().trim();
			NhanVien nv = new NhanVien(maNV, tenNV, phai, tuoi, soDienThoai);

			try {
			    nv_dao.themNhanVien(nv);

			    modelNV.addRow(new Object[] { nv.getMaNV(), nv.getHoTenNV(),nv.getPhai(),
			        nv.getTuoi(), nv.getSdt()
			    });
			} catch (Exception e2) {
			    e2.printStackTrace(); 
			    JOptionPane.showMessageDialog(this, "Trùng mã");
			}
		}if (o.equals(luu)) {
		    // Iterate through the rows of the table model
		    int rowCount = modelNV.getRowCount();
		    for (int i = 0; i < rowCount; i++) {
		        String maNV = (String) modelNV.getValueAt(i, 0);
		        String tenNV = (String) modelNV.getValueAt(i, 1);
		        String phai = (String) modelNV.getValueAt(i, 2);
		        String chuoiTuoi = (String) modelNV.getValueAt(i, 3);
		        int tuoi = 0;
		        try {
		            tuoi = Integer.parseInt(chuoiTuoi);
		        } catch (NumberFormatException e1) {
		            e1.printStackTrace();
		        }
		        String soDienThoai = (String) modelNV.getValueAt(i, 4);
		        NhanVien nv = new NhanVien(maNV, tenNV, phai, tuoi, soDienThoai);
		        
		        try {
		            nv_dao.capNhatNhanVien(nv);
		        } catch (Exception e2) {
		            e2.printStackTrace(); 
		            JOptionPane.showMessageDialog(this, "Lỗi khi lưu dữ liệu vào cơ sở dữ liệu");
		            return; 
		        }
		    }
		    JOptionPane.showMessageDialog(this, "Dữ liệu đã được lưu thành công");
		}

		
		if (o.equals(xoa)) {
		    int r = tableNhanVien.getSelectedRow();
		    if (r != -1) {
		        String maNV = (String) modelNV.getValueAt(r, 0);
		        modelNV.removeRow(r);
		        nv_dao.xoaNhanVien(maNV);
		    }
		}
		if(o.equals(xoaTrang)) {
			txtMaNV.setText("");
			txtho.setText("");
			nam.setSelected(false);
			nu.setSelected(false);
			txtTuoi.setText("");
			txtsdt.setText("");
			//đặt lại phương thức xóa trắng
    		ButtonGroup gr = new ButtonGroup();
			gr.add(nam);
			gr.add(nu);
			gr.clearSelection();
		}
		
		if (o.equals(tim)) {
		    String maNV = txtNhap.getText();
		    for (int i = 0; i < modelNV.getRowCount(); i++) {
		        Object maNVRow = modelNV.getValueAt(i, 0);
		        if (maNV.equals(maNVRow.toString())) {
		            tableNhanVien.setRowSelectionInterval(i, i);
		            tableNhanVien.scrollRectToVisible(tableNhanVien.getCellRect(i, 0, true));
		            return;
		        }
		    }
		    JOptionPane.showMessageDialog(this, "Không tìm thấy mã");
		}
	}
	public void docDuLieuDBVaoTable() {
		List<NhanVien> listNV = nv_dao.layThongTin();
		for (NhanVien nv : listNV ) {
			modelNV.addRow(new Object [] {nv.getMaNV(), nv.getHoTenNV(),nv.getPhai(),
			        nv.getTuoi(), nv.getSdt()});
		}
	}
}
