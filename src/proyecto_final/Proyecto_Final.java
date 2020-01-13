/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_final;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Henry
 */
public class Proyecto_Final {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println(Metodos.directorio("C:\\Users\\Henry\\Documents\\R"));
        String ruta = "C:\\Users\\Henry\\Documents\\MEGA";
        TreeMap<String, Number> arbol = generarArbol(ruta);
        System.out.println(arbol.getRoot().getContent());

    }

    public static TreeMap<String, Number> generarArbol(String ruta) {
        TreeMap<String, Number> arbol = new TreeMap<>(ruta);
        Stack<TreeMap<String, Number>> pila = new Stack<>();
        Stack<TreeMap<String, Number>> pila2 = new Stack<>();
        pila.push(arbol);
        while (!pila.isEmpty()) {
            TreeMap<String, Number> tmp = pila.pop();
            llenarHijos(tmp);
            pila2.push(tmp);
            tmp.getRoot().getHijos().forEach((sub) -> {
                if (new File(sub.getRoot().getKey()).isDirectory()) {
                    pila.push(sub);
                }
            });
            if (estaLlenoTamaño(tmp)) {
                tmp.getRoot().setContent(generarTamañoDirectorio(tmp));
            }
        }
        while (!pila2.isEmpty()) {
            TreeMap<String, Number> tmp2 = pila2.pop();
            tmp2.getRoot().setContent(generarTamañoDirectorio(tmp2));
        }
        return arbol;
    }

    public static boolean estaLlenoTamaño(TreeMap<String, Number> arbol) {
        for (TreeMap<String, Number> treeHijo : arbol.getRoot().getHijos()) {
            if (treeHijo.getRoot().getContent() == null) {
                return false;
            }
        }
        return true;
    }

    public static void llenarpeso(TreeMap<String, Number> arbol) {
        Stack<TreeMap<String, Number>> pila = new Stack<>();
        pila.push(arbol);
        while (!pila.isEmpty()) {

        }
    }

    public static void llenarHijos(TreeMap<String, Number> arbol) {
        File fichero = new File(arbol.getRoot().getKey());
        TreeMap<String, Number> arbol1 = arbol;
        if (fichero.isDirectory()) {
            File[] listaFichero = fichero.listFiles();
            for (File f : listaFichero) {
                TreeMap<String, Number> hijo = new TreeMap<>(f.getPath());
                if (!f.isDirectory()) {
                    hijo.getRoot().setContent(f.length());
                }
                arbol.getRoot().getHijos().add(hijo);
            }
        }
    }

    public static Number generarTamañoDirectorio(TreeMap<String, Number> arbol) {
        Number peso = 0;
        for (TreeMap<String, Number> arbolHijo : arbol.getRoot().getHijos()) {
            peso = arbolHijo.getRoot().getContent().longValue() + peso.longValue();
        }
        return peso;
    }
}
