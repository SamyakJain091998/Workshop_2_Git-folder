package com.ipl_league_analysis.workshop;

public class CSVBuilderFactory {

	public static ICSVBuilder createCSVBuilder() {
		// TODO Auto-generated method stub
		return new OpenCSVBuilder<>();
	}

}
