import numberrangesummarizer.NumberRangeSummarizer;

import java.util.*;

public class NumberSummarizerImpl implements NumberRangeSummarizer {
    public static void main(String[] args) {
        NumberSummarizerImpl num = new NumberSummarizerImpl();
        String sampleInput = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        try {
            Collection<Integer> collectionInput = num.collect(sampleInput);
            String range = num.summarizeCollection(collectionInput);

            System.out.println(range);
        } catch (ClassCastException exc) {
            System.out.println("Please review the string there might be a character");
        }
    }

    public Collection<Integer> collect(String input) {
        String[] arrayOfString = input.split(",");
        int[] integers = new int[arrayOfString.length];

        for (int i = 0; i < arrayOfString.length; i++) {
            integers[i] = Integer.parseInt(arrayOfString[i]);
        }

        Arrays.sort(integers);
        List<Integer> list = new ArrayList<>();

        for (int integer : integers) {
            list.add(integer);
        }
        return list;
    }

    public String summarizeCollection(Collection<Integer> input) {
        int count = 0;

        StringBuilder stingBuilder = new StringBuilder();
        ArrayList<Integer> inputList = new ArrayList<>(input);

        int length = inputList.size();
        int start;
        int next;

        for (int j = 0; j < length; j++) {
            if (j == length - 1) {
                if (!Objects.equals(inputList.get(length - 2), inputList.get(length - 1))) {
                    stingBuilder.append(inputList.get(j)).append(",");
                }
                break;
            }

            start = inputList.get(j);
            next = inputList.get(j + 1);

            if (next == start + 1) {
                count++;
                int lowestBound = start;
                for (int k = j + 1; ; k++) {
                    start = inputList.get(k);
                    next = inputList.get(k + 1);
                    if (next == start + 1) {
                        count++;
                    } else {
                        j = k;
                        if (count != 0) {
                            stingBuilder.append(lowestBound).append(" - ").append(inputList.get(k)).append(", ");
                        }
                        break;
                    }
                }
                count = 0;
            } else {
                stingBuilder.append(start).append(", ");
            }
        }

        String ranges = stingBuilder.toString();
        return ranges.substring(0, ranges.length() - 1);
    }
}