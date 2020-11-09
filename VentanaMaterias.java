/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_tutor;

import java.util.Collections;
import java.util.regex.Matcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author alejandro
 */
public class VentanaMaterias {
    private static Stage stageMaterias;
    private static TableView<Materia> tablaMaterias;
    private static Stage ventanaAlumnos;
    private static Alumno alumno;
    private static Button btnCrear, btnEliminar, btnRegresar;
    private static TextField txtCodigo, txtNombre, txtProfesor;
    private static ChoiceBox<String> choiceNota, choiceSemestre, choiceCreditos;


    public static void show(Stage ventanaAlumnos, Alumno alumno){

        ManejadorMaterias clicks = new ManejadorMaterias();
        VentanaMaterias.ventanaAlumnos = ventanaAlumnos;
        VentanaMaterias.alumno = alumno;


        //CENTER  Tabla de datos

        tablaMaterias = new TableView<Materia>();
        tablaMaterias.setEditable(true);

        TableColumn<Materia, String> colCodigo = new TableColumn("Código");
        colCodigo.setMinWidth(50);
        colCodigo.setSortable(false);
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        colCodigo.setStyle( "-fx-alignment: center;");

        TableColumn<Materia, String> colNombre = new TableColumn("Nombre");
        colNombre.setMinWidth(130);
        colNombre.setSortable(false);
        colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        colNombre.setStyle( "-fx-alignment: center;");

        TableColumn<Materia, Integer> colCreditos = new TableColumn("Creditos");
        colCreditos.setMinWidth(58);
        colCreditos.setMaxWidth(58);
        colCreditos.setSortable(false);
        colCreditos.setCellValueFactory(new PropertyValueFactory<>("Creditos"));
        colCreditos.setStyle( "-fx-alignment: center;");

        TableColumn<Materia, String> colProfesor = new TableColumn("Profesor");
        colProfesor.setMinWidth(130);
        colProfesor.setSortable(false);
        colProfesor.setCellValueFactory(new PropertyValueFactory<>("Profesor"));
        colProfesor.setStyle( "-fx-alignment: center;");

        TableColumn<Materia, Integer> colSemestre = new TableColumn("Semestre");
        colSemestre.setMinWidth(60);
        colSemestre.setMaxWidth(60);
        colSemestre.setSortable(false);
        colSemestre.setCellValueFactory(new PropertyValueFactory<>("Semestre"));
        colSemestre.setStyle( "-fx-alignment: center;");

        TableColumn<Materia, Double> colNota = new TableColumn("Nota");
        colNota.setMinWidth(40);
        colNota.setSortable(false);
        colNota.setCellValueFactory(new PropertyValueFactory<>("NotaLetra"));
        colNota.setStyle( "-fx-alignment: center;");

        tablaMaterias.getColumns().addAll(colCodigo, colNombre, colCreditos,
                colProfesor, colSemestre, colNota);




        Collections.sort(alumno.getMaterias());
        ObservableList<Materia> materias = FXCollections.observableArrayList();
        for(Materia materia: alumno.getMaterias()){
            materias.add(materia);
        }
        tablaMaterias.setItems(materias);
        tablaMaterias.setMaxHeight(250);

        btnEliminar = new Button();
        btnEliminar.setText("Eliminar");
        btnEliminar.setOnAction(clicks);

        VBox centro = new VBox();
        centro.getChildren().addAll(new Label("Lista de materias del estudiante " + alumno.getNombre()), new Label(), tablaMaterias, new Label(), btnEliminar);
        centro.setPadding(new Insets(20));


        ////////////////


        //// BOTTOM

        ///Formulario



        txtCodigo = new TextField();
        txtCodigo.setPrefColumnCount(8);
        txtCodigo.setPromptText("Codigo");

        txtNombre = new TextField();
        txtNombre.setPrefColumnCount(10);
        txtNombre.setPromptText("Nombre");

        choiceCreditos = new ChoiceBox();
        choiceCreditos.getItems().addAll("Creditos", "0", "1", "2", "3", "4", "5");
        choiceCreditos.setValue("Creditos");

        txtProfesor = new TextField();
        txtProfesor.setPrefColumnCount(10);
        txtProfesor.setPromptText("Profesor");

        choiceSemestre = new ChoiceBox();
        choiceSemestre.getItems().addAll("Semestre", "1", "2", "3", "4", "5", "6", "7", "8");
        choiceSemestre.setValue("Semestre");

        choiceNota = new ChoiceBox();
        choiceNota.getItems().addAll("A", "B", "C", "D", "F", "Nan", "Nota");
        choiceNota.setValue("Nota");

        FlowPane form = new FlowPane();
        form.setHgap(10);
        form.setVgap(8);
        form.getChildren().addAll(txtCodigo, txtNombre, choiceCreditos, txtProfesor, choiceSemestre, choiceNota);


        VBox formFinal = new VBox();
        formFinal.setSpacing(15);
        formFinal.getChildren().addAll(new Label ("Datos para crear o editar materia"), form);


        /// Botones

        btnCrear = new Button();
        btnCrear.setText("Crear/Editar");
        btnCrear.setOnAction(clicks);


        btnRegresar = new Button();
        btnRegresar.setText("Regresar");
        btnRegresar.setOnAction(clicks);

        HBox subMenu = new HBox();
        subMenu.setSpacing(40);
        subMenu.setAlignment(Pos.CENTER);
        subMenu.getChildren().addAll(btnRegresar, btnCrear);



        VBox bottom = new VBox();
        bottom.setSpacing(50);
        bottom.getChildren().addAll(formFinal,subMenu);
        bottom.setPadding(new Insets(5, 20, 20, 20));



        btnCrear.setPrefSize(100, 15);
        btnEliminar.setPrefSize(100, 15);
        btnRegresar.setPrefSize(100, 15);


        //////// ARMAR ESCENA

        BorderPane borderMaterias = new BorderPane();
        borderMaterias.setPadding(new Insets(10));
        borderMaterias.setCenter(centro);
        borderMaterias.setBottom(bottom);
        BorderPane.setMargin(tablaMaterias, new Insets(5));

        Scene scene = new Scene(borderMaterias, 560, 500);
        stageMaterias = new Stage();
        stageMaterias.initModality(Modality.APPLICATION_MODAL);
        stageMaterias.setResizable(false);
        stageMaterias.setTitle("Materias de " + alumno.getNombre());
        stageMaterias.setScene(scene);
        stageMaterias.show();
    }


