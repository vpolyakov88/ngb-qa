package epam.autotests.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class MyFileReader {

	public static File loadExternalFile(String fileName){
		return  new File(new File("").getAbsolutePath() + "\\src\\main\\resources\\"+fileName);
	}

	public static List<String[]> readFile(File fileName, String...requiredColumns){
		String line;
        String cvsSplitBy = ",";
        String[] lineItems;
        boolean firstRowFlag = true;
        int[] indexes = null;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        	List<String[]> result = new ArrayList<>();
        	while ((line = br.readLine()) != null) {
        		lineItems = line.split(cvsSplitBy);
        		if(firstRowFlag){
        			indexes = getColumnsIndex(lineItems, requiredColumns);
        			firstRowFlag = false;
        		}
        		else {
        			result.add(collectValues(indexes, lineItems));
        		}
            }
        	br.close();
        	return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	private static String[] collectValues(int[] indexes, String[] lineItems){
		String[] values = new String[indexes.length];
		int counter = 0;
		for (int i : indexes) {
			values[counter++] = lineItems[i];
		}
		return values;
	}
	
	private static int[] getColumnsIndex(String[] lineFromFile, String...columnsName){
		int[] indexes = new int[columnsName.length];
		int counter=0;
		for (int i = 0; i < columnsName.length; i++) {
			for (int j = 0; j < lineFromFile.length; j++) {
				if(columnsName[i].equals(lineFromFile[j])){
					indexes[counter] = j;
					counter++;
					break;
				}
			}
		}
		return indexes;
	}
	
	public static int countLines(File file) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(file.getAbsolutePath()));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean empty = true;
	        while ((readChars = is.read(c)) != -1) {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n') {
	                    ++count;
	                }
	            }
	        }
	        return (count == 0 && !empty) ? 1 : count;
	    } finally {
	        is.close();
	    }
	}
}