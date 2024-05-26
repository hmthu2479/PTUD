package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Mon;

public class ChiTietHoaDonDAO{
	public ArrayList<ChiTietHoaDon> layThongTin() {
		ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String SQL = "SELECT * FROM ChiTietHoaDon";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(SQL);
			while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getString(1));
                String mon = rs.getString(2);
                int soLuong = rs.getInt(3);
                double donGia = rs.getDouble(4);
                double thanhtien = rs.getDouble(5);
                ChiTietHoaDon ct = new ChiTietHoaDon(hd, mon,soLuong,donGia,thanhtien);
                dsChiTietHoaDon.add(ct);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsChiTietHoaDon;
	}

	public boolean themDon(ChiTietHoaDon ct) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String SQL = "INSERT INTO ChiTietHoaDon VALUES (?,?,?,?,?)";
		int n = 0;
		try {
			statement = con.prepareStatement(SQL);
            statement.setString(1, ct.getHoaDon().getMaHoaDon());
            statement.setString(2, ct.getMon());
            statement.setInt(3, ct.getSoLuong());
            statement.setDouble(4, ct.getDonGia());
            statement.setDouble(5, ct.getThanhTien());
            n = statement.executeUpdate();
        } catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
		public ArrayList<ChiTietHoaDon> TimHoaDon(String id) {
		ArrayList<ChiTietHoaDon> ds = new ArrayList<ChiTietHoaDon>();

		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {

			String sql = "select * from ChiTietHoaDon where maHoaDon=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getString(1));
                String mon = rs.getString(2);
                int soLuong = rs.getInt(3);
                double donGia = rs.getDouble(4);
                double thanhtien = rs.getDouble(5);
                ChiTietHoaDon ct = new ChiTietHoaDon(hd, mon,soLuong,donGia,thanhtien);
                ds.add(ct);
            }

		} catch (SQLException e) {
			e.printStackTrace();

		} 
		return ds;
	}
}
