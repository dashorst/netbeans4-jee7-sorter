/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.jeesorter;

import java.util.Arrays;
import javax.ejb.Stateless;

@Stateless
public class JavaSorter implements ISorter {
    @Override
    public <T> T[] sort(T[] input) {
        T[] output = Arrays.copyOf(input, input.length);
        Arrays.sort(output);
        return output;
    }
}
