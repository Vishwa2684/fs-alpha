// In an Intelligence Agency, each senior officer supervises either two junior officers 
// or none. The senior officer is assigned a clearance level equal to the higher clearance 
// level of the two junior officers they supervise.

// The clearance levels are represented as integer values in the range [1, 50], and 
// multiple officers may have the same clearance level.

// At the end, all officers (senior and junior) are collectively referred to as 
// agents in the system.

// You are provided with a hierarchical clearance level tree where each node represents 
// an officer's clearance level. The tree structure follows these rules:
// 	- If a node has two children, its clearance level is the maximum of the two children's
// 	  clearance levels.
// 	- If a node has no children, it's clearance level is same as exists.
// 	- The value -1 indicates an empty (null) position.
// Your task is to find the second highest clearance level among all agents in the agency. 
// If no such level exists, return -2.

// Input Format:
// -------------
// A single line of space separated integers, clearance levels of each individual.

// Output Format:
// --------------
// Print an integer, second top agent based on rank.


// Sample Input-1:
// ---------------
// 5 5 4 -1 -1 2 4

// Sample Output-1:
// ----------------
// 4


// Sample Input-2:
// ---------------
// 3 3 3 3 3

// Sample Output-2:
// ----------------
// -2



import java.util.*;
class TreeNode{
    int data;
    TreeNode left;
    TreeNode right;
    TreeNode(int data){
        this.data = data;
        left = null;
        right = null;
    }
}
public class day08program3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int n=s.length;
        int[] nums = new int[n];
        for(int i=0; i<n; i++) nums[i]=Integer.parseInt(s[i]);
        
        TreeNode root = buildTree(nums);

        int secondMax = findSecondMaxOf(root);
        System.out.println(secondMax);
    }

    public static TreeNode buildTree(int[] nums){
        int n = nums.length;
        TreeNode root = new TreeNode(nums[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int i=1;
        while(!q.isEmpty() && i<n){
            TreeNode curr = q.poll();
            if(nums[i]!=-1){
                curr.left = new TreeNode(nums[i]);
                q.offer(curr.left);
            }
            i++;
            if(i<n && nums[i]!=-1){
                curr.right = new TreeNode(nums[i]);
                q.offer(curr.right);
            }
            i++;
        }

        return root;
    }

    public static int findSecondMaxOf(TreeNode root){
        
        int max = Integer.MIN_VALUE;
        

        return max;
    }
}
