package com.wsa.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.wsa.dao.DAOFactory;

/*
 * l'interface ServletContextListener sert a recevoir les notification a propos des changement de context des servlets
 * contextInitialized() est appelée dès le démarrage de l'application, avant le chargement des servlets et filtres du projet.
 * On vat créer une instance de DAOFactory dans cette méthode et placer cette instance dans un attribut du ServletContext 
 * via sa méthode setAttribute(), qui a donc pour portée l'application entière
 * 
 * Pour que notre Listener soit pris en compte lors du démarrage de l'application, il faut ajouter la section 
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
        /* Récupération du ServletContext lors du chargement de l'application */
        ServletContext servletContext = event.getServletContext();
        /* Instanciation de notre DAOFactory */
        this.daoFactory = DAOFactory.getInstance();
        /*
         * Enregistrement dans un attribut ayant pour portée toute l'application
         */
        servletContext.setAttribute( ATT_DAO_FACTORY, this.daoFactory );
    }

    @Override
    public void contextDestroyed( ServletContextEvent arg0 ) {
        // TODO Auto-generated method stub
        /* Rien à réaliser lors de la fermeture de l'application... */
    }

}
