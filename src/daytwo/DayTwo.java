package daytwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayTwo {

    public static void main(String[] args) {
        String caminho = "src/daytwo/input.txt";
        List<String> reports = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                reports.add(linha);
            }

            long initial = System.currentTimeMillis();
            findSafeReports(reports);
            long end = System.currentTimeMillis();
            System.out.println("Tempo total: " + (end - initial) + " ms\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void findSafeReports(List<String> reports) {
        int safePartOne = 0;
        int safePartTwo = 0;
        for (String report : reports) {
            String[] levels = report.split(" ");
            int[] intLevels = Arrays.stream(levels).mapToInt(Integer::parseInt).toArray();
            if (partOne(intLevels)) {
                safePartOne += 1;
            }

            if (partTwo(intLevels)) {
                safePartTwo += 1;
            }
        }
        System.out.println(safePartOne);
        System.out.println(safePartTwo);
    }

    private static boolean partOne(int[] levels) {
        boolean isIncreasing = true;
        boolean isDecreasing = true;
        boolean isSafe = true;
        for (int y = 0; y < levels.length - 1; y++) {
            if (levels[y] > levels[y + 1]) {
                isIncreasing = false;
            } else if (levels[y] < levels[y + 1]) {
                isDecreasing = false;
            }

            if (!isDecreasing && !isIncreasing) {
                isSafe = false;
                break;
            }
            if (Math.abs(levels[y] - levels[y + 1]) > 3 || Math.abs(levels[y] - levels[y + 1]) < 1) {
                isSafe = false;
                break;
            }
        }
        return isSafe;
    }

    private static boolean partTwo(int[] levels) {
        List<Boolean> reports = new ArrayList<>();
        for(int i = 0; i < levels.length; i++){
            int[] levelRemoved = new int[levels.length - 1];
            int index = 0;
            for (int j = 0; j < levels.length; j++) {
                if (j != i) {  // Ignora o elemento na posição 'i'
                    levelRemoved[index++] = levels[j];
                }
            }
            boolean isIncreasing = true;
            boolean isDecreasing = true;
            boolean isSafe = true;
            for (int y = 0; y < levelRemoved.length - 1; y++) {
                if (levelRemoved[y] > levelRemoved[y + 1]) {
                    isIncreasing = false;
                } else if (levelRemoved[y] < levelRemoved[y + 1]) {
                    isDecreasing = false;
                }

                if (!isDecreasing && !isIncreasing) {
                    isSafe = false;
                    break;
                }
                if (Math.abs(levelRemoved[y] - levelRemoved[y + 1]) > 3 || Math.abs(levelRemoved[y] - levelRemoved[y + 1]) < 1) {
                    isSafe = false;
                    break;
                }
            }
            reports.add(isSafe);
        }

        return reports.contains(true);
    }
}