public class solutions {

	/*
	"Add two numbers II"
	Add two linked lists in the way you would add an int
	*/
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1.val == 0) {
            return l2;
        } else if(l2.val == 0) {
            return l1;
        }

        int[] longer = new int[2];
        longer[0] = -1;

        Stack<ListNode> s1 = new Stack<ListNode>();
        Stack<ListNode> s2 = new Stack<ListNode>();

        addNums(l1, l2, s1, s2, longer);
        ListNode x = new ListNode(1);

        if(longer[0] == 1) {
            if(longer[1] == 1) {
                x.next = l1;
                return x;
            }
            return l1;
        }
        if(longer[1] == 1) {
            x.next = l2;
            return x;
        }
        return l2;
    }

    private void addNums(ListNode l1, ListNode l2, Stack<ListNode> s1, Stack<ListNode> s2, int[] longer) {
        if(l1.next == null && l2.next == null) {
            if(longer[0] == -1) {
                longer[0] = 1;
            }
            if(s1.empty() || s1.peek() != l1) {
                s1.push(l1);
            }
            if(s2.empty() || s2.peek() != l2) {
                s2.push(l2);
            }
        } else if(l1.next == null && l2.next != null) {
            longer[0] = 2;
            if(s1.empty() || s1.peek() != l1) {
                s1.push(l1);
            }
            s2.push(l2);
            addNums(l1, l2.next, s1, s2, longer);
        } else if(l1.next != null && l2.next == null) {
            longer[0] = 1;
            if(s2.empty() || s2.peek() != l2) {
                s2.push(l2);
            }
            s1.push(l1);
            addNums(l1.next, l2, s1, s2, longer);
        } else {
            s1.push(l1);
            s2.push(l2);
            addNums(l1.next, l2.next, s1, s2, longer);
        }
        int x = 0;
        int y = 0;

        if(!s1.empty()) {
            x = s1.pop().val;
        }
        if(!s2.empty()) {
            y = s2.pop().val;
        }

        
        if(longer[0] == 1) {
            l1.val = (x + y + longer[1]) % 10;
            longer[1] = (x + y  + longer[1]) / 10;
        } else {
            l2.val = (x + y + longer[1]) % 10;
            longer[1] = (x + y  + longer[1]) / 10;
        }
    }

    /*
	Repeated substring pattern
	Given a string s, check if it can be constructed by taking a substring and
	repeating it a bunch of times
    */

    public boolean repeatedSubstringPattern(String s) {
        
        for(int i = 1; i <= s.length() / 2; i++) {
            if(s.length() % i == 0) {
                String[] x = s.split(s.substring(0, i));
                if(x.length == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    /*
	Longest consecutive subsequence
	Given an unsorted array of integers nums, return the length of the longest consecutive elements
	sequence.
	Runs in O(n) but saves a whole bunch of memory compared to most.
    */

    public int longestConsecutive(int[] nums) {
        if(nums.length <= 1) {
            return nums.length;
        } else if(nums.length == 2) {
            if(Math.abs(nums[1] - nums[0]) == 1) {
                return 2;
            }
            return 1;
        }



        Map<Integer, Integer> nodes = new TreeMap<Integer, Integer>();

        for(int i = 0; i < nums.length; i++) {
            if(nodes.get(nums[i]) == null) {
                nodes.put(nums[i], 0);
            }
        }
        List<Integer> keys = new ArrayList<Integer>(nodes.keySet());


        int currentSeq = 1;
        int longestSeq = 1;

        for(int i = 1; i < keys.size(); i++) {
            if(keys.get(i) - keys.get(i - 1) == 1) {
                currentSeq ++;
            } else {
                if(currentSeq > longestSeq) {
                    longestSeq = currentSeq;
                }
                currentSeq = 1;
            }
        }

                if(currentSeq > longestSeq) {
                    longestSeq = currentSeq;
                }


        return longestSeq;

    }

    /*
	Rotate array.
	Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
    */

    public void rotate(int[] nums, int k) {
        if(nums.length > 2 && nums.length != k) {
            int[] x = new int[nums.length];
            for(int i = 0; i < k % nums.length; i++) {
                x[i] = nums[nums.length - (k % nums.length) + i];
            }

            for(int i = 0; i < nums.length - (k % nums.length); i++) {
                x[i + (k % nums.length)] = nums[i];
            }

            for(int i = 0; i < nums.length; i++) {
                nums[i] = x[i];
            }
        } else if(nums.length == 2) {
            if(k % 2 == 1) {
                int x = nums[0];
                nums[0] = nums[1];
                nums[1] = x;
            }
        }
    }

    /*
	Jump Game
	You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

	Return true if you can reach the last index, or false otherwise.
    */

    //recursive solution.  Recursive calls in a loop going down
    public boolean canJump(int[] nums) {
        if(nums.length == 1) {
            return true;
        }
        if(nums.length == 2) {
            return nums[0] >= 1;
        }
        return findPath(nums, nums.length - 1);
    }

    private boolean findPath(int[] nums, int index) {
        if(index == 0) {
            return true;
        }
        int distance = 1;
        boolean found = false;
        for(int i = index - 1; i >= 0; i--) {
            if(nums[i] >= distance) {
                found = true;
                break;
            }
            distance ++;
        }
        if(found) {
            return findPath(nums, index - distance);
        }
        return false;
    }

    /*
	Majority element.  Easy enough one.
	Find the element that occurs the most.
    */
    //use map to track occurrences
    //pointers on either side
    public int majorityElement(int[] nums) {
        if(nums.length <= 2) {
            return nums[0];
        }
        Map<Integer, Integer> ocs = new HashMap<Integer, Integer>();

        for(int i = 0; i < (nums.length / 2) + (nums.length % 2); i++) {
            if(ocs.get(nums[i]) == null) {
                ocs.put(nums[i], 1);
            } else {
                ocs.put(nums[i], ocs.get(nums[i]) + 1);
                if(ocs.get(nums[i]) > nums.length / 2) {
                    return nums[i];
                }
            }

            if(ocs.get(nums[nums.length - i - 1]) == null) {
                ocs.put(nums[nums.length - i - 1], 1);
            } else {
                ocs.put(nums[nums.length - i - 1], ocs.get(nums[nums.length - i - 1]) + 1);
                if(ocs.get(nums[nums.length - i - 1]) > nums.length / 2) {
                    return nums[nums.length - i - 1];
                }
            }
        }

        return -1;

    }

    /*
	"Jump Game", not a huge fan of the titles in leetcode
	See if you can jump from the beginning to end over different arrays.
	Each int at index[n] is how far you could possibly jump.
    */
    //recursive solution.  Recursive calls in a loop going down
    public boolean canJump(int[] nums) {
        if(nums.length == 1) {
            return true;
        }
        if(nums.length == 2) {
            return nums[0] >= 1;
        }
        return findPath(nums, nums.length - 1);
    }

    private boolean findPath(int[] nums, int index) {
        if(index == 0) {
            return true;
        }
        int distance = 1;
        boolean found = false;
        for(int i = index - 1; i >= 0; i--) {
            if(nums[i] >= distance) {
                found = true;
                break;
            }
            distance ++;
        }
        if(found) {
            return findPath(nums, index - distance);
        }
        return false;
    }

    /*
	h-index.  Pretty simple one.
	Given an array of integers citations where citations[i] 
	is the number of citations a researcher received for their ith paper, return the researcher's h-index.
    */

    //sort then work backwards
    //
    public int hIndex(int[] citations) {
        Arrays.sort(citations);

        int x = 0;

        for(int i = citations.length - 1; i >= 0; i--) {
            if(citations[i] <= x) {
                break;
            }
            x ++;
        }
        return x;
    }

    /*
    Check if array is sorted and rotated
    */

    //is everything in the first portion of the array bigger than the last portion of the array?  
    //Keep track of min in first portion, if anything bigger than min return false
    public boolean check(int[] nums) {

        if(nums.length == 1 || nums.length == 2) {
            return true;
        }

        int min = nums[0];
        int half = 1;

        for(int i = 1; i < nums.length; i++) {
            if(half == 2) {
                if(nums[i] > min || nums[i] < nums[i - 1]) {
                    return false;
                }
            } else if(nums[i] < nums[i - 1]) {
                if(nums[i] > min) {
                    return false;
                }
                    half = 2;
            } else {
                if(nums[i] < min) {
                    min = nums[i];
                }
            }
        }

        return true;
    }



    /*
    Set Matrix Zeroes
    Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
    */

    //First pass: find zeroes
    //Then, iterate over list of coords and zero out appropriately
    public void setZeroes(int[][] matrix) {
        if(matrix.length == 1 && matrix[0].length == 1) {
            return;
        }
        List<List<Integer>> spots = new ArrayList<List<Integer>>();

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    List<Integer> x = new ArrayList<Integer>();
                    x.add(i);
                    x.add(j);
                    spots.add(x);
                }
            }
        }

        for(int i = 0; i < spots.size(); i++) {
            setZeroes(matrix, spots.get(i).get(1), spots.get(i).get(0));
        }
    }

    private void setZeroes(int[][] matrix, int startX, int startY) {
        int x1 = startX + 1;
        int x2 = startX - 1;
        int y1 = startY + 1;
        int y2 = startY - 1;
        while(x1 < matrix[0].length || x2 >= 0 || y1 < matrix.length || y2 >= 0) {
            if(x1 < matrix[0].length) {
                matrix[startY][x1] = 0;
                x1 ++;
            }
            if(x2 >= 0) {
                matrix[startY][x2] = 0;
                x2 --;
            }
            if(y1 < matrix.length) {
                matrix[y1][startX] = 0;
                y1 ++;
            }
            if(y2 >= 0) {
                matrix[y2][startX] = 0;
                y2 --;
            }
        }
    }

    /*
    Word search: find a word in a 2d array that flows in any direction.
    Good memoization/recursion exericse.
    */

    //First pass: look for starting letters, add to list of coords
    public boolean exist(char[][] board, String word) {
        
        List<List<Integer>> coords = new ArrayList<List<Integer>>();
        char c = word.charAt(0);

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == c) {
                    List<Integer> x = new ArrayList<Integer>();
                    x.add(j);
                    x.add(i);
                    coords.add(x);
                }
            }
        }
        System.out.println(word.length());
        for(int i = 0; i < coords.size(); i++) {
            boolean[][] visited = new boolean[board.length][board[0].length];
            if(findWord(board, word, 0, coords.get(i).get(0), coords.get(i).get(1), visited)) {
                return true;
            }
        }
        return false;
        
    }
    //memoize.  Remember where you're going.
    private boolean findWord(char[][] board, String word, int index, int x, int y, boolean[][] visited) {
        if(x < 0 || x >= board[0].length || y < 0 || y >= board.length || visited[y][x]) {
            return false;
        }
        if(board[y][x] != word.charAt(index)) {
            return false;
        }
        if(index == word.length() - 1) {
            return true;
        }

        visited[y][x] = true;

        if(findWord(board, word, index + 1, x + 1, y, visited)) {
            return true;
        }

        if(findWord(board, word, index + 1, x - 1, y, visited)) {
            return true;
        }

        if(findWord(board, word, index + 1, x, y + 1, visited)) {
            return true;
        }
        
        if(findWord(board, word, index + 1, x, y - 1, visited)) {
            return true;
        }

        visited[y][x] = false;

        return false;
    }

    /*
    Find first and last position of element in sorted array
    Binary search then find points where begin and end
    */

    public int[] searchRange(int[] nums, int target) {
        int[] bounds = new int[]{-1, -1};
        if(nums.length == 0) {
            return bounds;
        } else if(nums.length == 1) {
            if(nums[0] == target) {
                bounds[0] = 0;
                bounds[1] = 0;
            }
            return bounds;
        }
        int index = binSearch(nums, target, 0, nums.length - 1);
        bounds[0] = index;
        bounds[1] = index;
        findLim(nums, index + 1, target, 1, bounds, 1);
        findLim(nums, index - 1, target, -1, bounds, 0);
        return bounds;
    }

    private int binSearch(int[] nums, int target, int min, int max) {
        if(Math.abs(min - max) == 1) {
            System.out.println(min);
            if(nums[min] == target) {
                return min;
            } else if(nums[max] == target) {
                return max;
            }
            return -1;
        }

        if(nums[min] == target) {
            return min;
        } else if(nums[max] == target) {
            return max;
        } else if(nums[(max + min) / 2] == target) {
            return (max + min) / 2;
        } 

        if(nums[(max + min) / 2] < target) {
            min = (max + min) / 2;
        } else {
            max = (max + min) / 2;
        }

        return binSearch(nums, target, min, max);
    }

    private void findLim(int[] nums, int start, int targ, int inc, int[] bounds, int ind) {
        if(start < 0 || start >= nums.length) {
            return;
        }
        if(nums[start] == targ) {
            bounds[ind] = start;
            findLim(nums, start + inc, targ, inc, bounds, ind);
        }
    }


    /*
    Letter Case Permutation
    Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.

    Return a list of all possible strings we could create. Return the output in any order.
    */
    public List<String> letterCasePermutation(String s) {
        List<String> perms = new ArrayList<String>();
        Map<Character, Integer> letters = new HashMap<Character, Integer>();
        for(char c = 'a'; c <= 'z'; c++) {
            letters.put(c, 1);
        }
        for(char c = 'A'; c <= 'Z'; c++) {
            letters.put(c, 1);
        }
        findPerms(perms, s, 0, letters);
        return perms;
    }

    private void findPerms(List<String> perms, String s, int index, Map<Character, Integer> letters) {
        if(index == s.length()) {
            perms.add(s);
            return;
        }
        if(letters.get(s.charAt(index)) != null) {
            char c = s.charAt(index);
            String upper = s.substring(0, index) + Character.toUpperCase(c) + s.substring(index + 1, s.length());
            findPerms(perms, upper, index + 1, letters);
            String lower = s.substring(0, index) + Character.toLowerCase(c) + s.substring(index + 1, s.length());
            findPerms(perms, lower, index + 1, letters);
        } else {
            findPerms(perms, s, index + 1, letters);
        }
    }

    /*
    Find Largest Value in Each Tree Row
    Brute force.  Use a map, track vals at depths.
    */

    public List<Integer> largestValues(TreeNode root) {
        Map<Integer, Integer> rowMax = new TreeMap<Integer, Integer>();
        findMax(root, rowMax, 0);
        List<Integer> maxes = new ArrayList<Integer>();
        for(Integer x : rowMax.keySet()) {
            maxes.add(rowMax.get(x));
        }
        return maxes;
    }

    private void findMax(TreeNode cur, Map<Integer, Integer> maxes, int level) {
        if(cur == null) {
            return;
        }
        if(maxes.get(level) == null) {
            maxes.put(level, cur.val);
        }
        if(maxes.get(level) < cur.val) {
            maxes.put(level, cur.val);
        }
        findMax(cur.left, maxes, level + 1);
        findMax(cur.right, maxes, level + 1);
    }

    /*
    Flatten Binary Tree into Linked List
    Given the root of a binary tree, flatten the tree into a "linked list":

    The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
    The "linked list" should be in the same order as a pre-order traversal of the binary tree.

    */

    //Grab everything from left, put it in right, go to end of new right and add old right
    public void flatten(TreeNode root) {
        if(root != null) {
            TreeNode x = root;
            while(x.left != null || x.right != null) {
                TreeNode y = x.right;
                x.right = x.left;
                x.left = null;
                if(y != null) {
                    plantRight(x, y);
                }
                x = x.right;
            }
        }
    }

    private void plantRight(TreeNode root, TreeNode toPlant) {
        while(root.right != null) {
            root = root.right;
        }
        root.right = toPlant;
    }


