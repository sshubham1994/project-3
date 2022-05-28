package in.co.rays.proj3.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.MarksheetDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.MarksheetModelInt;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.StudentModelInt;
import in.co.rays.proj3.util.DataUtility;
import in.co.rays.proj3.util.DataValidator;
import in.co.rays.proj3.util.PropertyReader;
import in.co.rays.proj3.util.ServletUtility;

/**
 * Marksheet functionality Controller. Performs operation for add, update,
 * delete and get Marksheet
 */
@WebServlet(name = "MarksheetCtl", urlPatterns = { "/ctl/MarksheetCtl" })
public class MarksheetCtl extends BaseCtl {
	private static Logger log = Logger.getLogger(MarksheetCtl.class);

	/**
	 * Loads list and other data required to display at HTML form
	 * 
	 * @param request
	 */
	protected void preload(HttpServletRequest request) {
		StudentModelInt model = ModelFactory.getInstance().getStudentModel();
		try {
			List l = model.list();
			request.setAttribute("studentList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	/**
	 * Validates input data entered by User
	 * 
	 * @param request
	 * @return
	 */
	protected boolean validate(HttpServletRequest request) {

		log.debug("MarksheetCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", PropertyReader.getValue("error.require", "Roll Number"));
			pass = false;
		} else if (!DataValidator.isNameNumber(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", PropertyReader.getValue("error.pass", "Roll No."));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("physics"))) {
			request.setAttribute("physics", PropertyReader.getValue("error.require", "Marks"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("physics"))) {
			request.setAttribute("physics", PropertyReader.getValue("error.integer", "Physics Marks"));
			pass = false;
		}

		else if ((DataUtility.getInt(request.getParameter("physics")) > 100)) {
			request.setAttribute("physics", PropertyReader.getValue("error.marks", "Physics Marks"));
			pass = false;
		}

		else if ((DataUtility.getInt(request.getParameter("physics")) < 0)) {
			request.setAttribute("physics", PropertyReader.getValue("error.mark", "Physics Marks"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry", PropertyReader.getValue("error.require", "Marks"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry", PropertyReader.getValue("error.integer", "Chemistry Marks"));
			pass = false;
		}

		else if (DataUtility.getInt(request.getParameter("chemistry")) > 100) {
			request.setAttribute("chemistry", PropertyReader.getValue("error.marks", "Chemistry Marks"));
			pass = false;
		} else if ((DataUtility.getInt(request.getParameter("chemistry")) < 0)) {
			request.setAttribute("chemistry", PropertyReader.getValue("error.mark", "Chemistry Marks"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("maths"))) {
			request.setAttribute("maths", PropertyReader.getValue("error.require", "Maths"));
			pass = false;
		}

		else if (DataUtility.getInt(request.getParameter("maths")) > 100) {
			request.setAttribute("maths", PropertyReader.getValue("error.marks", "Maths Marks"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("maths"))) {
			request.setAttribute("maths", PropertyReader.getValue("error.integer", "Maths Marks"));
			pass = false;
		} else if ((DataUtility.getInt(request.getParameter("maths")) < 0)) {
			request.setAttribute("maths", PropertyReader.getValue("error.mark", "Marks Marks"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("studentId"))) {
			request.setAttribute("studentId", PropertyReader.getValue("error.require", "Student Name"));
			pass = false;
		}

		log.debug("MarksheetCtl Method validate Ended");

		return pass;
	}

	/**
	 * Populates dto object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("MarksheetCtl Method populatedto Started");

		MarksheetDTO dto = new MarksheetDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));
System.out.println(dto.getId());
		dto.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		System.out.println(dto.getRollNo());
		/*
		 * dto.setName(DataUtility.getString(request.getParameter("name")));
		 * System.out.println(dto.getName());
		 */
		dto.setPhysics(DataUtility.getString(request.getParameter("physics")));
		System.out.println(dto.getPhysics());
		// System.out.println("marksheet ctl populate dto"+dto.getPhysics());

		dto.setChemistry(DataUtility.getString(request.getParameter("chemistry")));
		System.out.println(dto.getChemistry());
		// System.out.println("marksheet ctl populate dto"+dto.getChemistry());

		dto.setMaths(DataUtility.getString(request.getParameter("maths")));
		System.out.println(dto.getMaths());
		// System.out.println("marksheet ctl populate dto"+dto.getMaths());

		dto.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
		System.out.println(dto.getStudentId());
		populate(dto, request);

		System.out.println("Population done");

		log.debug("MarksheetCtl Method populatedto Ended");

		return dto;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("MarksheetCtl Method doGet Started");
		// System.out.println("marksheet ctl doget id");
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		MarksheetModelInt model = ModelFactory.getInstance().getMarksheetModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			// System.out.println("marksheet ctl doget id"+id);
			MarksheetDTO dto;
			try {
				dto = model.findByPk(id);
				ServletUtility.setDTO(dto, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("MarksheetCtl Method doGet Ended");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("MarksheetCtl Method doPost Started");
		
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		MarksheetModelInt model = ModelFactory.getInstance().getMarksheetModel();
		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			MarksheetDTO dto = (MarksheetDTO) populateDTO(request);
			try {
				if (id > 0) {
					model.update(dto);

					ServletUtility.setDTO(dto, request);
					ServletUtility.setSuccessMessage("Data is successfully updated", request);
					ServletUtility.forward(getView(), request, response);
					return;
				} else {
					System.out.println("in else part to save");
					long pk = model.add(dto);
					 dto.setId(0); 

					ServletUtility.setDTO(dto, request);
					ServletUtility.setSuccessMessage("Data is successfully saved", request);
					ServletUtility.forward(ORSView.MARKSHEET_VIEW, request, response);
					System.out.println("saved");
					
				}

			} catch (ApplicationException e) {
				log.error(e);
				e.printStackTrace();
				 ServletUtility.handleException(e, request, response); 
System.out.println("error occured");
				 return;
			} catch (DuplicateRecordException e) {
				System.out.println("found existed record so cant add marksheet");
				ServletUtility.setDTO(dto, request);
				ServletUtility.setErrorMessage("Roll No. is already exist", request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			MarksheetDTO dto = (MarksheetDTO) populateDTO(request);

			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.MARKSHEET_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("MarksheetCtl Method doPost Ended");
	}

	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	protected String getView() {
		return ORSView.MARKSHEET_VIEW;
	}
}
