package entity;

import java.util.ArrayList;
import java.util.Objects;

public class NhanVien {
    private String maNV, hoNV, Phai;
    private int tuoi;
    private String sdt;
    private ArrayList<NhanVien> dsNV;

    public NhanVien() {
        dsNV = new ArrayList<NhanVien>();
    }

    public NhanVien(String maNV, String hoNV, String phai, int tuoi, String sdt) {
        this.maNV = maNV;
        this.hoNV = hoNV;
        Phai = phai;
        this.tuoi = tuoi;
        this.sdt = sdt;
        dsNV = new ArrayList<NhanVien>();
    }

    public NhanVien(String ma) {
		// TODO Auto-generated constructor stub
	}

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
        return "NhanVien [maNV=" + maNV + ", hoNV=" + hoNV + ", Phai=" + Phai + ", tuoi=" + tuoi + ", sdt=" + sdt + "]";
    }

    public boolean themNV(NhanVien nv) {
        if (dsNV.contains(nv))
            return false;
        dsNV.add(nv);
        return true;
    }

    public boolean xoaNV(String ma) {
        NhanVien nv = new NhanVien(ma);
        if (dsNV.contains(nv)) {
            dsNV.remove(nv);
            return true;
        }
        return false;
    }

    public NhanVien tim(String ma) {
        NhanVien nv = new NhanVien(ma);
        if (dsNV.contains(nv))
            return dsNV.get(dsNV.indexOf(nv));
        return null;
    }

    public ArrayList<NhanVien> getDsNV() {
        return dsNV;
    }

    public void setDsNV(ArrayList<NhanVien> dsNV) {
        this.dsNV = dsNV;
    }

    public NhanVien getElement(int index) {
        if (index < 0 || index >= dsNV.size())
            return null;
        return dsNV.get(index);
    }

    public int getSize() {
        return dsNV.size();
    }

    public static void main(String[] args) {

    }
}