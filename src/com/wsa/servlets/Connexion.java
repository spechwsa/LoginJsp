package com.wsa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wsa.bean.Utilisateur;
import com.wsa.dao.DAOFactory;
import com.wsa.dao.UtilisateurDao;
import com.wsa.forms.ConnexionForm;

/**
 * Servlet implementation class Connexion
 */
@WebServlet( "/Connexion" )
public class Connexion extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    private static final long  serialVersionUID = 1L;
    public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE              = "/WEB-INF/connexion.jsp";
    public static final String PAGE_CONNECTEE   = "Connectee";

    private UtilisateurDao     utilisateurDao;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        /* Affichage de la page de connexion */
        request.getRequestDispatcher( VUE ).forward( request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        ConnexionForm form = new ConnexionForm( utilisateurDao );

        /* Traitement de la requête et récupération du bean en résultat */
        Utilisateur utilisateur = form.connecterUtilisateur( request );

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
        if ( form.getErreurs().isEmpty() ) {
            session.setAttribute( ATT_SESSION_USER, utilisateur );
            /* Stockage du formulaire et du bean dans l'objet request */
            request.setAttribute( ATT_FORM, form );
            // sendRedirect vers la servlet, l'URL change dans le navigateur.
            response.sendRedirect( PAGE_CONNECTEE );

        } else {
            session.setAttribute( ATT_SESSION_USER, null );
            /* Stockage du formulaire et du bean dans l'objet request */
            request.setAttribute( ATT_FORM, form ); // Pour l'affichage erreur
            request.setAttribute( ATT_USER, utilisateur ); // Permet de rappeler
                                                           // à l'utilisateur ce
                                                           // qu'il a déjà
                                                           // saisie
            request.getRequestDispatcher( VUE ).forward( request, response );
        }

    }

}
