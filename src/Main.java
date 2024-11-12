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

        string = ("Надо мною - тишина, " +
                "Небо полное дождя, " +
                "Дождь проходит сквозь меня, " +
                "Но боли больше нет. " +
                "Под холодный шепот звезд " +
                "Мы сожгли последний мост, " +
                "И все в бездну сорвалось. " +
                "Свободным стану я " +
                "От зла и от добра, " +
                "Моя душа была на лезвии ножа. " +
                "Я бы мог с тобою быть, " +
                "Я бы мог про все забыть, " +
                "Я бы мог тебя любить, " +
                "Но это лишь игра. " +
                "В шуме ветра за спиной " +
                "Я забуду голос твой, " +
                "И о той любви земной, " +
                "Что нас сжигала в прах, " +
                "И я сходил с ума... " +
                "В моей душе нет больше места для тебя! " +
                "Я свободен, словно птица в небесах, " +
                "Я свободен, я забыл, что значит страх. " +
                "Я свободен с диким ветром наравне, " +
                "Я свободен наяву, а не во сне!").toLowerCase();
        cleaner(new String[]{";", "\\.", ",", "!", "-"});

        System.out.println("\nНормализованная строка (только буквы, нижний регистр):");
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
        myHashMap.remove(""); // при конвертации строки в список не должны проскочить ключи с нулевой длиной.

        System.out.println("\nВ анализируемой фразе обнаружены следующие уникальные записи (упорядочены по возрастанию):");
        TreeSet<String> keys = new TreeSet<>(myHashMap.keySet());
        System.out.println(keys);

        System.out.println("\nВсего уникальных записей:");
        System.out.println(myHashMap.size());

        System.out.println("\nЧастота обнаружения записей:");
        TreeMap<String, Integer> treeMap = new TreeMap<>(myHashMap);
        System.out.println(treeMap);

        int listCount = 1;  // В этой переменной будет храниться частота, с которой слова встречаются в строке.

        List<String> topWords1 = List.of(); // Вспомогательный массив для сохранения выборки из keys - слов,
                                            // частота которых больше, чем текущее значение listCount.
        topWords1 = List.of(String.valueOf(keys)); // Инициализируем вспомогательный массив загрузкой полного списка keys.
        List<String> topWords2 = List.of(); // Здесь будет храниться предыдущая выборка значений по частоте.
        do {
            topWords2 = topWords1;  // Перед созданием новой выборки при переходе на более высокую частоту
                                    // сохраним текущую выборку во вспомогательный список topWords2.

            int finalListCount = listCount; // Применим вспомогательную переменную,
                                            // потому что лямбда-функция требует final-величину.

            topWords1 = keys.stream()
                    .filter(s -> {
                        return myHashMap.get(s) > finalListCount;   // Берём в topWords1 только слова, встреченные более,
                                                                    // чем finalListCount раз.
                    })
                    .toList();

            listCount++;                // Начиная с частоты 2 последовательно проверяем более высокие частоты,
        }while (topWords1.size() > 3);  // пока наш список не сузится до ТОП3.

        // ТОП-3 можно составить не всегда, например, когда третье место делят несколько записей.
        // Тогда ТОП слов по частоте выйдет за пределы размера в 3 слова. Ниже разбираемся с этим:
        System.out.println("\nНаиболее часто встречаются слова:");
        System.out.println((topWords1.size()==3) ? topWords1 : topWords2);
    }

}