/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.harionovsky.bunstore.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.harionovsky.bunstore.models.Orders;
import ru.harionovsky.bunstore.models.Ware;
import ru.harionovsky.bunstore.models.Warehouse;
import ru.harionovsky.bunstore.utils.Basket;

/**
 *
 * @author Harionovsky
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView orderAdd(String[] Ware, String[] Count,
            HttpServletRequest objRequest, HttpServletResponse objResponse) {
        String sCookie = "";
        int iWareID, iQuant;

        if (Ware.length == Count.length) {
            StringBuilder strBuilder = new StringBuilder(Ware.length);
            for (int i = 0; i < Ware.length; i++) {
                iQuant = Integer.parseInt(Count[i]);
                iWareID = Integer.parseInt(Ware[i]);
                Ware elemWare = dbBS.Ware.find(iWareID);
                if (elemWare != null && iQuant > 0) {
                    Warehouse elemWH = dbBS.Warehouse.first("WareID = " + iWareID);
                    if ((elemWH == null) || (elemWH.getQuantity() < iQuant)) {
                        ModelAndView mvImpossible = new ModelAndView("impossible");
                        mvImpossible.addObject("message", "Извините, на складе нет товара \"" + elemWare.getName() + "\" в достаточном количестве");
                        return mvImpossible;
                    }
                    else {
                        if (strBuilder.length() > 0)
                            strBuilder.append(Basket.SEPARATOR);
                        strBuilder.append(Ware[i]).append("=").append(Count[i]);
                    }
                }
            }
            sCookie = strBuilder.toString();
        }

        if (sCookie.isEmpty()) {
            ModelAndView mvImpossible = new ModelAndView("impossible");
            mvImpossible.addObject("message", "Корзина пуста");
            return mvImpossible;
        }
        
        Basket objBasket = new Basket(objRequest, objResponse);
        objBasket.save(sCookie);
        
        return new ModelAndView("orderedit");
    }
    
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView orderInsert(String FIO, String Phone, String Address, HttpServletRequest objRequest, HttpServletResponse objResponse) {
        //Basket objBasket = new Basket(objRequest, objResponse);
        // TODO: проверяем количество
        Orders elemOrder = new Orders(FIO, Phone, Address);
        dbBS.Order.insert(elemOrder);
        // TODO: заполняем таблицу "OrderWare"
        // TODO: списываем количество из таблицы "Warehouse"
        // TODO: добавляем кол-во в таблицу "Reserve"
        // TODO: очищаем "куки"
        return new ModelAndView("redirect:/home");
    }
}
