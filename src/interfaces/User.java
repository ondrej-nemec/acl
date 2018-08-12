package interfaces;

import java.util.Set;

public interface User {

	public String getId();
	
	public int getRank();
	
	public Set<Role> getRoles();
	
}
