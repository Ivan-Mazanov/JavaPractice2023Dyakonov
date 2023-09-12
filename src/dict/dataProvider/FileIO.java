package dict.dataProvider;

import dict.dataSource.Dictionary;

import java.util.HashMap;

public interface FileIO {
    HashMap<String, String> getDataFromFile(String filePath);

    void loadDataInFile(Dictionary dictionary);
}
