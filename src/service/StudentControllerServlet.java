package service;

import java.io.IOException;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import base.Student;
import base.StudentDbUtil;

@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Define DataSource
	private StudentDbUtil studentDbUtil;

	@Resource(name = "jdbc/web_student_tracker")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		} catch (Exception e) {
			throw new ServletException();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// list the students ... in MVC

		try {
				// read the "command" parameter
			String theCommand = request.getParameter("command");
				// if the command is missing, the default to listing students
			if(theCommand == null)
			{
				theCommand = "LIST";
				
			}
			// route to the appropriate method
			switch(theCommand){
			case "LIST":
				listStudent(request, response);
				break;
			
			case "ADD":
				addStudent(request,response);
				break;
			case "LOAD":
				loadStudent(request,response);
			case "UPDATE":
				updateStudent(request,response);
				break;
			case "DELETE":
				deleteStudent(request,response);
				break;
			default:
				listStudent(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//read student id from form data
		String theStudentId = request.getParameter("studentID");
		// delete student from database
		studentDbUtil.deleteStudent(theStudentId);
		// send user back to "list students" page
		listStudent(request, response);
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read student info from form data
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstname = request.getParameter("firstName");
		String lastname = request.getParameter("lastName");
		String email = request.getParameter("email");
		// create a new student object
		Student theStudent = new Student(id, firstname, lastname, email);
		// perform update on database
		studentDbUtil.updateStudent(theStudent);
		// send them back to the "list students" page
		listStudent(request, response);
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// read student id from data
		String theStudentID = request.getParameter("studentID");
		// get student from database (db util)
		Student theStudent = studentDbUtil.getStudent(theStudentID);
		// place student in the request attribute
		request.setAttribute("THE_STUDENT", theStudent);
		// send to jsp page: update-student-form.jsp
		RequestDispatcher dispathcer = 
				request.getRequestDispatcher("/update-student-form.jsp");
		dispathcer.forward(request, response);
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			// read student info from form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
			
		
			// create a new student object
		Student theStudent = new Student(firstName,lastName,email);
			// add the student to the database
		studentDbUtil.addStudent(theStudent);
			// send back to main page (the student list)
		listStudent(request, response);
	}

	private void listStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// get students from db util
		List<Student> students = studentDbUtil.getStudents();
		// add students to the request
		request.setAttribute("STUDENT_LIST", students);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);

	}

}
