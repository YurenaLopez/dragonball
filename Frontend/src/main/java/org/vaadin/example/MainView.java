package org.vaadin.example;

import com.nimbusds.jose.shaded.gson.Gson;
import com.vaadin.flow.component.grid.Grid;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@Route
public class MainView extends VerticalLayout {
    Grid<Characters> grid = new Grid<>(Characters.class,false);

    public MainView(@Autowired CharactersService service) {
        // Creamos el release 1.0
        grid.addColumn(Characters::getId).setHeader("id").setAutoWidth(true);;
        grid.addColumn(Characters::getName).setHeader("name").setAutoWidth(true);;
        grid.addColumn(Characters::getKi).setHeader("ki").setAutoWidth(true);;
        grid.addColumn(Characters::getMaxKi).setHeader("maxKi").setAutoWidth(true);;
        grid.addColumn(Characters::getRace).setHeader("race").setAutoWidth(true);;
        grid.addColumn(Characters::getGender).setHeader("gender").setAutoWidth(true);;

        // Añadir el Grid al layout principal
        add(grid);
        loadCharacters();

    }
    private void loadCharacters() {
        // Llamada al controlador para obtener los datos de nationalData
        List<Characters> characters = getCharactersFromController();
        grid.setItems(characters);
    }

    private List<Characters> getCharactersFromController() {
        String url = String.format("http://localhost:8080/characters");

        try {
            // Configurar cliente HTTP
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Hacer la llamada GET
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // Convertir la respuesta JSON a una lista de NationalDataFile
                Characters[] ships = new Gson().fromJson(response.body(), Characters[].class);
                return Arrays.asList(ships);
            } else {
                // En caso de error mostrar mensaje
                System.out.println("Error al obtener datos: " + response.statusCode());
            }
        } catch (Exception e) {
            // Manejar excepcion
            e.printStackTrace();
        }

        // En caso de error
        return List.of();
    }
}


