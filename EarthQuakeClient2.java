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
    
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        
        String dataSource = "../data/nov20quakedatasmall.atom";
        
        ArrayList<QuakeEntry> list = parser.read(dataSource);
        
        System.out.println(list.size() + " taille des données");
        
        MatchAllFilter maf = new MatchAllFilter();
        
        //Filtres
        Filter filtre1 = new MagnitudeFilter(0.0, 2.0); // Filtre selon la magnitude du séisme
        //Ajout du filtre au tableau de filtre
        maf.addFilter(filtre1);
        
        Filter filtre2 = new DepthFilter(-100000.0, -10000.0); // Filtre selon la profondeur du séisme
        //Ajout du filtre au tableau de filtre
        maf.addFilter(filtre2);
        
        Filter filtre4 = new PhraseFilter("any", "a"); //Filtre en prenant en compte le mot clé a
        //Ajout du filtre au tableau de filtre
        maf.addFilter(filtre4);
        
        ArrayList<QuakeEntry> fillterResult = filter(list, maf);
        
        for(QuakeEntry qe : fillterResult){
            System.out.println(qe);
        }
        
    }
    
    public void quakesWithFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        
        String dataSource = "../data/nov20quakedatasmall.atom";
        //String source = "../data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(dataSource);
        
        System.out.println(list.size() + " Data lues");
        
        Location japan = new Location(35.42, 139.43);
        
        Filter filtre1 = new MagnitudeFilter(4.0, 5.0); // Filtre selon la magnitude du séisme
        Filter filtre2 = new DepthFilter(-35000.0, -12000.0); // Filtre selon la profondeur du séisme
        Filter filtre3 = new DistanceFilter(japan, 10000*1000); //Filtre selon une position
        Filter filtre4 = new PhraseFilter("end", "Japan"); //Filtre en prenant en compte le mot clé Japan
        
        
        ArrayList<QuakeEntry> firstFilter = filter(list, filtre3);
        ArrayList<QuakeEntry> finalResult = filter(firstFilter, filtre4);
        
        for(QuakeEntry qe : finalResult){
            System.out.println(qe);
        }
        
        System.out.println(finalResult.size() + "données trouvées");
    }
    
    

}
