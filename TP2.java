import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * I N F x 1 2 0
 * <p>
 * TP2 - Programme de Facturation de Expert-Plancher (v2)
 * <p>
 * Description du programme: Ce programme sert à facturer les clients de la compagnie d'Expert-Plancher. Elle a un menu
 * interractif qui (option 1) s'occupe de la sélection des produits, (option 2) de l'affichage de la facture et
 * (option 3) de l'historique de la compagnie. Finalement, l'option 4 sert à sortir du programme de facturation.
 *
 * @author Lilya Benladjreb
 * @version 2021-10-11
 * <p>
 * BENL28549807
 * benladjreb.lilya@courrier.uqam.ca
 */

public class TP2 {

    //Déclarer et initialiser les constantes
    static final int ID_VERNIS_EAU = 101;
    static final int ID_VERNIS_HUILE = 102;
    static final int ID_VERNIS_ALCOOL = 103;
    static final int ID_MAT = 1;
    static final int ID_SATINE = 2;
    static final int ID_SEMI_LUS = 3;
    static final int ID_LUS = 4;

    // Constantes de prix
    static final double PRIX_MARCHE = 30.00;
    static final double PRIX_CONMARCHE = 15.00;
    static final double TPS = 5.00;
    static final float TVQ = 9.975f;
    static final double PRIX_VEAU = 3.50;
    static final double PRIX_VHUILE = 3.75;
    static final double PRIX_VALCOOL = 4.00;

    //Constante de facture
    static final String NOM_ENTREPRISE = "Expert-Plancher";
    static final String ADR_ENTREPRISE = "2021 boulevard Java, Informatique, QC ";
    static final String TEL_ENTREPRISE = "(438) 182-1100";

    //Constantes Messages
    static final String CHOIX_MENU_UN = "Facturer le sablage et le vernissage de plancher et d'escaliers";
    static final String CHOIX_MENU_DEUX = "Afficher le montant total de toutes les factures";
    static final String CHOIX_MENU_TROIS = "Afficher le nombre de clients par type de vernis";
    static final String CHOIX_MENU_QUATRE = "Quitter le programme";
    static final String CHOIX_MENU_VER_EAU = "Le vernis à base d'eau";
    static final String CHOIX_MENU_VER_HUILE = "Le vernis à base d'huile";
    static final String CHOIX_MENU_VER_ALCOOL = "Le vernis à base d'alcool";
    static final String FINI_MAT = "Mat";
    static final String FINI_SAT = "Satiné";
    static final String FINI_SEMI_LUS = "Semi-lustré";
    static final String FINI_LUS = "Lustré";
    static final String MSG_ERR = "est invalide!";
    static final String MSG_FIN = "Merci beaucoup et passez une agréable journée!";
    static final char PETIT_O = 'o';
    static final char GRAND_O = 'O';
    static final char PETIT_N = 'n';
    static final char GRAND_N = 'N';

    //Variables Menu

    //Variable factures
    int numFacture = 0;


    int nbrTotalClientsEau = 0;
    int nbrTotalClientsHuile = 0;
    int nbrTotalClientsAlcool = 0;

    public static int calculerNbCaractere(String s) {

        return s.trim().length();
    }

    public static void afficherBienvenu() {
        // Afficher un message de bienvenu + résumé du programme
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Bienvenue dans le system de facturation d'" + NOM_ENTREPRISE);
        System.out.println("Ce programme permet de calculer la facture de sablage, de vernissage de plancher \nselon le prix par pied carré et le type de vernis choisi. Il affiche aussi \nl'historique en calculant les montants des factures crée par les ventes et le nombre \nde clients par type de vernis.");
        System.out.println("---------------------------------------------------------------------------------------");
    }

    public static int saisieMenuInitial() {
        // Afficher les choix de menu + saisie + validation

        int choixMenu;

        System.out.println("*** Menu de choix ***");
        System.out.println("1. " + CHOIX_MENU_UN);
        System.out.println("2. " + CHOIX_MENU_DEUX);
        System.out.println("3. " + CHOIX_MENU_TROIS);
        System.out.println("4. " + CHOIX_MENU_QUATRE + "\n");
        do {
            System.out.print("Entrez votre choix : ");
            choixMenu = Clavier.lireIntLn();
            if (choixMenu < 1 || choixMenu > 4) {
                System.out.println("L'option choisie " + MSG_ERR);
            }
        } while (choixMenu < 1 || choixMenu > 4);
        return choixMenu;
    }


