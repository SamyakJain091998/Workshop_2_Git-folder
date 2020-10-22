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
import static com.CSVExceptionJar.CSVException.CSVExceptionType.UNABLE_TO_PARSE;

import com.CSVExceptionJar.CSVException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class IPLAnalyzerClass {
	private List<Double> topAverageList = new ArrayList<>();
	private List<Double> topStrikeRateList = new ArrayList<>();
	List<IPL_Batsman_CSV> IPLBatsmanList = null;

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

	public List<String> returnsTopBoundaryHitters(String csvFilePath) {
		// TODO Auto-generated method stub
		Iterator<IPL_Batsman_CSV> csvIterator = returnsIteratorOfCSVFile(csvFilePath);
		IPL_Batsman_CSV batsmanObj = null;
		int maxFours = -1;
		int maxSixes = -1;
		String playerNameWithMostFours = null;
		String playerNameWithMostSixes = null;
		List<String> playerList = new ArrayList<>();

		while (csvIterator.hasNext()) {
			batsmanObj = csvIterator.next();
			if (batsmanObj.getFours() > maxFours) {
				maxFours = batsmanObj.getFours();
				playerNameWithMostFours = batsmanObj.getPlayer();
			}
			if (batsmanObj.getSixes() > maxSixes) {
				maxSixes = batsmanObj.getSixes();
				playerNameWithMostSixes = batsmanObj.getPlayer();
			}
		}
		playerList.add(playerNameWithMostFours);
		playerList.add(playerNameWithMostSixes);
		return playerList;
	}

	public String returnsTopBoundaryStrikingRatePlayer(String csvFilePath) {
		Iterator<IPL_Batsman_CSV> csvIterator = returnsIteratorOfCSVFile(csvFilePath);
		// TODO Auto-generated method stub
		IPL_Batsman_CSV batsmanObj = null;
		double maxConditionalStrikerate = 0.0;
		String player = null;
		while (csvIterator.hasNext()) {
			batsmanObj = csvIterator.next();
			double localConditionalStrikeRate = 4 * batsmanObj.getFours() + 6 * batsmanObj.getSixes();
			localConditionalStrikeRate = localConditionalStrikeRate / batsmanObj.getBf();
			if (localConditionalStrikeRate > maxConditionalStrikerate) {
				maxConditionalStrikerate = localConditionalStrikeRate;
				player = batsmanObj.getPlayer();
			}
		}
		return player;
	}

	public String returnsTopAvgAndStrikeRatePlayer(String csvFilePath) {
		Iterator<IPL_Batsman_CSV> csvIterator = returnsIteratorOfCSVFile(csvFilePath);
		IPL_Batsman_CSV batsmanObj = null;
		String invalidInput = "-";
		double maxAvgAndStrikeRate = -1;
		String playerName = null;
		while (csvIterator.hasNext()) {
			batsmanObj = csvIterator.next();
			if (invalidInput.equals(batsmanObj.getAvg())) {
				continue;
			}
			double localAvg = Double.parseDouble(batsmanObj.getAvg());
			localAvg += batsmanObj.getSr();
			localAvg /= 2;
			if (localAvg > maxAvgAndStrikeRate) {
				maxAvgAndStrikeRate = localAvg;
				playerName = batsmanObj.getPlayer();
			}
		}
		return playerName;
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

	public String returnsTopScoringAndAvgPlayer(String csvFilePath) throws CSVException, IPLException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			IPLBatsmanList = csvBuilder.returnsListToTheLoadingFunction(reader, IPL_Batsman_CSV.class);

			Comparator<IPL_Batsman_CSV> iplComparator = Comparator.comparing(IPL_Batsman_CSV::getRuns)
					.thenComparing(IPL_Batsman_CSV::getAvg);

			return getSortedDataBasisParameter(iplComparator, IPLBatsmanList, IPL_Batsman_CSV.class);
		} catch (IOException e) {
			// TODO: handle exception
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_PROBLEM);
		} catch (RuntimeException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
		// TODO Auto-generated method stub
	}

	private <E> String getSortedDataBasisParameter(Comparator<E> iplComparator, List<E> processedList,
			Class<E> classType) throws IPLException {
		// TODO Auto-generated method stub
		if (processedList == null || processedList.size() == 0) {
			throw new IPLException("No Census Data", IPLException.ExceptionType.NO_CENSUS_DATA);
		}
		this.sortList(iplComparator);

		return IPLBatsmanList.get(0).getPlayer();
	}

	private <E> void sortList(Comparator<E> iplComparator) {
		// TODO Auto-generated method stub
		for (int i = 0; i < IPLBatsmanList.size() - 1; i++) {
			for (int j = 0; j < IPLBatsmanList.size() - i - 1; j++) {
				E iplParameter1 = (E) IPLBatsmanList.get(j);
				E iplParameter2 = (E) IPLBatsmanList.get(j + 1);
				if (iplComparator.compare(iplParameter1, iplParameter2) < 0) {
					IPLBatsmanList.set(j, (IPL_Batsman_CSV) iplParameter2);
					IPLBatsmanList.set(j + 1, (IPL_Batsman_CSV) iplParameter1);
				}
			}
		}
		// TODO Auto-generated method stub
	}

}
