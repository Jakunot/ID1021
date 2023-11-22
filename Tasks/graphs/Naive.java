
public class Naive {

    public static void main(String [] args) {
        //bench(  "Stockholm","Malmö", 557);
        benchmark(  "Malmö","Göteborg", 557);
        benchmark(  "Göteborg","Stockholm", 557);
        benchmark(  "Malmö","Stockholm", 557);
        benchmark(  "Stockholm","Sundsvall", 557);
        benchmark(  "Stockholm","Umeå", 557);
        benchmark(  "Göteborg","Sundsvall", 557);
        benchmark(  "Sundsvall","Umeå", 557);
        benchmark(  "Umeå","Göteborg", 1100);
        benchmark(  "Göteborg","Umeå", 1100);
    }

    public static void benchmark(String from, String to, Integer max){
        Map map = new Map("C:\\Users\\jabku\\IdeaProjects\\Graphs\\src\\trains.csv");

        long t0 = System.nanoTime();
        Integer dist = shortest(map.lookup(from), map.lookup(to), max);
        long time = (System.nanoTime() - t0)/1_000_000;

        System.out.println("From:" + from + "\t" + "To:" + to + "\t" + "shortest: " + dist + " min (" + time + " ms)");
    }
    private static Integer shortest(City from, City to, Integer max) {
        if (max < 0)
            return null;
        if (from == to)
            return 0;
        Integer shortestDistance = null;
        for (int i = 0; i < from.neighbors.length; i++) {
            if (from.neighbors[i] != null) {
                Connection conn = from.neighbors[i];
                Integer candidate = shortest(conn.city, to, max - conn.distance);
                if(candidate != null) {
                    if (shortestDistance == null)
                        shortestDistance = conn.distance + candidate;
                    else
                        shortestDistance = Math.min(conn.distance + candidate, shortestDistance);
                }
            }
        }
        return shortestDistance;
    }
}