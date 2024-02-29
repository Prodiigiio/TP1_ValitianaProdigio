public class Configuration {
    private String description;
    private double prixMax;
    private Composant[] composants;
    private final static int MAX_COMPOSANTS = 20;

    public Configuration(String description, double prixMax, Composant[] composants) {
        setDescription(description);
        setPrixMax(prixMax);
        setComposants(composants);
    }

    public Configuration(Configuration originale) {
        Composant[] composantsCopie = new Composant[MAX_COMPOSANTS];
        setDescription(originale.getDescription() + " (copie)");
        setPrixMax(originale.getPrixMax());
        setComposants(copierComposants(composantsCopie, originale.composants));
    }

    private Composant[] copierComposants(Composant[] composantsCopie, Composant[] originale) {
        for (int i = 0; i < originale.length; i++) {
            if(originale[i] != null){
                composantsCopie[i] = originale[i].copier();
            }
        }
        return composantsCopie;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public double getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(double prixMax) {
        this.prixMax = prixMax;
    }


    public Composant[] getComposants() {
        return this.composants;
    }

    public void setComposants(Composant[] composants) {
        if (isLonguerTableauTropGrand(composants)) return;   //source pour "Guard clause": mon adelphe
        this.composants = tableauComposantRearrange(composants);
    }

    private boolean isLonguerTableauTropGrand(Composant[] composants) {
        return composants.length > MAX_COMPOSANTS;
    }

    public double calculerTotal(double taxe) {
        return calculerTaxes(taxe);
    }

    private double calculerTaxes(double taxe) {
        return getCoutTotalSansTaxes() + (getCoutTotalSansTaxes() * taxe);
    }

    private double getCoutTotalSansTaxes() {
        double coutTotalSansTaxes = 0.0;
        for (int i = 0; i < getComposants().length; i++) {
            if (getComposants()[i] != null) {
                coutTotalSansTaxes += getComposants()[i].getPrix();
            }
        }
        return coutTotalSansTaxes;
    }

    public Composant rechercher(String categorie) {
        for (int i = 0; i < getComposants().length; i++) {
            if (isComposantTrouve(categorie, i))
                return getComposants()[i];
        }
        return null;
    }

    private boolean isComposantTrouve(String categorie, int i) {
        return composants[i] != null && getComposants()[i].getCategorie().equalsIgnoreCase(categorie);
    }

    public int getNbComposants() {
        int compteurComposant = 0;
        for (int i = 0; i < getComposants().length; i++) {
            if (getComposants()[i] != null)
                compteurComposant++;
        }
        return compteurComposant;
    }

    public Composant[] tableauComposantRearrange(Composant[] composant) {
        Composant[] tableauTemp = new Composant[MAX_COMPOSANTS];
        int j = 0;
        for (int i = 0; i < composant.length; i++) {
            if (composant[i] != null) {
                tableauTemp[j] = composant[i];
                j++;
            }
        }
        return tableauTemp;
    }

    public boolean ajouter(Composant composant) {
        if (isConditionsPourAjouterNonRespecte(composant)) return false; // guard clause source: mon adelphe
        if (isAjouteALaConfiguration(composant)) return true;
        actualiserComposants();
        return false;
    }

    private void actualiserComposants() {
        setComposants(tableauComposantRearrange(getComposants()));
    }

    private boolean isConditionsPourAjouterNonRespecte(Composant composant) {
        return isAtteintMaxComposants()
                || isComposantExisteDejaDansCategorie(composant)
                || isDepassePrixMaximal(composant);
    }

    private boolean isAjouteALaConfiguration(Composant composant) {
        for (int i = 0; i < getComposants().length; i++) {
            if (composants[i] == null) {
                composants[i] = composant;
                setComposants(tableauComposantRearrange(composants));
                System.out.printf("%s ajouté à la configuration (total=%.2f$)%n", getComposants()[i], getCoutTotalSansTaxes());
                return true;
            }
        }
        return false;
    }

    private boolean isDepassePrixMaximal(Composant composant) {
        if ((getCoutTotalSansTaxes() + composant.getPrix()) > getPrixMax()) {
            System.out.println("L'ajout de ce composant ferait dépasser le prix maximum: " + composant);
            return true;
        }
        return false;
    }

    private boolean isComposantExisteDejaDansCategorie(Composant composant) {
        for (int i = 0; i < getComposants().length; i++) {
            if (composants[i] != null && composants[i].getCategorie().equalsIgnoreCase(composant.getCategorie())) {
                System.out.println("Il y a déjà un composant de cette catégorie: " + getComposants()[i].toString());
                return true;
            }
        }
        return false;
    }

    private boolean isAtteintMaxComposants() {
        return getNbComposants() >= MAX_COMPOSANTS;
    }

    public boolean retirer(Composant composant) {
        for (int i = 0; i < getComposants().length; i++) {
            if (getComposants()[i] != null && getComposants()[i].estIdentique(composant)) {
                Composant composantAAfficher =  composants[i];
                composants[i] = null;
                System.out.printf("%s retiré de la configuration (total=%.2f$)%n", composantAAfficher, getCoutTotalSansTaxes());
                actualiserComposants();
                return true;
            }
        }
        System.out.println("Composant introuvable: " + composant.toString());
        return false;
    }

    public boolean remplacer(Composant composant) {
        for (int i = 0; i < getComposants().length; i++) {
            if (composants[i] != null && composants[i].getCategorie().trim().equalsIgnoreCase(composant.getCategorie().trim())) {
                System.out.println(getComposants()[i].toString() + " retiré de la configuration");
                composants[i] = composant;
                System.out.println(getComposants()[i] + " ajouté de la configuration");
                actualiserComposants();
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String specComplete = "";
        for (int i = 0; i < getComposants().length; i++) {
            if (getComposants()[i] != null) {
                specComplete += String.format("%d : %s (%.2f$)\n", (i + 1), getComposants()[i].toString(), getComposants()[i].getPrix());
            }
        }
        return String.format("%s (max %.2f$):\n%s", getDescription(), getPrixMax(), specComplete);
    }
}