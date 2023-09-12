package dict.service;

import java.util.HashMap;

//Набор методов для перевода строки в hash map и обратно
public class Converter {
    public static HashMap<String, String> getHashMapFromString
            (String fileContent, String pairSeparator, String lineSeparator) {

        String[] pairs = fileContent.split(lineSeparator);
        HashMap<String, String> hashMap = new HashMap<>();

        for (String strPair : pairs) {
            String[] arrPair = strPair.split(pairSeparator);
            hashMap.put(arrPair[0], arrPair[1]);
        }
        return hashMap;
    }

    public static String getStringFromHashMap
            (HashMap<String, String> hashMap, String pairSeparator, String lineSeparator) {
        StringBuilder stringBuilder = new StringBuilder();

        hashMap.forEach((key, value) -> {
            stringBuilder.append(key).append(pairSeparator).append(value).append(lineSeparator);
        });
        return stringBuilder.toString();
    }
}
