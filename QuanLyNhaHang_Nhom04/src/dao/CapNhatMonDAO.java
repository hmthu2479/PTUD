package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CapNhatMonDAO {
    private static final String JDBC_URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhaHang";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123";

    public static void themMonAnVaoDatabase(String maMonAn, String tenMonAn, String loaiMonAn) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO Foods (MaMonAn, TenMonAn, LoaiMonAn) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, maMonAn);
                pstmt.setString(2, tenMonAn);
                pstmt.setString(3, loaiMonAn);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm dữ liệu vào cơ sở dữ liệu: " + e.getMessage());
        }
    }
    public static void themMonNuocVaoDatabase(String maMonNuoc, String tenMonNuoc, String loaiMonNuoc) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO Foods (MaMonAn, TenMonAn, LoaiMonAn) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, maMonNuoc);
                pstmt.setString(2, tenMonNuoc);
                pstmt.setString(3, loaiMonNuoc);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm dữ liệu vào cơ sở dữ liệu: " + e.getMessage());
        }
}
}

