
/**
 * @file InsertSchantz.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertSchantz")
public class InsertSchantz extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public InsertSchantz() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String title1 = request.getParameter("title");
      String album = request.getParameter("album");
      String artist = request.getParameter("artist");
      String year = request.getParameter("year");
      String guestArtist = request.getParameter("guestArtist");

      Connection connection = null;
      String insertSql = " INSERT INTO playlist (id,title, album, artist, year, guestArtist) values (default, ?, ?, ?, ?, ?)";

      try {
         DBConnectionSchantz.getDBConnection();
         connection = DBConnectionSchantz.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, title1);
         preparedStmt.setString(2, album);
         preparedStmt.setString(3, artist);
         preparedStmt.setString(4, year);
         preparedStmt.setString(5, guestArtist);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Data to DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>Song Title</b>: " + title1 + "\n" + //
            "  <li><b>Album name</b>: " + album + "\n" + //
            "  <li><b>Artist</b>: " + artist + "\n" + //
            "  <li><b>Release year</b>: " + year + "\n" + //
            "  <li><b>Guest artist</b>: " + guestArtist + "\n" + //
            "</ul>\n");

      out.println("<a href=simpleFormSearch.html>Search by title!</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
