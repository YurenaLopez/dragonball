package ufv._4.examenprueba.yls;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public class DbController {

    // Métodos GET para leer datos de la API
    @GetMapping("/characters")
    public ArrayList<Characters> characters() throws IOException {

        return  LeerFicheroJSON.LeerFicheroCharacters();
    }

    @PostMapping("/characters")
    public ArrayList<Characters> createCharacters(@RequestBody Characters nuevoDato) throws IOException {
        ArrayList<Characters> datos = LeerFicheroJSON.LeerFicheroCharacters();

        // GeneraMOS un nuevo ID para la nueva entrada
        if (nuevoDato.getId() == null || nuevoDato.getId().isEmpty()) {
            nuevoDato.setId(UUID.randomUUID().toString());
        }
        // Añadimos los datos nuevos al fichero existente
        datos.add(nuevoDato);

        // Convertimos la lista actualizada a JSON manteniendo el formato
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(datos);

        // Escribimos el JSON actualizado en el archivo
        File file = new File("characters.JSON");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(json); // Escribir el JSON actualizado
        fileWriter.flush();
        fileWriter.close();

        return datos;
    }
}
