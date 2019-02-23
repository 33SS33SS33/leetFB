package amaoa;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by shanshan on 2/21/19.
 */
public class MaxApplicationPair {
    class Application {
        int id;
        int size;
    }

    public static void s(Application[] foreground, Application[] background, int capacity) {

    }

    public static List<List<Integer>> getIdPairsForOptimal(List<List<Integer>> forwardList,
                                                           List<List<Integer>> backwardList, int maxDistance) {
        List<List<Integer>> result = new LinkedList<>();
        if(forwardList==null||forwardList.size()==0)
        forwardList = forwardList.stream().sorted((x1, x2) -> Integer.compare(x2.get(1), x1.get(1)))
                .collect(Collectors.toList());
        backwardList = backwardList.stream().sorted(Comparator.comparingInt(x -> x.get(1)))
                .collect(Collectors.toList());
        int maxDist = maxDistance;
        while (true) {
            for (List<Integer> l : forwardList) {
                for (List<Integer> b : backwardList) {
                    int forward = l.get(1);
                    int backward = b.get(1);
                    int tot = (forward + backward);
                    if (tot > maxDist) {
                        break;
                    }
                    if (tot == maxDist) {
                        // print the pair of Id and optimum distance
                        result.add(Arrays.asList(l.get(0), b.get(0), maxDist));
                        break;
                    }

                }
            }
            if (result.size() > 0) {
                break;
            }
            maxDist--;
        }
        return result;
    }
}
