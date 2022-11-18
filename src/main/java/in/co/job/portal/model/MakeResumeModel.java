package in.co.job.portal.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.job.portal.bean.MakeResumeBean;
import in.co.job.portal.bean.RoleBean;
import in.co.job.portal.exception.ApplicationException;
import in.co.job.portal.exception.DatabaseException;
import in.co.job.portal.exception.DuplicateRecordException;
import in.co.job.portal.util.JDBCDataSource;



/**
 * JDBC Implementation of Role Model
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 */
public class MakeResumeModel 
{
	private static Logger log = Logger.getLogger(MakeResumeModel.class);

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
            PreparedStatement pstmt = conn
                    .prepareStatement("SELECT MAX(ID) FROM P_RESUME");
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
    public long add(MakeResumeBean bean) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model add Started");
        Connection conn = null;
        int pk = 0;
       
        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPK();

            // Get auto-generated next primary key
            System.out.println(pk + " in ModelJDBC");
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT INTO P_RESUME VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, pk);
            pstmt.setString(2, bean.getName());
            pstmt.setString(3, bean.getEmail());
            pstmt.setString(4, bean.getMobile());
            pstmt.setString(5, bean.getgCourceName());
            pstmt.setString(6, bean.getgInsName());
            pstmt.setString(7, bean.getgPercentage());
            pstmt.setString(8, bean.gethCourceName());
            pstmt.setString(9, bean.gethInsName());
            pstmt.setString(10, bean.gethPercentage());
            pstmt.setString(11, bean.getsCourceName());
            pstmt.setString(12, bean.getsInsName());
            pstmt.setString(13, bean.getsPercentage());
            pstmt.setString(14, bean.getSkill());
            pstmt.setString(15, bean.getHobbies());
            pstmt.setString(16, bean.getpDetail());
            pstmt.setString(17, bean.getDeclaration());
            pstmt.setDate(18, new java.sql.Date(bean.getDate().getTime()));
            pstmt.setString(19, bean.getPlace());
            pstmt.setString(20,bean.getObjective());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException("Exception : Exception in add MakeResume");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model add End");
        return pk;
    }
    
    public MakeResumeBean findByPK(long pk) throws ApplicationException {
        log.debug("Model findByPK Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM P_Resume WHERE ID=?");
        MakeResumeBean bean = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new MakeResumeBean();
                bean.setId(rs.getLong(1));
                bean.setName(rs.getString(2));
                bean.setEmail(rs.getString(3));
                bean.setMobile(rs.getString(4));
                bean.setgCourceName(rs.getString(5));
                bean.setgInsName(rs.getString(6));
                bean.setgPercentage(rs.getString(7));
                bean.sethCourceName(rs.getString(8));
                bean.sethInsName(rs.getString(9));
                bean.sethPercentage(rs.getString(10));
                bean.setsCourceName(rs.getString(11));
                bean.setsInsName(rs.getString(12));
                bean.setsPercentage(rs.getString(13));
                bean.setSkill(rs.getString(14));
                bean.setHobbies(rs.getString(15));
                bean.setpDetail(rs.getString(16));
                bean.setDeclaration(rs.getString(17));
                bean.setDate(rs.getDate(18));
                bean.setPlace(rs.getString(19));
                bean.setDeclaration(rs.getString(20));
            }
            rs.close();
        } catch (Exception e) {
           log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model findByPK End");
        return bean;
    }

   
   
    
}
