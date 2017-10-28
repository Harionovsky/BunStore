/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.harionovsky.bunstore.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.harionovsky.bunstore.models.Ware;

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
        mvWare.addObject("listWare", listWare);
        return mvWare;
    }
    
    
    @RequestMapping("/welcome")
    public ModelAndView welcome() {
        return new ModelAndView("welcome");
    }
}