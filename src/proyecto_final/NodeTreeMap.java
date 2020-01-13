/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_final;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Henry
 * @param <K>
 * @param <T>
 */
public class NodeTreeMap<K, T> {

    private T content;
    private K key;
    private List<TreeMap<K, T>> hijos;

    public NodeTreeMap() {
        this.content = null;
        this.hijos = null;
        this.key = null;
    }

    public NodeTreeMap(K key) {
        this.key = key;
        this.content = null;
        this.hijos = new LinkedList<>();

    }

    public NodeTreeMap(K key, T content) {
        this.content = content;
        this.hijos = null;
        this.key = key;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public List<TreeMap<K, T>> getHijos() {
        return hijos;
    }

    public void setHijos(List<TreeMap<K, T>> hijos) {
        this.hijos = hijos;
    }

    

}
