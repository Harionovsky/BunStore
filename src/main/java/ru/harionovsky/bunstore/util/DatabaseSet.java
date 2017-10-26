/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.harionovsky.bunstore.util;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Harionovsky
 */
public class DatabaseSet<TEntity> {
    
    private final Class objClass;
    private final String sTableName;
    
    
    public DatabaseSet (Class objClass, String sTableName) {
        this.objClass = objClass;
        this.sTableName = sTableName;
    }
    
    
    public TEntity find(int iID) {
        Session objS = HibernateUtil.getFactory().openSession();
        try {
            return (TEntity)objS.get(objClass, iID);
        }
        catch (HibernateException objE) {
            return null;
        }
    }
    
    
    public Integer add(TEntity objNew) {
        Session objS = HibernateUtil.getFactory().openSession();
        Transaction objT = null;
        try {
            objT = objS.beginTransaction();
            Integer iNewID = (Integer)objS.save(objNew);
            objT.commit();
            return iNewID;
        }
        catch (HibernateException objE) {
            if (objT != null)
                objT.rollback();
            return null;
        }
    }
    
    
    public void save(TEntity objSave) {
        Session objS = HibernateUtil.getFactory().openSession();
        Transaction objT = null;
        try {
            objT = objS.beginTransaction();
            objS.update(objSave);
            objT.commit();
        }
        catch (HibernateException objE) {
            if (objT != null)
                objT.rollback();
        }
    }
    
    
    public List<TEntity> all() {
        Session objS = HibernateUtil.getFactory().openSession();
        try {
            return objS.createQuery("from " + sTableName).list();
        }
        catch (HibernateException objE) {
            return null;
        }
    }
}
