package com.report.gen.DAO;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.report.gen.domain.Report;
import com.report.gen.domain.User;
import com.report.gen.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Report entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.report.gen.domain.Report
 * @author MyEclipse Persistence Tools
 */
@Repository("reportDAO")
public class ReportDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ReportDAO.class);
	// property constants
	public static final String REQUIRED_WD = "requiredWD";
	public static final String DATE_IN = "dateIn";
	public static final String BV_DUE_DATE = "bvDueDate";
	public static final String DUE_DATE = "dueDate";
	public static final String DATE_OUT = "dateOut";
	public static final String CLIENT = "client";
	public static final String PO_NO = "poNo";
	public static final String BILLING_TO = "billingTo";
	public static final String INVOICE_TYPE = "invoiceType";
	public static final String MASTER_SAMPLE_NO = "masterSampleNo";
	public static final String REPORT_NO = "reportNo";
	public static final String SPECIAL_ITEM = "specialItem";
	public static final String VENDOR = "vendor";
	public static final String SAMPLE_DESCRIPTION = "sampleDescription";
	public static final String PRICE = "price";
	public static final String TEST_GROUP = "testGroup";
	public static final String STATUS = "status";
	public static final String HOLDING_REASON = "holdingReason";
	public static final String ENGINEER = "engineer";
	public static final String REPORT_CHECKER = "reportChecker";
	public static final String REPORT_SENDER = "reportSender";
	public static final String LOG_STATUS = "logStatus";
	public static final String OP_LOGOUT_TIME = "opLogoutTime";
	public static final String RECORDER = "recorder";
	public static final String DEPARTMENT = "department";
	public static final String RESULT_LOGIN_TIME = "resultLoginTime";
	public static final String RESULT_LOGIN_PENDING_TIME = "resultLoginPendingTime";
	public static final String GENERATE_START_TIME = "generateStartTime";
	public static final String GENERATE_END_TIME = "generateEndTime";
	public static final String GENERATE_STATUS = "generateStatus";
	public static final String CHECK_STATUS = "checkStatus";
	public static final String CHECK_FAILED_TIME = "checkFailedTime";
	public static final String REPORT_PATH = "reportPath";

	public void save(Report transientInstance) {
		log.debug("saving Report instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Report persistentInstance) {
		log.debug("deleting Report instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Report findById(java.lang.String id) {
		log.debug("getting Report instance with id: " + id);
		try {
			Report instance = (Report) getSession().get(
					"com.report.gen.domain.Report", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Report instance) {
		log.debug("finding Report instance by example");
		try {
			List results = getSession()
					.createCriteria("com.report.gen.domain.Report")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Report instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Report as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRequiredWD(Object requiredWD) {
		return findByProperty(REQUIRED_WD, requiredWD);
	}

	public List findByDateIn(Object dateIn) {
		return findByProperty(DATE_IN, dateIn);
	}

	public List findByBvDueDate(Object bvDueDate) {
		return findByProperty(BV_DUE_DATE, bvDueDate);
	}

	public List findByDueDate(Object dueDate) {
		return findByProperty(DUE_DATE, dueDate);
	}

	public List findByDateOut(Object dateOut) {
		return findByProperty(DATE_OUT, dateOut);
	}

	public List findByClient(Object client) {
		return findByProperty(CLIENT, client);
	}

	public List findByPoNo(Object poNo) {
		return findByProperty(PO_NO, poNo);
	}

	public List findByBillingTo(Object billingTo) {
		return findByProperty(BILLING_TO, billingTo);
	}

	public List findByInvoiceType(Object invoiceType) {
		return findByProperty(INVOICE_TYPE, invoiceType);
	}

	public List findByMasterSampleNo(Object masterSampleNo) {
		return findByProperty(MASTER_SAMPLE_NO, masterSampleNo);
	}

	public List findByReportNo(Object reportNo) {
		return findByProperty(REPORT_NO, reportNo);
	}

	public List findBySpecialItem(Object specialItem) {
		return findByProperty(SPECIAL_ITEM, specialItem);
	}

	public List findByVendor(Object vendor) {
		return findByProperty(VENDOR, vendor);
	}

	public List findBySampleDescription(Object sampleDescription) {
		return findByProperty(SAMPLE_DESCRIPTION, sampleDescription);
	}

	public List findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List findByTestGroup(Object testGroup) {
		return findByProperty(TEST_GROUP, testGroup);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByHoldingReason(Object holdingReason) {
		return findByProperty(HOLDING_REASON, holdingReason);
	}

	public List findByEngineer(Object engineer) {
		return findByProperty(ENGINEER, engineer);
	}

	public List findByReportChecker(Object reportChecker) {
		return findByProperty(REPORT_CHECKER, reportChecker);
	}

	public List findByReportSender(Object reportSender) {
		return findByProperty(REPORT_SENDER, reportSender);
	}

	public List findByLogStatus(Object logStatus) {
		return findByProperty(LOG_STATUS, logStatus);
	}

	public List findByOpLogoutTime(Object opLogoutTime) {
		return findByProperty(OP_LOGOUT_TIME, opLogoutTime);
	}

	public List findByRecorder(Object recorder) {
		return findByProperty(RECORDER, recorder);
	}

	public List findByDepartment(Object department) {
		return findByProperty(DEPARTMENT, department);
	}

	public List findByResultLoginTime(Object resultLoginTime) {
		return findByProperty(RESULT_LOGIN_TIME, resultLoginTime);
	}

	public List findByResultLoginPendingTime(Object resultLoginPendingTime) {
		return findByProperty(RESULT_LOGIN_PENDING_TIME, resultLoginPendingTime);
	}

	public List findByGenerateStartTime(Object generateStartTime) {
		return findByProperty(GENERATE_START_TIME, generateStartTime);
	}

	public List findByGenerateEndTime(Object generateEndTime) {
		return findByProperty(GENERATE_END_TIME, generateEndTime);
	}

	public List findByGenerateStatus(Object generateStatus) {
		return findByProperty(GENERATE_STATUS, generateStatus);
	}

	public List findByCheckStatus(Object checkStatus) {
		return findByProperty(CHECK_STATUS, checkStatus);
	}

	public List findByCheckFailedTime(Object checkFailedTime) {
		return findByProperty(CHECK_FAILED_TIME, checkFailedTime);
	}

	public List findByReportPath(Object reportPath) {
		return findByProperty(REPORT_PATH, reportPath);
	}

	public List findAll() {
		log.debug("finding all Report instances");
		try {
			String queryString = "from Report";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Report merge(Report detachedInstance) {
		log.debug("merging Report instance");
		try {
			Report result = (Report) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Report instance) {
		log.debug("attaching dirty Report instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Report instance) {
		log.debug("attaching clean Report instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public int getRowCount4Recorder(){
		
		try {
			Query queryObject = getSession().createQuery("select count(*) from Report r where r.overAllStatus = 1"); 
			return ((Long) queryObject.list().get(0)).intValue();
			
		} catch (RuntimeException e) {
			log.error("query for page failed", e);
			throw e;
		}	
	}
	
	public int getRowCount4EntryPerson(){
		
		try {
			Query queryObject = getSession().createQuery("select count(*) from Report r where r.overAllStatus = 2 or r.overAllStatus = 1"); 
			return ((Long) queryObject.list().get(0)).intValue();
			
		} catch (RuntimeException e) {
			log.error("query for page failed", e);
			throw e;
		}	
	}
	
	public int getRowCount4ResultLogin(){
		
		try {
			Query queryObject = getSession().createQuery("select count(*) from Report r where r.overAllStatus = 3 or r.overAllStatus = 2"); 
			return ((Long) queryObject.list().get(0)).intValue();
			
		} catch (RuntimeException e) {
			log.error("query for page failed", e);
			throw e;
		}	
	}
	
	public int getRowCount4GenWord(){
		
		try {
			Query queryObject = getSession().createQuery("select count(*) from Report r where r.overAllStatus = 4 or r.overAllStatus = 3"); 
			return ((Long) queryObject.list().get(0)).intValue();
			
		} catch (RuntimeException e) {
			log.error("query for page failed", e);
			throw e;
		}	
	}
	
	public int getRowCount4Check(){
		
		try {
			Query queryObject = getSession().createQuery("select count(*) from Report r where r.overAllStatus = 5 or r.overAllStatus = 4"); 
			return ((Long) queryObject.list().get(0)).intValue();
			
		} catch (RuntimeException e) {
			log.error("query for page failed", e);
			throw e;
		}	
	}
	
	public int getRowCount4Send(){
		
		try {
			Query queryObject = getSession().createQuery("select count(*) from Report r where r.overAllStatus = 6 or r.overAllStatus = 5"); 
			return ((Long) queryObject.list().get(0)).intValue();
			
		} catch (RuntimeException e) {
			log.error("query for page failed", e);
			throw e;
		}	
	}
	
	public int getRowCount4AddPrice(){
		
		try {
			Query queryObject = getSession().createQuery("select count(*) from Report r where r.overAllStatus = 7 or r.overAllStatus = 6"); 
			return ((Long) queryObject.list().get(0)).intValue();
			
		} catch (RuntimeException e) {
			log.error("query for page failed", e);
			throw e;
		}	
	}
	
	public int getRowCount4Statistics(){
		
		try {
			Query queryObject = getSession().createQuery("select count(*) from Report r where r.overAllStatus = 7"); 
			return ((Long) queryObject.list().get(0)).intValue();
			
		} catch (RuntimeException e) {
			log.error("query for page failed", e);
			throw e;
		}	
	}
	
    public int getAllRowCount(){
		
		try {
			Query queryObject = getSession().createQuery("select count(*) from Report"); 
			return ((Long) queryObject.list().get(0)).intValue();
			
		} catch (RuntimeException e) {
			log.error("query for page failed", e);
			throw e;
		}	
	}

	public int getRowCountByVendorAndCheckDate(String vendor, String checkDate){
		String sql = "select count(*) from Report r where 1=1 and r.checkStatus = 1 ";
		Query queryObject;
		try {
			if(vendor!= null && !vendor.equals(""))
			{
				sql += "and r.vendor like '%"+vendor+"%' ";
			}
			if(checkDate!=null && !checkDate.equals(""))
			{
				sql += "and r.dateOut = '"+checkDate+"'";
			}
			queryObject = getSession().createQuery(sql); 
			return ((Long) queryObject.list().get(0)).intValue();
			
		} catch (RuntimeException e) {
			log.error("query for page failed", e);
			throw e;
		}	
	}
	
	public int getRowCountByLoginAndCheckDate(User user, String fromCheckDateKeyWords, String endCheckDateKeyWords){
		String sql = "select count(*) from Report r where 1=1 and r.overAllStatus = 7 ";
		Query queryObject;
		try {
			if(user!= null)
			{
				sql += "and r.engineer = ? ";				
			}
			if(fromCheckDateKeyWords!=null && !fromCheckDateKeyWords.equals(""))
			{
				sql += "and r.dateOut >= '"+fromCheckDateKeyWords+"'";
			}
			if(endCheckDateKeyWords!=null && !endCheckDateKeyWords.equals(""))
			{
				sql += "and r.dateOut <= '"+endCheckDateKeyWords+"'";
			}
			queryObject = getSession().createQuery(sql);
			if(user!= null)
			{
				queryObject.setParameter(0, user);
				
			}
			return ((Long) queryObject.list().get(0)).intValue();
			
		} catch (RuntimeException e) {
			log.error("query for page failed", e);
			throw e;
		}	
	}
	
	public int getRowCountByMasterSampleNo(String masterSampleNo){
		String sql = "select count(*) from Report r where 1=1 and r.masterSampleNo = ? ";
		Query queryObject;
		try {
			queryObject = getSession().createQuery(sql); 
			queryObject.setParameter(0, masterSampleNo);
			return ((Long) queryObject.list().get(0)).intValue();
			
		} catch (RuntimeException e) {
			log.error("query for page failed", e);
			throw e;
		}	
	}
	
	public int getRowCountByCheckDate(String checkDate){
		String sql = "select count(*) from Report r where 1=1 and r.checkStatus = 1 ";
		Query queryObject;
		try 
		{
			if(checkDate!=null && !checkDate.equals(""))
			{
				sql += "and r.dateOut = '"+checkDate+"'";
			}
			queryObject = getSession().createQuery(sql); 
			return ((Long) queryObject.list().get(0)).intValue();
			
		} catch (RuntimeException e) {
			log.error("query for page failed", e);
			throw e;
		}	
	}
	
	public List<Report> getByMasterSampleNo(String masterSampleNo){
		String sql = "from Report r where r.overAllStatus < 3 and r.masterSampleNo = ? ";
		Query queryObject;
		try 
		{
			queryObject = getSession().createQuery(sql); 
			queryObject.setParameter(0, masterSampleNo);
			return queryObject.list();
			
		} catch (RuntimeException e) {
			log.error("query for page failed", e);
			throw e;
		}	
	}
	
	public List getReportByVendorAndCheckDate(String vendor, String checkDate, int offset, int pageSizeInt, int sortType){
		String sql = "from Report r where 1=1 and r.checkStatus = 1 ";
		Query queryObject;
		try {
			if(vendor!= null && !vendor.equals(""))
			{
				sql += "and r.vendor like '%"+vendor+"%' ";
			}
			if(checkDate!=null && !checkDate.equals(""))
			{
				sql += "and r.dateOut = '"+checkDate+"'";
			}
			
			if(sortType == 1)
			{
				sql += " order by r.dateOut desc";
			}
			else if(sortType == 2)
			{
				sql += " order by r.vendor asc";
			}
				
			queryObject = getSession().createQuery(sql);
		    queryObject.setFirstResult(offset);
		    queryObject.setMaxResults(pageSizeInt);
			 
			return queryObject.list();
			
		} catch (RuntimeException e) {
			log.error("query for page failed", e);
			throw e;
		}	
	}
	
	public List getReportByVendorAndCheckDate(User user, String fromCheckDateKeyWords, String endCheckDateKeyWords, int offset, int pageSizeInt){
		String sql = "from Report r where 1=1 and r.overAllStatus = 7 ";
		Query queryObject;
		try {
			if(user!= null)
			{
				sql += "and r.engineer = ? ";
			}
			if(fromCheckDateKeyWords!=null && !fromCheckDateKeyWords.equals(""))
			{
				sql += "and r.dateOut >= '"+fromCheckDateKeyWords+"'";
			}
			if(endCheckDateKeyWords!=null && !endCheckDateKeyWords.equals(""))
			{
				sql += "and r.dateOut <= '"+endCheckDateKeyWords+"'";
			}
			queryObject = getSession().createQuery(sql);
			if(user!= null)
			{
				queryObject.setParameter(0, user);
			}
		    queryObject.setFirstResult(offset);
		    queryObject.setMaxResults(pageSizeInt);
			 
			return queryObject.list();
			
		} catch (RuntimeException e) {
			log.error("query for page failed", e);
			throw e;
		}	
	}
	
	public List getReportByMasterSampleNo(String masterSampleNo, int offset, int pageSizeInt){
		String sql = "from Report r where 1=1 and r.masterSampleNo = ? ";
		Query queryObject;
		try {
			queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, masterSampleNo);
		    queryObject.setFirstResult(offset);
		    queryObject.setMaxResults(pageSizeInt);
			 
			return queryObject.list();
			
		} catch (RuntimeException e) {
			log.error("query for page failed", e);
			throw e;
		}	
	}
	
	public List getReportByCheckDate(String checkDate, int offset, int pageSizeInt){
		String sql = "from Report r where 1=1 and r.checkStatus = 1 ";
		Query queryObject;
		try {
			if(checkDate!=null && !checkDate.equals(""))
			{
				sql += "and r.dateOut = '"+checkDate+"'";
			}
			queryObject = getSession().createQuery(sql);
		    queryObject.setFirstResult(offset);
		    queryObject.setMaxResults(pageSizeInt);
			 
			return queryObject.list();
			
		} catch (RuntimeException e) {
			log.error("query for page failed", e);
			throw e;
		}	
	}
	
	public List findReportsByPage4Recorder(int offset, int pageLength) {
		log.debug("finding all Report instances");
		try {
			String queryString = "from Report r where r.overAllStatus = 1 order by r.opLogoutTime";
	         Query queryObject = getSession().createQuery(queryString);
	         queryObject.setFirstResult(offset);
	         queryObject.setMaxResults(pageLength);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findReportsByPage4EntryPerson(int offset, int pageLength) {
		log.debug("finding all Report instances");
		try {
			String queryString = "from Report r where r.overAllStatus = 2 or r.overAllStatus = 1 order by r.opLogoutTime";
	         Query queryObject = getSession().createQuery(queryString);
	         queryObject.setFirstResult(offset);
	         queryObject.setMaxResults(pageLength);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findReportsByPage4ResultLogin(int offset, int pageLength) {
		log.debug("finding all Report instances");
		try {
			String queryString = "from Report r where r.overAllStatus = 3 or r.overAllStatus = 2 order by r.opLogoutTime";
	         Query queryObject = getSession().createQuery(queryString);
	         queryObject.setFirstResult(offset);
	         queryObject.setMaxResults(pageLength);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findReportsByPage4GenWord(int offset, int pageLength) {
		log.debug("finding all Report instances");
		try {
			String queryString = "from Report r where r.overAllStatus = 4 or r.overAllStatus = 3 order by r.opLogoutTime";
	         Query queryObject = getSession().createQuery(queryString);
	         queryObject.setFirstResult(offset);
	         queryObject.setMaxResults(pageLength);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findReportsByPage4Check(int offset, int pageLength) {
		log.debug("finding all Report instances");
		try {
			String queryString = "from Report r where r.overAllStatus = 5 or r.overAllStatus = 4 order by r.opLogoutTime";
	         Query queryObject = getSession().createQuery(queryString);
	         queryObject.setFirstResult(offset);
	         queryObject.setMaxResults(pageLength);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findReportsByPage4Send(int offset, int pageLength) {
		log.debug("finding all Report instances");
		try {
			String queryString = "from Report r where r.overAllStatus = 6 or r.overAllStatus = 5 order by r.opLogoutTime";
	         Query queryObject = getSession().createQuery(queryString);
	         queryObject.setFirstResult(offset);
	         queryObject.setMaxResults(pageLength);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findReportsByPage4AddPrice(int offset, int pageLength) {
		log.debug("finding all Report instances");
		try {
			String queryString = "from Report r where r.overAllStatus = 7 or r.overAllStatus = 6 order by r.opLogoutTime";
	         Query queryObject = getSession().createQuery(queryString);
	         queryObject.setFirstResult(offset);
	         queryObject.setMaxResults(pageLength);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findReportsByPage4Statistics(int offset, int pageLength) {
		log.debug("finding all Report instances");
		try {
			String queryString = "from Report r where r.overAllStatus = 7 order by r.opLogoutTime";
	         Query queryObject = getSession().createQuery(queryString);
	         queryObject.setFirstResult(offset);
	         queryObject.setMaxResults(pageLength);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findReportsByPage(int offset, int pageLength) {
		log.debug("finding all Report instances");
		try {
			String queryString = "from Report r order by r.masterSampleNo";
	         Query queryObject = getSession().createQuery(queryString);
	         queryObject.setFirstResult(offset);
	         queryObject.setMaxResults(pageLength);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
}