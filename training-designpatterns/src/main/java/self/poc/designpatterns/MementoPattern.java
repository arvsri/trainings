package self.poc.designpatterns;

public class MementoPattern {
	public static void main(String[] args) {
		System.out.println("Memento Pattern Demonstration.");
		System.out.println("-------------------------------");
		// Run the caretaker
		Caretaker.run();
		System.out.println();
	}
}

class Caretaker {
	public static void run() {
		// Create originator and set initial values.
		System.out.println("Creating originator and setting initial values.");
		Originator originator = new Originator();
		originator.setState(true);
		originator.setName("The Originator");
		// Create memento.
		System.out.println("Creating memento.");
		Memento memento = originator.createMemento();
		System.out.println(originator);
		// Change originator values.
		System.out.println("Changing originator values.");
		originator.setState(false);
		originator.setName("To be undone.");
		System.out.println(originator);
		// Recover state from memento.
		System.out.println("Recovering originator values from memento.");
		originator.recoverFromMemento(memento);
		System.out.println(originator);
	}
}

class Memento {
	private boolean state;
	private String name;

	Memento(boolean parm1, String parm2) {
		this.state = parm1;
		this.name = parm2;
	}

	boolean getState() {
		return this.state;
	}

	String getName() {
		return this.name;
	}
}

class Originator {
	private boolean state;
	private String name;
	private String other;

	// Create memento, save critical data in it.
	public Memento createMemento() {
		return new Memento(state, name);
	}

	// Recover critical data from memento.
	public void recoverFromMemento(Memento memento) {
		this.state = memento.getState();
		this.name = memento.getName();
	}

	public void setState(boolean parm) {
		this.state = parm;
	}

	public void setName(String parm) {
		this.name = parm;
	}

	public String toString() {
		return "Originator.toString() state=" + state + ", name=" + name;
	}
}