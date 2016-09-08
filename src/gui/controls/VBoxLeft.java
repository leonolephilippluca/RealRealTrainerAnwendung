package gui.controls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import mockup.Controller;
import mockup.Trainer;

public class VBoxLeft extends VBox {

	public VBoxLeft() {

		setBoundaries();
		initializeComponents();
	}

	private void setBoundaries() {

		setStyle(
				"-fx-border-style: solid; -fx-border-width: 1px; -fx-border-color: transparent #d3d3d3 transparent transparent");
		setPadding(new Insets(50, 0, 20, 0));
	}

	private void initializeComponents() {

		getChildren().add(filter());
		getChildren().add(new Separator());
		getChildren().add(ScrollPaneForCachedBox());
		getChildren().add(addTrainerLink());

	}

	private ScrollPane ScrollPaneForCachedBox() {

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(cachedVBox());
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

		return scrollPane;
	}

	private VBox cachedVBox() {
		VBox vbox = new VBox();
		vbox.setPrefWidth(1200 * 0.34);

		// TODO: Wahre Trainerlist aus dem Backendholen
		/* NUR MOCKUP */
		Controller.fillTestData();
		for (Trainer tr : Controller.trainerlist) {
			vbox.getChildren().add(new TrainerListButton(tr));

		}

		return vbox;

	}

	private GridPane filter() {

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(0, 0, 17.50, 46));
		grid.setVgap(7);
		grid.setHgap(7);

		Label filterLabel = new Label("Sortieren nach: ");
		Label searchLabel = new Label("Suche: ");
		ComboBox filter = new ComboBox();
		filter.setItems(comboBoxContent());
		filter.setStyle("-fx-pref-width:200");
		TextField search = new TextField();

		grid.add(filterLabel, 0, 0);
		grid.add(filter, 1, 0);
		grid.add(searchLabel, 0, 1);
		grid.add(search, 1, 1);

		return grid;
	}
	
	private ObservableList<String> comboBoxContent() {
		
		ObservableList<String> list = FXCollections.observableArrayList();
		list.add("ID");
		list.add("Name");
		list.add("Alter");
		list.add("Erfahrung");
		
		return list;
	}
	
	private HBox addTrainerLink() {
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(20, 0, 0 ,0 ));
		
		Text text = new Text("+ Trainer hinzufügen");
		text.setStyle("-fx-fill: #000080;");
				
		hbox.getChildren().add(text);
		return hbox;
	}
	
}
