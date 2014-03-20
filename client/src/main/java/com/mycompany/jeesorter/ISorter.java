/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.jeesorter;

import javax.ejb.Remote;

/**
 *
 * @author han
 */
@Remote
public interface ISorter {
    public <T> T[] sort(T[] input);
}
