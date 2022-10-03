package com.bridgelabz;

public class IndianState {
	 public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {

	        try {

	            if(csvFilePath.contains("txt")) {
	                throw new CensusAnalyserException("File must be in CSV Format", CensusAnalyserException.ExceptionType.CENSUS_INCORRECT_FILE_FORMAT);
	            }
	            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
	            CsvToBeanBuilder<IndianCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<IndiaCensusCSV>(reader);
	            csvToBeanBuilder.withType(IndianCensusCSV.class);
	            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
	            CsvToBean<IndianCensusCSV> csvToBean = csvToBeanBuilder.build();
	            Iterator<IndianCensusCSV> censusCSVIterator = csvToBean.iterator();

	            int numberOfEntries = 0;
	            while(censusCSVIterator.hasNext()) {
	                numberOfEntries++;
	                IndianCensusCSV censusData = censusCSVIterator.next();
	            }
	            return numberOfEntries;
	        }
	        catch(IOException e) {
	            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
	        }
	        catch(RuntimeException e) {
	            throw new CensusAnalyserException("CSV File Must Have Comma As Incorrect Header", CensusAnalyserException.ExceptionType.CENSUS_WRONG_DELIMITER_OR_WRONG_HEADER);
	        }
	    }

}
