package top50Google;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FindAllPossibleRecipesfromGivenSupplies {
    /**
     * You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients.
     * The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i].
     * Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.
     * You are also given a string array supplies containing all the ingredients that you initially have,and you have an infinite supply of all of them.
     * Return a list of all the recipes that you can create. You may return the answer in any order.
     * Note that two recipes may contain each other in their ingredients.
     * Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
     * Output: ["bread"]
     * Explanation:We can create "bread" since we have the ingredients "yeast" and "flour".
     */
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        // Construct directed graph and count in-degrees.
        Map<String, Set<String>> ingredientToRecipes = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();
        for (int i = 0; i < recipes.length; ++i) {
            for (String ing : ingredients.get(i)) {
                ingredientToRecipes.computeIfAbsent(ing, s -> new HashSet<>()).add(recipes[i]);
            }
            inDegree.put(recipes[i], ingredients.get(i).size());
        }
        // Toplogical Sort.
        List<String> ans = new ArrayList<>();
        Queue<String> available = Stream.of(supplies).collect(Collectors.toCollection(LinkedList::new));
        while (!available.isEmpty()) {
            String ing = available.poll();
            if (ingredientToRecipes.containsKey(ing)) {
                for (String rcp : ingredientToRecipes.remove(ing)) {
                    if (inDegree.merge(rcp, -1, Integer::sum) == 0) {
                        available.offer(rcp);
                        ans.add(rcp);
                    }
                }
            }
        }
        return ans;
    }
}
