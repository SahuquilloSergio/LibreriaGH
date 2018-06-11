package com.sergio.libreriagh;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        
        String nombre;
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(""
                + "1.Crear repositorio\n"
                + "2.Clonar\n"
                + "3.Commit\n"
                + "4.Inicializar\n"
                + "5.Push\n"
                + "6.Salir"));
        

        switch(opcion){
            case 1:
                nombre=JOptionPane.showInputDialog("Nombre del repositorio");
                String usuario = JOptionPane.showInputDialog("Nombre de Usuario");
                String contraseña = JOptionPane.showInputDialog("Contraseña");
                Metodos.crear(nombre, usuario, contraseña);
                break;
            case 2:
                nombre = JOptionPane.showInputDialog("Nombre que le va a dar al nuevo proyecto.");
                String url = JOptionPane.showInputDialog("Url del proyecto.");
                Metodos.clonar(url, nombre);
                break;
            case 3:
                nombre = JOptionPane.showInputDialog("Ruta del repositorio");
                String msn = JOptionPane.showInputDialog("Mensaje del commit");
                Metodos.hacerCommit(nombre,msn);
                break;
            case 4:
                nombre = JOptionPane.showInputDialog("Ruta del proyecto:");
                Metodos.inicializarRepo(nombre);
                break;
            case 5:
                
                nombre = JOptionPane.showInputDialog("Ruta del proyecto:");
                url = JOptionPane.showInputDialog("Url del repositorio remoto");
                usuario = JOptionPane.showInputDialog("Usuario:");
                contraseña = JOptionPane.showInputDialog("Contraseña:");
                Metodos.push(url, nombre,contraseña,usuario);
                break;
            case 6:
                System.exit(0);
        }
    }
    }
