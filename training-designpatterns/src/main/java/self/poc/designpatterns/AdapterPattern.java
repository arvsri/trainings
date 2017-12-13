package self.poc.designpatterns;

public class AdapterPattern {
	public static void main(String[] args) {
		System.out.println("Adapter Pattern Demonstration.");
		System.out.println("------------------------------");
		// Create targets.
		System.out.println("Creating targets.");
		TargetIF target1 = new AdapterByClass();
		TargetIF target2 = new AdapterByObject();
		// Call target requests
		System.out.println("Calling targets.");
		System.out.println("target1.newRequest()->" + target1.newRequest());
		System.out.println("target2.newRequest()->" + target2.newRequest());
		System.out.println();
	}
}

interface TargetIF {
	public String newRequest();
}

class Adaptee {
	public Adaptee() {
		System.out.println("Adaptee constructed.");
	}

	String oldRequest() {
		return "Adaptee.oldRequest() called.";
	}
}

class AdapterByClass extends Adaptee implements TargetIF {
	public AdapterByClass() {
		System.out.println("AdapterByClass constructed.");
	}

	public String newRequest() {
		return oldRequest();
	}
}

class AdapterByObject implements TargetIF {
	private Adaptee adaptee;

	public AdapterByObject() {
		System.out.println("AdapterByObject constructed.");
	}

	public String newRequest() {
		// Create an Adaptee object if it doesn't exist yet
		if (adaptee == null) {
			adaptee = new Adaptee();
		}
		return adaptee.oldRequest();
	}
}

