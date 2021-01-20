/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util;

/**
 *
 * @author POkechukwu
 */
public enum ResponseCode {

    _00("00", "Operation Completed Successfully"),
    _34("34", "Invalid Phone Number Lenght"),
    VENDOR_COMMUNICATION_ERROR("A90", "Error while communicating with nibss service"),
    PAYLOAD_PROCESSING_ERROR("A91", "Error occured while retrieving request object from payload"),
    INVALID_ACCOUNT_NUMBER("A92", "No Record Found For Account Number Or Invalid Treasury Single Account Supplied"),
    ENCRYPTION_FAILURE("A92", "Error while encryption message involved in transaction"),
    OPERATION_CAPACITY_ERROR("A93", "Error trying to fulfill request on service method"),
    _94("94", "Unable to interprete response from nibss"),
    _95("95", "Unknown error occured"),
    _96("96", "System malfunction"),
    _97("97", "Timeout waiting for response from destination"),
    _98("A98", "NIBSS TSA Revenue Collection service is not available"),
	_99("99", "Bad Request");
    

    private final String code;
    private final String description;

    ResponseCode(String code, String description) {

        this.code = code;
        this.description = description;

    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static ResponseCode getItem(String code) {
        for (ResponseCode item : ResponseCode.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

}
