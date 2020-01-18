/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_final;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Henry
 */
public class Metodos {

    private Pane root = new Pane();
    List<Rectangle> rectangles = new LinkedList<>();

    public Metodos() throws IOException {
        String ruta = "C:\\Users\\Henry\\Documents\\NetBeansProjects";
//        File objetofile = new File(ruta);
//        Desktop.getDesktop().open(objetofile);
        TreeMap<String, Number> arbol = Metodos.generarArbol(ruta);
        System.out.println(arbol.getRoot().getContent());
        TreeMap<String, Number> nodo = arbol.getRoot().getHijos().get(0).getRoot().getHijos().get(1);
        System.out.println(nodo.getRoot().getKey());
        System.out.println(arbol.nivelArbol(arbol, nodo));
        System.out.println(nombreArchivo(arbol));
        root.setMaxHeight(1000);
        root.setMaxWidth(500);
        getTree(arbol, 0.0, new Rectangle(1000, 607), arbol.getRoot().getContent().doubleValue(), 0);

    }

    public Pane crearRectangulo(double anchura, double altura) {
        Pane pane = new Pane();
        Rectangle rec = new Rectangle(anchura, altura);

        Random num = new Random();
        rec.setFill(Color.rgb(num.nextInt(255), num.nextInt(255), num.nextInt(255)));
        pane.getChildren().add(rec);
        return pane;
    }

    public String nombreArchivo(TreeMap<String, Number> arbol) {
        File f = new File(arbol.getRoot().getKey());
        return f.getName();
    }

    public Double getTree(TreeMap<String, Number> arbol, Double desplazamiento, Rectangle padre, Double tamaño_padre, int nivel) {

        double tamaño = arbol.getRoot().getContent().doubleValue() / tamaño_padre;
        double tamaño_X = padre.getWidth();
        double tamaño_Y = padre.getHeight();
        double posicion_X = padre.getLayoutX();
        double posicion_Y = padre.getLayoutY();
        Rectangle rectangulo = new Rectangle();
        rectangulo.setManaged(false);
        if (nivel % 2 != 0) {
            rectangulo.setLayoutX(posicion_X + desplazamiento);
            rectangulo.setLayoutY(posicion_Y);
            rectangulo.setWidth(tamaño * tamaño_X);
            rectangulo.setHeight(tamaño_Y);
            desplazamiento = desplazamiento + rectangulo.getWidth();
        } else if (nivel % 2 == 0) {
            rectangulo.setLayoutX(posicion_X);
            rectangulo.setLayoutY(posicion_Y + desplazamiento);
            rectangulo.setWidth(tamaño_X);
            rectangulo.setHeight(tamaño * tamaño_Y);
            desplazamiento = desplazamiento + rectangulo.getHeight();

        }

        if (arbol.isLeaf()) {
            rectangulo.setId(nombreArchivo(arbol));

            estiloRectangulo(rectangulo);
            root.getChildren().add(rectangulo);
        } else {
            double des = 0;
            for (TreeMap<String, Number> a : arbol.getRoot().getHijos()) {
                des = getTree(a, des, rectangulo, arbol.getRoot().getContent().doubleValue(), nivel + 1);
            }
        }
        Pane p = new Pane();
        Label l = new Label(nombreArchivo(arbol));

        l.setManaged(false);
        l.setLayoutX(posicion_X + desplazamiento);
        l.setLayoutY(posicion_Y);
        p.getChildren().add(l);
        root.getChildren().add(p);
        return desplazamiento;
    }

    public void estiloRectangulo(Rectangle r) {
        r.setArcWidth(7);
        r.setArcHeight(6);
        Map<String, Color> mapa = new HashMap<>();
        mapa.put("docx", Color.BLUE);
        mapa.put("pdf", Color.RED);
        mapa.put("pptx", Color.RED);
        mapa.put("xlsx", Color.GREEN);
        String ao = r.getId();
        String[] s = ao.replace(".", ",").split(",");
        String a = "";
        if (s.length == 2) {
            a = s[1];
        }
        if (mapa.containsKey(a)) {
            r.setFill(mapa.get(a));
        } else {
            Random num = new Random();
            r.setFill(Color.rgb(num.nextInt(255), num.nextInt(255), num.nextInt(255)));
        }

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

    public static void llenarHijos(TreeMap<String, Number> arbol) {
        File fichero = new File(arbol.getRoot().getKey());
        TreeMap<String, Number> arbol1 = arbol;
        if (fichero.isDirectory()) {
            File[] listaFichero = fichero.listFiles();
            for (File f : listaFichero) {
                TreeMap<String, Number> hijo = new TreeMap<>(f.getPath());
                if (!f.isDirectory()) {
                    hijo.getRoot().setContent(f.length() * 0.001);
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

    public Pane getRoot() {
        return root;
    }

}
