import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.HashMap;

public class MovieRunnerAverage {
    SecondRatings sr = new SecondRatings("/Users/lolzyloz/Dropbox/Java/5/StepOneStarterProgram/data/ratedmoviesfull.csv", "/Users/lolzyloz/Dropbox/Java/5/StepOneStarterProgram/data/ratings.csv");
    public HashMap<String,Movie> myMovieDict = sr.myMoviesDict;
    public HashMap<String,Rater> myRaterDict = sr.myRatersDict;

    public void printAverageRatings() throws Exception {
        System.out.println(sr.getMovieSize());
        System.out.println(sr.getRaterSize());
    }

    public ArrayList<Rater> getSimilarRaters(String ID) {
        Rater rrID = sr.myRatersDict.get(ID);
        ArrayList<Rater> results = new ArrayList<>();


        for (String raterID : sr.myRatersDict.keySet()) {
            Rater rr = sr.myRatersDict.get(raterID);
            //System.out.println(rrID.getID() + " " + rr.getID());
            Double relativeRating = 0.0;
            if (!rr.getID().equals(ID)) {
                //System.out.println(rr.getID() + " " + ID);
                for (String movieID: sr.myMoviesDict.keySet()) {
                    if (rrID.getRating(movieID) != -1 && rr.getRating(movieID) != -1) {
                        relativeRating += (rrID.getRating(movieID)-5.0) * (rr.getRating(movieID) - 5.0);
                    }
                }
            }
            if (relativeRating!=0.0) {
                Rater relativeRr = new Rater(raterID);
                relativeRr.addRating("relative",relativeRating);
                results.add(relativeRr);
            }



        }
        Collections.sort(results);
        //Collections.sort(results,Collections.reverseOrder());

        return results;
    }

    public HashMap<String, ArrayList<Rater>> allSimilarRaters() {
        HashMap<String, ArrayList<Rater>> results = new HashMap<>();
        for (String raterID : sr.myRatersDict.keySet()) {
            results.put(raterID, getSimilarRaters(raterID));

        }
        return results;
    }



    private Double getAverageByID (String ID, Integer minimalRaters) {
        ArrayList<Double> tempScore = new ArrayList<>();
        for (int i = 0; i< sr.myRaters.size(); i++) {
            Rater rr = sr.myRaters.get(i);
            for (int j = 0; j < rr.myRatings.size(); j++) {
                Rating rg = rr.myRatings.get(j);
                if (rg.getItem().equals(ID)) {
                    tempScore.add(rg.getValue());
                }
            }
        }
        if (tempScore.size() < minimalRaters) {
            return 0.0;
        }
        else {
            Double finalScore = 0.0;
            for (int i = 0 ; i< tempScore.size(); i++) {
                finalScore += tempScore.get(i);
            }

        if(tempScore.size() >=50) {
                //System.out.println(ID + " more than 50");
        }

            return finalScore/tempScore.size();
        }
    }

    public HashMap<String,Double> getAverageRatings (Integer minimalRaters) {
        HashMap<String,Double> results = new HashMap<>();
        for (int i = 0; i < sr.myMovies.size(); i++) {
            Movie mv = sr.myMovies.get(i);
            if (!results.containsKey(mv.getID())) {
                Double averageRating = getAverageByID(mv.getID(),minimalRaters);
                results.put(mv.getID(),averageRating);
            }
        }
        return results;
    }

    public String getTopMovie(String userID, Integer minimal, Integer Maximal) {
        ArrayList<Rater> similarRaters = getSimilarRaters(userID);
        for (String movieID : myMovieDict.keySet()) {
            Mo
            for (int i = 0; i < similarRaters.size(); i++) {
                Rater otherRater = similarRaters.get(i);
                Double relativeWeight = otherRater.getRating("relative");

                Rater otherRr = myRaterDict.get(otherRater.getID());
                if ()

            }

        }

        }


    }
