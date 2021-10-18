package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AccountService;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //create a session
        HttpSession mySession = request.getSession();
        
        //create a string for the logout message for later
        String responseMessage = "";
        
        //for detecting if "logout" exists in the url
        String action = request.getParameter("action");
        
        //starts on the login page because action="logout" is false
        if(action == null){
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        return;
        
        //detects when action="logout" after we click the logout button
        }
        else if(action.equals("logout")){
        //sets our logout message to what we want then sets it as an attribute
        responseMessage = "Successfully logged out!";
        request.setAttribute("responseMessage", responseMessage);
        //destroys our session and takes us back to the login page
        mySession.invalidate();
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        return;
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //create a session
        HttpSession mySession = request.getSession();
        
        //create a string for the logout message for later
        String responseMessage = "";
        
        //get the strings typed in by the user (getParameter takes the name="" attribute from jsp to get the value of a field)
        String username = request.getParameter("ubox");
        String password = request.getParameter("pbox");
        
        //create an AccountService class object so we can use the logIn() method
        AccountService accServ = new AccountService();
        
        if (accServ.logIn(username, password) != null){
            mySession.setAttribute("username", username);
            response.sendRedirect("home");
            return;
        }
        else{
            request.setAttribute("usernameSaver", username);
            request.setAttribute("passwordSaver", password);
            responseMessage = "Incorrect information! Please try again!";
            request.setAttribute("responseMessage", responseMessage);
            
        // this will have the servlet call upon a JSP to be loaded by the client's browser
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        // stop the code call
        return;
        }
 
    }

}
