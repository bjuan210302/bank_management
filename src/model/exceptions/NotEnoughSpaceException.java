package model.exceptions;

public class NotEnoughSpaceException extends Exception {

	private int maxItems;
	
	public NotEnoughSpaceException(int maxItems) {
		super();
		this.maxItems = maxItems;
	}

	public int getMaxItems() {
		return maxItems;
	}
	
}
