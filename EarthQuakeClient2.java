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
        
        //String dataSource = "../data/nov20quakedatasmall.atom";
        String source = "../data/nov20quakedata.atom";
        
        ArrayList<QuakeEntry> list = parser.read(source);
        
        System.out.println(list.size() + " taille des données");
        
        MatchAllFilter maf = new MatchAllFilter();
        
        //Filtres
        Filter filtre1 = new MagnitudeFilter(1.0, 4.0, "Magnitude"); // Filtre selon la magnitude du séisme
        //Ajout du filtre au tableau de filtre
        maf.addFilter(filtre1);
        
        Filter filtre2 = new DepthFilter(-180000.0, -30000.0, "Profondeur"); // Filtre selon la profondeur du séisme
        //Ajout du filtre au tableau de filtre
        maf.addFilter(filtre2);
        
        Filter filtre4 = new PhraseFilter("any", "o", "Phrase"); //Filtre en prenant en compte le mot clé a
        //Ajout du filtre au tableau de filtre
        maf.addFilter(filtre4);
        
        ArrayList<QuakeEntry> fillterResult = filter(list, maf);
        
        for(QuakeEntry qe : fillterResult){
            System.out.println(qe);
        }
        
        System.out.println("Les filtres utilisés sont: " + maf.getName());
        
        System.out.println(fillterResult.size() + " Données trouvées");
        
    }
    
    
    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser();
        
        //String dataSource = "../data/nov20quakedatasmall.atom";
        String source = "../data/nov20quakedata.atom";
        
        ArrayList<QuakeEntry> list = parser.read(source);
        
        System.out.println(list.size() + " taille des données");
        
        MatchAllFilter maf = new MatchAllFilter();
        
        Location danemark = new Location(55.7308,9.1153);
        
        //Filtres
        Filter filtre1 = new MagnitudeFilter(0.0, 5.0, "Magnitude"); // Filtre selon la magnitude du séisme
        //Ajout du filtre au tableau de filtre
        maf.addFilter(filtre1);
        
        Filter filtre2 = new DistanceFilter(danemark, 3000*1000, "Distance"); // Filtre selon la profondeur du séisme
        //Ajout du filtre au tableau de filtre
        maf.addFilter(filtre2);
        
        Filter filtre4 = new PhraseFilter("any", "e", "Phrase"); //Filtre en prenant en compte le mot clé a
        //Ajout du filtre au tableau de filtre
        maf.addFilter(filtre4);
        
        ArrayList<QuakeEntry> fillterResult = filter(list, maf);
        
        for(QuakeEntry qe : fillterResult){
            System.out.println(qe);
        }
        
        System.out.println(fillterResult.size() + " Données trouvées");
        
    }
    
    
    public void quakesWithFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        
        //String dataSource = "../data/nov20quakedatasmall.atom";
        String source = "../data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        System.out.println(list.size() + " Data lues");
        
        Location denver = new Location(39.7392, -104.9903);
        
        Filter filtre1 = new MagnitudeFilter(3.5, 4.5, "Magnitude"); // Filtre selon la magnitude du séisme
        Filter filtre2 = new DepthFilter(-55000.0, -20000.0, "Profondeur"); // Filtre selon la profondeur du séisme
        //Filter filtre3 = new DistanceFilter(denver, 1000*1000); //Filtre selon une position
        //Filter filtre4 = new PhraseFilter("end", "a"); //Filtre en prenant en compte le mot clé Japan
        
        
        ArrayList<QuakeEntry> firstFilter = filter(list, filtre1);
        ArrayList<QuakeEntry> finalResult = filter(firstFilter, filtre2);
        
        for(QuakeEntry qe : finalResult){
            System.out.println(qe);
        }
        
        System.out.println(finalResult.size() + "données trouvées");
    }
    
    

}
