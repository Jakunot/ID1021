import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ZipIndexArray {
    Node[] data;

    private class Node {
        Integer code;
        String name;
        Integer population;

        public Node(Integer code, String name, Integer population) {
            this.code = code;
            this.name = name;
            this.population = population;
        }
    }

    public ZipIndexArray(String file) {
        this.data = new Node[98500]; // 984 99 is the last line in the file we want to read
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            Integer code = null;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                data[code] = new Node(code, row[1], Integer.valueOf(row[2]));
                // row[0] is code, row[1] is name and row[2] is population
            }
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    public String lookup(Integer zipCode) {
        return this.data[zipCode].name;
    }

    public static void main(String[] args) {
        // Create a ZipIndexArray instance and load zip code data
        ZipIndexArray zip = new ZipIndexArray("C:\\path\\to\\file");

        // Define the number of iterations for warm-up and benchmarking
        int iterations = 1000;

        // Benchmark lookup for zip code 11115
        benchmarkLookup(zip, 11115, iterations);

        // Benchmark lookup for zip code 98499
        benchmarkLookup(zip, 98499, iterations);
    }

    // Method to benchmark lookup operation and print results
    private static void benchmarkLookup(ZipIndexArray zip, int zipCode, int iterations) {
        // Warm-up phase
        for (int i = 0; i < iterations; i++) {
            zip.lookup(zipCode);
        }

        // Benchmark phase
        long timeStart = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            zip.lookup(zipCode);
        }
        long timeStop = System.nanoTime();

        // Output results for the lookup operation benchmark
        System.out.println("Lookup " + zipCode + ": " + zip.lookup(zipCode) + (timeStop - timeStart) / iterations + "ns");
    }


}