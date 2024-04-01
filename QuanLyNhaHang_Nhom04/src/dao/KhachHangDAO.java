package dao;

import connectDB.ConnectDB;
import entity.KhachHang;

import java.sql.*;
import java.util.ArrayList;

public class KhachHangDAO {
    //lấy danh sách khách hàng
    public ArrayList<KhachHang> layThongTin(){
        ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
        try{
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String SQL = "SELECT * FROM KhachHang";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()){
                String maKH = rs.getString(1);
                String tenKH = rs.getString(2);
                String diaChi = rs.getString(5);
                String soDienThoai = rs.getString(4);
                String phai = rs.getString(3);
                KhachHang kh = new KhachHang(maKH,tenKH,phai,soDienThoai,diaChi);
                dsKH.add(kh);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsKH;
    }
    //thêm Khách hàng
    public boolean themKhachHang(KhachHang khachHang){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;

        String SQL = "INSERT INTO KhachHang VALUES (?,?,?,?,?)";
        int n = 0;
        try{
            statement = con.prepareStatement(SQL);
            statement.setString(1,khachHang.getMaKH());
            statement.setString(2,khachHang.getTenKH());
            statement.setString(3,khachHang.getPhai());
            statement.setString(4,khachHang.getSdt());
            statement.setString(5,khachHang.getDiaChi());
            n = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return n>0;
    }
    // Xóa nhân viên
    public boolean xoaKhachHang(String maKH){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        int n=0;
        try{
            String SQL = "DELETE FROM KhachHang WHERE maKH = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,maKH);
            n = statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return n > 0;
    }
    //sửa khách hàng
    public boolean capNhatKhachHang(KhachHang khachHang){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        int n = 0;
        try{
        	String SQL = "UPDATE KhachHang SET tenKH = ?, phai = ?, sdt = ?, diaChi = ? WHERE maKH = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,khachHang.getMaKH());
            statement.setString(2,khachHang.getTenKH());
            statement.setString(3,khachHang.getPhai());
            statement.setString(4,khachHang.getSdt());
            statement.setString(5,khachHang.getDiaChi());
            n = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return n>0;
    }


}

