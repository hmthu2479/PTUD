package entity;

import java.util.Objects;

public class Mon {
	private String maMon;
	private String tenMon;
	private double donGia;
	private String loaiMon;
	private int soLuong;
	private KhuVuc khuVuc;  // Sử dụng đối tượng KhuVuc thay vì String
	private Ban ban;      // Sử dụng đối tượng Ban thay vì String
	private Phong phong; // Sử dụng đối tượng Phong thay vì String
	private double tongTien = soLuong*donGia;
	
	
	
	public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
        tinhTongTien(); // Cập nhật tổng tiền khi donGia thay đổi
    }

    public String getLoaiMon() {
        return loaiMon;
    }

    public void setLoaiMon(String loaiMon) {
        this.loaiMon = loaiMon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
        tinhTongTien(); // Cập nhật tổng tiền khi soLuong thay đổi
    }

    public KhuVuc getKhuVuc() {
        return khuVuc;
    }

    public void setKhuVuc(KhuVuc khuVuc) {
        this.khuVuc = khuVuc;
    }

    public Ban getBan() {
        return ban;
    }

    public void setBan(Ban ban) {
        this.ban = ban;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public double getTongTien() {
        return this.tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    
    
    public void tinhTongTien() {
        this.tongTien = this.soLuong * this.donGia;
    }
	public Mon(KhuVuc khuVuc, Phong phong,Ban ban, String maMon, String tenMon,String loaiMon, double donGia, int soLuong, double tongTien) {
		super();
		this.maMon = maMon;
		this.tenMon = tenMon;
		this.donGia = donGia;
		this.loaiMon = loaiMon;
		this.soLuong = soLuong;
		this.khuVuc = khuVuc;
		this.ban = ban;
		this.phong = phong;
		this.tongTien = tongTien;
	}

	
	public Mon(String ma) {
		// TODO Auto-generated constructor stub
		this.maMon = ma;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maMon);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mon other = (Mon) obj;
		return Objects.equals(maMon, other.maMon);
	}

	@Override
	public String toString() {
		return "Mon [maMon=" + maMon + ", tenMon=" + tenMon + ", donGia=" + donGia + ", loaiMon=" + loaiMon
				+ ", soLuong=" + soLuong + ", khuVuc=" + khuVuc + ", ban=" + ban + ", phong=" + phong + ", tongTien="
				+ tongTien + "]";
	}


}
