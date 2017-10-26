/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.harionovsky.bunstore.service;

import java.util.List;
import org.hibernate.Session;
import ru.harionovsky.bunstore.models.Ware;
import ru.harionovsky.bunstore.util.HibernateUtil;

/**
 *
 * @author Harionovsky
 */
public class to_delete_WareService {
    
    private to_delete_WareService() {};
    
    public static Ware getWareById(int id) {
        
        Ware ware;
        Session session = HibernateUtil.getFactory().openSession();
        try (Session session2 = HibernateUtil.getFactory().openSession()) {
            ware = session2.get(Ware.class, id);
        }
        
        return ware;
    }    
    
    public static List<Ware> getWares() {
        
        List<Ware> wares;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            wares = session.createQuery("from WARES").list();
        }
        return wares;
    }
    
    public static void save(Ware ware) {
        
        try (Session session = HibernateUtil.getFactory().openSession()) {
            session.beginTransaction();
            
            session.save(ware);
            
            session.getTransaction().commit();
            
            session.close();
        }
    }
}
