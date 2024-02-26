public class Configuration {
    private String DESCRIPTION;
    private double prixMax;
    private Composant[] composants;
    private final int MAX_COMPOSANTS = 20;


    public Configuration(String description, double prixMax, Composant[] composants){
        setDescription(description);
        setPrixMax(prixMax);
        setComposants(composants);
    }

    public Configuration(Configuration originale){
        setDescription(originale.getDESCRIPTION());
        setPrixMax(originale.getPrixMax());
        setComposants(originale.getComposants());
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
        if(composants.length > MAX_COMPOSANTS) //source pour "Guard clause": mon adelphe
            return;
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

    public Composant rechercher(String categorie){
        for (int i = 0; i < getComposants().length; i++) {
            if(getComposants()[i] != null && getComposants()[i].getCategorie().equalsIgnoreCase(categorie))
                return getComposants()[i];
        }
        return null;
    }
    public int getNbComposants(){
        int compteurComposant = 0;
        for (int i = 0; i < getComposants().length; i++) {
            if(getComposants()[i] !=null)
                compteurComposant++;
        }
        return compteurComposant;
    }

    public Composant[] tableauComposantRearrange(Composant[] composant){
        Composant[] tableauTemp = new Composant[MAX_COMPOSANTS];
        int j = 0;
        for (int i = 0; i < composant.length; i++) {
            if(composant[i] != null){
                tableauTemp[j] = composant[i];
                j++;
            }
        }
        return tableauTemp;
    }

    public boolean ajouter(Composant composant){

        if(getNbComposants() >= MAX_COMPOSANTS) // guard clause source: mon adelphe
            return false;

        for (int i = 0; i < getComposants().length; i++) {
            if(getComposants()[i] != null && getComposants()[i].getCategorie().equalsIgnoreCase(composant.getCategorie()))
                return false;
        }

        if((getCoutTotalSansTaxes() + composant.getPrix()) > getPrixMax())
            return false;


        for (int i = 0; i < getComposants().length; i++) {
            if(getComposants()[i] == null){
                getComposants()[i] = composant;
                setComposants(tableauComposantRearrange(getComposants()));
                return true;
            }
        }
        setComposants(tableauComposantRearrange(getComposants()));
        return false;
    }



    public boolean retirer(Composant composant){
        for (int i = 0; i < getComposants().length; i++) {
            if(getComposants()[i] != null && getComposants()[i].estIdentique(composant)){
                getComposants()[i] = null;
                setComposants(tableauComposantRearrange(getComposants()));
                return true;
            }
            if(getComposants()[i] != null && !(getComposants()[i].equals(composant))){
                System.out.println("Composant introuvable: " + getComposants()[i].toString());
            }
        }
        setComposants(tableauComposantRearrange(getComposants()));
        return false;
    }

    public boolean remplacer(Composant composant){
        for (int i = 0; i < getComposants().length; i++) {
            if(getComposants()[i] != null && getComposants()[i].getCategorie().equalsIgnoreCase(composant.getCategorie())){
                getComposants()[i] = null;
                getComposants()[i] = composant;
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
