package com.wsa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wsa.bean.Utilisateur;
import com.wsa.dao.DAOFactory;
import com.wsa.dao.UtilisateurDao;
import com.wsa.forms.InscriptionForm;

/**
 * Servlet implementation class Inscription
 */
@WebServlet( "/Inscription" )
public class Inscription extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    private static final long  serialVersionUID = 1L;
    public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String VUE              = "/WEB-INF/inscription.jsp";

    private UtilisateurDao     utilisateurDao;

    public void init() throws ServletException {
        /* R�cup�ration d'une instance de notre DAO Utilisateur */
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        /* Affichage de la page d'inscription */
        request.getRequestDispatcher( VUE ).forward( request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        /* Pr�paration de l'objet formulaire */
        InscriptionForm form = new InscriptionForm( utilisateurDao );

        /*
         * Appel au traitement et � la validation de la requ�te, et r�cup�ration
         * du bean en r�sultant
         */
        Utilisateur utilisateur = form.inscrireUtilisateur( request );

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );

        request.getRequestDispatcher( VUE ).forward( request, response );
    }

}