    public static String saisieNomClient() {
        // Entrer le nom de famille du Client + validation
        String nomClient;
        int nbCar;

        do {
            System.out.print("Entrez le nom du client (entre 2 et 25 caractères inclusivement) : ");
            nomClient = Clavier.lireString();
            nbCar = calculerNbCaractere(nomClient);

            if (nbCar < 2 || nbCar > 25) {
                System.out.println("Le nom " + MSG_ERR);
            }
        } while (nbCar < 2 || nbCar > 25);
        return nomClient;
    }

    public static String saisiePrenomClient() {
        // Entrer le prénom du client + validation
        String prenomClient;
        int nbCar;
        do {
            System.out.print("Entrez le prénom du client (entre 2 et 25 caractères inclusivement): ");
            prenomClient = Clavier.lireString();
            nbCar = calculerNbCaractere(prenomClient);

            if (nbCar < 2 || nbCar > 25) {
                System.out.println("Le prénom " + MSG_ERR);
            }
        } while (nbCar < 2 || nbCar > 25);
        return prenomClient;
    }

    public static String saisieNumTelClient() {
        // Entrer un numéro de telephone, verifier si non-vide, verifier format NNN NNN-NNNN
        String telClient;
        int nbCar;

        do {
            System.out.print("Entrez le numéro de téléphone du client (format : NNN NNN-NNNN): ");
            telClient = Clavier.lireString();
            nbCar = calculerNbCaractere(telClient);

            if (!(telClient.matches("^[\\d]{3}\\s[\\d]{3}-[\\d]{4}$"))) {
                System.out.println("Le numéro de téléphone " + MSG_ERR);
            }

        } while (nbCar < 1 || !(telClient.matches("^[\\d]{3}\\s[\\d]{3}-[\\d]{4}$")));

        //Ajouter instructions pour que le num de téléphone affiche (514) 555-5555

        return telClient;
    }

    public static String saisieAdresseClient() {
        // Entrer l'adresse du client + validation
        String adrClient;
        int nbCar;

        do {
            System.out.print("Entrez l'adresse du client (entre 10 et 80 caractères inclusivement): ");
            adrClient = Clavier.lireString();
            nbCar = calculerNbCaractere(adrClient);

            if (nbCar < 10 || nbCar > 80) {
                System.out.println("L'adresse du client " + MSG_ERR);
            }
        } while (nbCar < 10 || nbCar > 80);
        return adrClient;
    }

    public static double saisieSurfacePlancher() {
        double surfacePlancher;

        // Entrer la surface du plancher + validation
        do {
            System.out.print("Entrez la surface à sabler et à vernir en pieds carré (supérieur à 0) : ");
            surfacePlancher = Clavier.lireDoubleLn();

            if (surfacePlancher <= 0) {
                System.out.println("La surface " + MSG_ERR);
            }
        } while (surfacePlancher <= 0);
        return surfacePlancher;
    }

    public static int saisieIdentifiantVernis() {
        int identifiantVernis;

        do {
            //Saisie et validation du vernis
            System.out.print("Entrez l'identifiant du vernis");
            System.out.print(" (" + ID_VERNIS_EAU + " = " + CHOIX_MENU_VER_EAU + "\n"
                    + "                                " + ID_VERNIS_HUILE + " = " + CHOIX_MENU_VER_HUILE + "\n"
                    + "                                " + ID_VERNIS_ALCOOL + " = " + CHOIX_MENU_VER_ALCOOL + "):  ");
            identifiantVernis = Clavier.lireIntLn();

            if (identifiantVernis < ID_VERNIS_EAU || identifiantVernis > ID_VERNIS_ALCOOL) {
                System.out.println("L’identifiant du type de vernis " + MSG_ERR);
            }

        } while (identifiantVernis < ID_VERNIS_EAU || identifiantVernis > ID_VERNIS_ALCOOL);

        return identifiantVernis;
    }

