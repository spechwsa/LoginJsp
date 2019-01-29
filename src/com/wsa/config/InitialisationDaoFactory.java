package com.wsa.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.wsa.dao.DAOFactory;

/*
 * l'interface ServletContextListener sert a recevoir les notification a propos des changement de context des servlets
 * contextInitialized() est appel�e d�s le d�marrage de l'application, avant le chargement des servlets et filtres du projet.
 * On vat cr�er une instance de DAOFactory dans cette m�thode et placer cette instance dans un attribut du ServletContext 
 * via sa m�thode setAttribute(), qui a donc pour port�e l'application enti�re
 * 
 * Pour que notre Listener soit pris en compte lors du d�marrage de l'application, il faut ajouter la section 
 * suivante au fichier web.xml :
 * <listener>
 *    <listener-class>com.ecole.config.InitialisationDaoFactory</listener-class>
 * </listener>	
 * 
 */

public class InitialisationDaoFactory implements ServletContextListener {
    /*
     * comme le fichier et dans le package com.wsa.dao il va se retrouver dans
     * le WAR dans WEB-INF\classes\com\wsa\dao
     */
    private static final String ATT_DAO_FACTORY = "daofactory";

    private DAOFactory          daoFactory;

    @Override
    public void contextInitialized( ServletContextEvent event ) {
        /* R�cup�ration du ServletContext lors du chargement de l'application */
        ServletContext servletContext = event.getServletContext();
        /* Instanciation de notre DAOFactory */
        this.daoFactory = DAOFactory.getInstance();
        /*
         * Enregistrement dans un attribut ayant pour port�e toute l'application
         */
        servletContext.setAttribute( ATT_DAO_FACTORY, this.daoFactory );
    }

    @Override
    public void contextDestroyed( ServletContextEvent arg0 ) {
        // TODO Auto-generated method stub
        /* Rien � r�aliser lors de la fermeture de l'application... */
    }

}
