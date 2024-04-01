package dao;

import connectDB.ConnectDB;
import entity.Ban;
import entity.KhuVuc;
import entity.Phong;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BanDAO {
    //lấy danh sách Bàn
	public ArrayList<Ban> layThongTin() {
	    ArrayList<Ban> dsBan = new ArrayList<Ban>();
	    try {
	        ConnectDB.getInstance().connect();
	        Connection con = ConnectDB.getConnection();
	        String SQL = "SELECT b.maBan, b.soBan, b.soGhe, k.tenKhuVuc, p.tenPhong " +
		                "FROM Ban b " +
		                "INNER JOIN KhuVuc k ON b.maKhuVuc = k.maKhuVuc " +
		                "INNER JOIN Phong p ON b.maPhong = p.maPhong";		
	        Statement statement = con.createStatement();
	        ResultSet rs = statement.executeQuery(SQL);
	        while (rs.next()) {
	            String maBan = rs.getString(1);
	            String soBan = rs.getString(2);
	            int soGhe = rs.getInt(3);
	            String tenKhuVuc = rs.getString(4);
	            String tenPhong = rs.getString(5);
	            KhuVuc khuVuc = new KhuVuc(tenKhuVuc);
	            Phong phong = new Phong(tenPhong);
	            Ban b = new Ban(maBan, soBan, soGhe, khuVuc, phong);
	            dsBan.add(b);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsBan;
	}
    	//thêm Bàn
    	public boolean themBan(Ban ban) {
	        ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        PreparedStatement statement = null;
	        
	        String SQL = "insert into Ban (maBan, soBan, soGhe, maKhuVuc, maPhong) values (?, ?, ?,?,?)";
	        int n = 0;
	        try {
	            statement = con.prepareStatement(SQL);
	            statement.setString(1, ban.getMaBan());
	            statement.setString(2, ban.getSoBan());
	            statement.setInt(3, ban.getSoGhe());
	            statement.setString(4, ban.getKhuVuc().getMaKhuVuc());
	            statement.setString(5, ban.getPhong().getMaPhong());
	            n = statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return n > 0;
    }
	
    // Xóa khu vực
    public boolean xoaBan(String maBan){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        int n=0;
        try{
            String SQL = "DELETE FROM Ban WHERE maBan = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,maBan);
            n = statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return n > 0;
    }
    //sửa Bàn
    public boolean capNhatBan(Ban ban){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String SQL = "UPDATE Ban SET soBan = ?, soGhe = ?, maKhuVuc = ? , maPhong = ? WHERE maBan = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1, ban.getMaBan());
            statement.setString(2, ban.getSoBan());
            statement.setInt(3, ban.getSoGhe());
            statement.setString(4, ban.getKhuVuc().getMaKhuVuc());
            statement.setString(5, ban.getPhong().getMaPhong());
            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

}



