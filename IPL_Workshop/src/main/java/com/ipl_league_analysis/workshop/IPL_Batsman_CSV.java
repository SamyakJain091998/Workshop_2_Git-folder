package com.ipl_league_analysis.workshop;

import com.opencsv.bean.CsvBindByName;

public class IPL_Batsman_CSV {

	@CsvBindByName(column = "PLAYER", required = true)
	public String player;

	@CsvBindByName(column = "Mat", required = true)
	public String mat;

	@CsvBindByName(column = "Inns", required = true)
	public String inns;

	@CsvBindByName(column = "NO", required = true)
	public String no;

	@CsvBindByName(column = "Runs", required = true)
	public String runs;

	@CsvBindByName(column = "HS", required = true)
	public String hs;

	@CsvBindByName(column = "Avg", required = true)
	public double avg;

	public double getAvg() {
		return avg;
	}

	@CsvBindByName(column = "BF", required = true)
	public String bf;

	@CsvBindByName(column = "SR", required = true)
	public String sr;

	@CsvBindByName(column = "100", required = true)
	public String hundreds;

	@CsvBindByName(column = "50", required = true)
	public String fifties;

	@CsvBindByName(column = "4s", required = true)
	public String fours;

	@CsvBindByName(column = "6s", required = true)
	public String sixes;

	@Override
	public String toString() {
		return "IPL_Batsman_CSV [player=" + player + ", mat=" + mat + ", inns=" + inns + ", no=" + no
				+ ", runs=" + runs + ", hs=" + hs + ", avg=" + avg + ", bf=" + bf + ", sr=" + sr + ", hundreds="
				+ hundreds + ", fifties=" + fifties + ", fours=" + fours + ", sixes=" + sixes + "]";
	}

}
