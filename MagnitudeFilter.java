
/**
 * La classe MagnitudeFilter​Implemente l'interface Filter. 
 * Cette classe possède deux varibles d'instance pour
 * une magnitude minimum et maximum, un constructeur pour
 * initialiser ces variables, et une méthode satisfie qui
 * retourne true si la magnitude d'un séisme est compris
 * entre la magnitude maximum et manimum ou égale à l'une d'eux.
 * Toute fois cette méthode retourne false.
 * 
 * @author Boguhe Stephane
 * @version 1.01
 */
public class MagnitudeFilter implements Filter {
    
    private double min;
    private double max;
    
    public MagnitudeFilter(double minMag, double maxMag){
        this.min = minMag;
        this.max = maxMag;
    }
    
    
    @Override
    public boolean satisfie(QuakeEntry qe){
        
        if(qe.getMagnitude() >= this.min && qe.getMagnitude() <= this.max){
            return true;
        }
        return false;
    }

}
