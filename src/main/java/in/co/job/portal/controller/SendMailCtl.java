package in.co.job.portal.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

import org.apache.log4j.Logger;

import in.co.job.portal.bean.BaseBean;
import in.co.job.portal.bean.JobBean;
import in.co.job.portal.bean.SendMailBean;
import in.co.job.portal.bean.UserBean;
import in.co.job.portal.exception.ApplicationException;
import in.co.job.portal.exception.DuplicateRecordException;
import in.co.job.portal.exception.RecordNotFoundException;
import in.co.job.portal.model.JobModel;
import in.co.job.portal.util.DataUtility;
import in.co.job.portal.util.DataValidator;
import in.co.job.portal.util.PropertyReader;
import in.co.job.portal.util.ServletUtility;

/**
 * Servlet implementation class SendMailCtl
 */
@WebServlet(name="SendMailCtl",urlPatterns={"/ctl/SendMailCtl"})
public class SendMailCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(JobCtl.class);
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

		log.debug("JobCtl Method validate Started");

		boolean pass = true;

		 

		

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description",
					PropertyReader.getValue("error.require", "Description"));
			pass = false;

		} 
		
		log.debug("JobCtl Method validate Ended");

		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("JobCtl Method populatebean Started");

		SendMailBean bean = new SendMailBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		

		populateDTO(bean, request);

		log.debug("JobCtl Method populatebean Ended");

		return bean;
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("JobCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        
		
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
          
            
			SendMailBean bean;
            
                bean =new SendMailBean();
                bean.setId(id);
             
                ServletUtility.setBean(bean, request);
            
            
        }

        ServletUtility.forward(getView(), request, response);
        log.debug("JobCtl Method doGet Ended");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("JobCtl Method doPost Started");
        String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        JobModel model = new JobModel();
       
        long id = DataUtility.getLong(request.getParameter("id"));
        if (OP_SENDMAIL.equalsIgnoreCase(op)) {
        	
            SendMailBean sBean=(SendMailBean)populateBean(request);
            
            HttpSession session=request.getSession();
			UserBean uBean=(UserBean)session.getAttribute("user");
			sBean.setEmail(uBean.getLogin());
			sBean.setPassword(uBean.getPassword());
            
            try {
            	System.out.println("Ctl Id====="+id);
            	JobBean dbean = model.findByPK(sBean.getId());
            	model.JobMailSend(dbean, sBean);
            	 ServletUtility.setBean(sBean, request);
               
            	ServletUtility.setSuccessMessage("Mail Has Bean Send", request);
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.setBean(sBean, request);
                ServletUtility.setErrorMessage("Message not send Plz Reset Your Google Id", request);
                ServletUtility.forward(JPView.SEND_MAIL_VIEW, request, response);
                return;
            } catch (RecordNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
            
            ServletUtility.forward(getView(), request, response);
       
    }
    				
    		
        ServletUtility.forward(getView(), request, response);
        

        log.debug("JobCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return JPView.SEND_MAIL_VIEW;
	}

}
