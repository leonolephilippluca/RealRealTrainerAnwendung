package gui.controls;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jfxtras.scene.control.gauge.linear.SimpleMetroArcGauge;
import jfxtras.scene.control.gauge.linear.elements.PercentSegment;
import mockup.Trainer;

public class TrainerViewGrid extends VBox {

	public TrainerViewGrid(Trainer t) {
		setBoundaries();
		initializeComponents(t);

	}

	private void setBoundaries() {

		setAlignment(Pos.CENTER);
		setPrefWidth(600);
		setPadding(new Insets(25, 65, 0, 65));
		setSpacing(95);

	}

	private void initializeComponents(Trainer t) {

		getChildren().add(listBox(fatLabel("Name"), new Separator(), dataText(t.getName())));
		getChildren().add(listBox(fatLabel("ID"), new Separator(), dataText(t.getId() + "")));
		getChildren().add(listBox(fatLabel("Alter"), new Separator(), dataText(t.getAge() + "")));
		getChildren().add(listBox(fatLabel("Erfahrung"), new Separator(), radar(t.getExperience())));

	}

	private Label fatLabel(String text) {
		Label label = new Label(text);
		label.setStyle("-fx-font-weight: bold; -fx-font-size: 30");
		return label;
	}

	private Text dataText(String text) {

		Text tx = new Text(text);
		tx.setStyle("-fx-font-size: 15");
		return tx;
	}

	private SimpleMetroArcGauge radar(int experience) {

		SimpleMetroArcGauge x = new SimpleMetroArcGauge();

		// x.setPadding(new Insets(200, 0, 0, 0));

		x.getStyleClass().add("colorscheme-red-to-green-10");
		x.setOpacity(0.45);

		x.setMinValue(0);
		x.setMaxValue(10);

//		PercentSegment percentSegment = new PercentSegment(x, 0, 0.01);
		PercentSegment percentSegment2 = new PercentSegment(x, 0.02, 9.99);
		PercentSegment percentSegment3 = new PercentSegment(x, 10, 19.99);
		PercentSegment percentSegment4 = new PercentSegment(x, 20, 29.99);
		PercentSegment percentSegment5 = new PercentSegment(x, 30, 39.99);
		PercentSegment percentSegment6 = new PercentSegment(x, 40, 49.99);
		PercentSegment percentSegment7 = new PercentSegment(x, 50, 59.99);
		PercentSegment percentSegment8 = new PercentSegment(x, 60, 69.99);
		PercentSegment percentSegment9 = new PercentSegment(x, 70, 79.99);
		PercentSegment percentSegment10 = new PercentSegment(x, 80, 89.99);
		PercentSegment percentSegment11 = new PercentSegment(x, 90, 100);

//		x.segments().add(percentSegment);
		x.segments().add(percentSegment2);
		x.segments().add(percentSegment3);
		x.segments().add(percentSegment4);
		x.segments().add(percentSegment5);
		x.segments().add(percentSegment6);
		x.segments().add(percentSegment7);
		x.segments().add(percentSegment8);
		x.segments().add(percentSegment9);
		x.segments().add(percentSegment10);
		x.segments().add(percentSegment11);

		x.setValue(experience);

		return x;
	}

	private VBox listBox(Label label, Separator separator, Node node) {

		VBox vbox = new VBox();
		vbox.setPrefWidth(600);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(2);
		vbox.getChildren().add(label);

		if (node instanceof SimpleMetroArcGauge) {
			VBox nested = new VBox();
			nested.setSpacing(15);
			nested.setPadding(new Insets(0, 0, 15, 0));
			nested.getChildren().add(separator);
			nested.getChildren().add(node);

			vbox.getChildren().add(nested);

		} else {

			vbox.getChildren().add(separator);
			vbox.getChildren().add(node);
		}

		return vbox;

	}

}
