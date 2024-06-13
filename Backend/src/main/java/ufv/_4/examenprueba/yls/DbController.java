package ufv._4.examenprueba.yls;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.ArrayList;

@RestController
public class DbController {

    // Métodos GET para leer datos de la API
    @GetMapping("/characters")
    public ArrayList<Characters> characters() throws IOException {

        return  LeerFicheroJSON.LeerFicheroCharacters();
    }
}
