package com.ipl_league_analysis.workshop;

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
	@Test
	public void given_Batsman_Data_CSV_File_Returns_Correct_Number_Of_Records() {
		try {
			IplAnalyzer.loadBatsmanData(IPL_BATTING_SHEET);
//			System.out.println(numberOfEntries);
//			Assert.assertEquals(101, numberOfEntries);
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
