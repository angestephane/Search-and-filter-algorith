
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
    
    public DepthFilter(double minDepth, double maxDepth){
        this.minimum = minDepth;
        this.maximum = maxDepth;
    }
    
    @Override
    public boolean satisfie(QuakeEntry qe){
        if(qe.getDepth() >= this.minimum && qe.getDepth() <= this.maximum){
            return true;
        }
        return false;
    }

}
