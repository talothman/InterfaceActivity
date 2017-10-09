import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.*;
import java.util.Scanner;

public class InterfaceDriver extends Application {
	//Modify this based on the number of groups you've assigned to the class
	private static int NUM_OF_GROUPS;

  public static void main(String[] args) {
  		launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
  	System.out.println("Enter the number of groups you've assigned to this section:");
		Scanner s = new Scanner(System.in);
		NUM_OF_GROUPS = s.nextInt();

		primaryStage.setTitle("Drawing Interfaces Driver");
		Group root = new Group();

		Button b = new Button();
		b.setText("Generate!");
		b.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				for(int i = 1; i <= NUM_OF_GROUPS; i++){
					Stage stage = new Stage();
					Group root1 = new Group();
					Canvas canvas = new Canvas(500, 500);
					GraphicsContext gc = canvas.getGraphicsContext2D();
					try{
						Drawer d = (Drawer) Class.forName("DrawerClass" + i).newInstance();
						d.draw(gc);
					}catch(Exception e1){
						System.out.println("Cannot find DrawerClass class.");
						System.exit(0);
					}
					root1.getChildren().add(canvas);
					stage.setScene(new Scene(root1));
					stage.show();
				}
			}
		});

		root.getChildren().add(b);
  	primaryStage.setScene(new Scene(root));
  	primaryStage.show();
  }
}
