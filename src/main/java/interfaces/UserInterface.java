package interfaces;

import java.util.List;

public interface UserInterface {

	public String getId();
	
	public int getRank();
	
	public List<RoleInterface> getRoles();
	
	public boolean equals(UserInterface user);
	
}
