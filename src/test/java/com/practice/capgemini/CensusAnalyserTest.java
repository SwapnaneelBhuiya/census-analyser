package com.practice.capgemini;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Type;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String INDIA_CENSUS_CSV_FILE_PATH_1 = "./src/main/resources/IndiaStateCensusData.txt";
    private static final String INDIA_CENSUS_CSV_FILE_PATH_3= "./src/main/resources/IndiaStateCensusData2.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String INDIA_STATE_CODE_FILE_PATH="./src/main/resources/IndiaStateCode.csv";
    private static final String INDIA_STATE_CODE_FILE_PATH_2="./src/main/resources/IndiaStateCode.txt";
    private static final String INDIA_STATE_CODE_FILE_PATH_3="./src/main/resources/IndiaStateCode2.csv";
    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29,numOfRecords);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenTheStateCensuCSVFilewhencorrectbuttypeincorrectReturnsACustomException()
    {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH_1);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenTheStateCensusCSVFileWhenCorrectButDelimiterIncorrectReturnsACustomException()
    {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH_3);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenTheStateCensusCSVFileWhenCorrectButCSVHeaderIncorrectReturnsACustomException()
    {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH_3);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenIndianStateCSV_ShouldReturnExactCount() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int i = censusAnalyser.loadIndiaCensusData(INDIA_STATE_CODE_FILE_PATH);
            Assert.assertEquals(37, i);
        }
        catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenIndiaStateCSV_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenTheStateCodeCSVFilewhencorrectbuttypeincorrectReturnsACustomException()
    {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_STATE_CODE_FILE_PATH_2);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenTheStateCodeCSVFileWhenCorrectButDelimiterIncorrectReturnsACustomException()
    {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_STATE_CODE_FILE_PATH_3);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenTheStateCodeCSVFileWhenCorrectButCSVHeaderIncorrectReturnsACustomException()
    {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_STATE_CODE_FILE_PATH_3);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void giveIndianCensusDta_WhenSortedOnState_ShouldReturnResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        String sortedCensusData= censusAnalyser.getStateWiseSortedCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        IndiaCensusCSV[] censusCSV=new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
    }
    @Test
    public void giveIndianCensusDta_WhenSortedOnStateCode_ShouldReturnResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        String sortedCensusData= censusAnalyser.getStateCodeWiseSortedCensusData(INDIA_STATE_CODE_FILE_PATH);
        IndiaStateCodeCSV[] censusCSV=new Gson().fromJson(sortedCensusData, IndiaStateCodeCSV[].class);
        Assert.assertEquals("Andhra Pradesh New", censusCSV[0].state);
    }
    @Test
    public void givenIndianCensusData_WhenSortedOnStateWrong_ShouldReturnWrongSortedResult() {
        String sortedCensusData = null;
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertNotEquals("Goa",censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenIndianCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {

        String sortedCensusData = null;
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            sortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(199812341,censusCSV[0].population);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnAreaInSqKm_ShouldReturnSortedResult() {

        String sortedCensusData = null;
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            sortedCensusData = censusAnalyser.getAreaInSqKmWiseSortedCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(342239,censusCSV[0].areaInSqKm);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenIndianCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortedResult() {

        String sortedCensusData = null;
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            sortedCensusData = censusAnalyser.getDensityInSqKmWiseSortedCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(1102,censusCSV[0].densityPerSqKm);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}


