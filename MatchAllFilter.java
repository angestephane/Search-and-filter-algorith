import java.util.*;
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MatchAllFilter implements Filter {
    
    private ArrayList<Filter> filters = new ArrayList<Filter>();
    
    public MatchAllFilter(){}
    
    public void addFilter(Filter filter){
        filters.add(filter);
    }
    
    @Override
    public boolean satisfie(QuakeEntry qe){
        boolean arrayValue = true;
        for(Filter filter : filters){
            if(!filter.satisfie(qe)){
                arrayValue = false;
                break;
            }
        }
        
        return arrayValue;
    }

    
}
