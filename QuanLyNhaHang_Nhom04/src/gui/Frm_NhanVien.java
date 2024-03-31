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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import connectDB.ConnectDB;
import dao.NhanVienDAO;
import entity.NhanVien;

public class Frm_NhanVien extends JPanel implements ActionListener {
	private JLabel lb_title;
	private JLabel lb_MaNV;
	private JTextField txtMaNV;
	private JLabel lb_ho;
	private JTextField txtho;
	private JLabel lb_tuoi;
	private JTextField txtTuoi;
	private JLabel lb_sdt;
	private JTextField txtsdt;
	private JRadioButton nu;
	private JPanel jpS;
	private JPanel bTrai;
	private JPanel bPhai;
	private JLabel lb_Nhap;
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

	public Frm_NhanVien() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nv_dao = new NhanVienDAO();
		
		setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		setBackground(Color.white);
		jpN = new JPanel();
		jpN.setLayout(new BoxLayout(jpN, BoxLayout.Y_AXIS));
		jpN.setPreferredSize(new Dimension(520, 602));
		jpN.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        Color lightBlue = new Color(173, 216, 230); // Light blue colorS
		lb_title = new JLabel("THÔNG TIN NHÂN VIÊN");
		lb_title.setFont(new Font("Arial", Font.BOLD, 35));
		lb_title.setForeground(Color.black);

		lb_MaNV = new JLabel("Mã nhân viên: ");
		txtMaNV = new JTextField();
		lb_ho = new JLabel("Họ: ");
		txtho = new JTextField();
		lb_tuoi = new JLabel("Tuổi: ");
		txtTuoi = new JTextField();
		lb_sdt = new JLabel("Số điện thoại: ");
		txtsdt = new JTextField();
		phai = new JLabel("Phái: ");
		nu = new JRadioButton("Nữ");
		nam = new JRadioButton("Nam");
		ButtonGroup gr = new ButtonGroup();
		gr.add(nam);
		gr.add(nu);

		JPanel jpFields = new JPanel(new GridLayout(0, 1));
		jpFields.add(lb_MaNV);
		jpFields.add(txtMaNV);
		jpFields.add(lb_ho);
		jpFields.add(txtho);
		jpFields.add(lb_tuoi);
		jpFields.add(txtTuoi);
		jpFields.add(lb_sdt);
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
		txtMaNV.setPreferredSize(textFieldSize);
		txtho.setPreferredSize(textFieldSize);
		txtTuoi.setPreferredSize(textFieldSize);
		txtsdt.setPreferredSize(textFieldSize);
		
		Font textFieldFont = new Font("Arial", Font.BOLD, 18); 
		txtMaNV.setFont(textFieldFont);
		txtho.setFont(textFieldFont);
		txtTuoi.setFont(textFieldFont);
		txtsdt.setFont(textFieldFont);
		
		lb_MaNV.setHorizontalAlignment(JLabel.CENTER);
		lb_ho.setHorizontalAlignment(JLabel.CENTER);
		lb_tuoi.setHorizontalAlignment(JLabel.CENTER);
		lb_sdt.setHorizontalAlignment(JLabel.CENTER);
		jpPhai.setAlignmentX(Component.CENTER_ALIGNMENT);

		lb_title.setAlignmentX(Component.CENTER_ALIGNMENT);

		jpN.add(Box.createVerticalGlue()); 
		jpN.add(lb_title);
		jpN.add(Box.createVerticalStrut(10)); 
		jpN.add(jpFields);
		jpN.add(Box.createVerticalStrut(10)); 
		jpN.add(jpPhai);
		jpN.add(Box.createVerticalGlue());

		add(jpN, BorderLayout.WEST);
		JPanel emptyPanel = new JPanel();
		emptyPanel.setPreferredSize(new Dimension(15, 390));
		emptyPanel.setBackground(Color.white);
		add(emptyPanel,BorderLayout.CENTER);

        // Set font size and boldness for labels
       // lb_title.setFont(lb_title.getFont().deriveFont(Font.BOLD, 25));
        lb_MaNV.setFont(lb_MaNV.getFont().deriveFont(Font.BOLD, 20));
        lb_ho.setFont(lb_ho.getFont().deriveFont(Font.BOLD, 20));
        lb_tuoi.setFont(lb_tuoi.getFont().deriveFont(Font.BOLD, 20));
        lb_sdt.setFont(lb_sdt.getFont().deriveFont(Font.BOLD, 20));

