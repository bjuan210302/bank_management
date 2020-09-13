package model.structures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.structures.Stack;

class StackTest {

	private Stack<String> stack;
	
	private void setupEmptyQueue() {
		stack = new Stack<String>();
	}
	
	@Test
	void testPop() {
		setupEmptyQueue();
		String e1 = "food", e2 = "apple", e3 = "spork";
		stack.push(e1);
		stack.push(e2);
		stack.push(e3);
		
		assertTrue(stack.pop().equals(e3)); //Last to be added is first to be popped
		assertTrue(stack.pop().equals(e2));
		assertTrue(stack.pop().equals(e1)); //First to be added is first to be popped
	}

}
