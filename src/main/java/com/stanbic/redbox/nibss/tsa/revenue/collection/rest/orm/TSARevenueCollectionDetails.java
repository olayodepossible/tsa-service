package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RBX_T_TSA_TRANS_DETAILS")
public class TSARevenueCollectionDetails {

	
	@Id
	@Column(name = "ID")
	private Long id;
	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;
	@Column(name = "ACCOUNT_NAME")
	private String accountName;
	@Column(name = "MDA_CODE")
	private String mdaCode;
	@Column(name = "SORT_CODE")
	private String sortCode;
	@Column(name = "BRANCH_CODE")
	private String branchCode;
	@Column(name = "BANK_CODE")
	private String bankCode;
	@Column(name = "COLLECTION_CODE")
	private String collectionCode; 
	@Column(name = "COLLECTION_NAME")
	private String collectionName; 
	@Column(name = "MDA_NAME")
	private String mdaName;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMdaName() {
		return mdaName;
	}

	public void setMdaName(String mdaName) {
		this.mdaName = mdaName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getMdaCode() {
		return mdaCode;
	}

	public void setMdaCode(String mdaCode) {
		this.mdaCode = mdaCode;
	}

	public String getSortCode() {
		return sortCode;
	}

	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getCollectionCode() {
		return collectionCode;
	}

	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}
   
	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	@Override
	public String toString() {
		return "TSARevenueCollectionDetails [id=" + id + ", accountNumber=" + accountNumber + ", accountName="
				+ accountName + ", mdaCode=" + mdaCode + ", sortCode=" + sortCode + ", branchCode=" + branchCode
				+ ", bankCode=" + bankCode + ", collectionCode=" + collectionCode + ", collectionName=" + collectionName
				+ ", mdaName=" + mdaName + "]";
	}

}
