package nchandi.spring.services.NCHANDIWebsite_Service.advice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import nchandi.spring.services.NCHANDIWebsite_Service.exceptions.MissingRolesException;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Aspect
@Component
public class SecurityAdvice {
	@Autowired
	HttpServletRequest request;
	
	@Value("${allowedgroups:null}")
	private String securitygroups;
	
	@Value("${security.accesscheck.aspect.enabled:true}")
	private String aspectenabled;
	
	@Before("within(nchandi.spring.services.trend.controllers.*)")
	public void doAccessCheck() throws MissingRolesException {
		if(aspectenabled.equals("true")) {
			List<String> userroles = Arrays.asList(StringUtils.commaDelimitedListToStringArray(securitygroups));
			
			if(userroles != null) {
				List<String> allowedroles = (List<String>)request.getSession().getAttribute("allowed_roles");
				
				if(allowedroles != null) {
				  if(Collections.disjoint(userroles, allowedroles))
					 throw new MissingRolesException("You do not have permission to access this resource");
				}else throw new MissingRolesException("Could not find roles for this user");
			}else throw new MissingRolesException("No security groups could be found for this application.");
		}
	}

}
