package dayone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DayOne {

    public static void main(String[] args) {

        String caminho = "src/dayone/input.txt";
        List<Integer> locationA = new ArrayList<>();
        List<Integer> locationB = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;

            while((linha = br.readLine()) != null){
                String[] locations = linha.split(" {3}");
                locationA.add(Integer.parseInt(locations[0]));
                locationB.add(Integer.parseInt(locations[1]));
            }

            Collections.sort(locationA);
            Collections.sort(locationB);

            System.out.println(findTotalDistance(locationA, locationB));
            System.out.println(findSimilarityScore(locationA,locationB));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static int findTotalDistance(List<Integer> listA, List<Integer> listB) {
        int totalDistance = 0;
        for (int i = 0; i < listA.size(); i++){
            totalDistance += Math.abs(listA.get(i) - listB.get(i));
        }
        return totalDistance;
    }

    public static int findSimilarityScore(List<Integer> listA, List<Integer> listB){
        int similarityScore = 0;
        for(Integer location: listA){
            Integer frequency = Collections.frequency(listB, location);
            similarityScore += location * frequency;
        }
        return similarityScore;
    }
}
