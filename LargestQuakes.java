
import java.util.*;

/**
 * Determine les N Séismes avec 
 * La plus grandes magnetude.
 * 
 * @author Boguhe Ange Stephane 
 * @date 18/01/2023
 */
public class LargestQuakes {
    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int indexOfLargest = 0;
        
        for(int k=1; k<data.size(); k++){
            if(data.get(k).getMagnitude() > data.get(indexOfLargest).getMagnitude()){
                indexOfLargest = k;
            }
        }
        
        return indexOfLargest;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();
        
        if(howMany > copy.size()){
            return result;
        }else{
            int tmp;
           for(int k=0; k < howMany; k++){
               tmp = indexOfLargest(copy);
               result.add(copy.get(tmp));
               copy.remove(tmp);
            }
        }

        return result;
    }
    
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        
        String source = "../data/nov20quakedata.atom";
        //String sourceForSmallData = "../../data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";

        ArrayList<QuakeEntry> list = parser.read(source);
        
        System.out.println(list.size() + " #Données lues");
        
        ArrayList<QuakeEntry> largestMagnitude = getLargest(list, 50);
        
        for(QuakeEntry qe : largestMagnitude ){
           System.out.println(qe); 
        }

    }

}
