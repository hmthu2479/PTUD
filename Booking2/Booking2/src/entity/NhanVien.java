package entity;

import java.util.Objects;

public class NhanVien {
	public String maNV, hoNV, Phai;
	public int tuoi;
	public double tienLuong;
	
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getHoNV() {
		return hoNV;
	}
	public void setHoNV(String hoNV) {
		this.hoNV = hoNV;
	}
	public String getPhai() {
		return Phai;
	}
	public void setPhai(String phai) {
		Phai = phai;
	}
	public int getTuoi() {
		return tuoi;
	}
	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}
	public double getTienLuong() {
		return tienLuong;
	}
	public void setTienLuong(double tienLuong) {
		this.tienLuong = tienLuong;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}
	public NhanVien(String maNV, String hoNV, String phai, int tuoi, double tienLuong) {
		super();
		this.maNV = maNV;
		this.hoNV = hoNV;
		Phai = phai;
		this.tuoi = tuoi;
		this.tienLuong = tienLuong;
	}
	public NhanVien(String maNV) {
		
	}
	
	public NhanVien() {
		
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoNV=" + hoNV  + ", Phai=" + Phai + ", tuoi=" + tuoi
				+ ", tienLuong=" + tienLuong + "]";
	}
	public static void main(String[] args) {
		
	}
	
	
}
