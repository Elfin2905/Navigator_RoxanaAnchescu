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

    //Die Methode calculateDistance berechnet die Distanz zwischen zwei Städten.
    public double calculateDistance(City city1, City city2) {
        double lat1 = city1.getLatitude();
        double lon1 = city1.getLongitude();
        double lat2 = city2.getLatitude();
        double lon2 = city2.getLongitude();
        double earthRadius = 6371; // in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = degreesToRadians(lat1);
        lat2 = degreesToRadians(lat2);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;

    }
    
    private double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
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
