/**
 * Midterm Lab 3: CSV Dataset Processing
 * Student: LANCETA, ALEXANDER D.
 * Machine Problems: MP07, MP08, MP09
 */

import java.io.*;
import java.util.*;

public class MP_Solver {
    // Record class to structure dataset
    static class Record {
        String name, exam, result;
        int score;
        Record(String n, String e, String res, int s) {
            this.name = n; this.exam = e; this.result = res; this.score = s;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter dataset file path: ");
        String path = sc.nextLine().replace("\"", "");
        List<Record> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                // SKIP metadata: Only process lines containing result keywords
                if (!line.contains("PASS") && !line.contains("FAIL")) continue;

                // Split commas ignoring those inside quotes
                String[] p = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                
                if (p.length >= 8) {
                    String name = p[0].replace("\"", "").trim();
                    String exam = p[3].trim();
                    String result = p[7].trim();
                    int score = 0;
                    try { score = Integer.parseInt(p[6].trim()); } catch (Exception e) {}
                    records.add(new Record(name, exam, result, score));
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // MP07: Sort and Print (Tabular Format)
        records.sort(Comparator.comparing(r -> r.exam));
        System.out.println("\n[MP07] Sorted by Exam Name:");
        for (int i = 0; i < records.size(); i++) {
            Record r = records.get(i);
            System.out.printf("%2d. %-45s | %s%n", (i + 1), r.exam, r.name);
        }

        // MP08: Filter
        System.out.println("\n[MP08] Candidates who passed:");
        for(Record r : records) {
            if("PASS".equalsIgnoreCase(r.result)) System.out.println("- " + r.name);
        }

        // MP09: Stats
        double avg = records.stream().mapToInt(r -> r.score).average().orElse(0.0);
        System.out.printf("\n[MP09] Dataset Statistics - Average Score: %.2f%n", avg);
        sc.close();
    }
}