import java.util.*;
import java.io.*;

public class ProductCategoryProfitabilityAnalyzer {
    public static void main(String[] args) {
        //  Requirement: Use Scanner for input
        Scanner input = new Scanner(System.in); 
        File file;

        //  Requirement: Loop until valid path
        while (true) {
            System.out.print("Enter dataset file path (vgchartz-2024.csv): ");
            String path = input.nextLine();
            file = new File(path); //  Requirement: Use File class for validation

            if (file.exists() && file.isFile()) {
                break; 
            } else {
                System.out.println("Invalid file path. Please try again.");
            }
        }

        Map<String, Double> totalSalesMap = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>();

        // Requirement: Load dataset into memory
        try (Scanner reader = new Scanner(file)) {
            if (reader.hasNextLine()) reader.nextLine(); // Skip CSV Header

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                // Regex handles commas inside quotes (common in game titles)
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                
                if (data.length < 9) continue; 

                // Requirement: Group by product category (Genre)
                String category = data[3].trim(); 
                try {
                    // Using Column 8 for Total Sales from vgchartz-2024.csv
                    double sales = Double.parseDouble(data[8].trim()); 
                    
                    totalSalesMap.put(category, totalSalesMap.getOrDefault(category, 0.0) + sales);
                    countMap.put(category, countMap.getOrDefault(category, 0) + 1);
                } catch (NumberFormatException e) {
                    continue; 
                }
            }

            // Requirement: Perform analytics and display formatted results
            displayResults(totalSalesMap, countMap); 
        } catch (Exception e) {
            System.out.println("Error processing data: " + e.getMessage());
        } finally {
            // FIX: Scanner closed here to resolve the "resource leak" issue
            input.close(); 
        }
    }

    private static void displayResults(Map<String, Double> sales, Map<String, Integer> counts) {
        String mostProfitable = "";
        String leastProfitable = "";
        double maxSales = -1;
        double minSales = Double.MAX_VALUE;

        System.out.println("\n--- Category Analytics ---");
        for (String cat : sales.keySet()) {
            double total = sales.get(cat); // Requirement: Total sales per category
            double avg = total / counts.get(cat); // Requirement: Average sale per category
            
            System.out.printf("Category: %-15s | Total: %10.2f | Avg: %8.2f\n", cat, total, avg);

            // Identify Most and Least profitable
            if (total > maxSales) {
                maxSales = total;
                mostProfitable = cat;
            }
            if (total < minSales) {
                minSales = total;
                leastProfitable = cat;
            }
        }
        System.out.println("\nMost Profitable Category: " + mostProfitable);
        System.out.println("Least Profitable Category: " + leastProfitable);
    }
}