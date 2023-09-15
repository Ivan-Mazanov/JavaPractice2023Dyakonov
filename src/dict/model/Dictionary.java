package dict.model;

import java.util.HashMap;
import java.util.function.Predicate;

/**
 * Dictionary data storage and actions can be done with that data
 */
public class Dictionary {
    private String dictionaryName;
    private String filePath;
    private HashMap<String, String> content;
    private Predicate<String> validationFunc;

    /**
     * @param dictionaryName Name that represents dictionary in container
     * @param filePath       Path to file
     * @param content        HasMap object that represents dictionary
     * @param validationFunc Expression that validates input data
     */
    public Dictionary(String dictionaryName, String filePath, HashMap<String, String> content,
                      Predicate<String> validationFunc) {
        this.dictionaryName = dictionaryName;
        this.filePath = filePath;
        this.content = content;
        this.validationFunc = validationFunc;
    }

    /**
     * @return Name that represents dictionary in container
     */
    public String getDictionaryName() {
        return dictionaryName;
    }

    /**
     * @return Path to file
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @return HasMap object that represents dictionary
     */
    public HashMap<String, String> getContent() {
        return content;
    }

    /**
     * Deletes pair from dictionary by key
     *
     * @param key Key associated with value
     * @return Returns true if successful else false
     */
    public boolean deleteByKey(String key) {
        var result = content.remove(key);

        return result != null;
    }

    /**
     * Finds value in dictionary by key
     *
     * @param key Key associated with value
     * @return Value associated with key
     */
    public String findByKey(String key) {
        return content.get(key);
    }

    /**
     * Adds new pair in dictionary using validation func
     *
     * @param key
     * @param value
     * @return Returns true if successful else false
     */
    public boolean addPair(String key, String value) {
        if (validationFunc.test(key)) {
            content.put(key, value);
            return true;
        }
        return false;
    }
}
