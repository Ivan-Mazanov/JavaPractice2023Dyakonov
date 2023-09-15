package dict.service;

import dict.model.Dictionary;

import java.util.ArrayList;
import java.util.List;


/**
 * Container for dictionaries storage
 */
class DictContainer {
    private static List<Dictionary> content = new ArrayList<>();

    /**
     * Adds dictionary in container
     *
     * @param dictionary Dictionary that will be added in container
     * @return Returns true if successful else false. If false name is already exists
     */
    public boolean addDict(Dictionary dictionary) {
        if (checkIfNameNotExist(dictionary.getDictionaryName())) {
            content.add(dictionary);
            return true;
        }
        return false;
    }

    /**
     * Gets dictionary from container by name
     *
     * @param dictName Dictionary name
     * @return Reference to dictionary object in container
     */
    public Dictionary getDict(String dictName) {
        for (Dictionary dictionary : content) {

            if (dictName.equals(dictionary.getDictionaryName()))
                return dictionary;

        }
        return null;
    }

    /**
     * Gets list of dictionaries in container
     *
     * @return List of dictionaries
     */
    public List<Dictionary> getCopyOfContent() {

        return List.copyOf(content);
    }

    private boolean checkIfNameNotExist(String newName) {
        for (Dictionary dictionary : content) {
            if (newName.equals(dictionary.getDictionaryName()))
                return false;
        }
        return true;
    }
}