/*
Validate Binary Search Tree
Just make sure a given tree is a valid BST.  In order traversal is pretty much made for this.
*/

 //I'm overthinking this.  The only thing to check is if an in-order traversal produces a 
 //continually increasing list.
    public boolean isValidBST(TreeNode root) {
        int[] lastN = new int[1];
        lastN[0] = Integer.MIN_VALUE;
        List<Integer> x = new ArrayList<Integer>();
        return inOrder(root, x);
    }

    private boolean inOrder(TreeNode root, List<Integer> x) {
        if(root == null) {
            return true;
        }
        if(!inOrder(root.left, x)) {
            return false;
        }
        if(x.size() > 0 && root.val <= x.get(x.size() - 1)) {
            return false;
        }
        x.add(root.val);
        return inOrder(root.right, x);
    }

    /*
    Swap kth node with kth from end node value in linked list
    */

    public ListNode swapNodes(ListNode head, int k) {
        int x = 1;
        ListNode cur = head;
        if(k >= 1) {
            while(x < k) {
                cur = cur.next;
                x++;
            }
            ListNode start = head;
            swapKth(start, cur, k);
        }

        return head;
    }

    private int swapKth(ListNode cur, ListNode toSwap, int k) {
        if(cur.next == null) {
            if(k == 1) {
                int x = cur.val;
                cur.val = toSwap.val;
                toSwap.val = x;
            }
            return 2;
        } else {
            int z = swapKth(cur.next, toSwap, k);
            if((z == k)) {
                int x = cur.val;
                cur.val = toSwap.val;
                toSwap.val = x;
            }
            return z + 1;
        }

    }

    /*
    Finding the center of a star graph.  Easy.
    */
    //Just find the first edge that recurs twice
    public int findCenter(int[][] edges) {
        Map<Integer, Integer> foundEdges = new HashMap<Integer, Integer>();
        for(int i = 0; i < edges.length; i++) {
            if(foundEdges.get(edges[i][0]) != null) {
                return edges[i][0];
            }
            if(foundEdges.get(edges[i][1]) != null) {
                return edges[i][1];
            }
            foundEdges.put(edges[i][1], 1);
            foundEdges.put(edges[i][0], 1);
        }
        return -1;
    }

    /*
    Clone graph - return a deep clone of the graph passed in via the head node.
    */

     public Node cloneGraph(Node node) {
        if(node == null) {
            return node;
        }
        Node newNode = new Node(node.val);
        Map<Integer, Node> visited = new HashMap<Integer, Node>();
        makeClone(node, newNode, visited);
        return newNode;
    }

    private void makeClone(Node oldNode, Node newNode, Map<Integer, Node> visited) {
        if(oldNode != null && visited.get(oldNode.val) == null) {
            visited.put(oldNode.val, newNode);
            List<Node> newNeighbors = new ArrayList<Node>();
            for(int i = 0; i < oldNode.neighbors.size(); i++) {
                Node neighbor = new Node(oldNode.neighbors.get(i).val);
                if(visited.get(neighbor.val) != null) {
                    newNeighbors.add(visited.get(neighbor.val));
                } else {
                    newNeighbors.add(neighbor);
                }
                makeClone(oldNode.neighbors.get(i), newNeighbors.get(i), visited);
            }
            newNode.neighbors = newNeighbors;
        } 
    }

    /*
    
    Roman numeral to integer converter.  brute force.

    */

    public int romanToInt(String s) {
        int sum = 0;
        while(s.contains("CM")) {
            sum += 900;
            s = s.replaceFirst("CM", "");
        }
        while(s.contains("CD")) {
            sum += 400;
            s = s.replaceFirst("CD", "");
        }
        while(s.contains("XC")) {
            sum += 90;
            s = s.replaceFirst("XC", "");
        }
        while(s.contains("XL")) {
            sum += 40;
            s = s.replaceFirst("XL", "");
        }
        while(s.contains("IX")) {
            sum += 9;
            s = s.replaceFirst("IX", "");
        }
        while(s.contains("IV")) {
            sum += 4;
            s = s.replaceFirst("IV", "");
        }

        while(s.contains("M")) {
            sum += 1000;
            s = s.replaceFirst("M", "");
        }
        while(s.contains("D")) {
            sum += 500;
            s = s.replaceFirst("D", "");
        }
        while(s.contains("C")) {
            sum += 100;
            s = s.replaceFirst("C", "");
        }
        while(s.contains("L")) {
            sum += 50;
            s = s.replaceFirst("L", "");
        }
        while(s.contains("X")) {
            sum += 10;
            s = s.replaceFirst("X", "");
        }
        while(s.contains("V")) {
            sum += 5;
            s = s.replaceFirst("V", "");
        }
        while(s.contains("I")) {
            sum += 1;
            s = s.replaceFirst("I", "");
        }
        return sum;
    }

    /*
    Quick sort implementation
    */

    public void sortColors(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int start, int end) {
        if(start == end || start > end) {
            return;
        }
        
        int part = partition(nums, start, end);
        
        quickSort(nums, start, part - 1);
        quickSort(nums, part + 1, end);
    }

    private int partition(int[] nums, int begin, int end) {
        int i = (begin-1);
        int swapTemp = 0;

        for (int j = begin; j < end; j++) {
            if (nums[j] <= nums[end]) {
                i++;

                swapTemp = nums[i];
                nums[i] = nums[j];
                nums[j] = swapTemp;
            }
        }

        swapTemp = nums[i+1];
        nums[i+1] = nums[end];
        nums[end] = swapTemp;

        return i+1;
    }

}

