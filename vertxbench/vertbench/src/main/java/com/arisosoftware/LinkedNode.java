package com.arisosoftware;

public class LinkedNode<T> {
	T data;
	public LinkedNode<T> prev, next;

	public LinkedNode(T data, LinkedNode<T> prev, LinkedNode<T> next) {
		this.data = data;
		this.prev = prev;
		this.next = next;
	}

	public T getData() {
		return data;
	}
}