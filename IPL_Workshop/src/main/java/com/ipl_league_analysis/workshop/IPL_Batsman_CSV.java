package com.ipl_league_analysis.workshop;

import com.opencsv.bean.CsvBindByName;

public class IPL_Batsman_CSV {

	@CsvBindByName(column = "POS", required = true)
	public int pos;

	@CsvBindByName(column = "PLAYER", required = true)
	public String player;
//
//	@CsvBindByName(column = "Mat", required = true)
//	public int mat;
//
//	@CsvBindByName(column = "Inns", required = true)
//	public int inns;
//
//	@CsvBindByName(column = "NO", required = true)
//	public int no;
//
//	@CsvBindByName(column = "Runs", required = true)
//	public int runs;
//
//	@CsvBindByName(column = "HS", required = true)
//	public int hs;
//
	@CsvBindByName(column = "Avg", required = true)
	public String avg;
//
//	@CsvBindByName(column = "BF", required = true)
//	public int bf;
//
	@CsvBindByName(column = "SR", required = true)
	public double sr;
//
//	@CsvBindByName(column = "100", required = true)
//	public int hundreds;
//
//	@CsvBindByName(column = "50", required = true)
//	public int fifties;
//
	@CsvBindByName(column = "4s", required = true)
	public int fours;

	@CsvBindByName(column = "6s", required = true)
	public int sixes;

	public int getPos() {
		return pos;
	}

	public String getPlayer() {
		return player;
	}

//
//	public int getMat() {
//		return mat;
//	}
//
//	public int getInns() {
//		return inns;
//	}
//
//	public int getNo() {
//		return no;
//	}
//
//	public int getRuns() {
//		return runs;
//	}
//
//	public int getHs() {
//		return hs;
//	}
//
	public String getAvg() {
		return avg;
	}

//
//	public int getBf() {
//		return bf;
//	}
//
	public double getSr() {
		return sr;
	}

//
//	public int getHundreds() {
//		return hundreds;
//	}
//
//	public int getFifties() {
//		return fifties;
//	}
//
	public int getFours() {
		return fours;
	}

	public int getSixes() {
		return sixes;
	}
//
//	@Override
//	public String toString() {
//		return "IPL_Batsman_CSV [pos=" + pos + ", player=" + player + ", mat=" + mat + ", inns=" + inns + ", no=" + no
//				+ ", runs=" + runs + ", hs=" + hs + ", avg=" + avg + ", bf=" + bf + ", sr=" + sr + ", hundreds="
//				+ hundreds + ", fifties=" + fifties + ", fours=" + fours + ", sixes=" + sixes + "]";
//	}

}
