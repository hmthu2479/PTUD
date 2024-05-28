package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.Ban;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuVuc;
import entity.NhanVien;
import entity.Phong;

public class HoaDonDAO {
	public ArrayList<HoaDon> getAllHoaDon() {
		ArrayList<HoaDon> hoaDonList = new ArrayList<>();
        try {
        	ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
	        String SQL = "SELECT hd.maHoaDon, hd.tongTien, k.tenKhuVuc, p.tenPhong, b.soBan, nv.tenNV, hd.ngayLap, kh.tenKH, hd.ngayDat " +
	                "FROM HoaDon hd " +
	                "INNER JOIN KhuVuc k ON hd.maKhuVuc = k.maKhuVuc " +
	                "LEFT JOIN KhachHang kh ON hd.maKH = kh.maKH " +
	                "INNER JOIN NhanVien nv ON hd.maNV = nv.maNV " + 
	                "INNER JOIN Ban b ON hd.maBan = b.maBan " +
	                "LEFT JOIN Phong p ON hd.maPhong = p.maPhong";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                String maHoaDon = resultSet.getString(1);
                double tongTien = resultSet.getDouble(2);
                KhuVuc khuVuc = new KhuVuc( resultSet.getString(3));
                Phong phong = resultSet.getString(4) != null ? new Phong(resultSet.getString(4).trim()) : new Phong(null);
                Ban banAn = new Ban( resultSet.getString(5));
                NhanVien tenNhanVien = new NhanVien ( resultSet.getString(6));
                Timestamp timestamp = resultSet.getTimestamp(7);
                
                // Step 2: Convert Timestamp to LocalDateTime
                LocalDateTime ngayLap = timestamp.toLocalDateTime();
                KhachHang tenKhachHang = new KhachHang( resultSet.getString(8));
                Date ngayDat = resultSet.getDate(9);

                HoaDon hoaDon = new HoaDon(maHoaDon, tongTien, khuVuc, phong, banAn, tenNhanVien, ngayLap, tenKhachHang, ngayDat);
                hoaDonList.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoaDonList;
    }

	public boolean themHoaDon(HoaDon hd){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String sql = "INSERT INTO HoaDon VALUES (?,?,?,?,?,?,?,?,?)";
        int n = 0;
        PreparedStatement statement = null;
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,hd.getMaHoaDon());
            statement.setDouble(2,hd.getTongTien());
            statement.setString(3,hd.getKhuVuc().getMaKhuVuc());
	        if (hd.getPhong() != null) {
	            statement.setString(4, hd.getPhong().getMaPhong());
	        } else {
	            statement.setNull(4, Types.VARCHAR);
	        }
            statement.setString(5,hd.getBanAn().getMaBan());
            statement.setString(6,hd.getNhanVien().getMaNV());
            LocalDateTime ngayLap = hd.getNgayLap();
		     Timestamp timestamp = Timestamp.valueOf(ngayLap);
		     statement.setTimestamp(7, timestamp);
	        if (hd.getKhachHang() != null) {
	            statement.setString(8, hd.getKhachHang().getMaKH());
	        } else {
	            statement.setNull(8, Types.VARCHAR);
	        }
	         
	        if (hd.getNgayDat() != null) {
	            statement.setDate(9, new java.sql.Date(hd.getNgayDat().getTime()));
	        } else {
	            statement.setNull(9, Types.VARCHAR);
	        } 
            n = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n>0;
    }
	public String layMaMoiNhat() {
	    String ma = null;
	    try {
	        Connection connection = ConnectDB.getInstance().getConnection();
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT MAX(maHoaDon) AS ma FROM HoaDon");
	        if (resultSet.next()) {
	            ma = resultSet.getString("ma");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ma;
	}
	public ArrayList<HoaDon> timHoaDon(String idHD){
        ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try{
            String SQL = "SELECT * FROM HoaDon WHERE maHoaDon = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,idHD);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maHoaDon = rs.getString(1);
                double tongTien = rs.getDouble(2);
                KhuVuc khuVuc = new KhuVuc( rs.getString(3));
                Phong phong = new Phong( rs.getString(4));
                Ban banAn = new Ban( rs.getString(5));
                NhanVien tenNhanVien = new NhanVien ( rs.getString(6));
                Timestamp timestamp = rs.getTimestamp(7);
                LocalDateTime ngayLap = timestamp.toLocalDateTime();
                KhachHang tenKhachHang = new KhachHang( rs.getString(8));
                Date ngayDat = rs.getDate(9);

                HoaDon hoaDon = new HoaDon(maHoaDon, tongTien, khuVuc, phong, banAn, tenNhanVien, ngayLap, tenKhachHang, ngayDat);
                dsHoaDon.add(hoaDon);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsHoaDon;
        }
	
	public boolean xoaHoaDon(String idHD) {
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement statement = null;
	    try {
	        String SQL = "DELETE FROM HoaDon WHERE maHoaDon = ?";
	        statement = con.prepareStatement(SQL);
	        statement.setString(1, idHD);
	        int r = statement.executeUpdate();
	        if (r > 0) {
	            return true; // Hóa đơn đã được xóa thành công
	        } else {
	            return false; // Không tìm thấy hóa đơn để xóa
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    } 
	    
	}
}

