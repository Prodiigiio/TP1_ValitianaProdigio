import java.util.Arrays;

public class Configuration {
    private String DESCRIPTION;
    private double prixMax;
    private Composant[] composants;
    private final int MAX_COMPOSANTS = 20;

    public Configuration(String description, double prixMax, Composant[] composants) {
        setDescription(description);
        setPrixMax(prixMax);
        setComposants(composants);
    }

    public Configuration(Configuration originale) {
        setDescription(originale.getDESCRIPTION());
        setPrixMax(originale.getPrixMax());
        setComposants(originale.getComposants());

        for (int i = 0; i < getComposants().length; i++) {
            if(getComposants()[i] != null){
                getComposants()[i] = originale.getComposants()[i].copier();
               // getNbComposants()[i] += composants[i].copier(); verifier plus tard
            }
        }


    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    private void setDescription(String description) {
        this.DESCRIPTION = description;
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
        if (composants.length > MAX_COMPOSANTS) {    //source pour "Guard clause": mon adelphe
            return;
        }
        this.composants = tableauComposantRearrange(composants);
    }

    public double calculerTotal(double taxe) {
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
            if (getComposants()[i] != null && getComposants()[i].getCategorie().equalsIgnoreCase(categorie))
                return getComposants()[i];
        }
        return null;
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

        if (getNbComposants() >= MAX_COMPOSANTS) // guard clause source: mon adelphe
            return false;

        for (int i = 0; i < getComposants().length; i++) {
            if (getComposants()[i] != null && getComposants()[i].getCategorie().equalsIgnoreCase(composant.getCategorie())) {
                System.out.println("Il y a déjà un composant de cette catégorie: " + getComposants()[i].toString());
                return false;
            }
        }

        if ((getCoutTotalSansTaxes() + composant.getPrix()) > getPrixMax()) {
            System.out.println("L'ajout de ce composant ferait dépasser le prix maximum: " + composant);
            return false;
        }

        for (int i = 0; i < getComposants().length; i++) {
            if (getComposants()[i] == null) {
                getComposants()[i] = composant;
                setComposants(tableauComposantRearrange(getComposants()));
                System.out.printf("%s ajouté à la configuration (total=%.2f$)%n", getComposants()[i], getCoutTotalSansTaxes());
                return true;
            }
        }
        setComposants(tableauComposantRearrange(getComposants()));
        return false;
    }

    public boolean retirer(Composant composant) {
        for (int i = 0; i < getComposants().length; i++) {
            if (getComposants()[i] != null && getComposants()[i].estIdentique(composant)) {
                System.out.printf("%s retiré de la configuration (total=%.2f$)%n", getComposants()[i], getCoutTotalSansTaxes());
                getComposants()[i] = null;
                setComposants(tableauComposantRearrange(getComposants()));
                return true;
            }
        }
        System.out.println("Composant introuvable: " + composant.toString());
        return false;
    }

    public boolean remplacer(Composant composant) {
        for (int i = 0; i < getComposants().length; i++) {
            if (composants[i] != null && composants[i].getCategorie().equalsIgnoreCase(composant.getCategorie().trim())) {
                System.out.println(getComposants()[i].toString() + " retiré de la configuration");
                composants[i] = composant;
                System.out.println(getComposants()[i] + " ajouté de la configuration");
                setComposants(tableauComposantRearrange(getComposants()));
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
        return String.format("%s (max %.2f$):\n%s", getDESCRIPTION(), getPrixMax(), specComplete);
    }
}