
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class IntegerListZip {
    Node[] data;
    int max;

    private class Node {
        private Integer code;
        private String name;
        private Integer population;

        public Node(Integer code, String name, Integer population) {
            this.code = code;
            this.name = name;
            this.population = population;
        }

    }

    public IntegerListZip(String fileName) {
        data = new Node[10_000]; // the csv file is 9,675 lines
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            String line;
            Integer code = null;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                data[i++] = new Node(code, row[1], Integer.valueOf(row[2]));
            }
            max = i - 1; // max is number of zip nodes
        } catch (Exception e) {
            System.out.println(" file " + fileName + " not found");
        }
    }

    public String linearLookup(Integer code) {
        for (int i = 0; i < data.length; i++) {
            if (code.equals(data[i].code)) {
                return data[i].name;
            }
        }
        return null;
    }

    public String binaryLookup(Integer zip) {
        int min = 0;
        int mx = this.max;
        while (true) {
            int index = (min + mx) / 2;
            int compare = zip.compareTo(data[index].code);// returns 0 if they are equal
            if (compare == 0) {
                return data[index].name;
            }
            if (compare > 0 && index < mx) {
                min = index + 1;
                continue;
            }
            if (compare < 0 && index > min) {
                mx = index - 1;
                continue;
            }
            break;
        }
        return null;
    }

    public static void main(String[] args) {
        // Read the zip file and store it in the data field
        IntegerListZip zip = new IntegerListZip("C:\\path\\to\\file");

        // Define the number of iterations for warm-up and benchmarking
        int iterations = 1000;

        // Benchmark linear lookup for zip code 11115
        benchmarkLookup(zip, 11115, iterations, "linear");

        // Benchmark linear lookup for zip code 98499
        benchmarkLookup(zip, 98499, iterations, "linear");

        // Benchmark binary lookup for zip code 11115
        benchmarkLookup(zip, 11115, iterations, "binary");

        // Benchmark binary lookup for zip code 98499
        benchmarkLookup(zip, 98499, iterations, "binary");
    }

    // Method to benchmark lookup operation and print results
    private static void benchmarkLookup(IntegerListZip zip, int zipCode, int iterations, String lookupType) {
        // Warm-up phase
        for (int i = 0; i < iterations; i++) {
            if (lookupType.equals("linear")) {
                zip.linearLookup(zipCode);
            } else if (lookupType.equals("binary")) {
                zip.binaryLookup(zipCode);
            }
        }

        // Benchmark phase
        long timeStart = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            if (lookupType.equals("linear")) {
                zip.linearLookup(zipCode);
            } else if (lookupType.equals("binary")) {
                zip.binaryLookup(zipCode);
            }
        }
        long timeStop = System.nanoTime();

        // Output results for the lookup operation benchmark
        System.out.println(lookupType + " " + zipCode + ": " + zip.linearLookup(zipCode) + (timeStop - timeStart) / iterations + "ns");
    }

}