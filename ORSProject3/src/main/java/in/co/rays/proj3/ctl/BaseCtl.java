package in.co.rays.proj3.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.UserDTO;
import in.co.rays.proj3.util.DataUtility;
import in.co.rays.proj3.util.DataValidator;
import in.co.rays.proj3.util.ServletUtility;



/**
 * 
 * Base controller class of project. It contain (1) Generic operations (2)
 * Generic constants (3) Generic work flow
 * @author shubham sharma
 *
 */
public abstract class BaseCtl extends HttpServlet {
	
	public static Logger log = Logger.getLogger(BaseCtl.class);
	
	public static final String OP_SAVE = "Save";
    public static final String OP_CANCEL = "Cancel";
    public static final String OP_DELETE = "Delete";
    public static final String OP_LIST = "List";
    public static final String OP_SEARCH = "Search";
    public static final String OP_VIEW = "View";
    public static final String OP_NEXT = "Next";
    public static final String OP_PREVIOUS = "Previous";
    public static final String OP_NEW = "New";
    public static final String OP_GO = "Go";
    public static final String OP_BACK = "Back";
    public static final String OP_LOG_OUT = "Logout";
    public static final String OP_UPDATE = "Update";
    public static final String OP_RESET = "Reset";
    /**
     * Success message key constant
     */
    public static final String MSG_SUCCESS = "success";   
    /**
     * Error message key constant
     */  
    public static final String MSG_ERROR = "error";
    
    /**
     * Validates input data entered by User
     * 
     * @param request
     * @return
     */ 
    protected boolean validate(HttpServletRequest request) {	
        return true;
    }
    
    /**
     * Loads list and other data required to display at HTML form
     * 
     * @param request
     */   
    protected void preload(HttpServletRequest request) {
    	System.out.println("iN PreLoad()..");
    }
    
    /**
     * Populates dto object from request parameters
     * 
     * @param request
     * @return
     */
    protected BaseDTO populateDTO(HttpServletRequest request) {
        return null;
    }
    
    /**
     * Populates Generic attributes in DTO
     * 
     * @param dto
     * @param request
     * @return
     */
    protected BaseDTO populate(BaseDTO dto, HttpServletRequest request) {
		String createdBy = request.getParameter("createdBy");
		String modifiedBy = null;
		UserDTO userdto = (UserDTO) request.getSession().getAttribute("user");
		if (userdto == null) {
			// If record is created without login
			createdBy = "root";
			modifiedBy = "root";
		} else {
			modifiedBy = userdto.getLogin();
			// If record is created first time
			if ("null".equalsIgnoreCase(createdBy) || DataValidator.isNull(createdBy)) {
				createdBy = modifiedBy;
			}
		}

		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
		long cdt = DataUtility.getLong(request.getParameter("createdDatetime"));
		if (cdt > 0L) {
			dto.setCreatedDatetime(DataUtility.getTimestamp(cdt));
		} else {
			dto.setCreatedDatetime(DataUtility.getCurrentTimestamp());
		}

		dto.setModifiedDatetime(DataUtility.getCurrentTimestamp());
		return dto;
	}
    
    @Override
    protected void service(HttpServletRequest request,HttpServletResponse response) 
    		throws ServletException, IOException {

        // Load the preloaded data required to display at HTML form
        preload(request);

        String op = DataUtility.getString(request.getParameter("operation"));
        System.out.println("Operation in BaseCTL : "+op);

        // Check if operation is not DELETE, VIEW, CANCEL, Reset and NULL then perform input data validation

        if (DataValidator.isNotNull(op) && !OP_CANCEL.equalsIgnoreCase(op)
                && !OP_VIEW.equalsIgnoreCase(op)
                && !OP_DELETE.equalsIgnoreCase(op)
        		&& !OP_RESET.equalsIgnoreCase(op)){
            // Check validation, If fail then send back to page with error
            // messages

            if (!validate(request)) {
                BaseDTO dto = (BaseDTO) populateDTO(request);
                ServletUtility.setDTO(dto, request);
                ServletUtility.forward(getView(), request, response);
                return;
            }
        }
        System.out.println("base ctl service"+request.getMethod());
        System.out.println(request.getRequestedSessionId());
       
        super.service(request, response);
    }
    
    /**
     * Returns the VIEW page of this Controller
     * 
     * @return
     */
    protected abstract String getView();
    
}
