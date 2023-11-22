public class City {
    String name;
    Connection[] neighbors;
    int cities;

    public City(String name){
        this.name = name;
        neighbors = new Connection[51];
        int cities = 0;
    }

    public void addConnection(City city, int distance){
        neighbors[cities] = new Connection(city, distance);
        cities++;
    }
}