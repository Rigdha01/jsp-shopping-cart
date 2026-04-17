import jakarta.servlet.*;//define the project package
import jakarta.servlet.http.*;//needed for servlet functionality
import java.io.IOException;//needed for servlet functionality
import java.util.*;//import list and the array lists

public class CartServlet extends HttpServlet {//It handles http req (GET and POST)

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)//It is used for when a user use/submit a form
            throws ServletException, IOException {

        String item = request.getParameter("item");//reads the items name from the input

        HttpSession session = request.getSession();//this session stores the data per user

      
        List<String> cart = (List<String>) session.getAttribute("cart");//helps you to retrive the cart from the session and helps to type casting also(required). the object is changed to the string 

        if (cart == null) {//if first time user then the cart does not exists
            cart = new ArrayList<>();
        }

       
        if (item != null && !item.trim().isEmpty()) {//help you to prevent empty or null items
            cart.add(item);
        }

     
        session.setAttribute("cart", cart);//help you to update the session data


        response.sendRedirect("cart");//redirects to the \cart. It also triggers the doGet method
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)//used when the user visits 
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<String> cart = (List<String>) session.getAttribute("cart");

        request.setAttribute("cartItems", cart);//it passes the cart data to the jsp page

        RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");//send the req to the cart.jsp and jsp will display the cart items
        rd.forward(request, response);
    }
}