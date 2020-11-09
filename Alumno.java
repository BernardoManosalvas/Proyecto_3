/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_tutor;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author alejandro
 */
public class Alumno implements Comparable<Alumno>{
    // Private
    private String codigo = "";
    private String nombre;
    private String carrera;
    private String email;
    private String celular;
    private double gpa;
    private int totalCreditos=0;
    private ArrayList<Materia> materias = new ArrayList<Materia>();
    public static String[] carerras = {"FISICA", "MATEMATICAS", "COMPUTACION", "MUSICA", "SOCIOLOGIA", "BIOLOGIA",
            "DERECHO", "MEDICINA", "CINE", "ECONOMIA", "FINANZAS", "AGRONOMIA", "CARRERA"};



    public static Pattern codigoPattern = Pattern.compile("(00){0,1}[0-9]{6}"); //00209216
    public static Pattern emailPattern = Pattern.compile("[A-z0-9_/+|\\-]+@[a-z0-9-]+(\\.[a-z]{2,4}){1,3}", Pattern.CASE_INSENSITIVE);
    public static Pattern celularPattern = Pattern.compile("09[8-9][0-9]{7}");

    public Alumno() {
    }

    public Alumno(String codigo, String nombre, String carrera, String email, String celular) {
        setCodigo(codigo);
        if(!codigo.equals("")){
            setNombre(nombre);
            setCarrera(carrera);
            setEmail(email);
            setCelular(celular);
        }
    }

    @Override
    public String toString(){
        String s = "";
        s+= "Codigo:\t" + this.getCodigo() + "\n";
        s+= "Nombre:\t" + this.getNombre() + "\n";
        s+= "Carrera:\t" + this.getEmail() + "\n";
        s+= "Celular:\t" + this.getCelular() + "\n";
        s+= "GPA:\t\t" + this.getGpa() + "\n";
        s+= "Creditos:\t" + this.getTotalCreditos() + "\n";
        return s;
    }


    public boolean agregarMateria(Materia materia){
        String codigoNuevo = materia.getCodigo();
        boolean codigoDisponible = true;
        for(Materia m: materias){
            if(m.getCodigo().equals(codigoNuevo)){
                codigoDisponible = false;
            }
        }
        int creditosTemp = this.totalCreditos + materia.getCreditos();

        if(codigoDisponible && creditosTemp<=15){
            materias.add(materia);
            this.actualizarCreditos();
            return true;
        }
        return false;
    }

    public void agregarMateria(Materia... materias){
        for(Materia materia: materias){
            this.agregarMateria(materia);
        }
    }

    public boolean eliminarMateria(Materia materia){
        if(materia.getNota()==-1){
            materias.remove(materia);
            return true;
        }
        return false;
    }

    public void actualizarCreditos(){
        int creditos=0;
        for(Materia materia: materias){
            creditos+=materia.getCreditos();
        }
        this.totalCreditos = creditos;
    }

    public void setCodigo(String codigo) {
        Matcher codigoM = this.codigoPattern.matcher(codigo);
        if(codigoM.matches()){
            if(codigo.length()==6){
                codigo = "00" + codigo;
            }
            this.codigo = codigo;
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void setEmail(String email) {
        Matcher emailM = this.emailPattern.matcher(email);
        if(emailM.matches()){
            this.email = email;
        }
    }

    public void setCelular(String celular) {
        Matcher celularM = this.celularPattern.matcher(celular);
        if(celularM.matches()){
            this.celular = celular;
        }
    }


    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public String getEmail() {
        return email;
    }

    public String getCelular() {
        return celular;
    }

    public double getGpa() {
        double accNota=0;
        int accCreds = 0;
        for(Materia materia: materias){
            if(materia.getNota() != -1){
                accNota += materia.getNota() * materia.getCreditos() * 1.0;
                accCreds += materia.getCreditos();
            }
        }
        gpa = accNota/accCreds;
        return Math.round(gpa*100)/100.0;
    }

    public int getTotalCreditos() {
        return totalCreditos;
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    @Override
    public int compareTo(Alumno t) {
        return getCodigo().compareTo(t.getCodigo()); //To change body of generated methods, choose Tools | Templates.
    }

    public void eliminarMaterias(){
        for(int i=0; i<materias.size(); i++){
            materias.remove(i);
        }
    }








}
