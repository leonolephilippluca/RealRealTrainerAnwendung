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

	private void setBoundaries() {

		setPadding(new Insets(10, 25, 0, 25));
		// setStyle("-fx-base: #ff8080; -fx-background-opacity: 0.5");

		setAlignment(Pos.CENTER);

	}

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

			}

		});

		return button;
	}

}
