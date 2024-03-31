package dao;

import connectDB.ConnectDB;
import entity.PhieuDatBan;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class PhieuDatBanDAO {
    //lấy danh sách nhân viên
    public ArrayList<PhieuDatBan> PhieuDatBan(){
        ArrayList<PhieuDatBan> dsPhieuDB = new ArrayList<PhieuDatBan>();
        try{
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String SQL = "SELECT * FROM NhanVien";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()){
                String maPhieu = rs.getString(1);
                String khuVuc = rs.getString(2);
                String soBan = rs.getString(3);
                int soLuongNguoi = rs.getInt(4);
                String ngayThang = rs.getString(6);
                String ngayLap = rs.getString(7);
                String gioDat = rs.getString(8);
                String hoTen = rs.getString(9);
                String soDienThoai = rs.getString(5);
                String diaChi = rs.getString(5);
                PhieuDatBan p = new PhieuDatBan(maPhieu,khuVuc,phai,tuoi,soDienThoai);
                dsPhieuDB.add(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsPhieuDB;
    }
    //thêm nhân viên
    public boolean themNhanVien(NhanVien nhanVien){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;

        String SQL = "INSERT INTO NhanVien VALUES (?,?,?,?,?)";
        int n = 0;
        try{
            statement = con.prepareStatement(SQL);
            statement.setString(1,nhanVien.getMaNV());
            statement.setString(2,nhanVien.getHoTenNV());
            statement.setString(3,nhanVien.getPhai());
            statement.setInt(4,nhanVien.getTuoi());
            statement.setString(5,nhanVien.getSdt());
  
            n = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return n>0;
    }
    // Xóa nhân viên
    public boolean xoaNhanVien(String maNV){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        int n=0;
        try{
            String SQL = "DELETE FROM NhanVien WHERE maNhanVien = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,maNV);
            n = statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return n > 0;
    }
    //sửa nhân viên
    public boolean capNhatNhanVien(NhanVien nhanVien){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        int n = 0;
        try{
            String SQL = "UPDATE NhanVien SET hoTen = ?, ngaySinh = ?, diaChi = ?, soDT = ?, eMail = ? WHERE maNhanVien = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,nhanVien.getMaNV());
            statement.setString(2,nhanVien.getHoTenNV());
            statement.setString(3,nhanVien.getPhai());
            statement.setInt(4,nhanVien.getTuoi());
            statement.setString(5,nhanVien.getSdt());
            n = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return n>0;
    }

    //kiểm tra mã nhân viên
    public boolean kiemTraMaNV(String maNV){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        ResultSet rs = null;
        try{
            String SQL = "SELECT * FROM NhanVien WHERE maNhanVien = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,maNV);
            rs = statement.executeQuery();
            if (rs.next()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}


