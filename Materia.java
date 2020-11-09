/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_tutor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author alejandro
 */
public class Materia implements Comparable<Materia>{
    // Private
    private String codigo = "";
    private String nombre;
    private int creditos;
    private String profesor;
    private int semestre;
    private int nota=-1;
    public static int notaA = 4;
    public static int notaB = 3;
    public static int notaC = 2;
    public static int notaD = 1;
    public static int notaF = 0;
    public static int notaNan = -1;

    public static Pattern codigoPattern = Pattern.compile("[A-Z]{3}-[0-9]{4}");

    public Materia() {}

    public Materia(String codigo, String nombre, int creditos, String profesor, int semestre, int nota) {
        this.setCodigo(codigo);
        if(!codigo.equals("")){
            this.setNombre(nombre);
            this.setCreditos(creditos);
            this.setProfesor(profesor);
            this.setSemestre(semestre);
            this.setNota(nota);
        }
    }

    @Override
    public String toString(){
        String s = "";
        s+= "Codigo:   \t\t" + this.getCodigo();
        s+= "\nNombre:  \t\t" + this.getNombre();
        s+= "\nCreditos:\t\t" + this.getCreditos();
        s+= "\nProfesor:\t\t" + this.getProfesor();
        s+= "\nSemestre:\t" + this.getSemestre();
        s+= "\nNota:    \t\t" + this.getNotaLetra() + "\n";
        return s;
    }


    public void setCodigo(String codigo) {
        Matcher codigoM = codigoPattern.matcher(codigo);
        if(codigoM.matches()){
            this.codigo = codigo;
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCreditos(int creditos) {
        if(creditos>=0 && creditos<=5){
            this.creditos = creditos;
        }
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public void setSemestre(int semestre) {
        if(semestre>=1 && semestre<=8){
            this.semestre = semestre;
        }
    }

    public void setNota(int nota) {
        if(nota==notaA ||nota==notaB || nota==notaC || nota==notaD || nota==notaF){
            this.nota = nota;
        }else{
            this.nota = notaNan;
        }
    }

    public void setNota(String nota){
        if(nota.equals("A"))
            this.nota = 4;
        if(nota.equals("B"))
            this.nota = 3;
        if(nota.equals("C"))
            this.nota = 2;
        if(nota.equals("D"))
            this.nota = 1;
        if(nota.equals("F"))
            this.nota = 0;
        if(nota.equals("Nan"))
            this.nota = -1;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public String getProfesor() {
        return profesor;
    }

    public int getSemestre() {
        return semestre;
    }

    public int getNota() {
        return nota;
    }

    public String getNotaLetra() {
        String nota = "";
        if(this.getNota() == notaA )
            nota = "A";
        if(this.getNota() == notaB )
            nota = "B";
        if(this.getNota() == notaC )
            nota = "C";
        if(this.getNota() == notaD )
            nota = "D";
        if(this.getNota() == notaF )
            nota = "F";
        if(this.getNota() == notaNan)
            nota = "Nan" ;
        return nota;
    }

    @Override
    public int compareTo(Materia t) {
        return getCodigo().compareTo(t.getCodigo()); //To change body of generated methods, choose Tools | Templates.
    }
}
