package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class PhieuDatBan {
    private String maPhieu, khuVuc, soBan, ngayDat, gioDat;
    private int soLuongNguoi;
    private LocalDate ngayLap;
    private KhachHang khachHang;
    private NhanVien nhanVien;
	public PhieuDatBan(String maPhieu, String khuVuc, String soBan, int soLuongNguoi, String ngayDat, LocalDate ngayLap,
			String gioDat, KhachHang khachHang, NhanVien nhanVien) {
		super();
		this.maPhieu = maPhieu;
		this.khuVuc = khuVuc;
		this.soBan = soBan;
		this.soLuongNguoi = soLuongNguoi;
		this.ngayDat = ngayDat;
		this.ngayLap = ngayLap;
		this.gioDat = gioDat;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
	}
	public String getMaPhieu() {
		return maPhieu;
	}
	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
	}
	public String getKhuVuc() {
		return khuVuc;
	}
	public void setKhuVuc(String khuVuc) {
		this.khuVuc = khuVuc;
	}
	public String getSoBan() {
		return soBan;
	}
	public void setSoBan(String soBan) {
		this.soBan = soBan;
	}
	public String getNgayDat() {
		return ngayDat;
	}
	public void setNgayDat(String ngayDat) {
		this.ngayDat = ngayDat;
	}
	public String getGioDat() {
		return gioDat;
	}
	public void setGioDat(String gioDat) {
		this.gioDat = gioDat;
	}
	public int getSoLuongNguoi() {
		return soLuongNguoi;
	}
	public void setSoLuongNguoi(int soLuongNguoi) {
		this.soLuongNguoi = soLuongNguoi;
	}
	public LocalDate getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	@Override
	public String toString() {
		return "PhieuDatBan [maPhieu=" + maPhieu + ", khuVuc=" + khuVuc + ", soBan=" + soBan + ", ngayDat=" + ngayDat
				+ ", gioDat=" + gioDat + ", soLuongNguoi=" + soLuongNguoi + ", ngayLap=" + ngayLap + ", khachHang="
				+ khachHang + ", nhanVien=" + nhanVien + "]";
	}
    
    
    }


    