package com.androidbegin.filterlistviewtutorial;

import android.view.LayoutInflater;

public class WorldPopulation {
	private String rank;
	private String country;
	private String population;
	int flag;
	LayoutInflater inflater;
	
	public WorldPopulation(String rank, String country, String population, int flag) {
		this.rank = rank;
		this.country = country;
		this.population = population;
		this.flag = flag;
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
	public int getlag(){
		return this.flag;
	}
}
