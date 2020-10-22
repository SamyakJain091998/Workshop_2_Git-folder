package com.ipl_league_analysis.workshop;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import com.CSVExceptionJar.CSVException;
import static com.CSVExceptionJar.CSVException.CSVExceptionType.UNABLE_TO_PARSE;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;

public class OpenCSVBuilder<E> implements ICSVBuilder<E> {

	public OpenCSVBuilder() {
	}

	public Iterator<E> returnsIteratorToTheLoadingFunction(Reader reader, Class csvClass) throws CSVException {
		return this.getCSVBean(reader, csvClass).iterator();
	}

	@Override
	public List<E> returnsListToTheLoadingFunction(Reader reader, Class csvClass) throws CSVException {
		// TODO Auto-generated method stub
		try {
			HeaderColumnNameMappingStrategy<E> strategy = new HeaderColumnNameMappingStrategy<>();
			strategy.setType(csvClass);

			CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(reader).withMappingStrategy(strategy)
					.withIgnoreLeadingWhiteSpace(true).build();

			List<E> cars = csvToBean.parse();
			return cars;
		} catch (IllegalStateException e) {
			// TODO: handle exception
			throw new CSVException(e.getMessage(), CSVException.CSVExceptionType.UNABLE_TO_PARSE);
		}
	}

	private CsvToBean<E> getCSVBean(Reader reader, Class csvClass) throws CSVException {
		// TODO Auto-generated method stub
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			return csvToBeanBuilder.build();
		} catch (IllegalStateException e) {
			// TODO: handle exception
			throw new CSVException(e.getMessage(), CSVException.CSVExceptionType.UNABLE_TO_PARSE);
		}
	}
}
