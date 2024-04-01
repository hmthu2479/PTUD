package entity;

public class Phong {
	private String maPhong;
	private String tenPhong;
	private KhuVuc khuVuc;
	public Phong(String maPhong, String tenPhong, KhuVuc khuVuc) {
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.khuVuc = khuVuc;
	}
	public Phong(String tenPhong) {
		// TODO Auto-generated constructor stub
		this.maPhong = maPhong;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public String getTenPhong() {
		return tenPhong;
	}
	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}
	public KhuVuc getKhuVuc() {
		return khuVuc;
	}
	public void setKhuVuc(KhuVuc khuVuc) {
		this.khuVuc = khuVuc;
	}
	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", tenPhong=" + tenPhong + ", khuVuc=" + khuVuc + "]";
	}
	
}
