import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.io.CharArrayWriter;

import java.time.LocalDateTime;

public class WordFrequencyGame {

    public static final String SPLIT_REGEX = "\\s+";
    public static final String DELIMITER = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final int DEFAULT_COUNT = 1;

    public String getResult(String inputStr) {
        if (inputStr.split(SPLIT_REGEX).length == DEFAULT_COUNT) {
            return inputStr + " " + DEFAULT_COUNT;
        }
        try {
            //split the input string with 1 to n pieces of spaces
            String[] words = inputStr.split(SPLIT_REGEX);

            List<Input> inputList = new ArrayList<>();
            for (String word : words) {
                inputList.add(new Input(word, DEFAULT_COUNT));
            }

            //get the map for the next step of sizing the same word
            Map<String, List<Input>> countMap = getListMap(inputList);

            List<Input> countList = new ArrayList<>();
            for (Map.Entry<String, List<Input>> entry : countMap.entrySet()) {
                countList.add(new Input(entry.getKey(), entry.getValue().size()));
            }
            inputList = countList;
            inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

            StringJoiner joiner = new StringJoiner(DELIMITER);
            for (Input word : inputList) {
                joiner.add(word.getValue() + " " + word.getWordCount());
            }
            return joiner.toString();
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }


    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> countMap = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!countMap.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                countMap.put(input.getValue(), arr);
            } else {
                countMap.get(input.getValue()).add(input);
            }
        }
        return countMap;
    }
}
