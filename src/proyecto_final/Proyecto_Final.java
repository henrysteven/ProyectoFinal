/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_final;

import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Henry
 */
public class Proyecto_Final extends Application{
    Stage stage1 = new Stage();
    @Override
    public void start(Stage stage) throws Exception {
        Metodos m = new Metodos();
        Scene scene = new Scene(m.getRoot(), 1000, 600);
        //stage1.setResizable(false);
        stage1.setScene(scene);
        stage1.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
       
        //System.out.println(Metodos.directorio("C:\Users\Henry\Documents\Bandicam"));
        
        launch(args);
    }

   
}
