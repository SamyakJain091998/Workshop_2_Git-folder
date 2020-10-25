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
	private List<Double> topAverageList = null;
	private List<Double> topStrikeRateList = null;
	List<IPL_Batsman_CSV> IPLBatsmanList = null;
	List<IPL_Bowling_CSV> IPLBowlerList = null;

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
		topAverageList = new ArrayList<>();
		Iterator<IPL_Batsman_CSV> csvIterator = returnsIteratorOfCSVFile(csvFilePath, IPL_Batsman_CSV.class);
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

	public List<Double> returnsTopBowlingAverages(String csvFilePath) {
		topAverageList = new ArrayList<>();
		Iterator<IPL_Bowling_CSV> csvIterator = returnsIteratorOfCSVFile(csvFilePath, IPL_Bowling_CSV.class);
//		double MAX = -1;
		Double avgDouble;
		IPL_Bowling_CSV bowlerObj = null;
		String invalidString = "-";
		while (csvIterator.hasNext()) {
			bowlerObj = csvIterator.next();
			if (!(invalidString.equals(bowlerObj.getAvg()))) {
				avgDouble = Double.parseDouble(bowlerObj.getAvg());
				topAverageList.add(avgDouble);
			}
		}
		return returnsReverseSortedSublist(topAverageList);
	}

	public <E> List<Double> returnsTopStrikeRates(String csvFilePath, Class classType) {
		topStrikeRateList = new ArrayList<>();
		Iterator<E> csvIterator = returnsIteratorOfCSVFile(csvFilePath, classType);
//		double MAX = -1;
		Double avgDouble;
		E playerObj = null;
		String invalidString = "-";
		if (classType.equals(IPL_Batsman_CSV.class)) {
			while (csvIterator.hasNext()) {
				playerObj = csvIterator.next();
				if (!(invalidString.equals(((IPL_Batsman_CSV) playerObj).getSr()))) {
					avgDouble = Double.parseDouble(((IPL_Batsman_CSV) playerObj).getSr());
					topStrikeRateList.add(avgDouble);
				}
			}
		} else {
			while (csvIterator.hasNext()) {
				playerObj = csvIterator.next();
				if (!(invalidString.equals(((IPL_Bowling_CSV) playerObj).getSr()))) {
					avgDouble = Double.parseDouble(((IPL_Bowling_CSV) playerObj).getSr());
					topStrikeRateList.add(avgDouble);
				}
			}
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
		Iterator<IPL_Batsman_CSV> csvIterator = returnsIteratorOfCSVFile(csvFilePath, IPL_Batsman_CSV.class);
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
		Iterator<IPL_Batsman_CSV> csvIterator = returnsIteratorOfCSVFile(csvFilePath, IPL_Batsman_CSV.class);
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
		Iterator<IPL_Batsman_CSV> csvIterator = returnsIteratorOfCSVFile(csvFilePath, IPL_Batsman_CSV.class);
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
			double sr = Double.parseDouble(batsmanObj.getSr());

			localAvg += sr;
			localAvg /= 2;
			if (localAvg > maxAvgAndStrikeRate) {
				maxAvgAndStrikeRate = localAvg;
				playerName = batsmanObj.getPlayer();
			}
		}
		return playerName;
	}

	public <E> Iterator<E> returnsIteratorOfCSVFile(String csvFilePath, Class classType) {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
			CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader).withType(classType).withIgnoreLeadingWhiteSpace(true)
					.build();
			Iterator<E> csvIterator = csvToBean.iterator();
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

	public String returnsBestBattingEconomyPlayer(String csvFilePath) throws CSVException, IPLException {
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

	public String returnsBestEconomyRatePlayer(String csvFilePath) throws CSVException, IPLException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			IPLBowlerList = csvBuilder.returnsListToTheLoadingFunction(reader, IPL_Bowling_CSV.class);

			Comparator<IPL_Bowling_CSV> iplComparator = Comparator.comparing(IPL_Bowling_CSV::getEcon);

			return getSortedDataBasisParameter(iplComparator, IPLBowlerList, IPL_Bowling_CSV.class);
		} catch (IOException e) {
			// TODO: handle exception
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_PROBLEM);
		} catch (RuntimeException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
	}

	public String returnsBestWicketHaulStrikeRatePlayer(String csvFilePath) throws CSVException, IPLException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			IPLBowlerList = csvBuilder.returnsListToTheLoadingFunction(reader, IPL_Bowling_CSV.class);

			Comparator<IPL_Bowling_CSV> iplComparator = Comparator
					.comparing(IPL_Bowling_CSV::FiveWicketHaulAndFourWicketHaulStrikingRate);

			return getSortedDataBasisParameter(iplComparator, IPLBowlerList, IPL_Bowling_CSV.class);
		} catch (IOException e) {
			// TODO: handle exception
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_PROBLEM);
		} catch (RuntimeException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
	}

	public String returnsBestBowlingAverageAndStrikeRatePlayer(String csvFilePath) throws CSVException, IPLException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			IPLBowlerList = csvBuilder.returnsListToTheLoadingFunction(reader, IPL_Bowling_CSV.class);

			Comparator<IPL_Bowling_CSV> iplComparator = Comparator.comparing(IPL_Bowling_CSV::getAvg)
					.thenComparing(IPL_Bowling_CSV::getSr);
