package OrganizerApp2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteEvent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id").toString());
            DBHandler dbHandler = new DBHandler();
            dbHandler.deleteEvent(id);
            response.sendRedirect(request.getContextPath() + "/index");
        }
        catch(Exception ex) {
            ex.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }

}
