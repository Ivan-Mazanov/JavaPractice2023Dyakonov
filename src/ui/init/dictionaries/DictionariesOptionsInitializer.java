package ui.init.dictionaries;

import ui.ConsoleUI;
import ui.service.FileService;
import ui.init.OptionsInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DictionariesOptionsInitializer implements OptionsInitializer {

    private static boolean isInit = false;
    private static String message;

    public boolean isInit() {
        return isInit;
    }

    @Override
    public boolean initOptions(String filePath) {
        if (isInit) {
            message = "Can't load dictionaries. Reason: dictionaries have been loaded already";
            return false;
        }
        try {
            ArrayList<List<String>> listOfLines = FileService.getListOfLines(filePath);
            ConsoleUI consoleUI = new ConsoleUI();
            StringBuilder stringBuilder = new StringBuilder();

            for (List<String> line : listOfLines) {
                if (line.size() < 3)
                    line.add(null);

                stringBuilder.append(consoleUI.initializeDicts(line.get(0), line.get(1), k -> k.matches(line.get(2))))
                        .append(System.lineSeparator());
            }
            message = stringBuilder.toString();

        } catch (IOException e) {
            message = "Unable to load dictionaries list. Can't find options file path. Program is shutting down";
            return false;
        }
        isInit = true;
        return true;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
