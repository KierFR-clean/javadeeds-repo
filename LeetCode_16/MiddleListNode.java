package LeetCode_16;

public class MiddleListNode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sn = new Solution();
		ListNode ptrfi = new ListNode(5);
		ListNode ptrfo = new ListNode(4, ptrfi);
		ListNode ptrth = new ListNode(3, ptrfo);
		ListNode ptrtwo = new ListNode(2, ptrth);
		ListNode ptr = new ListNode(1, ptrtwo);

		
		ptr.print();
		System.out.println(sn.middleNode(ptr));
		
	}
	
	
}
class ListNode {
	int val;
	ListNode next;
	
	public ListNode(int val) {
		this.val = val;
	}
	
	public ListNode(int val , ListNode next) {
		this.val = val;
		this.next = next;
	}
	
	public String toString() {
		return "" + val;
	}
	
	public void print() {
		ListNode c = this;
		
		while (c != null) {
			System.out.print(c.val + (c.next != null ? "->":"->null"));
			c = c.next;
		}
	}
}

class Solution {
	public ListNode middleNode(ListNode head) {
        ListNode s = head;
        ListNode f = head;
        
        while (f != null && f.next != null) {
 

        	
            s = s.next;
            f = f.next.next;
        }

        return s;
    }
}

