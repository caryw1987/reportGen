package com.report.gen.domain;

import org.springframework.stereotype.Component;

/**
 * Report entity. @author MyEclipse Persistence Tools
 */
@Component
public class Report implements java.io.Serializable {

	// Fields

	private String puid;
	private String requiredWD;
	private String dateIn;
	private String bvDueDate;
	private String dueDate;
	private String dateOut;
	private String client;
	private String poNo;
	private String billingTo;
	private Integer invoiceType;
	private String masterSampleNo;
	private String reportNo;
	private String specialItem;
	private String vendor;
	private String sampleDescription;
	private Float price;
	private String testGroup;
	private Integer status;
	private String holdingReason;
	private String lateReason;
	private User engineer;
	private User reportChecker;
	private User reportSender;
	private Integer logStatus;
	private String opLogoutTime;
	private User recorder;
	private String department;
	private Integer resultLoginStatus;
	private String resultLoginTime;
	private String resultLoginPendingTime;
	private String generateStartTime;
	private String generateEndTime;
	private Integer generateStatus;
	private Integer checkStatus;
	private String checkFailedTime;
	private Integer sendStatus;
	private String reportPath;
	private Integer moneyType;
	private String otherMoneyType;
	private Integer overAllStatus;
	private String reportRelativePath;

	// Constructors

	

	/** default constructor */
	public Report() {
	}

	/** full constructor */
	public Report(String requiredWD, String dateIn, String bvDueDate,
			String dueDate, String dateOut, String client, String poNo,
			String billingTo, Integer invoiceType, String masterSampleNo,
			String reportNo, String specialItem, String vendor,
			String sampleDescription, Float price, String testGroup,
			Integer status, String holdingReason, String lateReason ,User engineer,
			User reportChecker, User reportSender, Integer logStatus,
			String opLogoutTime, User recorder, String department, Integer resultLoginStatus,
			String resultLoginTime, String resultLoginPendingTime,
			String generateStartTime, String generateEndTime,
			Integer generateStatus, Integer checkStatus,
			String checkFailedTime, Integer sendStatus, String reportPath, Integer moneyType,  
			String otherMoneyType, Integer overAllStatus, String reportRelativePath) 
	{
		this.requiredWD = requiredWD;
		this.dateIn = dateIn;
		this.bvDueDate = bvDueDate;
		this.dueDate = dueDate;
		this.dateOut = dateOut;
		this.client = client;
		this.poNo = poNo;
		this.billingTo = billingTo;
		this.invoiceType = invoiceType;
		this.masterSampleNo = masterSampleNo;
		this.reportNo = reportNo;
		this.specialItem = specialItem;
		this.vendor = vendor;
		this.sampleDescription = sampleDescription;
		this.price = price;
		this.testGroup = testGroup;
		this.status = status;
		this.holdingReason = holdingReason;
		this.lateReason = lateReason;
		this.engineer = engineer;
		this.reportChecker = reportChecker;
		this.reportSender = reportSender;
		this.logStatus = logStatus;
		this.opLogoutTime = opLogoutTime;
		this.recorder = recorder;
		this.department = department;
		this.resultLoginStatus = resultLoginStatus;
		this.resultLoginTime = resultLoginTime;
		this.resultLoginPendingTime = resultLoginPendingTime;
		this.generateStartTime = generateStartTime;
		this.generateEndTime = generateEndTime;
		this.generateStatus = generateStatus;
		this.checkStatus = checkStatus;
		this.checkFailedTime = checkFailedTime;
		this.sendStatus = sendStatus;
		this.reportPath = reportPath;
		this.moneyType = moneyType;
		this.otherMoneyType = otherMoneyType;
		this.overAllStatus = overAllStatus;
		this.reportRelativePath = reportRelativePath;
	}

	// Property accessors

	public String getPuid() {
		return this.puid;
	}

	public void setPuid(String puid) {
		this.puid = puid;
	}

	public String getRequiredWD() {
		return this.requiredWD;
	}

	public void setRequiredWD(String requiredWD) {
		this.requiredWD = requiredWD;
	}

