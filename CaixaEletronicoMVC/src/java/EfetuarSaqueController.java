
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/efetuarSaqueController"})
public class EfetuarSaqueController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int valor = Integer.parseInt(request.getParameter("valor"));
        
        ((Conta)request.getSession().getAttribute("conta")).sacar(valor);
        
        response.sendRedirect("central.jsp");
    }

}
