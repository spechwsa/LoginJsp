package com.wsa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Deconnexion
 */
@WebServlet( "/Deconnexion" )
public class Deconnexion extends HttpServlet {
    private static final long  serialVersionUID = 1L;
    public static final String VUE              = "/Ecole/Connexion";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deconnexion() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        /* R�cup�ration et destruction de la session en cours */
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect( VUE );
    }

}
