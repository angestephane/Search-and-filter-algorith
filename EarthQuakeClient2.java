import java.util.*;
import edu.duke.*;

/**
 * La Classe EarthQuakeClient2 permet d'effectuer
 * les mêmes filtre réalisés dans la classe 
 * EarthQuakeClient mais plus simplement. Avec 
 * moins de code.
 * 
 * @author Boguhe Stephane 
 * @version 1.01
 */



public class EarthQuakeClient2 {
    
    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f){
        ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();
        
        for(QuakeEntry qe : quakeData){
            if(f.satisfie(qe)){
               result.add(qe);
            }
        }
        return result;
    }
    
    public void quakesWithFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        
        String dataSource = "../data/nov20quakedatasmall.atom";
        //String source = "../data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(dataSource);
        
        System.out.println(list.size() + " Data lues");
        
        Filter filtre1 = new MagnitudeFilter(4.0, 5.0);
        Filter filtre2 = new DepthFilter(-35000.0, -12000.0);
        
        ArrayList<QuakeEntry> resultFilter = filter(list, filtre1);
        ArrayList<QuakeEntry> finalResult = filter(resultFilter, filtre2);
        
        for(QuakeEntry qe : finalResult){
            System.out.println(qe);
        }
        
        System.out.println(finalResult.size() + "données trouvées");
    }
    
    

}