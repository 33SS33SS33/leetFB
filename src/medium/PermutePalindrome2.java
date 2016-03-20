package medium;

import java.util.*;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
public class PermutePalindrome2 {
    Set<String> permute(ArrayList<Character> chars, final String single){

        if(chars.isEmpty()){

            return Collections.singleton(single);
        } else if(chars.size() == 1) {

            return Collections.singleton(chars.get(0) + single + chars.get(0));
        }

        Set<String> found = new HashSet<String>();

        // else
        for(int i = 0; i < chars.size(); i++){

            char c = chars.get(i);

            ArrayList<Character> rest = new ArrayList<Character>(chars);
            rest.remove(i);

     /*       found.addAll(permute(rest, single).stream()
                    .map(s -> c + s + c)
                    .collect(Collectors.toSet()));*/
        }

        return found;
    }

    public List<String> generatePalindromes(String s) {


        ArrayList<Character> chars = new ArrayList<Character>();

        Set<Character> single = new HashSet<Character>();

        for(char c : s.toCharArray()){

            if(single.contains(c)){
                single.remove(c);
                chars.add(c);
            } else {
                single.add(c);
            }
        }

        if(single.size() > 1) return Collections.emptyList();

        // else
        String _single = "";

        if(single.size() == 1) {
            _single = single.iterator().next() + "";
        }

        return new ArrayList<String>(permute(chars, _single));
    }
}
