package com.ipl_league_analysis.workshop;

import com.opencsv.bean.CsvBindByName;

public class IPL_Bowling_CSV {

	@CsvBindByName(column = "POS", required = true)
	public int pos;

	@CsvBindByName(column = "PLAYER", required = true)
	public String player;

//	@CsvBindByName(column = "Mat", required = true)
//	public int mat;
//
//	@CsvBindByName(column = "Inns", required = true)
//	public int inns;
//
//	@CsvBindByName(column = "Ov", required = true)
//	public double ov;
//
//	@CsvBindByName(column = "Runs", required = true)
//	public int runs;
//
	@CsvBindByName(column = "Wkts", required = true)
	public int wkts;
//
//	@CsvBindByName(column = "BBI", required = true)
//	public int bbi;

	@CsvBindByName(column = "Avg", required = true)
	public String avg;

	public int getPos() {
		return pos;
	}

	public int getWickets() {
		return wkts;
	}

	public String getPlayer() {
		return player;
	}

	public String getAvg() {
		if (!("-".equals(avg))) {
			return avg;
		}
		return "1000";
	}

	@CsvBindByName(column = "Econ", required = true)
	public double econ;

	@CsvBindByName(column = "SR", required = true)
	public String sr;

	public String getSr() {
		return sr;
	}

	public double getEcon() {
		return econ;
	}

	@CsvBindByName(column = "4w", required = true)
	public int fourWicketHaul;

	@CsvBindByName(column = "5w", required = true)
	public int fiveWicketHaul;

	public double FiveWicketHaulAndFourWicketHaulStrikingRate() {
		double strikeRate = fourWicketHaul * 4 + fiveWicketHaul * 5;
		if (wkts == 0) {
			return 0;
		}
		if (strikeRate != 0) {
			strikeRate = strikeRate / wkts;
			return strikeRate;
		}
		return 0;
	}
//	@Override
//	public String toString() {
//		return "IPL_Bowling_CSV [player=" + player + ", mat=" + mat + ", inns=" + inns + ", ov=" + ov + ", runs=" + runs
//				+ ", wkts=" + wkts + ", bbi=" + bbi + ", avg=" + avg + ", econ=" + econ + ", sr=" + sr
//				+ ", fourWicketHaul=" + fourWicketHaul + ", sixWicketHaul=" + sixWicketHaul + "]";
//	}

}
