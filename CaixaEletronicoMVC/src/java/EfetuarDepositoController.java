
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/efetuarDepositoController")
public class EfetuarDepositoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int valor = Integer.parseInt(request.getParameter("valor"));
        
        if(request.getSession().getAttribute("conta") == null){
            request.getSession().setAttribute("conta", new Conta());
        }
        ((Conta)request.getSession().getAttribute("conta")).depositar(valor);
        
        request.getRequestDispatcher("central.jsp").forward(request, response);
    }
}
