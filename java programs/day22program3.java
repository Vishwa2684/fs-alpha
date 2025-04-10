/*
There is a switch-board made by an electrician,
If you turn on any two adjacent switches, it will cause short-circuit
and damage the switch-board.

You are given N integers(only 0's and 1's), Indiactes current status of 
the switch board with N switches, where 1 indiactes switch is ON and 0 indiactes
switch is OFF. And an integer K, more number of switches to be turned ON.

Return true if and only if you can turn ON all the K switches, without causing 
any damage to switch-board. Otherwise return fasle.

Input Format:
-------------
Line-1: Two integers N and K, number of switches, and more number of switches to be ON
Line-2: N space separated integers, only 0's and 1's.

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
5 1
1 0 0 0 1

Sample Output-1:
----------------
true

Sample Input-2:
---------------
5 2	
1 0 0 0 1

Sample Output-2:
----------------
flase

*/


import java.util.*;

public class day22program3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); 
        int k = sc.nextInt(); 
        int[] switches = new int[n];
        for (int i = 0; i < n; i++) {
            switches[i] = sc.nextInt();
        }
        System.out.println(canTurnOnSwitches(switches, k));
    }
    public static boolean canTurnOnSwitches(int[] switches, int K) {
        int count = 0;
        int n = switches.length;
        for (int i = 0; i < n; i++) {
            if (switches[i] == 0) {
                if ((i == 0 || switches[i - 1] == 0) && (i == n - 1 || switches[i + 1] == 0)) {
                    switches[i] = 1; 
                    count++;
                    i++; 
                }
            }
        }
        return count >= K;
    }
}







// import java.util.*;

// public class program{
//     public static void main(String[] args){
//         Scanner sc = new Scanner(System.in);
//         int n = sc.nextInt();
//         int k = sc.nextInt();
//         int[] arr = new int[n];
//         for(int i=0; i<n; i++){
//             arr[i] = sc.nextInt();
//         }
//         System.out.print(canPlaceFlowers(arr,k));
//     }
    
//     public static boolean canPlaceFlowers(int[] flowerbed, int n) {
//         int count=0;
//         for(int i=0; i<flowerbed.length; i++){
//             if(flowerbed[i]==0){
//                 count++;
//             }
//             else if(flowerbed[i]==1){
//                 int val=0;
//                 if(count>=3 && count%2 == 0){
//                     val = (count/2)-1;
//                 }
//                 else{
//                     val = (count/2);
//                 }
//                 n = n-val;
//                 count=0;
//             }
//         }
//         if(n==0){
//             return true;
//         }
//         return false;
//     }
// }