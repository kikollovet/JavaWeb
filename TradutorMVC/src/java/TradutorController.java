
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/tradutorController")
public class TradutorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String palavra = request.getParameter("palavra");
        
        Tradutor tradutor = new Tradutor();
        String palavraTraduzida = tradutor.traduzir(palavra);
        request.setAttribute("palavraTraduzida", palavraTraduzida);
        
        request.getRequestDispatcher("traducao.jsp").forward(request, response);
    }

}
