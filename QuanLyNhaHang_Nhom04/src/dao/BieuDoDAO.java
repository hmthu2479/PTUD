package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import connectDB.ConnectDB;


public class BieuDoDAO {
    public Map<LocalDate, Double> thongKeTongTienTheoNgay() {
        Map<LocalDate, Double> thongKe = new HashMap<>();

        String query = "SELECT ngayLap, SUM(tongTien) AS tongTien " +
                       "FROM HoaDon " +
                       "GROUP BY ngayLap";

        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
                double tongTien = rs.getDouble("tongTien");
                thongKe.put(ngayLap, tongTien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return thongKe;
    }

    public Map<Integer, Double> thongKeTongTienTheoThang() {
        Map<Integer, Double> thongKe = new HashMap<>();

        String query = "SELECT MONTH(ngayLap) AS thang, SUM(tongTien) AS tongTien " +
                       "FROM HoaDon " +
                       "GROUP BY MONTH(ngayLap)";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int thang = rs.getInt("thang");
                double tongTien = rs.getDouble("tongTien");
                thongKe.put(thang, tongTien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return thongKe;
    }
}
