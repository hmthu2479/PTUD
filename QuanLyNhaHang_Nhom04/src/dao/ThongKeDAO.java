package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class ThongKeDAO {


    public Map<LocalDate, Integer> thongKeTheoNgayLap() {
        Map<LocalDate, Integer> thongKe = new HashMap<>();

        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(
                     "SELECT ngayLap, COUNT(*) AS soLuong " +
                     "FROM HoaDon " +
                     "GROUP BY ngayLap"
                 )) {

                while (rs.next()) {
                    LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
                    int soLuong = rs.getInt("soLuong");
                    thongKe.put(ngayLap, soLuong);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.getInstance().disconnect();
        }

        return thongKe;
    }

    public Map<Integer, Integer> thongKeTheoThangLap() {
        Map<Integer, Integer> thongKe = new HashMap<>();

        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            try (PreparedStatement pstmt = con.prepareStatement(
                 "SELECT MONTH(ngayLap) AS thang, COUNT(*) AS soLuong " +
                 "FROM HoaDon " +
                 "GROUP BY MONTH(ngayLap)"
             )) {

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        int thang = rs.getInt("thang");
                        int soLuong = rs.getInt("soLuong");
                        thongKe.put(thang, soLuong);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.getInstance().disconnect();
        }

        return thongKe;
    }
    
}
