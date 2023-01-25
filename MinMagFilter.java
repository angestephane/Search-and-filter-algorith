
/**
 * Classe permettant de filtrer les séismes
 * sur base de leur magnitude.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinMagFilter implements Filter {
    
    private double magMin;
    
    public MinMagFilter(double min){
        this.magMin = min;
    }
    
    @Override
    public boolean satisfie(QuakeEntry qe){
        return qe.getMagnitude() >= this.magMin;
    }

}
