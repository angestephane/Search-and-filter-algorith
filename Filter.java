
/**
 * Cette interface dispose d'une méthode.
 * La méthode filter permettra d'éviter
 * une duplication du code.
 * 
 * @author Boguhe Ange Stephane 
 * @version 1.01
 */
public interface Filter {
    
    public boolean satisfie(QuakeEntry qe);
    public String getName();

}
