
public class Catalogue {
	public static String[] tabNoms = { "Stylo bleu", "Stylo rouge", "Cahier petit format", "Cahier grand format" };//Variable passe en public pour l affichage dans mon choix 1 du menu
	private static double[] tabPrix = { 1.2, 1.25, 2.00, 3.00 };

	public static void main(String[] args) {
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("Catalogue initial :");
		afficher();
		ajouter("Classeur A4", 1.55);
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("Catalogue avec classeur A4 en plus :");
		afficher();
		ajouter("Ramette A4", 2.55);
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("Catalogue avec classeur A4 et ramette A4 en plus :");
		afficher();

		int styloBleu = chercher("Stylo bleu");
		if (styloBleu != -1) {
			System.out.println("Produit " + styloBleu + " : " + getNom(styloBleu) + " = " + getPrix(styloBleu) + "�");
		} else {
			System.out.println("Pas de stylo bleu");
		}
		int classeur = chercher("Classeur A4");
		if (classeur != -1) {
			System.out.println("Produit " + classeur + " : " + getNom(classeur) + " = " + getPrix(classeur) + "�");
		} else {
			System.out.println("Pas de classeur A4");
		}
		int trousse = chercher("Trousse");
		if (trousse != -1) {
			System.out.println("Produit " + trousse + " : " + getNom(trousse) + " = " + getPrix(trousse) + "�");
		} else {
			System.out.println("Pas de trousse");
		}
	}

	/**
	 * retourne le nom du produit de num�ro numProduit.
	 * 
	 * @param numProduit le num�ro du produit
	 * @return le nom du produit
	 */
	public static String getNom(int numProduit) {

		return tabNoms[numProduit];
	}

	/**
	 * retourne le prix du produit de num�ro numProduit.
	 * 
	 * @param numProduit le num�ro du produit
	 * @return le prix du produit
	 */
	public static double getPrix(int numProduit) {
		// TODO Question 3.1
		return tabPrix[numProduit];
	}

	/**
	 * affiche le catalogue � l'�cran.
	 */
	public static void afficher() {
		for (int i = 0; i <tabPrix.length; i++) {
			System.out.println(getNom(i) + " = " + getPrix(i)+"€");
		}
	}

	/**
	 * La m�thode <code>ajouter(String nom, double prix)</code> doit permettre
	 * d'ajouter un nouveau produit � la structure de donn�es. Il faudra cr�er deux
	 * tableaux, un pour les noms et un pour les prix avec une case de plus que
	 * tabNoms et tabPrix. Puis, recopier le contenu de tabNoms et tabPrix dans les
	 * nouveaux tableaux. Ensuite, il faudra ajouter le nom et le prix du produit
	 * dans les derni�res cases des deux nouveaux tableaux. Enfin, il faudra que
	 * tabNoms et tabPrix d�signent ces nouveaux tableaux.
	 * 
	 * @param nom  le nom du nouveau produit
	 * @param prix le prix du nouveau produit
	 */
	public static void ajouter(String nom, double prix) {
		String [] tamptabnom= new String [tabNoms.length +1 ];
		double [] tamptabprix= new double[tabPrix.length +1];
		for (int i=0;i<tabNoms.length;i++) {
			
			tamptabnom[i]=tabNoms[i];
			tamptabprix[i]=tabPrix[i];
			
		}
		tamptabnom[tamptabnom.length-1]= nom;
		tamptabprix[tamptabprix.length-1]= prix;
		tabNoms=tamptabnom;
		tabPrix=tamptabprix;
		// TODO Question 3.3

	}

	/**
	 * cherche dans le catalogue le produit dont le nom est pass� en param�tre et
	 * retourne son num�ro. Si le produit n'est pas trouv�, la m�thode retournera
	 * -1.
	 * 
	 * Pour savoir si le nom du produit num�ro i est �gal au nom cherch�, n'utilisez
	 * pas == mais, plut�t, l'expression nom.equals(tabNoms[i]) qui retourne true si
	 * les deux textes sont identiques ou false dans le cas contraire.
	 * 
	 * @param nom le nom du produit cherch�
	 * @return le num�ro du produit dans le catalogue si le produit est pr�sent
	 *         sinon -1.
	 */
	public static int chercher(String nom) {
		int indice=0;
		while (indice<tabNoms.length && !(nom.equals(tabNoms[indice]))  ){indice++;}
		if (indice == tabNoms.length) {indice=-1;}
		// TODO Question 3.4
		return indice;
		
		
		
	}
}
