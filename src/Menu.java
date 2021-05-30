
public class Menu {

	public static void main(String[] args) {
		boolean correct = true;
		do {
			try {
				int Choix;

				System.out.println(
						"\n\n************************---- Bienvenue chez Office Valley ------************************\n \n\n                               "
								+ "Que voulez vous faire?\n\n\n\n*1:   Afficher un produit\n*2:   Ajouter un produit\n*3:   Afficher tout le Catalogue\n*4:   "
								+ "Affichage d une facture\n*5:   Ajout d une facture\n*6:   Afficher le facturier\n*7:   EXIT =>>\n\n");
				do {
					System.out.print("Pour le choix rentrez un chiffre: ");
					Choix = Terminal.lireInt();
				} while (Choix < 1 || Choix > 7);

				if (Choix == 1) {
					affichProd();
				}
				if (Choix == 2) {
					entrerUnProduit();
				}
				if (Choix == 3) {Catalogue.afficher();
					main(null);
				}
				if (Choix == 4) {
					afficherFact();
				}
				if (Choix == 5) {
					creerFacture();
				}

				if (Choix == 6) {
					Facture.afficher();
					main(null);
				}
				if (Choix == 7) {
					System.out.print("\n\n\nHOOPS, VOUS N'AVEZ RIEN SAUVEGARDE,DOMMAGE,\nBYE BYE ======>>>>>>>>");
				}
			} catch (TerminalException e) {
				System.out.print("Je ne crois pas que vous ayez rentre un chiffre, reessayez");
				main(null);
				correct = false;
			}
		} while (!correct);

	}

	public static void entrerUnProduit() {
		boolean correct = true;
		System.out.print(
				"\n*********-Ajout de produits au catalogue-********\n\n" + "Veuillez rentrer le nom de l'article: ");
		String Nom = Terminal.lireString();
		do {
			try {
				System.out.print("\nVeuillez rentrer son prix en euros: ");
				double Prix = Terminal.lireDouble();
				Catalogue.ajouter(Nom, Prix);
				main(null);
			} catch (TerminalException e) {
				System.out.print("Ca ne ressemble pas trop a un prix.... Essayez encore");
				correct = false;
			}
		} while (!correct);
	}

	public static void affichProd() {

		System.out.print("\n*****-En Stock–*****\n");
		for (int i = 0; i < Catalogue.tabNoms.length; i++) {
			System.out.println(Catalogue.tabNoms[i]);
		}
		System.out.print("\nVeuillez rentrer le NOM :");
		String nom = Terminal.lireString();
		int num = Catalogue.chercher(nom);

		if (num == -1) {
			System.out.println("Le produit " + nom + " n 'est pas en catalogue");
		} else {
			double prix = Catalogue.getPrix(num);
			System.out.print("\nProduit n=˚" + num + ": " + nom + " au prix de : " + prix + "€");
		}
		main(null);
	}



	public static void afficherFact() {
		if (Facture.Prod.length - 1 >= 0) {
			try {
				System.out.print(
						"*****************-Facture-****************\n\n\nQuelle facture voulez vous afficher(de 0 a "
								+ (Facture.Prod.length - 1) + "): ");
				int nfact = Terminal.lireInt();
				Facture.afficherFacture(nfact);
			} catch (TerminalException e) {
				System.out.println("\n\nCa ne ressemble pas trop a un chiffre, essais unr nouvelle fois");
				afficherFact();
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("\n\nCa n est pas entre 0 et " + (Facture.Prod.length - 1) + "\n\n\n\n");
				afficherFact();
			}
		} else {
			System.out.print("Il n y a pas de factures");
		}
		main(null);
	}

	public static void creerFacture() {
		int nfact = Facture.nouvelleFacture();
		int num = 0;
		int nb = 0;
		int quant = 0;
		boolean correct = true;
		System.out.print("\n*********-Creation d'une facture-********\n\n");
		try {
			do {
				System.out.print("\nCombien d'article avez vous a rentrer: ");
				nb = Terminal.lireInt();
			} while (nb < 0);
		} catch (TerminalException e) {
			System.out.println("Je crois qu avec ca va etre difficile de creer une facture, reesayez.");
			creerFacture();
		}
		Catalogue.afficher();
		for (int j = 0; j < nb; j++) {

			do {
				try {
					do {
						correct = true;
						System.out.print(
								"\nArticle: "+j+"\n"+"\nRentrez le numeros de produit de 0 a " + (Catalogue.tabNoms.length - 1) + ": ");
						num = Terminal.lireInt();
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
					System.out.println("Hmmm c est moi ou ca n est pas un nombre, Reesayez");
					correct = false;
				}
			} while (!correct);
			System.out.println("\n##-PRODUIT ENREGISTRE_##\n");
			Facture.ajouterProduit(nfact, num, quant);

		}
		System.out.println("\n########-FACTURE ENREGISTRE_########\n");
		main(null);
	}

}
