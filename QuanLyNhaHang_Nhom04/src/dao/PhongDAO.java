package dao;

import connectDB.ConnectDB;
import entity.KhuVuc;
import entity.Phong;

import java.sql.*;
import java.util.ArrayList;

public class PhongDAO {
    //lấy danh sách phòng
	public ArrayList<Phong> layThongTin() {
	    ArrayList<Phong> dsPhong = new ArrayList<Phong>();
	    try {
	        ConnectDB.getInstance().connect();
	        Connection con = ConnectDB.getConnection();
	        String SQL = "SELECT p.maPhong, p.tenPhong, k.tenKhuVuc " +
	                     "FROM Phong p " +
	                     "INNER JOIN KhuVuc k ON p.maKhuVuc = k.maKhuVuc";
	        Statement statement = con.createStatement();
	        ResultSet rs = statement.executeQuery(SQL);
	        while (rs.next()) {
	            String maPhong = rs.getString(1).trim();
	            String tenPhong = rs.getString(2).trim();
	            String tenKhuVuc = rs.getString(3).trim();
	            KhuVuc khuVuc = new KhuVuc(tenKhuVuc);
	            Phong p = new Phong(maPhong, tenPhong, khuVuc);
	            dsPhong.add(p);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsPhong;
	}
    	//thêm khu vực
    	public boolean themPhong(Phong phong) {
	        ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        PreparedStatement statement = null;
	        
	        String SQL = "insert into Phong (maPhong, tenPhong, maKhuVuc) values (?, ?, ?)";
	        int n = 0;
	        try {
	            statement = con.prepareStatement(SQL);
	            statement.setString(1, phong.getMaPhong().trim());
	            statement.setString(2, phong.getTenPhong().trim());
	            statement.setString(3, phong.getKhuVuc().getMaKhuVuc());
	            
	            n = statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return n > 0;
    }
	
    // Xóa phiếu
    public boolean xoaPhong(String maPhong){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        int n=0;
        try{
            String SQL = "DELETE FROM Phong WHERE maPhong = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,maPhong);
            n = statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return n > 0;
    }
    //sửa phiếu
    public boolean capNhatPhong(Phong phong){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String SQL = "UPDATE Phong SET tenPhong = ?, maKhuVuc = ? WHERE maPhong = ?";
            statement = con.prepareStatement(SQL);
            //statement.setString(1, phong.getMaPhong());
            statement.setString(1, phong.getTenPhong());
            statement.setString(2, phong.getKhuVuc().getMaKhuVuc());
            statement.setString(3, phong.getMaPhong());
            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }


}


