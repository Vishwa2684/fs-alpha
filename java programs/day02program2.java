// /*
// You are a treasure hunter exploring an ancient vault filled with 
// treasure boxes. The vault is represented as an array treasures of 
// n integers, where each integer corresponds to the value of a treasure. 
// You have a special key that allows you to scan and select treasures 
// from a sub-vault (a segment of the array) of size k. Additionally, 
// you have a magical power factor f and a priority filter x.

// The priority-weighted treasure sum of a sub-vault is calculated as follows:
// 	1. Count the occurrences of each treasure value in the sub-vault.
// 	2. Assign a priority score to each treasure based on its frequency 
// 	multiplied by the treasure's value raised to the power of f 
// 	(i.e., priority_score[treasure] = frequency[treasure] * (value^f)).
// 	3. Select only the top x treasures based on their priority scores. 
// 	If two treasures have the same priority score, the treasure with 
// 	the higher value is prioritized.
// 	4. Calculate the total value of the selected treasures.

// Your task is to return an integer array priority_sums of length n - k + 1, 
// where priority_sums[i] represents the priority-weighted treasure sum for 
// the sub-vault corresponding to treasures[i..i + k - 1].

// Input Format:
// ---------------
// Line-1: Four space separated integers, N, K, X, F
// Line-2: N space separated integers, boxes[].

// Output Format:
// -----------------
// An integer array, priority_sums[], of length n - k + 1


// Sample Input-1:
// -----------------
// 8 5 2 2
// 1 2 3 1 2 2 3 4

// Sample Output-1:
// --------------------
// [7, 9, 10, 7]

// Explanation:
// We calculate the priority-weighted treasure sum for each sub-vault:

// 1. Sub-vault 1: [1, 2, 3, 1, 2]
//    - Frequencies: {1: 2, 2: 2, 3: 1}
//    - Priority scores:
//      - 1 → 2 * (1^2) = 2
//      - 2 → 2 * (2^2) = 8
//      - 3 → 1 * (3^2) = 9
//    - Top 2 treasures by priority: 3 (score 9) and 2 (score 8).
//    - Total value: 2 + 3 + 2  = 7.

// 2. Sub-vault 2: [2, 3, 1, 2, 2]
//    - Frequencies: {2: 3, 3: 1, 1: 1}
//    - Priority scores:
//      - 2 → 3 * (2^2) = 12
//      - 3 → 1 * (3^2) = 9
//      - 1 → 1 * (1^2) = 1
//    - Top 2 treasures by priority: 2 (score 12) and 3 (score 9).
//    - Total value: 2 + 2 + 2 + 3 = 9.

// 3. Sub-vault 3: [3, 1, 2, 2, 3]
//    - Frequencies: {3: 2, 2: 2, 1: 1}
//    - Priority scores:
//      - 3 → 2 * (3^2) = 18
//      - 2 → 2 * (2^2) = 8
//      - 1 → 1 * (1^2) = 1
//    - Top 2 treasures by priority: 3 (score 18) and 2 (score 8).
//    - Total value: 3 + 2 + 2 + 3 = 10.

// 4. Sub-vault 4: [1, 2, 2, 3, 4]
//    - Frequencies: {1: 1, 2: 2, 3: 1, 4: 1}
//    - Priority scores:
//      - 2 → 2 * (2^2) = 8
//      - 3 → 1 * (3^2) = 9
//      - 4 → 1 * (4^2) = 16
//      - 1 → 1 * (1^2) = 1
//    - Top 2 treasures by priority: 4 (score 16) and 3 (score 9).
//    - Total value: 3 + 4  = 7.

// Sample Input-2:
// -----------------
// 6 3 2 1
// 5 5 6 7 5 6

// Sample Output-1:
// --------------------
// [16, 13, 13, 13]

// Constraints:
// 1. 1 <= n == treasures.length <= 50
// 2. 1 <= treasures[i] <= 50
// 3. 1 <= x <= k <= treasures.length
// 4. 1 <= f <= 10
// */



//Using priority Queue, Without sliding window
import java.util.*;
public class day02program2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int x = sc.nextInt();
        int f = sc.nextInt();
        int[] boxes = new int[n];
        for(int i=0; i<n; i++){
            boxes[i]=sc.nextInt();
        }
        
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<=n-k; i++){
            HashMap<Integer,Integer> map = new HashMap<>();
            for(int j=i; j<i+k; j++ ){
                map.put(boxes[j], map.getOrDefault(boxes[j],0)+1 );
            }
            PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
                int priorityA = map.get(a) * (int)Math.pow(a,f);
                int priorityB = map.get(b) * (int)Math.pow(b,f);
                if(priorityA != priorityB){
                    return priorityB-priorityA;
                }
                return b-a;
            });
            
            for(int key : map.keySet() ){
                pq.add(key);
            }
            int sum = 0, count=0;
            while(count<x && !pq.isEmpty() ){
                int key = pq.poll();
                sum += key*map.get(key);
                count++;
            }
            list.add(sum);
        }
        System.out.println(list);
    }
}



// // Without using priority Queue, with sliding window
// import java.util.*;
// public class day2program2{
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         int n = sc.nextInt();
//         int k = sc.nextInt();
//         int x = sc.nextInt();
//         int f = sc.nextInt();
//         int[] boxes = new int[n];
//         for(int i=0; i<n; i++){
//             boxes[i]=sc.nextInt();
//         }
//         HashMap<Integer,Integer> map = new HashMap<>();
//         ArrayList<Integer> list = new ArrayList<>();
//         int l = 0;
//         int r = 0;
//         for(int i=0; i<n; i++){
//             map.put(boxes[i], map.getOrDefault(boxes[i],0)+1);
//             if(r-l == k-1){
//                 int res = findResult(map, f, x);
//                 list.add(res);
//                 map.put(boxes[l], map.get(boxes[l])-1);
//                 l++;
//             }
//             r++;
//         }
//         System.out.println(list);
//     }
//     public static int findResult(HashMap<Integer,Integer> map, int f, int x){
//         List<Integer> al = new ArrayList<>(map.keySet());
//         Comparator<Integer> comp = new Comparator<Integer>() {
//             public int compare(Integer a, Integer b){
//                 int p1 = map.get(a)*(int)Math.pow(a,f);
//                 int p2 = map.get(b)*(int)Math.pow(b,f);
//                 if(p1!=p2){
//                     return p2-p1;
//                 }
//                 return b-a;
//             }
//         };
//         Collections.sort(al,comp);
//         int res = 0;
//         int i=0;
//         while(i<x){
//             res += al.get(i)*map.get(al.get(i));
//             i++;
//         }
//         return res;
//     }
// }

