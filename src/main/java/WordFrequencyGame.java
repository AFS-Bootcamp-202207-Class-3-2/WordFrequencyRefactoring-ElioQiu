import java.util.*;

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
            List<Input> inputList = getInputList(inputStr);

            //get the map for the next step of sizing the same word
            Map<String, List<Input>> countMap = getListMap(inputList);

            inputList = getCountList(countMap);
            inputList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

            return buildResultString(inputList);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private String buildResultString(List<Input> inputList) {
        StringJoiner joiner = new StringJoiner(DELIMITER);
        inputList.forEach(input -> {
            joiner.add(input.getValue() + " " + input.getWordCount());
        });
        return joiner.toString();
    }

    private List<Input> getCountList(Map<String, List<Input>> countMap) {
        List<Input> countList = new ArrayList<>();
        countMap.forEach((key, value) -> countList.add(new Input(key, value.size())));
        return countList;
    }

    private List<Input> getInputList(String inputStr) {
        String[] words = inputStr.split(SPLIT_REGEX);
        List<Input> inputList = new ArrayList<>();
        Arrays.stream(words).forEach(word ->{
            inputList.add(new Input(word, DEFAULT_COUNT));
        });
        return inputList;
    }


    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> countMap = new HashMap<>();
        inputList.forEach(input -> {
            countMap.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
        });
        return countMap;
    }
}
