1class Solution {
2    public boolean isValid(String word) {
3        int totalCount=word.length();
4        boolean doesHaveVowel=false;
5        boolean doesHaveConsonant=false;
6       
7        String vowels = "aeiouAEIOU";
8        int onlyDigit=0;
9        for(int i=0; i < word.length(); i++){
10            
11    // If the character is found, indexOf returns its position (>= 0); otherwise, it returns -1.
12            if(vowels.indexOf(word.charAt(i)) != -1) doesHaveVowel=true;
13            if(vowels.indexOf(word.charAt(i)) == -1 && Character.isAlphabetic(word.charAt(i))) doesHaveConsonant=true;
14            if(!Character.isAlphabetic(word.charAt(i)) && !Character.isDigit(word.charAt(i))) return false;
15        }
16        if(doesHaveVowel && doesHaveConsonant && totalCount >= 3){
17            return true;
18        }
19        return false;
20    }
21}