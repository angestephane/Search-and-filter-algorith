
/**
 * La classe DistanceFilter​Implemente l'interface Filter. 
 * Cette classe possède deux varibles d'instance pour
 * la localisation et la dispance maximum, un constructeur pour
 * initialiser ces variables, et une méthode satisfie qui
 * retourne true si la distance du séisme est inférieur
 * à la distance maximum spécifiée.
 * Toute fois cette méthode retourne false.
 * 
 * @author Boguhe Stephane
 * @version 1.01
 */
public class DistanceFilter implements Filter {
    private Location localisation;
    private double distanceMax;
    private String name;
    
    public DistanceFilter(Location loc, double distMax, String filterName){
        this.localisation = loc;
        this.distanceMax = distMax;
        this.name = filterName;
    }
    
    
    @Override
    public boolean satisfie(QuakeEntry qe){
        
        if(qe.getLocation().distanceTo(this.localisation) < this.distanceMax){
            return true;
        }
        return false;
    }

    @Override
    public String getName(){
        return this.name;
    }
}
