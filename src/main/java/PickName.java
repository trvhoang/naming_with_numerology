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
        nameOperator.excludeNumbers.add(9);

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
    }
}
