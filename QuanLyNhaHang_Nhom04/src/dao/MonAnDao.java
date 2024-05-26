package dao;

import connectDB.ConnectDB;
import entity.MonAn;

import java.sql.*;
import java.util.ArrayList;

public class MonAnDao {

    public ArrayList<MonAn> layThongTin(){
        ArrayList<MonAn> dsMonAn = new ArrayList<MonAn>();
        try{
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String SQL = "SELECT * FROM MonAn";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()){
                String maMA = rs.getString(1).trim();
                String tenMA = rs.getString(3).trim();
                String loaiMA = rs.getString(2).trim();
                double donGia =rs.getDouble(4);
                MonAn MA = new MonAn(maMA,loaiMA,tenMA,donGia);
                dsMonAn.add(MA);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsMonAn;
    }
    public String layMaMoiNhat() {
	    String ma = null;
	    try {
	        Connection connection = ConnectDB.getInstance().getConnection();
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT MAX(maMonAn) AS ma FROM MonAn");
	        if (resultSet.next()) {
	            ma = resultSet.getString("ma");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ma;
	}

    public boolean themMonAn(MonAn MonAn){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;

        String SQL = "INSERT INTO MonAn VALUES (?,?,?,?)";
        int n = 0;
        try{
        	statement = con.prepareStatement(SQL);
        	statement.setString(1, MonAn.getMaMonAn().trim());
        	statement.setString(2, MonAn.getLoaiMonAn().trim());
        	statement.setString(3, MonAn.getTenMonAn().trim());
        	statement.setDouble(4, MonAn.getDonGia());
  
            n = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return n>0;
    }

    public boolean xoaMonAn(String maMA){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        int n=0;
        try{
            String SQL = "DELETE FROM MonAn WHERE maMonAn = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,maMA);
            n = statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean capNhatMonAn(MonAn MonAn){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        int n = 0;
        try{
        	String SQL = "UPDATE MonAn SET LoaiMonAn = ?, TenMonAn = ?, donGia = ? WHERE maMonAn = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1, MonAn.getLoaiMonAn().trim());
            statement.setString(2, MonAn.getTenMonAn().trim());
        	statement.setDouble(3, MonAn.getDonGia());
            statement.setString(4, MonAn.getMaMonAn().trim());

            n = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return n>0;
    }
    public MonAn TimMonAn(String id) {
		MonAn MA = new MonAn();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {

			String sql = "select * from MonAn where maMonAn=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maMA = rs.getString(1).trim();
                String tenMA = rs.getString(2).trim();
                String loaiMA = rs.getString(3).trim();
                double donGia =rs.getDouble(4);
                MA = new MonAn(maMA,loaiMA,tenMA,donGia);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();

		} 
		return MA;
	}
    public ArrayList<MonAn> layThongTinTheoLoai(String loai) {
        ArrayList<MonAn> dsMonAn = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String SQL = "SELECT * FROM MonAn WHERE LoaiMonAn = ?";
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setString(1, loai);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maMA = rs.getString(1).trim();
                String tenMA = rs.getString(3).trim();
                String loaiMA = rs.getString(2).trim();
                double donGia = rs.getDouble(4);
                MonAn MA = new MonAn(maMA, loaiMA, tenMA, donGia);
                dsMonAn.add(MA);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsMonAn;
    }

}


