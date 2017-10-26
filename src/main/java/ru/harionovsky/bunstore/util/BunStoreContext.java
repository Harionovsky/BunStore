/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.harionovsky.bunstore.util;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.harionovsky.bunstore.models.*;

/**
 *
 * @author Harionovsky
 */
public class BunStoreContext {
    
    public DatabaseSet<Ware> Wares;
    /*public DatabaseSet<WarehouseEntity> Warehouses;
    public DatabaseSet<ReserveEntity> Reserves;
    public DatabaseSet<OrderEntity> Orders;*/
    /*
    static {
        Wares = new DatabaseSet<Ware>();
    }
    */
    public BunStoreContext() {
        Wares = new DatabaseSet<>(Ware.class, "WARES");
    }
    
    /*
    public List<Ware> AllWare() {
                Session objS = HibernateUtil.getFactory().openSession();
        try {
            List<Ware> wares;
            wares = objS.createQuery("from WARES").list();
            return wares;
        }
        catch (HibernateException objE) {
            return null;
        }
    }*/
}
