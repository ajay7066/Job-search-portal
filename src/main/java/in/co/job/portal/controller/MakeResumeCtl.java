package in.co.job.portal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.itextpdf.text.DocumentException;

import in.co.job.portal.bean.BaseBean;
import in.co.job.portal.bean.JobBean;
import in.co.job.portal.bean.MakeResumeBean;
import in.co.job.portal.bean.UserBean;
import in.co.job.portal.exception.ApplicationException;
import in.co.job.portal.exception.DuplicateRecordException;
import in.co.job.portal.model.JobModel;
import in.co.job.portal.model.MakeResumeModel;
import in.co.job.portal.model.UserModel;
import in.co.job.portal.util.DataUtility;
import in.co.job.portal.util.DataValidator;
import in.co.job.portal.util.PropertyReader;
import in.co.job.portal.util.ResumeCreate;
import in.co.job.portal.util.ServletUtility;


/**
 * Servlet implementation class MakeResumeCtl
 */
@WebServlet(name="MakeResumeCtl",urlPatterns={"/ctl/MakeResumeCtl"})
public class MakeResumeCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(MakeResumeCtl.class);
	/**
	 * Loads list and other data required to display at HTML form
	 * 
	 * @param request
	 */
	
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("MakeResumeCtl Method validate Started");

		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email",
					PropertyReader.getValue("error.require", "Email Id"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("objective"))) {
			request.setAttribute("objective",
					PropertyReader.getValue("error.require", "Objective"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("gCourseName"))) {
			request.setAttribute("gCourseName",
					PropertyReader.getValue("error.require", "Graduation Cource Name"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("gInsName"))) {
			request.setAttribute("gInsName",
					PropertyReader.getValue("error.require", "Graduation Institude Name"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("gPer"))) {
			request.setAttribute("gPer",
					PropertyReader.getValue("error.require", "Graduation Percentage"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("hCourseName"))) {
			request.setAttribute("hCourseName",
					PropertyReader.getValue("error.require", "Higher Secoundary Cource Name"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("hInsName"))) {
			request.setAttribute("hInsName",
					PropertyReader.getValue("error.require", "Higher Secoundary Institude Name"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("hPer"))) {
			request.setAttribute("hPer",
					PropertyReader.getValue("error.require", "Higher Secoundary Percentage"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("sCourseName"))) {
			request.setAttribute("sCourseName",
					PropertyReader.getValue("error.require", "Secoundary Cource Name"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("sInsName"))) {
			request.setAttribute("sInsName",
					PropertyReader.getValue("error.require", "Secoundary Institude Name"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("sPer"))) {
			request.setAttribute("sPer",
					PropertyReader.getValue("error.require", "Secoundary Percentage"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("skill"))) {
			request.setAttribute("skill",
					PropertyReader.getValue("error.require", "Skill"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("hobbies"))) {
			request.setAttribute("hobbies",
					PropertyReader.getValue("error.require", "Hobbies"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("pDetail"))) {
			request.setAttribute("pDetail",
					PropertyReader.getValue("error.require", "Personal Detail"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("declaration"))) {
			request.setAttribute("declaration",
					PropertyReader.getValue("error.require", "Declaration"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("date"))) {
			request.setAttribute("date",
					PropertyReader.getValue("error.require", "Date"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("place"))) {
			request.setAttribute("place",
					PropertyReader.getValue("error.require", "Place"));
			pass = false;
		}
		
		log.debug("MakeResumeCtl Method validate Ended");

		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("MakeResumeCtl Method populatebean Started");

		MakeResumeBean bean = new MakeResumeBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setMobile(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setObjective(DataUtility.getString(request.getParameter("objective")));
		bean.setgCourceName(DataUtility.getString(request.getParameter("gCourseName")));
		bean.setgInsName(DataUtility.getString(request.getParameter("gInsName")));
		bean.setgPercentage(DataUtility.getString(request.getParameter("gPer")));
		bean.sethCourceName(DataUtility.getString(request.getParameter("hCourseName")));
		bean.sethInsName(DataUtility.getString(request.getParameter("hInsName")));
		bean.sethPercentage(DataUtility.getString(request.getParameter("hPer")));
		bean.setsCourceName(DataUtility.getString(request.getParameter("sCourseName")));
		bean.setsInsName(DataUtility.getString(request.getParameter("sInsName")));
		bean.setsPercentage(DataUtility.getString(request.getParameter("sPer")));
		bean.setSkill(DataUtility.getString(request.getParameter("skill")));
		bean.setHobbies(DataUtility.getString(request.getParameter("hobbies")));
		bean.setpDetail(DataUtility.getString(request.getParameter("pDetail")));
		bean.setDeclaration(DataUtility.getString(request.getParameter("declaration")));
		bean.setDate(DataUtility.getDate(request.getParameter("date")));
		bean.setPlace(DataUtility.getString(request.getParameter("place")));
		
		populateDTO(bean, request);
		log.debug("MakeResumeCtl Method populatebean Ended");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("MakeResumeCtl Method doGet Started");
		long jid=DataUtility.getLong(request.getParameter("jId"));
		request.getSession().setAttribute("jiId",jid);
        ServletUtility.forward(getView(), request, response);
        log.debug("MakeResumeCtl Method doGet Ended");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("MakeResumeCtl Method doPost Started");
        String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        MakeResumeModel model = new MakeResumeModel();
        long id = DataUtility.getLong(request.getParameter("id"));
        if (OP_SAVE.equalsIgnoreCase(op)) {
        	MakeResumeBean bean = (MakeResumeBean) populateBean(request);
            try {
                    long pk = model.add(bean);
                    
                    try {
						ResumeCreate.ResumeCreate(bean);
						
						Long jiid=(Long)request.getSession().getAttribute("jiId");
						JobBean jBean=new JobModel().findByPK(DataUtility.getLong(String.valueOf(jiid)));
						UserModel uModel=new UserModel();
	                	UserBean ubean= uModel.findByLogin(jBean.getPostByRecId());
	                	JobBean jbBean=new JobBean();
	                	jbBean.setRecruiterId(ubean.getId());
	                	jbBean.setJobId(jBean.getId());
	                	jbBean.setResumeFile(bean.getName()+".pdf");
	                	UserBean ubBean=(UserBean)request.getSession().getAttribute("user");
	                	jbBean.setUserId(ubBean.getId());
	                    new JobModel().jobadd(jbBean);
						
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    ServletUtility.setSuccessMessage("Data Successfully Save", request);
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("MakeResume id already exists", request);
            }
            ServletUtility.forward(getView(), request, response);
        } else if (OP_RESET.equalsIgnoreCase(op)) {
    		ServletUtility.redirect(JPView.MAKE_RESUME_CTL, request, response);
    		return;
    }		
        ServletUtility.forward(getView(), request, response);
        log.debug("MakeResumeCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		return JPView.MAKE_RESUME_VIEW;
	}

}
