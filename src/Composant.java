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
        return categorie;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    public String getMarque() {
        return marque;
    }
    public void setMarque(String marque) {
        this.marque = marque;
    }
    public String getNom() {
        return nom;
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
        return rabais;
    }
    public void setRabais(double rabais) {
        this.rabais = rabais;
    }

    public Composant copier(){
        return new Composant(getCategorie(), getMarque(), getNom(), getPrix(), getRabais());
    }

    public boolean estIdentique(Composant autre){
        if(this.categorie.equals(autre.getCategorie()))
            if(this.marque.equals(autre.getMarque()))
                if(this.nom.equals(autre.getNom()))
                    return true;
        return false;
    }

    @Override
    public String toString() {
        return "[" + getCategorie() +"]" + " " + getMarque() + " " + getNom();
    }
}
