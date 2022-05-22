package com.sivalabs.tcdemo.rest;

import com.sivalabs.tcdemo.infra.MockServerContainerInitializer;
import com.sivalabs.tcdemo.infra.PostgresDatabaseContainerInitializer;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.Header;
import org.mockserver.verify.VerificationTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static com.sivalabs.tcdemo.infra.MockServerContainerInitializer.mockServerContainer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration(initializers = {PostgresDatabaseContainerInitializer.class, MockServerContainerInitializer.class})
public class GithubControllerIntegrationTest {
    @Autowired
    protected MockMvc mockMvc;

    private MockServerClient mockServerClient = new MockServerClient(
            mockServerContainer.getContainerIpAddress(),
            mockServerContainer.getServerPort());

    @Test
    void shouldGetGithubUserProfile() throws Exception {
        String username = "sivaprasadreddy";
        mockGetUserFromGithub(username);
        this.mockMvc.perform(get("/api/github/users/{username}", username))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login", is(username)))
                .andExpect(jsonPath("$.name", is("K. Siva Prasad Reddy")))
                .andExpect(jsonPath("$.public_repos", is(50)))
        ;
        verifyMockServerRequest("GET", "/users/.*", 1);
    }

    private void mockGetUserFromGithub(String username) {
        mockServerClient.when(
                        request().withMethod("GET").withPath("/users/.*"))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(new Header("Content-Type", "application/json; charset=utf-8"))
                                .withBody(json(
                                        "{ " +
                                        "\"login\": \""+username+"\", " +
                                        "\"name\": \"K. Siva Prasad Reddy\", " +
                                        "\"public_repos\": 50 " +
                                        "}"))
                );
    }

    private void verifyMockServerRequest(String method, String path, int times) {
        mockServerClient.verify(
                request()
                        .withMethod(method)
                        .withPath(path),
                VerificationTimes.exactly(times)
        );
    }
}