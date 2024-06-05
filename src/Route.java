import java.util.ArrayList;
import java.util.List;

public class Route {
    private ArrayList<City> routeCities = new ArrayList<>();
    private double totalDistance;

    public Route(){
        this.routeCities = new ArrayList<>();
        this.totalDistance = 0;
    }

    public Route(City startCity) {
        this();
        this.routeCities.add(startCity);
    }

    public Route(Route other){
        this.routeCities = new ArrayList<>(other.routeCities);
        this.totalDistance = other.totalDistance;
    }

    public void appendCity(City city, Connection connection) {
        this.routeCities.add(city);
        if (connection != null) {
            this.totalDistance += connection.getDistanceInKm();
        }
    }

    public void printConnections() {
        for (int i = 0; i < routeCities.size() - 1; i++) {
            City city1 = routeCities.get(i);
            City city2 = routeCities.get(i + 1);
            System.out.println("Verbindung von " + city1.getName() + " nach " + city2.getName());
        }
    }

    public City getLastCity() {
        if (!routeCities.isEmpty()) {
            return routeCities.get(routeCities.size() - 1);
        } else {
            return null;
        }
    }

    //Check ob die Stadt in der Route enthalten ist
    public boolean containsCity(City city) {
        return this.routeCities.contains(city);
    }

    public City getLasCity() {
        return this.routeCities.get(this.routeCities.size() - 1);
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public ArrayList<City> getRouteCities() {
        return routeCities;
    }

    public void setRouteCities(ArrayList<City> routeCities) {
        this.routeCities = routeCities;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void addCity(City city) {
        this.routeCities.add(city);
    }

    public static Route getShortestRoute(City start,City destination) {
        List<Route> allPossibleRoutes = new ArrayList<>();
        Route inizialRoute = new Route();
        inizialRoute.appendCity(start, null);  
        addAllRoutes(allPossibleRoutes, inizialRoute, destination);
        Route shortestRoute = null;
        for (Route route : allPossibleRoutes) {
            if (shortestRoute == null || route.getTotalDistance() < shortestRoute.getTotalDistance()) {
                shortestRoute = route;
            }
        }
        return shortestRoute;
    }

    //Methode Hinzufügen aller möglichen Routen
    private static void addAllRoutes(List<Route> allPossibleRoutes, Route currentRoute, City destination) {
        City currentCity = currentRoute.getLastCity();
        if (currentCity.equals(destination)) {
            allPossibleRoutes.add(currentRoute);
            return;
        }

        List<Connection> possibleNextCities = currentCity.getConnections();
        for (Connection nextConnection : possibleNextCities) {
            City nextCity = nextConnection.getOtherCity(currentCity);
            if (!currentRoute.containsCity(nextCity)) {
                Route continuedRoute = new Route(currentRoute);
                addAllRoutes(allPossibleRoutes, continuedRoute, nextCity, destination, nextConnection);
            }
        }  
    }

    private static void addAllRoutes(List<Route> allPossibleRoutes, Route currentRoute, City destination, City nextCity, Connection nextConnection) {
        currentRoute.appendCity(nextCity, nextConnection); //Füge die Stadt und die Verbindung zur Route hinzu . Wird habe die methode aufgerufen
        City currentCity = currentRoute.getLastCity();
        if (currentCity.equals(destination)) {
            allPossibleRoutes.add(new Route(currentRoute));
            return;
        }
    
        //Füge alle möglichen Routen hinzu
        List<Connection> possibleNextCities = currentCity.getConnections();
        for (Connection nextConnectionInLoop : possibleNextCities) {
            City nextCityInLoop = nextConnectionInLoop.getOtherCity(currentCity);
            if (!currentRoute.containsCity(nextCityInLoop)) {
                Route continuedRoute = new Route(currentRoute);
                addAllRoutes(allPossibleRoutes, continuedRoute, destination, nextCityInLoop, nextConnectionInLoop);
            }
        }
    }

    public static void printRoute(Route route) {
        System.out.println("Gesamtdistanz: " + route.getTotalDistance() + " km");
        for (City city : route.getRouteCities()) {
            System.out.println(city.getName());
        }
    }

    public String toString() {
        StringBuilder routeString = new StringBuilder();
        for (City city : routeCities) {
            routeString.append(city.getCity()).append(" - ");
        }
        routeString.setLength(routeString.length() - 3); // Entfernt das letzte " - "
        routeString.append("; Distanz: ").append(totalDistance).append(" km");
        return routeString.toString();
    }
}
