package com.ride2go.r2gapi;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.testcontainers.containers.MySQLContainer;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ITSmoke extends SpringIntegrationTest {

    @LocalServerPort
    private int port;

    @ClassRule
    public static MySQLContainer<Ride2GoMySqlContainer> mySqlContainer = Ride2GoMySqlContainer.getInstance();

    // @Test
    public void checkHealthEndpoint() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://localhost:" + port + "/actuator/health");
        ResponseHandler<String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        try {
            String responseBody = httpclient.execute(httpget, responseHandler);
            assertThat(responseBody, is("{\"status\":\"UP\"}"));
        } finally {
            httpclient.close();
        }

    }
}
