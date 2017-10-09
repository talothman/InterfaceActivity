# A JavaFX Interface Activity #
The objective of this bonus activity is to help you to understand Java Interfaces and JavaFX concepts.

## Introduction ##
`Interfaces` are used to enforce some sense of expected behavior. Classes that implement a specific `Interface` need to implement the `Interface`'s methods. For example, if we had a `Drawer` interface with a method `draw`, we can expect any class that implements this method to implement that `draw` method. This gives us an idea about what behavior classes that implement the `Drawer` interface are capable of doing (capable of drawing something).

## Tasks ##
In this exercise, you will develop two works of art using JavaFX's [`GraphicContext`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/canvas/GraphicsContext.html) class. Have a look at the documentation. You will need to write a 2 classes that implement the `Drawer` interface, run your classes using the driver below and answer the questions.

```java
    import javafx.scene.canvas.GraphicsContext;

    public interface Drawer{
    	public void draw(GraphicsContext gc);
    }
```
Notice that the interface contains one abstract method that awaits your implementation. Write two separate classes following this notation: `DrawerClass[num]` (example: DrawerClass1, DrawerClass2) that implement the `draw` method of the `Drawer` interface. One example of how `GraphicsContext` can be used is the following:

```java
    public void draw(GraphicsContext gc) {
    	gc.setFill(Color.GREEN);
    	gc.setStroke(Color.BLUE);
    	gc.setLineWidth(5);
    	gc.strokeLine(40, 10, 10, 40);
    	gc.fillOval(10, 60, 30, 30);
    	gc.strokeOval(60, 60, 30, 30);
    }
```
This produces the following work of art:

![Beauty](http://i.imgur.com/QFZ2fjc.jpg)

Notice that colors are set using the `setFill()` and `setStroke()` methods. The `setLineWidth` determines how many pixels the outline of your shape is. Also, methods that start with stroke, like `strokeOval()` and `strokeLine()` are used to draw the specified shape. Refer to the documentation for `GraphicsContext` to see what other shapes you could use.

## Instructions to Run your Code ##

There are two things that you need to do. You must compile all the classes submitted to you by the students using `javac *.java`. Additionally, you must enter the `NUM_OF_GROUPS` constant which you will be prompted for when you run `java InterfaceDriver`. Here is the `InterfaceDriver` code:
```java
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
    						e1.printStackTrace();
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
