/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.safePark.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class ListaDeCidades {
  
    public static List<ListaDeCidades> listar(){
        
        ArrayList al = new ArrayList();
   
        al.add("C");
        al.add("A");
        al.add("E");
        al.add("B");
        al.add("D");
        al.add("F");
        
        return al;
        
    }
}
