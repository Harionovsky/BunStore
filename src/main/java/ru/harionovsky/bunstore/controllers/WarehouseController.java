/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.harionovsky.bunstore.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.harionovsky.bunstore.models.Ware;
import ru.harionovsky.bunstore.models.Warehouse;

/**
 *
 * @author Harionovsky
 */
@Controller
@RequestMapping("/warehouse")
public class WarehouseController extends BaseController {
    
    @RequestMapping
    public ModelAndView warehouse() {
        ModelAndView mvWarehouse = new ModelAndView("warehouse");
        List<Warehouse> listWarehouse = dbBS.Warehouse.all();
        List<String[]> listWH = new ArrayList<>(listWarehouse.size());

        for (Warehouse itemWH : listWarehouse) {
            Ware elemWare = dbBS.Ware.find(itemWH.getWareid());
            if (elemWare != null) {
                String[] arrItem = new String[4];
                arrItem[0] = "" + itemWH.getId();
                arrItem[1] = "(" + elemWare.getCode() + ") " + elemWare.getName();
                arrItem[2] = "" + itemWH.getQuantity();
                arrItem[3] = "";
                listWH.add(arrItem);                
            }
        }

        mvWarehouse.addObject("listWH", listWH);
        return mvWarehouse;
    }
    
    
    @RequestMapping("/inc")
    public ModelAndView warehouseInc() {
        ModelAndView mvWarehouseEdit = new ModelAndView("warehouseedit");
        mvWarehouseEdit.addObject("title", "Добавление товара на склад");
        mvWarehouseEdit.addObject("act", "inc");
        List<Ware> listWare = dbBS.Ware.all();
        mvWarehouseEdit.addObject("listWare", listWare);
        mvWarehouseEdit.addObject("qq", listWare.size());
        return mvWarehouseEdit;
    }
    
    
    @RequestMapping(value = "/inc", method = RequestMethod.POST)
    public ModelAndView warehouseIncrement(int WareID, int Quantity) {
        Ware elemWare = dbBS.Ware.find(WareID);
        if (elemWare != null) {
            Warehouse elemWarehouse = dbBS.Warehouse.first("WareID = " + WareID);
            if (elemWarehouse == null) {
                elemWarehouse = new Warehouse(WareID, Quantity);
                dbBS.Warehouse.insert(elemWarehouse);
            }
            else {
                elemWarehouse.setQuantity(elemWarehouse.getQuantity() + Quantity);
                dbBS.Warehouse.update(elemWarehouse);                
            }
        }
        return new ModelAndView("redirect:/warehouse");
    }
    
    
    @RequestMapping("/dec")
    public ModelAndView warehouseDec() {
        ModelAndView mvWarehouseEdit = new ModelAndView("warehouseedit");
        mvWarehouseEdit.addObject("title", "Списание товара со склада");
        mvWarehouseEdit.addObject("act", "dec");
        List<Ware> listWare = dbBS.Ware.all();
        mvWarehouseEdit.addObject("listWare", listWare);
        return mvWarehouseEdit;
    }
    
    
    @RequestMapping(value = "/dec", method = RequestMethod.POST)
    public ModelAndView warehouseDecrement(int WareID, int Quantity) {
        Ware elemWare = dbBS.Ware.find(WareID);
        if (elemWare != null) {
            Warehouse elemWarehouse = dbBS.Warehouse.first("WareID = " + WareID);
            if (elemWarehouse != null) {
                if (elemWarehouse.getQuantity() > Quantity) {
                    elemWarehouse.setQuantity(elemWarehouse.getQuantity() - Quantity);
                    dbBS.Warehouse.update(elemWarehouse);                
                }
                else
                    dbBS.Warehouse.delete(elemWarehouse);
            }
        }
        return new ModelAndView("redirect:/warehouse");
    }
}