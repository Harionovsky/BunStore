/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.harionovsky.bunstore.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.harionovsky.bunstore.models.Ware;
import ru.harionovsky.bunstore.utils.BunStoreContext;

/**
 *
 * @author Harionovsky
 */
@Controller
public class MainController {
    
    private final BunStoreContext dbBunStore;
    
    
    public MainController() {
        dbBunStore = new BunStoreContext();
    }

    
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

    
    
    @RequestMapping("/warehouse")
    public ModelAndView warehouse() {
        return new ModelAndView("warehouse");
    }
}
