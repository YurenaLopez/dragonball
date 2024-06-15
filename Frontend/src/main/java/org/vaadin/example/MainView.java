package org.vaadin.example;

import com.nimbusds.jose.shaded.gson.Gson;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@Route
public class MainView extends VerticalLayout {

    private Grid<Characters> grid = new Grid<>(Characters.class);

    public MainView(@Autowired CharactersService service) {
        grid.setColumns("id", "name", "ki", "maxKi", "race", "gender");
        // Configurar columnas del Grid
        grid.addColumn(Characters::getId).setHeader("ID").setAutoWidth(true);
        grid.addColumn(Characters::getName).setHeader("Nombre").setAutoWidth(true);
        grid.addColumn(Characters::getKi).setHeader("Ki").setAutoWidth(true);
        grid.addColumn(Characters::getMaxKi).setHeader("Ki Máximo").setAutoWidth(true);
        grid.addColumn(Characters::getRace).setHeader("Raza").setAutoWidth(true);
        grid.addColumn(Characters::getGender).setHeader("Género").setAutoWidth(true);

        // Configurar botón Generar PDF
        PDFManager pdfManager = new PDFManager();
        grid.addColumn(new NativeButtonRenderer<>("Generar", characters -> {
            pdfManager.GenerarPDF(characters.getName(), characters);
        }));

        // Añadir el Grid al layout principal
        add(grid);
        loadCharacters();
    }

    private void loadCharacters() {
        // Obtener datos del servicio o controlador
        List<Characters> characters = getCharactersFromController();
        grid.setItems(characters);
    }

    private List<Characters> getCharactersFromController() {
        String url = "http://localhost:8080/characters";

        try {
            // Configurar cliente HTTP
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Hacer la llamada GET
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // Convertir la respuesta JSON a una lista de Characters
                Characters[] charactersArray = new Gson().fromJson(response.body(), Characters[].class);
                return Arrays.asList(charactersArray);
            } else {
                // En caso de error mostrar mensaje
                System.out.println("Error al obtener datos: " + response.statusCode());
            }
        } catch (Exception e) {
            // Manejar excepción
            e.printStackTrace();
        }

        // En caso de error
        return List.of();
    }
}
