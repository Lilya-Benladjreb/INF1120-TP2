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
    final String ADR_ENTREPRISE = "2021 boulevard Java, Informatique, QC ";
    final String TEL_ENTREPRISE = "(438)182-1100";

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
    final String MSG_FIN = "Merci beaucoup et passez une agréable journée!";
    static final char PETIT_O = 'o';
    static final char GRAND_O = 'O';
    static final char PETIT_N = 'n';
    static final char GRAND_N = 'N';

    //Variables Menu
    static int choixMenu;
    static char modePaiement;
    static char reponseEscalier;
    static int identifiantVernis;
    static int typeVernisFinition;
    static double surfacePlancher;
    static double nbrMarches;
    static double nbrContreMarches;
    static int nbCar;
    static String inputTrimmed;

    //Variables client
    static String nomClient;
    static String prenomClient;
    static String telClient;
    static String adrClient;
    static String modePaieClient;
    static double prixChoixVernisClient;
    static double prixSurfaceClient;
    static double prixMarchesClient;
    static double prixContreMarchesClient;
    static double soustotalClient;
    static double montantTpsClient;
    static double montantTvqClient;
    int choixEau = 0;
    int choixHuile = 0;
    int choixAlcool = 0;

    //Variable factures
    int numFacture = 0;
    static double montantTotalClient = 0;
    static double montantTotalFactures = 0;
    int nbrTotalClientsEau = 0;
    int nbrTotalClientsHuile = 0;
    int nbrTotalClientsAlcool = 0;


    public static void afficherBienvenu() {
        // Afficher un message de bienvenu + résumé du programme
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Bienvenue dans le system de facturation d'" + NOM_ENTREPRISE);
        System.out.println("Ce programme permet de calculer la facture de sablage, de vernissage de plancher \nselon le prix par pied carré et le type de vernis choisi. Il affiche aussi \nl'historique en calculant les montants des factures crée par les ventes et le nombre \nde clients par type de vernis.");
        System.out.println("---------------------------------------------------------------------------------------");
    }

    public static int saisieMenuInitial() {
        // Afficher les choix de menu + saisie + validation
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

        do {
            System.out.print("Entrez le nom du client (entre 2 et 25 caractères inclusivement) : ");
            nomClient = Clavier.lireString();
            inputTrimmed = nomClient.trim();
            nbCar = inputTrimmed.length();

            if (nbCar < 2 || nbCar > 25) {
                System.out.println("Le nom " + MSG_ERR);
            }
        } while (nbCar < 2 || nbCar > 25);
        return nomClient;
    }

    public static String saisiePrenomClient() {
        // Entrer le prénom du client + validation
        do {
            System.out.print("Entrez le prénom du client (entre 2 et 25 caractères inclusivement): ");
            prenomClient = Clavier.lireString();
            inputTrimmed = prenomClient.trim();
            nbCar = inputTrimmed.length();

            if (nbCar < 2 || nbCar > 25) {
                System.out.println("Le prénom " + MSG_ERR);
            }
        } while (nbCar < 2 || nbCar > 25);
        return prenomClient;
    }

    public static String saisieNumTelClient() {
        // Entrer un numéro de telephone, verifier si non-vide, verifier format NNN NNN-NNNN
        do {
            System.out.print("Entrez le numéro de téléphone du client (format : NNN NNN-NNNN): ");
            telClient = Clavier.lireString();
            inputTrimmed = telClient.trim();
            nbCar = inputTrimmed.length();

            if (!(telClient.matches("^[\\d]{3}\\s[\\d]{3}-[\\d]{4}$"))) {
                System.out.println("Le numéro de téléphone " + MSG_ERR);
            }

        } while (nbCar < 1 || !(telClient.matches("^[\\d]{3}\\s[\\d]{3}-[\\d]{4}$")));
        return telClient;
    }

    public static String saisieAdresseClient() {
        // Entrer l'adresse du client + validation
        do {
            System.out.print("Entrez l'adresse du client (entre 10 et 80 caractères inclusivement): ");
            adrClient = Clavier.lireString();
            inputTrimmed = adrClient.trim();
            nbCar = inputTrimmed.length();

            if (nbCar < 10 || nbCar > 80) {
                System.out.println("L'adresse du client " + MSG_ERR);
            }
        } while (nbCar < 10 || nbCar > 80);
        return adrClient;
    }

    public static double saisieSurfacePlancher() {
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
        if (reponseEscalier == PETIT_O || reponseEscalier == GRAND_O) {
            do {
                System.out.print("Entrez le nombre de marches (supérieur à 0): ");
                nbrMarches = Clavier.lireDoubleLn();
                if (nbrMarches <= 0) {
                    System.out.println("Le nombre de marche " + MSG_ERR);
                }
            } while (nbrMarches <= 0);
        }
        return nbrMarches;
    }

    public static double saisieNombreContremarche() {
        //Saisie nombre contremarche
        if (nbrMarches > 0) {
            do {
                System.out.print("Entrez le nombre de contremarches (supérieur à 0): ");
                nbrContreMarches = Clavier.lireDoubleLn();
                if (nbrContreMarches <= 0) {
                    System.out.println("Le nombre de contremarche " + MSG_ERR);
                }
            } while (nbrContreMarches <= 0);
        }
        return nbrContreMarches;
    }

    public static char saisieModeDePaiement() {
        //Saisie mode de paiement
        do {
            System.out.print("Entrez le mode de paiement (C ou c = Comptant, D ou d = Débit, et R et r = Crédit): ");
            modePaiement = Clavier.lireCharLn();
            if (modePaiement == 'C' || modePaiement == 'c') {
                //Comptant
                modePaieClient = "Comptant";
            } else if (modePaiement == 'D' || modePaiement == 'd') {
                //Débit
                modePaieClient = "Débit";
            } else if (modePaiement == 'R' || modePaiement == 'r') {
                //Crédit
                modePaieClient = "Crédit";
            } else {
                //entrée incorrecte (message d'erreur)
                System.out.println("L'identifiant du mode de paiement " + MSG_ERR);
            }

        } while (modePaiement != 'C' && modePaiement != 'c' && modePaiement != 'D' && modePaiement != 'd' && modePaiement != 'R' && modePaiement != 'r');

        return modePaiement;
    }

    public static double determinerPrixChoixVernisClient(int identifiantVernis) {
        //Prix Surface à vernir
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
        prixSurfaceClient = prixChoixVernisClient * surfacePlancher;
        return prixSurfaceClient;
    }

    public static double calculerPrixMarchesClient(double nbrMarches) {
        // Calculer le sous-total des marches d'escaliers à sabler et à vernir.
        prixMarchesClient = nbrMarches * PRIX_MARCHE;
        return prixMarchesClient;
    }

    public static double calculerPrixContremarchesClient(double nbrContreMarches) {
        // Calculer le sous-total des contremarches d'escalier à sabler et à vernir.
        prixContreMarchesClient = nbrContreMarches * PRIX_CONMARCHE;
        return prixContreMarchesClient;
    }

    public static double calculerSoustotalClient(double prixSurfaceClient, double prixMarchesClient, double prixContreMarchesClient) {
        soustotalClient = prixSurfaceClient + prixMarchesClient + prixContreMarchesClient;
        return soustotalClient;
    }

    public static double calculerTps(double soustotalClient) {
        montantTpsClient = soustotalClient * (TPS / 100);
        return montantTpsClient;
    }

    public static double calculerTvq(double soustotalClient) {
        montantTvqClient = soustotalClient * (TVQ / 100);
        return montantTvqClient;
    }

    public static double calculerMontantTotalClient(double soustotalClient, double montantTpsClient, double montantTvqClient){
        montantTotalClient = soustotalClient + montantTpsClient + montantTvqClient;
        return montantTotalClient;
    }

    public static void main(String[] params) {

        //Déclarer et initialiser les constantes
        final int ID_VERNIS_EAU = 101;
        final int ID_VERNIS_HUILE = 102;
        final int ID_VERNIS_ALCOOL = 103;
        final int ID_MAT = 1;
        final int ID_SATINE = 2;
        final int ID_SEMI_LUS = 3;
        final int ID_LUS = 4;

        // Constantes de prix
        final double PRIX_MARCHE = 30.00;
        final double PRIX_CONMARCHE = 15.00;
        final double TPS = 5.00;
        final float TVQ = 9.975f;
        final double PRIX_VEAU = 3.50;
        final double PRIX_VHUILE = 3.75;
        final double PRIX_VALCOOL = 4.00;

        //Constante de facture
        final String NOM_ENTREPRISE = "Expert-Plancher";
        final String ADR_ENTREPRISE = "2021 boulevard Java, Informatique, QC ";
        final String TEL_ENTREPRISE = "(438)182-1100";

        //Constantes Messages
        final String MSG_ERR = "Entrée invalide!\n";
        final String MSG_FIN = "Merci beaucoup et passez une agréable journée!";

        //Variables Menu
        double nbrMarches;
        double nbrContreMarches;

        //Variables client
        String nomClient;
        String prenomClient;
        String telClient;
        String adrClient;
        int choixEau = 0;
        int choixHuile = 0;
        int choixAlcool = 0;

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

            //Déclaration de la date et l'heure
            Date dateHeureSysteme = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            //option 1

            if (choixMenu == 1) {
                nomClient = saisieNomClient();
                prenomClient = saisiePrenomClient();
                telClient = saisieNumTelClient();
                adrClient = saisieAdresseClient();

                numFacture += 1;

                surfacePlancher = saisieSurfacePlancher();

                identifiantVernis = saisieIdentifiantVernis();

                typeVernisFinition = saisieTypeVernisFinition();

                reponseEscalier = saisieQuestionEscalier();

                nbrMarches = saisieNombreMarche();

                nbrContreMarches = saisieNombreContremarche();

                modePaiement = saisieModeDePaiement();

                prixChoixVernisClient = determinerPrixChoixVernisClient(identifiantVernis);

                prixSurfaceClient = calculerPrixSurfaceClient(prixChoixVernisClient, surfacePlancher);

                prixMarchesClient = calculerPrixMarchesClient(nbrMarches);

                prixContreMarchesClient = calculerPrixContremarchesClient(nbrContreMarches);

                soustotalClient = calculerSoustotalClient(prixSurfaceClient, prixMarchesClient, prixContreMarchesClient);

                montantTpsClient = calculerTps(soustotalClient);

                montantTvqClient = calculerTvq(soustotalClient);


                //Affichage Date et Heure
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println(NOM_ENTREPRISE);
                System.out.print(ADR_ENTREPRISE + "     " + TEL_ENTREPRISE + "\n");
                System.out.println("Facture No: " + numFacture + "       Date et Heure: " + simpleDateFormat.format(dateHeureSysteme));
                System.out.println("---------------------------------------------------------------------------------------");

                //Affichage info client
                System.out.printf("Nom et prénom : %s %s       ", nomClient, prenomClient);
                System.out.println("Téléphone : " + telClient);
                System.out.println("Adresse du client : " + adrClient);

                //Afficher choix du client
                //type de vernis à appliquer
                String choixVernisClient;

                if (identifiantVernis == ID_VERNIS_EAU) {
                    choixEau = 1;
                    nbrTotalClientsEau += choixEau;

                    if (typeVernisFinition == ID_MAT) {
                        choixVernisClient = "Le vernis à base d'eau avec le fini Mat";
                    } else if (typeVernisFinition == ID_SATINE) {
                        choixVernisClient = "Le vernis à base d'eau avec le fini Satiné";
                    } else if (typeVernisFinition == ID_SEMI_LUS) {
                        choixVernisClient = "Le vernis à base d'eau avec le fini Semi-lustré";
                    } else {
                        choixVernisClient = "Le vernis à base d'eau avec le fini Lustré";
                    }

                } else if (identifiantVernis == ID_VERNIS_HUILE) {
                    choixHuile = 1;
                    nbrTotalClientsHuile += choixHuile;
                    if (typeVernisFinition == ID_MAT) {
                        choixVernisClient = "Le vernis à base d'huile avec le fini Mat";
                    } else if (typeVernisFinition == ID_SATINE) {
                        choixVernisClient = "Le vernis à base d'huile avec le fini Satiné";
                    } else if (typeVernisFinition == ID_SEMI_LUS) {
                        choixVernisClient = "Le vernis à base d'huile avec le fini Semi-lustré";
                    } else {
                        choixVernisClient = "Le vernis à base d'huile avec le fini Lustré";
                    }

                } else {
                    choixAlcool = 1;
                    nbrTotalClientsAlcool += choixAlcool;
                    if (typeVernisFinition == ID_MAT) {
                        choixVernisClient = "Le vernis à base d'alcool avec le fini Mat";
                    } else if (typeVernisFinition == ID_SATINE) {
                        choixVernisClient = "Le vernis à base d'alcool avec le fini Satiné";
                    } else if (typeVernisFinition == ID_SEMI_LUS) {
                        choixVernisClient = "Le vernis à base d'alcool avec le fini Semi-lustré";
                    } else {
                        choixVernisClient = "Le vernis à base d'alcool avec le fini Lustré";
                    }
                }
                System.out.println("Type de vernis à appliquer : " + choixVernisClient);

                System.out.println("Mode de paiement : " + modePaieClient + "\n");

                //Calculer les prix et les afficher
                double prixSurfaceClient;
                double prixChoixVernisClient;
                double prixMarchesClient;
                double prixContreMarchesClient;

                //Prix Surface à vernir
                if (identifiantVernis == ID_VERNIS_EAU && surfacePlancher > 0) {
                    prixSurfaceClient = surfacePlancher * PRIX_VEAU;
                    prixChoixVernisClient = PRIX_VEAU;
                } else if (identifiantVernis == ID_VERNIS_HUILE && surfacePlancher > 0) {
                    prixSurfaceClient = surfacePlancher * PRIX_VHUILE;
                    prixChoixVernisClient = PRIX_VHUILE;
                } else {
                    prixSurfaceClient = surfacePlancher * PRIX_VALCOOL;
                    prixChoixVernisClient = PRIX_VALCOOL;
                }
                System.out.printf("Surface à sabler et à vernir     %.2f pied carré x %.2f$ = %.2f$ \n", surfacePlancher, prixChoixVernisClient, prixSurfaceClient);


                //Prix total
                double soustotalClient = 0;
                double montantTps;
                double montantTvq;

                //Prix des marches et contre-marches
                if (reponseEscalier == PETIT_O || reponseEscalier == GRAND_O) {
                    prixMarchesClient = nbrMarches * PRIX_MARCHE;
                    prixContreMarchesClient = nbrContreMarches * PRIX_CONMARCHE;

                    System.out.printf("Nombre de marches                %.2f x %.2f$ = %.2f$ \n", nbrMarches, PRIX_MARCHE, prixMarchesClient);
                    System.out.printf("Nombre de contremarches          %.2f x %.2f$ = %.2f$ \n ", nbrContreMarches, PRIX_CONMARCHE, prixContreMarchesClient);

                    soustotalClient = prixMarchesClient + prixContreMarchesClient;

                }


                soustotalClient += prixSurfaceClient;
                montantTps = soustotalClient * (TPS / 100);
                montantTvq = soustotalClient * (TVQ / 100);
                montantTotalClient = soustotalClient + montantTps + montantTvq;

                System.out.printf("Sous-total                      %.2f$\n", soustotalClient);
                System.out.printf("Montant TPS                      %.2f$\n", montantTps);
                System.out.printf("Montant TVQ                      %.2f$\n", montantTvq);
                System.out.printf("Montant total                    %.2f$\n", montantTotalClient);

                System.out.println("        -------------------------------------------------------------------           ");
                System.out.println("                 " + MSG_FIN);

                montantTotalFactures += montantTotalClient;


            } else if (choixMenu == 2) {
                //Option 2

                System.out.println("\n-----------------------------------------------------------------------------------------------\n"
                        + NOM_ENTREPRISE + "\n"

                        + "Date et Heure : " + simpleDateFormat.format(dateHeureSysteme)
                        + "\n-----------------------------------------------------------------------------------------------\n");

                //calculer montant total dépensé par les clents
                System.out.printf("Le montant total de toutes les factures %.2f$", montantTotalFactures);
                System.out.println("\n-----------------------------------------------------------------------------------------------\n");

                //option 3

            } else if (choixMenu == 3) {
                System.out.println("\n-----------------------------------------------------------------------------------------------\n"
                        + NOM_ENTREPRISE + "\n"

                        + "Date et Heure : " + simpleDateFormat.format(dateHeureSysteme)
                        + "\n-----------------------------------------------------------------------------------------------\n");

                //calculer le nombre de clients par type de vernis
                ;

                System.out.println("Type de vernis " + "    Nombre de clients");
                System.out.println("****************************************\n");
                System.out.println("Le vernis à base d'eau      " + nbrTotalClientsEau);
                System.out.println("Le vernis à base d'huile    " + nbrTotalClientsHuile);
                System.out.println("Le vernis à l'alcool        " + nbrTotalClientsAlcool);
                System.out.println("\n-----------------------------------------------------------------------------------------------\n");

            } else if (choixMenu < 1 || choixMenu > 4) {
                System.out.println(MSG_ERR);

            }
        } while (choixMenu != 4);

        System.exit(0);

    } // main
} // TP1
