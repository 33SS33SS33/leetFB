package dP;

import java.util.List;
import java.util.stream.Collectors;

public class test {
    boolean a;

    public boolean isA() {
        return a;
    }

    public boolean asa(String args){
        System.out.print(a);
        return a;
    }

    public static void main(String[] args){
//        new test().asa("1");
        List<String> a;
        a=null;
//        if(a!=null)
        a=a.stream().filter(b->b.equalsIgnoreCase("mm")).collect(Collectors.toList());
        System.out.print(a);
    }
}
