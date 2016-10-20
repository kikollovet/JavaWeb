
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calcular")
public class CalculadoraWebServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        double valor1 = Double.parseDouble(request.getParameter("v1"));
        double valor2 = Double.parseDouble(request.getParameter("v2"));
        String tipoOperacao = request.getParameter("tipoOperacao");
        
        if(tipoOperacao.equals("soma")){
            double resultado = valor1 + valor2;
                out.println("<html>");
		out.println("<body>");
		out.println("<h1>O resultado da soma é " + resultado);
		out.println("</body>");
		out.println("</html>");
        }
        
        if(tipoOperacao.equals("subtracao")){
            double resultado = valor1 - valor2;
                out.println("<html>");
		out.println("<body>");
		out.println("<h1>O resultado da subtração é " + resultado);
		out.println("</body>");
		out.println("</html>");
        }
        
        if(tipoOperacao.equals("multiplicacao")){
            double resultado = valor1 * valor2;
                out.println("<html>");
		out.println("<body>");
		out.println("<h1>O resultado da multiplicação é " + resultado);
		out.println("</body>");
		out.println("</html>");
        }
        
        if(tipoOperacao.equals("divisao")){
            double resultado = valor1 / valor2;
                out.println("<html>");
		out.println("<body>");
		out.println("<h1>O resultado da divisão é " + resultado);
		out.println("</body>");
		out.println("</html>");
        }
    }

}
