package helper.implementations;

import java.util.List;

import interfaces.RoleInterface;
import interfaces.UserInterface;

public class TestUser implements UserInterface{

	private String id;
	
	private int rank;
	
	private List<RoleInterface> roles;
	
	public TestUser(String id, int rank, List<RoleInterface> roles) {
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
	public List<RoleInterface> getRoles() {
		return roles;
	}

	@Override
	public boolean equals(UserInterface user) {
		return id.equals(user.getId());
	}
	@Override
	public String toString() {
		return id;
	}
}
