package dict.io;

import dict.model.Dictionary;

import java.io.IOException;
import java.util.HashMap;

/**
 * File input\output
 */
public interface FileIO {
    /**
     * Gets dictionary from file
     *
     * @param filePath Path to file
     * @return HashMap object that associates with dictionary in file
     * @throws IOException Wrong file path
     */
    HashMap<String, String> getDataFromFile(String filePath) throws IOException;

    /**
     * Loads dictionary in file
     *
     * @param dictionary Dictionary that will be load in file
     * @throws IOException Wrong file path
     */
    void loadDataInFile(Dictionary dictionary) throws IOException;
}
