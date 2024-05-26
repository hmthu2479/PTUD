package dao;

import connectDB.ConnectDB;
import entity.MonNuoc;

import java.sql.*;
import java.util.ArrayList;

public class MonNuocDao {

    public ArrayList<MonNuoc> layThongTin(){
        ArrayList<MonNuoc> dsMonNuoc = new ArrayList<MonNuoc>();
        try{
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String SQL = "SELECT * FROM MonNuoc";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()){
                String maMN = rs.getString(1).trim();
                String tenMN = rs.getString(3).trim();
                String loaiMN = rs.getString(2).trim();
                double donGia =rs.getDouble(4);
                MonNuoc mn = new MonNuoc(maMN,loaiMN,tenMN,donGia);
                dsMonNuoc.add(mn);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsMonNuoc;
    }
    public String layMaMoiNhat() {
	    String ma = null;
	    try {
	        Connection connection = ConnectDB.getInstance().getConnection();
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT MAX(maMonNuoc) AS ma FROM MonNuoc");
	        if (resultSet.next()) {
	            ma = resultSet.getString("ma");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ma;
	}

    public boolean themMonNuoc(MonNuoc MonNuoc){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;

        String SQL = "INSERT INTO MonNuoc VALUES (?,?,?,?)";
        int n = 0;
        try{
        	statement = con.prepareStatement(SQL);
        	statement.setString(1, MonNuoc.getMaMonNuoc().trim());
        	statement.setString(2, MonNuoc.getLoaiMonNuoc().trim());
        	statement.setString(3, MonNuoc.getTenMonNuoc().trim());
        	statement.setDouble(4, MonNuoc.getDonGia());
  
            n = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return n>0;
    }

    public boolean xoaMonNuoc(String maMN){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        int n=0;
        try{
            String SQL = "DELETE FROM MonNuoc WHERE maMonNuoc = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,maMN);
            n = statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean capNhatMonNuoc(MonNuoc MonNuoc){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        int n = 0;
        try{
        	String SQL = "UPDATE MonNuoc SET loaiMonNuoc = ?, tenMonNuoc = ?, donGia = ? WHERE maMonNuoc = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1, MonNuoc.getLoaiMonNuoc().trim());
            statement.setString(2, MonNuoc.getTenMonNuoc().trim());
        	statement.setDouble(3, MonNuoc.getDonGia());
            statement.setString(4, MonNuoc.getMaMonNuoc().trim());

            n = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return n>0;
    }
    public MonNuoc TimMonNuoc(String id) {
		MonNuoc mn = new MonNuoc();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {

			String sql = "select * from MonNuoc where maMonNuoc=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maMN = rs.getString(1).trim();
                String tenMN = rs.getString(2).trim();
                String loaiMN = rs.getString(3).trim();
                double donGia =rs.getDouble(4);
                mn = new MonNuoc(maMN,loaiMN,tenMN,donGia);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();

		} 
		return mn;
	}
    public ArrayList<MonNuoc> layThongTinTheoLoai(String loai) {
        ArrayList<MonNuoc> dsMonNuoc = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String SQL = "SELECT * FROM MonNuoc WHERE loaiMonNuoc = ?";
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setString(1, loai);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maMN = rs.getString(1).trim();
                String tenMN = rs.getString(3).trim();
                String loaiMN = rs.getString(2).trim();
                double donGia = rs.getDouble(4);
                MonNuoc mn = new MonNuoc(maMN, loaiMN, tenMN, donGia);
                dsMonNuoc.add(mn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsMonNuoc;
    }

}


