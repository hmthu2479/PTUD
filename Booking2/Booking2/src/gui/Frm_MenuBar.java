package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;

public class Frm_MenuBar extends JFrame {
    private static Frm_MenuBar frm;
	private Frm_DatMon datMon; 
    private Frm_DatBan datBan; 
    private Frm_NhanVien nhanVien;
    private Frm_ThongKe thongKe;
    private Frm_QuanLyMonAn qly ;

    public Frm_MenuBar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Ordering App");
        setSize(1150, 650);
        setLocationRelativeTo(null);
       
        JMenu thucDon = new JMenu("Thực đơn");
        JMenuItem monAn = new JMenuItem("Món ăn");//when click into move to Frm_DatMon
        JMenuItem capNhatMonAn = new JMenuItem("Cập nhật thực đơn");//when click into move to Frm_QuanLyMonAn
        thucDon.add(monAn);
        thucDon.add(capNhatMonAn);

        JMenu ban = new JMenu("Bàn");
        JMenuItem capNhatBan = new JMenuItem("Cập nhật");//when click into move to Frm_DatBan
        ban.add(capNhatBan);
        
        JMenu nhanVienMenu = new JMenu("Nhân viên");
        JMenuItem datMonItem = new JMenuItem("Đặt món");
        JMenuItem datBanItem = new JMenuItem("Đặt bàn");//when click into move to Frm_DatBan
        JMenuItem capNhatNhanVien = new JMenuItem("Cập nhật nhân viên");//when click into move to Frm_NhanVien
        JMenuItem thongKeNhanVien = new JMenuItem("Thống kê doanh thu");//when click into move to Frm_ThongKe
        nhanVienMenu.add(datMonItem);
        nhanVienMenu.add(datBanItem);
        nhanVienMenu.add(capNhatNhanVien);
        nhanVienMenu.add(thongKeNhanVien);

        JMenu khachHangMenu = new JMenu("Khách hàng");
        JMenuItem timKiem = new JMenuItem("Tìm kiếm");
        JMenuItem capNhatKhachHang = new JMenuItem("Cập nhật");
        khachHangMenu.add(timKiem);
        khachHangMenu.add(capNhatKhachHang);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(thucDon);
        menuBar.add(ban);
        menuBar.add(nhanVienMenu);
        menuBar.add(khachHangMenu);

        ActionListener menuActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == datBanItem) {
                    if (datBan == null) {
                        datBan = new Frm_DatBan();
                    }
                    setContentPane(datBan);
                } else if (e.getSource() == datMonItem) {
                    if (datMon == null) {
                        datMon = new Frm_DatMon();
                    }
                    setContentPane(datMon);
                } else if (e.getSource() == capNhatNhanVien) {
                    if (nhanVien == null) {
                        nhanVien = new Frm_NhanVien();
                    }
                    setContentPane(nhanVien);
                } else if (e.getSource() == capNhatMonAn) {
                    if (qly == null) {
                        qly = new Frm_QuanLyMonAn();
                    }
                    setContentPane(qly);
                } else if (e.getSource() == thongKeNhanVien) {
                    if (thongKe == null) {
                        thongKe = new Frm_ThongKe();
                    }
                    setContentPane(thongKe);
                }
                revalidate();
            }
        };

        datMonItem.addActionListener(menuActionListener);
        datBanItem.addActionListener(menuActionListener);
        capNhatNhanVien.addActionListener(menuActionListener);
        capNhatMonAn.addActionListener(menuActionListener);
        thongKeNhanVien.addActionListener(menuActionListener);
        monAn.addActionListener(menuActionListener);
        capNhatBan.addActionListener(menuActionListener);
        

     // Set font and size for all menu items
        menuBar.getComponents();
        for (Component c : menuBar.getComponents()) {
            if (c instanceof JMenu) {
                JMenu menu = (JMenu) c;
                menu.setFont(new Font("Arial", Font.BOLD, 20)); 

                for (Component menuItem : menu.getMenuComponents()) {
                    if (menuItem instanceof JMenuItem) {
                        JMenuItem item = (JMenuItem) menuItem;
                        item.setFont(new Font("Arial", Font.BOLD, 17));
                    }
                }
            }
        }
        
        //Image on menu bar
        thucDon.setOpaque(false);
        thucDon.setBackground(new Color(0, 0, 0, 0));
        ImageIcon menuIcon = new ImageIcon(getClass().getResource("/img/icons8-manage-24.png"));
        Image menuImage = menuIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        thucDon.setIcon(new ImageIcon(menuImage));
        
        
        // Image on screen
        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/cute-aesthetic-cafe-art.jpg"));
        Image originalImage = icon.getImage();

        imageLabel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = imageLabel.getWidth();
                int height = imageLabel.getHeight();
                Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
            }
        });

        Dimension preferredSize = menuBar.getPreferredSize();
        preferredSize.height = 42;
        menuBar.setPreferredSize(preferredSize);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(menuBar, BorderLayout.NORTH);
        panel.add(imageLabel, BorderLayout.CENTER);
        setContentPane(panel);
        setJMenuBar(menuBar);
        setVisible(true);
    }

    public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Frm_MenuBar frm = new Frm_MenuBar();
				frm.setVisible(true);
			}
		});
	}

}