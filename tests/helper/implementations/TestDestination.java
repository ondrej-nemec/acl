package helper.implementations;

import interfaces.Destination;

public class TestDestination implements Destination {

	private String id;
	
	public TestDestination(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public boolean equals(Destination destination) {
		return id.equals(destination.getId());
	}

}
