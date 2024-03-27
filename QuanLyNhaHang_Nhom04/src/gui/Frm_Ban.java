package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout; 
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Frm_Ban extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTable table;
	private DefaultTableModel modelKV;
	private JButton btn_them;
	private JButton btn_xoa;
	private JButton btn_luu;
	private JButton btn_thoat;
	private JTextField txt_them;
	private JLabel lbl_them;
	private JLabel lbl_tim;
	private JTextField txt_tim;
	private JButton btn_tim;
	private JComboBox cmb_khuVuc;

    public Frm_Ban() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 255, 255));
        titlePanel.setPreferredSize(new Dimension(100, 35)); 

        JLabel titleLabel = new JLabel("Chi tiết bàn");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        

        String[] columnNames = {"Bàn", "Sức chứa", "Thuộc"};

        modelKV = new DefaultTableModel(columnNames, 0);
		table = new JTable(modelKV);
        table.setFont(new Font("Arial", Font.BOLD, 16));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setRowHeight(30);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
        Color lightBlue = new Color(173, 216, 230); // Light blue color
        header.setBackground(lightBlue);
        header.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel pnlButton1 = new JPanel();
        pnlButton1.setLayout(new BoxLayout(pnlButton1, BoxLayout.X_AXIS));
        pnlButton1.setBackground(new Color(255, 255, 255));
        pnlButton1.setPreferredSize(new Dimension(100, 35));

        lbl_them = new JLabel("Nhập bàn: ");
        lbl_them.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl_them.setFont(new Font("Tahoma", Font.BOLD, 14));
        String[] khuVuc = {"Khu vực 1", "Khu vực 2", "Tầng 1", "Tầng 2"};
        JComboBox<String> cmb_khuVuc = new JComboBox<>(khuVuc);
        String[] phong = {"Phòng Vip 1","Phòng Vip 2"};

        for (String item : phong) {
            cmb_khuVuc.addItem(item);
        }
        txt_them = new JTextField();
        txt_them.setFont(new Font("Tahoma", Font.BOLD, 15));
        btn_them = new JButton("Thêm");
        btn_them.setFont(new Font("Tahoma", Font.BOLD, 15));
        pnlButton1.add(lbl_them);
        pnlButton1.add(cmb_khuVuc);
        pnlButton1.add(Box.createHorizontalStrut(3));
        pnlButton1.add(txt_them);
        pnlButton1.add(Box.createHorizontalStrut(6));
        pnlButton1.add(btn_them);
        pnlButton1.add(Box.createHorizontalStrut(12));
        lbl_tim = new JLabel("Nhập bàn cần tìm: ");
        lbl_tim.setFont(new Font("Tahoma", Font.BOLD, 14));
        txt_tim = new JTextField();
        txt_tim.setFont(new Font("Tahoma", Font.BOLD, 15));
        btn_tim = new JButton("Tìm");
        btn_tim.setFont(new Font("Tahoma", Font.BOLD, 15));
        pnlButton1.add(lbl_tim);
        pnlButton1.add(txt_tim);
        pnlButton1.add(Box.createHorizontalStrut(6));
        pnlButton1.add(btn_tim);

        JPanel pnlButton2 = new JPanel();
        pnlButton2.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlButton2.setBackground(new Color(255, 255, 255));
        pnlButton2.setPreferredSize(new Dimension(100, 35));

        btn_xoa = new JButton("Xóa");
        btn_xoa.setFont(new Font("Tahoma", Font.BOLD, 15));
        btn_luu = new JButton("Lưu");
        btn_luu.setFont(new Font("Tahoma", Font.BOLD, 15));
        btn_thoat = new JButton("Thoát");
        btn_thoat.setFont(new Font("Tahoma", Font.BOLD, 15));

        pnlButton2.add(btn_xoa);
        pnlButton2.add(btn_luu);
        pnlButton2.add(btn_thoat);

        JPanel pnlButton = new JPanel();
        pnlButton.setBackground(new Color(255, 255, 255));
        pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.Y_AXIS));
        pnlButton.add(pnlButton1);
        pnlButton.add(pnlButton2);
        
 
        JPanel pnlContainer = new JPanel();
        pnlContainer.setBackground(new Color(255, 255, 255));
        pnlContainer.setLayout(new BoxLayout(pnlContainer, BoxLayout.Y_AXIS));
        pnlContainer.add(titlePanel, BorderLayout.NORTH);
        pnlContainer.add(Box.createVerticalStrut(10));
        pnlContainer.add(new JScrollPane(table), BorderLayout.CENTER);
        pnlContainer.add(Box.createVerticalStrut(10));
        pnlContainer.add(pnlButton, BorderLayout.SOUTH);
        add(pnlContainer);
        pnlContainer.setBorder(new EmptyBorder(7, 15, 7, 15));
        setSize(760, 465);
    }

}


