package com.ipl_league_analysis.workshop;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.opencsv.*;

public class SystemTestClass {
	private static final String IPL_BATTING_SHEET = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
	private static final String IPL_BOWLING_SHEET = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
	IPLAnalyzerClass IplAnalyzer = null;

	// path
	// Default Test
	@Test
	public void test() {
		Assert.assertEquals(true, true);
	}

	@Before
	public void initializesIPLAnalyzerClassObject() {
		IplAnalyzer = new IPLAnalyzerClass();
	}

	// Start
	@Ignore
	@Test
	public void given_Batsman_Data_CSV_File_Returns_Correct_Number_Of_Records() {
		try {
			int numberOfEntries = IplAnalyzer.loadBatsmanData(IPL_BATTING_SHEET);
			System.out.println(numberOfEntries);
			Assert.assertEquals(101, numberOfEntries);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void given_Batsman_Data_CSV_File_Returns_Top_Batting_Averages() {
		try {
			List<Double> battingAvgList = IplAnalyzer.returnsTopBattingAverages(IPL_BATTING_SHEET);
			List<Double> checkList = new ArrayList<>();
			checkList.add(83.2);
			checkList.add(69.2);
			checkList.add(56.66);
			checkList.add(55.62);
			checkList.add(53.9);

			Assert.assertEquals(checkList, battingAvgList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void given_Batsman_Data_CSV_File_Returns_Top_Strike_Rates() {
		try {
			List<Double> strikeRateList = IplAnalyzer.returnsTopStrikeRates(IPL_BATTING_SHEET);
			List<Double> checkList = new ArrayList<>();
			checkList.add(333.33);
			checkList.add(204.81);
			checkList.add(200.0);
			checkList.add(191.42);
			checkList.add(175.0);

			Assert.assertEquals(checkList, strikeRateList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void given_Batsman_Data_CSV_File_Returns_Top_Fours_And_Sixes_Hitter() {
		try {
			List<String> strikeRateList = IplAnalyzer.returnsTopBoundaryHitters(IPL_BATTING_SHEET);
			Assert.assertEquals("Shikhar Dhawan", strikeRateList.get(0));
			Assert.assertEquals("Andre Russell", strikeRateList.get(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
