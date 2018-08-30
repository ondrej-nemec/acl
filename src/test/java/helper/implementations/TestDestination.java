package helper.implementations;

import interfaces.DestinationInterface;

public class TestDestination implements DestinationInterface {

	private String id;
	
	public TestDestination(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public boolean equals(DestinationInterface destination) {
		return id.equals(destination.getId());
	}

	@Override
	public String toString() {
		return id;
	}
}
