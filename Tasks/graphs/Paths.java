
public class Paths {
    City[] path;
    int sp;

    public Paths() {
        path = new City[52];
        sp = 0;
    }

    public static void main(String [] args) {
        benchmark(  "Malmö","Göteborg", 1000);
        benchmark(  "Göteborg","Stockholm", 1000);
        benchmark(  "Malmö","Stockholm", 1000);
        benchmark(  "Stockholm","Sundsvall", 1000);
        benchmark(  "Stockholm","Umeå", 1000);
        benchmark(  "Göteborg","Sundsvall", 1000);
        benchmark(  "Sundsvall","Umeå", 1000);
        benchmark(  "Umeå","Göteborg", 10000);
        benchmark(  "Göteborg","Umeå", 10000);
        benchmark(  "Malmö","Kiruna", 10000);
    }

    public static void benchmark(String from, String to, Integer max){
        Map map = new Map("C:\\Users\\jabku\\IdeaProjects\\Graphs\\src\\trains.csv");
        Paths path = new Paths();

        float t0 = System.nanoTime();
        Integer dist = path.shortest(map.lookup(from), map.lookup(to), max);
        float time = (System.nanoTime() - t0)/1_000_000;

        System.out.println("From:" + from + "\t" + "To:" + to + "\t" + "shortest: " + dist + " min (" + time + " us)");
    }

    private Integer shortest(City from, City to, Integer max) {
        if (max != null && max < 0)
            return null;

        if (from == to)
            return 0;

        for (int i = 0; i < sp; i++) {
            if (path[i] == from)
                return null;
        }
        path[sp++] = from;

        Integer shortestDistance = null;
        for (int i = 0; i < from.neighbors.length; i++){
            if (from.neighbors[i] != null){
                Connection conn = from.neighbors[i];
                Integer candidate;
                if (max == null)
                    candidate = shortest(conn.city, to, max);
                else
                    candidate = shortest(conn.city, to, max - conn.distance);

                if(candidate != null) {
                    if (shortestDistance == null) {
                        shortestDistance = conn.distance + candidate;
                    }
                    else {
                        shortestDistance = Math.min(conn.distance + candidate, shortestDistance);
                    }
                    max = shortestDistance;
                }
            }
        }

        path[sp--] = null;
        return shortestDistance;
    }
}