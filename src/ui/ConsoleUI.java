package ui;

import dict.io.FileType;
import dict.service.DictService;

import java.util.List;
import java.util.function.Predicate;

//Работа с DictService и отправка сообщений клиенту
public class ConsoleUI {
    private static DictService dictService;
    private String currentDictName;

    public ConsoleUI() {
        dictService = new DictService(FileType.CSV);
    }

    public String getNames() {
        StringBuilder names = new StringBuilder();
        for (String name : dictService.getNames()) {
            names.append(" ").append(name);
        }

        return names.toString();
    }

    public String switchSpecifiedDict(String dictName) {
        List<String> names = dictService.getNames();

        for (String name : names) {
            if (name.equals(dictName)) {
                currentDictName = dictName;
                return "Successfully switched on " + dictName;
            }
        }
        return "No dictionaries with this name";
    }

    public String getCurrentDict() {
        if (currentDictName == null)
            return "Not switched on a specified dictionary";

        return dictService.getDictString(currentDictName);
    }

    public String deleteFromCurrentDict(String key) {
        if (currentDictName == null)
            return "Not switched on a specified dictionary";

        boolean result = dictService.deleteByKey(currentDictName, key);

        if (result)
            return "Successfully deleted";
        return "There is no such key";
    }

    public String findInCurrentDict(String key) {
        if (currentDictName == null)
            return "Not switched on a specified dictionary";

        String result = dictService.findByKey(currentDictName, key);
        if (result == null)
            return "There is no such key";
        return result;
    }

    public String addInCurrentDict(String key, String value) {
        if (currentDictName == null)
            return "Not switched on a specified dictionary";

        boolean result = dictService.addNewPair(currentDictName, key, value);

        if (result)
            return "Successfully added";
        return "Wrong input data";
    }

    public String saveCurrentDict() {
        if (dictService.saveData(currentDictName))
            return "Current dictionary was successfully saved";

        return "Data saving was failed";
    }

    public String saveAllData() {
        List<String> names = dictService.getNames();

        for (String name : names) {
            if (!dictService.saveData(name))
                return "Data saving was failed";
        }

        return "All data was successfully saved";
    }

    public String initializeDicts(String dictName, String filePath, Predicate<String> validationFunc) {
        if (dictService.initializeDict(dictName, filePath, validationFunc))
            return dictName.toUpperCase() + " were loaded in memory";

        return "Unable to load " + dictName.toUpperCase() + " in memory";
    }
}
