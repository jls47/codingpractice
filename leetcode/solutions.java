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
}