		nam.setFont(lb_sdt.getFont().deriveFont(Font.BOLD, 20));
		nu.setFont(lb_sdt.getFont().deriveFont(Font.BOLD, 20));
		phai.setFont(lb_sdt.getFont().deriveFont(Font.BOLD, 20));

		
		String[] columns = {
			"Mã NV", "Họ", "Phái", "Tuổi", "SĐT" 	
		};
		modelNV = new DefaultTableModel(columns, 0);
		tableNhanVien = new JTable(modelNV);
		tableNhanVien.setPreferredScrollableViewportSize(new Dimension(940, 570));
		tableNhanVien.setRowHeight(30);
		

        JTableHeader header = tableNhanVien.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));

        header.setBackground(lightBlue);
        header.setFont(new Font("Arial", Font.BOLD, 20));
		
		String[] luaChon = {"Nam", "Nữ"};
        TableColumn phaiColumn = tableNhanVien.getColumnModel().getColumn(3);
        phaiColumn.setCellEditor(new DefaultCellEditor(new JComboBox<>(luaChon)));
        
        JScrollPane scrollPane = new JScrollPane(tableNhanVien);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(scrollPane, BorderLayout.EAST);
		
				
				
		jpS = new JPanel();
		jpS.setBorder(new EmptyBorder(10, 0, 0, 0));
		jpS.add(bTrai = new JPanel());
                
		bTrai.setLayout(new BoxLayout(bTrai, BoxLayout.X_AXIS));
		bTrai.setBackground(Color.white);
			bTrai.add(lb_Nhap = new JLabel("Nhập mã số cần tìm: "));
			bTrai.add(txtNhap = new JTextField(15));
			bTrai.add(tim = new JButton("Tìm"));
			lb_Nhap.setFont(textFieldFont);
			txtho.setFont(textFieldFont);
			tim.setFont(textFieldFont);
			
				
		jpS.add(bPhai = new JPanel());
		jpS.setBackground(Color.white);
		bPhai.setLayout(new BoxLayout(bPhai, BoxLayout.X_AXIS));
		bPhai.setBackground(Color.white);
		bPhai.setLayout(new BoxLayout(bPhai, BoxLayout.X_AXIS));

		bPhai.add(Box.createHorizontalStrut(30));
		bPhai.add(them = new JButton("Thêm"));
		bPhai.add(Box.createHorizontalStrut(10));
		bPhai.add(xoaTrang = new JButton("Xóa trắng"));
		bPhai.add(Box.createHorizontalStrut(10));
		bPhai.add(xoa = new JButton("Xóa"));

		bPhai.add(Box.createHorizontalGlue());
			them.setFont(textFieldFont);
			xoaTrang.setFont(textFieldFont);
			xoa.setFont(textFieldFont);
		add(jpS,BorderLayout.SOUTH);
	
	
		xoaTrang.addActionListener(this);
		them.addActionListener(this);
		xoa.addActionListener(this);
		tim.addActionListener(this);
		docDuLieuDBVaoTable();
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(them)) {
			String maNV = txtMaNV.getText();
			String tenNV = txtho.getText();
			String phai = nam.isSelected()?"Nam" : nu.isSelected()?"Nữ":"";
			int tuoi = Integer.parseInt(txtTuoi.getText());
			String soDienThoai = txtsdt.getText();
			NhanVien nv = new NhanVien(maNV,tenNV,phai,tuoi,soDienThoai);

			try {
			    nv_dao.themNhanVien(nv);

			    modelNV.addRow(new Object[] { nv.getMaNV(), nv.getHoTenNV(),nv.getPhai(),
			        nv.getTuoi(), nv.getSdt()
			    });
			} catch (Exception e2) {
			    e2.printStackTrace(); 
			    JOptionPane.showMessageDialog(this, "Trùng mã");
			}
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
		    String maNV = Integer.parseInt(txtNhap.getText());
		    for (int i = 0; i < modelNV.getRowCount(); i++) {
		        int maNVRow = (int) modelNV.getValueAt(i, 0);
		        if (maNV == maNVRow) {
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
