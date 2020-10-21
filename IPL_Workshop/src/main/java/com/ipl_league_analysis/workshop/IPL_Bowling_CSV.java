package com.ipl_league_analysis.workshop;

import com.opencsv.bean.CsvBindByName;

public class IPL_Bowling_CSV {
	@CsvBindByName(column = "PLAYER", required = true)
	public String player;

	@CsvBindByName(column = "Mat", required = true)
	public int mat;

	@CsvBindByName(column = "Inns", required = true)
	public int inns;

	@CsvBindByName(column = "Ov", required = true)
	public double ov;

	@CsvBindByName(column = "Runs", required = true)
	public int runs;

	@CsvBindByName(column = "Wkts", required = true)
	public int wkts;

	@CsvBindByName(column = "BBI", required = true)
	public int bbi;

	@CsvBindByName(column = "Avg", required = true)
	public double avg;

	@CsvBindByName(column = "Econ", required = true)
	public double econ;

	@CsvBindByName(column = "SR", required = true)
	public double sr;

	@CsvBindByName(column = "4w", required = true)
	public int fourWicketHaul;

	@CsvBindByName(column = "6w", required = true)
	public int sixWicketHaul;

	@Override
	public String toString() {
		return "IPL_Bowling_CSV [player=" + player + ", mat=" + mat + ", inns=" + inns + ", ov=" + ov + ", runs=" + runs
				+ ", wkts=" + wkts + ", bbi=" + bbi + ", avg=" + avg + ", econ=" + econ + ", sr=" + sr
				+ ", fourWicketHaul=" + fourWicketHaul + ", sixWicketHaul=" + sixWicketHaul + "]";
	}
}
