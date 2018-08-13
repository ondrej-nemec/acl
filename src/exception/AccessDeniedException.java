package exception;

import helper.Action;
import interfaces.Destination;
import interfaces.User;

public class AccessDeniedException extends Exception{

	private static final long serialVersionUID = 1L;

	public AccessDeniedException() {
		super();
	}
	
	public AccessDeniedException(User user, Destination destination, Action action) {
		super(user + ":" + destination + ":" + action);
	}
}
