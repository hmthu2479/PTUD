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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import entity.KhachHang;
import entity.KhuVuc;
import entity.NhanVien;

public class Frm_CapNhatNhanVien extends JPanel implements ActionListener,MouseListener{
	private JLabel lbtitle;
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
	private JButton sua;
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
		JTextField[] textFields = { txtho, txtTuoi, txtsdt};

		for (JTextField textField : textFields) {
		    textField.setPreferredSize(textFieldSize);
		    textField.setHorizontalAlignment(SwingConstants.CENTER);
		    textField.setFont(textFieldFont);
		}
		
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

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < tableNhanVien.getColumnCount(); i++) {
			tableNhanVien.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

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
			bTrai.add(lbNhap = new JLabel("Nhập tên cần tìm: "));
			bTrai.add(txtNhap = new JTextField(15));
			txtNhap.setFont(new Font("Arial", Font.BOLD, 16));
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
		bPhai.add(sua = new JButton("Sửa"));


		bPhai.add(Box.createHorizontalGlue());
			them.setFont(textFieldFont);
			xoaTrang.setFont(textFieldFont);
			xoa.setFont(textFieldFont);
			sua.setFont(textFieldFont);
		add(jpS,BorderLayout.SOUTH);
	
	
		xoaTrang.addActionListener(this);
		them.addActionListener(this);
		xoa.addActionListener(this);
		tim.addActionListener(this);
		sua.addActionListener(this);
		tableNhanVien.addMouseListener(this);
		docDuLieuDBVaoTable();
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(them)) {
		    String maNV = maTangDan();
		    String tenNV = txtho.getText().trim();
		    String phai = nam.isSelected() ? "Nam" : nu.isSelected() ? "Nữ" : "";		
		    int tuoi;
		    try {
		        tuoi = Integer.parseInt(txtTuoi.getText().trim());
		        if (tuoi < 18 || tuoi > 60) {
		            JOptionPane.showMessageDialog(this, "Tuổi phải nằm trong khoảng từ 18 đến 60");
		            return;
		        }
		    } catch (NumberFormatException ex) {
		        JOptionPane.showMessageDialog(this, "Tuổi phải là số");
		        return;
		    }

		    String soDienThoai = txtsdt.getText().trim();
		    if (!soDienThoai.matches("\\d{10,11}")) {
		        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
		        return;
		    }
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
		}
			if (o.equals(sua)) {
		    int r = tableNhanVien.getSelectedRow();
		    if (r != -1) {
		        String maNV = (String) modelNV.getValueAt(r, 0);
		        String tenNV = txtho.getText().trim();
		        String phai = nam.isSelected() ? "Nam" : "Nữ";
		        int tuoi;
			    try {
			        tuoi = Integer.parseInt(txtTuoi.getText().trim());
			        if (tuoi < 18 || tuoi > 60) {
			            JOptionPane.showMessageDialog(this, "Tuổi phải nằm trong khoảng từ 18 đến 60");
			            return;
			        }
			    } catch (NumberFormatException ex) {
			        JOptionPane.showMessageDialog(this, "Tuổi phải là số");
			        return;
			    }
			    String soDienThoai = txtsdt.getText().trim();
			    if (!soDienThoai.matches("\\d{10,11}")) {
			        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
			        return;
			    }
		        NhanVien nv = new NhanVien(maNV, tenNV, phai, tuoi, soDienThoai);
		        
		        String ten = (String) modelNV.getValueAt(r, 1);
		        String Phai = (String) modelNV.getValueAt(r, 2);
		        int Tuoi = (int) modelNV.getValueAt(r, 3);
		        String Sdt = (String) modelNV.getValueAt(r, 4);
		        
		        if (!tenNV.equals(ten) || !phai.equals(Phai) || tuoi != Tuoi || !soDienThoai.equals(Sdt)) {
		            try {
		                nv_dao.capNhatNhanVien(nv);
		                modelNV.setValueAt(nv.getHoTenNV(), r, 1); 
		                modelNV.setValueAt(nv.getPhai(), r, 2);    
		                modelNV.setValueAt(nv.getTuoi(), r, 3);  
		                modelNV.setValueAt(nv.getSdt(), r, 4);
		                txtho.setText("");
		                nam.setSelected(false);
		                nu.setSelected(false);
		                txtTuoi.setText("");
		                txtsdt.setText("");
		                ButtonGroup gr = new ButtonGroup();
		                gr.add(nam);
		                gr.add(nu);
		                gr.clearSelection();
		                JOptionPane.showMessageDialog(this, "Dữ liệu đã được lưu thành công");
		            } catch (Exception e2) {
		                e2.printStackTrace(); 
		                JOptionPane.showMessageDialog(this, "Lỗi khi lưu dữ liệu vào cơ sở dữ liệu");
		            }
		        } else {
		            JOptionPane.showMessageDialog(this, "Không có thay đổi nào để lưu");
		        }
		    } else {
		        JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để cập nhật");
		    }
		}
		
			if (o.equals(xoa)) {
			    int r = tableNhanVien.getSelectedRow();
			    if (r != -1) {
			        String maNV = (String) modelNV.getValueAt(r, 0);
			        
			        int rs = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa nhân viên này?");
			        if (rs == JOptionPane.YES_OPTION) {
			            modelNV.removeRow(r);
			            nv_dao.xoaNhanVien(maNV);
			        }
			    }
			}
		if(o.equals(xoaTrang)) {
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
	        String tim = txtNhap.getText();
	        List<NhanVien> list = nv_dao.layThongTin();
	        //Lấy model của bảng hiện tại
	        DefaultTableModel model = (DefaultTableModel) tableNhanVien.getModel();
	        model.setRowCount(0);

	        // Duyệt qua từng Bàn trong danh sách
	        for (NhanVien nv : list) {
	            if (nv.getHoTenNV().contains(tim)) { 
	                //Thêm dòng mới vào bảng với thông tin của Bàn đó
	                model.addRow(new Object[]{nv.getMaNV(), nv.getHoTenNV(),nv.getPhai(),
	    		            nv.getTuoi(), nv.getSdt()});
	            }
	        }

	        if (model.getRowCount() == 0) {
	            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên");
	        }
	    }
	}
     
	private int count = 0;

	private String maTangDan() {
	    String ma = nv_dao.layMaMoiNhat();
	 // Lấy phần số của mã bàn (bỏ đi ký tự "NV") và tăng giá trị lên 1
	    count = Integer.parseInt(ma.substring(2)) + 1; 
	    return String.format("NV%03d", count);
	}


	public void docDuLieuDBVaoTable() {
		List<NhanVien> listNV = nv_dao.layThongTin();
		for (NhanVien nv : listNV ) {
			modelNV.addRow(new Object [] {nv.getMaNV(), nv.getHoTenNV(),nv.getPhai(),
			        nv.getTuoi(), nv.getSdt()});
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableNhanVien.getSelectedRow();
		txtho.setText(modelNV.getValueAt(row, 1).toString());
		String gioiTinh = modelNV.getValueAt(row, 2).toString();
	    if (gioiTinh.equals("Nam")) {
	        nam.setSelected(true);
	    } else {
	        nu.setSelected(true);
	    }
		txtTuoi.setText(modelNV.getValueAt(row, 3).toString());
		txtsdt.setText(modelNV.getValueAt(row, 4).toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
