/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.harionovsky.bunstore.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.harionovsky.bunstore.models.Ware;

/**
 *
 * @author Harionovsky
 */
@Controller
@RequestMapping("/ware")
public class WareController extends BaseController {
    
    @RequestMapping
    public ModelAndView ware() {
        ModelAndView mvWare = new ModelAndView("ware");
        List<Ware> listWare = dbBS.Ware.all();
        mvWare.addObject("listWare", listWare);
        return mvWare;
    }
    
    
    @RequestMapping("/add")
    public ModelAndView wareAdd() {
        ModelAndView mvWareAdd = new ModelAndView("wareedit");
        mvWareAdd.addObject("title", "Добавление товара");
        mvWareAdd.addObject("act", "add");
        return mvWareAdd;
    }
    
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView wareInsert(String Code, String Name, String Description) {
        Ware elemWare = new Ware(Code, Name, Description);
        dbBS.Ware.insert(elemWare);
        return new ModelAndView("redirect:/ware");
    }
    
    
    @RequestMapping("/edit")
    public ModelAndView wareEdit(int id) {
        Ware elemWare = dbBS.Ware.find(id);
        if (elemWare == null) {
            return new ModelAndView("redirect:/ware");
        }
        ModelAndView mvWareEdit = new ModelAndView("wareedit");
        mvWareEdit.addObject("title", "Редактирование товара");
        mvWareEdit.addObject("act", "edit");
        mvWareEdit.addObject("id", id);
        mvWareEdit.addObject("code", elemWare.getCode());
        mvWareEdit.addObject("name", elemWare.getName());
        mvWareEdit.addObject("desc", elemWare.getDescription());
        return mvWareEdit;
    }
    
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView wareUpdate(int ID, String Code, String Name, String Description) {
        Ware elemWare = dbBS.Ware.find(ID);
        if (elemWare != null) {
            elemWare.setCode(Code);
            elemWare.setName(Name);
            elemWare.setDescription(Description);
            dbBS.Ware.update(elemWare);
        }
        return new ModelAndView("redirect:/ware");
    }
    
    
    @RequestMapping("/delete")
    public ModelAndView wareDelete(int id) {
        Ware elemWare = dbBS.Ware.find(id);
        if (elemWare != null) {
            dbBS.Ware.delete(elemWare);
        }
        return new ModelAndView("redirect:/ware");
    }
}
