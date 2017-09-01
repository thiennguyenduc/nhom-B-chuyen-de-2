package com.example.giao_dien;

import java.io.Serializable;

public class DiaChiAll implements Serializable {
		private int id;
		private String Name;
		private String Mota;
		private String Hinh;
		
		public DiaChiAll(int id, String name, String mota,String hinh){
			this.id = id;
			Name = name;
			Hinh = hinh;
			Mota = mota;
			
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		public String getHinh() {
			return Hinh;
		}

		public void setHinh(String hinh) {
			Hinh = hinh;
		}

		public String getMota() {
			return Mota;
		}

		public void setMota(String mota) {
			Mota = mota;
		}
		
		
		
}
