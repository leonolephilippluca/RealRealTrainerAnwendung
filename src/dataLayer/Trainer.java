package dataLayer;

public class Trainer implements businessObjects.ITrainer {
	
	private Integer id;
	private String name;
	private int age;
	private int experience;
	
	public Trainer() {
		
	}
	
	public Trainer(int id) {
		this.setId(id);
	}
	
	/**
	 * @return returns -1, if id has not been set.
	 */
	public int getId() {
		if(this.id != null){
			return id;
		}
		else return -1;
	}
	
	/**
	 * Id can only be set once.
	 * @param id 
	 * @return returns false if id is already set.
	 */
	public boolean setId(int id) {
		//Id can only be set once. if id exists returns false;
		if(this.id == null){
			this.id = id;
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExperience() {
		return this.experience;
	}

	public void setExperience(int exp) {
		this.experience = exp;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;		
	}
}
