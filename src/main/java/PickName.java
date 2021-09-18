import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class PickName {
    private static String getFileContent() throws IOException {
        String file = "name.txt";
        var path = Paths.get("src/main/resources/", file);
        String content = new String(Files.readAllBytes(path));
        return content;
    }

    public static void main(String[] args) throws IOException {
        String nameFile = getFileContent().replace(" ", "").trim();
        List<String> nameList = Arrays.stream(nameFile.split(",")).distinct().collect(Collectors.toList());
        NameOperator nameOperator = new NameOperator();
        nameOperator.nameList = nameList;
        nameOperator.excludeNumbers.add(1);
        nameOperator.excludeNumbers.add(2);

        nameOperator.getNameExceptNumber();
        List<String> result = nameOperator.combineNameFromString(2);
        result = result.stream().sorted().collect(Collectors.toList());
        if (result.size() == 0) System.out.println("NO SUITABLE NAME, FILTER BY SELF \n" + result);
        if ((result.size() > 0) && nameOperator.checkContainAllNumberExcept()) {
            System.out.println("GOOD NAME FULL NUMBERS COMES\n");
            nameOperator.printResult();
        }
        if ((result.size() > 0) && !nameOperator.checkContainAllNumberExcept()) {
            System.out.println("NO NAME FULL NUMBERS\n");
            nameOperator.printResult();
        }


//        String nameFiltered = getNameExceptNumber(nameList, 1, 2);
//        String nameCombined = combineNameFromString(nameFiltered);
//        List<String> nameCombinedList = Arrays.stream(nameCombined.split(",")).collect(Collectors.toList());
//        List<String> nameCombinedAndFiltered = getCombinedNameNotDuplicatedNumber(nameCombinedList.stream().distinct().sorted().collect(Collectors.toList()), 1, 2);
//        if (nameCombinedAndFiltered.size() > 0) {
//            nameCombinedAndFiltered = nameCombinedAndFiltered.stream().filter(name -> !name.isEmpty()).collect(Collectors.toList());
//            nameCombinedAndFiltered = nameCombinedAndFiltered.stream().filter(name -> !name.isBlank()).collect(Collectors.toList());
//            Map<Long, String> result = new HashMap<>();
//            Map<Long, String> temp = new HashMap<>();
//            for (int i = 0; i < nameCombinedAndFiltered.size() - 1; i++) {
//                temp = NumAndChar.getStringNumberOfName(nameCombinedAndFiltered.get(i));
//                for (Map.Entry<Long, String> item : temp.entrySet()) {
//                    result.put(item.getKey(), item.getValue());
//                }
//            }
//            result.entrySet().stream()
//                    .sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
//        }
//    }

    }
}
