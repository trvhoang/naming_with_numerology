import java.util.*;
import java.util.stream.Collectors;

public class NameOperator {
    public List<String> nameList;
    public List<Integer> excludeNumbers;
    public List<String> resultList;

    NameOperator() {
        this.nameList = new ArrayList<>();
        this.excludeNumbers = new ArrayList<>();
        this.resultList = new ArrayList<>();
    }

    public Boolean doesContainsExcludedNumber(String input, List<Integer> filters) {
        boolean flag = false;
        Map<Long, String> map = NumAndChar.getStringNumberOfName(input);
        String stringNum = map.entrySet().iterator().next().getKey().toString();
        for (int i = 0; i < filters.size() - 1; i++) {
            if (stringNum.contains(filters.get(i).toString())) flag = true;
        }
        return flag;
    }

    public String getNameExceptNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.nameList.size() - 1; i++) {
            if (!doesContainsExcludedNumber(nameList.get(i), excludeNumbers)) {
                stringBuilder.append(nameList.get(i) + ",");
            }
        }
        if (stringBuilder.length() == 0) throw new RuntimeException("getNameExceptNumber No name suitable");
        String result = stringBuilder.substring(0, stringBuilder.length() - 1);
        return result;
    }

    public List<String> combineNameFromString(Integer duplicateNum) {
        String[] combineArray = getNameExceptNumber().split(",");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < combineArray.length - 1; i++) {
            for (int y = 0; y < combineArray.length - 1; y++) {
                if (i != y) {
                    String input = combineArray[i] + combineArray[y];
                    if (checkDuplication(input, duplicateNum)) {
                        result.append(combineArray[i] + " " + combineArray[y] + ", ");
                    }

                }
            }
        }
        this.resultList = Arrays.stream(result.toString().split(",")).collect(Collectors.toList());
        return this.resultList;
    }

    public Boolean checkDuplication(String input, Integer duplicateNum) {
        Map<Long, String> map = NumAndChar.getStringNumberOfName(input);
        String stringNum = map.entrySet().iterator().next().getKey().toString();
        List<String> numList = Arrays.stream(stringNum.split("")).collect(Collectors.toList());
        int count = 0;
        for (int i = 0; i < numList.size() - 1; i++) {
            count = 0;
            for (int y = 0; y < numList.size() - 1; y++) {
                String string1 = numList.get(i);
                String string2 = numList.get(y);
                if ((i != y) && (string1.equals(string2))) {
                    count++;
                }
            }
            if (count > duplicateNum) return false;
        }
        return true;
    }

    public Boolean checkContainAllNumberExcept() {
        List<String> input = this.resultList;
        input.removeAll(Arrays.asList(" ", "", ",", null));
        input = input.stream().filter(item -> !item.isEmpty()).collect(Collectors.toList());
        input = input.stream().filter(item -> !item.isBlank()).collect(Collectors.toList());
        boolean flag = true;
        if (input.size() == 0) {
            System.out.println("NO INPUT TO CHECK NAME FULL NUMBER");
            return false;
        }
        List<Integer> list = new ArrayList<>();
        for (String chars : input) {
            try {
                Integer abc = NumAndChar.getNumberOfChar(chars);
                list.add(NumAndChar.getNumberOfChar(chars));
            } catch (Exception e) {
                System.out.println("VALIDATOR ERROR " + chars);
            }
        }
        Integer[] allNum = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> allNumList = Arrays.stream(allNum).collect(Collectors.toList());
        list.stream().sorted();
        for (Integer number : this.excludeNumbers) {
            allNumList.remove(number);
        }
        for (Integer number : allNumList) {
            String numberString = number.toString();
            for (String string : this.resultList) {
                Map<Long, String> map = NumAndChar.getStringNumberOfName(string);
                String mapString = map.entrySet().iterator().next().getKey().toString();
                if (mapString.contains(numberString)) flag = false;
            }
        }
        return flag;
    }

    public void printResult() {
        List<String> result = this.resultList;
        Map<String, Long> mapResult = new HashMap<>();
        for (int i = 0; i < result.size() - 1; i++) {
            Long temp = NumAndChar.getNumberOfName(result.get(i));
            mapResult.put(result.get(i), temp);
        }
        mapResult.entrySet().stream().distinct()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(System.out::println);
    }
}
