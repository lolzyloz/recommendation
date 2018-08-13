import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
import java.io.Reader;
import java.io.FileReader;

public class main {
    public static ArrayList<Movie> loadMovies ( String filename) throws Exception {
        ArrayList<Movie> results = new ArrayList<>();
        Reader in = new FileReader(filename);
        CSVParser parser = new CSVParser(in, CSVFormat.EXCEL.withHeader());
        for (CSVRecord record : parser) {
            //System.out.println(record.toString());
            Movie mv = new Movie(record.get("id"),record.get("title"),record.get("year"),record.get("genre"),record.get("director"),record.get("country"),record.get("poster"),Integer.parseInt(record.get("minutes")));
            results.add(mv);
            //System.out.println(mv.toString());
        }

        return results;

    }

    public static void testLoadMovies (ArrayList<Movie> aList) {
        Movie mv;
        HashSet<String> movies = new HashSet<>();
        Integer comedyCount = 0;
        Integer longMovies = 0;
        HashMap<String,HashSet<String>> directors = new HashMap<>();
        for (int i = 0; i<aList.size(); i++) {
            mv = aList.get(i);
            //System.out.println(mv);
            movies.add(mv.getID());
            if (mv.getGenres().toLowerCase().contains("comedy")) {
                comedyCount += 1;
            }
            if (mv.getMinutes() > 150) {
                longMovies += 1;
            }
            String[] allDirectors = mv.getDirector().split(", ");
            for (int j = 0; j< allDirectors.length; j++) {
                //System.out.println(j + "j" + mv.getTitle());
                if (!directors.containsKey(allDirectors[j])) {
                    HashSet<String> temp = new HashSet<>();
                    temp.add(mv.getID());
                    directors.put(allDirectors[j],temp);
                }
                else {
                    HashSet<String> temp = directors.get(allDirectors[j]);
                    temp.add(mv.getID());
                    directors.replace(allDirectors[j],temp);

                }

            }

        }

        System.out.println(movies.size());
        System.out.println(comedyCount);
        System.out.println(longMovies);


        String directorMax = "";
        Integer directMax = 0;
        for (String x : directors.keySet()) {
            //System.out.println(x + " " + directors.get(x).size() + " " + directors.get(x).toString() );
            if (directors.get(x).size() > directMax) {
                directorMax = x;
                directMax = directors.get(x).size();
            }
        }
        System.out.println(directorMax + " " + directors.get(directorMax) + " " + directors.get(directorMax).size() );
    }

    public static ArrayList<Rater> loadRatings ( String filename) throws Exception {
        HashMap<String,Rater> table = new HashMap<>();
        ArrayList<Rater> results = new ArrayList<>();
        Reader in = new FileReader(filename);
        Rater rr;
        CSVParser parser = new CSVParser(in, CSVFormat.EXCEL.withHeader());
        for (CSVRecord record : parser) {
            //System.out.println(record.toString());
            if (table.containsKey(record.get("rater_id"))){
                rr = table.get(record.get("rater_id"));
                rr.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                //System.out.println(rr.getID() + " " + rr.numRatings());

            }
            else {
                rr = new Rater(record.get("rater_id"));
                rr.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                table.put(record.get("rater_id"),rr);
                //System.out.println(rr.getID() + " " + rr.numRatings());
            }
        }

        for (String x : table.keySet()) {
            results.add(table.get(x));

        }

        return results;

    }

    public static void testLoadRaters (ArrayList<Rater> aList ) {
        Rater rr;
        HashMap<String, Rater> Raters = new HashMap<>();
        Rater maxRr = new Rater("-1");
        Integer numRates = 0;
        HashMap<String,Integer> movieMap = new HashMap<>();
        HashSet<String> movies = new HashSet<>();
        for (int i = 0 ; i < aList.size(); i ++) {
            rr = aList.get(i);
            Raters.put(rr.getID(), rr);
            //System.out.println(rr.getID());
            if (rr.numRatings() > numRates) {
                maxRr = rr;
                numRates = rr.numRatings();
            }
            ArrayList<Rating> temp = rr.myRatings;

            for (int j = 0; j<rr.numRatings(); j++) {
                Rating temp2 = temp.get(j);
                if(movieMap.containsKey(temp2.getItem())){
                    movieMap.replace(temp2.getItem(),movieMap.get(temp2.getItem()) + 1);
                }
                else {
                    movieMap.put(temp2.getItem(),1);
                }
                movies.add(temp2.getItem());

            }

        }
        System.out.println(Raters.size());
        System.out.println(Raters.get("193").numRatings());
        System.out.println(maxRr.numRatings() + " " + maxRr.getID());
        System.out.println(movieMap.get("1798709"));
        System.out.println(movies.size());

    }

    public static void main(String[] args) throws Exception {
//        testLoadMovies(loadMovies("/Users/lolzyloz/Dropbox/Java/5/StepOneStarterProgram/data/ratedmoviesfull.csv"));
//        testLoadRaters(loadRatings("/Users/lolzyloz/Dropbox/Java/5/StepOneStarterProgram/data/ratings.csv"));
        MovieRunnerAverage mra = new MovieRunnerAverage();
        HashMap<String,Double> averageRating = mra.getAverageRatings(3);
//        Integer MovieCount = 0;
//        String directorsNeeded = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
//        for (String x : averageRating.keySet()) {
//            //if (averageRating.get(x) != 0.0 && mra.myMovieDict.get(x).getYear() >= 2000){
//            //if (averageRating.get(x) != 0.0 && mra.myMovieDict.get(x).getGenres().toLowerCase().contains("comedy")){
//            //if (averageRating.get(x) != 0.0 && mra.myMovieDict.get(x).getMinutes() >= 105 && mra.myMovieDict.get(x).getMinutes() <= 135){
//            //if (averageRating.get(x) != 0.0 && mra.myMovieDict.get(x).getYear() >= 1990 && mra.myMovieDict.get(x).getGenres().toLowerCase().contains("drama")) {
//            if (averageRating.get(x) != 0.0 && mra.myMovieDict.get(x).getMinutes() >= 90 && mra.myMovieDict.get(x).getMinutes() <= 180){
//                String[] directors = mra.myMovieDict.get(x).getDirector().split(", ");
//                for (int j = 0; j < directors.length; j ++) {
//                    if (directorsNeeded.contains(directors[j])){
//                        MovieCount+=1;
//                        System.out.println(MovieCount + " " + j + " " + mra.myMovieDict.get(x).getID() );
//                        break;
//                    }
//                }
//
//
//           }
//            //System.out.println(x + " " + averageRating.get(x));
//        }
//        System.out.println(MovieCount);
        ArrayList<Rater> similarRaters = mra.getSimilarRaters("1");
        for (int i = 0; i < similarRaters.size(); i++) {
            Rater otherRater = similarRaters.get(i);
            System.out.println(otherRater.getID() + " " + otherRater.getRating("relative"));

        }

    }
}
