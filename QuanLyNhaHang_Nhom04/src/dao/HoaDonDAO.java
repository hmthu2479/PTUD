package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.Ban;
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
            String sql = "SELECT * FROM HoaDon";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String maHoaDon = resultSet.getString(1);
                double tongTien = resultSet.getDouble(2);
                KhuVuc khuVuc = new KhuVuc( resultSet.getString(3));
                Phong phong = new Phong( resultSet.getString(4));
                Ban banAn = new Ban( resultSet.getString(5));
                NhanVien tenNhanVien = new NhanVien ( resultSet.getString(6));
                Date ngayLap = resultSet.getDate(7);
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
            statement.setString(4,hd.getPhong().getMaPhong());
            statement.setString(5,hd.getBanAn().getMaBan());
            statement.setString(6,hd.getNhanVien().getMaNV());
            statement.setString(7,hd.getKhachHang().getMaKH());
            LocalDate ngayDat, ngayLap;
            ngayDat= hd.getNgayDat().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            ngayLap= hd.getNgayLap().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            statement.setDate(8, java.sql.Date.valueOf(ngayDat));
            statement.setDate(9, java.sql.Date.valueOf(ngayLap));
            n = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n>0;
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
                Date ngayLap = rs.getDate(7);
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

}

