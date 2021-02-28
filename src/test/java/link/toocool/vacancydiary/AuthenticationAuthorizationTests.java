package link.toocool.vacancydiary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import link.toocool.vacancydiary.dto.TokenDTO;
import link.toocool.vacancydiary.dto.authentication.AuthenticationRequestDTO;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;

import static java.lang.String.valueOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(value = {"classpath:initial-data/schema_after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = {"classpath:initial-data/schema_before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AuthenticationAuthorizationTests {

    public static final Integer USER1_ID = 1;

    public static final Integer USER2_ID = 2;

    public static final String USER1_EMAIL = "user1@gmail.com";

    public static final String ADMIN1_EMAIL = "admin1@gmail.com";

    public static final String USER1_PASSWORD = "user1";

    public static final String ADMIN1_PASSWORD = "admin1";

    public static final String GET_USER_INFO_URL = "http://localhost:{port}/api/v1/users/{user_id}";

    public static final String GET_TOKEN_URL = "http://localhost:{port}/api/v1/auth/login";

    public static final String GET_VACANCIES_URL = "http://localhost:{port}/api/v1/users/{user_id}/vacancies";

    public static final String GET_USERS_URL = "http://localhost:{port}/api/v1/users";

    @Value("${jwt.header}")
    private String authorizationHeader;

    @LocalServerPort
    private int port;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void givenUserDoesNotAuthenticated_whenGetProtectedPage_then403IsReceived() throws IOException {

        // Given
        HttpUriRequest request = new HttpGet(GET_USER_INFO_URL.replace("{port}", valueOf(port))
                .replace("{user_id}", valueOf(USER1_ID)));

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_FORBIDDEN));
    }

    @Test
    public void givenUserDoesNotAuthorized_whenGetToken_then200IsReceived()
            throws IOException {

        // Given
        HttpUriRequest request = getTokenRequest(USER1_EMAIL, USER1_PASSWORD);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void givenUserAuthorized_whenGetVacancies_then200IsReceived() throws IOException {

        // Given
        HttpUriRequest tokenRequest = getTokenRequest(USER1_EMAIL, USER1_PASSWORD);
        HttpResponse httpTokenResponse = HttpClientBuilder.create().build().execute(tokenRequest);
        String tokenDtoJson = new String(httpTokenResponse.getEntity().getContent().readAllBytes());
        String token = objectMapper.readValue(tokenDtoJson, TokenDTO.class).getToken();
        HttpUriRequest vacanciesRequest = RequestBuilder.create("GET")
                .setUri(GET_VACANCIES_URL
                        .replace("{port}", valueOf(port))
                        .replace("{user_id}", valueOf(USER1_ID)))
                .setHeader(authorizationHeader, token)
                .build();

        // When
        HttpResponse httpVacanciesResponse = HttpClientBuilder.create().build().execute(vacanciesRequest);

        // Then
        assertThat(
                httpVacanciesResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void givenUserAuthorized_whenGetVacanciesAnotherUser_then403IsReceived() throws IOException {

        // Given
        HttpUriRequest tokenRequest = getTokenRequest(USER1_EMAIL, USER1_PASSWORD);
        HttpResponse httpTokenResponse = HttpClientBuilder.create().build().execute(tokenRequest);
        String tokenDtoJson = new String(httpTokenResponse.getEntity().getContent().readAllBytes());
        String token = objectMapper.readValue(tokenDtoJson, TokenDTO.class).getToken();
        HttpUriRequest vacanciesRequest = RequestBuilder.create("GET")
                .setUri(GET_VACANCIES_URL
                        .replace("{port}", valueOf(port))
                        .replace("{user_id}", valueOf(USER2_ID)))
                .setHeader(authorizationHeader, token)
                .build();

        // When
        HttpResponse httpVacanciesResponse = HttpClientBuilder.create().build().execute(vacanciesRequest);

        // Then
        assertThat(
                httpVacanciesResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_FORBIDDEN));
    }

    @Test
    public void givenAdminAuthorized_whenGetVacanciesAnotherUser_then200IsReceived() throws IOException {

        // Given
        HttpUriRequest tokenRequest = getTokenRequest(ADMIN1_EMAIL, ADMIN1_PASSWORD);
        HttpResponse httpTokenResponse = HttpClientBuilder.create().build().execute(tokenRequest);
        String tokenDtoJson = new String(httpTokenResponse.getEntity().getContent().readAllBytes());
        String token = objectMapper.readValue(tokenDtoJson, TokenDTO.class).getToken();
        HttpUriRequest vacanciesRequest = RequestBuilder.create("GET")
                .setUri(GET_VACANCIES_URL
                        .replace("{port}", valueOf(port))
                        .replace("{user_id}", valueOf(USER2_ID)))
                .setHeader(authorizationHeader, token)
                .build();

        // When
        HttpResponse httpVacanciesResponse = HttpClientBuilder.create().build().execute(vacanciesRequest);

        // Then
        assertThat(
                httpVacanciesResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void givenUserAuthorized_whenGetUsers_then403IsReceived() throws IOException {

        // Given
        HttpUriRequest tokenRequest = getTokenRequest(USER1_EMAIL, USER1_PASSWORD);
        HttpResponse httpTokenResponse = HttpClientBuilder.create().build().execute(tokenRequest);
        String tokenDtoJson = new String(httpTokenResponse.getEntity().getContent().readAllBytes());
        String token = objectMapper.readValue(tokenDtoJson, TokenDTO.class).getToken();
        HttpUriRequest vacanciesRequest = RequestBuilder.create("GET")
                .setUri(GET_USERS_URL
                        .replace("{port}", valueOf(port)))
                .setHeader(authorizationHeader, token)
                .build();

        // When
        HttpResponse httpVacanciesResponse = HttpClientBuilder.create().build().execute(vacanciesRequest);

        // Then
        assertThat(
                httpVacanciesResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_FORBIDDEN));
    }

    @Test
    public void givenAdminAuthorized_whenGetUsers_then200IsReceived() throws IOException {

        // Given
        HttpUriRequest tokenRequest = getTokenRequest(ADMIN1_EMAIL, ADMIN1_PASSWORD);
        HttpResponse httpTokenResponse = HttpClientBuilder.create().build().execute(tokenRequest);
        String tokenDtoJson = new String(httpTokenResponse.getEntity().getContent().readAllBytes());
        String token = objectMapper.readValue(tokenDtoJson, TokenDTO.class).getToken();
        HttpUriRequest vacanciesRequest = RequestBuilder.create("GET")
                .setUri(GET_USERS_URL
                        .replace("{port}", valueOf(port)))
                .setHeader(authorizationHeader, token)
                .build();

        // When
        HttpResponse httpVacanciesResponse = HttpClientBuilder.create().build().execute(vacanciesRequest);

        // Then
        assertThat(
                httpVacanciesResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));
    }

    private HttpUriRequest getTokenRequest(String email, String password) throws JsonProcessingException {
        AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO();
        authenticationRequestDTO.setEmail(email);
        authenticationRequestDTO.setPassword(password);
        String json = objectMapper.writeValueAsString(authenticationRequestDTO);
        return RequestBuilder.create("POST")
                .setUri(GET_TOKEN_URL.replace("{port}", valueOf(port)))
                .setEntity(new StringEntity(json, ContentType.APPLICATION_JSON))
                .build();
    }
}
