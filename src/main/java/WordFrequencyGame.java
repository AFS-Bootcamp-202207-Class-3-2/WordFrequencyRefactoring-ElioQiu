import java.util.*;

public class WordFrequencyGame {

    public static final String SPLIT_REGEX = "\\s+";
    public static final String DELIMITER = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final int DEFAULT_COUNT = 1;

    public String getResult(String inputStr) {
        try {
            List<Input> inputList = formatInputList(inputStr);

            inputList = getCountList(inputList);
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

    private List<Input> getCountList(List<Input> inputList) {
        Map<String, List<Input>> countMap = new HashMap<>();
        inputList.forEach(input -> {
            countMap.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
        });
        List<Input> countList = new ArrayList<>();
        countMap.forEach((key, value) -> countList.add(new Input(key, value.size())));
        return countList;
    }

    private List<Input> formatInputList(String inputStr) {
        String[] words = inputStr.split(SPLIT_REGEX);
        List<Input> inputList = new ArrayList<>();
        Arrays.stream(words).forEach(word ->{
            inputList.add(new Input(word, DEFAULT_COUNT));
        });
        return inputList;
    }

}
