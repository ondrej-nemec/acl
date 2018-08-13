package helper.implementations;

import java.util.List;

import interfaces.Role;
import interfaces.User;

public class TestUser implements User{

	private String id;
	
	private int rank;
	
	private List<Role> roles;
	
	public TestUser(String id, int rank, List<Role> roles) {
		this.id = id;
		this.rank = rank;
		this.roles = roles;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public int getRank() {
		return rank;
	}

	@Override
	public List<Role> getRoles() {
		return roles;
	}

	@Override
	public boolean equals(User user) {
		return id.equals(user.getId());
	}

}
