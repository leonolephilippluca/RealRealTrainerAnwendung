package mockup;
/**
 * 
 * @author pmoritzer
 * Klasse um Trainerobjekte zu erzeugen ohne Datenanbindung für Frontendtests
 */
public class Trainer {

	private int id;
	private String name;
	private int age;
	private int experience;

	public Trainer(int id, String name, int age, int experience) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.experience = experience;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getId() {
		return id;
	}

}
