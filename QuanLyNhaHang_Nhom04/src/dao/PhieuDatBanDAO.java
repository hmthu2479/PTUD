package dao;

import connectDB.ConnectDB;
import entity.Ban;
import entity.KhachHang;
import entity.KhuVuc;
import entity.NhanVien;
import entity.PhieuDatBan;
import entity.Phong;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class PhieuDatBanDAO {
    //lấy danh sách phiếu
    public ArrayList<PhieuDatBan> layThongTin(){
        ArrayList<PhieuDatBan> dsPhieuDB = new ArrayList<PhieuDatBan>();
        try{
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String SQL = "SELECT phieu.maPhieu, phieu.maKhuVuc, phieu.soBan, phieu.soNguoi, phieu.ngayDat, phieu.ngayLap, phieu.gioDat, phieu.maKH, phieu.maNV" +
	                "FROM PhieuDatBan phieu " +
	                "INNER JOIN KhuVuc k ON phieu.maKhuVuc = k.maKhuVuc " +
	                "INNER JOIN KhachHang kh ON phieu.maKH = kh.maKH"+
	                "INNER JOIN NhanVien nv ON phieu.maNV = nv.maNV";	
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()){
                String maPhieu = rs.getString(1);
	            String tenKhuVuc = rs.getString(2);
                String soBan = rs.getString(3);
                int soLuongNguoi = rs.getInt(4);
                String ngayDat = rs.getString(5);
                Date ngayLap = rs.getDate(6);
                String gioDat = rs.getString(7);
                String tenKH = rs.getString(8);
                String tenNV = rs.getString(9);
                
                KhuVuc khuVuc = new KhuVuc(tenKhuVuc);
                Ban ban = new Ban(soBan);
                NhanVien nv = new NhanVien(tenNV);
                KhachHang kh = new KhachHang(tenKH);
                PhieuDatBan p = new PhieuDatBan(maPhieu, tenKhuVuc, soBan, soLuongNguoi, ngayDat, null, gioDat, kh, nv);
                dsPhieuDB.add(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsPhieuDB;
    }
    //thêm phiếu
    public boolean themPhieu(PhieuDatBan phieu){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;

        String SQL = "INSERT INTO PhieuDatBan VALUES (?,?,?,?,?,?,?,?,?)";
        int n = 0;
        try{
            statement = con.prepareStatement(SQL);
            statement.setString(1,phieu.getMaPhieu());
            statement.setString(2,phieu.getKhuVuc());
            statement.setString(3,phieu.getSoBan());
            statement.setInt(4,phieu.getSoLuongNguoi());
            statement.setString(5,phieu.getNgayDat());
            LocalDate ngayLap = phieu.getNgayLap();
            Date sqlNgayLap = Date.valueOf(ngayLap.toString());
            statement.setDate(6, (java.sql.Date) sqlNgayLap);
            statement.setString(7,phieu.getGioDat());
            statement.setString(8,phieu.getKhachHang().getMaKH());
            statement.setString(9,phieu.getNhanVien().getMaNV());
            
            n = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return n>0;
    }
    
    public boolean xoaPhieu(String maPhieu){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        int n=0;
        try{
            String SQL = "DELETE FROM PhieuDatBan WHERE maPhieu = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,maPhieu);
            n = statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return n > 0;
    }
    //sửa phiếu
    public boolean capNhatPhieu(PhieuDatBan phieu){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        int n = 0;
        try{
            String SQL = "UPDATE PhieuDatBan SET maKhuVuc = ?, maBan = ?, soNguoi = ?, ngayDat = ?, ngayLap = ?, gioDat = ?, maKH =?, maNV = ? WHERE maPhieu = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,phieu.getMaPhieu());
            statement.setString(2,phieu.getKhuVuc());
            statement.setString(3,phieu.getSoBan());
            statement.setInt(4,phieu.getSoLuongNguoi());
            statement.setString(5,phieu.getNgayDat());
            LocalDate ngayLap = phieu.getNgayLap();
            Date sqlNgayLap = Date.valueOf(ngayLap.toString());
            statement.setDate(6, (java.sql.Date) sqlNgayLap);
            statement.setString(7,phieu.getGioDat());
            statement.setString(8,phieu.getKhachHang().getMaKH());
            statement.setString(9,phieu.getNhanVien().getMaNV());
            n = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return n>0;
    }

}


