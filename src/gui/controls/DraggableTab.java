package gui.controls;

import java.util.HashSet;
import java.util.Set;

import insidefx.undecorator.Undecorator;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import jfxtras.scene.control.gauge.linear.SimpleMetroArcGauge;

/**
 * Ein ziehbarer Tab, der aus der TabView rausgezogen werden kann, innerhalb der TabView verschoben werden kann
 * oder in ein neues Fenster und zurück geschoben werden kann. Wichtig ist es sicherzugehen, dass
 * DraggableTabs nicht zusammen mit normalen Tabs benutzt werden sollten um Konflikte zu vermeiden!!
 * @author pmoritzer
 */
public class DraggableTab extends Tab {

    private static final Set<TabPane> tabPanes = new HashSet<>();
    private Label nameLabel;
    private Text dragText;
    private static final Stage markerStage;
    private Stage dragStage;
    private boolean detachable;

    static {
        markerStage = new Stage();
        markerStage.initStyle(StageStyle.UNDECORATED);
        Rectangle dummy = new Rectangle(3, 10, Color.web("#555555"));
        StackPane markerStack = new StackPane();
        markerStack.getChildren().add(dummy);
        markerStage.setScene(new Scene(markerStack));
    }

    /**
     * Erzeugung des Tabs 
     * 
     * @param text Der Text, der auf dem Tab stehen soll
     */
    public DraggableTab(String text) {
        nameLabel = new Label(text);
        setGraphic(nameLabel);
        detachable = true;
        dragStage = new Stage();
        dragStage.initStyle(StageStyle.TRANSPARENT);
        StackPane dragStagePane = new StackPane();
        dragStagePane.setStyle("-fx-background-color:transparent;");
        dragText = new Text(text);
        StackPane.setAlignment(dragText, Pos.CENTER);
        dragStagePane.getChildren().add(dragText);
        Scene scene = new Scene(dragStagePane);
        scene.getStylesheets().add("skin/undecorator.css");
		scene.getStylesheets().add("resources/css/style.css");
		scene.getStylesheets().add(SimpleMetroArcGauge.segmentColorschemeCSSPath());
        dragStage.setScene(scene);
        
        nameLabel.setOnMouseDragged(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                dragStage.setWidth(nameLabel.getWidth() + 10);
                dragStage.setHeight(nameLabel.getHeight() + 10);
                dragStage.setX(t.getScreenX());
                dragStage.setY(t.getScreenY());
                dragStage.show();
                Point2D screenPoint = new Point2D(t.getScreenX(), t.getScreenY());
                tabPanes.add(getTabPane());
                InsertData data = getInsertData(screenPoint);
                if(data == null || data.getInsertPane().getTabs().isEmpty()) {
                    markerStage.hide();
                }
                else {
                    int index = data.getIndex();
                    boolean end = false;
                    if(index == data.getInsertPane().getTabs().size()) {
                        end = true;
                        index--;
                    }
                    Rectangle2D rect = getAbsoluteRect(data.getInsertPane().getTabs().get(index));
                    if(end) {
                        markerStage.setX(rect.getMaxX() + 13);
                    }
                    else {
                        markerStage.setX(rect.getMinX());
                    }
                    markerStage.setY(rect.getMaxY() + 10);
                    markerStage.show();
                }
            }
        });
        nameLabel.setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                markerStage.hide();
                dragStage.hide();
                if(!t.isStillSincePress()) {
                    Point2D screenPoint = new Point2D(t.getScreenX(), t.getScreenY());
                    TabPane oldTabPane = getTabPane();
                    int oldIndex = oldTabPane.getTabs().indexOf(DraggableTab.this);
                    tabPanes.add(oldTabPane);
                    InsertData insertData = getInsertData(screenPoint);
                    if(insertData != null) {
                        int addIndex = insertData.getIndex();
                        if(oldTabPane == insertData.getInsertPane() && oldTabPane.getTabs().size() == 1) {
                            return;
                        }
                        oldTabPane.getTabs().remove(DraggableTab.this);
                        if(oldIndex < addIndex && oldTabPane == insertData.getInsertPane()) {
                            addIndex--;
                        }
                        if(addIndex > insertData.getInsertPane().getTabs().size()) {
                            addIndex = insertData.getInsertPane().getTabs().size();
                        }
                        insertData.getInsertPane().getTabs().add(addIndex, DraggableTab.this);
                        insertData.getInsertPane().selectionModelProperty().get().select(addIndex);
                        return;
                    }
                    if(!detachable) {
                        return;
                    }
                    final Stage newStage = new Stage();
                    final TabPane pane = new TabPane();
                    pane.setOnDragEntered(new EventHandler<DragEvent>() {

						@Override
						public void handle(DragEvent event) {
							// TODO Auto-generated method stub
							
						}});
                    pane.setMinWidth(600);
            		pane.setMinHeight(800);
            		pane.setPadding(new Insets(50, 0,0,0));
            		pane.setOnDragEntered(new EventHandler<DragEvent>() {

						@Override
						public void handle(DragEvent event) {
							
						}});
            		Undecorator undecorator = new Undecorator(newStage, pane);
            		undecorator.setOnDragEntered(new EventHandler<DragEvent>() {

						@Override
						public void handle(DragEvent event) {
							// TODO Auto-generated method stub
							
						}
            			
            		});

                    pane.setStyle("-fx-background-color: transparent");
                    tabPanes.add(pane);
                    newStage.setOnHiding(new EventHandler<WindowEvent>() {

                        @Override
                        public void handle(WindowEvent t) {
                            tabPanes.remove(pane);
                        }
                    });
                    getTabPane().getTabs().remove(DraggableTab.this);
                    pane.getTabs().add(DraggableTab.this);
                    pane.getTabs().addListener(new ListChangeListener<Tab>() {

                        @Override
                        public void onChanged(ListChangeListener.Change<? extends Tab> change) {
                            if(pane.getTabs().isEmpty()) {
                                newStage.hide();
                            }
                        }
                    });
                    Scene scene = new Scene(undecorator);
                    scene.getStylesheets().add("skin/undecorator.css");
            		scene.getStylesheets().add("resources/css/style.css");
            		scene.getStylesheets().add(SimpleMetroArcGauge.segmentColorschemeCSSPath());
            		
            		
                    newStage.setScene(scene);
                    newStage.initStyle(StageStyle.TRANSPARENT);
                    newStage.setX(t.getScreenX());
                    newStage.setY(t.getScreenY());
                    newStage.show();
                    pane.requestLayout();
                    pane.requestFocus();
                }
            }

        });
    }

    /**
     * Stellt sicher, dass der Tab gelöst werden kann
     * 
     * @param detachable true = lösbar, false = fest
     */
    public void setDetachable(boolean detachable) {
        this.detachable = detachable;
    }

    /**
     * Ersetzt die setText methode, da setText nicht mit dem Drag klarkommt
     * 
     * @param text setzt den Text des Tabs
     */
    public void setLabelText(String text) {
        nameLabel.setText(text);
        dragText.setText(text);
    }

    private InsertData getInsertData(Point2D screenPoint) {
        for(TabPane tabPane : tabPanes) {
            Rectangle2D tabAbsolute = getAbsoluteRect(tabPane);
            if(tabAbsolute.contains(screenPoint)) {
                int tabInsertIndex = 0;
                if(!tabPane.getTabs().isEmpty()) {
                    Rectangle2D firstTabRect = getAbsoluteRect(tabPane.getTabs().get(0));
                    if(firstTabRect.getMaxY()+60 < screenPoint.getY() || firstTabRect.getMinY() > screenPoint.getY()) {
                        return null;
                    }
                    Rectangle2D lastTabRect = getAbsoluteRect(tabPane.getTabs().get(tabPane.getTabs().size() - 1));
                    if(screenPoint.getX() < (firstTabRect.getMinX() + firstTabRect.getWidth() / 2)) {
                        tabInsertIndex = 0;
                    }
                    else if(screenPoint.getX() > (lastTabRect.getMaxX() - lastTabRect.getWidth() / 2)) {
                        tabInsertIndex = tabPane.getTabs().size();
                    }
                    else {
                        for(int i = 0; i < tabPane.getTabs().size() - 1; i++) {
                            Tab leftTab = tabPane.getTabs().get(i);
                            Tab rightTab = tabPane.getTabs().get(i + 1);
                            if(leftTab instanceof DraggableTab && rightTab instanceof DraggableTab) {
                                Rectangle2D leftTabRect = getAbsoluteRect(leftTab);
                                Rectangle2D rightTabRect = getAbsoluteRect(rightTab);
                                if(betweenX(leftTabRect, rightTabRect, screenPoint.getX())) {
                                    tabInsertIndex = i + 1;
                                    break;
                                }
                            }
                        }
                    }
                }
                return new InsertData(tabInsertIndex, tabPane);
            }
        }
        return null;
    }

    private Rectangle2D getAbsoluteRect(Control node) {
        return new Rectangle2D(node.localToScene(node.getLayoutBounds().getMinX(), node.getLayoutBounds().getMinY()).getX() + node.getScene().getWindow().getX(),
                node.localToScene(node.getLayoutBounds().getMinX(), node.getLayoutBounds().getMinY()).getY() + node.getScene().getWindow().getY(),
                node.getWidth(),
                node.getHeight());
    }

    private Rectangle2D getAbsoluteRect(Tab tab) {
        Control node = ((DraggableTab) tab).getLabel();
        return getAbsoluteRect(node);
    }

    private Label getLabel() {
        return nameLabel;
    }

    private boolean betweenX(Rectangle2D r1, Rectangle2D r2, double xPoint) {
        double lowerBound = r1.getMinX() + r1.getWidth() / 2;
        double upperBound = r2.getMaxX() - r2.getWidth() / 2;
        return xPoint >= lowerBound && xPoint <= upperBound;
    }

    private static class InsertData {

        private final int index;
        private final TabPane insertPane;

        public InsertData(int index, TabPane insertPane) {
            this.index = index;
            this.insertPane = insertPane;
        }

        public int getIndex() {
            return index;
        }

        public TabPane getInsertPane() {
            return insertPane;
        }

    }
}