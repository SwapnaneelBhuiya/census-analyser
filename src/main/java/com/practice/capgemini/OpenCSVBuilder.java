package com.practice.capgemini;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class OpenCSVBuilder<E> implements ICSVBuilder {

    public Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException {
        CsvToBean<E> csvToBean;
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            csvToBean = csvToBeanBuilder.build();
            //Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
            //Iterable<IndiaCensusCSV> csvIterable = () -> censusCSVIterator;
            return csvToBean.iterator();
        } catch (IllegalStateException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }
}
