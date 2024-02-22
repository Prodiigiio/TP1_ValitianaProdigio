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
        return composants;
    }

    public void setComposants(Composant[] composants) {
        if(this.composants.length > MAX_COMPOSANTS) //source pour "Guard clause": mon adelphe
            return;
        this.composants = composants;
    }

    public double calculerTotal(double taxe) {
        return getCoutTotalSansTaxes() + (getCoutTotalSansTaxes() * taxe);
    }

    private double getCoutTotalSansTaxes() {
        double coutTotalSansTaxes = 0;
        for (int i = 0; i < getComposants().length; i++) {
            if (getComposants()[i] != null) {
                coutTotalSansTaxes += getComposants()[i].getPrix();
            }
        }
        return coutTotalSansTaxes;
    }

    public Composant rechercher(String categorie){
        for (int i = 0; i < getComposants().length; i++) {
            if(getComposants()[i] != null)
                if(getComposants()[i].getCategorie().equals(categorie))
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

    public boolean ajouter(Composant composant){
        if(getNbComposants() >= getComposants().length) // guard clause source: mon adelphe
            return false;
        if((getCoutTotalSansTaxes() + composant.getPrix()) > getPrixMax())
            return false;
        for (int i = 0; i < getComposants().length; i++) {
            if(getComposants()[i].getCategorie().equals(composant.getCategorie()))
                return false;
        }
        for (int i = 0; i < getComposants().length; i++) {
            if(getComposants()[i] == null){
                getComposants()[i] = composant;
                return true;
            }
        }
        return false;
    }

    public boolean retirer(Composant composant){
        for (int i = 0; i < getComposants().length; i++) {
            if(getComposants()[i].equals(composant)) {
                getComposants()[i] = null;
                return true;
            }
        }
        return false;
    }

    public boolean remplacer(Composant composant){
        for (int i = 0; i < getComposants().length; i++) {
            if(getComposants()[i].getCategorie().equals(composant.getCategorie())){
                getComposants()[i] = composant;
                return true;
            }
        }
        return false;
    }
}
