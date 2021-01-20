/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.api;

import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.exception.RedBoxTSARevenueCollectionException;
import java.io.IOException;
import org.apache.camel.Exchange;

/**
 *
 * @author David
 */
public interface NibssTSARevenueCollectionCallerApi {
    public void ping(Exchange exchange) throws IOException, RedBoxTSARevenueCollectionException;
    public void reset(Exchange exchange) throws Exception;
    public void login(Exchange exchange) throws Exception;
    public void encrypt(Exchange exchange) throws Exception;
    public void notification(Exchange exchange) throws Exception;
    public void mdalist(Exchange exchange) throws Exception; 
    
    
}
