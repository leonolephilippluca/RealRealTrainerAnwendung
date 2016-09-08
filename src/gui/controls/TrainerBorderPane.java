package gui.controls;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class TrainerBorderPane extends BorderPane {

	public TrainerBorderPane() {
		setBoundaries();
		initializeComponents();
	}

	private void setBoundaries() {

		setStyle("-fx-background-color: transparent;");

	}

	private void initializeComponents() {
		//
		setLeft(new VBoxLeft());

		setCenter(Container());

	}

	/*
	 * Wrappt die TabView in eine VBox f�r Padding-Anpassungen
	 */
	private VBox Container() {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(30, 0, 0, 0));
		vbox.getChildren().add(TrainerTab.getInstance());

		return vbox;
	}
}
