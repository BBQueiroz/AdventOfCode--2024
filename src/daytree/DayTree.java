package daytree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayTree {

    public static void main(String[] args) throws IOException {
        long initial = System.currentTimeMillis();
        System.out.println("Parte 1: " + findMultiplications(false));
        long end = System.currentTimeMillis();
        System.out.println("Tempo total: " + (end - initial) + " ms\n");

        initial = System.currentTimeMillis();
        System.out.println("Parte 2: " + findMultiplications(true));
        end = System.currentTimeMillis();
        System.out.println("Tempo total: " + (end - initial) + " ms\n");
    }

    public static int findMultiplications(boolean useCommands) throws IOException {
        String caminho = "src/daytree/input.txt";
        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)");
        List<Integer> valores = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;

            boolean enabled = true;
            while ((linha = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(linha);

                while (matcher.find()) {
                    if(useCommands) {
                        if (matcher.group().equals("do()")) {
                            enabled = true;
                        } else if (matcher.group().equals("don't()")) {
                            enabled = false;
                        }
                    }
                    if(enabled && matcher.group().startsWith("mul")) valores.add(multiply(matcher));
                }
            }
        }
        return valores.stream().mapToInt(Integer::intValue).sum();
    }

    public static int multiply(Matcher matcher){
        String[] numeros = matcher.group().substring(4, matcher.group().length() - 1).split(",");
        int x = Integer.parseInt(numeros[0]);
        int y = Integer.parseInt(numeros[1]);

        return x * y;
    }

}