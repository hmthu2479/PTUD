package entity;

public class MonAn {
	private String maMonAn;
	private String loaiMonAn;
	private String tenMonAn;
	private double donGia;
	public MonAn(String maMonAn, String loaiMonAn, String tenMonAn, double donGia) {
		this.maMonAn = maMonAn;
		this.loaiMonAn = loaiMonAn;
		this.tenMonAn = tenMonAn;
		this.donGia =donGia;
	}
	public MonAn(String maMonAn) {
		// TODO Auto-generated constructor stub
		this.maMonAn = maMonAn;
	}
	public MonAn() {
		// TODO Auto-generated constructor stub
	}
	public String getMaMonAn() {
		return maMonAn;
	}
	public void setMaMonAn(String maMonAn) {
		this.maMonAn = maMonAn;
	}
	public String getLoaiMonAn() {
		return loaiMonAn;
	}
	public void setLoaiMonAn(String loaiMonAn) {
		this.loaiMonAn = loaiMonAn;
	}
	public String getTenMonAn() {
		return tenMonAn;
	}
	public void setTenMonAn(String tenMonAn) {
		this.tenMonAn = tenMonAn;
	}
	@Override
	public String toString() {
		return "MonAn [maMonAn=" + maMonAn + ", loaiMonAn=" + loaiMonAn + ", tenMonAn=" + tenMonAn + "]";
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	
	
}

