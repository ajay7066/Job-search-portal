package in.co.job.portal.bean;

import java.sql.Timestamp;
import java.util.Date;

public class JobBean extends BaseBean {

	private String companyName;
	private String language;
	private String Description;
	private Date postDate;
	private String postByRecId;
	private String address;
	
	private long recruiterId;
	private long jobId;
	private long userId;
	private Timestamp applayDate;
	private String userName;
	private String resumeFile;
	
	
	
	
	
	
	
	
	
	
	
	
	public String getResumeFile() {
		return resumeFile;
	}

	public void setResumeFile(String resumeFile) {
		this.resumeFile = resumeFile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(long recruiterId) {
		this.recruiterId = recruiterId;
	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	

	public String getCompanyName() {
		return companyName;
	}

	public Timestamp getApplayDate() {
		return applayDate;
	}

	public void setApplayDate(Timestamp applayDate) {
		this.applayDate = applayDate;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getPostByRecId() {
		return postByRecId;
	}

	public void setPostByRecId(String postByRecId) {
		this.postByRecId = postByRecId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
