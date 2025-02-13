<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Property Details</title>
</head>
<body>
    <h2>Property Listings</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Owner</th>
            <th>Address</th>
            <th>City</th>
            <th>Pincode</th>
            <th>Landmark</th>
        </tr>
        <%
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "");
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM property");

                while (rs.next()) {
        %>
                    <tr>
                        <td><%= rs.getInt("id") %></td>
                        <td><%= rs.getString("owner") %></td>
                        <td><%= rs.getString("address") %></td>
                        <td><%= rs.getString("city") %></td>
                        <td><%= rs.getString("pincode") %></td>
                        <td><%= rs.getString("landmark") %></td>
                    </tr>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            }
        %>
    </table>
     <br>
    <!-- Add a link to return to the form -->
    <a href="property_form.html">Go Back to Form</a>
</body>
</html>