/*
Insert Delete GetRandom O(1)
Nice little class implementation test.
*/

class RandomizedSet {

    public RandomizedSet() {
        x = new HashMap<Integer, Integer>();
    }
    
    public boolean insert(int val) {
        return x.put(val, 1) == null;
    }
    
    public boolean remove(int val) {
        return x.remove(val) != null;
    }
    
    public int getRandom() {
        List<Integer> z = new ArrayList<Integer>(x.keySet());
        int y = (int)(Math.random() * (double)z.size());
        return z.get(y);
    }

    private Map<Integer, Integer> x;
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */


/*
Spiral Matrix I
Given an m x n matrix, return all elements of the matrix in spiral order.
*/


class Direction {
    public String next = "";
    public int x = 0;
    public int y = 0;

    public Direction(String s, int d1, int d2) {
        next = s;
        y = d1;
        x = d2;
    }
}   

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix.length;
        List<Integer> mat = new ArrayList<Integer>();
        Map<String, Direction> dirs = new HashMap<String, Direction>();
        dirs.put("right", new Direction("down", 0, 1));
        dirs.put("down", new Direction("left", 1, 0));
        dirs.put("left", new Direction("up", 0, -1));
        dirs.put("up", new Direction("right", -1, 0));
        Direction current = dirs.get("right");
        int cx = 0;
        int cy = 0;
        
