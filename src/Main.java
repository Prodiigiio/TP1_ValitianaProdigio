/* INSCRIVEZ VOTRE (OU VOS) PRÉNOM ET NOM CI-DESSOUS
   1:Prodigio Valitiana
   2:
   Adresse URL de votre dépôt GitHub:
NE MODIFIEZ PAS LE RESTE DE CE FICHIER
(sauf si c'est pour mettre certaines sections en commentaire le temps de tester votre code)
*/
public class Main {
    public static void main(String[] args) {
        // Initialisation des composants
        Composant core13600k = new Composant("CPU", "Intel", "Core i5-13600K", 330);
        Composant ryzen5700x = new Composant("CPU", "AMD", "Ryzen 5 5700X", 260);
        Composant ryzen7800x3d = new Composant("CPU", "AMD", "Ryzen 7 7800X3D", 500);
        Composant asusB760 = new Composant("Carte mère", "Asus", "ROG Strix B760", 200);
        Composant msiB550 = new Composant("Carte mère", "MSI", "B550 Gaming Wifi", 150);
        Composant tridentzDDR4 = new Composant("Ram", "GSkill", "Trident-Z DDR4 32GB", 135);
        Composant tridentzDDR5 = new Composant("Ram", "GSkill", "Trident-Z DDR5 16GB", 90);
        Composant samsung980 = new Composant(" ssd", "Samsung", "980 Pro 2TB", 250);
        Composant wdSN850x = new Composant("ssd ", "Western Digital", "SN850X 1TB", 100);
        Composant asusRTX4060 = new Composant("gpu", "Asus", "RTX 4060", 460);
        Composant gbRTX4060 = new Composant("GPU", "Gigabyte", "RTX 4060", 400);
        commencerTest("Affichage des configurations initiales");
        // Initialisation des configurations
        Configuration config0 = new Configuration("Build vide", 0, new Composant[]{});
        System.out.println(config0);
        afficherTotal(config0);
        Configuration config1 = new Configuration("Build Intel Gen13", 1250, new Composant[]{core13600k, asusB760, tridentzDDR5, asusRTX4060});
        System.out.println(config1);
        afficherTotal(config1);
        Configuration config2 = new Configuration("Build AMD Gen5", 1000, new Composant[]{ryzen5700x, msiB550});
        System.out.println(config2);
        afficherTotal(config2);
        commencerTest("Recherche et manipulation de composants");
        Composant cpu = config1.rechercher("CPU");
        System.out.println("La composante: " + cpu); // La composante: [CPU] Intel Core i5-13600K
        System.out.println("Sa catégorie: " + cpu.getCategorie()); // Sa catégorie: CPU
        System.out.println("Est-ce core13600k?: " + cpu.estIdentique(core13600k)); // Est-ce core13600k?: true
        Composant mobo = config2.rechercher("carte mère");
        System.out.println("La composante: " + mobo); // La composante: [CARTE MÈRE] MSI B550 Gaming Wifi
        System.out.println("Son prix: " + mobo.getPrix()); // Son prix: 150.0
        System.out.println("Son rabais: " + mobo.getRabais()); // Son rabais: 0.0
        Composant ram = config1.rechercher("ram").copier();
        System.out.println("La composante: " + ram); // La composante: [RAM] GSkill Trident-Z DDR5 16GB
        System.out.println("Sa catégorie: " + ram.getCategorie()); // Sa catégorie: RAM
        ram.setPrix(100);
        ram.setRabais(0.25);
        System.out.println("La prix de la composante: " + ram.getPrix()); // La prix de la composante: 75.0
        System.out.println("Son rabais: " + ram.getRabais()); // Son rabais: 0.25
        System.out.println("Est-ce tridentzDDR5?: " + ram.estIdentique(tridentzDDR5)); // Est-ce tridentzDDR5?: true
        Composant aucun = config2.rechercher("ssd");
        System.out.println("La composante: " + aucun); // La composante: null
        commencerTest("Ajouts de composants");
        // Ajouts permis
        config2.ajouter(tridentzDDR4); // [RAM] GSkill Trident-Z DDR4 32GB ajouté à la configuration (total=545,00$)
        config2.ajouter(wdSN850x); // [SSD] Western Digital SN850X 1TB ajouté à la configuration (total=645,00$)
        gbRTX4060.setRabais(0.20);
        System.out.printf("# Rabais de %.0f%% sur %s (nouveau prix: %.2f$)\n",
                gbRTX4060.getRabais() * 100, gbRTX4060, gbRTX4060.getPrix()); // # Rabais de 20% sur [GPU] Gigabyte RTX 4060 (nouveau prix: 320,00$)
        config2.ajouter(gbRTX4060); // [GPU] Gigabyte RTX 4060 ajouté à la configuration (total=965,00$)
        System.out.println();
        System.out.println(config2);    // Build AMD Gen5 (max 1000,00$) :
                                        //  1: [CPU] AMD Ryzen 5 5700X (260,00$)
                                        //  2: [CARTE MÈRE] MSI B550 Gaming Wifi (150,00$)
                                        //  3: [RAM] GSkill Trident-Z DDR4 32GB (135,00$)
                                        //  4: [SSD] Western Digital SN850X 1TB (100,00$)
                                        //  5: [GPU] Gigabyte RTX 4060 (320,00$)
        afficherTotal(config2); // Total: 5 composants pour 1109,75$ (taxes incluses)
        // Ajouts refusés
        config2.ajouter(ryzen7800x3d); // Il y a déjà un composant de cette catégorie: [CPU] AMD Ryzen 5 5700X
        config2.ajouter(gbRTX4060); // Il y a déjà un composant de cette catégorie: [GPU] Gigabyte RTX 4060
        config1.ajouter(samsung980); // L'ajout de ce composant ferait dépasser le prix maximum: [SSD] Samsung 980 Pro 2TB
        commencerTest("Retrait de composants");
        // Retraits permis
        config2.retirer(tridentzDDR4); // [RAM] GSkill Trident-Z DDR4 32GB retiré de la configuration (total=830,00$)
        config2.retirer(gbRTX4060); // [GPU] Gigabyte RTX 4060 retiré de la configuration (total=510,00$)
        ryzen5700x.setPrix(220);
        config2.retirer(ryzen5700x); // [CPU] AMD Ryzen 5 5700X retiré de la configuration (total=250,00$)
        System.out.println();
        System.out.println(config2);    // Build AMD Gen5 (max 1000,00$) :
                                        //  1: [CARTE MÈRE] MSI B550 Gaming Wifi (150,00$)
                                        //  2: [SSD] Western Digital SN850X 1TB (100,00$)
        afficherTotal(config2); // Total: 2 composants pour 287,50$ (taxes incluses)
        // Retraits refusés
        config1.retirer(tridentzDDR4); // Composant introuvable: [RAM] GSkill Trident-Z DDR4 32GB
        config1.retirer(gbRTX4060); // Composant introuvable: [GPU] Gigabyte RTX 4060
        config2.retirer(ryzen5700x); // Composant introuvable: [CPU] AMD Ryzen 5 5700X
        commencerTest("Remplacement de composants");
        config2.remplacer(samsung980);  // [SSD] Western Digital SN850X 1TB retiré de la configuration (total=150,00$)
                                        // [SSD] Samsung 980 Pro 2TB ajouté à la configuration (total=400,00$)
        System.out.println(config2); // Build AMD Gen5 (max 1000,00$) :
                                        //  1: [CARTE MÈRE] MSI B550 Gaming Wifi (150,00$)
                                        //  2: [SSD] Samsung 980 Pro 2TB (250,00$)
        afficherTotal(config2); // Total: 2 composants pour 460,00$ (taxes incluses)
        commencerTest("Copie de configurations");
        Configuration config3 = new Configuration(config2);
        config2.getComposants()[0].setPrix(99);
        System.out.println(config2);    //Build AMD Gen5 (max 1000,00$) :
                                        //  1: [CARTE MÈRE] MSI B550 Gaming Wifi (99,00$)
                                        //  2: [SSD] Samsung 980 Pro 2TB (250,00$)
        System.out.println(config3);    //Build AMD Gen5 (copie) (max 1000,00$) :
                                        //  1: [CARTE MÈRE] MSI B550 Gaming Wifi (150,00$)
                                        //  2: [SSD] Samsung 980 Pro 2TB (250,00$)
        commencerTest("Validation finale");
        System.out.println(config1);    //Build Intel Gen13 (max 1250,00$) :
                                        //  1: [CPU] Intel Core i5-13600K (330,00$)
                                        //  2: [CARTE MÈRE] Asus ROG Strix B760 (200,00$)
                                        //  3: [RAM] GSkill Trident-Z DDR5 16GB (90,00$)
                                        //  4: [GPU] Asus RTX 4060 (460,00$)
        afficherTotal(config1); //Total: 4 composants pour 1242,00$ (taxes incluses)
        System.out.println(config2);    //Build AMD Gen5 (max 1000,00$) :
                                        //  1: [CARTE MÈRE] MSI B550 Gaming Wifi (99,00$)
                                        //  2: [SSD] Samsung 980 Pro 2TB (250,00$)
        afficherTotal(config2); //Total: 2 composants pour 401,35$ (taxes incluses)
        System.out.println(config3);    //Build AMD Gen5 (copie) (max 1000,00$) :
                                        //  1: [CARTE MÈRE] MSI B550 Gaming Wifi (150,00$)
                                        //  2: [SSD] Samsung 980 Pro 2TB (250,00$)
        afficherTotal(config3); //Total: 2 composants pour 460,00$ (taxes incluses)

    }
    private static void afficherTotal(Configuration config) {
        int nbComposants = config.getNbComposants();
        double totalAvecTvecTaxes = config.calculerTotal(0.15);
        System.out.printf("\tTotal: %d composants pour %.2f$ (taxes incluses)\n\n",
                nbComposants, totalAvecTvecTaxes);
    }
    private static int noTest = 0;
    private static void commencerTest(String description) {
        System.out.printf("\n=== Test #%d : %s ===\n\n", ++noTest, description);
    }
}
