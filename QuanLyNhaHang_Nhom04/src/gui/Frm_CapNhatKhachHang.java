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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import connectDB.ConnectDB;
import dao.KhachHangDAO;
import entity.KhachHang;

public class Frm_CapNhatKhachHang extends JPanel implements ActionListener, MouseListener {
	private JLabel lbtitle;
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
		JTextField[] textFields = {txthoTen, txtdiaChi, txtsdt};

		for (JTextField textField : textFields) {
		    textField.setPreferredSize(textFieldSize);
		    textField.setHorizontalAlignment(SwingConstants.CENTER);
		    textField.setFont(textFieldFont);
		}
		
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
        TableColumn phaiColumn = tableKhachHang.getColumnModel().getColumn(2);
        phaiColumn.setCellEditor(new DefaultCellEditor(new JComboBox<>(luaChon)));
        
        JScrollPane scrollPane = new JScrollPane(tableKhachHang);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(scrollPane, BorderLayout.EAST);
		
				
				
		jpS = new JPanel();
		jpS.setBorder(new EmptyBorder(10, 0, 0, 0));
		jpS.add(bTrai = new JPanel());
                
		bTrai.setLayout(new BoxLayout(bTrai, BoxLayout.X_AXIS));
		bTrai.setBackground(new Color(204, 235, 150));
			bTrai.add(lbNhap = new JLabel("Nhập tên cần tìm: "));
			bTrai.add(txtNhap = new JTextField(15));
			txtNhap.setFont(new Font("Arial", Font.BOLD, 16));
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
		tableKhachHang.addMouseListener(this);
		docDuLieuDBVaoTable();
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(them)) {
			String maKH = maNgauNhien();
			String tenKH = txthoTen.getText();
			String phai = nam.isSelected()?"Nam" : nu.isSelected()?"Nữ":"";
			String diaChi = txtdiaChi.getText();
			String soDienThoai = txtsdt.getText();
			KhachHang kh = new KhachHang(maKH,tenKH,phai,soDienThoai,diaChi);

			try {
			    kh_dao.themKhachHang(kh);

			    modelKH.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(),kh.getPhai().trim(),
			    		 kh.getSdt(),kh.getDiaChi()
			    });
			    JOptionPane.showMessageDialog(this, "Thêm thành công");
			} catch (Exception e2) {
			    e2.printStackTrace(); 
			    JOptionPane.showMessageDialog(this, "Trùng mã");
			    return;
			}
		}
		if (o.equals(luu)) {
		    int r = tableKhachHang.getSelectedRow();
		    if (r != -1) {
		        String maKH = (String) modelKH.getValueAt(r, 0);
		        String tenKH = txthoTen.getText().trim();
		        String phai = nam.isSelected() ? "Nam" : "Nữ";
		        String sdt = txtsdt.getText().trim();
		        String diaChi = txtdiaChi.getText().trim();
		        
		        if (!tenKH.equals(modelKH.getValueAt(r, 1)) || !phai.equals(modelKH.getValueAt(r, 2)) || !sdt.equals(modelKH.getValueAt(r, 3)) || !diaChi.equals(modelKH.getValueAt(r, 4))) {
		            KhachHang kh = new KhachHang(maKH, tenKH, phai, sdt, diaChi);
		            try {
		                kh_dao.capNhatKhachHang(kh);
		                
		                modelKH.setValueAt(kh.getTenKH(), r, 1); 
		                modelKH.setValueAt(kh.getPhai(), r, 2);    
		                modelKH.setValueAt(kh.getSdt(), r, 3);  
		                modelKH.setValueAt(kh.getDiaChi(), r, 4);
		                
		                txthoTen.setText("");
		                nam.setSelected(false);
		                nu.setSelected(false);
		                txtdiaChi.setText("");
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
		    int r = tableKhachHang.getSelectedRow();
		    if (r != -1) {
		        String maKH = (String) modelKH.getValueAt(r, 0);
		        modelKH.removeRow(r);
		        kh_dao.xoaKhachHang(maKH);
		    }
		}
		if(o.equals(xoaTrang)) {
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
            String tenKH = txtNhap.getText();
            ListSelectionModel timKH = tableKhachHang.getSelectionModel();
            timKH.clearSelection(); 
            for (int i = 0; i < modelKH.getRowCount(); i++) {
                if (modelKH.getValueAt(i, 1).toString().contains(tenKH)) {
                	timKH.addSelectionInterval(i, i); 
                }
            }
            if (timKH.isSelectionEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng");
            }
        }
	}
	private String maNgauNhien() {
        Random rd = new Random();
        int ma = rd.nextInt(1000);
        return String.format("KH%03d", ma); 
    }
	public void docDuLieuDBVaoTable() {

	    List<KhachHang> listKH = kh_dao.layThongTin();
	    for (KhachHang kh : listKH) {
	        modelKH.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getPhai().trim(),
	        		kh.getSdt(), kh.getDiaChi() });
	    }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableKhachHang.getSelectedRow();
		txthoTen.setText(modelKH.getValueAt(row, 1).toString());
		String gioiTinh = modelKH.getValueAt(row, 2).toString();
	    if (gioiTinh.equals("Nam")) {
	        nam.setSelected(true);
	    } else {
	        nu.setSelected(true);
	    }
		txtsdt.setText(modelKH.getValueAt(row, 3).toString());
		txtdiaChi.setText(modelKH.getValueAt(row, 4).toString());
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
