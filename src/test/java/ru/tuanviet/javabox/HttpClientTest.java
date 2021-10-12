package ru.tuanviet.javabox;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;


public class HttpClientTest {

    @Rule
    public WireMockRule wm = new WireMockRule(
        options().dynamicPort()
    );

    @Test
    public void shouldReturnServiceResponseForJsonObject() {
        // given
        final String testServiceUri = serviceWithResponse("test-service-success.json");

        // when
        Data response = new HttpClient().fetch(wm.baseUrl() + testServiceUri, Data.class);

        // then
        assertThat(response.getField1()).isEqualTo("value1");
        assertThat(response.getField2()).isEqualTo(42);
    }

    @Test
    public void shouldReturnServiceResponseForJsonArray() {
        // given
        final String testServiceUri = serviceWithResponse("test-service-array-success.json");

        // when
        TypeReference<List<String>> listType = new TypeReference<List<String>>() {
        };
        List<String> response = new HttpClient().fetch(wm.baseUrl() + testServiceUri, listType);

        // then
        assertThat(response).isEqualTo(asList("value1", "value2"));
    }

    @Test
    public void shouldWorkWithSlowServices() {
        // given
        final String testServiceUri = serviceWithResponse("test-service-success.json", 300);

        // when
        Data response = HttpClient.withTimeout(350).fetch(
            wm.baseUrl() + testServiceUri,
            Data.class
        );

        // then
        assertThat(response.getField1()).isEqualTo("value1");
        assertThat(response.getField2()).isEqualTo(42);
    }

    private String serviceWithResponse(String responseFile) {
        return serviceWithResponse(responseFile, null);
    }

    private String serviceWithResponse(String responseFile, Integer timeoutMillis) {
        final String testServiceUri = "/test-service";
        ResponseDefinitionBuilder response = aResponse()
            .withBodyFile("http-client/" + responseFile);

        if (timeoutMillis != null) {
            response = response.withFixedDelay(timeoutMillis);
        }

        givenThat(
            get(urlEqualTo(testServiceUri)).willReturn(response)
        );

        return testServiceUri;
    }
}

class Data {
    private String field1;
    private Integer field2;

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public Integer getField2() {
        return field2;
    }

    public void setField2(Integer field2) {
        this.field2 = field2;
    }
}
