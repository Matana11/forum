package forum.kaoyan.qinghuagong;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Test {
    public int[] reversePrint(ListNode head) {
        Stack<ListNode> stack=new Stack<ListNode>();
        ListNode temp=head;
        while(temp!=null){
            stack.push(temp);
            temp=temp.next;
        }
        int size=stack.size();
        int[] reverse = new int[size];
        for(int i=0;i<size;i++){
            reverse[i]=stack.pop().val;

        }
        return reverse;
    }

    public static void main(String args[]){
        Test test=new Test();
        int[] head=new int[]{1,2,3};
        System.out.println();
    }
}
