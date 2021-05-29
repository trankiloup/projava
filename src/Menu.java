
public class Menu {

	public static void main(String[] args) {
		auChoix();

	}

	public static void auChoix() {

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
		if (Choix == 3) {
			Catalogue.afficher();
			auChoix();
		}
		if (Choix == 4) {
			afficherFact();
		}
		if (Choix == 5) {
			creerFacture();}
		
		if (Choix == 6) {
				Facture.afficher();
				auChoix();
			}
		if (Choix ==7) {
			System.out.print("\n\n\nBYE BYE ======>>>>>>>>");
		}
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
		auChoix();
	}

	public static void entrerUnProduit() {
		System.out.print(
				"\n*********-Ajout de produits au catalogue-********\n\n" + "Veuillez rentrer le nom de l'article: ");
		String Nom = Terminal.lireString();
		System.out.print("\nVeuillez rentrer son prix en euros: ");
		double Prix = Terminal.lireDouble();
		Catalogue.ajouter(Nom, Prix);
		auChoix();

	}

	public static void afficherFact() {
		System.out.print("*****************-Facture-****************\n\n\nQuelle facture voulez vous afficher(de 0 a "
				+ (Facture.Prod.length - 1) + "): ");
		int nfact = Terminal.lireInt();
		Facture.afficherFacture(nfact);
		auChoix();
	}

	public static void creerFacture() {
		int nfact = Facture.nouvelleFacture();
		System.out.print("\n*********-Creation d'une facture-********\n\n");
		System.out.print("Combien d'article avew vous a rentrer: ");
		int nb = Terminal.lireInt();
		for (int j = 0; j < nb; j++) {
			for (int i = 0; i < Catalogue.tabNoms.length; i++) {
				System.out.println("* " + i + " : " + Catalogue.getNom(i) + "  => prix: " + Catalogue.getPrix(i) + "€");
			}
			System.out.print("\nRentrez le numeros de produit: ");
			int num = Terminal.lireInt();
			System.out.print("Combien en voulez vous : ");
			int quant = Terminal.lireInt();
			Facture.ajouterProduit(nfact, num, quant);
		}
		auChoix();
	}

}
