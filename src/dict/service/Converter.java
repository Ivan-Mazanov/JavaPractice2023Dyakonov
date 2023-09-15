package dict.service;

import java.util.HashMap;

/**
 * Methods that convert String in HashMap and vise versa
 */
public class Converter {
    /**
     * Gets HashMap from String
     *
     * @param fileContent   String that represents dictionary from file
     * @param pairSeparator Separator that used to divide key and value in String
     * @param lineSeparator Separator that used to divide pairs in String
     * @return HashMap object associated with dictionary from file
     */
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

    /**
     * Gets String from HashMap
     *
     * @param hashMap       HashMap that represents dictionary
     * @param pairSeparator Separator that used to divide key and value in String
     * @param lineSeparator Separator that used to divide pairs in String
     * @return String object associated with HashMap dictionary
     */
    public static String getStringFromHashMap
    (HashMap<String, String> hashMap, String pairSeparator, String lineSeparator) {
        StringBuilder stringBuilder = new StringBuilder();

        hashMap.forEach((key, value) -> {
            stringBuilder.append(key).append(pairSeparator).append(value).append(lineSeparator);
        });
        return stringBuilder.toString();
    }
}
