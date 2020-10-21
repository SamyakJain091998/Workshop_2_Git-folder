package com.ipl_league_analysis.workshop;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
	public void loadBatsmanData(String csvFilePath) throws Exception {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {

			CsvToBeanBuilder<IPL_Batsman_CSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(IPL_Batsman_CSV.class);
			CsvToBean<IPL_Batsman_CSV> csvToBean = csvToBeanBuilder.build();
			final Iterator<IPL_Batsman_CSV> censusCsvIterator = csvToBean.iterator();
			Iterable<IPL_Batsman_CSV> csvIterable = () -> censusCsvIterator;
//			int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).distinct().count();

			return;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
}
