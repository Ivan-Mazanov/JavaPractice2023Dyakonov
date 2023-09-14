package dict.io;

import dict.model.Dictionary;

import java.io.IOException;
import java.util.HashMap;

public interface FileIO {
    HashMap<String, String> getDataFromFile(String filePath) throws IOException;

    void loadDataInFile(Dictionary dictionary) throws IOException;
}
