/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_tutor;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author alejandro
 */
public class VentanaModal {
    static Stage ventanaStage = new Stage();
    static String titulo;
    static boolean si;

    static{
        ventanaStage.initModality(Modality.APPLICATION_MODAL);
    }

    // Mostrar un texto
    public static void ok(String titulo, String mensaje) {
        ventanaStage.setTitle(titulo);
        Label mensajeL = new Label(mensaje);

        ventanaStage.setResizable(false);

        Button btnOk = new Button("Ok");
        btnOk.setPrefSize(70, 15);
        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ventanaStage.close();
            }
        });

        VBox modalBox = new VBox();
        modalBox.setPadding(new Insets(40, 80, 40, 80) );
        modalBox.setSpacing(40);
        modalBox.getChildren().addAll(mensajeL, btnOk);
        modalBox.setAlignment(Pos.CENTER);

        Scene escena = new Scene(modalBox);
        ventanaStage.setScene(escena);
        ventanaStage.showAndWait();
    }

    public static boolean si_no(String titulo, String mensaje){
        ventanaStage.setTitle(titulo);
        Label mensajeL = new Label(mensaje);
        Button btnSi = new Button("SI");
        btnSi.setPrefSize(70, 15);
        btnSi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ventanaStage.close();
                si = true;
            }
        });
        Button btnNo = new Button("No");
        btnNo.setPrefSize(70, 15);
        btnNo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ventanaStage.close();
                si = false;
            }
        });

        HBox botonesBox = new HBox();
        botonesBox.setAlignment(Pos.CENTER);
        botonesBox.setSpacing(10);
        botonesBox.getChildren().addAll(btnSi, btnNo);

        VBox modalBox = new VBox();
        modalBox.setPadding(new Insets(40, 80, 40, 80) );
        modalBox.setSpacing(40);
        modalBox.getChildren().addAll(mensajeL, botonesBox);
        modalBox.setAlignment(Pos.CENTER);

        Scene escena = new Scene(modalBox);
        ventanaStage.setScene(escena);
        ventanaStage.showAndWait();
        return si;
    }

}