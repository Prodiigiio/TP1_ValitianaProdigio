public class Configuration {
    private String DESCRIPTION;
    private double prixMax;
    private Composant[] composants;

    public Configuration(String description, double prixMax, Composant[] composants){
        setDescription(description);
        setPrixMax(prixMax);
        setComposants(composants);
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

    private Configuration copie(){
        return new Configuration(getDESCRIPTION(), getPrixMax(), getComposants());
    }

    private Configuration copierProfonde
}
