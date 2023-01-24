import java.util.*;
import edu.duke.*;

/**
 * Cette classe effectue un filtre
 * sur les location de séisme
 * d'une position spécifique.
 *
 * @author Boguhe Ange Stephane
 * @version 1.0 
 */
public class ClosestQuakes {
    
    /**
     * Cette methode détermine les "howMany"
     * séisme les plus proche de la localisation
     * current
     */
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();
        
        for(int i=0; i<howMany; i++){
            int minIndex=0;
            
            for(int k=1; k<copy.size(); k++){
               QuakeEntry qe = copy.get(k);
               Location loc = qe.getLocation();
               if(loc.distanceTo(current) < copy.get(minIndex).getLocation().distanceTo(current)){
                   minIndex = k;
                }
            }
            result.add(copy.get(minIndex));
            copy.remove(minIndex);
        }
        
        return result;
        
    }
    
    /**
     * Retourne une liste de séisme  
     * située à moins de "distMax"
     * de la localisation "from".
     */ 
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {      
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    /**
     * Lit les données depuis une liste de 
     * séisme, les trie par ordre croissant.
     * puis affiche un nombre precis de 
     * séisme proche d'une localisation 
     * spécifique
     */
    public void findClosestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        
        String source = "../../data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        
        Location jakarta = new Location(-6.211, 106.845);
        //Location casa = new Location(33.5950627, -7.6187768);
        
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println(list.size() + " données lues");
        
        ArrayList<QuakeEntry> close = getClosest(list, jakarta, 3);
        for(int k = 0; k<close.size(); k++){
            QuakeEntry qe = close.get(k);
            double distanceEnMettre = jakarta.distanceTo(qe.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceEnMettre/1000,qe);
        }
        System.out.println("Nombre trouvé: " + close.size());
        
        
        
    }
    
    /**
     * Affiche les Seismes situés
     * à moins de 300km d'une position spécifique. 
     */
    public void closeTo() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../../data/nov20quakedata.atom";
        String sourceSmallData = "../../data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(sourceSmallData);
        System.out.println("# quakes read: " + list.size());
        
        //Durham, NC
        //Location city = new Location(35.988, -78.907);
        //Bridgeport, CA
        Location city = new Location(38.17,-118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 300*1000, city);
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }
        System.out.println(close.size()+ " tremblements de tèrre qui match avec ce critère");
    }

}
