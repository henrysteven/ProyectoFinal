/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_final;

import java.io.File;

/**
 *
 * @author Henry
 * @param <K>
 * @param <T>
 */
public class TreeMap<K, T> {

    private NodeTreeMap<K, T> root;

    public TreeMap() {
        this.root = null;
    }

    public TreeMap(K key) {
        this.root = new NodeTreeMap<>(key);
    }

    public TreeMap(NodeTreeMap<K, T> root) {
        this.root = root;
    }

    public TreeMap(K key, T content) {
        this.root = new NodeTreeMap<>(key, content);
    }

    public NodeTreeMap<K, T> getRoot() {
        return root;
    }

    public void setRoot(NodeTreeMap<K, T> root) {
        this.root = root;
    }

    public void addHijos(TreeMap<K, T> arbol) {
        this.root.getHijos().add(arbol);
    }

    public boolean isLeaf() {
        return root.getHijos().isEmpty();
    }

    public int nivelArbol(TreeMap<K, T> arbol,TreeMap<K, T> b) {
        int mayor = 0;
        int tmp;
        if (arbol == null && arbol.getRoot().getKey().equals(b.getRoot().getKey())) {
            return 0;
        } else {
            for (TreeMap<K, T> hijo : arbol.getRoot().getHijos()) {
                tmp = nivelArbol(hijo,b);
                if (tmp > mayor) {
                    mayor = tmp;
                }
            }
            return mayor + 1;
        }
    }


}
