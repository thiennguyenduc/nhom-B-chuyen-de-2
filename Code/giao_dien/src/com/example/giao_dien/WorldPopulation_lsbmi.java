package com.example.giao_dien;

import android.view.LayoutInflater;

public class WorldPopulation_lsbmi {
	private String rank;
	private String country;
	private String population;
	LayoutInflater inflater;
	
	public WorldPopulation_lsbmi(String rank, String country, String population) {
		this.rank = rank;
		this.country = country;
		this.population = population;
	}

	public String getRank() {
		return this.rank;
	}

	public String getCountry() {
		return this.country;
	}

	public String getPopulation() {
		return this.population;
	}
}
