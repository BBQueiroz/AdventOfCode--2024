package dayfour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayFour {

    public static void main(String[] args) throws IOException {
        System.out.println("Iniciando dia 4");
        List<String> linhas = readFile();

        long initial = System.currentTimeMillis();
        System.out.println("Parte 1: " + findFirstLetter(linhas, false));
        long end = System.currentTimeMillis();
        System.out.println("Tempo total: " + (end - initial) + " ms\n");

        initial = System.currentTimeMillis();
        System.out.println("Parte 2: " + findFirstLetter(linhas, true));
        end = System.currentTimeMillis();
        System.out.println("Tempo total: " + (end - initial) + " ms\n");
    }
    private static List<String> readFile() throws IOException {
        String caminho = "src/dayfour/input.txt";
        List<String> linhas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        }
        return linhas;
    }

    public static int findFirstLetter(List<String> linhas, boolean xMas){
        int counter = 0;
        for(int i = 0; i < linhas.size() ; i++){
            for(int j = 0; j < linhas.get(i).length() ; j++){
                char letter = linhas.get(i).charAt(j);

                if (xMas && letter == 'A') {
                    counter += findXMas(linhas, i, j);
                } else if (letter == 'X' && !xMas){
                    counter += findAllDirections(linhas, i, j);
                }
            }
        }
        return counter;
    }

    public static int findXMas(List<String> linhas, int row, int column){
        if(row >= (linhas.size() -1) || column >= (linhas.get(row).length()-1)  || row <= 0 || column <= 0 ) return 0;

        int counter = 0;
        if(linhas.get(row+1).charAt(column-1) == 'M' && linhas.get(row-1).charAt(column+1) == 'S' || linhas.get(row-1).charAt(column+1) == 'M' && linhas.get(row+1).charAt(column-1) == 'S' ){
            if(linhas.get(row+1).charAt(column+1) == 'M' && linhas.get(row-1).charAt(column-1) == 'S' || linhas.get(row-1).charAt(column-1) == 'M' && linhas.get(row+1).charAt(column+1) == 'S' ){
                counter++;
            }
        }
        return counter;
    }

    public static int findAllDirections(List<String> linhas, int row, int column){
        int counter = 0;
        for (int rowIncrease = -1; rowIncrease <= 1; rowIncrease++) {
            for (int columnIncreate = -1; columnIncreate <= 1; columnIncreate++) {
                if (isValid(linhas, row, column, rowIncrease, columnIncreate)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public static boolean isValid(List<String> linhas, int row, int col, int incRow, int incCol){
        char[] expectedLetters = "MAS".toCharArray();
        for(int i = 1; i <= expectedLetters.length; i++){
            int arrayRow = row + (incRow * i);
            int arrayCol = col + (incCol * i);

            if(arrayRow >= linhas.size() || arrayCol >= linhas.get(row).length() || arrayRow < 0 || arrayCol < 0 ){
                return false;
            }

            if(linhas.get(arrayRow).charAt(arrayCol) != expectedLetters[i - 1]){
                return false;
            }
        }
        return true;
    }
}
