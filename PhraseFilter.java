
/**
 * La classe PhraseFilter l'interface Filter. 
 * Cette classe possède deux varibles d'instance une 
 * variable qui indique où la recherche sera faite dans 
 * le texte et a trois valeur ("start", "any", "end"), et
 * une variable qui indique la phrase à chercher dans 
 * le titre. 
 * La classe rédefinit la méthode satisfie qui
 * retourne true si la phrase est trouvée à la position
 * indiqué dans le titre du séisme.
 * Toute fois cette méthode retourne false.
 */
public class PhraseFilter implements Filter {
    private String where;
    private String phrase;
    
    public PhraseFilter(String getSearchLocation, String sentenceToSearch){
        this.where = getSearchLocation;
        this.phrase = sentenceToSearch;
    }
    
    
    @Override
    public boolean satisfie(QuakeEntry qe){
        
        if(where.equals("start")){
            if(qe.getInfo().startsWith(this.phrase)){
                return true;
            }else{
                return false;
            }
        }
        else if(where.equals("end")){
            if(qe.getInfo().endsWith(this.phrase)){
                return true;
            }else{
                return false;
            }
        }
        else{
            int resultIndex = qe.getInfo().indexOf(phrase);
            if(resultIndex > 0){
                return true;
            }
            else {
                return false;
            }
        }
        
    }


}
