package OrganizerApp2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/edit")
public class EditEventServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        getServletContext().getRequestDispatcher("/editEvent.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String date = request.getParameter("date");
            String desc = request.getParameter("desc");
            int typeID = Integer.parseInt(request.getParameter("typeID"));
        try {
            DBHandler dbHandler = new DBHandler();
            dbHandler.updEvent(id,name,date,desc,typeID, " ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/index");

    }

}
