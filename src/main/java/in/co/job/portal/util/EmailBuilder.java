package in.co.job.portal.util;

import java.util.HashMap;

/**
 * Class that build Application Email messages
 *
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 *
 */

public class EmailBuilder {
	/**
	 * Returns Successful User Registration Message
	 *
	 * @param map
	 *            : Message parameters
	 * @return
	 */

	public static String getUserRegistrationMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();

		msg.append("<HTML><BODY>");
		msg.append("Registration is successful ");
		msg.append("<H1>Hi! Greetings from Job Portal!</H1>");
		msg.append(
				"<P>Congratulations for registering on Job Portal! You can now access your Job Portal account online - anywhere, anytime and  enjoy the Job  Details.</P>");
		
		msg.append("<P><B>Login Id : " + map.get("login") + "<BR>" + " Password : " + map.get("password") + "</B></p>");

		msg.append(
				"<P> As a security measure, we recommended that you change your password after you first log in.</p>");
		

		msg.append(
				"<p>We assure you the best service at all times and look forward to a warm and long-standing association with you.</p>");

		msg.append("</BODY></HTML>");

		return msg.toString();
	}

	/**
	 * Returns Email message of Forget Password
	 *
	 * @param map
	 *            : params
	 * @return
	 */

	public static String getForgetPasswordMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();

		msg.append("<HTML><BODY>");
		msg.append("<H1>Applay For Job Thats Info Is !! Company Name:" + map.get("company") + " Language: " + map.get("language") + "</H1>");
		msg.append("<P><B>Description : " + map.get("description")+"</B></p>");
		msg.append("</BODY></HTML>");

		return msg.toString();
	}
	
	public static String getSendMailMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();

		msg.append("<HTML><BODY>");
		msg.append("<H1>Hii Dear "+map.get("userName")+"</H1>");
		msg.append("<P><B> " +map.get("description")+ "</B></p>");
		msg.append("</BODY></HTML>");

		return msg.toString();
	}

	/**
	 * Returns Email message of Change Password
	 *
	 * @param map
	 * @return
	 */
	public static String getChangePasswordMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();

		msg.append("<HTML><BODY>");
		msg.append("<H1>Your Password has been changed Successfully !! " + map.get("firstName") + " "
				+ map.get("lastName") + "</H1>");

		msg.append("<P><B>To access account user Login Id : " + map.get("login") + "<BR>" + " Password : "
				+ map.get("password") + "</B></p>");
		msg.append("</BODY></HTML>");

		return msg.toString();
	}

	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", "Rohan123");
		map.put("password", "268651");
		System.out.println(EmailBuilder.getUserRegistrationMessage(map));
	}
}
