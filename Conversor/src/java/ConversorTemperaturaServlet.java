/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kiko
 */
@WebServlet("/converter")
public class ConversorTemperaturaServlet extends HttpServlet {

   

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        String opcao = request.getParameter("tipoConversao");
        double temperatura = Double.parseDouble(request.getParameter("temperatura"));
        
        if(opcao.equals("cf")){
            double temperaturaConvertida = ((temperatura * 9) / 5) + 32;
                        out.println("<html>");
			out.println("<body>");
			out.println("<h1>A temperatura é " + temperaturaConvertida + "F");
			out.println("</body>");
			out.println("</html>");
        }
        
        if(opcao.equals("fc")){
            double temperaturaConvertida = ((temperatura - 32) * 5) / 9;
                        out.println("<html>");
			out.println("<body>");
			out.println("<h1>A temperatura é " + temperaturaConvertida + "C");
			out.println("</body>");
			out.println("</html>");
        }
    }
}
