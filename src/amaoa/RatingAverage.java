package amaoa;

import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Created by shanshan on 2/21/19.
 */
public class RatingAverage {


    public class Movie {
        List<Integer> ratings;

        public List<movies.Movie> getSimilarMovies() {
            return null;
        }

        public Float getRating() {
            return null;
        }
    }

 /*   private static void bfsSearchMovies(List<Movie> movies) {
        PriorityQueue<Movie> queue = new PriorityQueue<>(5, ((m1, m2) -> new Float(m2.getRating()).compareTo(m1.getRating())));
        TreeMap<Integer, PriorityQueue<Movie>> treeMap = new TreeMap<>();

        for (Movie M : movies) {
            if (!queue.contains(M)) {
                queue.offer(M);
                bfsSearchMovies(M, queue);
            }
        }
    }*/
}