    public static int saisieTypeVernisFinition() {

        int typeVernisFinition;

        do {
            //Saisie du Finit
            System.out.print("Entrez le type de finition" + "(" + ID_MAT + " = " + FINI_MAT + ", "
                    + ID_SATINE + " = " + FINI_SAT + ", "
                    + ID_SEMI_LUS + " = " + FINI_SEMI_LUS + ", "
                    + ID_LUS + " = " + FINI_LUS + "): ");
            typeVernisFinition = Clavier.lireInt();

            if (typeVernisFinition < ID_MAT || typeVernisFinition > ID_LUS) {
                System.out.println("L’identifiant du type de finition " + MSG_ERR);
            }

        } while (typeVernisFinition < ID_MAT || typeVernisFinition > ID_LUS);

        return typeVernisFinition;
    }

    public static char saisieQuestionEscalier() {
        char reponseEscalier;

        do {
            //Saisie Escalier
            System.out.print("Avez-vous des escaliers à sabler et à vernir? (O ou o = Oui, N ou n = Non): ");
            reponseEscalier = Clavier.lireCharLn();

            if (reponseEscalier != PETIT_O && reponseEscalier != GRAND_O && reponseEscalier != PETIT_N && reponseEscalier != GRAND_N) {
                System.out.println("La réponse " + MSG_ERR);
            }
        } while (reponseEscalier != PETIT_O && reponseEscalier != GRAND_O && reponseEscalier != PETIT_N && reponseEscalier != GRAND_N);

        return reponseEscalier;
    }

    public static double saisieNombreMarche() {
        //Saisie Nombre de marche
        double nbrMarches;
        do {
            System.out.print("Entrez le nombre de marches (supérieur à 0): ");
            nbrMarches = Clavier.lireDoubleLn();
            if (nbrMarches <= 0) {
                System.out.println("Le nombre de marche " + MSG_ERR);
            }
        } while (nbrMarches <= 0);
        return nbrMarches;
    }

    public static double saisieNombreContremarche() {
        //Saisie nombre contremarche

        double nbrContreMarches;

        do {
            System.out.print("Entrez le nombre de contremarches (supérieur à 0): ");
            nbrContreMarches = Clavier.lireDoubleLn();
            if (nbrContreMarches <= 0) {
                System.out.println("Le nombre de contremarche " + MSG_ERR);
            }
        } while (nbrContreMarches <= 0);
        return nbrContreMarches;
    }

    public static char saisieModeDePaiement() {
        //Saisie mode de paiement
        char modePaiement;

        do {
            System.out.print("Entrez le mode de paiement (C ou c = Comptant, D ou d = Débit, et R et r = Crédit): ");
            modePaiement = Clavier.lireCharLn();
            if (modePaiement != 'C' && modePaiement != 'c' && modePaiement != 'D' && modePaiement != 'd' && modePaiement != 'R' && modePaiement != 'r') {
                //entrée incorrecte (message d'erreur)
                System.out.println("L'identifiant du mode de paiement " + MSG_ERR);
            }

        } while (modePaiement != 'C' && modePaiement != 'c' && modePaiement != 'D' && modePaiement != 'd' && modePaiement != 'R' && modePaiement != 'r');

        return modePaiement;
    }

    public static double determinerPrixChoixVernisClient(int identifiantVernis) {
        //Prix Surface à vernir
        double prixChoixVernisClient;

        if (identifiantVernis == ID_VERNIS_EAU) {
            prixChoixVernisClient = PRIX_VEAU;
        } else if (identifiantVernis == ID_VERNIS_HUILE) {
            prixChoixVernisClient = PRIX_VHUILE;
        } else {
            prixChoixVernisClient = PRIX_VALCOOL;
        }
        return prixChoixVernisClient;
    }

