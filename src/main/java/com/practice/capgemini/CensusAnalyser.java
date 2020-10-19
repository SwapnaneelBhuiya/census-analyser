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
            try {
                Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
                CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
                csvToBeanBuilder.withType(IndiaCensusCSV.class);
                csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
                csvToBean=csvToBeanBuilder.build();
                Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
                Iterable<IndiaCensusCSV> csvIterable=()-> censusCSVIterator;
                int namOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(),false).count();
                return namOfEateries;
//                while (censusCSVIterator.hasNext()) {
//                    namOfEateries++;
//                    IndiaCensusCSV censusData = censusCSVIterator.next();
//                }
            } catch (IOException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            }
            catch(IllegalStateException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
            }
        }
    }
