package gui;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class Frm_KhuVuc extends JDialog {

	    private static final long serialVersionUID = 1L;
	    private JTable table;
		private DefaultTableModel modelKV;
		private JButton btn_them;
		private JButton btn_xoa;
		private JButton btn_luu;
		private JButton btn_thoat;
		private JTextField txt_them;
		private JLabel lbl_them;

	    public Frm_KhuVuc() {
	        setTitle("Chi tiết khu vực");
	        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        JPanel titlePanel = new JPanel();
	        titlePanel.setBackground(new Color(255, 255, 255));
	        titlePanel.setPreferredSize(new Dimension(100, 35)); 

	        JLabel titleLabel = new JLabel("Chi tiết khu vực");
	        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
	        titlePanel.add(titleLabel);
	        

	        String[] columnNames = {"Mã khu vực", "Tên khu vực"};

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

	        JPanel pnlButton = new JPanel();
	        pnlButton.setBackground(new Color(255, 255, 255));
	        pnlButton.setPreferredSize(new Dimension(100, 35));
	        pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.X_AXIS));

	        lbl_them = new JLabel("Nhập tên khu vực cần thêm: ");
	        lbl_them.setFont(new Font("Tahoma", Font.BOLD, 14));
	        txt_them = new JTextField();
	        txt_them.setFont(new Font("Tahoma", Font.BOLD, 15));
	        btn_them = new JButton("Thêm KV");
	        btn_them.setFont(new Font("Tahoma", Font.BOLD, 15));
	        pnlButton.add(lbl_them);
	        pnlButton.add(txt_them);
	        pnlButton.add(Box.createHorizontalStrut(6));
	        pnlButton.add(btn_them);
	        pnlButton.add(Box.createHorizontalStrut(15));
	        btn_xoa = new JButton("Xóa KV");
	        btn_xoa.setFont(new Font("Tahoma", Font.BOLD, 15));
	        pnlButton.add(btn_xoa);
	        pnlButton.add(Box.createHorizontalStrut(6));
	        btn_luu = new JButton("Lưu");
	        btn_luu.setFont(new Font("Tahoma", Font.BOLD, 15));
	        pnlButton.add(btn_luu);
	        pnlButton.add(Box.createHorizontalStrut(6));
	        btn_thoat = new JButton("Thoát");
	        btn_thoat.setFont(new Font("Tahoma", Font.BOLD, 15));
	        pnlButton.add(btn_thoat);

	        
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
	        setSize(730, 465);
	    }
	
}
