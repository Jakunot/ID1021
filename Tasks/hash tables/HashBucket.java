import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class HashBucket {
    Node[] data;
    int mod;
    int[] keys;
    int max;

    private class Node {
        private Integer code;
        private String name;
        private Integer population;
        private Node next;

        public Node(Integer code, String name, Integer population) {
            this.code = code;
            this.name = name;
            this.population = population;
            this.next = null;
        }
    }

    public HashBucket(String file, int mod) {
        this.mod = mod;
        data = new Node[mod];
        this.keys = new int[9676];
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            int code = 0;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                code = Integer.parseInt(row[0].replaceAll("\\s", ""));
                insert(code, new Node(code, row[1], Integer.valueOf(row[2])));
                this.keys[i++] = code;

            }
            max = i - 1; // max is number of zip nodes
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    private void insert(Integer code, Node entry) {
        Integer key = code % this.mod; // convert the zip code to a simple hash using modulo
        Node current = this.data[key];
        Node prev = null;

        while (current != null) {
            if (code.equals(current.code)) {
                current = current.next;
                break;
            }
            prev = current;
            current = current.next;
        }
        if (prev != null) {
            prev.next = entry;
        } else {
            data[key] = entry;
        }
        entry.next = current;
    }

    public String lookup(Integer key) {
        Integer index = key % this.mod;
        Node current = data[index];
        while (current != null) {
            if (key.equals(current.code))
                return current.name;
            current = current.next;
        }
        return null;
    }

    public void collisions(int mod) {
        int[] data = new int[mod];
        int[] cols = new int[10];
        for (int i = 0; i < max; i++) {
            Integer index = keys[i] % mod;
            cols[data[index]]++;
            data[index]++;
        }
        System.out.print(mod);
        for (int i = 0; i < 10; i++) {
            System.out.print("\t" + cols[i]);
        }
        System.out.println();
    }

    public int nrOfCollisions() {
        Set<Integer> store = new HashSet<Integer>();
        int count = 0;
        for (int i = 0; i < keys.length; i++) {

            if (store.add(keys[i] % this.mod) == false) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int mod = 30000;

        // Initialize HashBucket with the CSV file and mod value
        HashBucket hash = new HashBucket("C:C:\\path\\to\\file", mod);

        // Output the number of collisions for the given mod value
        System.out.println("Number of collisions for mod = " + mod + ": " + hash.nrOfCollisions());

        // Analyze and print collisions for each bucket
        hash.collisions(mod);

        // Benchmark the lookup operation for zip code 11115
        benchmark(hash, 11115, "111 15");

        // Benchmark the lookup operation for zip code 98499
        benchmark(hash, 98499, "984 99");
    }

    // Method to benchmark the lookup operation and print results
    private static void benchmark(HashBucket hash, int zipCode, String zipCodeStr) {
        int warmUpIterations = 1000;
        int benchmarkIterations = 1000;

        // Warm-up phase
        for (int i = 0; i < warmUpIterations; i++) {
            hash.lookup(zipCode);
        }

        // Benchmark phase
        long timeStart = System.nanoTime();
        for (int i = 0; i < benchmarkIterations; i++) {
            hash.lookup(zipCode);
        }
        long timeStop = System.nanoTime();

        // Calculate and print benchmark results
        System.out.println("Lookup " + zipCodeStr + ": " + hash.lookup(zipCode) +
                ", Time per operation: " + (timeStop - timeStart) / benchmarkIterations + "ns");
    }
}