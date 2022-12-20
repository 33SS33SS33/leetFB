package TopInterview;

class FindTheCelebrity {
    /**
     * The first pass is to pick out the candidate. If candidate knows i, then switch candidate.
     * The second pass is to ch eck whether the candidate is real.
     * Given: function:  isddFriend(a, b)
     * Returns true if b is treated as a friend by a group of persons, say,
     * represented as an array. all the other n - 1 people know him/her but he/she does not know any of them.
     * 可以通过一遍就过滤出来一个候选的
     * The key part is the first loop.
     * To understand this you can think the knows(a,b) as a a < b comparison,
     * if a knows b then a < b, if a does not know b, a > b.
     * then if there is a celebrity, he/she must be the "maximum" of the n people.
     * 然后只需要检查这个候选的是不是就行了
     */
    public int findTheCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i))
                candidate = i;
        }
        for (int i = 0; i < n; i++) {
            if (i != candidate && (knows(candidate, i) || !knows(i, candidate))) return -1;
        }
        return candidate;
    }

    public boolean knows(int a, int b) {
        return true;
    }

}