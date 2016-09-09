package gui.controls;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import mockup.Controller;
import mockup.Trainer;
import util.TextLimiter;

/**
 * Control für die Neuerstellung eines Trainers
 */
public class NewTrainerForm extends VBox {

	/**
	 * Konstruktor no args
	 */
	public NewTrainerForm() {

		setBoundaries();
		initializeComponents();

	}

	/**
	 * Methode um Größe und Abstand der jeweiligen Komponenten festzulegen
	 */
	private void setBoundaries() {

		setAlignment(Pos.CENTER);
		setSpacing(75);

	}

	/**
	 * Methode zum initialisieren der Komponenten
	 */
	private void initializeComponents() {

		TextField name = txt();
		Spinner<Integer> id = spinner("id");
		Spinner<Integer> age = spinner("age");
		Spinner<Integer> experience = spinner("experience");

		getChildren().add(NestedVBox(new Label("ID"), new Separator(), id));
		getChildren().add(NestedVBox(new Label("Name"), new Separator(), name));
		getChildren().add(NestedVBox(new Label("Alter"), new Separator(), age));
		getChildren().add(NestedVBox(new Label("Erfahrung"), new Separator(), experience));
		HBox hbox = new HBox();
		Button confirm = confirm("Abschließen");
		confirm.setId("trainerbutton");
		hbox.getChildren().add(confirm);
		hbox.setAlignment(Pos.CENTER);

		confirm.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				// TODO: NUR MOCKUP!!! MIT RICHTIGER BACKEND METHODE
				// VERKNÜPFEN

				try {
					Controller.newTrainer(id.getValue(), name.getText(), age.getValue(), experience.getValue());

					TrainerTab.getInstance().addNewTab(
							new Trainer(id.getValue(), name.getText(), age.getValue(), experience.getValue()));
					TrainerBorderPane pane = (TrainerBorderPane) getParent();
					pane.setCenter(TrainerTab.getInstance());
					pane.setLeft(new VBoxLeft());
				} catch (Exception e) {

					getChildren().add(new Text("ist falsch"));

				}

			}
		});

		getChildren().add(hbox);
	}

	/**
	 * Methode um ein Segment (Überschrift + einzugebenes Feld) für die Form der
	 * Neuerstellung eines Trainers zu erstellen
	 * 
	 * @param label
	 *            Überschrift zum jeweiligen einzutragenden Feld
	 * @param separator
	 *            Strich dadrunter
	 * @param node
	 *            Textfeld zur Eingabe der Trainerinformationen / Slider für
	 *            Erfahrung
	 * @return ein Viertel der großen VBox für die Form
	 */
	private VBox NestedVBox(Label label, Separator separator, Node node) {

		label.setStyle("-fx-text-fill: grey");

		if (node instanceof TextField) {
			TextLimiter.limit((TextField) node, 40);

		}

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(0, 250, 0, 250));
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().add(label);
		vbox.getChildren().add(separator);
		vbox.getChildren().add(node);

		return vbox;
	}

	private TextField txt() {
		TextField txt = new TextField();
		return txt;

	}

	/**
	 * Button, der die Neuerstellung eines Trainers abschließt + Clientseitige
	 * Validierung der Form
	 * 
	 * @param text
	 *            der Text des Buttons
	 * @return den Button
	 */
	private Button confirm(String text) {
		Button button = new Button(text);

		return button;
	}

	/**
	 * @param "id",
	 *            "age", "experience" allowed
	 * @return Spinner
	 */
	private Spinner<Integer> spinner(String type) {

		Spinner<Integer> spinner = new Spinner<Integer>();

		SpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000,
				Controller.trainerlist.size() + 1);

		if (type.equals("age")) {

			svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 50);

		} else if (type.equals("experience")) {

			svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 5);

		}
		spinner.setValueFactory(svf);
		spinner.setEditable(false);

		spinner.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue.equals("\\d*")) {
				spinner.getValueFactory().setValue(5);
				
			}
			if (!newValue) {
				spinner.increment(0);
			}

		});

		return spinner;

	}

}
