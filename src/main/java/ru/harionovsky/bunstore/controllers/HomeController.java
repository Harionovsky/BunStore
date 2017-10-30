/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.harionovsky.bunstore.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.harionovsky.bunstore.models.Ware;
import ru.harionovsky.bunstore.models.Warehouse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.harionovsky.bunstore.utils.Basket;

/**
 *
 * @author Harionovsky
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {
    
    @RequestMapping
    public ModelAndView home(HttpServletRequest objRequest) {
        ModelAndView mvWare = new ModelAndView("home");
        List<Ware> listWare = dbBS.Ware.all();
        List<String[]> listW = new ArrayList<>(listWare.size());
        int iCount;

        for (Ware itemW : listWare) {
            String[] arrItem = new String[4];
            arrItem[0] = "" + itemW.getId();
            arrItem[1] = itemW.getName();
            arrItem[2] = itemW.getDescription();
            
            List<Warehouse> listWH = dbBS.Warehouse.where("WareID = " + itemW.getId());
            iCount = 0;
            for (Warehouse itemWH : listWH) {
                iCount += itemWH.getQuantity();
            }
            arrItem[3] = (iCount > 9 ? "2" : (iCount > 0 ? "1" : "0"));

            listW.add(arrItem);                
        }

        mvWare.addObject("listW", listW);
        
        Basket objBasket = new Basket(objRequest);
        mvWare.addObject("inBasket", objBasket.size());
        
        return mvWare;
    }
    
    
    @RequestMapping("/put")
    public ModelAndView put(int id, HttpServletRequest objRequest, HttpServletResponse objResponse) {
        Basket objBasket = new Basket(objRequest, objResponse);
        objBasket.put(id);
        return new ModelAndView("redirect:/home");
    }
    
    
    @RequestMapping("/basket")
    public ModelAndView basket(HttpServletRequest objRequest) {
        ModelAndView mvBasket = new ModelAndView("basket");
        Basket objBasket = new Basket(objRequest);
        List<String> listBasket = Arrays.asList(objBasket.all());
        List<String[]> listW = new ArrayList<>(listBasket.size());

        for (String itemB : listBasket) {
            String[] arrLine = itemB.split("=");
            int iWareID = Integer.parseInt(arrLine[0]);
            int iCount = Integer.parseInt(arrLine[1]);
            Ware elemWare = dbBS.Ware.find(iWareID);
            if (elemWare != null) {
                String[] arrItem = new String[3];
                arrItem[0] = "" + elemWare.getId();
                arrItem[1] = elemWare.getName();
                arrItem[2] = "" + iCount;
                listW.add(arrItem);
            }
        }
        
        mvBasket.addObject("listW", listW);
        return mvBasket;
    }
    
    
    @RequestMapping("/save")
    public ModelAndView saveAndGo(String go, HttpServletRequest objRequest, HttpServletResponse objResponse) {
        //Basket objBasket = new Basket(objRequest, objResponse);
        //objBasket.put(id);
        return new ModelAndView("redirect:/" + go);
    }
    
    
    @RequestMapping("/welcome")
    public ModelAndView welcome() {
        return new ModelAndView("welcome");
    }
}