        for(int i = 1; i <= (matrix.length * matrix[0].length); i++) {
            mat.add(matrix[cy][cx]);
            matrix[cy][cx] = -101;
            if(
                cy + current.y >= matrix.length ||
                cy + current.y < 0 ||
                cx + current.x >= matrix[0].length || 
                cx + current.x < 0 ||
                matrix[cy + current.y][cx + current.x] == -101
            ) {
                System.out.println(cy + current.y);
                System.out.println(cx + current.x);
                current = dirs.get(current.next);
            }
            cy += current.y;
            cx += current.x;

        }
        return mat;
    }
}


/*
Spiral Matrix II
Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

Look, an actual opportunity to use OOP!
*/
class Direction {
    public String next = "";
    public int x = 0;
    public int y = 0;

    public Direction(String s, int d1, int d2) {
        next = s;
        y = d1;
        x = d2;
    }
}

class Solution {
    //Use map for addition directions
    //And switching - map of arrs
    //Keep track of place by seeing if you're
    //at n^2 yet

    public int[][] generateMatrix(int n) {
        int[][] mat = new int[n][n];
        Map<String, Direction> dirs = new HashMap<String, Direction>();
        dirs.put("right", new Direction("down", 0, 1));
        dirs.put("down", new Direction("left", 1, 0));
        dirs.put("left", new Direction("up", 0, -1));
        dirs.put("up", new Direction("right", -1, 0));
        Direction current = dirs.get("right");
        int cx = 0;
        int cy = 0;

        for(int i = 1; i <= (n * n); i++) {
            mat[cy][cx] = i;
            if(
                cy + current.y >= n ||
                cy + current.y < 0 ||
                cx + current.x >= n || 
                cx + current.x < 0 ||
                mat[cy + current.y][cx + current.x] != 0
            ) {
                current = dirs.get(current.next);
            }
            cy += current.y;
            cx += current.x;

        }
        return mat;
    }
}

/*
Implement a stack with a queue.  Why?  Dunno.
*/

class MyStack {

    Queue<Integer> q;

    public MyStack() {
        q = new LinkedList<Integer>();
    }
    
    public void push(int x) {
        q.add(x);
        for(int i = 1; i < q.size(); i++) {
            int z = q.remove();
            q.add(z);
        }
    }
    
    public int pop() {
        return q.remove();
    }
    
    public int top() {
        return q.peek();
    }
    
    public boolean empty() {
        return q.size() == 0;
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */