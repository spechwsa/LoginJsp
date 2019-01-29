package com.wsa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Connectee
 */
@WebServlet( "/Connectee" )
public class Connectee extends HttpServlet {
    private static final long  serialVersionUID = 1L;
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE              = "/WEB-INF/pageConnectee.jsp";
    public static final String VUE_HOME         = "home.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connectee() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        if ( session.getAttribute( ATT_SESSION_USER ) != null ) {
            /* Affichage de la page connectée */
            request.getRequestDispatcher( VUE ).forward( request, response );
        } else {
            response.sendRedirect( VUE_HOME );
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet( request, response );
    }

}