    public static double calculerPrixSurfaceClient(double prixChoixVernisClient, double surfacePlancher) {
        // Calculer le sous-total de sablage er de vernissage selon de prix par pied carre et la surface à sabler et vernir.
        double prixSurfaceClient;

        prixSurfaceClient = prixChoixVernisClient * surfacePlancher;
        return prixSurfaceClient;
    }

    public static double calculerPrixMarchesClient(double nbrMarches) {
        // Calculer le sous-total des marches d'escaliers à sabler et à vernir.
        double prixMarchesClient;

        prixMarchesClient = nbrMarches * PRIX_MARCHE;
        return prixMarchesClient;
    }

    public static double calculerPrixContremarchesClient(double nbrContreMarches) {
        // Calculer le sous-total des contremarches d'escalier à sabler et à vernir.
        double prixContreMarchesClient;

        prixContreMarchesClient = nbrContreMarches * PRIX_CONMARCHE;
        return prixContreMarchesClient;
    }

    public static double calculerSoustotalClient(double prixSurfaceClient, double prixMarchesClient, double prixContreMarchesClient) {
        //Calculer le sous-total du client
        double soustotalClient;

        soustotalClient = prixSurfaceClient + prixMarchesClient + prixContreMarchesClient;
        return soustotalClient;
    }

    public static double calculerTps(double soustotalClient) {
        //Calculer la TPS du client
        double montantTpsClient;

        montantTpsClient = soustotalClient * (TPS / 100);
        return montantTpsClient;
    }

    public static double calculerTvq(double soustotalClient) {
        double montantTvqClient;
        //Calculer la TVQ du client
        montantTvqClient = soustotalClient * (TVQ / 100);
        return montantTvqClient;
    }

    public static double calculerMontantTotalClient(double soustotalClient, double montantTpsClient, double montantTvqClient) {
        double montantTotalClient = 0;
        montantTotalClient = soustotalClient + montantTpsClient + montantTvqClient;
        return montantTotalClient;
    }

    public static double calculerMontantTotalFactures(double montantTotalClient, double montantTotalFactures) {
        //Faire la somme des deux montants
        montantTotalFactures += montantTotalClient;
        return montantTotalFactures;
    }

    public static int incrementerNombreFactures(int numFacture) {
        //Ajouter +1 à un numero de facture
        numFacture++;
        return numFacture;
    }

    public static String determinerTypeDeVernis(int identifiantVernis) {
        String choixMenuVernis;
        //recois ID du vernis et retourne le string approprié
        if (identifiantVernis == ID_VERNIS_EAU) {
            choixMenuVernis = CHOIX_MENU_VER_EAU;
        } else if (identifiantVernis == ID_VERNIS_HUILE) {
            choixMenuVernis = CHOIX_MENU_VER_HUILE;
        } else {
            choixMenuVernis = CHOIX_MENU_VER_ALCOOL;
        }
        return choixMenuVernis;
    }

    public static String determinerTypeDeFinition(int typeVernisFinition) {
        String choixFinitionClient;

        if (typeVernisFinition == ID_MAT) {
            choixFinitionClient = FINI_MAT;
        } else if (typeVernisFinition == ID_SATINE) {
            choixFinitionClient = FINI_SAT;
        } else if (typeVernisFinition == ID_LUS) {
            choixFinitionClient = FINI_LUS;
        } else {
            choixFinitionClient = FINI_SEMI_LUS;
        }

        return choixFinitionClient;
    }

    public static String determinerModeDePaiementClient(char modePaiement) {
        String choixModeDePaiementClient;

        if (modePaiement == 'C' || modePaiement == 'c') {
            //Comptant
            choixModeDePaiementClient = "Comptant";
        } else if (modePaiement == 'D' || modePaiement == 'd') {
            //Débit
            choixModeDePaiementClient = "Débit";
        } else {
            //Crédit
            choixModeDePaiementClient = "Crédit";
        }

        return choixModeDePaiementClient;
    }

    public static int incrementerNombreClientParVernis(int idVernisCible, int idVernisSaisie, int nombreClient) {

        if (idVernisCible == idVernisSaisie) {
            nombreClient++;
        }

        return nombreClient;
    }

