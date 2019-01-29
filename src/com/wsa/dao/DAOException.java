package com.wsa.dao;

//Afin de cacher la nature du mode de stockage des donn�es au reste de l'application
//li�es � l'interaction avec la base de donn�es
public class DAOException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /*
     * Constructeurs
     */
    public DAOException( String message ) {
        super( message );
    }

    public DAOException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DAOException( Throwable cause ) {
        super( cause );
    }
}
