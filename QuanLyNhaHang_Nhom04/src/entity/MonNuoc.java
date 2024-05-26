package entity;

public class MonNuoc {
	private String maMonNuoc;
	private String loaiMonNuoc;
	private String tenMonNuoc;
	private double donGia;
	public MonNuoc(String maMonNuoc, String loaiMonNuoc, String tenMonNuoc,double donGia) {
		super();
		this.maMonNuoc = maMonNuoc;
		this.loaiMonNuoc = loaiMonNuoc;
		this.tenMonNuoc = tenMonNuoc;
		this.donGia =donGia;
	}
	public MonNuoc(String maMonNuoc) {
		// TODO Auto-generated constructor stub
		this.maMonNuoc = maMonNuoc;
	}
	public MonNuoc() {
		// TODO Auto-generated constructor stub
	}
	public String getMaMonNuoc() {
		return maMonNuoc;
	}
	public void setMaMonNuoc(String maMonNuoc) {
		this.maMonNuoc = maMonNuoc;
	}
	public String getLoaiMonNuoc() {
		return loaiMonNuoc;
	}
	public void setLoaiMonNuoc(String loaiMonNuoc) {
		this.loaiMonNuoc = loaiMonNuoc;
	}
	public String getTenMonNuoc() {
		return tenMonNuoc;
	}
	public void setTenMonNuoc(String tenMonNuoc) {
		this.tenMonNuoc = tenMonNuoc;
	}

	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	
	
}
