
/**
 * La classe​DepthFilter implement l'interface Filter. 
 * Cette classe possède deux varibles d'instance pour
 * une profondeur minimum et maximum, un constructeur pour
 * initialiser ces variables, et une méthode satisfie qui
 * retourne true si la profondeur du séisme est compris
 * entre la profondeur maximum et manimum ou égale à l'une d'eux.
 * Toute fois cette méthode retourne false.
 * 
 * @author Boguhe Stephane
 * @version 1.01
 */
public class DepthFilter implements Filter {
    private double minimum;
    private double maximum;
    private String name;
    
    public DepthFilter(double minDepth, double maxDepth, String filterName){
        this.minimum = minDepth;
        this.maximum = maxDepth;
        this.name = filterName;
    }
    
    @Override
    public boolean satisfie(QuakeEntry qe){
        if(qe.getDepth() >= this.minimum && qe.getDepth() <= this.maximum){
            return true;
        }
        return false;
    }

    @Override
    public String getName(){
        return this.name;
    }
}
