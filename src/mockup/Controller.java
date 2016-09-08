package mockup;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author pmoritzer
 * 
 *         MockupController um Trainer zu erstellen und eine Liste von allen
 *         Trainern zu bekommen
 */
public class Controller {

	public static List<Trainer> trainerlist = new ArrayList<Trainer>();

	/**
	 * Methode um neuen Mockup-Trainer über User-Interface zu erstellen und zur
	 * Liste hinzuzufügen
	 */
	public static void newTrainer(int Id, String name, int age, int experience) {

		Trainer t = new Trainer(Id, name, age, experience);
		trainerlist.add(t);

	}

	/**
	 * Testliste füllen
	 */
	public static void fillTestData() {

		trainerlist.add(new Trainer(1, "Omega Eins", 52, 1));
		trainerlist.add(new Trainer(2, "Omega Zwei", 52, 2));
		trainerlist.add(new Trainer(3, "Omega Drei", 52, 3));
		trainerlist.add(new Trainer(3, "Omega Vier", 52, 4));
		trainerlist.add(new Trainer(3, "Omega Fünf", 52, 5));
		trainerlist.add(new Trainer(3, "Omega Sechs", 52, 6));
		trainerlist.add(new Trainer(3, "Omega Sieben", 52, 7));
		trainerlist.add(new Trainer(3, "Omega Acht", 52, 8));
		trainerlist.add(new Trainer(3, "Omega Neun", 52, 9));
		trainerlist.add(new Trainer(3, "Omega Zehn", 52, 10));
	}

}