    private static void setDefault(){
        txtCodigo.setText("");
        txtNombre.setText("");
        txtProfesor.setText("");
        choiceCreditos.setValue("Creditos");
        choiceSemestre.setValue("Semestre");
        choiceNota.setValue("Nota");
    }

    private static class ManejadorMaterias implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            if(event.getSource() == btnEliminar){
                Materia materia = tablaMaterias.getSelectionModel().getSelectedItem();
                if(materia!=null){
                    int index = tablaMaterias.getSelectionModel().getSelectedIndex();
                    boolean borrar = VentanaModal.si_no("Eliminar Materia", "Desea eliminar la materia\n\n"+materia);
                    if (borrar){
                        if(alumno.eliminarMateria(materia)){
                            tablaMaterias.getItems().remove(index);
                        }else{
                            VentanaModal.ok("Error", "No se puede eliminar una materia\ncon nota asociada");
                        }
                    }
                }else{
                    VentanaModal.ok("Error", "Ninguna materia seleccionada");
                }
                alumno.actualizarCreditos();
            }

            if(event.getSource() == btnCrear){
                String errores = "";

                String codigo = txtCodigo.getText();

                Matcher codigoM = Materia.codigoPattern.matcher(codigo);
                String nombre = txtNombre.getText();
                String profesor = txtProfesor.getText();
                String creditoS = choiceCreditos.getValue();
                String semestreS = choiceSemestre.getValue();
                String notaS = choiceNota.getValue();


                if(!codigoM.matches())
                    errores += "* Código incorrecto\n\n";
                if(creditoS.equals("Creditos"))
                    errores += "* Créditos sin seleccionar\n\n";
                if(nombre.equals(""))
                    errores += "* Nombre Vacio\n\n";
                if(profesor.equals(""))
                    errores += "* Profesor sin definir\n\n";
                if(semestreS.equals("Semestre"))
                    errores += "* Semestre sin seleccionar\n\n";
                if(notaS.equals("Nota"))
                    errores += "* Nota sin seleccionar\n\n";

                if(errores.equals("")){
                    int creditos = Integer.parseInt(creditoS);
                    int semestre = Integer.parseInt(semestreS);
                    Materia materia = new Materia(codigo, nombre, creditos, profesor, semestre, 0);
                    String nota = choiceNota.getValue();
                    materia.setNota(nota);

                    if(alumno.agregarMateria(materia)){
                        tablaMaterias.getItems().add(materia);
                        System.out.println(alumno.getMaterias());
                    }else{
                        for(int i=0; i<alumno.getMaterias().size(); i++){
                            if(alumno.getMaterias().get(i).getCodigo().equals(codigo)){
                                tablaMaterias.getItems().set(i, materia);
                                alumno.getMaterias().set(i, materia);
                                System.out.println(alumno.getMaterias());
                                break;
                            }
                        }
                    }
                    setDefault();
                }else{
                    VentanaModal.ok("Error", "Errores en los campos\n\n" + errores);
                }
                alumno.actualizarCreditos();
            }

            if(event.getSource() == btnRegresar){
                stageMaterias.close();
                ventanaAlumnos.close();
                VentanaAlumnos.show(Proyecto2_Tutor.alumnos);
            }

        }



    }

}
