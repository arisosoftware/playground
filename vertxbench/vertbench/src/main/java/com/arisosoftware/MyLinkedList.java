package com.arisosoftware;
 
import java.util.ArrayList;
import java.util.List;

public class MyLinkedList<T> implements Iterable<T> {
	private int size = 0;
	private LinkedNode<T> head = null;
	private LinkedNode<T> tail = null;

// Internal node class to represent data
// Empty this linked list, O(n)
	public void clear() {
		LinkedNode<T> trav = head;
		while (trav != null) {
			LinkedNode<T> next = trav.next;
			trav.prev = trav.next = null;
			trav.data = null;
			trav = next;
		}
		head = tail = trav = null;
		size = 0;
	}

// Return the size of this linked list
	public int size() {
		return size;
	}

// Is this linked list empty?
	public boolean isEmpty() {
		return size() == 0;
	}

// Add an element to the tail of the linked list, O(1)
	public void add(T elem) {
		addLast(elem);
	}

// Add a node to the tail of the linked list, O(1)
	public void addLast(T elem) {
// The linked list is empty
		if (isEmpty()) {
			head = tail = new LinkedNode<T>(elem, null, null);
		} else {
			tail.next = new LinkedNode<T>(elem, tail, null);
			tail = tail.next;
		}
		size++;
	}
	
	public void addAll(List<T> list)
	{
	    for(int i=0;i<list.size();i++)
		{
	    	addLast(list.get(i));
		}		
	}

	public void deleteAll(List<LinkedNode> list)
	{
		   for(int i=0;i<list.size();i++)
			{
			    this.remove(list.get(i));
			}		
	}
	
// Add an element to the beginning of this linked list, O(1)
	public void addFirst(T elem) {
// The linked list is empty
		if (isEmpty()) {
			head = tail = new LinkedNode<T>(elem, null, null);
		} else {
			head.prev = new LinkedNode<T>(elem, null, head);
			head = head.prev;
		}
		size++;
	}

// Check the value of the first node if it exists, O(1)
	public T peekFirst() {
		if (isEmpty())
			return null;
		return head.data;
	}

	public LinkedNode getFirstNode() {
		if (isEmpty())
			return null;
		return head;
	}

	public void RemoveNode(LinkedNode node) {
		if (node.prev==null)
		{
			node = node.next;
		}
		else 
		{
			node.prev = node.next;
		}
		 
		--size;
		if (isEmpty())
			tail = null;
		else
			node.prev = null;
	}

// Check the value of the last node if it exists, O(1)
	public T peekLast() {
		if (isEmpty())
			throw new RuntimeException("Empty list");
		return tail.data;
	}

// Remove the first value at the head of the linked list, O(1)
	public T removeFirst() {
// Can't remove data from an empty list -_-
		if (isEmpty())
			throw new RuntimeException("Empty list");
// Extract the data at the head and move
// the head pointer forwards one node
		T data = head.data;
		head = head.next;
		--size;
// If the list is empty set the tail to null
		if (isEmpty())
			tail = null;
// Do a memory cleanup of the previous node
		else
			head.prev = null;
// Return the data that was at the first node we just removed
		return data;
	}

// Remove the last value at the tail of the linked list, O(1)
	public T removeLast() {
// Can't remove data from an empty list -_-
		if (isEmpty())
			throw new RuntimeException("Empty list");
// Extract the data at the tail and move
// the tail pointer backwards one node
		T data = tail.data;
		tail = tail.prev;
		--size;
// If the list is now empty set the head to null
		if (isEmpty())
			head = null;
// Do a memory clean of the node that was just removed
		else
			tail.next = null;
// Return the data that was in the last node we just removed
		return data;
	}

// Remove an arbitrary node from the linked list, O(1)
	private T remove(LinkedNode<T> node) {
// If the node to remove is somewhere either at the
// head or the tail handle those independently
		if (node.prev == null)
			return removeFirst();
		if (node.next == null)
			return removeLast();
// Make the pointers of adjacent nodes skip over 'node'
		node.next.prev = node.prev;
		node.prev.next = node.next;
// Temporarily store the data we want to return
		T data = node.data;
// Memory cleanup
		node.data = null;
		node = node.prev = node.next = null;
		--size;
// Return the data in the node we just removed
		return data;
	}

// Remove a node at a particular index, O(n)
	public T removeAt(int index) {
// Make sure the index provided is valid -_-
		if (index < 0 || index >= size)
			throw new IllegalArgumentException();
		int i;
		LinkedNode<T> trav;
// Search from the front of the list
		if (index < size / 2) {
			for (i = 0, trav = head; i != index; i++)
				trav = trav.next;
// Search from the back of the list
		} else
			for (i = size - 1, trav = tail; i != index; i--)
				trav = trav.prev;
		return remove(trav);
	}

// Remove a particular value in the linked list, O(n)
	public boolean remove(Object obj) {
		LinkedNode<T> trav = head;
// Support searching for null
		if (obj == null) {
			for (trav = head; trav != null; trav = trav.next) {
				if (trav.data == null) {
					remove(trav);
					return true;
				}
			}
// Search for non null object
		} else {
			for (trav = head; trav != null; trav = trav.next) {
				if (obj.equals(trav.data)) {
					remove(trav);
					return true;
				}
			}
		}
		return false;
	}

// Find the index of a particular value in the linked list, O(n)
	public int indexOf(Object obj) {
		int index = 0;
		LinkedNode<T> trav = head;
// Support searching for null
		if (obj == null) {
			for (; trav != null; trav = trav.next, index++)
				if (trav.data == null)
					return index;
// Search for non null object
		} else
			for (; trav != null; trav = trav.next, index++)
				if (obj.equals(trav.data))
					return index;
		return -1;
	}

// Check is a value is contained within the linked list
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}

	@Override
	public java.util.Iterator<T> iterator() {
		return new java.util.Iterator<T>() {
			private LinkedNode<T> trav = head;

			@Override
			public boolean hasNext() {
				return trav != null;
			}

			@Override
			public T next() {
				T data = trav.data;
				trav = trav.next;
				return data;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		LinkedNode<T> trav = head;
		while (trav != null) {
			sb.append(trav.data + ", ");
			trav = trav.next;
		}
		sb.append(" ]");
		return sb.toString();
	}
}
