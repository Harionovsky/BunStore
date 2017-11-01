/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.harionovsky.bunstore.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.harionovsky.bunstore.models.*;
import ru.harionovsky.bunstore.utils.Basket;

/**
 *
 * @author Harionovsky
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {
    
    @RequestMapping
    public ModelAndView order() {
        ModelAndView mvOrder = new ModelAndView("order");
        List<Orders> listOrder = dbBS.Order.where("IsDone is null", "ID desc");
        List<String[]> listO = new ArrayList<>(listOrder.size());
        List<Reserve> listReserve;
        StringBuilder strBuilder;
        Ware elemWare;

        for (Orders itemO : listOrder) {
            String[] arrItem = new String[5];
            arrItem[0] = "" + itemO.getId();
            arrItem[1] = itemO.getFio();
            arrItem[2] = itemO.getPhone();
            arrItem[3] = itemO.getAddress();

            listReserve = dbBS.Reserve.where("OrderID = " + itemO.getId());
            strBuilder = new StringBuilder(listReserve.size());
            for (Reserve itemR : listReserve) {
                elemWare = dbBS.Ware.find(itemR.getWareid());
                if (elemWare != null) {
                    if (strBuilder.length() > 0)
                        strBuilder.append("; ");
                    strBuilder.append("(").append(elemWare.getCode()).append(") ").append(elemWare.getName()).
                            append(" = ").append(itemR.getQuantity()).append(" шт.");
                }
            }
            arrItem[4] = strBuilder.toString();

            listO.add(arrItem); 
        }
        
        mvOrder.addObject("listO", listO);
        return mvOrder;
    }    
    
    
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
        Basket objBasket = new Basket(objRequest, objResponse);
        String[] arrBasket = objBasket.all();
        int[] arrWare = new int[arrBasket.length];
        int[] arrQuant = new int[arrBasket.length];
        Ware elemWare;
        Warehouse elemWH;
        Reserve elemReserve;
        String[] arrLine;
        int iWareID, iQuant;
        boolean bIsEmpty = true;

        // Проверяем количество и списываем его со склада
        for (int i = 0; i < arrBasket.length; i++) {
            arrLine = arrBasket[i].split("=");
            iWareID = Integer.parseInt(arrLine[0]);
            iQuant = Integer.parseInt(arrLine[1]);
            elemWare = dbBS.Ware.find(iWareID);
            if (elemWare != null && iQuant > 0) {
                elemWH = dbBS.Warehouse.first("WareID = " + iWareID);
                if ((elemWH != null) && (elemWH.getQuantity() >= iQuant)) {
                    elemWH.setQuantity(elemWH.getQuantity() - iQuant);
                    dbBS.Warehouse.update(elemWH);
                    arrWare[i] = iWareID;
                    arrQuant[i] = iQuant;
                    bIsEmpty = false;
                }
                else {
                    arrWare[i] = 0;
                    arrQuant[i] = 0;
                }
            }
        }
        
        // Создаём заявку и заносим количество в резерв
        if (bIsEmpty == false) {
            Orders elemOrder = new Orders(FIO, Phone, Address);
            int iOrderID = dbBS.Order.insert(elemOrder);
            
            for (int i = 0; i < arrWare.length; i++) {
                elemReserve = new Reserve();
                elemReserve.setOrderid(iOrderID);
                elemReserve.setWareid(arrWare[i]);
                elemReserve.setQuantity(arrQuant[i]);
                dbBS.Reserve.insert(elemReserve);
            }
        }

        // Очищаем корзину
        objBasket.save("");
        return new ModelAndView("redirect:/order/thanks");
    }
    
    
    @RequestMapping("/cancel")
    public ModelAndView orderCancel(int id) {
        Orders elemOrder = dbBS.Order.find(id);
        if (elemOrder != null) {
            List<Reserve> listReserve = dbBS.Reserve.where("OrderID = " + elemOrder.getId());
            for (Reserve itemR : listReserve) {
                Warehouse elemWH = dbBS.Warehouse.first("WareID = " + itemR.getWareid());
                if (elemWH == null) {
                    elemWH = new Warehouse(itemR.getWareid(), itemR.getQuantity());
                    dbBS.Warehouse.insert(elemWH);
                }
                else {
                    elemWH.setQuantity(elemWH.getQuantity() + itemR.getQuantity());
                    dbBS.Warehouse.update(elemWH);
                }
                dbBS.Reserve.delete(itemR);
            }
            dbBS.Order.delete(elemOrder);
        }
        return new ModelAndView("redirect:/order");
    }
    
    
    @RequestMapping("/done")
    public ModelAndView orderDone(int id) {
        Orders elemOrder = dbBS.Order.find(id);
        if (elemOrder != null) {
            elemOrder.setIsdone(true);
            dbBS.Order.update(elemOrder);
        }
        return new ModelAndView("redirect:/order");
    }
    
    
    @RequestMapping("/thanks")
    public ModelAndView thanks() {
        return new ModelAndView("thanks");
    }
}
