package entity;

import java.util.Objects;

public class NhanVien {
    private String maNV, hoTenNV, Phai;
    private int tuoi;
    private String sdt;

    public NhanVien(String maNV, String hoTenNV, String phai, int tuoi, String sdt) {
        this.maNV = maNV;
        this.hoTenNV = hoTenNV;
        Phai = phai;
        this.tuoi = tuoi;
        this.sdt = sdt;
    }

    public NhanVien(String ma) {
		// TODO Auto-generated constructor stub
	}

    public NhanVien() {
		// TODO Auto-generated constructor stub
	}

	public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTenNV() {
        return hoTenNV;
    }

    public void setHoTenNV(String hoNV) {
        this.hoTenNV = hoTenNV;
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
	public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
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

    @Override
    public String toString() {
        return "NhanVien [maNV=" + maNV + ", hoTenNV=" + hoTenNV + ", Phai=" + Phai + ", tuoi=" + tuoi + ", sdt=" + sdt + "]";
    }

}