
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    public ArrayList<Movie> myMovies;
    public ArrayList<Rater> myRaters;
    public HashMap<String,Movie> myMoviesDict = new HashMap<>();
    public HashMap<String,Rater> myRatersDict = new HashMap<>();
    
    public SecondRatings() throws Exception {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String moviefile, String ratingfile) {
        try {
            myMovies = main.loadMovies(moviefile);
            myRaters = main.loadRatings(ratingfile);
            for (int i = 0; i <myMovies.size(); i++){
                Movie mv = myMovies.get(i);
                myMoviesDict.put(mv.getID(),mv);
            }
            for (int i = 0; i <myRaters.size(); i++){
                Rater rr = myRaters.get(i);
                myRatersDict.put(rr.getID(),rr);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer getMovieSize() {
//        HashSet<String> results = new HashSet<>();
//        for (int i = 0; i< myMovies.size(); i ++) {
//            Movie temp = myMovies.get(i);
//            results.add(temp.getID());
//        }
//        return results.size();
        return myMovies.size();
    }

    public Integer getRaterSize() {
        return myRaters.size();
    }

}
