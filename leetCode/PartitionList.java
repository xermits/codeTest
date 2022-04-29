package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartitionList {
	// https://leetcode.com/problems/partition-list/
	public static void main(String[] args) {
		PartitionList a = new PartitionList();

		//ListNode head = a.makeList(Arrays.asList(1,4,3,2,5,2));		
		ListNode head = a.makeList(Arrays.asList());
		ListNode result = a.partition(head, 0);
		
		System.out.println(Arrays.toString(a.toArray(result)));
	}

	private int[] toArray(ListNode input) {
		ArrayList<Integer> result = new ArrayList<>();
		
		ListNode current = input;
		while(current != null){
			result.add(current.val);
			current = current.next;
		}
		
		return result.stream().mapToInt((x)->x).toArray();
	}

	private ListNode makeList(List<Integer> inputArray) {		
		ListNode header = new ListNode(0);
		
		ListNode prev = header;
		
		for(int v : inputArray){
			prev.next = new ListNode(v);
			prev = prev.next;
		}
		
		return header.next;
	}

	public ListNode partition(ListNode start, int x) {
		ArrayList<Integer> lessItems = new ArrayList<>();
		ArrayList<Integer> greaterEqualItems = new ArrayList<>();
		
		ListNode curNode = start;
		while(curNode != null){
			if(curNode.val < x){
				lessItems.add(curNode.val);
			}else{
				greaterEqualItems.add(curNode.val);
			}
			curNode = curNode.next;
		}
		
		ListNode lessHeader = makeList(lessItems);
		ListNode greaterEqualHeader = makeList(greaterEqualItems);
		
		if(lessHeader == null){
			return greaterEqualHeader;
		}
		curNode = lessHeader;
		while(curNode.next != null){
			curNode = curNode.next;
		}
		
		curNode.next = greaterEqualHeader;
		
		return lessHeader;				
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}
}
