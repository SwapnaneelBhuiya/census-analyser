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
            CsvToBean<IndiaCensusCSV> csvToBean;Reader reader =null;
            try {
                reader = Files.newBufferedReader(Paths.get(csvFilePath));
                CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
                csvToBeanBuilder.withType(IndiaCensusCSV.class);
                csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
                csvToBean = csvToBeanBuilder.build();
                Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
                Iterable<IndiaCensusCSV> csvIterable = () -> censusCSVIterator;
                int namOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
                return namOfEateries;
//                while (censusCSVIterator.hasNext()) {
//                    namOfEateries++;
//                    IndiaCensusCSV censusData = censusCSVIterator.next();
//                }
            }
            catch(IllegalStateException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
            }catch (IOException|RuntimeException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            }finally {
                try {
                    reader.close();
                }
                catch(Exception e){}
            }
        }
        public int loadIndianStateCode(String csvFilePath) throws CensusAnalyserException {
            CsvToBean<IndiaStateCodeCSV> csvToBean;Reader reader =null;
            try {
                reader= Files.newBufferedReader(Paths.get(csvFilePath));
                CsvToBeanBuilder<IndiaStateCodeCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
                csvToBeanBuilder.withType(IndiaStateCodeCSV.class);
                csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
                csvToBean=csvToBeanBuilder.build();
                Iterator<IndiaStateCodeCSV> censusCSVIterator = csvToBean.iterator();
                Iterable<IndiaStateCodeCSV> csvIterable=()-> censusCSVIterator;
                int namOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(),false).count();
                return namOfEateries;
//                while (censusCSVIterator.hasNext()) {
//                    namOfEateries++;
//                    IndiaCensusCSV censusData = censusCSVIterator.next();
//                }
            }
            catch(IllegalStateException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
            }
            catch (IOException|RuntimeException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            }
            finally {
                try {
                    reader.close();
                }
                catch(Exception e){}
            }
        }
    }
