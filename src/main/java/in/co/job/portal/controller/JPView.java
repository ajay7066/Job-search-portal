package in.co.job.portal.controller;

public interface JPView {
	
	public String APP_CONTEXT = "/JobPortal";

	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";

	
	
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";	
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	
	public String Apply_VIEW = PAGE_FOLDER + "/ApplayJobView.jsp";
	public String Apply_LIST_VIEW = PAGE_FOLDER + "/ApplayJobListView.jsp";
	
	public String JOB_VIEW = PAGE_FOLDER + "/JobView.jsp";	
	public String JOB_LIST_VIEW = PAGE_FOLDER + "/JobListView.jsp";
	
	public String RECRUITER_LIST_VIEW = PAGE_FOLDER + "/RecruiterListView.jsp";
	
	public String SEND_MAIL_VIEW = PAGE_FOLDER + "/SendMailView.jsp";	
		
	public String MAKE_RESUME_VIEW = PAGE_FOLDER + "/MakeResumeView.jsp";
	
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/WelcomeView.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	
	

	public String ERROR_CTL = "/ctl/ErrorCtl";

	
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	public String RECRUITER_LIST_CTL = APP_CONTEXT + "/ctl/RecruiterListCtl";
	
	public String JOB_CTL = APP_CONTEXT + "/ctl/JobCtl";
	public String JOB_LIST_CTL = APP_CONTEXT + "/ctl/JobListCtl";
	
	public String Apply_CTL = APP_CONTEXT + "/ctl/ApplayJobCtl";
	public String APPLY_LIST_CTL = APP_CONTEXT + "/ctl/ApplayJobListCtl";
	public String SEND_MAIL_CTL = APP_CONTEXT + "/ctl/SendMailCtl";
	
	
	public String MAKE_RESUME_CTL = APP_CONTEXT + "/ctl/MakeResumeCtl";
	
	
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";



}
