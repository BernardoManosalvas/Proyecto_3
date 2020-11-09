/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_tutor;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class Proyecto2_Tutor extends Application {

    public static ArrayList<Alumno> alumnos = new ArrayList<>();
    Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Crear materias independientes de la nota de cada alumno, luego asignar la nota del alumno

        // Alumno 1
        Materia cosmos = new Materia("ARL-1159", "COSMOS", 3, "PABLO DÁVILA", 5, Materia.notaB);
        Materia algebra = new Materia("MAT-1862", "ALGEBRA LINEAL", 8, "DIEGO OCHOA", 5, Materia.notaA);
        Materia calc3 = new Materia("MAT-2356", "CALCULO 3", 5, "JULIO IBARRA", 5, Materia.notaA);
        Materia progra = new Materia("CMP-9560", "PROGRAMACION 3", 4, "PABLO YEPEZ", 5, Materia.notaNan);
        Materia yoga = new Materia("YOG-9560", "YOGA", 0, "PROFE YOGA", 5, Materia.notaA);
        Alumno alumno1 = new Alumno("00209216", "Alejandro Duque", "COMPUTACION", "aduquead13@gmail.com", "0992659674") ;

        // Alumno 2
        Materia fisica = new Materia("FIS-3657", "FISICA", 3, "LOTFI BOUBEKEUR", 5, Materia.notaB);
        Materia progra3 = new Materia("CMP-1444", "PROGRAMACION 3", 3, "PABLO YEPEZ", 5, Materia.notaNan);
        Materia writing = new Materia("ENG-2312", "WRITING & RHETORIC", 1, "RHYS DAVIES", 7, Materia.notaA);
        Alumno alumno2 = new Alumno("00200001", "Bernardo Manosalvas", "COMPUTACION", "bmanosalvast@usfq.ec", "0992659673");

        asignarAlumno(alumno1,cosmos,algebra,calc3,progra,yoga);
        asignarAlumno(alumno2,fisica,progra3,writing);
        //alumnos.add(alumno2);

        Button btnEstudiantes = new Button();
        btnEstudiantes.setText("Mis Estudiantes");
        btnEstudiantes.setOnAction(new EventHandler<>() {

            @Override
            public void handle(ActionEvent event) {
                VentanaAlumnos.show(alumnos);
                primaryStage.close();
            }
        });

        VBox root = new VBox();
        root.getChildren().addAll(btnEstudiantes, new Label("Creado por: \nAlejandro Duque \nBernardo Manosalvas"));
        root.setSpacing(40);
        root.setPadding(new Insets(50,80,50,80));

        Scene scene = new Scene(root);

        primaryStage.setTitle("Bienvenido Tutor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }

    public static void eliminarAlumno(Alumno alumnoSelecionado){
        alumnoSelecionado.eliminarMaterias();
        alumnos.remove(alumnoSelecionado);
    }

    public static boolean agregarAlumno(Alumno alumno){

        for(Alumno alumnoC: alumnos){
            if(alumnoC.getCodigo().equals(alumno.getCodigo())){
                return false;
            }
        }

        alumnos.add(alumno);
        return true;
    }

    private void asignarAlumno(Alumno alumno, Materia... materias){
        alumno.agregarMateria(materias);
        alumnos.add(alumno);
    }

}
