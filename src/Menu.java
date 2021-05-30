
public class Menu {

	public static void main(String[] args) throws InterruptedException {
		boolean correct = true;
		do { /*
				 * boucle while imbrique, la premiere permet de gerer les TerminalException et
				 * la seconde, d obliger a rentrer un nombre entre 1 et 7
				 */
			try {
				int Choix;

				System.out.println(
						"\n\n************************---- Bienvenue chez Office Valley ------************************\n \n\n                               "
								+ "Que voulez vous faire?\n\n\n\n*1:   Afficher un produit\n*2:   Ajouter un produit\n*3:   Afficher tout le Catalogue\n*4:   "
								+ "Affichage d une facture\n*5:   Creer une facture\n*6:   Afficher le facturier\n*7:   EXIT =>>\n\n");
				do {
					System.out.print("Pour le choix rentrez un chiffre: ");
					Choix = Terminal.lireInt();
				} while (Choix < 1 || Choix > 7);

				if (Choix == 1) {
					effacetout();
					affichProd(); // affiche les infos sur le produit rentrer par l utilisateur
					attente(10);
					main(null);
				}
				if (Choix == 2) {
					effacetout();
					entrerUnProduit(); // Permet d ajouter un produit au catalogue
					main(null);
				}
				if (Choix == 3) {
					effacetout();
					Catalogue.afficher(); // Permet d afficher le cataltogue
					attente(10);
					main(null);
				}
				if (Choix == 4) {
					effacetout();
					afficherFact(); // Permet d afficher 1 facture en fonction de son numeros, rentre par l
									// utilisateur
					attente(10);
					main(null);
				}
				if (Choix == 5) {
					effacetout();
					creerFacture(); // Permet de creer une facture
					attente(5);
					main(null);
				}

				if (Choix == 6) {
					effacetout();
					Facture.afficher(); // Permet d afficher le facturier	
					attente(10);
					main(null);
				}
				if (Choix == 7) { // Sortie de programme avec un System.exit, comme argument 0 pour une sortie avec
									// succes...
					effacetout();
					System.out.print("\n\n\nHOOPS, VOUS N'AVEZ RIEN SAUVEGARDE,DOMMAGE,\nBYE BYE ======>>>>>>>>");
					System.exit(0);
				}
			} catch (TerminalException e) {
				System.out.print("Je ne crois pas que vous ayez rentre un chiffre, reessayez");
				main(null);
				correct = false; /*
									 * Si la TerminalException est leve, alors le catch renvoie un message et on
									 * relance le main pour refaire un choix plus approprie en attribuant la valeur
									 * false a "correct"
									 */
			}
		} while (!correct);		//Boucle a faire tant que while est false

	}

	public static void affichProd() {
		/* Cette boucle for va lire les noms de TabNoms jusqu a la taille du tableau en incrementant i
			Je n ai pas voulu utiliser la fonction afficher de catalogue, car elle utilise les numeros de produits, ajoutant de 
			la confusion a la saisie(Pour cette fonction il est demande de rentrer le nom)*/
		System.out.print("\n*****-En Stock–*****\n");
		for (int i = 0; i < Catalogue.tabNoms.length; i++) {		
			System.out.println(Catalogue.tabNoms[i]);
		}
		System.out.print("\nVeuillez rentrer le NOM :");
		String nom = Terminal.lireString();
		int num = Catalogue.chercher(nom);

		if (num == -1) {
			System.out.println("Le produit " + nom + " n 'est pas en catalogue");	//Si le produit n est pas en catalogue, la valeur renvoye est -1
		} else {
			double prix = Catalogue.getPrix(num);
			System.out.print("\nProduit n=˚" + num + ": " + nom + " au prix de : " + prix + "€");
		}

	}

	public static void entrerUnProduit() {
		boolean correct = true;
		double Prix=0;
		System.out.print(
				"\n*********-Ajout de produits au catalogue-********\n\n" + "Veuillez rentrer le nom de l'article: ");
		String Nom = Terminal.lireString();
		do { correct=true; //reinitialisation de correct si l erreur est faites plusieurs fois
			try {
				System.out.print("\nVeuillez rentrer son prix en euros: ");
				 Prix = Terminal.lireDouble();
				Catalogue.ajouter(Nom, Prix);
												//Idem que plus haut un try catch evite d avoir une erreur si l utilisateur rentre autre chose qu un double
			} catch (TerminalException e) {
				System.out.print("Ca ne ressemble pas trop a un prix.... Essayez encore");
				correct = false;
			}
		} while (!correct || (Prix<0)); // Idem pour Terminal exception et en plus gestion de prix negatif
		System.out.println("\n##-PRODUIT ENREGISTRE_##\n");}


