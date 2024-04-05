package dao;

import connectDB.ConnectDB;
import entity.KhuVuc;
import entity.Phong;

import java.sql.*;
import java.util.ArrayList;

public class PhongDAO {

    public ArrayList<Phong> layThongTin() {
        ArrayList<Phong> dsPhong = new ArrayList<>();
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String SQL = "SELECT p.maPhong, p.tenPhong, k.tenKhuVuc, " +
                         "       (SELECT SUM(b.soGhe) FROM Ban b WHERE b.maPhong = p.maPhong) AS soGhe " +
                         "FROM Phong p " +
                         "INNER JOIN KhuVuc k ON p.maKhuVuc = k.maKhuVuc";

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                String maPhong = rs.getString(1).trim();
                String tenPhong = rs.getString(2).trim();
                String tenKhuVuc = rs.getString(3).trim();
                int soGhe = rs.getInt(4);
                KhuVuc khuVuc = new KhuVuc(tenKhuVuc);
                Phong p = new Phong(maPhong, tenPhong, khuVuc, soGhe);
                dsPhong.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsPhong;
    }

    public boolean themPhong(Phong phong) {
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String SQL = "INSERT INTO Phong (maPhong, tenPhong, maKhuVuc) VALUES (?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setString(1, phong.getMaPhong().trim());
            statement.setString(2, phong.getTenPhong().trim());
            statement.setString(3, phong.getKhuVuc().getMaKhuVuc());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaPhong(String maPhong) {
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String SQL = "DELETE FROM Phong WHERE maPhong = ?";
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setString(1, maPhong);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean capNhatPhong(Phong phong) {
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String SQL = "UPDATE Phong SET tenPhong = ?, maKhuVuc = ?, soGhe = ? WHERE maPhong = ?";
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setString(1, phong.getTenPhong());
            statement.setString(2, phong.getKhuVuc().getMaKhuVuc());
            statement.setInt(3, phong.getSoGhe());
            statement.setString(4, phong.getMaPhong());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
