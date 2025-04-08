/** Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.*/

class KMP {
    public static void main(String[] args) {
        String haystack = "abaacabababaa", needle = "ababa";
        System.out.println(kmp(haystack, needle));
    }
    public static int kmp(String a, String b){
        int[] next = getPattern(b);
        int i = 0, j = 0;
        while(i < a.length() && j < b.length()){
            if(a.charAt(i) == b.charAt(j)){
                i++;
                j++;
            }else{
                if(j == 0){
                    i++;
                }else{
                    j = next[j-1];
                }
            }
        }
        if(j < b.length()) return -1;
        return i - b.length();
    }
    public static int[] getPattern(String s){
        int[] next = new int[s.length()];
        /**
         * 0 0 1 2 3
         * a b a b a
         *     a b a b a
         */
        int j = 0;
        for(int i=1;i<s.length();){
            if(s.charAt(j) == s.charAt(i)){
                next[i] = j + 1;
                i++;
                j++;
            }else{
                if(j == 0){
                    i++;
                }else{
                    j = next[j-1];
                }
            }
        }
        return next;
    }
}