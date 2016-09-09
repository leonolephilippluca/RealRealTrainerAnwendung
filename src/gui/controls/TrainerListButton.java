package gui.controls;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import mockup.Trainer;

public class TrainerListButton extends HBox {

	public TrainerListButton(Trainer t) {
		setBoundaries();
		initializeComponents(t);
	}
	
	/**
	 * Methode um Größe und Abstand der jeweiligen Komponenten festzulegen
	 */
	private void setBoundaries() {

		setPadding(new Insets(10, 25, 0, 25));

		setAlignment(Pos.CENTER);

	}

	/**
	 * Methode zum initialisieren der Komponenten
	 */
	private void initializeComponents(Trainer t) {

		Button b = mainButton(t);
		getChildren().add(b);

	}

	private Button mainButton(Trainer t) {

		Button button = new Button(t.getName());
		button.setPrefWidth(300);
		button.setPrefHeight(65);
		button.setId("trainerbutton");

		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				TrainerTab.getInstance().addNewTab(t);
				//FIXME: irgendwie unschön, aber vorübergehend brauchbar.
				TrainerBorderPane pane = (TrainerBorderPane) getParent().getParent().getParent().getParent().getParent().getParent();
				pane.setCenter(TrainerTab.getInstance());

			}

		});

		return button;
	}

}
