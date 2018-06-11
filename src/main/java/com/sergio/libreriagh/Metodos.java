package com.sergio.libreriagh;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
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
     /**
     * Método para hacer un commit
     *
     * @param ruta Ruta del repositorio
     * @param msn Mensaje del commit
     */
    public static void hacerCommit(String ruta, String msn){
        try{
            /*Creamos un objeto del tipo repository builder y le mandamos 
            * los parámetros
            */
            
            FileRepositoryBuilder repositoryBuilder=new FileRepositoryBuilder();
            Repository repository=repositoryBuilder.setGitDir(new File(ruta))
                    .readEnvironment()
                    .findGitDir()
                    .setMustExist(true)
                    .build();

            Git git=new Git(repository);
            AddCommand add=git.add();
            add.addFilepattern(ruta).call();
            CommitCommand commit=git.commit();
            commit.setMessage(msn).call();
            //Recogemos la excepcion
        }catch(IOException ex){
            System.out.println("Error:"+ex);
        }catch(GitAPIException ex){
            System.out.println("Error:"+ex);
        }

    }
    
     /**
     * Método para inicializar un repositorio
     *
     * @param ruta Ruta del repositorio
     */
    public static void inicializarRepo(String ruta){
        InitCommand repositorio=new InitCommand();
        try{
            repositorio.setDirectory(new File(ruta)).call();
            //Recogemos la excepcion
        }catch(GitAPIException ex){
            System.out.println("Error:"+ex);
        }
    }
 
    /**
     * 
     * @param url del repositorio
     * @param repositorio nombre del repositorio 
     * @param contrasena del usuario
     * @param usuario de github
     */
    public static void push(String url, String repositorio,String contrasena,String usuario){
        try{
            /*Creamos un objeto de tipo FileRepositoryBuilder y le mandamos
            * los parámetros para hacer un push del repositorio
            *
            */
            FileRepositoryBuilder repositoryBuilder=new FileRepositoryBuilder();
            Repository repository=repositoryBuilder.setGitDir(new File(repositorio))
                    .readEnvironment()
                    .findGitDir()
                    .setMustExist(true)
                    .build();
            
            Git git=new Git(repository);

            RemoteAddCommand remoteAddCommand=git.remoteAdd();
            remoteAddCommand.setName("origin");
            remoteAddCommand.setUri(new URIish(url));
            remoteAddCommand.call();
            
            PushCommand pushCommand=git.push();
            pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(usuario, contrasena));
            pushCommand.call();
         //Recogemos la excepcion   
        }catch(IOException ex){
            System.out.println("Error: "+ex);
        }catch(URISyntaxException ex){
            System.out.println("Error: "+ex);
        }catch(GitAPIException ex){
            System.out.println("Error: "+ex);
        }
    }
}
//FIN