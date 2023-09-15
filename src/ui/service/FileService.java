package ui.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileService {
    public static ArrayList<List<String>> getListOfLines(String filePath) throws IOException {
        String line;
        ArrayList<List<String>> listOfLines = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

            while ((line = bufferedReader.readLine()) != null) {

                listOfLines.add(new ArrayList<>(Arrays.asList(line.split(","))));
            }
        }
        return listOfLines;
    }
}
