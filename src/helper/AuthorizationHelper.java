package helper;

import exception.AccessDeniedException;
import interfaces.Destination;
import interfaces.User;
import rules.Rules;

public class AuthorizationHelper {

	private Rules rules;
	
	public AuthorizationHelper(final Rules rules) {  //TODO logger, log every action
		this.rules = rules;
	}
	
	public boolean isAllowed(final User who, final Destination where, final Action what) {
		
		
		
		throw new UnsupportedOperationException();
	}
	
	public void throwIfIsNotAllowed(final User who, final Destination where, final Action what) throws AccessDeniedException {
		if(!isAllowed(who, where, what))
			throw new AccessDeniedException(who, where, what);
	}
}
