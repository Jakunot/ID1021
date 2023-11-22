import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Hash {
    Node[] data;
    int mod;
    int[] keys;
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

    public Hash(String file, int mod) {
        this.mod = mod;
        data = new Node[mod];
        this.keys = new int[9675];
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            int code = 0;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                code = Integer.parseInt(row[0].replaceAll("\\s", ""));
                this.keys[i++] = code;
                insert(code, new Node(code, row[1], Integer.valueOf(row[2])));
            }
            max = i - 1;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    private void insert(Integer code, Node entry) {
        Integer key = code % this.mod;
        while (this.data[key] != null) {
            key++;
        }
        this.data[key] = entry;
    }

    public String lookup(Integer code) {
        Integer key = code % this.mod;
        while (!this.data[key].code.equals(code)) {
            key++;
        }
        return this.data[key].name;
    }

    public void countSteps() {
        int maxSteps = 0;
        double totalSteps = 0;
        int totalLookups = 0;

        for (int i = 0; i < this.keys.length; i++) {
            int code = this.keys[i];
            int key = code % this.mod;
            int steps = 0;

            while (!this.data[key].code.equals(code)) {
                key++;
                steps++;
                totalSteps++;
            }

            if (steps > 0) {
                System.out.println("For code " + code + ", steps = " + steps);
                totalLookups++;
            }

            maxSteps = Math.max(maxSteps, steps);
        }

        System.out.println("Max steps: " + maxSteps);
        System.out.println("Average steps: " + totalSteps / totalLookups);
        System.out.println("Average steps per lookup: " + totalSteps / this.keys.length);
        System.out.println("Total number of lookups: " + totalLookups);
    }

    public static void main(String[] args) {
        int mod = 40000;
        Hash hash = new Hash("C:\\path\\to\\file", mod);

        hash.countSteps();

        int k = 1000;

        benchmark(hash, 11115, k);
        benchmark(hash, 98499, k);
    }

    //The method then measures the time taken for the lookup operation over a specified number of iterations
    private static void benchmark(Hash hash, int zipCode, int iterations) {
        // Warm-up for better benchmark results
        for (int i = 0; i < iterations; i++) {
            hash.lookup(zipCode);
        }

        long timeStart = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            hash.lookup(zipCode);
        }
        long timeStop = System.nanoTime();

        System.out.println("Lookup " + zipCode + ": " + hash.lookup(zipCode) + ", Time per operation: " + (timeStop - timeStart) / iterations + "ns");
    }

}