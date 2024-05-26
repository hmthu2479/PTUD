package entity;

public class ChiTietHoaDon {

    private HoaDon HoaDon;
    private String mon;
    private int soLuong;
    private double donGia;
    private double thanhTien;
	public ChiTietHoaDon(entity.HoaDon hoaDon, String mon, int soLuong, double donGia, double thanhTien) {
		super();
		HoaDon = hoaDon;
		this.mon = mon;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.thanhTien = thanhTien;
	}
	public HoaDon getHoaDon() {
		return HoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		HoaDon = hoaDon;
	}
	public String getMon() {
		return mon;
	}
	public void setMon(String mon) {
		this.mon = mon;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public double getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}
	
	
}

