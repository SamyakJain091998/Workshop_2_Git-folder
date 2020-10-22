package com.ipl_league_analysis.workshop;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import com.CSVExceptionJar.CSVException;

public interface ICSVBuilder<E> {
	public Iterator<E> returnsIteratorToTheLoadingFunction(Reader reader, Class csvClass) throws CSVException;

	public List<E> returnsListToTheLoadingFunction(Reader reader, Class csvClass) throws CSVException;
}