    public static void afficherDate() {
        //Affichage Date et Heure
        Date dateHeureSysteme = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.print("Date et Heure: " + simpleDateFormat.format(dateHeureSysteme) + "\n");

    }

    public static void affichageFacture(int numFacture, String nomClient, String prenomClient, String telClient,
                                        String adrClient, double surfacePlancher, int idVernisSaisie, char reponseEscalier,
                                        int typeDeFinitionChoisi, char modeDePaiementChoisi, double prixChoixVernisClient,
                                        double prixSurfaceClient, double nombreMarcheSaisie, double sousTotalMarches,
                                        double nombreContremarcheSaisie, double sousTotalContremarche, double sousTotalDuClient,
                                        double montantTps, double montantTvq, double montantTotalFinalClient) {


        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(NOM_ENTREPRISE);
        System.out.print(ADR_ENTREPRISE + "     " + TEL_ENTREPRISE + "\n");
        System.out.print("Facture No: " + numFacture + "       ");
        afficherDate();
        System.out.println("---------------------------------------------------------------------------------------");


        //Afficher info du Client

        System.out.printf("Nom et prénom : %s %s       ", nomClient, prenomClient);
        System.out.println("Téléphone : " + telClient);
        System.out.println("Adresse du client : " + adrClient);

        //Afficher les choix du menu
        String choixMenuVernis = determinerTypeDeVernis(idVernisSaisie);
        String choixFinitionClient = determinerTypeDeFinition(typeDeFinitionChoisi);
        String choixModeDePaiementClient = determinerModeDePaiementClient(modeDePaiementChoisi);

        System.out.println("Type de vernis à appliquer : " + choixMenuVernis + choixFinitionClient);
        System.out.println("Mode de paiement : " + choixModeDePaiementClient + "\n");

        System.out.printf("Surface à sabler et à vernir     %.2f pied carré x %.2f$ = %.2f$ \n", surfacePlancher, prixChoixVernisClient, prixSurfaceClient);

        //Prix des marches et contre-marches

        if (reponseEscalier == 'o' || reponseEscalier == 'O') {
            System.out.printf("Nombre de marches                %.2f x %.2f$ = %.2f$ \n", nombreMarcheSaisie, PRIX_MARCHE, sousTotalMarches);
            System.out.printf("Nombre de contremarches          %.2f x %.2f$ = %.2f$ \n ", nombreContremarcheSaisie, PRIX_CONMARCHE, sousTotalContremarche);
        }
        System.out.printf("Sous-total                      %.2f$\n", sousTotalDuClient);
        System.out.printf("Montant TPS                      %.2f$\n", montantTps);
        System.out.printf("Montant TVQ                      %.2f$\n", montantTvq);
        System.out.printf("Montant total                    %.2f$\n", montantTotalFinalClient);

        // fin facture
        System.out.println("        -------------------------------------------------------------------           ");
        System.out.println("                 " + MSG_FIN);

    }

    public static void afficherMontantTotalToutesFacturesConfondue(double montantTotalFactures) {
        //Nom entreprise + date + montant total des clients
        System.out.println("\n-----------------------------------------------------------------------------------------------\n"
                + NOM_ENTREPRISE + "\n");
        afficherDate();
        System.out.println("-----------------------------------------------------------------------------------------------\n");

        //calculer montant total dépensé par les clients

        System.out.printf("Le montant total de toutes les factures %.2f$", montantTotalFactures);
        System.out.println("\n-----------------------------------------------------------------------------------------------\n");

    }

    public static void afficherCompteurClientParVernis(int nbrTotalClientsEau, int nbrTotalClientsHuile, int nbrTotalClientsAlcool) {
        System.out.println("\n-----------------------------------------------------------------------------------------------\n"
                + NOM_ENTREPRISE + "\n");
        afficherDate();
        System.out.println("\n-----------------------------------------------------------------------------------------------\n");

        //Afficher le nombre de clients par type de vernis

        System.out.println("Type de vernis " + "    Nombre de clients");
        System.out.println("****************************************\n");
        System.out.println("Le vernis à base d'eau      " + nbrTotalClientsEau);
        System.out.println("Le vernis à base d'huile    " + nbrTotalClientsHuile);
        System.out.println("Le vernis à l'alcool        " + nbrTotalClientsAlcool);
        System.out.println("\n-----------------------------------------------------------------------------------------------\n");

    }


