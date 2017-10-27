/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.harionovsky.bunstore.utils;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Harionovsky
 */
public class HibernateUtil {

    private static final SessionFactory objFactory;
    
    static {
        try {
            objFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception objE) {
            throw new ExceptionInInitializerError(objE);
        }
    }
    
    public static SessionFactory getFactory() {
        return objFactory;
    }
}
