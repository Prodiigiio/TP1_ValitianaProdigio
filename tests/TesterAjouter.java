import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TesterAjouter {
    @Test
    void ajouterComposantQuiMarche(){
        Composant composant = new Composant("SSD", "Marque1", "Nom1", 100.0);
        Configuration configuration = new Configuration("Description", 500.0, new Composant[20]);

        boolean result = configuration.ajouter(composant);

        assertTrue(result);
        assertEquals(1, configuration.getNbComposants());
    }
}
