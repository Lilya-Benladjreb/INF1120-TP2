import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * I N F x 1 2 0
 * <p>
 * TP2 - Programme de Facturation de Expert-Plancher (v2)
 *
 * @author Lilya Benladjreb
 * @version 2021-10-11
 * <p>
 * BENL28549807
 * benladjreb.lilya@courrier.uqam.ca
 * Description du programme: Ce programme sert à facturer les clients de la compagnie d'Expert-Plancher. Elle a un menu
 * interractif qui (option 1) s'occupe de la sélection des produits, (option 2) de l'affichage de la facture et
 * (option 3) de l'historique de la compagnie. Finalement, l'option 4 sert à sortir du programme de facturation.
 */

public class TP2 {

    // Autres m�thodes s'il y a lieu

    public static void main(String[] params) {

        //Déclarer et initialiser les constantes
        final int ID_VERNIS_EAU = 101;
        final int ID_VERNIS_HUILE = 102;
        final int ID_VERNIS_ALCOOL = 103;
        final int ID_MAT = 1;
        final int ID_SATINE = 2;
        final int ID_SEMI_LUS = 3;
        final int ID_LUS = 4;
        final String UNITE = "pied(s) carré";

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
        final String CHOIX_MENU_UN = "Facturer le sablage et le vernissage de plancher et d'escaliers";
        final String CHOIX_MENU_DEUX = "Afficher le montant total de toutes les factures";
        final String CHOIX_MENU_TROIS = "Afficher le nombre de clients par type de vernis";
        final String CHOIX_MENU_VER_EAU = "Le vernis à base d'eau";
        final String CHOIX_MENU_VER_HUILE = "Le vernis à base d'huile";
        final String CHOIX_MENU_VER_ALCOOL = "Le vernis à base d'alcool";
        final String FINI_MAT = "Mat";
        final String FINI_SAT = "Satiné";
        final String FINI_SEMI_LUS = "Semi-lustré";
        final String FINI_LUS = "Lustré";
        final String CHOIX_MENU_QUATRE = "Quitter le programme";
        final String MSG_ERR = "Entrée invalide!\n";
        final String MSG_FIN = "Merci beaucoup et passez une agréable journée!";
        final char PETIT_O = 'o';
        final char GRAND_O = 'O';
        final char PETIT_N = 'n';
        final char GRAND_N = 'N';

        //Variables Menu
        char choixMenu;
        char modePaiement;
        char reponseEscalier;
        int identifiantVernis;
        int typeVernisFinition;
        boolean valide = false;
        double surfacePlancher;
        double nbrMarches;
        double nbrContreMarches;
        double prixTotPlancher;
        double prixTotMarches;

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
        String dateFacture;
        String heureFacture;
        double montantTotalClient = 0;
        double montantTotalFactures = 0;
        int nbrTotalClientsEau = 0;
        int nbrTotalClientsHuile = 0;
        int nbrTotalClientsAlcool = 0;

        // Affichicher un message de bienvenu + résumé du programme
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Bienvenue dans le system de facturation d'" + NOM_ENTREPRISE);
        System.out.println("Ce programme permet de calculer la facture de sablage, de vernissage de plancher \nselon le prix par pied carré et le type de vernis choisi. Il affiche aussi \nl'historique en calculant les montants des factures crée par les ventes et le nombre \nde clients par type de vernis.");
        System.out.println("---------------------------------------------------------------------------------------");

        // Afficher le menu 
        do {
            System.out.println("*** Menu de choix ***");
            System.out.println("1. " + CHOIX_MENU_UN);
            System.out.println("2. " + CHOIX_MENU_DEUX);
            System.out.println("3. " + CHOIX_MENU_TROIS);
            System.out.println("4. " + CHOIX_MENU_QUATRE + "\n");

            //Exécution des choix: 
            System.out.print("Entrez votre choix : ");
            choixMenu = Clavier.lireCharLn();

            //Déclaration de la date et l'heure
            Date dateHeureSysteme = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            //option 1

            if (choixMenu == '1') {
                System.out.print("Entrez le nom du client: ");
                nomClient = Clavier.lireString();
                System.out.print("Entrez le prénom du client: ");
                prenomClient = Clavier.lireString();
                System.out.print("Entrez le numéro de téléphone du client: ");
                telClient = Clavier.lireString();
                System.out.print("Entrez l'adresse du client: ");
                adrClient = Clavier.lireString();

                numFacture += 1;

                do {
                    System.out.print("Entrez la surface à sabler et à vernir en pieds carré (supérieur à 0) : ");
                    surfacePlancher = Clavier.lireDouble();

                    if (surfacePlancher > 0) {
                        valide = true;
                    } else {
                        System.out.print(MSG_ERR);
                    }
                } while (!valide);

                do {
                    //Saisie du vernis
                    System.out.print("Entrez l'identifiant du vernis");
                    System.out.print(" (" + ID_VERNIS_EAU + " = " + CHOIX_MENU_VER_EAU + "\n"
                            + "                                " + ID_VERNIS_HUILE + " = " + CHOIX_MENU_VER_HUILE + "\n"
                            + "                                " + ID_VERNIS_ALCOOL + " = " + CHOIX_MENU_VER_ALCOOL + "):  ");
                    identifiantVernis = Clavier.lireInt();

                    if (identifiantVernis < ID_VERNIS_EAU || identifiantVernis > ID_VERNIS_ALCOOL) {
                        System.out.println(MSG_ERR);
                    }

                } while (identifiantVernis < ID_VERNIS_EAU || identifiantVernis > ID_VERNIS_ALCOOL);

                do {
                    //Saisie du Finit
                    System.out.print("Entrez le type de finition" + "(" + ID_MAT + " = " + FINI_MAT + ", "
                            + ID_SATINE + " = " + FINI_SAT + ", "
                            + ID_SEMI_LUS + " = " + FINI_SEMI_LUS + ", "
                            + ID_LUS + " = " + FINI_LUS + "): ");
                    typeVernisFinition = Clavier.lireInt();

                    if (typeVernisFinition < ID_MAT || typeVernisFinition > ID_LUS) {
                        System.out.println(MSG_ERR);
                    }

                } while (typeVernisFinition < ID_MAT || typeVernisFinition > ID_LUS);

                do {
                    //Saisie Escalier 
                    System.out.print("Avez-vous des escaliers à sabler et à vernir (O ou o = Oui, N ou n = Non): ");
                    reponseEscalier = Clavier.lireCharLn();

                    if (reponseEscalier != PETIT_O && reponseEscalier != GRAND_O && reponseEscalier != PETIT_N && reponseEscalier != GRAND_N) {
                        valide = false;
                        System.out.println(MSG_ERR);
                    }
                } while (!valide);

                nbrMarches = 0;
                nbrContreMarches = 0;

                if (reponseEscalier == PETIT_O || reponseEscalier == GRAND_O) {
                    do {
                        System.out.print("Entrez le nombre de marches (supérieur à 0): ");
                        nbrMarches = Clavier.lireDouble();
                        if (nbrMarches <= 0) {
                            System.out.print(MSG_ERR);
                        }
                    } while (nbrMarches <= 0);

                    do {
                        System.out.print("Entrez le nombre de contremarches (supérieur à 0): ");
                        nbrContreMarches = Clavier.lireDouble();
                        if (nbrContreMarches <= 0) {
                            System.out.println(MSG_ERR);
                        }
                    } while (nbrContreMarches <= 0);
                }

                //Saisie mode paiement
                String modePaieClient;
                modePaiement = ' ';
                modePaieClient = " ";

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
                        //entrée incorecte (message d'erreur)
                        valide = false;
                        System.out.println(MSG_ERR);
                    }

                } while (!valide);

                //Calculer no Facture pour l'afficher dans menu 1 et pouvoir l'additionner dans menu 3

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

                if (identifiantVernis == ID_VERNIS_EAU && typeVernisFinition == ID_MAT) {
                    choixVernisClient = "Le vernis à base d'eau avec le fini Mat";
                    choixEau = 1;
                    nbrTotalClientsEau += choixEau;
                } else if (identifiantVernis == ID_VERNIS_EAU && typeVernisFinition == ID_SATINE) {
                    choixVernisClient = "Le vernis à base d'eau avec le fini Satiné";
                    choixEau = 1;
                    nbrTotalClientsEau += choixEau;
                } else if (identifiantVernis == ID_VERNIS_EAU && typeVernisFinition == ID_SEMI_LUS) {
                    choixVernisClient = "Le vernis à base d'eau avec le fini Semi-lustré";
                    choixEau = 1;
                    nbrTotalClientsEau += choixEau;
                } else if (identifiantVernis == ID_VERNIS_EAU) {
                    choixVernisClient = "Le vernis à base d'eau avec le fini Lustré";
                    choixEau = 1;
                    nbrTotalClientsEau += choixEau;
                } else if (identifiantVernis == ID_VERNIS_HUILE && typeVernisFinition == ID_MAT) {
                    choixVernisClient = "Le vernis à base d'huile avec le fini Mat";
                    choixHuile = 1;
                    nbrTotalClientsHuile += choixHuile;
                } else if (identifiantVernis == ID_VERNIS_HUILE && typeVernisFinition == ID_SATINE) {
                    choixVernisClient = "Le vernis à base d'huile avec le fini Satiné";
                    choixHuile = 1;
                    nbrTotalClientsHuile += choixHuile;
                } else if (identifiantVernis == ID_VERNIS_HUILE && typeVernisFinition == ID_SEMI_LUS) {
                    choixVernisClient = "Le vernis à base d'huile avec le fini Semi-lustré";
                    choixHuile = 1;
                    nbrTotalClientsHuile += choixHuile;
                } else if (identifiantVernis == ID_VERNIS_HUILE) {
                    choixVernisClient = "Le vernis à base d'huile avec le fini Lustré";
                    choixHuile = 1;
                    nbrTotalClientsEau += choixHuile;
                } else if (typeVernisFinition == ID_MAT) {
                    choixVernisClient = "Le vernis à l'alcool avec le fini Mat";
                    choixAlcool = 1;
                    nbrTotalClientsAlcool += choixAlcool;
                } else if (typeVernisFinition == ID_SATINE) {
                    choixVernisClient = "Le vernis à l'alcool avec le fini Satiné";
                    choixAlcool = 1;
                    nbrTotalClientsAlcool += choixAlcool;
                } else if (typeVernisFinition == ID_SEMI_LUS) {
                    choixVernisClient = "Le vernis à l'alcool avec le fini Semi-lustré";
                    choixAlcool = 1;
                    nbrTotalClientsAlcool += choixAlcool;
                } else {
                    choixVernisClient = "Le vernis à l'alcool avec le fini Lustré";
                    choixAlcool = 1;
                    nbrTotalClientsAlcool += choixAlcool;
                }
                System.out.println("Type de vernis à appliquer : " + choixVernisClient);

                System.out.println("Mode de paiement : " + modePaieClient + "\n");

                //Calculer les prix et les afficher
                double prixSurfaceClient;
                double prixChoixVClient;
                double prixMarchesClient;
                double prixContreMarchesClient;
                double SousTotalClient;

                //Prix Surface à vernir
                if (identifiantVernis == ID_VERNIS_EAU && surfacePlancher > 0) {
                    prixSurfaceClient = surfacePlancher * PRIX_VEAU;
                    prixChoixVClient = PRIX_VEAU;
                } else if (identifiantVernis == ID_VERNIS_HUILE && surfacePlancher > 0) {
                    prixSurfaceClient = surfacePlancher * PRIX_VHUILE;
                    prixChoixVClient = PRIX_VHUILE;
                } else {
                    prixSurfaceClient = surfacePlancher * PRIX_VALCOOL;
                    prixChoixVClient = PRIX_VALCOOL;
                }
                System.out.printf("Surface à sabler et à vernir     %.2f pied carré x %.2f$ = %.2f$ \n", surfacePlancher, prixChoixVClient, prixSurfaceClient);


                //Prix total
                double soustotalClient = 0;
                double montantTps;
                double montantTvq;

                //Prix des marches et contre-marches 
                if (reponseEscalier == PETIT_O || reponseEscalier == GRAND_O) {
                    prixMarchesClient = nbrMarches * PRIX_MARCHE;
                    prixContreMarchesClient = nbrContreMarches * PRIX_CONMARCHE;

                    System.out.printf("Nombre de marches                %f x %.2f$ = %.2f$ \n", nbrMarches, PRIX_MARCHE, prixMarchesClient);
                    System.out.printf("Nombre de contremarches          %f x %.2f$ = %.2f$ \n ", nbrContreMarches, PRIX_CONMARCHE, prixContreMarchesClient);

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


            } else if (choixMenu == '2') {
                //Option 2

                System.out.println("\n-----------------------------------------------------------------------------------------------\n"
                        + NOM_ENTREPRISE + "\n"

                        + "Date et Heure : " + simpleDateFormat.format(dateHeureSysteme)
                        + "\n-----------------------------------------------------------------------------------------------\n");

                //calculer montant total dépensé par les clents
                System.out.printf("Le montant total de toutes les factures %.2f$", montantTotalFactures);
                System.out.println("\n-----------------------------------------------------------------------------------------------\n");

                //option 3

            } else if (choixMenu == '3') {
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

            } else if (choixMenu < '1' || choixMenu > '4') {
                System.out.println(MSG_ERR);

            }
        } while (choixMenu != '4');

        System.exit(0);

    } // main
} // TP1
