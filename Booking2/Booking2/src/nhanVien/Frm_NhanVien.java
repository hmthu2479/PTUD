package nhanVien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComponent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class Frm_NhanVien extends JPanel implements ActionListener {
	private JLabel lb_title;
	private JPanel jpMaNV;
	private JLabel lb_MaNV;
	private JTextField ijpMaNV;
	private JPanel jpHoTen;
	private JPanel jpho;
	private JLabel lb_ho;
	private JTextField ijpho;
	private JPanel jpTuoi;
	private JLabel lb_tuoi;
	private JTextField ijpTuoi;
	private JPanel jpTien;
	private JLabel lb_tien;
	private JTextField ijptien;
	private JPanel jpTuoi_Phai;
	private JPanel jpPhai;
	private JRadioButton nu;
	private JPanel jpS;
	private JPanel bTrai;
	private JPanel bPhai;
	private JLabel lb_Nhap;
	private JTextField ijpNhap;
	private JButton tim;
	private JButton them;
	private JButton xoaTrang;
	private JButton xoa;
	private JButton luu;
	private JPanel jpN;
	private JLabel phai;
	private DefaultTableModel modelNV;
	private JTable tableNhanVien;
	private NhanVien_Collection listNV;
	private JRadioButton nam;

	public Frm_NhanVien() {
		setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		setBackground(Color.white);
		jpN = new JPanel();
		jpN.setLayout(new BoxLayout(jpN, BoxLayout.Y_AXIS));
		jpN.setPreferredSize(new Dimension(470, 432));
		jpN.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        Color lightBlue = new Color(173, 216, 230); // Light blue color
        Color lightYellow = new Color(255, 255, 153);
		lb_title = new JLabel("THÔNG TIN NHÂN VIÊN");
		lb_title.setFont(new Font("Arial", Font.BOLD, 35));
		lb_title.setForeground(Color.black);

		lb_MaNV = new JLabel("Mã nhân viên: ");
		ijpMaNV = new JTextField();
		lb_ho = new JLabel("Họ: ");
		ijpho = new JTextField();
		lb_tuoi = new JLabel("Tuổi: ");
		ijpTuoi = new JTextField();
		lb_tien = new JLabel("Tiền lương: ");
		ijptien = new JTextField();
		phai = new JLabel("Phái: ");
		nu = new JRadioButton("Nữ");
		nam = new JRadioButton("Nam");
		ButtonGroup gr = new ButtonGroup();
		gr.add(nam);
		gr.add(nu);

		// Create a JPanel with GridLayout for the labels and text fields
		JPanel jpFields = new JPanel(new GridLayout(0, 1));
		jpFields.add(lb_MaNV);
		jpFields.add(ijpMaNV);
		jpFields.add(lb_ho);
		jpFields.add(ijpho);
		jpFields.add(lb_tuoi);
		jpFields.add(ijpTuoi);
		jpFields.add(lb_tien);
		jpFields.add(ijptien);

		// Create a JPanel for the radio buttons
		JPanel jpPhai = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jpPhai.add(phai);
		jpPhai.add(nu);
		jpPhai.add(nam);

		// Center align the components within jpFields
		for (Component component : jpFields.getComponents()) {
		    if (component instanceof JLabel || component instanceof JTextField) {
		        ((JComponent) component).setAlignmentX(Component.CENTER_ALIGNMENT);
		    }
		}
		
		Dimension textFieldSize = new Dimension(200, 30); // Adjust the width and height as needed
		ijpMaNV.setPreferredSize(textFieldSize);
		ijpho.setPreferredSize(textFieldSize);
		ijpTuoi.setPreferredSize(textFieldSize);
		ijptien.setPreferredSize(textFieldSize);
		
		Font textFieldFont = new Font("Arial", Font.BOLD, 16); // Adjust the font size as needed
		ijpMaNV.setFont(textFieldFont);
		ijpho.setFont(textFieldFont);
		ijpTuoi.setFont(textFieldFont);
		ijptien.setFont(textFieldFont);
		
		lb_MaNV.setHorizontalAlignment(JLabel.CENTER);
		lb_ho.setHorizontalAlignment(JLabel.CENTER);
		lb_tuoi.setHorizontalAlignment(JLabel.CENTER);
		lb_tien.setHorizontalAlignment(JLabel.CENTER);
		// Center align the components within jpPhai
		jpPhai.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Center align the title within jpN
		lb_title.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Add the JPanels to jpN
		jpN.add(Box.createVerticalGlue()); // Add glue to push components to top
		jpN.add(lb_title);
		jpN.add(Box.createVerticalStrut(10)); // Add vertical spacing
		jpN.add(jpFields);
		jpN.add(Box.createVerticalStrut(10)); // Add vertical spacing
		jpN.add(jpPhai);
		jpN.add(Box.createVerticalGlue()); // Add glue to push components to bottom

		add(jpN, BorderLayout.WEST);
		JPanel emptyPanel = new JPanel();
		emptyPanel.setPreferredSize(new Dimension(10, 390));
		emptyPanel.setBackground(Color.white);
		add(emptyPanel,BorderLayout.CENTER);

        // Set font size and boldness for labels
        lb_title.setFont(lb_title.getFont().deriveFont(Font.BOLD, 25));
        lb_MaNV.setFont(lb_MaNV.getFont().deriveFont(Font.BOLD, 18));
        lb_ho.setFont(lb_ho.getFont().deriveFont(Font.BOLD, 18));
        lb_tuoi.setFont(lb_tuoi.getFont().deriveFont(Font.BOLD, 18));
        lb_tien.setFont(lb_tien.getFont().deriveFont(Font.BOLD, 18));

		nam.setFont(lb_tien.getFont().deriveFont(Font.BOLD, 18));
		nu.setFont(lb_tien.getFont().deriveFont(Font.BOLD, 18));
		phai.setFont(lb_tien.getFont().deriveFont(Font.BOLD, 18));

		
		String[] columns = {
			"Mã NV", "Họ", "Phái", "Tuổi", "Tiền lương" 	
		};
		modelNV = new DefaultTableModel(columns, 0);
		tableNhanVien = new JTable(modelNV);
		tableNhanVien.setPreferredScrollableViewportSize(new Dimension(600, 400));
		tableNhanVien.setRowHeight(30);
		

        JTableHeader header = tableNhanVien.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));

        header.setBackground(lightBlue);
        header.setFont(new Font("Arial", Font.BOLD, 16));
		
		String[] luaChon = {"Nam", "Nữ"};
        TableColumn phaiColumn = tableNhanVien.getColumnModel().getColumn(3);
        phaiColumn.setCellEditor(new DefaultCellEditor(new JComboBox<>(luaChon)));
        
        JScrollPane scrollPane = new JScrollPane(tableNhanVien);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(scrollPane, BorderLayout.EAST);
		
		listNV = new NhanVien_Collection();
				
				
		jpS = new JPanel();
		jpS.add(bTrai = new JPanel());
                
		bTrai.setLayout(new BoxLayout(bTrai, BoxLayout.X_AXIS));
		bTrai.setBackground(Color.white);
			bTrai.add(lb_Nhap = new JLabel("Nhập mã số cần tìm: "));
			bTrai.add(ijpNhap = new JTextField(15));
			bTrai.add(tim = new JButton("Tìm"));
			lb_Nhap.setFont(textFieldFont);
			ijpho.setFont(textFieldFont);
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
		
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(them)) {
			String ma = ijpMaNV.getText();
			String ho = ijpho.getText();
			int tuoi = Integer.parseInt(ijpTuoi.getText());
			String phai_Nam_Nu = nam.isSelected()?"Nam" : nu.isSelected()?"Nữ":"";
			double luong = Double.parseDouble(ijptien.getText());
			
			NhanVien nv = new NhanVien(ma, ho, phai_Nam_Nu, tuoi, luong);
			
			if(!listNV.themNV(nv)) {
				JOptionPane.showMessageDialog(this, "Trùng mã");
			}
			else {
				modelNV.addRow(new Object[] {nv.getMaNV(), nv.getHoNV(),
						 phai_Nam_Nu,nv.getTuoi(), nv.getTienLuong()
				});
			}
		}
		if(o.equals(xoa)) {
			int r = tableNhanVien.getSelectedRow();
			modelNV.removeRow(r);
			
			NhanVien nv = listNV.getElement(r);
			listNV.xoaNV(nv.getMaNV());
		}
		if(o.equals(xoaTrang)) {
			ijpMaNV.setText("");
			ijpho.setText("");
			nam.setSelected(false);
			nu.setSelected(false);
			ijpTuoi.setText("");
			ijptien.setText("");
			//đặt lại phương thức xóa trắng
    		ButtonGroup gr = new ButtonGroup();
			gr.add(nam);
			gr.add(nu);
			gr.clearSelection();
		}
		if (o.equals(tim)) {
            String maTim = ijpNhap.getText();
            if(maTim != null && maTim.trim().length() > 0) {
            	try {
            		NhanVien nvTim = listNV.tim(maTim);
            		if (nvTim != null) {
                        JOptionPane.showMessageDialog(this,
                                "Thông tin nhân viên:\nMã: " + nvTim.getMaNV() +
                                        "\nHọ: " + nvTim.getHoNV() +
                                        "\nTuổi: " + nvTim.getTuoi() +
                                        "\nPhái: " + nvTim.getPhai() +
                                        "\nTiền lương: " + nvTim.getTienLuong(),
                                "Tìm kiếm thành công", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên có mã " + maTim, "Thông báo",
                                JOptionPane.ERROR_MESSAGE);
                    }
            	}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ");
				}
            }
        }
	}
}