	public String getDateIn() {
		return this.dateIn;
	}

	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}

	public String getBvDueDate() {
		return this.bvDueDate;
	}

	public void setBvDueDate(String bvDueDate) {
		this.bvDueDate = bvDueDate;
	}

	public String getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getDateOut() {
		return this.dateOut;
	}

	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}

	public String getClient() {
		return this.client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getPoNo() {
		return this.poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public String getBillingTo() {
		return this.billingTo;
	}

	public void setBillingTo(String billingTo) {
		this.billingTo = billingTo;
	}

	public Integer getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getMasterSampleNo() {
		return this.masterSampleNo;
	}

	public void setMasterSampleNo(String masterSampleNo) {
		this.masterSampleNo = masterSampleNo;
	}

	public String getReportNo() {
		return this.reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	public String getSpecialItem() {
		return this.specialItem;
	}

	public void setSpecialItem(String specialItem) {
		this.specialItem = specialItem;
	}

	public String getVendor() {
		return this.vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getSampleDescription() {
		return this.sampleDescription;
	}

	public void setSampleDescription(String sampleDescription) {
		this.sampleDescription = sampleDescription;
	}

	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getTestGroup() {
		return this.testGroup;
	}

	public void setTestGroup(String testGroup) {
		this.testGroup = testGroup;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getHoldingReason() {
		return this.holdingReason;
	}

	public void setHoldingReason(String holdingReason) {
		this.holdingReason = holdingReason;
	}
	
	public String getLateReason() {
		return this.lateReason;
	}

	public void setLateReason(String lateReason) {
		this.lateReason = lateReason;
	}

	public User getEngineer() {
		return this.engineer;
	}

	public void setEngineer(User engineer) {
		this.engineer = engineer;
	}

	public User getReportChecker() {
		return this.reportChecker;
	}

	public void setReportChecker(User reportChecker) {
		this.reportChecker = reportChecker;
	}

	public User getReportSender() {
		return this.reportSender;
	}

	public void setReportSender(User reportSender) {
		this.reportSender = reportSender;
	}

	public Integer getLogStatus() {
		return this.logStatus;
	}

	public void setLogStatus(Integer logStatus) {
		this.logStatus = logStatus;
	}

	public String getOpLogoutTime() {
		return this.opLogoutTime;
	}

	public void setOpLogoutTime(String opLogoutTime) {
		this.opLogoutTime = opLogoutTime;
	}

	public User getRecorder() {
		return this.recorder;
	}

	public void setRecorder(User recorder) {
		this.recorder = recorder;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getResultLoginTime() {
		return this.resultLoginTime;
	}

	public void setResultLoginTime(String resultLoginTime) {
		this.resultLoginTime = resultLoginTime;
	}

	public String getResultLoginPendingTime() {
		return this.resultLoginPendingTime;
	}

	public void setResultLoginPendingTime(String resultLoginPendingTime) {
		this.resultLoginPendingTime = resultLoginPendingTime;
	}

	public String getGenerateStartTime() {
		return this.generateStartTime;
	}

	public void setGenerateStartTime(String generateStartTime) {
		this.generateStartTime = generateStartTime;
	}

	public String getGenerateEndTime() {
		return this.generateEndTime;
	}

	public void setGenerateEndTime(String generateEndTime) {
		this.generateEndTime = generateEndTime;
	}

	public Integer getGenerateStatus() {
		return this.generateStatus;
	}

	public void setGenerateStatus(Integer generateStatus) {
		this.generateStatus = generateStatus;
	}

	public Integer getCheckStatus() {
		return this.checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getCheckFailedTime() {
		return this.checkFailedTime;
	}

	public void setCheckFailedTime(String checkFailedTime) {
		this.checkFailedTime = checkFailedTime;
	}

	public String getReportPath() {
		return this.reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	
	public Integer getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}
	
	public Integer getResultLoginStatus() {
		return resultLoginStatus;
	}

	public void setResultLoginStatus(Integer resultLoginStatus) {
		this.resultLoginStatus = resultLoginStatus;
	}

	public Integer getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(Integer moneyType) {
		this.moneyType = moneyType;
	}

	public String getOtherMoneyType() {
		return otherMoneyType;
	}

	public void setOtherMoneyType(String otherMoneyType) {
		this.otherMoneyType = otherMoneyType;
	}

	public Integer getOverAllStatus() {
		return overAllStatus;
	}

	public void setOverAllStatus(Integer overAllStatus) {
		this.overAllStatus = overAllStatus;
	}

	public String getReportRelativePath() {
		return reportRelativePath;
	}

	public void setReportRelativePath(String reportRelativePath) {
		this.reportRelativePath = reportRelativePath;
	}
}