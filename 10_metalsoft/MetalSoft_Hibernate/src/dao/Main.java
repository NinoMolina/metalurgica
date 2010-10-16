package dao;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.Presupuesto;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nino
 */
public class Main {

    public static void main(String arg[]){

        DaoPresupuesto<Presupuesto> dao=new DaoPresupuesto<Presupuesto>();
        System.out.println(Presupuesto.class.getSimpleName());
        Presupuesto p=dao.findById(19l, Presupuesto.class.getSimpleName());
        List<Presupuesto> list=dao.findAll(Presupuesto.class.getSimpleName());
        System.out.println(p.toString());
    }
}
