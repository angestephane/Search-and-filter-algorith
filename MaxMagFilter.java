
/**
 * Classe permettant de filtrer les séismes
 * sur base de leur magnitude.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MaxMagFilter implements Filter {
    
    private double magMin;
    
    public MaxMagFilter(double min){
        this.magMin = min;
    }
    
    @Override
    public boolean satisfie(QuakeEntry qe){
        return qe.getMagnitude() >= this.magMin;
    }

}
