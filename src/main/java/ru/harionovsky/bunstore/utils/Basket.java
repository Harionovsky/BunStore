/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.harionovsky.bunstore.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Harionovsky
 */
public class Basket {
    
    private HttpServletResponse objResponse;
    private String sCookie;
    
    
    public Basket(HttpServletRequest objRequest) {
        Cookie[] arrCookies = objRequest.getCookies();
        if (arrCookies != null) {
            for (Cookie itemC : arrCookies) {
                if (itemC.getName().equals("basket")) {
                    sCookie = itemC.getValue();
                    break;
                }
            }
        }
    }
    
    
    public Basket(HttpServletRequest objRequest, HttpServletResponse objResponse) {
        this(objRequest);
        this.objResponse = objResponse;
    }
    
    
    public int size() {
        return (sCookie != null ? sCookie.split(" ").length : 0);
    }
    
    
    public String[] all() {
        return (sCookie != null ? sCookie.split(" ") : new String[0]);
    }
    
    
    public void put(int iWareID) {
        if (objResponse != null) {
            if (sCookie != null) {
                String[] arrBasket = sCookie.split(" ");
                StringBuilder strBuilder = new StringBuilder(arrBasket.length);
                boolean bNeedAdd = true;
                for (String itemB : arrBasket) {
                    if (itemB.startsWith(iWareID + "=")) {
                        int iCount = Integer.parseInt(itemB.split("=")[1]);
                        strBuilder.append(iWareID).append("=").append(++iCount).append(" ");
                        bNeedAdd = false;
                    }
                    else
                        strBuilder.append(itemB).append(" ");
                }
                if (bNeedAdd) {
                    strBuilder.append(iWareID).append("=1 ");
                }
                sCookie = strBuilder.toString();
            }
            else
                sCookie = iWareID + "=1 ";
            
            Cookie objCookie = new Cookie("basket", sCookie);
            objResponse.addCookie(objCookie);
        }
    }
    
    
    public void del(int iWareID) {
        if (sCookie != null) {
            String[] arrBasket = sCookie.split(" ");
            StringBuilder strBuilder = new StringBuilder(arrBasket.length - 1);
            for (String itemB : arrBasket) {
                if (itemB.startsWith(iWareID + "=") == false)
                    strBuilder.append(itemB).append(" ");
            }
            this.save(strBuilder.toString());
        }
    }
    
    
    public void save(String sCookieNew) {
        if (objResponse != null) {
            sCookie = sCookieNew;
            Cookie objCookie = new Cookie("basket", sCookie);
            objResponse.addCookie(objCookie);
        }
    }
}
