package menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.*;

import datBan.Frm_DatBan;
import goiMon.Frm_GoiMon;
import nhanVien.Frm_NhanVien;
import quanLy.Frm_QuanLyMonAn;
import thongKe.Frm_ThongKe;

public class MenuBar extends JFrame implements ActionListener{
    private Frm_GoiMon ordering; // Reference to the Ordering frame
    private Frm_DatBan bookingPanel; // Reference to the Booking panel
    private Frm_NhanVien gdien;
    private Frm_ThongKe tKe;
    private Frm_QuanLyMonAn qly;

    public MenuBar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Ordering App");
        setSize(1150, 600);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create the "Đặt bàn" menu item
        JMenuItem booking = new JMenuItem("Đặt bàn");
        JMenuItem goiMon = new JMenuItem("Gọi món");
        JMenuItem nhanVien = new JMenuItem("Nhân viên");
        JMenuItem quanLy = new JMenuItem("Quản lý món ăn");
        JMenuItem khachHang = new JMenuItem("Khách hàng");
        JMenuItem thongKe = new JMenuItem("Thống kê");

        ActionListener menuActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == booking) {
                    if (bookingPanel == null) {
                        bookingPanel = new Frm_DatBan();
                    }
                    setContentPane(bookingPanel);
                } else if (e.getSource() == goiMon) {
                    if (ordering == null) {
                        ordering = new Frm_GoiMon();
                    }
                    setContentPane(ordering);
                } else if (e.getSource() == nhanVien) {
                    if (gdien == null) {
                        gdien = new Frm_NhanVien();
                    }
                    setContentPane(gdien);
                } else if (e.getSource() == quanLy) {
                	if (qly == null) {
                        qly = new Frm_QuanLyMonAn();
                    }
                    setContentPane(qly);
                } else if (e.getSource() == khachHang) {
                    // Handle Khách hàng menu item action
                } else if (e.getSource() == thongKe) {
                	if (tKe == null) {
                        tKe = new Frm_ThongKe();
                    } 
                	 setContentPane(tKe);
                }
                revalidate();
            }
        };

        booking.addActionListener(menuActionListener);
        goiMon.addActionListener(menuActionListener);
        nhanVien.addActionListener(menuActionListener);
        quanLy.addActionListener(menuActionListener);
        khachHang.addActionListener(menuActionListener);
        thongKe.addActionListener(menuActionListener);
        

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(quanLy);
        menuBar.add(goiMon);
        menuBar.add(booking);
        menuBar.add(khachHang);
        menuBar.add(nhanVien);
        menuBar.add(thongKe);

        // Set font and size for all menu items
        menuBar.getComponents();
        for (Component c : menuBar.getComponents()) {
            if (c instanceof JMenu || c instanceof JMenuItem) {
                c.setFont(new Font("Arial", Font.BOLD, 20)); // Set the desired font and size here
            }
        }

        try {
            File iconFile = new File("C:/Users/Admin/eclipse-workspace/Booking2/src/img/icons8-manage-24.png");
            ImageIcon icon = new ImageIcon(iconFile.toURI().toURL());
            
            // Resize the icon to a smaller size
            Image iconImage = icon.getImage();
            Image resizedIconImage = iconImage.getScaledInstance(24, 24, Image.SCALE_DEFAULT); // Set the desired width and height here
            ImageIcon resizedIcon = new ImageIcon(resizedIconImage);
            
            quanLy.setIcon(resizedIcon);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Dimension preferredSize = menuBar.getPreferredSize();
        preferredSize.height = 42; // Set the desired height here
        menuBar.setPreferredSize(preferredSize);


        setJMenuBar(menuBar);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuBar::new);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}