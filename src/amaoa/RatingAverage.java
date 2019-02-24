package amaoa;

import java.util.List;

/**
 * Created by shanshan on 2/21/19.
 */
public class RatingAverage {


    public class Movie {
        List<Integer> ratings;

        public List<Movies.Movie> getSimilarMovies() {
            return null;
        }

        public Float getRating() {
            return null;
        }
    }

 /*   private static void bfsSearchMovies(List<Movie> Movies) {
        PriorityQueue<Movie> queue = new PriorityQueue<>(5, ((m1, m2) -> new Float(m2.getRating()).compareTo(m1.getRating())));
        TreeMap<Integer, PriorityQueue<Movie>> treeMap = new TreeMap<>();

        for (Movie M : Movies) {
            if (!queue.contains(M)) {
                queue.offer(M);
                bfsSearchMovies(M, queue);
            }
        }
    }*/
}
