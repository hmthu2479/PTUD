package dao;

import connectDB.ConnectDB;
import entity.Ban;
import entity.KhuVuc;
import entity.Mon;
import entity.Phong;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MonDAO {

	// Lấy danh sách món ăn
	public ArrayList<Mon> layDanhSachMon() {
		ArrayList<Mon> dsMon = new ArrayList<>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String SQL = "SELECT m.*, k.tenKhuVuc, p.tenPhong, b.soBan FROM Mon m " +
					"INNER JOIN Ban b ON m.maBan = b.maBan " +
					"INNER JOIN Phong p ON b.maPhong = p.maPhong " +
					"INNER JOIN KhuVuc k ON p.maKhuVuc = k.maKhuVuc";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(SQL);
			while (rs.next()) {
				String maMon = rs.getString("maMon");
				String tenMon = rs.getString("tenMon");
				double donGia = rs.getDouble("donGia");
				String loaiMon = rs.getString("loaiMon");
				int soLuong = rs.getInt("soLuong");
				String soBan = rs.getString("soBan");
				String tenKhuVuc = rs.getString("tenKhuVuc");
				String tenPhong = rs.getString("tenPhong");
				double tongTien = rs.getDouble("tongTien");
				KhuVuc khuVuc = new KhuVuc(tenKhuVuc);
				Phong phong = new Phong(tenPhong);
				Ban ban = new Ban(soBan);
				Mon mon = new Mon(khuVuc, phong, ban, maMon, tenMon, loaiMon, donGia, soLuong, tongTien);
				dsMon.add(mon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsMon;
	}

	// Thêm món ăn
	public boolean themMon(Mon mon) {
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String SQL = "INSERT INTO Mon (maMon, tenMon, donGia, loaiMon, soLuong) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = con.prepareStatement(SQL);
			statement.setString(1, mon.getMaMon());
			statement.setString(2, mon.getTenMon());
			statement.setDouble(3, mon.getDonGia());
			statement.setString(4, mon.getLoaiMon());
			statement.setInt(5, mon.getSoLuong());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	// Xóa món ăn theo mã
	public boolean xoaMon(String maMon) {
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String SQL = "DELETE FROM Mon WHERE maMon = ?";
			PreparedStatement statement = con.prepareStatement(SQL);
			statement.setString(1, maMon);
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	// Cập nhật thông tin món ăn
	public void suaMon(Mon mon) {
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String SQL = "UPDATE Mon SET tenMon = ?, donGia = ?, loaiMon = ?, soLuong = ? WHERE maMon = ?";
			PreparedStatement statement = con.prepareStatement(SQL);
			statement.setString(1, mon.getTenMon());
			statement.setDouble(2, mon.getDonGia());
			statement.setString(3, mon.getLoaiMon());
			statement.setInt(4, mon.getSoLuong());
			statement.setString(5, mon.getMaMon());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	// Lấy thông tin món ăn theo mã (tính toán tổng giá tiền)
	public Mon layThongTinMonTheoMa(String maMon) {
		Mon mon = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String SQL = "SELECT m.*, k.tenKhuVuc, p.tenPhong, b.soBan FROM Mon m " +
					"INNER JOIN Ban b ON m.maBan = b.maBan " +
					"INNER JOIN Phong p ON b.maPhong = p.maPhong " +
					"INNER JOIN KhuVuc k ON p.maKhuVuc = k.maKhuVuc " +
					"WHERE m.maMon = ?";
			PreparedStatement statement = con.prepareStatement(SQL);
			statement.setString(1, maMon);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String tenMon = rs.getString("tenMon");
				double donGia = rs.getDouble("donGia");
				String loaiMon = rs.getString("loaiMon");
				int soLuong = rs.getInt("soLuong");
				String soBan = rs.getString("soBan");
				String tenKhuVuc = rs.getString("tenKhuVuc");
				String tenPhong = rs.getString("tenPhong");
				KhuVuc khuVuc = new KhuVuc(tenKhuVuc);
				Phong phong = new Phong(tenPhong);
				Ban ban = new Ban(soBan);
				double tongTien = rs.getDouble("tongTien"); 
				mon = new Mon(khuVuc, phong, ban, maMon, tenMon, loaiMon, donGia, soLuong, tongTien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mon;
	}

	// Tìm kiếm món ăn theo tên
	public List<Mon> timKiemMonTheoTen(String tenMon) {
		List<Mon> dsMon = new ArrayList<>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String SQL = "SELECT m.*, k.tenKhuVuc, p.tenPhong, b.soBan FROM Mon m " +
					"INNER JOIN Ban b ON m.maBan = b.maBan " +
					"INNER JOIN Phong p ON b.maPhong = p.maPhong " +
					"INNER JOIN KhuVuc k ON p.maKhuVuc = k.maKhuVuc " +
					"WHERE m.tenMon LIKE ?";
			PreparedStatement statement = con.prepareStatement(SQL);
			statement.setString(1, "%" + tenMon + "%"); // Tìm kiếm theo tên có dấu
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maMon = rs.getString("maMon");
				double donGia = rs.getDouble("donGia");
				String loaiMon = rs.getString("loaiMon");
				int soLuong = rs.getInt("soLuong");
				String soBan = rs.getString("soBan");
				String tenKhuVuc = rs.getString("tenKhuVuc");
				String tenPhong = rs.getString("tenPhong");
				KhuVuc khuVuc = new KhuVuc(tenKhuVuc);
				Phong phong = new Phong(tenPhong);
				Ban ban = new Ban(soBan);
				double tongTien = soLuong * donGia;
				Mon mon = new Mon(khuVuc, phong, ban, maMon, tenMon, loaiMon, donGia, soLuong, tongTien);
				dsMon.add(mon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsMon;
	}

	// Cập nhật số lượng món ăn
	public boolean capNhatSoLuongMon(String maMon, int soLuongMoi) {
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String SQL = "UPDATE Mon SET soLuong = ? WHERE maMon = ?";
			PreparedStatement statement = con.prepareStatement(SQL);
			statement.setInt(1, soLuongMoi);
			statement.setString(2, maMon);
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public Mon TimMon(String idMon) {
	    Mon mon = null;
	    try {
	        ConnectDB.getInstance().connect();
	        Connection con = ConnectDB.getConnection();
	        String SQL = "SELECT * FROM Mon WHERE maMon = ?";
	        PreparedStatement statement = con.prepareStatement(SQL);
	        statement.setString(1, idMon);
	        ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	        	String maMon = rs.getString("maMon");
				String tenMon = rs.getString("tenMon");
				double donGia = rs.getDouble("donGia");
				String loaiMon = rs.getString("loaiMon");
				int soLuong = rs.getInt("soLuong");
				String soBan = rs.getString("soBan");
				String tenKhuVuc = rs.getString("tenKhuVuc");
				String tenPhong = rs.getString("tenPhong");
				double tongTien = rs.getDouble("tongTien");
				KhuVuc khuVuc = new KhuVuc(tenKhuVuc);
				Phong phong = new Phong(tenPhong);
				Ban ban = new Ban(soBan);
				mon = new Mon(khuVuc, phong, ban, maMon, tenMon, loaiMon, donGia, soLuong, tongTien);
				
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return mon;
	}

}