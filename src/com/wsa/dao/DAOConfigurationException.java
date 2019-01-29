package com.wsa.dao;

//Afin de cacher la nature du mode de stockage des donn�es au reste de l'application
//li�es � la configuration du DAO et du driver JDBC
public class DAOConfigurationException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /*
     * Constructeurs
     */
    public DAOConfigurationException( String message ) {
        super( message );
    }

    public DAOConfigurationException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DAOConfigurationException( Throwable cause ) {
        super( cause );
    }
}
