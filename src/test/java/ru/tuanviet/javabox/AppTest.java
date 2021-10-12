package ru.tuanviet.javabox;


import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

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
        givenThat(
            get(urlEqualTo("/names"))
                .willReturn(aResponse().withBodyFile("generate-random-names/names-success.json"))
        );

        // when
        String randomName = new App().generateRandomName();

        // then
        String[] nameParts = randomName.split(" ");
        assertThat(nameParts[0]).isIn("Александр", "София");
        assertThat(nameParts[1]).isIn("Абажур", "Абажурчик");
    }
}
