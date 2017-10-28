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
import org.springframework.web.servlet.ModelAndView;
import ru.harionovsky.bunstore.models.Ware;
import ru.harionovsky.bunstore.models.Warehouse;

/**
 *
 * @author Harionovsky
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {
    
    @RequestMapping
    public ModelAndView home() {
        ModelAndView mvWare = new ModelAndView("home");
        List<Ware> listWare = dbBS.Ware.all();
        //mvWare.addObject("listWare", listWare);
        
        List<String[]> listW = new ArrayList<>(listWare.size());
        int iCount;

        for (Ware itemW : listWare) {
            String[] arrItem = new String[3];
            arrItem[0] = itemW.getName();
            arrItem[1] = itemW.getDescription();
            
            List<Warehouse> listWH = dbBS.Warehouse.where("WareID = " + itemW.getId());
            iCount = 0;
            for (Warehouse itemWH : listWH) {
                iCount += itemWH.getQuantity();
            }
            arrItem[2] = (iCount == 0 ? "0" : (iCount < 10 ? "1" : "2"));

            listW.add(arrItem);                
        }

        mvWare.addObject("listW", listW);
        
        
        
        return mvWare;
    }
    
    
    @RequestMapping("/welcome")
    public ModelAndView welcome() {
        return new ModelAndView("welcome");
    }
}