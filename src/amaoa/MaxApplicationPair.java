package amaoa;

import java.util.*;

/**
 * Created by shanshan on 2/21/19.
 */
public class MaxApplicationPair {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
        List<List<Integer>> forwardList = new ArrayList<>();
        List<Integer> list1 = Arrays.asList(1, 3000);
        List<Integer> list5 = Arrays.asList(9, 3000);
        forwardList.add(list1);
        forwardList.add(list5);

        List<List<Integer>> backwardList = new ArrayList<>();
        List<Integer> list2 = Arrays.asList(3, 4000);
        List<Integer> list3 = Arrays.asList(4, 4000);
        List<Integer> list4 = Arrays.asList(5, 8000);
        backwardList.add(list2);
        backwardList.add(list3);
        backwardList.add(list4);
        System.out.println(getIdPairsForOptimal2(forwardList, backwardList, 8000));
        Application app1 = new Application(1, 3000);
        Application app2 = new Application(9, 3000);
        Application app3 = new Application(3, 4000);
        Application app4 = new Application(4, 4000);
        Application app5 = new Application(5, 8000);
        Application[] foreground = {app1, app2};
        Application[] background = {app3, app4, app5};
        int[][] re = getIdPairs(foreground, background, 8000);
        for (int i = 0; i < re.length; i++) {
            for (int j = 0; j < re[0].length; j++) {
                System.out.print(re[i][j]);
            }
        }
    }

    public static List<List<Integer>> getIdPairsForOptimal2(List<List<Integer>> forwardList,
                                                            List<List<Integer>> backwardList, int maxDistance) {
        List<List<Integer>> result = new LinkedList<>();
        if (forwardList == null || forwardList.size() == 0 || backwardList == null || backwardList.size() == 0)
            return result;

        Collections.sort(forwardList, Comparator.comparing(o -> o.get(1)));
        Collections.sort(backwardList, Comparator.comparing(o -> o.get(1)));
        int sum = Integer.MIN_VALUE;
        for (List<Integer> forwardRoute : forwardList) {
            for (List<Integer> returnRoute : backwardList) {
                int tmp = forwardRoute.get(1) + returnRoute.get(1);
                if (tmp > maxDistance) {
                    continue;
                }
                if (tmp >= sum) {
                    List<Integer> l = new ArrayList<>();
                    l.add(forwardRoute.get(0));
                    l.add(returnRoute.get(0));
                    if (tmp > sum) {
                        result = new ArrayList<>();
                    }
                    result.add(l);
                    sum = tmp;
                }
            }
        }
        return result;
    }

    static class Application {
        int id;
        int size;

        public Application(int id, int size) {
            this.id = id;
            this.size = size;
        }
    }

    public static int[][] getIdPairs(Application[] foreground, Application[] background, int capacity) {
        List<List<Integer>> result = new LinkedList<>();
        if (foreground == null || foreground.length == 0 || background == null || background.length == 0)
            return new int[0][0];
        Arrays.sort(foreground, Comparator.comparingInt(a -> a.size));
        Arrays.sort(background, Comparator.comparingInt(a -> a.size));
        int sum = Integer.MIN_VALUE;
        for (Application f : foreground) {
            for (Application b : background) {
                int forward = f.size;
                int backward = b.size;
                int tot = (forward + backward);
                if (tot > capacity) {
                    continue;
                }
                if (tot >= sum) {
                    List<Integer> l = new ArrayList<>();
                    l.add(f.id);
                    l.add(b.id);
                    if (tot > sum) {
                        result = new ArrayList<>();
                    }
                    result.add(l);
                    sum = tot;
                }

            }
        }
        int[][] res = new int[result.size()][2];
        for (int i = 0; i < res.length; ++i) {
            for (int j = 0; j < res[i].length; ++j) {
                res[i][j] = result.get(i).get(j);
            }
        }
        return res;
    }

/*    public static List<List<Integer>> getIdPairsForOptimal(List<List<Integer>> forwardList,
                                                           List<List<Integer>> backwardList, int maxDistance) {
        List<List<Integer>> result = new LinkedList<>();
        if (forwardList == null || forwardList.size() == 0 || backwardList == null || backwardList.size() == 0)
            return result;
*//*        forwardList = forwardList.stream().sorted(Comparator.comparingInt(x -> x.get(1)))
                .collect(Collectors.toList());
        backwardList = backwardList.stream().sorted(Comparator.comparingInt(x -> x.get(1)))
                .collect(Collectors.toList());*//*
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
                        result.add(Arrays.asList(l.get(0), b.get(0)));
                        continue;
                    }

                }
            }
*//*            if (result.size() > 0) {
                break;
            }
            maxDist--;*//*
            return result;
        }
    }*/


}
