
public class Facture {
	public static int[][] Prod = new int[0][]; //passez de private a public pour pouvoir recuperer la longueur du tableau dans la calsse Menu
	private static int[][] Quant = new int[0][];

	/*
	 * Question 4.1 : Définir un facturier (voir sujet) Choix des abscices et
	 * ordonnees: Nous allons prendre pour les lignes des tableaux, les factures et
	 * en colonnes les lignes de produits. En choisissqnt cette solutions, cela
	 * evite de creer pour toutes les factures , le nombre de cases de la plus
	 * grande facture. En effet on consomme des ressources inutilement.
	 * 
	 */

	public static void main(String[] args) {
		int numFacture = nouvelleFacture();
		ajouterProduit(numFacture, 0, 10);
		ajouterProduit(numFacture, 3, 2);
		//afficherFacture(numFacture);

		numFacture = nouvelleFacture();
		ajouterProduit(numFacture, 1, 1);
		ajouterProduit(numFacture, 0, 5);
		ajouterProduit(numFacture, 2, 5);
		//afficherFacture(numFacture);

		//afficher();

	}

	public static int nouvelleFacture() {
		int[][] TamProd = new int[Prod.length + 1][0];
		int[][] TamQuant = new int[Quant.length + 1][0];
		for (int i = 0; i < Prod.length; i++) {

			TamProd[i] = Prod[i];
			TamQuant[i] = Quant[i];

		}
		Quant = TamQuant;
		Prod = TamProd;

		// TODO Question 4.2
		return Prod.length - 1;
	}

	public static void ajouterProduit(int numFacture, int numProduit, int quantite) {
		int[] TampligneP = new int[Prod[numFacture].length + 1];
		int[] TampligneQ = new int[Quant[numFacture].length + 1];
		for (int i = 0; i < Prod[numFacture].length; i++) {
			TampligneP[i] = Prod[numFacture][i];
			TampligneQ[i] = Quant[numFacture][i];
		}
		Prod[numFacture] = TampligneP;
		Quant[numFacture] = TampligneQ;
		Prod[numFacture][TampligneP.length - 1] = numProduit;
		Quant[numFacture][TampligneQ.length - 1] = quantite;

		// TODO Question 4.3

	}

	/**
	 * Affiche une facture avec son numéro, la liste des produits (nom du produit,
	 * quantité, prix unitaire et prix total de la ligne), et le total de la
	 * facture.
	 * 
	 * @param numFacture numéro de la facture
	 */
	public static void afficherFacture(int numFacture) {
		System.out.println("Facture n˚" + numFacture+"\n");
		double total =0;
		for (int i = 0; i < Prod[numFacture].length; i++) {
		String nom=Catalogue.getNom(Prod[numFacture][i]) ;
		double prix=Catalogue.getPrix(Prod[numFacture][i]);
		double quant=Quant[numFacture][i];
		total=total+prix*quant;
		System.out.println(nom + " ("+ prix+"€ par piece) x "+quant+" pieces = "+ quant*prix + "€" );
		}
		System.out.println("\nMontant total:"+ total+"€");
		System.out.println("\n---------------------------\n");
		// TODO Question 4.4

	}

	/**
	 * affiche toutes les factures.
	 */
	public static void afficher() {
		System.out.println("\n\n*********************-Facturier-*************************\n");
		
		for(int i=0;i<Prod.length;i++) {
			afficherFacture(i);
			
		
		// TODO Question 4.5

	}
		System.out.println("\n*******************************************************\n");}

}

