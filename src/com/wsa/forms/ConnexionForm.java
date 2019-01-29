package com.wsa.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.wsa.bean.Utilisateur;
import com.wsa.dao.UtilisateurDao;

public final class ConnexionForm {
    private static final String CHAMP_EMAIL      = "email";
    private static final String CHAMP_PASS       = "motdepasse";
    private static final String ALGO_CHIFFREMENT = "SHA-256";

    private String              resultat;
    private Map<String, String> erreurs          = new HashMap<String, String>();

    private UtilisateurDao      utilisateurDao;

    public ConnexionForm( UtilisateurDao utilisateurDao ) {
        this.utilisateurDao = utilisateurDao;
    }

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Utilisateur connecterUtilisateur( HttpServletRequest request ) {
        /* R�cup�ration des champs du formulaire */
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );

        Utilisateur utilisateur = new Utilisateur();

        /* Validation du champ email. */
        try {
            validationEmail( email );
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        utilisateur.setEmail( email );

        /* Validation du champ mot de passe. */
        try {
            validationMotDePasse( motDePasse );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
        }
        utilisateur.setMotDePasse( motDePasse );

        // verification existence utilisateur dans la base avec le bon mot de
        // passe
        Utilisateur utilisateurEnBase;
        if ( erreurs.isEmpty() ) {
            try {
                utilisateurEnBase = utilisateurDao.trouver( email );
                if ( utilisateurEnBase == null ) {
                    setErreur( CHAMP_EMAIL, "Inscriver vous svp" );
                } else {
                    utilisateur.setNom( utilisateurEnBase.getNom() );
                    /*
                     * nous souhaiterons v�rifier si un utilisateur entre le bon
                     * mot de passe lors de sa connexion, il nous suffit de le
                     * comparer directement � l'empreinte stock�e gr�ce � la
                     * m�thode passwordEncryptor.checkPassword().
                     */
                    ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
                    passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
                    if ( !passwordEncryptor.checkPassword( motDePasse, utilisateurEnBase.getMotDePasse() ) ) {
                        setErreur( CHAMP_PASS, "Mauvaise combinaison Utilistateur et mot de passe" );
                    }
                }
            } catch ( Exception e ) {
                // TODO: handle exception
                setErreur( CHAMP_EMAIL, "Probleme acces base, essayez plus tard " );
            }
        }

        /* Initialisation du r�sultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            resultat = "Succ�s de la connexion.";
        } else {
            resultat = "�chec de la connexion.";
        }

        return utilisateur;
    }

    /**
     * Valide l'adresse email saisie.
     */
    private void validationEmail( String email ) throws Exception {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
    }

    /**
     * Valide le mot de passe saisi.
     */
    private void validationMotDePasse( String motDePasse ) throws Exception {
        if ( motDePasse != null ) {
            if ( motDePasse.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caract�res." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }

    /*
     * Ajoute un message correspondant au champ sp�cifi� � la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * M�thode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}