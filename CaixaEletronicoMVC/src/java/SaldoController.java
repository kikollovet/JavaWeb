
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/saldoController"})
public class SaldoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(request.getSession().getAttribute("conta") == null){
            request.getSession().setAttribute("conta", new Conta());
        }
        int saldo = ((Conta)request.getSession().getAttribute("conta")).getSaldo();
        request.setAttribute("saldo", saldo);
        
        request.getRequestDispatcher("saldo.jsp").forward(request, response);
    }

}
