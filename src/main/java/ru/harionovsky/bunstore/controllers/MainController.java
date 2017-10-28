/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.harionovsky.bunstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Harionovsky
 */
@Controller
public class MainController {
    

    
    @RequestMapping("/home")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("home");
        return mav;
    }
    
    
//    @RequestMapping("/ware")
//    public ModelAndView ware(Model model/*@PathVariable("page") String Name2*/) {
//        //@RequestParam(value = "name", required = false, defaultValue = "World") String name
//        ModelAndView mav = new ModelAndView("ware");
//        List<Ware> listWare = dbBunStore.Ware.all();
//        mav.addObject("listWare", listWare);
//        return mav;
//    }

    
    /*
    @RequestMapping("/warehouse")
    public ModelAndView warehouse() {
        return new ModelAndView("warehouse");
    }*/
}
