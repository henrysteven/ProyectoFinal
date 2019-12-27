/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_final;

import java.io.File;
import java.util.Stack;

/**
 *
 * @author Henry
 */
public class Metodos {

    public static int directorio(String url) {
        Stack<File> pila = new Stack();
        int tamaño = 0;
        File carpeta = new File(url);
        File[] listado = carpeta.listFiles();
        //System.out.println(carpeta.getUsableSpace());
        if (listado == null || listado.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");
        } else {
            for (File archivo : listado) {
                if (archivo.isDirectory()) {
                    pila.push(archivo);
                } else {
                    tamaño += archivo.length();
                }
            }
            while (!pila.isEmpty()) {
                File archivo1 = pila.pop();
                if (archivo1.isDirectory()) {
                    File[] arFile = archivo1.listFiles();
                    for (File file : arFile) {
                        pila.push(file);
                    }
                } else {
                    tamaño += archivo1.length();
                }
            }
        }
        return tamaño;
    }

}
