package ufv._4.examenprueba.yls;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class LeerFicheroJSON {
    // Método para leer un archivo JSON y convertirlo a una lista de objetos NationalDataFile
    public static ArrayList<Characters> LeerFicheroCharacters() throws IOException {
        // Obtiene el archivo JSON
        InputStream inputStream = LeerFicheroJSON.class.getClassLoader().getResourceAsStream("characters.JSON");

        // Copia el archivo JSON a un File
        File personajes = new File("characters.JSON");
        FileUtils.copyInputStreamToFile(inputStream, personajes);

        // Inicializa Gson
        Gson gson = new Gson();

        // Lee el JSON desde el archivo y lo convierte a un ArrayList de NationalDataFile
        JsonReader reader = new JsonReader(new FileReader(personajes));
        Type CharacterListType = new TypeToken<ArrayList<Characters>>() {}.getType();
        ArrayList<Characters> characters = gson.fromJson(reader, CharacterListType);

        return characters;
    }

}
