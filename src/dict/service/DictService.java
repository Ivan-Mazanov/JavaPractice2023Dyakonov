package dict.service;

import dict.io.CsvFileIO;
import dict.io.FileIO;
import dict.io.FileType;
import dict.model.Dictionary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import static dict.service.Converter.getStringFromHashMap;

/**
 * Service that represents methods for UI
 */
public class DictService {
    private FileIO fIleIO;
    private DictContainer dictContainer = new DictContainer();

    /**
     * @param fileType Type of file where dictionary is stored
     */
    public DictService(FileType fileType) {
        switch (fileType) {
            case CSV -> fIleIO = new CsvFileIO();
        }
    }

    /**
     * Gets data from file and initialize dictionary to put it in dictionary container
     *
     * @param dictName       Name that represents dictionary in container
     * @param filePath       Path to file
     * @param validationFunc Expression that validates input data
     * @return Returns true if successful else false. If false wrong file path or dictionary name is already exists
     */
    public boolean initializeDict(String dictName, String filePath, Predicate<String> validationFunc) {
        HashMap<String, String> content;
        try {
            content = fIleIO.getDataFromFile(filePath);
        } catch (IOException e) {
            return false;
        }

        Dictionary dictionary = new Dictionary(dictName, filePath, content, validationFunc);

        return dictContainer.addDict(dictionary);
    }

    /**
     * @return List of dictionaries names
     */
    public List<String> getNames() {
        List<String> nameList = new ArrayList<>();

        for (Dictionary dictionary : dictContainer.getCopyOfContent()) {

            nameList.add(dictionary.getDictionaryName());
        }
        return nameList;
    }

    /**
     * Adds new pair in dictionary using validation func
     *
     * @param dictName Name that represents dictionary in container
     * @param key
     * @param value
     * @return Returns true if successful else false
     */
    public boolean addNewPair(String dictName, String key, String value) {
        if (key == null)
            return false;
        return dictContainer.getDict(dictName).addPair(key, value);
    }

    /**
     * Gets dictionary represented in String
     *
     * @param dictName Name that represents dictionary in container
     * @return Dictionary represented in String
     */
    public String getDictString(String dictName) {
        var dict = dictContainer.getDict(dictName).getContent();

        return getStringFromHashMap(dict, ":", System.lineSeparator());
    }

    /**
     * Finds value in dictionary by key
     *
     * @param dictName Name that represents dictionary in container
     * @param key
     * @return Value
     */
    public String findByKey(String dictName, String key) {
        return dictContainer.getDict(dictName).findByKey(key);
    }

    /**
     * Deletes pair from dictionary by key
     *
     * @param dictName Name that represents dictionary in container
     * @param key
     * @return Returns true if successful else false
     */
    public boolean deleteByKey(String dictName, String key) {
        return dictContainer.getDict(dictName).deleteByKey(key);
    }

    /**
     * Save dictionary in file
     *
     * @param dictName Name that represents dictionary in container
     * @return Returns true if successful else false
     */
    public boolean saveData(String dictName) {
        try {
            fIleIO.loadDataInFile(dictContainer.getDict(dictName));
            return true;

        } catch (IOException e) {
            return false;
        }
    }
}
