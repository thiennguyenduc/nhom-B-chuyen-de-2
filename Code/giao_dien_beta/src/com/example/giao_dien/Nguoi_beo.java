package com.example.giao_dien;

import java.io.Serializable;

public class Nguoi_beo implements Serializable {
		private int Product_id;
		private String Thumb;
		private String Name;
		private String Description;
		
		
		public Nguoi_beo(int product_id, String thumb, String name,
				String description) {
			super();
			Product_id = product_id;
			Thumb = thumb;
			Name = name;
			Description = description;
		}


		public Nguoi_beo() {
			super();
		}


		public int getProduct_id() {
			return Product_id;
		}


		public void setProduct_id(int product_id) {
			Product_id = product_id;
		}


		public String getThumb() {
			return Thumb;
		}


		public void setThumb(String thumb) {
			Thumb = thumb;
		}


		public String getName() {
			return Name;
		}


		public void setName(String name) {
			Name = name;
		}


		public String getDescription() {
			return Description;
		}


		public void setDescription(String description) {
			Description = description;
		}


		@Override
		public String toString() {
			return "Nguoi_beo [Product_id=" + Product_id + ", Thumb=" + Thumb
					+ ", Name=" + Name + ", Description=" + Description + "]";
		}
		
		
		
		
		
}
