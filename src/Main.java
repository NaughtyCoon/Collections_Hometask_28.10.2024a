import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static String string;
    public static String[] str;

    // Произведём первичную очистку строки от лишних (небуквенных) символов
    public static void cleaner(String[] letterToClear){
        for (String lt : letterToClear) {
            str = string.split(lt);
            string = "";
            for (String a : str) {
                string += a;
            }
        }
    }

    public static void main(String[] args) {

        string = ("Even though I walk through the valley" +
                " of the shadow of death I will fear no evil, for You are with me; " +
                "Your rod and your staff they comfort me.").toLowerCase();
        cleaner(new String[]{";", "\\.", ","});

        System.out.println(string);

        str = string.split(" ");

        HashMap<String, Integer> myHashMap = new HashMap<>();
        int strValue = 0;
        for (int i=0; i < str.length; i++) {
            if (myHashMap.get(str[i]) == null) {
                myHashMap.put(str[i], 1);
            } else {
                myHashMap.put(str[i], myHashMap.get(str[i]) + 1);
            }
        }

        System.out.println("\nВ анализируемой фразе обнаружены следующие уникальные записи (упорядочены по возрастанию):");
        TreeSet<String> keys = new TreeSet<>(myHashMap.keySet());
        System.out.println(keys);

        System.out.println("\nВсего уникальных записей:");
        System.out.println(myHashMap.size());

        //System.out.println(myHashMap);
        System.out.println("\nЧастота обнаружения записей:");
        TreeMap<String, Integer> treeMap = new TreeMap<>(myHashMap);
        System.out.println(treeMap);

       
    }

}