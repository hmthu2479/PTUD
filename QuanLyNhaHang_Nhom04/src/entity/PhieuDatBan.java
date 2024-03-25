package entity;

import java.time.LocalDate;
import java.util.Objects;

public class PhieuDatBan {
	private String maPhieu, khuVuc, soBan, ngayThang,gioDat,hoTen,sdt,diaChi;
	private int soLuongNguoi;
	private LocalDate ngayLap; 

	public PhieuDatBan(String maPhieu, String khuVuc, String soBan, int soLuongNguoi, String ngayThang, LocalDate ngayLap, String gioDat, String hoTen,
			String sdt, String diaChi) {
		super();
		this.maPhieu = maPhieu;
		this.khuVuc = khuVuc;
		this.soBan = soBan;
		this.ngayThang = ngayThang;
		this.ngayLap = ngayLap;
		this.gioDat = gioDat;
		this.hoTen = hoTen;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.soLuongNguoi = soLuongNguoi;
	}
	public PhieuDatBan(String ma) {
		// TODO Auto-generated constructor stub
	}
	public String getMaPhieu() {
		return maPhieu;
	}
	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
	}
	public String getSoBan() {
		return soBan;
	}
	public void setSoBan(String soBan) {
		this.soBan = soBan;
	}
	public String getKhuVuc() {
		return khuVuc;
	}
	public void setKhuVuc(String khuVuc) {
		this.khuVuc = khuVuc;
	}

	public LocalDate getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}
	public String getGioDat() {
		return gioDat;
	}
	public void setGioDat(String gioDat) {
		this.gioDat = gioDat;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public int getSoLuongNguoi() {
		return soLuongNguoi;
	}
	public void setSoLuongNguoi(int soLuongNguoi) {
		this.soLuongNguoi = soLuongNguoi;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maPhieu);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhieuDatBan other = (PhieuDatBan) obj;
		return Objects.equals(maPhieu, other.maPhieu);
	}

	public String getNgayThang() {
		return ngayThang;
	}
	public void setNgayThang(String ngayThang) {
		this.ngayThang = ngayThang;
	}
	@Override
	public String toString() {
		return "PhieuDatBan [maPhieu=" + maPhieu + ", khuVuc=" + khuVuc + ", soBan=" + soBan + ", ngayThang="
				+ ngayThang + ", ngayLap=" + ngayLap + ", gioDat=" + gioDat + ", hoTen=" + hoTen + ", sdt=" + sdt
				+ ", diaChi=" + diaChi + ", soLuongNguoi=" + soLuongNguoi + "]";
	}
	
}
