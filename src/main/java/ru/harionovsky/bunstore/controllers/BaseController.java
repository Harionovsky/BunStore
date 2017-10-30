/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.harionovsky.bunstore.controllers;

import ru.harionovsky.bunstore.utils.BunStoreContext;

/**
 *
 * @author Harionovsky
 */
public abstract class BaseController {
    
    protected final BunStoreContext dbBS;
    
    
    public BaseController() {
        dbBS = new BunStoreContext();
    }
}