package self.poc.designpatterns;

public class SingletonPattern {
	public static void main(String[] args) {
		System.out.println("Singleton Pattern Demonstration.");
		System.out.println("--------------------------------");
		System.out.println("Getting Singleton instance (s1)");
		Singleton s1 = Singleton.getInstance();
		System.out.println("s1.getInfo()=" + s1.getInfo());
		System.out.println("Getting Singleton instance (s2)");
		Singleton s2 = Singleton.getInstance();
		System.out.println("s2.getInfo()=" + s2.getInfo());
		System.out.println("s1.setValue(42)");
		s1.setValue(42);
		System.out.println("s1.getValue()=" + s1.getValue());
		System.out.println("s2.getValue()=" + s2.getValue());
		System.out.println("s1.equals(s2)=" + s1.equals(s2)
				+ ", s2.equals(s1)=" + s2.equals(s1));
		// The following will not compile
		// Singleton s3 = (Singleton) s1.clone();
		System.out.println();
	}
}

/*
 * Singletons really are "per classloader" and in a J2EE application, many
 * developers make the mistake of assuming that a singleton really is a
 * singleton in a cluster of application servers. This is not true!
 */
final class Singleton {
	private static Singleton instance;
	private int value;

	private Singleton() {
		System.out.println("Singleton constructed.");
	}

	public static synchronized Singleton getInstance() {
		// if it has not been instantiated yet
		if (instance == null)
			// instantiate it here
			instance = new Singleton();
		return instance;
	}

	// remaining methods are for demo purposes
	// your singleton would have it's business
	// methods here...
	public String getInfo() {
		return getClass().getName() +
		// Uncomment line below to also see the loader
		// +", loaded by " + getClass().getClassLoader();
				", id#" + System.identityHashCode(this);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int parm) {
		value = parm;
	}

	public boolean equals(Singleton parm) {
		return (System.identityHashCode(this) == System.identityHashCode(parm));
	}
}