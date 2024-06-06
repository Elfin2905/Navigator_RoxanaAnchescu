import java.util.ArrayList;
import java.util.List;

public class Route {
    private ArrayList<City> routeCities;
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

    public boolean containsCity(City city) {
        return this.routeCities.contains(city);
    }

    public City getLastCity() {
        if (this.routeCities.isEmpty()) {
            return null;
        }
        return this.routeCities.get(this.routeCities.size() - 1);
    }

    public City getLastcity() {
        if (this.routeCities.isEmpty()) {
            return null;
        }
        return this.routeCities.get(this.routeCities.size() - 1);
    }
    
    public City addCity() {
        return this.routeCities.get(this.routeCities.size() - 1);
    }

    public void addCity(City city) {
        this.routeCities.add(city);
    }
    
    public double getTotalDistance() {
        return totalDistance;
    }

    //versuchen ob es mit getCity oder getName funktioniert
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.routeCities.size(); i++) {
            sb.append(this.routeCities.get(i).getName());
            if (i < this.routeCities.size() - 1) {
                sb.append(" - ");
            }
         }
        sb.append(" ; Distanz:").append(Math.round(this.totalDistance)).append(" km");
        return sb.toString();

          }

          public static Route getShortestRoute(City start, City destination) {
            List<Route> allPossibleRoutes = new ArrayList<>();
            Route inizialRoute = new Route();
            inizialRoute.appendCity(start, null);
            addAllPossibleRoutes(allPossibleRoutes, inizialRoute, destination);

            //Sortieren all Possible Routes
            List<Route> routesOrderByDistance = new ArrayList<>(allPossibleRoutes);

            //Rückgabe der kürzesten Route
            return routesOrderByDistance.get(0);
          }

          public static List<Route> insertionSort(List<Route> routes) {
            for (int i = 1; i < routes.size(); i++) {
              Route key = routes.get(i);
              int j = i - 1;
              while (j >= 0 && routes.get(j).getTotalDistance() > key.getTotalDistance()) {
                routes.set(j + 1, routes.get(j));
                j = j - 1;
              }
              routes.set(j + 1, key);
            }
            return routes;
          }

          public static void addAllPossibleRoutes(List<Route> allPossibleRoutes, Route currentRoute, City destination) {
            City currentCity = currentRoute.getLastcity();
            City nextCity = currentRoute.getLastcity();
            Route continuedRoute = new Route(currentRoute);
            if (!currentRoute.containsCity(nextCity)) {
                Route newRoute = new Route(currentRoute);
                continuedRoute.appendCity(nextCity, null);
                addAllPossibleRoutes(allPossibleRoutes, currentRoute, destination);
                return;

            }
            List<Connection> possibleNextCitis = new ArrayList<>();
            for (Connection nextConnection : possibleNextCitis) {
              if (!currentRoute.containsCity(nextCity)) {
                Route newRoute = new Route(currentRoute);
                newRoute.appendCity(nextCity, nextConnection);
                addAllPossibleRoutes(allPossibleRoutes, continuedRoute, destination);
              }
            }
          }
        }
        
    
