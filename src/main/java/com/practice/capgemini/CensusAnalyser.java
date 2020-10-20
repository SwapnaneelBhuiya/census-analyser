package com.practice.capgemini;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
        public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
            CsvToBean<IndiaCensusCSV> csvToBean;
            try (Reader reader= Files.newBufferedReader(Paths.get(csvFilePath));){
                Iterator<IndiaCensusCSV> censusCSVIterator = new OpenCSVBuilder().getCSVFileIterator(reader,  IndiaCensusCSV.class);
                return getCount(censusCSVIterator);
            }
            catch(IllegalStateException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
            }catch (IOException|RuntimeException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            }
        }
        public int loadIndianStateCode(String csvFilePath) throws CensusAnalyserException {
            CsvToBean<IndiaStateCodeCSV> csvToBean;
            try (Reader reader= Files.newBufferedReader(Paths.get(csvFilePath));){
                Iterator<IndiaStateCodeCSV> censusCSVIterator = new OpenCSVBuilder().getCSVFileIterator(reader,  IndiaStateCodeCSV.class);
                return getCount(censusCSVIterator);
            }
            catch(IllegalStateException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
            }
            catch (IOException|RuntimeException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            }
        }

    private <E> int getCount(Iterator<E> iterator)
    {
        Iterable csvIterable=()-> iterator;
        int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(),false).count();
        return numOfEntries;
    }
}