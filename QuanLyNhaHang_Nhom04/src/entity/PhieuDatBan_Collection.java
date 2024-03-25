package entity;

import java.util.ArrayList;

public class PhieuDatBan_Collection {
	private ArrayList<PhieuDatBan> dsPhieu;

	public PhieuDatBan_Collection() {
		dsPhieu = new ArrayList<PhieuDatBan> ();
	}
	public boolean themPhieu(PhieuDatBan phieu) {
		if(dsPhieu.contains(phieu))
			return false;
		dsPhieu.add(phieu);
		return true;
	}
	public boolean xoaPhieu(String ma) {
	    return dsPhieu.removeIf(phieu -> phieu.getMaPhieu().equals(ma));
	}
	
	public PhieuDatBan timPhieu(String ma) {
	    for (PhieuDatBan phieu : dsPhieu) {
	        if (phieu.getMaPhieu().equals(ma)) {
	            return phieu;
	        }
	    }
	    return null;
	}
	public ArrayList<PhieuDatBan> getDsPhieu() {
		return dsPhieu;
	}
	public void setDsPhieu(ArrayList<PhieuDatBan> dsPhieu) {
		this.dsPhieu = dsPhieu;
	}
	public PhieuDatBan getElement(int index) {
		if(index<0 || index>=dsPhieu.size())
			return null;
		return dsPhieu.get(index);
	}
	
	public int getSize() {
		return dsPhieu.size();
	}

	
}
