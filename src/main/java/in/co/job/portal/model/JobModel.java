package in.co.job.portal.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.job.portal.bean.JobBean;
import in.co.job.portal.bean.RoleBean;
import in.co.job.portal.bean.SendMailBean;
import in.co.job.portal.bean.UserBean;
import in.co.job.portal.exception.ApplicationException;
import in.co.job.portal.exception.DatabaseException;
import in.co.job.portal.exception.DuplicateRecordException;
import in.co.job.portal.exception.RecordNotFoundException;
import in.co.job.portal.util.DataUtility;
import in.co.job.portal.util.EmailBuilder;
import in.co.job.portal.util.EmailMessage;
import in.co.job.portal.util.EmailUtility;
import in.co.job.portal.util.JDBCDataSource;
import in.co.job.portal.util.JobSendMail;

public class JobModel {

	private static Logger log = Logger.getLogger(JobModel.class);

	/**
	 * Find next PK of Role
	 * 
	 * @throws DatabaseException
	 */
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM P_Job");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	/**
	 * Add a Role
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * @throws ApplicationException
	 * 
	 * 
	 */
	public long add(JobBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO P_Job VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getCompanyName());
			pstmt.setString(3, bean.getLanguage());
			pstmt.setString(4, bean.getDescription());
			pstmt.setDate(5, new java.sql.Date(bean.getPostDate().getTime()));
			pstmt.setString(6, bean.getPostByRecId());
			pstmt.setString(7, bean.getAddress());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/**
	 * Delete a Role
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * @throws ApplicationException
	 */
	public void delete(JobBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM P_job WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/**
	 * Find User by Role
	 * 
	 * @param name
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 * @throws ApplicationException
	 */
	public JobBean findByName(String name) throws ApplicationException {
		log.debug("Model findBy EmailId Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM P_Job WHERE companyName=?");
		JobBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new JobBean();
				bean.setId(rs.getLong(1));
				bean.setCompanyName(rs.getString(2));
				bean.setLanguage(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setPostDate(rs.getDate(5));
				bean.setPostByRecId(rs.getString(6));
				bean.setAddress(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy EmailId End");
		return bean;
	}

	/**
	 * Find Role by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 * @throws ApplicationException
	 */
	public JobBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM P_job WHERE ID=?");
		JobBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new JobBean();
				bean.setId(rs.getLong(1));
				bean.setCompanyName(rs.getString(2));
				bean.setLanguage(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setPostDate(rs.getDate(5));
				bean.setPostByRecId(rs.getString(6));
				bean.setAddress(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	/**
	 * Update a Role
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * @throws ApplicationException
	 */
	public void update(JobBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE P_Job SET companyNAME=?,language=?,DESCRIPTION=?,postDate=?,postbyrecId=?,address=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getCompanyName());
			pstmt.setString(2, bean.getLanguage());
			pstmt.setString(3, bean.getDescription());
			pstmt.setDate(4, new java.sql.Date(bean.getPostDate().getTime()));
			pstmt.setString(5, bean.getPostByRecId());
			pstmt.setString(6, bean.getAddress());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Role ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Search Role
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws DatabaseException
	 * @throws ApplicationException
	 */
	public List search(JobBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search Role with pagination
	 * 
	 * @return list : List of Roles
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 * @throws ApplicationException
	 */
	public List search(JobBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM P_Job WHERE 1=1");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getCompanyName() != null && bean.getCompanyName().length() > 0) {
				sql.append(" AND companyNAME LIKE '" + bean.getCompanyName() + "%'");
			}
			if (bean.getLanguage() != null && bean.getLanguage().length() > 0) {
				sql.append(" AND language LIKE '" + bean.getLanguage() + "%'");
			}
			if (bean.getPostByRecId() != null && bean.getPostByRecId().length() > 0) {
				sql.append(" AND postbyrecId LIKE '" + bean.getPostByRecId() + "%'");
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" AND DESCRIPTION LIKE '" + bean.getDescription() + "%'");
			}
		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new JobBean();
				bean.setId(rs.getLong(1));
				bean.setCompanyName(rs.getString(2));
				bean.setLanguage(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setPostDate(rs.getDate(5));
				bean.setPostByRecId(rs.getString(6));
				bean.setAddress(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model search End");
		return list;
	}

	/**
	 * Get List of Role
	 * 
	 * @return list : List of Role
	 * @throws DatabaseException
	 * @throws ApplicationException
	 */
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of Role with pagination
	 * 
	 * @return list : List of Role
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 * @throws ApplicationException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from P_Job");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				JobBean bean = new JobBean();
				bean.setId(rs.getLong(1));
				bean.setCompanyName(rs.getString(2));
				bean.setLanguage(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setPostDate(rs.getDate(5));
				bean.setPostByRecId(rs.getString(6));
				bean.setAddress(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model list End");
		return list;

	}

	public boolean JobMailSend(JobBean bean, SendMailBean sBean)
			throws ApplicationException, RecordNotFoundException, ApplicationException {

		boolean flag;
		JobModel jModel=new JobModel();
		JobBean jBean=jModel.jobfindByPK(sBean.getId());
		
		UserModel uModel=new UserModel();
		UserBean uBean= uModel.findByPK(jBean.getUserId());
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("userName", jBean.getUserName());
		map.put("description", sBean.getDescription());

		String message = EmailBuilder.getSendMailMessage(map);
		EmailMessage msg = new EmailMessage();
		msg.setTo(uBean.getLogin());
		msg.setSubject("Job Portal Apply for Job");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);
		JobSendMail.sendMail(msg, sBean.getEmail().trim(), sBean.getPassword().trim());
		flag = true;

		return flag;
	}

	public Integer jnextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM P_Applay");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	public long jobadd(JobBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		
		UserModel umodel=new UserModel();
		UserBean uBean=umodel.findByPK(bean.getUserId());
		bean.setUserName(uBean.getFirstName()+" "+uBean.getLastName());
		
		JobBean existBean=jobfindByJobId(bean.getJobId(),bean.getUserId());
		if (existBean != null) {
			throw new DuplicateRecordException("Job Id already Applyed");
		}
		
		try {
			conn = JDBCDataSource.getConnection();
			pk = jnextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO P_applay VALUES(?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getRecruiterId());
			pstmt.setLong(3, bean.getJobId());
			pstmt.setLong(4, bean.getUserId());
			pstmt.setTimestamp(5, DataUtility.getCurrentTimestamp());
			pstmt.setString(6,bean.getUserName());
			pstmt.setString(7,bean.getResumeFile());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}
	
	
	public List jobSearch(JobBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search Role with pagination
	 * 
	 * @return list : List of Roles
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 * @throws ApplicationException
	 */
	public List jobSearch(JobBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM P_applay WHERE 1=1");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getUserId() > 0) {
				sql.append(" AND userid = " + bean.getUserId());
			}
			if (bean.getUserName() != null && bean.getUserName().length() > 0) {
				sql.append(" AND userName LIKE '" + bean.getUserName() + "%'");
			}
			if (bean.getRecruiterId() > 0) {
				sql.append(" AND RecruiterId = " + bean.getRecruiterId());
			}
			
		}
		 sql.append(" Order by ID Desc");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new JobBean();
				bean.setId(rs.getLong(1));
				bean.setRecruiterId(rs.getLong(2));
				bean.setJobId(rs.getLong(3));
				bean.setUserId(rs.getLong(4));
				bean.setApplayDate(rs.getTimestamp(5));
				bean.setUserName(rs.getString(6));
				bean.setResumeFile(rs.getString(7));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model search End");
		return list;
	}
	
	public JobBean jobfindByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM P_applay WHERE ID=?");
		JobBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new JobBean();
				bean.setId(rs.getLong(1));
				bean.setRecruiterId(rs.getLong(2));
				bean.setJobId(rs.getLong(3));
				bean.setUserId(rs.getLong(4));
				bean.setApplayDate(rs.getTimestamp(5));
				bean.setUserName(rs.getString(6));
				bean.setResumeFile(rs.getString(7));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}
	
	public JobBean jobfindByJobId(long jID,long uId) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM P_applay WHERE jobId=? and userID=?");
		JobBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, jID);
			pstmt.setLong(2,uId);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new JobBean();
				bean.setId(rs.getLong(1));
				bean.setRecruiterId(rs.getLong(2));
				bean.setJobId(rs.getLong(3));
				bean.setUserId(rs.getLong(4));
				bean.setApplayDate(rs.getTimestamp(5));
				bean.setUserName(rs.getString(6));
				bean.setResumeFile(rs.getString(7));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}
	
	public void updateApplayJob(JobBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE P_applay SET RecruiterId=?,JobId=?,UserId=?,applayDate=?,UserName=?,ResumeFile=? WHERE ID=?");
			pstmt.setLong(1, bean.getRecruiterId());
			pstmt.setLong(2, bean.getJobId());
			pstmt.setLong(3, bean.getUserId());
			pstmt.setTimestamp(4, DataUtility.getCurrentTimestamp());
			pstmt.setString(5,bean.getUserName());
			pstmt.setString(6,bean.getResumeFile());
			pstmt.setLong(7, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Role ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

}
