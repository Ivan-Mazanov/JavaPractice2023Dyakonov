package dict.io;

import dict.model.Dictionary;

import java.io.*;
import java.util.HashMap;

import static dict.service.Converter.getHashMapFromString;
import static dict.service.Converter.getStringFromHashMap;

/**
 * File input/output for csv format
 */
public class CsvFileIO implements FileIO {
    @Override
    public HashMap<String, String> getDataFromFile(String filePath) throws IOException {
        if (!isValidFile(filePath))
            throw new IOException("Wrong file format. Use .csv instead");

        StringBuilder fileContent = new StringBuilder();
        String line;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line).append(System.lineSeparator());
            }
        }

        return getHashMapFromString(fileContent.toString(), ",", System.lineSeparator());
    }

    @Override
    public void loadDataInFile(Dictionary dictionary) throws IOException {
        String fileContent = getStringFromHashMap(dictionary.getContent(), ",", System.lineSeparator());

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dictionary.getFilePath()))) {
            bufferedWriter.write(fileContent);
        }
    }

    private boolean isValidFile(String filePath) {
        File file = new File(filePath);

        if (!file.exists())
            return false;

        String fileName = file.getName();

        String extension = "";

        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex >= 0)
            extension = fileName.substring(dotIndex + 1);

        return extension.equals("csv");
    }
}
