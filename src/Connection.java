import java.util.List;

public class Connection {
    private City city1;
    private City city2;
    private final double distanceInKm;

    public Connection(City city1, City city2) {
        this.city1 = city1;
        this.city2 = city2;
        this.distanceInKm = calculateDistance(city1, city2);
    }

    public City getCity1() {
        return city1;
    }

    public City getCity2(){
        return city2;
    }

    public double getDistanceInKm() {
        return distanceInKm;
    }

    public double calculateDistance(City city1, City city2) {
        // Your calculation logic here
        // For example, if City objects have latitude and longitude properties, you might calculate the distance like this:
        double lat1 = city1.getLatitude();
        double lon1 = city1.getLongitude();
        double lat2 = city2.getLatitude();
        double lon2 = city2.getLongitude();
    
        // Die Methode calculateDistanceInKm berechnet die Entfernung zwischen zwei Städten in Kilometern. 
        // Die Methode verwendet die Haversine-Formel, um die Entfernung zwischen zwei Punkten auf der Erdoberfläche zu berechnen.
        // Die Methode nimmt zwei City-Objekte als Parameter und gibt die Entfernung zwischen den beiden Städten in Kilometern zurück.
        // Die Methode verwendet die Längen- und Breitengradinformationen der beiden City-Objekte, um die Entfernung zu berechnen.
        double earthRadius = 6371.0; // kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = earthRadius * c;
    
        return distance;
    }

    //Die Methode degreesToRadians wandelt einen Winkel von Grad in Radiant um.
    private double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }

    public static void printConnections(List<City> cities, List<Connection> connections) {
    for (City city : cities) {
        System.out.println(city.getName() + " (" + city.getLatitude() + ", " + city.getLongitude() + ")");
        for (Connection connection : connections) {
            System.out.println(connection.getCity1().getName() + " - " + connection.getCity2().getName() + ": " + connection.getDistanceInKm() + " km");
        }
    }
}

    //Die Methode getOtherCity gibt die andere Stadt in der Verbindung zurück.
    // Sie überprüft, welche der beiden Städte mit der übergebenen Stadt übereinstimmt und gibt die andere Stadt zurück.
    // Wenn die übergebene Stadt nicht Teil der Verbindung ist, wird eine IllegalArgumentException ausgelöst.
    public City getOtherCity(City city) {
        if (city1 == city) {
            return city2;
        } else if (city2 == city) {
            return city1;
        } else {
            throw new IllegalArgumentException("City is not part of this connection");
        }
    }
        

    
}
