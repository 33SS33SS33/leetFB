package amaoa;

import java.util.*;

/**
 * Created by shanshan on 2/21/19.
 */
public class MaxApplicationPair {
    class Application {
        int id;
        int size;
    }

    public static int[] s(Application[] foreground, Application[] background, int capacity) {
        int[] res = new int[2];
        if (foreground == null || foreground.length == 0 || background == null || background.length == 0)
            return res;
        Arrays.sort(foreground, Comparator.comparingInt(a -> a.size));
        Arrays.sort(background, Comparator.comparingInt(a -> a.size));
        int max = capacity;
        while (true) {
            for (Application l : foreground) {
                for (Application b : background) {
                    int forward = l.size;
                    int backward = b.size;
                    int tot = (forward + backward);
                    if (tot > max) {
                        break;
                    }
                    if (tot == max) {
                        res[0] = l.id;
                        res[0] = b.id;
                        break;
                    }

                }
            }
            if (res.length > 0) {
                break;
            }
            max--;
        }
        return res;
    }

    public static List<List<Integer>> getIdPairsForOptimal(List<List<Integer>> forwardList,
                                                           List<List<Integer>> backwardList, int maxDistance) {
        List<List<Integer>> result = new LinkedList<>();
        if (forwardList == null || forwardList.size() == 0 || backwardList == null || backwardList.size() == 0)
            return result;
/*        forwardList = forwardList.stream().sorted(Comparator.comparingInt(x -> x.get(1)))
                .collect(Collectors.toList());
        backwardList = backwardList.stream().sorted(Comparator.comparingInt(x -> x.get(1)))
                .collect(Collectors.toList());*/
        Collections.sort(forwardList, Comparator.comparing(o -> o.get(1)));
        Collections.sort(backwardList, Comparator.comparing(o -> o.get(1)));
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
