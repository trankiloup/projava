
public class Facture {
	public static int[][] Prod = new int[0][]; // passe de private a public pour pouvoir recuperer la longueur du
												// tableau dans la classe Menu
	private static int[][] Quant = new int[0][];

	/*
	 * Question 4.1 : Définir un facturier (voir sujet) Choix des abscices et
	 * ordonnees: Nous allons prendre pour les lignes des tableaux, les factures et
	 * en colonnes les lignes de produits. En choisissqnt cette solutions, cela
	 * evite de creer pour toutes les factures , le nombre de cases de la plus
	 * grande facture. En effet on consomme de la memoire inutilement.
	 * 
	 */

	public static int nouvelleFacture()
	/*
	 * Pour creer une nouvelle facture, on creer deux tableaux avec une case de
	 * plus, ceux sont les tableaux Tampons: Tamprod et TamQuant
	 *  1 pour les produits, et 1 pour la quantite Puis on recopie Prod et Quant a l aide de la boucle
	 * FOR dans les tableaux tampons, jusqu a la taille du tableau Prod (qui fait la meme taille que Quant)
	 */
	{
		int[][] TamProd = new int[Prod.length + 1][0]; // creation tableau plus grand
														// avec mise a 0 du tableau de tableau pour pouvoir ajouter un
														// produit
		int[][] TamQuant = new int[Quant.length + 1][0];
		for (int i = 0; i < Prod.length; i++) {

			TamProd[i] = Prod[i]; // copie de tableau
			TamQuant[i] = Quant[i];

		}
		Prod = TamProd; // Remplace l ancienne adresse de Prod et Quant avec celles des tableaux tampons
		Quant = TamQuant;

		// TODO Question 4.2
		return Prod.length - 1; // renvoie le numeros de la derniere facture
	}

	public static void ajouterProduit(int numFacture, int numProduit, int quantite) {
		/*
		 * Cette fonction permet de rentrer des lignes de produit sur la derniere
		 * facture cree, pour cela on cree un tableau tampon de la taille initial + 1 case 
		 * pour y mettre le nouveau Produit et la nouvelle quantite
		 */
		int[] TampligneP = new int[Prod[numFacture].length + 1]; // Creation de 2 Tableaux tampons de la taille de l
																	// originale + 1 case, a l adresse
		int[] TampligneQ = new int[Quant[numFacture].length + 1]; // du numeros de facture
		for (int i = 0; i < Prod[numFacture].length; i++) {
			TampligneP[i] = Prod[numFacture][i]; // Recopie de l ancien tableau dans le tableau tampon
			TampligneQ[i] = Quant[numFacture][i];
		}
		Prod[numFacture] = TampligneP; // Ecrase l ancienne adresse des tableaux se situant au numeros de facture de
		Quant[numFacture] = TampligneQ; // Prod et Quant avec celles des tableaux tampons
		Prod[numFacture][TampligneP.length - 1] = numProduit;
		Quant[numFacture][TampligneQ.length - 1] = quantite; // Copie le nouveau produit et sa nouvelle quantite dans la
																// derniere case cree

		// TODO Question 4.3

	}

	/**
	 * Affiche une facture avec son numéro, la liste des produits (nom du produit,
	 * quantité, prix unitaire et prix total de la ligne), et le total de la
	 * facture.
	 * 
	 * @param numFacture numéro de la facture
	 */
	public static void afficherFacture(int numFacture)
	/*
	 * Pour afficher la facture, on extrait les valeurs de Prod et Quant a l
	 * emplacement du numeros de facture
	 */

	{
		System.out.println("\n\nFacture n˚" + numFacture + "\n");
		double total = 0;										//Variable permettant de calculer le prix total
		for (int i = 0; i < Prod[numFacture].length; i++) {			//on va recuperer les valeurs du tableau de tableau jusqu a la longueur du tableau de tableau
			String nom = Catalogue.getNom(Prod[numFacture][i]);		//On recupere le nom du produit avec son numeros en faisant appel a la fonction getNom
			double prix = Catalogue.getPrix(Prod[numFacture][i]);	//idem pour le prix
			double quant = Quant[numFacture][i];			//On recupere la quantite
			total = total + prix * quant;		//on fait la somme des nouveaux prix trouves
			System.out.println(nom + " (" + prix + "€ par piece) x " + quant + " pieces = " + quant * prix + "€"); //affichage du tout
		}
		System.out.println("\nMontant total:" + total + "€");			//affichage des resultats
		System.out.println("\n---------------------------\n");
		// TODO Question 4.4

	}

	/**
	 * affiche toutes les factures.
		Pour cela on fait appel a la fonction afficherFacture en incrementantle numeros de facture
		jusqu'a la taille du tableau Prod
	 * @throws InterruptedException 
	 *
	 */
	public static void afficher() throws InterruptedException {
		if (Prod.length > 0) {
			System.out.println("\n\n*********************-Facturier-*************************\n");

			for (int i = 0; i < Prod.length; i++) {
				afficherFacture(i);	
				Thread.sleep(800);
			}
		} else {
			System.out.print("Il n y a pas de facture a afficher");
		}

		// TODO Question 4.5

		System.out.println("\n*******************************************************\n");
	}

}
