
public class App {
    public static void main(String[] args) throws Exception {
        City ingolstadt = new City("Ingolstadt", 48.764001378240835, 11.42625484665524);
        City muenchen = new City("München", 48.13891855227781, 11.577266137987793);
        City nuernberg = new City("Nürnberg", 49.45061728516064, 11.076253152617218);
        City ulm = new City("Ulm", 48.40203485876449, 9.976324958249457);
        City stuttgart = new City("Stuttgart", 48.77593813959718, 9.17688481609677);
        City augsburg = new City("Augsburg", 48.3684765834842, 10.89671693335146);
        City regensburg = new City("Regensburg", 49.01662121288669, 12.092787603517024);
        City wuerzburg = new City("Würzburg", 49.79296114327296, 9.945390533206055);

        

        //System.out.println(ingolstadt);
        //System.out.println(muenchen);
        //System.out.println(nuernberg);
        // System.out.println(ulm);
        //System.out.println(stuttgart);
        //System.out.println(augsburg);
        //System.out.println(regensburg);
        //System.out.println(wuerzburg);

        Connection connection1 = new Connection(ingolstadt, muenchen);
        Connection connection2 = new Connection(ingolstadt, nuernberg);
        Connection connection3 = new Connection(ingolstadt, regensburg);
        Connection connection4 = new Connection(ingolstadt, augsburg);
        Connection connection5 = new Connection(muenchen, augsburg);
        Connection connection6 = new Connection(ulm, augsburg);
        Connection connection7 = new Connection(stuttgart, ulm);
        Connection connection8 = new Connection(stuttgart, nuernberg);
        Connection connection9 = new Connection(stuttgart, wuerzburg);
        Connection connection10 = new Connection(regensburg, muenchen);
        Connection connection11 = new Connection(regensburg, nuernberg);
        Connection connection12 = new Connection(nuernberg, ulm);

         // Ausgabe der Distanzen
         //System.out.println("Distance between Ingolstadt and München: " + connection1.getDistanceInKm() + " km");
         //System.out.println("Distance between Ingolstadt and Nürnberg: " + connection2.getDistanceInKm() + " km");
         //System.out.println("Distance between Ingolstadt and Regensburg: " + connection3.getDistanceInKm() + " km");
         //System.out.println("Distance between Ingolstadt and Augsburg: " + connection4.getDistanceInKm() + " km");
         //System.out.println("Distance between München and Augsburg: " + connection5.getDistanceInKm() + " km");
         // System.out.println("Distance between Ulm and Augsburg: " + connection6.getDistanceInKm() + " km");
         //System.out.println("Distance between Stuttgart and Ulm: " + connection7.getDistanceInKm() + " km");
         //System.out.println("Distance between Stuttgart and Nürnberg: " + connection8.getDistanceInKm() + " km");
         //System.out.println("Distance between Stuttgart and Würzburg: " + connection9.getDistanceInKm() + " km");
         //System.out.println("Distance between Regensburg and München: " + connection10.getDistanceInKm() + " km");
         //System.out.println("Distance between Regensburg and Nürnberg: " + connection11.getDistanceInKm() + " km");
         //System.out.println("Distance between Nürnberg and Ulm: " + connection12.getDistanceInKm() + " km");


        ingolstadt.addConnection(muenchen);
        ingolstadt.addConnection(nuernberg);
        ingolstadt.addConnection(regensburg);
        ingolstadt.addConnection(augsburg);
        muenchen.addConnection(augsburg);
        ulm.addConnection(augsburg);
        stuttgart.addConnection(ulm);
        stuttgart.addConnection(nuernberg);
        stuttgart.addConnection(wuerzburg);
        regensburg.addConnection(muenchen);
        regensburg.addConnection(nuernberg);
        nuernberg.addConnection(ulm);

        System.out.println("Beste Route:"+stuttgart.getRouteTo(regensburg).toString());


        
  }    	
}



  