	public static void creerFacture() {
		/*Dans cette fonction, on va demander le nombre d article a rentrer, faire une boucle for qui va englober
		 la demande du numeros de l article, et sa quantite a repeter 'nb' fois
		 Le tout avec une gestion de la TerminalException dans un Do while englobant un autre
		 do while pour eviter de rentrer un nombre negatif.
		 */
		int nfact = Facture.nouvelleFacture();// recupere le numeros et cree une nouvelle facture
		int num = 0;	
		int nb = 0;			//initialisation des variables, pour dans l ordre : le numeros de l article, le nombre d article a rentrer, et la quantite de chaque article
		int quant = 0;
		boolean correct = true; //pour gerer la lever d exception
		System.out.print("\n*********-Creation d'une facture-********\n\n");
		try {
			do {
				System.out.print("\nCombien d'article avez vous a rentrer: ");	//nombre d article a rentrer dans la facture avec gestion des exceptions
				nb = Terminal.lireInt();
			} while (nb < 0);
		} catch (TerminalException e) {
			System.out.println("Je crois qu avec ca, ca va etre difficile de creer une facture, reesayez.");
			creerFacture();
		}
		Catalogue.afficher();	//Pour faciliter la saisie j affiche le catalogue
		for (int j = 0; j < nb; j++) {

			do {
				try {
					do {
						correct = true;
						System.out.print("\nArticle: " + j + "\n" + "\nRentrez le numeros de produit de 0 a "
								+ (Catalogue.tabNoms.length - 1) + ": ");
						num = Terminal.lireInt();								//Boucle permettant de rentrer le numeros de produit jusqu a ce qu il soit bon
					} while (num < 0 || num > Catalogue.tabNoms.length - 1);
				} catch (TerminalException e) {
					System.out.print("Hmmm c est moi ou ca n est pas un nombre, Reesayez");
					correct = false;
				}
			} while (!correct);

			do {
				try {
					do {
						correct = true;
						System.out.print("Combien en voulez vous : ");

						quant = Terminal.lireInt();
					} while (quant < 0);
				} catch (TerminalException e) {
					System.out.println("Hmmm c est moi ou ca n est pas un nombre, Reesayez");	//Boucle permettant de rentrer le nombre jusqu a ce que la saisie soit bonne
					correct = false;
				}
			} while (!correct);
			System.out.println("\n##-PRODUIT ENREGISTRE_##\n");
			Facture.ajouterProduit(nfact, num, quant);

		}
		System.out.println("\n########-FACTURE ENREGISTRE_########\n");

	}
	public static void afficherFact() {
		if (Facture.Prod.length - 1 >= 0) {//Ne fait la boucle que s il y a ds factures
			try {
				System.out.print(
						"*****************-Facture-****************\n\n\nQuelle facture voulez vous afficher(de 0 a "
								+ (Facture.Prod.length - 1) + "): "); //Permet d afficher le range des factures
				int nfact = Terminal.lireInt();
				Facture.afficherFacture(nfact);
			} catch (TerminalException e) {
				System.out.println("\n\nCa ne ressemble pas trop a un chiffre, essayez une nouvelle fois");
				afficherFact();
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("\n\nCa n est pas entre 0 et " + (Facture.Prod.length - 1) + "\n\n\n\n");
				afficherFact();  //Pour eviter les sorties de tableaux try catch d exception avec retour au debut de la fonction
			}
		} else {
			System.out.print("Il n y a pas de factures"); 
		}
		
	}

	public static void effacetout() {
		for (int i = 0; i < 50; ++i)
			System.out.println(); // Permet de decaler l affichage de 50 lignes pour plus de clartee
	}

	public static void attente(int t) throws InterruptedException {
		System.out.print("\n\nVeuillez patienter");
		for (int i = 0; i < t; i++) { // Permet une attente, afin d ameliorer la visiblite de l information
			Thread.sleep(300);
			System.out.print(".");
		}
		;
	}
}
