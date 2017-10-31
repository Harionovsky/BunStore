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

    public static final String SEPARATOR = ",";
    
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
        sCookie = (sCookie == null ? "" : sCookie.trim());
    }
    
    
    public Basket(HttpServletRequest objRequest, HttpServletResponse objResponse) {
        this(objRequest);
        this.objResponse = objResponse;
    }
    
    
    public int size() {
        return (sCookie.isEmpty() ? 0 : sCookie.split(SEPARATOR).length);
    }
    
    
    public String[] all() {
        return (sCookie.isEmpty() ? new String[0] : sCookie.split(SEPARATOR));
    }
    
    
    public void put(int iWareID) {
        String sResult;
        if (sCookie.isEmpty())
            sResult = iWareID + "=1";
        else {
            String[] arrBasket = sCookie.split(SEPARATOR);
            StringBuilder strBuilder = new StringBuilder(arrBasket.length);
            boolean bNeedAdd = true;
            for (String itemB : arrBasket) {
                if (strBuilder.length() > 0)
                    strBuilder.append(SEPARATOR);
                if (itemB.startsWith(iWareID + "=")) {
                    int iCount = Integer.parseInt(itemB.split("=")[1]);
                    strBuilder.append(iWareID).append("=").append(++iCount);
                    bNeedAdd = false;
                } else {
                    strBuilder.append(itemB);
                }
            }
            if (bNeedAdd) {
                if (strBuilder.length() > 0)
                    strBuilder.append(SEPARATOR);
                strBuilder.append(iWareID).append("=1");
            }
            sResult = strBuilder.toString();
        }
        this.save(sResult);
    }
    
    
    public void del(int iWareID) {
        if (sCookie.isEmpty() == false) {
            String[] arrBasket = sCookie.split(SEPARATOR);
            StringBuilder strBuilder = new StringBuilder(arrBasket.length - 1);
            for (String itemB : arrBasket) {
                if (itemB.startsWith(iWareID + "=") == false) {
                    if (strBuilder.length() > 0)
                        strBuilder.append(SEPARATOR);
                    strBuilder.append(itemB);
                }
            }
            this.save(strBuilder.toString());
        }
    }
    
    
    public void save(String sCookieNew) {
        sCookie = (sCookieNew == null ? "" : sCookieNew.trim());
        if (objResponse != null) {
            Cookie objCookie = new Cookie("basket", sCookie);
            objCookie.setPath("/");
            objResponse.addCookie(objCookie);
        }
    }
}
