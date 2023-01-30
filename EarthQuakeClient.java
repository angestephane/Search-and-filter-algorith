
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;              
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
        ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();
        
        for(QuakeEntry qe : quakeData){
            if(qe.getDepth() > minDepth && qe.getDepth() < maxDepth ) {
                result.add(qe);
            }
        }
        
        return result;
    }
    
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();
        
         
        if(where.equals("any")){
            int indexOfPhrase = 0;
            for(int k=0; k<quakeData.size(); k++){
                indexOfPhrase = quakeData.get(k).getInfo().indexOf(phrase);
                if(indexOfPhrase > 0) {
                    result.add(quakeData.get(k));
                }
            } 
        }
        
        if(where.equals("end")){
            for(QuakeEntry qe : quakeData){
                if(qe.getInfo().endsWith(phrase)){
                    result.add(qe);
                }
            }
        }
        
        if(where.equals("start")){
            for(QuakeEntry qe : quakeData){
                if(qe.getInfo().startsWith(phrase)){
                    result.add(qe);
                }
            }
        }
        
        return result;
    }
    
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
    
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "../data/nov20quakedata.atom";
        //String sourceSmallData = "../data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
       
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : listBig) {
           System.out.println(qe); 
        }
        System.out.println(listBig.size()+ " tremblements de tèrre qui match avec ce critère");
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../../data/nov20quakedata.atom";
        String sourceSmallData = "../../data/nov20quakedatasmall.atom";
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(sourceSmallData);
        System.out.println("#quakes read: " + list.size());
        dumpCSV(list);
        
    }
    
    
    
    
    public void quakeOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        
        //String dataSource = "../../data/nov20quakedatasmall.atom";
        String source = "../data/nov20quakedata.atom";
        
        ArrayList<QuakeEntry> list = parser.read(source);
        
        System.out.println("Quake Read : "+ list.size());
        
        double minDepth = -4000.0;
        double maxDepth = -2000.0;
        
        ArrayList<QuakeEntry> quakes = filterByDepth(list,minDepth ,maxDepth);
        
        for(QuakeEntry quake: quakes){
            System.out.println(quake);
        }
        System.out.println(quakes.size()+ " tremblements de tèrre qui match avec ce critère");
        
        
    }
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        
        //String dataSource = "../../data/nov20quakedatasmall.atom";
        String source = "../data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        String where = "any";
        String phrase = "Can";
        
        ArrayList<QuakeEntry> quakes = filterByPhrase(list, where, phrase);
        
        System.out.println("Quake Read : "+ list.size());
        
        for(QuakeEntry quake: quakes){
            System.out.println(quake);
        }
        
        switch(where){
            case "any" : 
                System.out.println(quakes.size()+ " seisme(s) correspondant(s) à " + phrase);
                break;
            
            case "end" : 
               System.out.println(quakes.size()+ " seisme(s) correspondant(s) à " + phrase + " à la fin"); 
               break;
            
            case "start":
                System.out.println(quakes.size()+ " seisme(s) correspondant(s) à " + phrase + " au début");
                break;
            
        }
        
    }
        
}
