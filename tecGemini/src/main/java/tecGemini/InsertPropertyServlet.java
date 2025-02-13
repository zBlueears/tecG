package tecGemini;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/InsertPropertyServlet")
public class InsertPropertyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String owner = request.getParameter("Owner");
        String address = request.getParameter("Address");
        String city = request.getParameter("City");
        String landmark = request.getParameter("Landmark");
        String pincode = request.getParameter("Pincode");

        Connection conn = null;
        PreparedStatement pst = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "");
            String sql = "INSERT INTO property (owner, address, city, landmark, pincode) VALUES (?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, owner);
            pst.setString(2, address);
            pst.setString(3, city);
            pst.setString(4, landmark);
            pst.setString(5, pincode);

            int rowsInserted = pst.executeUpdate();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            if (rowsInserted > 0) {
                out.println("<h3>Property details saved successfully!</h3>");
                out.println("<a href='view_properties.jsp'>View Properties</a>");
            } else {
                out.println("<h3>Error saving property details.</h3>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("GET method is working, but use POST to insert properties.");
    }

}
