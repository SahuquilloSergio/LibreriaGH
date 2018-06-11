package com.sergio.libreriagh;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.kohsuke.github.GHCreateRepositoryBuilder;
import org.kohsuke.github.GitHub;

/**
 * Clase con los métodos para crear repositorios, hacer
 * commit, inicializar repositorio, hacer push y clonar
 */
public class Metodos{

    /**
     * Método para crear un repositorio en github
     *
     * @param nombre recibe el nombre que le daremos al repositorio
     * @param usuario nombre de usuario de GitHub
     * @param contraseña del usuario
     */
    public static void crear(String nombre, String usuario, String contraseña){
        try{
            //Nos conectamos a github
            GitHub github=GitHub.connectUsingPassword(usuario, contraseña);
            GHCreateRepositoryBuilder builder;
            //Creamos el repositorio
            builder=github.createRepository(nombre);
            builder.create();
            //Recogemos la excepcion
        }catch(IOException ex){
            System.out.println("Error:"+ex);
        }
    }
 /**
     * Método para clonar un repositorio
     *
     * @param url Dirección web del repositorio
     * @param nombre Nombre con el que se guardará el proyecto
     */
    public static void clonar(String url, String nombre){
        try{
            Git.cloneRepository()
                    .setURI(url)
                    .setDirectory(new File("/home/serxa/NetBeansProjects/"+nombre))
                    .call();
        }catch(GitAPIException ex){
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}