//					.thenComparing(IPL_Bowling_CSV::getSr);

			return getSortedDataBasisParameter(iplComparator, IPLBowlerList, IPL_Bowling_CSV.class, "hello");
		} catch (IOException e) {
			// TODO: handle exception
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_PROBLEM);
		} catch (RuntimeException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
		// TODO Auto-generated method stub
	}

	public String returnsBestWicketTakerAndBowlingAveragePlayer(String csvFilePath) throws CSVException, IPLException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			IPLBowlerList = csvBuilder.returnsListToTheLoadingFunction(reader, IPL_Bowling_CSV.class);

			Comparator<IPL_Bowling_CSV> iplComparator = Comparator.comparing(IPL_Bowling_CSV::getWickets);

			return getSortedDataBasisParameter(iplComparator, IPLBowlerList, IPL_Bowling_CSV.class);
		} catch (IOException e) {
			// TODO: handle exception
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_PROBLEM);
		} catch (RuntimeException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
	}

	private <E> String getSortedDataBasisParameter(Comparator<E> iplComparator, List<E> processedList,
			Class<E> classType) throws IPLException {
		// TODO Auto-generated method stub
		if (processedList == null || processedList.size() == 0) {
			throw new IPLException("No Census Data", IPLException.ExceptionType.NO_CENSUS_DATA);
		}

		this.sortList(iplComparator, processedList);

		if (classType.equals(IPL_Batsman_CSV.class)) {
			return IPLBatsmanList.get(0).getPlayer();
		} else {
			return IPLBowlerList.get(0).getPlayer();
		}
	}

	private <E> String getSortedDataBasisParameter(Comparator<E> iplComparator, List<E> processedList,
			Class<E> classType, String hello) throws IPLException {
		// TODO Auto-generated method stub
		if (processedList == null || processedList.size() == 0) {
			System.out.println("here");
			throw new IPLException("No Census Data", IPLException.ExceptionType.NO_CENSUS_DATA);
		}

		this.sortList(iplComparator, processedList);

		if (classType.equals(IPL_Batsman_CSV.class)) {
			return IPLBatsmanList.get(0).getPlayer();
		} else {
			for (int i = IPLBowlerList.size() - 1; i >= 0; i--) {
				if (!("1000".equals(IPLBowlerList.get(i).getAvg()))) {
					return IPLBowlerList.get(i).getPlayer();
				}
			}
		}
		return null;
	}

	private <E> void sortList(Comparator<E> iplComparator, List<E> givenList) {
		// TODO Auto-generated method stub
		for (int i = 0; i < givenList.size() - 1; i++) {
			for (int j = 0; j < givenList.size() - i - 1; j++) {
				E iplParameter1 = (E) givenList.get(j);
				E iplParameter2 = (E) givenList.get(j + 1);
				if (iplComparator.compare(iplParameter1, iplParameter2) < 0) {
					givenList.set(j, (E) iplParameter2);
					givenList.set(j + 1, (E) iplParameter1);
				}
			}

		}
		// TODO Auto-generated method stub
	}
}
