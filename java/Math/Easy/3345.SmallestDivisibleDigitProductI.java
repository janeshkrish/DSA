class Solution {
    public int smallestNumber(int n, int t) {
        int temp = n;
        int product = 1;
        while(temp > 0){
            int lastdigit = temp % 10;
            product = product * lastdigit;
            temp = temp /10;
        }
        if(product % t == 0){
            return n;
        }
        return smallestNumber(n+1,t);
    }
}