    public static void main(String[] params) {


        //Variables Menu
        int choixMenu;
        char reponseEscalier;
        int identifiantVernis;
        int typeVernisSaisie;
        double surfacePlancher;
        double nbrMarches;
        double nbrContreMarches;
        char modePaiement;

        //Variables client
        String nomClient;
        String prenomClient;
        String telClient;
        String adrClient;
        double prixChoixVernisClient;
        double prixSurfaceClient;
        double prixMarchesClient;
        double prixContreMarchesClient;
        double soustotalClient;
        double montantTpsClient;
        double montantTvqClient;


        //Variable factures
        int numFacture = 0;
        double montantTotalClient = 0;
        double montantTotalFactures = 0;
        int nbrTotalClientsEau = 0;
        int nbrTotalClientsHuile = 0;
        int nbrTotalClientsAlcool = 0;

        afficherBienvenu();


        // Afficher le menu
        do {

            choixMenu = saisieMenuInitial();

            //option 1

            if (choixMenu == 1) {

                nomClient = saisieNomClient();
                prenomClient = saisiePrenomClient();
                telClient = saisieNumTelClient();
                adrClient = saisieAdresseClient();
                numFacture = incrementerNombreFactures(numFacture);
                surfacePlancher = saisieSurfacePlancher();
                identifiantVernis = saisieIdentifiantVernis();
                typeVernisSaisie = saisieTypeVernisFinition();
                reponseEscalier = saisieQuestionEscalier();
                nbrMarches = saisieNombreMarche();
                nbrContreMarches = saisieNombreContremarche();
                prixMarchesClient = calculerPrixMarchesClient(nbrMarches);
                prixContreMarchesClient = calculerPrixContremarchesClient(nbrContreMarches);
                modePaiement = saisieModeDePaiement();
                prixChoixVernisClient = determinerPrixChoixVernisClient(identifiantVernis);
                prixSurfaceClient = calculerPrixSurfaceClient(prixChoixVernisClient, surfacePlancher);
                soustotalClient = calculerSoustotalClient(prixSurfaceClient, prixMarchesClient, prixContreMarchesClient);
                montantTpsClient = calculerTps(soustotalClient);
                montantTvqClient = calculerTvq(soustotalClient);
                montantTotalClient = calculerMontantTotalClient(soustotalClient, montantTpsClient, montantTvqClient);
                montantTotalFactures = calculerMontantTotalFactures(montantTotalClient, montantTotalFactures);
                nbrTotalClientsAlcool = incrementerNombreClientParVernis(ID_VERNIS_ALCOOL, typeVernisSaisie, nbrTotalClientsAlcool);
                nbrTotalClientsHuile = incrementerNombreClientParVernis(ID_VERNIS_HUILE, typeVernisSaisie, nbrTotalClientsHuile);
                nbrTotalClientsEau = incrementerNombreClientParVernis(ID_VERNIS_EAU, typeVernisSaisie, nbrTotalClientsEau);

                affichageFacture(numFacture, nomClient, prenomClient, telClient, adrClient, surfacePlancher,
                        identifiantVernis, reponseEscalier, typeVernisSaisie, modePaiement, prixChoixVernisClient,
                        prixSurfaceClient, nbrMarches, prixMarchesClient, nbrContreMarches, prixContreMarchesClient,
                        soustotalClient, montantTpsClient, montantTvqClient, montantTotalClient);


            } else if (choixMenu == 2) {

                afficherMontantTotalToutesFacturesConfondue(montantTotalFactures);

            } else if (choixMenu == 3) {

                afficherCompteurClientParVernis(nbrTotalClientsEau, nbrTotalClientsHuile, nbrTotalClientsAlcool);

            } else if (choixMenu < 1 || choixMenu > 4) {
                System.out.println(MSG_ERR);

            }
        } while (choixMenu != 4);

        System.out.println(MSG_FIN);
        System.exit(0);

    } // main

}// TP1
