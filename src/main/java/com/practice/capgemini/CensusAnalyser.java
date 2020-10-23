package com.practice.capgemini;
import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    List<IndiaCensusCSV> csvFileList;
    List<IndiaStateCodeCSV> csvList;
    public CensusAnalyser() {
        this.csvFileList = new ArrayList<IndiaCensusCSV>();
    }
        public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
            CsvToBean<IndiaCensusCSV> csvToBean;
            try (Reader reader= Files.newBufferedReader(Paths.get(csvFilePath));){
                ICSVBuilder icsvBuilder= CSVBuilderFactory.createCSVBuilder();
                Iterator<IndiaCensusCSV> censusCSVIterator = icsvBuilder.getCSVFileIterator(reader,  IndiaCensusCSV.class);
                while (censusCSVIterator.hasNext()) {
                    this.csvFileList.add((censusCSVIterator.next()));
                }
                return csvFileList.size();
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
                ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
                this.csvList = csvBuilder.getCSVFileList(reader, IndiaStateCodeCSV.class);
                return csvList.size();
            }
            catch(IllegalStateException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
            }
            catch (IOException|RuntimeException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (CSVBuilderException e) {
                e.printStackTrace();
            }
            return 0;
        }

    private <E> int getCount(Iterator<E> iterator)
    {
        Iterable csvIterable=()-> iterator;
        int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(),false).count();
        return numOfEntries;
    }

    public String getStateWiseSortedCensusData(String indiaCensusCsvFilePath) throws CensusAnalyserException {
            loadIndiaCensusData(indiaCensusCsvFilePath);
//            ICSVBuilder csvBuilder=CSVBuilderFactory.createCSVBuilder();
            if (csvFileList == null || csvFileList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
            }
            Comparator<IndiaCensusCSV> censusComparator= Comparator.comparing(census->census.state);
            this.sort(censusComparator);
            String sortedStateCensusJson = new Gson().toJson(this.csvFileList);
            return sortedStateCensusJson;
    }

    private void sort(Comparator<IndiaCensusCSV> censusComparator) {
        for (int i = 0; i < csvFileList.size(); i++) {
            for (int j = 0; j < csvFileList.size() - i - 1; j++) {
                IndiaCensusCSV census1 = csvFileList.get(j);
                IndiaCensusCSV census2 = csvFileList.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    csvFileList.set(j, census2);
                    csvFileList.set(j + 1, census1);
                }
            }
        }
    }
    private void sort_desc(Comparator<IndiaCensusCSV> censusComparator) {
        for (int i = 0; i < csvFileList.size(); i++) {
            for (int j = 0; j < csvFileList.size() - i - 1; j++) {
                IndiaCensusCSV census1 = csvFileList.get(j);
                IndiaCensusCSV census2 = csvFileList.get(j + 1);
                if (censusComparator.compare(census1, census2) < 0) {
                    csvFileList.set(j, census2);
                    csvFileList.set(j + 1, census1);
                }
            }
        }
    }
    private void sort_code(Comparator<IndiaStateCodeCSV> censusComparator) {
        for (int i = 0; i < csvList.size(); i++) {
            for (int j = 0; j < csvList.size() - i - 1; j++) {
                IndiaStateCodeCSV census1 = csvList.get(j);
                IndiaStateCodeCSV census2 = csvList.get(j + 1);
                if (censusComparator.compare(census1, census2) < 0) {
                    csvList.set(j, census2);
                    csvList.set(j + 1, census1);
                }
            }
        }
    }
    public String getPopulationWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
        loadIndiaCensusData(csvFilePath);
        if (csvFileList == null || csvFileList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.population);
        this.sort_desc(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(this.csvFileList);
        return sortedStateCensusJson;
    }

    public String getAreaInSqKmWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
        loadIndiaCensusData(csvFilePath);
        if (csvFileList == null || csvFileList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
        this.sort_desc(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(this.csvFileList);
        return sortedStateCensusJson;
    }

    public String getStateCodeWiseSortedCensusData(String indiaStateCodeFilePath) throws CensusAnalyserException {
        loadIndianStateCode(indiaStateCodeFilePath);
        if (csvList == null || csvList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaStateCodeCSV> censusComparator= Comparator.comparing(census->census.stateCode);
        this.sort_code(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(this.csvList);
        return sortedStateCensusJson;
    }

    public String getDensityInSqKmWiseSortedCensusData(String indiaCensusCsvFilePath) throws CensusAnalyserException {
        loadIndiaCensusData(indiaCensusCsvFilePath);
        if (csvFileList == null || csvFileList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        this.sort_desc(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(this.csvFileList);
        return sortedStateCensusJson;
    }
}