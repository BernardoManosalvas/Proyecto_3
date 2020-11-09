/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_tutor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class VentanaAlumnos {

    public static Button btnMostrar, btnEliminar, btnCrear, btnSalir;
    public static TextField textCodigo, textNombre, textEmail, textCelular;
    public static ChoiceBox<String> choiceCarrera;
    public static TableView<Alumno> tablaAlumnos;
    public static Stage stageAlumnos;
    private static Alumno a;

    public static void show(ArrayList<Alumno> alumnos){
        Stage ventanaAlumnos = new Stage();
        ManejadorAlumno click = new ManejadorAlumno();

        ventanaAlumnos.initModality(Modality.APPLICATION_MODAL);

        //CENTRO Tabla de alumnos

        tablaAlumnos = new TableView<>();
        tablaAlumnos.setEditable(true);

        TableColumn<Alumno, String> colCodigo = new TableColumn<>("Codigo");
        colCodigo.setMinWidth(50);
        colCodigo.setSortable(false);
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colCodigo.setStyle( "-fx-alignment: center;");

        TableColumn<Alumno, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setMinWidth(130);
        colNombre.setSortable(false);
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNombre.setStyle( "-fx-alignment: center;");

        TableColumn<Alumno, String> colCarrera = new TableColumn<>("Carrera");
        colCarrera.setMinWidth(50);
        colCarrera.setSortable(false);
        colCarrera.setCellValueFactory(new PropertyValueFactory<>("carrera"));
        colCarrera.setStyle( "-fx-alignment: center;");

        TableColumn<Alumno, String> colEmail = new TableColumn<>("Email");
        colEmail.setMinWidth(160);
        colEmail.setSortable(false);
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmail.setStyle( "-fx-alignment: center;");

        TableColumn<Alumno, String> colCelular = new TableColumn<>("Celular");
        colCelular.setMinWidth(55);
        colCelular.setSortable(false);
        colCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
        colCelular.setStyle( "-fx-alignment: center;");

        TableColumn<Alumno, Double> colGpa = new TableColumn<>("GPA");
        colGpa.setMinWidth(20);
        colGpa.setMaxWidth(45);
        colGpa.setSortable(false);
        colGpa.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        colGpa.setStyle( "-fx-alignment: center;");

        TableColumn<Alumno, String> colCreditos = new TableColumn<>("Creditos");
        colCreditos.setMinWidth(20);
        colCreditos.setSortable(false);
        colCreditos.setCellValueFactory(new PropertyValueFactory<>("totalCreditos"));
        colCreditos.setStyle( "-fx-alignment: center;");


        tablaAlumnos.getColumns().addAll(colCodigo, colNombre, colCarrera, colEmail, colCelular, colGpa, colCreditos);

        ObservableList<Alumno> alumnosTabla = FXCollections.observableArrayList();
        alumnosTabla.addAll(alumnos);
        tablaAlumnos.setItems(alumnosTabla);
        tablaAlumnos.setMaxHeight(250);

        // Mostrar y Eliminar HBox
        HBox botonesAlumnos = new HBox();

        btnMostrar = new Button("Mostrar Materias");
        btnMostrar.setOnAction(click);

        btnEliminar = new Button("Eliminar");
        btnEliminar.setOnAction(click);

        botonesAlumnos.getChildren().addAll(btnMostrar, btnEliminar);
        botonesAlumnos.setSpacing(10);


        // Agregar layouts VBox centro

        VBox centro = new VBox();
        centro.getChildren().addAll(new Label("Lista de estudiantes"), new Label(), tablaAlumnos, new Label(), botonesAlumnos);
        centro.setPadding(new Insets(20));




        // FORM

        textCodigo = new TextField();
        textCodigo.setPrefColumnCount(5);
        textCodigo.setPromptText("Codigo");

        textNombre = new TextField();
        textNombre.setPrefColumnCount(9);
        textNombre.setPromptText("Nombre");

        choiceCarrera = new ChoiceBox<>(FXCollections.observableArrayList(Alumno.carerras));
        choiceCarrera.setPrefWidth(100);
        choiceCarrera.setValue("CARRERA");

        textEmail = new TextField();
        textEmail.setPrefColumnCount(17);
        textEmail.setPromptText("Email");

        textCelular = new TextField();
        textCelular.setPrefColumnCount(10);
        textCelular.setPromptText("Celular");

        FlowPane form = new FlowPane();
        form.setHgap(10);
        form.setVgap(8);
        form.getChildren().addAll(textCodigo, textNombre, choiceCarrera, textEmail, textCelular);

        VBox formF = new VBox();
        formF.setSpacing(15);
        formF.getChildren().addAll(new Label("Crear Estudiante"), form);




        // BUTTONS

        btnSalir = new Button("Salir");
        btnSalir.setOnAction(click);

        btnCrear = new Button("Crear/Editar");
        btnCrear.setOnAction(click);

        HBox buttons = new HBox();
        buttons.setSpacing(40);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(btnSalir, btnCrear);

        // BOTTOM

        VBox bottom = new VBox();
        bottom.setSpacing(50);
        bottom.getChildren().addAll(formF, buttons);
        bottom.setPadding(new Insets(5, 20, 20, 20));

        btnCrear.setPrefSize(100, 15);
        btnEliminar.setPrefSize(100, 15);
        btnMostrar.setPrefSize(120, 15);
        btnSalir.setPrefSize(100, 15);

        //Scene

        BorderPane borderPaneAlumnos = new BorderPane();
        borderPaneAlumnos.setPadding(new Insets(10));
        borderPaneAlumnos.setCenter(centro);
        borderPaneAlumnos.setBottom(bottom);
        BorderPane.setMargin(tablaAlumnos, new Insets(5));

        Scene scene = new Scene(borderPaneAlumnos, 725, 550);
        stageAlumnos = new Stage();
        stageAlumnos.initModality(Modality.APPLICATION_MODAL);
        stageAlumnos.setResizable(false);
        stageAlumnos.setTitle("Listado Alumnos");
        stageAlumnos.setScene(scene);
        stageAlumnos.show();

    }

     private static void setDefault (){
         textCodigo.setText("");
         textNombre.setText("");
         textEmail.setText("");
         textCelular.setText("");
         choiceCarrera.setValue("CARRERA");
     }

    private static class ManejadorAlumno implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            if(event.getSource()==btnMostrar){
                Alumno alumnoSelec = tablaAlumnos.getSelectionModel().getSelectedItem();
                if(alumnoSelec != null){
                    stageAlumnos.close();
                    VentanaMaterias.show(stageAlumnos, alumnoSelec);
                }else{
                    VentanaModal.ok("Error", "Ningun alumno seleccionado");
                }
            }

            if (event.getSource()==btnEliminar){
                Alumno alumnoSelec = tablaAlumnos.getSelectionModel().getSelectedItem();
                if (alumnoSelec != null){
                    int index = tablaAlumnos.getSelectionModel().getSelectedIndex();
                    boolean borrar = VentanaModal.si_no("Eliminar Alumno", "Desea eliminar el alumno \n\n" + alumnoSelec);
                    if(borrar){
                        Proyecto2_Tutor.eliminarAlumno(alumnoSelec);
                        tablaAlumnos.getItems().remove(index);
                    }
                }else{
                    VentanaModal.ok("Error", "Ningun alumno seleccionado");
                }

            }

            if (event.getSource()==btnCrear){
                String errores = "";

                String codigoStr = textCodigo.getText();
                Matcher codigoM = Alumno.codigoPattern.matcher(codigoStr);

                String nombreStr = textNombre.getText();
                String carreraStr = choiceCarrera.getValue();

                String emailStr = textEmail.getText();
                Matcher emailM = Alumno.emailPattern.matcher(emailStr);

                String celularStr = textCelular.getText();
                Matcher celularM = Alumno.celularPattern.matcher(celularStr);

                if(!codigoM.matches())
                    errores += "* Código no valido\n\n";
                if(nombreStr.equals(""))
                    errores += "* Nombre Vacio\n\n";
                if(carreraStr.equals("CARRERA"))
                    errores += "* Carrera sin selecionar\n\n";
                if(!emailM.matches())
                    errores += "* Email no valido\n\n";
                if(!celularM.matches())
                    errores += "* Celular invalido";

                if(errores.equals("")){
                    a = new Alumno(codigoStr, nombreStr, carreraStr, emailStr, celularStr);
                    if (Proyecto2_Tutor.agregarAlumno(a)){
                        tablaAlumnos.getItems().add(a);
                        setDefault();
                    }else{
                        for(int i=0; i<Proyecto2_Tutor.alumnos.size(); i++){
                            if(Proyecto2_Tutor.alumnos.get(i).getCodigo().equals(a.getCodigo())){
                                for(Materia materia: Proyecto2_Tutor.alumnos.get(i).getMaterias()){
                                    a.getMaterias().add(materia);
                                }
                                a.actualizarCreditos();
                                tablaAlumnos.getItems().set(i, a);
                                Proyecto2_Tutor.alumnos.set(i, a);
                                System.out.println(a);
                                break;
                            }
                        }
                    }
                }else {
                    VentanaModal.ok("Error", "Errores en los campos\n\n" + errores);
                }
            }

            if(event.getSource()==btnSalir){
                stageAlumnos.close();
            }
        }
    }
}
