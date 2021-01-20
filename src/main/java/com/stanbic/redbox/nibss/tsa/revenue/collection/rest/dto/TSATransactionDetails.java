package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TSATransactionDetails {

	@JsonProperty("uniqueReference")
	private String uniqueReference;
	@JsonProperty("bankBranchid")
	private String bankBranchid;
	@JsonProperty("bankId")
	private String bankId;
	@JsonProperty("batchId")
	private String batchId;
	@JsonProperty("branchPhoneno")
	private String branchPhoneno;
	@JsonProperty("bvn")
	private String bvn;
	@JsonProperty("cbnAcct")
	private String cbnAcct;
	@JsonProperty("channel")
	private String channel;
	@JsonProperty("collectedAmount")
	private String collectedAmount;
	@JsonProperty("currency")
	private String currency;
	@JsonProperty("customerAccount")
	private String customerAccount;
	@JsonProperty("customerEmail")
	private String customerEmail;
	@JsonProperty("customerName")
	private String customerName;
	@JsonProperty("customerPhone")
	private String customerPhone;
	@JsonProperty("customerTin")
	private String customerTin;
	@JsonProperty("fee")	
	private String fee;
	@JsonProperty("feedType")
	private String feedType;
	@JsonProperty("gifmisCode")
	private String gifmisCode;
	@JsonProperty("itemCode")
	private String itemCode;
	@JsonProperty("itemName")
	private String itemName;
	@JsonProperty("locationCode")
	private String locationCode;
	@JsonProperty("locationName")
	private String locationName;
	@JsonProperty("mdaCode")
	private String mdaCode;
	@JsonProperty("mdaName")
	private String mdaName;
	@JsonProperty("narrationDesc")
	private String narrationDesc;
	@JsonProperty("payColDate")
	private String payColDate;
	@JsonProperty("psspCode")
	private String psspCode;
	@JsonProperty("psspName")
	private String psspName;
	@JsonProperty("remittedAmount")
	private String remittedAmount;
	@JsonProperty("requestedAmount")
	private String requestedAmount;
	@JsonProperty("sessionId")
	private String sessionId;
	@JsonProperty("settlementRef")
	private String settlementRef;
	@JsonProperty("tsaPcCodename")
	private String tsaPcCodename;
	@JsonProperty("tsaPcCoderef")
	private String tsaPcCoderef;
	@JsonProperty("valueDate")
	private String valueDate;
	@JsonProperty("whoPays")
	private String whoPays;
	
	
	
	 
	public String getUniqueReference() {
		return uniqueReference;
	}
	public void setUniqueReference(String uniqueReference) {
		this.uniqueReference = uniqueReference;
	}
	public String getBankBranchid() {
		return bankBranchid;
	}
	public void setBankBranchid(String bankBranchid) {
		this.bankBranchid = bankBranchid;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getBranchPhoneno() {
		return branchPhoneno;
	}
	public void setBranchPhoneno(String branchPhoneno) {
		this.branchPhoneno = branchPhoneno;
	}
	public String getBvn() {
		return bvn;
	}
	public void setBvn(String bvn) {
		this.bvn = bvn;
	}
	public String getCbnAcct() {
		return cbnAcct;
	}
	public void setCbnAcct(String cbnAcct) {
		this.cbnAcct = cbnAcct;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getCollectedAmount() {
		return collectedAmount;
	}
	public void setCollectedAmount(String collectedAmount) {
		this.collectedAmount = collectedAmount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerTin() {
		return customerTin;
	}
	public void setCustomerTin(String customerTin) {
		this.customerTin = customerTin;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getFeedType() {
		return feedType;
	}
	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}
	public String getGifmisCode() {
		return gifmisCode;
	}
	public void setGifmisCode(String gifmisCode) {
		this.gifmisCode = gifmisCode;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getMdaCode() {
		return mdaCode;
	}
	public void setMdaCode(String mdaCode) {
		this.mdaCode = mdaCode;
	}
	public String getMdaName() {
		return mdaName;
	}
	public void setMdaName(String mdaName) {
		this.mdaName = mdaName;
	}
	public String getNarrationDesc() {
		return narrationDesc;
	}
	public void setNarrationDesc(String narrationDesc) {
		this.narrationDesc = narrationDesc;
	}
	public String getPayColDate() {
		return payColDate;
	}
	public void setPayColDate(String payColDate) {
		this.payColDate = payColDate;
	}
	public String getPsspCode() {
		return psspCode;
	}
	public void setPsspCode(String psspCode) {
		this.psspCode = psspCode;
	}
	public String getPsspName() {
		return psspName;
	}
	public void setPsspName(String psspName) {
		this.psspName = psspName;
	}
	public String getRemittedAmount() {
		return remittedAmount;
	}
	public void setRemittedAmount(String remittedAmount) {
		this.remittedAmount = remittedAmount;
	}
	public String getRequestedAmount() {
		return requestedAmount;
	}
	public void setRequestedAmount(String requestedAmount) {
		this.requestedAmount = requestedAmount;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getSettlementRef() {
		return settlementRef;
	}
	public void setSettlementRef(String settlementRef) {
		this.settlementRef = settlementRef;
	}
	public String getTsaPcCodename() {
		return tsaPcCodename;
	}
	public void setTsaPcCodename(String tsaPcCodename) {
		this.tsaPcCodename = tsaPcCodename;
	}
	public String getTsaPcCoderef() {
		return tsaPcCoderef;
	}
	public void setTsaPcCoderef(String tsaPcCoderef) {
		this.tsaPcCoderef = tsaPcCoderef;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public String getWhoPays() {
		return whoPays;
	}
	public void setWhoPays(String whoPays) {
		this.whoPays = whoPays;
	}
	
	@Override
	public String toString() {
		return "TSATransactionDetails [uniqueReference=" + uniqueReference + ", bankBranchid=" + bankBranchid
				+ ", bankId=" + bankId + ", batchId=" + batchId + ", branchPhoneno=" + branchPhoneno + ", bvn=" + bvn
				+ ", cbnAcct=" + cbnAcct + ", channel=" + channel + ", collectedAmount=" + collectedAmount
				+ ", currency=" + currency + ", customerAccount=" + customerAccount + ", customerEmail=" + customerEmail
				+ ", customerName=" + customerName + ", customerPhone=" + customerPhone + ", customerTin=" + customerTin
				+ ", fee=" + fee + ", feedType=" + feedType + ", gifmisCode=" + gifmisCode + ", itemCode=" + itemCode
				+ ", itemName=" + itemName + ", locationCode=" + locationCode + ", locationName=" + locationName
				+ ", mdaCode=" + mdaCode + ", mdaName=" + mdaName + ", narrationDesc=" + narrationDesc + ", payColDate="
				+ payColDate + ", psspCode=" + psspCode + ", psspName=" + psspName + ", remittedAmount="
				+ remittedAmount + ", requestedAmount=" + requestedAmount + ", sessionId=" + sessionId
				+ ", settlementRef=" + settlementRef + ", tsaPcCodename=" + tsaPcCodename + ", tsaPcCoderef="
				+ tsaPcCoderef + ", valueDate=" + valueDate + ", whoPays=" + whoPays + "]";
	}
}