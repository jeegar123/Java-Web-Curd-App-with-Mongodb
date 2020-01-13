/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.DataConnection;
import POJOs.Book;
import com.mongodb.DBObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author Vinod
 */
@WebServlet(name = "BookServlet", urlPatterns = {"/book"})
public class BookServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
DataConnection connection = new DataConnection();
            HashMap<Integer, Book> allBooks = connection.getAllBooks();
            Book book = new Book("","");
            String id = ""  ;
                 
            out.println("<html>");
            out.println("\n"
                    + "<head>\n"
                    + "    <link rel=\"stylesheet\" href=\"https://bootswatch.com/4/cosmo/bootstrap.min.css\">\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/mycss.css\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "  <script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\"></script> "
                    + "    <title>Books</title>\n"
                    + "</head>\n"
                    + "");

            out.println("<body>\n"
                    + "\n"
                    + "    <nav class=\"navbar navbar-expand-lg navbar-dark bg-primary\">\n"
                    + "        <!-- <a class=\"navbar-brand\" href=\"#\">Home</a>\n"
                    + "            <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarColor01\" aria-controls=\"navbarColor01\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n"
                    + "              <span class=\"navbar-toggler-icon\"></span>\n"
                    + "            </button>\n"
                    + "           -->\n"
                    + "        <div class=\"collapse navbar-collapse\" id=\"navbarColor01\">\n"
                    + "            <ul class=\"navbar-nav mr-auto\">\n"
                    //                    + "                <li class=\"nav-item\">\n"
                    //                    + "                    <a class=\"nav-link\" href=\"home.html\">Home</a>\n"
                    //                    + "                </li>\n"
                    //                    + "                <li class=\"nav-item active\">\n"
                    //                    + "                    <a class=\"nav-link\" href=\"#\">Books<span class=\"sr-only\">(current)</span></a>\n"
                    //                    + "                </li>\n"
                    //                    + "               \n"
                    //                    + "                <li class=\"nav-item\">\n"
                    //                    + "                    <a class=\"nav-link\" href=\"account.html\">Your Account </a>\n"
                    //                    + "                </li>\n"
                    + "            </ul>\n"
                    + "            <!-- <form class=\"form-inline my-2 my-lg-0\">\n"
                    + "                <input class=\"form-control mr-sm-2\" type=\"text\" placeholder=\"Search\">\n"
                    + "                <button class=\"btn btn-secondary my-2 my-sm-0\" type=\"submit\">Search</button>\n"
                    + "              </f0orm> -->\n"
                    + "            <a href='logout' class=\"btn btn-primary\">logout</a>\n"
                    + "        </div>\n"
                    + "    </nav>\n"
                    + "\n");
                  
                                            
                    if(request.getParameter("id")!=null){
                        book.setBookName(request.getParameter("bookName"));
                        book.setBookAuthor(request.getParameter("bookAuthor"));
                        id=request.getParameter("id");
                        
                    }
                    out.print("    <div align=\"center\" id=\"div_books\">\n"
                    + "        <form action='add'>\n"
                            +"<input type=\"hidden\" name=\"checkFlag\" value='"+id+"' >"
                    + "            <input type=\"text\" placeholder=\"enter book name\" class=\"book_input\" name='bookName' value='"+book.getBookName()+"'>\n"
                    + "            <input type=\"text\" placeholder=\"enter book author\" class=\"book_input\" name='bookAuthor' value='"+book.getBookAuthor()+"'>\n"
                    + "            <button class=\"book_button\">Add Book</button>\n"
                    + "        </form>\n"
                    + "    </div>\n"
                    + "\n");
                       
                    out.print("    <div>\n"
                    + "        <table class=\"table table-hover\">\n"
                    + "            <thead>\n"
                    + "                <tr>\n"
                    + "                    <th scope=\"col\">Sr no</th>\n"
                    + "                    <th scope=\"col\">Book Name</th>\n"
                    + "                    <th scope=\"col\">Book Author</th>\n"
                    + "                    <th scope=\"col\">Update</th>\n"
                    + "                    <th scope=\"col\">Remove</th>\n"
                    + "                </tr>\n"
                    + "            </thead>\n"
                    + "            <tbody>\n");
                    
                    if(allBooks!=null){
                    int i=1;
            for (Map.Entry<Integer, Book> enrty : allBooks.entrySet()) {

                out.print("                <tr>\n"
                        + "                    <th scope=\"row\">" + i++ + "</th>\n"
                        + "                    <td>" + enrty.getValue().getBookName() + "</td>\n"
                        + "                    <td>" + enrty.getValue().getBookAuthor() + "</td>\n"
                        + "                    <td><a href='edit?id="+enrty.getKey()+"&bookName="+enrty.getValue().getBookName()+"&bookAuthor="+enrty.getValue().getBookAuthor()+"'class=\"btn btn-primary btn-sm\">Edit</a></td>\n"
                        + "                    <td><a href='delete?id="+enrty.getKey()+"' class=\"btn btn-danger btn-sm\">Delete</a></td>\n"
                        + "                  </tr>\n");
            }
                    } 
            out.print("            </tbody>\n"
                    + "        </table>\n"
                    + "    </div>\n"
                    + "\n"
                    + "    </div>\n"
                    + "\n"
                    + "\n");
            out.print(
                    "</body>\n"
                    + "\n"
                    + "");

            out.println("</html>");
      
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void myFunction(HttpServletRequest request, HttpServletResponse response) {
      
        RequestDispatcher dispatcher=request.getRequestDispatcher("/TestServelt");
        
        try {
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
