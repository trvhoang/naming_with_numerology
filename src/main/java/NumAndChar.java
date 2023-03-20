import java.util.*;
import java.util.stream.Collectors;

public enum NumAndChar {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8),
    I(9),
    J(1),
    K(2),
    L(3),
    M(4),
    N(5),
    O(6),
    P(7),
    Q(8),
    R(9),
    S(1),
    T(2),
    U(3),
    V(4),
    W(5),
    X(6),
    Y(7),
    Z(8);

    public Integer number;

    NumAndChar(Integer number) {
        this.number = number;
    }

    public static Integer getNumberOfChar(String input) {
        input.replace(" ","").trim();
        try{
            return NumAndChar.valueOf(input.trim().toUpperCase()).number;
        } catch (Exception e) {
            return null;
        }

    }

    public static Map<Long,String> getStringNumberOfName(String input){
        input.replace(" ","");
        Map<Long,String> map = new HashMap<>();
        List<String> inputList = Arrays.stream(input.split("")).collect(Collectors.toList());
        inputList.removeAll(Collections.singleton(" "));
        StringBuilder stringBuilder = new StringBuilder();
        inputList.stream().forEach(s -> stringBuilder.append(getNumberOfChar(s).toString()));
        map.put(Long.valueOf(stringBuilder.toString()), input);
        return map;
    }

    public static Long getNumberOfName(String input){
        input.replace(" ","");
        Map<Long,String> map = new HashMap<>();
        List<String> inputList = Arrays.stream(input.split("")).collect(Collectors.toList());
        inputList.removeAll(Collections.singleton(" "));
        StringBuilder stringBuilder = new StringBuilder();
        inputList.stream().forEach(s -> stringBuilder.append(getNumberOfChar(s).toString()));
        return Long.valueOf(stringBuilder.toString());
    }

    public Integer getNumber() {
        return this.number;
    }

    public static String getCharByNum(Integer... numbers) {
        StringBuilder string = new StringBuilder();
        for (Integer number : numbers) {
            for (NumAndChar numAndChar : NumAndChar.values()) {
                if (numAndChar.getNumber() == number) {
                    string.append(numAndChar.name() + ",");
                }
            }
        }
        return string.substring(0, string.length() - 1);
    }
}
