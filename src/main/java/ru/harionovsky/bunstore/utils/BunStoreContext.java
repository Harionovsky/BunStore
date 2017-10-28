/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.harionovsky.bunstore.utils;

import ru.harionovsky.bunstore.models.*;

/**
 *
 * @author Harionovsky
 */
public class BunStoreContext {
    
    public DatabaseSet<Orders> Order;
    public DatabaseSet<Reserve> Reserve;
    public DatabaseSet<Ware> Ware;
    public DatabaseSet<Warehouse> Warehouse;


    public BunStoreContext() {
        Order = new DatabaseSet<>(Orders.class, "Orders");
        Reserve = new DatabaseSet<>(Reserve.class, "Reserve");
        Ware = new DatabaseSet<>(Ware.class, "Ware");
        Warehouse = new DatabaseSet<>(Warehouse.class, "Warehouse");
    }
}
