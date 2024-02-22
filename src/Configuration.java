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
        this.composants = composants;
    }

    public double calculerTotal(double taxe) {
        double coutTotalSansTaxes = 0;
        for (int i = 0; i < getComposants().length; i++) {
            if (getComposants()[i] != null) {
                coutTotalSansTaxes += getComposants()[i].getPrix();
            }
        }
        return coutTotalSansTaxes + (coutTotalSansTaxes * taxe);
    }
}
