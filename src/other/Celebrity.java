package other;

/**
 * Given:
 * function:  isFriend(a, b)  
 * Returns true iff b is treated as a friend by a group of persons, say, 
 * represented as an array
 */
class Celebrity {
    class Person{

    }
    public static void main(String[] args) {
        
    }
    
    /**
     * Traverse the list
     * Update leader index if leader is friend of current id
     * Then check if leader is real with one more traversal
     */
    boolean hasLeader(Person[] persons) {
        int n = persons.length;
        int leader = 0;
        int cur = 1;
        while (cur < n) {
            if (isFriend(persons[leader], persons[cur])) leader = cur;
            cur++;
        }
        
        for (int i = 0; i < n; i++) {
            if (i != leader && isFriend(persons[leader], persons[i])) {
                return false;
            }
        }
        return true;
    }
    public boolean isFriend(Person a,Person b){
        return true;
    }
}