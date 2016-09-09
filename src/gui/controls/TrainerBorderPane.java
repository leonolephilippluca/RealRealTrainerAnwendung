package gui.controls;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class TrainerBorderPane extends BorderPane {

	public TrainerBorderPane() {
		setBoundaries();
		initializeComponents();
	}
	
	/**
	 * Methode um Gr��e und Abstand der jeweiligen Komponenten festzulegen
	 */
	private void setBoundaries() {

		setStyle("-fx-background-color: transparent;");

	}

	/**
	 * Methode zum initialisieren der Komponenten
	 */
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
