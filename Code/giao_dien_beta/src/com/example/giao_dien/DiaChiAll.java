package com.example.giao_dien;

public class DiaChiAll {
	private String tencuahang;
	private String diachi;
	private String sdt;
	private int flag;
	
	public DiaChiAll(String tencuahang, String diachi, String sdt, int flag) {
		this.tencuahang = tencuahang;
		this.diachi = diachi;
		this.sdt = sdt;
		this.flag = flag;
	}
	
	public String getTencuahang() {
		return this.tencuahang;
	}
	public String getDiachi() {
		return this.diachi;
	}
	public String getSdt() {
		return this.sdt;
	}
	public int getFlag() {
		return this.flag;
	}
	
}
