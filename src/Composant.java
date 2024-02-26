public class Composant {
    private String categorie;
    private String marque;
    private String nom;
    private double prix;
    private double rabais;

    public Composant(String categorie, String marque, String nom, double prix){
        setCategorie(categorie);
        setMarque(marque);
        setNom(nom);
        setPrix(prix);
        setRabais(0.00);
    }

    private Composant(String categorie, String marque, String nom, double prix, double rabais){
            setCategorie(categorie);
            setMarque(marque);
            setNom(nom);
            setPrix(prix);
            setRabais(rabais);
    }
    public String getCategorie() {
        return this.categorie;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    public String getMarque() {
        return this.marque;
    }
    public void setMarque(String marque) {
        this.marque = marque;
    }
    public String getNom() {
        return this.nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public double getPrix() {
        return this.prix - (this.prix * getRabais());
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public double getRabais() {
        return this.rabais;
    }
    public void setRabais(double rabais) {
        if(this.rabais >= 0 && this.rabais <= 100){
            this.rabais = rabais;
        }
    }

    public Composant copier(){
        return new Composant(getCategorie(), getMarque(), getNom(), getPrix(), getRabais());
    }

    public boolean estIdentique(Composant autre){
        return getCategorie().equals(autre.getCategorie()) &&
                getMarque().equals(autre.getMarque()) &&
                getNom().equals(autre.getNom());
    }

    @Override
    public String toString() {
        return "[" + getCategorie().toUpperCase().trim() +"]" + " " + getMarque() + " " + getNom();
    }
}