package com.ipl_league_analysis.workshop;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class IPLAnalyzerClass {
	private List<Double> topAverageList = new ArrayList<>();
	private List<Double> topStrikeRateList = new ArrayList<>();

	public int loadBatsmanData(String csvFilePath) {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {

			CsvToBeanBuilder<IPL_Batsman_CSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(IPL_Batsman_CSV.class);
			CsvToBean<IPL_Batsman_CSV> csvToBean = csvToBeanBuilder.build();
			final Iterator<IPL_Batsman_CSV> censusCsvIterator = csvToBean.iterator();
			Iterable<IPL_Batsman_CSV> csvIterable = () -> censusCsvIterator;
			int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).distinct().count();
			return numOfEntries;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Double> returnsTopBattingAverages(String csvFilePath) {
		Iterator<IPL_Batsman_CSV> csvIterator = returnsIteratorOfCSVFile(csvFilePath);
//		double MAX = -1;
		Double avgDouble;
		IPL_Batsman_CSV batsmanObj = null;
		String invalidString = "-";
		while (csvIterator.hasNext()) {
			batsmanObj = csvIterator.next();
			if (!(invalidString.equals(batsmanObj.getAvg()))) {
				avgDouble = Double.parseDouble(batsmanObj.getAvg());
				topAverageList.add(avgDouble);
			}
		}
		return returnsReverseSortedSublist(topAverageList);
	}

	public List<Double> returnsTopStrikeRates(String csvFilePath) {
		Iterator<IPL_Batsman_CSV> csvIterator = returnsIteratorOfCSVFile(csvFilePath);
//		double MAX = -1;
		Double strikeRateDouble;
		IPL_Batsman_CSV batsmanObj = null;
//		String invalidString = "-";
		while (csvIterator.hasNext()) {
			batsmanObj = csvIterator.next();
			strikeRateDouble = batsmanObj.getSr();
			topStrikeRateList.add(strikeRateDouble);
		}
		return returnsReverseSortedSublist(topStrikeRateList);
	}

	public List<Double> returnsReverseSortedSublist(List<Double> toppersList) {
		Collections.sort(toppersList, Collections.reverseOrder());
		toppersList = toppersList.subList(0, 5);
		return toppersList;
	}

	public Iterator<IPL_Batsman_CSV> returnsIteratorOfCSVFile(String csvFilePath) {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
			CsvToBean<IPL_Batsman_CSV> csvToBean = new CsvToBeanBuilder(reader).withType(IPL_Batsman_CSV.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			Iterator<IPL_Batsman_CSV> csvIterator = csvToBean.iterator();
			return csvIterator;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
