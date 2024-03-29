import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TesterAjouter {
    @Test
    void ajouterComposantQuiMarche(){
        Composant composant = new Composant("SSD", "Marque1", "Nom1", 100.0);
        Configuration configuration = new Configuration("Description", 500.0, new Composant[20]);

        boolean result = configuration.ajouter(composant);

        assertTrue(result);
        assertEquals(1, configuration.getNbComposants());
    }

    @Test
    void ajouterComposantMemeCategorieQuiMarchePas() {
        Composant composant1 = new Composant("SSD", "Marque1", "Nom1", 100.0);
        Composant composant2 = new Composant("SSD", "Marque2", "Nom2", 150.0);
        Configuration configuration = new Configuration("Description", 500.0, new Composant[20]);
        configuration.ajouter(composant1);

        boolean result = configuration.ajouter(composant2);

        assertFalse(result);
        assertEquals(1, configuration.getNbComposants());
    }

    @Test
    void ajouterComposantDepassePrixMaxMarchePas() {
        Composant composant = new Composant("GPU", "Marque1", "Nom1", 600.0);
        Configuration configuration = new Configuration("Description", 500.0, new Composant[20]);

        boolean result = configuration.ajouter(composant);

        assertFalse(result);
        assertEquals(0, configuration.getNbComposants());
    }

    @Test
    void ajouterComposantDepasseNbMaxMarchePas() {
        Composant composant = new Composant("CPU", "Marque1", "Nom1", 100.0);
        Configuration configuration = new Configuration("Description", 50000.0, new Composant[20]);
        for (int i = 0; i < 20; i++) {
            configuration.ajouter(new Composant("RAM" + i, "Marque", "Nom" + i, 50.0));
        }

        boolean result = configuration.ajouter(composant);

        assertFalse(result);
        assertEquals(20, configuration.getNbComposants());
    }
}