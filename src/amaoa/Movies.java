package amaoa;

import java.util.*;

/**
 * 现在要求找到 k个和movie最相似 的movies。
 */
public class Movies {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PriorityQueue<Movie> queue = new PriorityQueue<Movie>(new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return new Float(m2.getRating()).compareTo(m1.getRating());
            }
        });
    }


    public List<Movie> getNearest(Movie movie, int k) {
        /**
         A priorityQueue to keep minHeap of top k rating
         Use DFS level order traverse all nodes
         */
        PriorityQueue<Movie> pq = new PriorityQueue<>(k, (a, b) -> (a.rating - b.rating > 0 ? 1 : -1));
        Queue<Movie> queue = new LinkedList<>();
        HashSet<Movie> hash = new HashSet<>();
        for (Movie indegree : movie.similarMovies) {
            if (!hash.contains(indegree)) {
                queue.offer(indegree);
                hash.add(indegree);
            }
        }
        while (!queue.isEmpty()) {
            Movie oneMovie = queue.poll();
            if (pq.size() < k) {
                pq.add(oneMovie);
            } else {
                if (pq.peek().rating < oneMovie.rating) {
                    pq.poll();
                    pq.offer(oneMovie);
                }
            }
            for (Movie similar : oneMovie.similarMovies) {
                if (!hash.contains(similar)) {
                    pq.offer(similar);
                    hash.add(similar);
                }
            }
        }
        List<Movie> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.poll());
        }
        return res;
    }

    public class Movie {
        int movieId;
        float rating;
        List<Movie> similarMovies;

        public List<Movie> getSimilarMovies() {
            return null;
        }

        public Float getRating() {
            return null;
        }
    }
}
