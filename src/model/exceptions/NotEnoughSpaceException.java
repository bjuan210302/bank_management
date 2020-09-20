package model.exceptions;

public class NotEnoughSpaceException extends Exception {

	private int maxItems;
	private int registeredItems;
	
	public NotEnoughSpaceException(int maxItems, int registeredItems) {
		super();
		this.maxItems = maxItems;
		this.registeredItems = registeredItems;
	}

	public int getMaxItems() {
		return maxItems;
	}

	public int getRegisteredItems() {
		return registeredItems;
	}
	
	
}
