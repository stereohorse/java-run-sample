package ru.tuanviet.javabox;


import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    @Rule
    public WireMockRule wm = new WireMockRule(
        options().dynamicPort()
    );

    @Test
    public void shouldGenerateRandomName() {
        // then
        final String namesServiceUri = serviceWithResponse("/names-service", "names-success.json");
        final String nounsServiceUri = serviceWithResponse("/nouns-service", "nouns-success.json");

        // when
        List<String> randomNames = new App(
            wm.baseUrl() + namesServiceUri,
            wm.baseUrl() + nounsServiceUri,
            500,
            5
        ).generateRandomName();

        // then
        assertThat(randomNames).hasSize(5);

        for (final String name : randomNames) {
            String[] nameParts = name.split(" ");
            assertThat(nameParts[0]).isIn("Александр", "София");
            assertThat(nameParts[1]).isIn("Абажур", "Абажурчик");
        }
    }

    private String serviceWithResponse(String serviceUri, String responseFile) {
        return serviceWithResponse(serviceUri, responseFile, null);
    }

    private String serviceWithResponse(String serviceUri, String responseFile, Integer timeoutMillis) {
        ResponseDefinitionBuilder response = aResponse()
            .withBodyFile("generate-random-names/" + responseFile);

        if (timeoutMillis != null) {
            response = response.withFixedDelay(timeoutMillis);
        }

        givenThat(
            get(urlEqualTo(serviceUri)).willReturn(response)
        );

        return serviceUri;
    }
}
