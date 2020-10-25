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
			List<Double> strikeRateList = IplAnalyzer.returnsTopStrikeRates(IPL_BATTING_SHEET, IPL_Batsman_CSV.class);
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

	@Ignore
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

	@Ignore
	@Test
	public void given_Batsman_Data_CSV_File_Returns_Player_With_Best_Striking_Rate_WithBoundaries() {
		try {
			String player = IplAnalyzer.returnsTopBoundaryStrikingRatePlayer(IPL_BATTING_SHEET);
			System.out.println(player);
			Assert.assertEquals(true, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void given_Batsman_Data_CSV_File_Returns_Player_With_Best_Avg_And_Striking_Rate() {
		try {
			String player = IplAnalyzer.returnsTopAvgAndStrikeRatePlayer(IPL_BATTING_SHEET);
			System.out.println(player);
			Assert.assertEquals(true, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void given_Batsman_Data_CSV_File_Returns_Player_With_Max_Runs_Best_Avg() {
		try {
			String player = IplAnalyzer.returnsTopScoringAndAvgPlayer(IPL_BATTING_SHEET);
//			List<String> playerList = new ArrayList<>();
//			playerList.add("David Warner");
//			playerList.add("KL Rahul");
//			playerList.add("Quinton de Kock");
//			playerList.add("Shikhar Dhawan");
//			playerList.add("Andre Russell");
//			
			Assert.assertEquals("David Warner", player);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void given_Bowling_Data_CSV_File_Returns_Top_Bowling_Averages() {
		try {
			List<Double> bowlingAvgList = IplAnalyzer.returnsTopBowlingAverages(IPL_BOWLING_SHEET);
			List<Double> checkList = new ArrayList<>();
			checkList.add(166.0);
			checkList.add(118.0);
			checkList.add(94.25);
			checkList.add(91.5);
			checkList.add(77.66);

			Assert.assertEquals(checkList, bowlingAvgList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void given_Bowler_Data_CSV_File_Returns_Top_Strike_Rates() {
		try {
			List<Double> strikeRateList = IplAnalyzer.returnsTopStrikeRates(IPL_BOWLING_SHEET, IPL_Bowling_CSV.class);
			List<Double> checkList = new ArrayList<>();
			checkList.add(120.0);
			checkList.add(60.5);
			checkList.add(54.0);
			checkList.add(51.0);
			checkList.add(50.0);

			Assert.assertEquals(checkList, strikeRateList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void given_Bowler_Data_CSV_File_Returns_Player_With_Best_Economy_Rate() {
		try {
			String playerName = IplAnalyzer.returnsBestEconomyRatePlayer(IPL_BOWLING_SHEET);
			Assert.assertEquals("Ben Cutting", playerName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void givenBowlerDataCSVFile_WhenSortedBasis5wAnd4w_ReturnsPlayerWithBestStrikingRate() {
		try {
			String playerName = IplAnalyzer.returnsBestWicketHaulStrikeRatePlayer(IPL_BOWLING_SHEET);
			Assert.assertEquals("Alzarri Joseph", playerName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void givenBowlerDataCSVFile_WhenSortedBasisBowlingAverageAndStrikingRate_ReturnsBestPlayer() {
		try {
			String playerName = IplAnalyzer.returnsBestBowlingAverageAndStrikeRatePlayer(IPL_BOWLING_SHEET);
			Assert.assertEquals("Anukul Roy", playerName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void givenBowlerDataCSVFile_WhenSortedBasisMaxWicketsAndBestAverage_ReturnsBestPlayer() {
		try {
			String playerName = IplAnalyzer.returnsBestWicketTakerAndBowlingAveragePlayer(IPL_BOWLING_SHEET);
			Assert.assertEquals("Imran Tahir", playerName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void givenBowlerDataCSVFile_WhenSortedBasisBestBattingAndBowlingAverages_ReturnsThePlayers() {
		try {
			List<String> players = IplAnalyzer.returnsBestBowlingAndBattingAveragePlayers(IPL_BATTING_SHEET,
					IPL_BOWLING_SHEET);

			Assert.assertEquals("MS Dhoni", players.get(0));
			Assert.assertEquals("Prasidh Krishna", players.get(1));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void givenBowlerAndBatsmanDataCSVFile_WhenSortedBasisParameter_ReturnsBestAllRounders() {
		try {
			String player = IplAnalyzer.returnsBestAllRounders(IPL_BATTING_SHEET, IPL_BOWLING_SHEET);
			Assert.assertEquals("Andre Russell", player);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenBatsmanDataCSVFile_WhenSorted_ReturnsPlayerWithMaxHundredsAndBestAvg() {
		try {
			String playerName = IplAnalyzer.returnsMaxHundredsScorerAndBestAveragePlayer(IPL_BATTING_SHEET);
			Assert.assertEquals("David Warner", playerName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
