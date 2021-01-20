package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RBX_P_TSA_COLLECTION_LOG")
public class NibssTSARevenueCollectionLog implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "tsaRevCollSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tsaRevCollSeq", sequenceName = "RBX_P_TSA_REVENUE_COLL_SEQ", initialValue = 1)
    private Long id;
    @Column(name = "TRAN_TIME")
    private Date requestTime;
    @Column(name = "RESPONSE_TIME")
    private Date responseTime;
    @Lob
    @Column(name = "REQUEST_PAYLOAD")
    private String requestPayload;
    @Lob
    @Column(name = "RESPONSE_PAYLOAD")
    private String responsePayload;
    @Column(name = "RESPONSE_CODE")
    private String responseCode;
    @Column(name = "RESPONSE_DESC")
    private String responseDescription;
    @Lob
    @Column(name = "PRODUCER_REQ_PAYLOAD")
    private String producerRequestPayload;
    @Lob
    @Column(name = "PRODUCER_RESP_PAYLOAD")
    private String producerResponsePayload;
    @Column(name = "PRODUCER_RESP_CODE")
    private String producerResponseCode;
    @Column(name = "PRODUCER_RESP_DESC")
    private String producerResponseDescription;
    @Column(name = "PRODUCER_REQ_TIME")
    private Date producerRequestTime;
    @Column(name = "PRODUCER_RESP_TIME")
    private Date producerResponseTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    public String getRequestPayload() {
        return requestPayload;
    }

    public void setRequestPayload(String requestPayload) {
        this.requestPayload = requestPayload;
    }

    public String getResponsePayload() {
        return responsePayload;
    }

    public void setResponsePayload(String responsePayload) {
        this.responsePayload = responsePayload;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getProducerRequestPayload() {
        return producerRequestPayload;
    }

    public void setProducerRequestPayload(String producerRequestPayload) {
        this.producerRequestPayload = producerRequestPayload;
    }

    public String getProducerResponsePayload() {
        return producerResponsePayload;
    }

    public void setProducerResponsePayload(String producerResponsePayload) {
        this.producerResponsePayload = producerResponsePayload;
    }

    public String getProducerResponseCode() {
        return producerResponseCode;
    }

    public void setProducerResponseCode(String producerResponseCode) {
        this.producerResponseCode = producerResponseCode;
    }

    public String getProducerResponseDescription() {
        return producerResponseDescription;
    }

    public void setProducerResponseDescription(String producerResponseDescription) {
        this.producerResponseDescription = producerResponseDescription;
    }

    public Date getProducerRequestTime() {
        return producerRequestTime;
    }

    public void setProducerRequestTime(Date producerRequestTime) {
        this.producerRequestTime = producerRequestTime;
    }

    public Date getProducerResponseTime() {
        return producerResponseTime;
    }

    public void setProducerResponseTime(Date producerResponseTime) {
        this.producerResponseTime = producerResponseTime;
    }

    @Override
    public String toString() {
        return "NibssTSARevenueCollectionLog{" + "id=" + id + ", requestTime=" + requestTime + ", responseTime=" + responseTime + ", requestPayload=" + requestPayload + ", responsePayload=" + responsePayload + ", responseCode=" + responseCode + ", responseDescription=" + responseDescription + ", producerRequestPayload=" + producerRequestPayload + ", producerResponsePayload=" + producerResponsePayload + ", producerResponseCode=" + producerResponseCode + ", producerResponseDescription=" + producerResponseDescription + ", producerRequestTime=" + producerRequestTime + ", producerResponseTime=" + producerResponseTime + '}';
    }

}
