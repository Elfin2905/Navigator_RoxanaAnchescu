import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class City {
    private String name;
    private double latitude;
    private double longitude;
    private ArrayList<Connection> connections;
    private List<City> routeCities = new ArrayList<>();

    public City(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.connections = new ArrayList<>();
    }

    public City (String name) {
        this.name = name;
        this.connections = new ArrayList<>();
    }

    
    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCity() {
        return this.name;
    }

    public ArrayList<Connection> getConnections() {
        return connections;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void addConnection(City cityToConnect) {
        if (this.equals(cityToConnect)) {
            System.out.println("Eine Stadt kann nicht mit sich selbst verknüpft werden.");
            return;
        }

        Connection connection = new Connection(this, cityToConnect);
        this.connections.add(connection);
        cityToConnect.getConnections().add(connection);
    }

    public Route getRouteTo(City destination) {
        Queue<Route> queue = new LinkedList<>();
        Route inizialRoute = new Route(this);
        queue.add(inizialRoute);

        while (!queue.isEmpty()) {
            Route currentRoute = queue.poll();
            City currentCity = currentRoute.getLastCity();

            if (currentCity.equals(destination)) {
                return currentRoute;
            }

            for (Connection connection : currentCity.getConnections()) {
                City nextCity = connection.getOtherCity(currentCity);
                if (!currentRoute.containsCity(nextCity)) {
                    Route newRoute = new Route(currentRoute);
                    newRoute.addCity(nextCity);
                    queue.add(newRoute);
                }
            }
        }
        return null;
    }

    

    public String toString() {
        return name + " " + latitude + " " + longitude;
    }
}
