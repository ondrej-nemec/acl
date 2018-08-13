package interfaces;

import java.util.List;

public interface User {

	public String getId();
	
	public int getRank();
	
	public List<Role> getRoles();
	
	public boolean equals(User user);
	
}
