package gui.controls;

import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import mockup.Trainer;

public class TrainerTab extends TabPane {
	
	private static TrainerTab instance;

	private TrainerTab() {
		
		setBoundaries();
		initializeComponents();
		
	}

	/**
	 * Methode um Größe und Abstand der jeweiligen Komponenten festzulegen
	 */
	private void setBoundaries() {
		
		setPadding(new Insets(37.5, 0, 0, 0));
//		setStyle("-fx-background-color: transparent; -fx-border-style: solid; -fx-border-width: 2px; -fx-border-color: transparent transparent transparent gray;");
	}

	/**
	 * Methode zum initialisieren der Komponenten
	 */
	private void initializeComponents() {
		
	}
	
	public void addNewTab(Trainer t) {
		
		DraggableTab tab = initTab(t);
		getTabs().add(tab);
		selectTab(tab);
	
	}
	
	private void selectTab(Tab tab) {
		
		getSelectionModel().select(tab);
		
	}
	
	private DraggableTab initTab(Trainer t) {
		
		DraggableTab tab = new DraggableTab(t.getName());
		
		TrainerViewGrid content = new TrainerViewGrid(t);
		tab.setContent(content);
		
		return tab;
		
		
	}
	
	public static TrainerTab getInstance() {
		
		if(TrainerTab.instance == null) {
			TrainerTab.instance = new TrainerTab();
		}
		return TrainerTab.instance;
	}
	
}