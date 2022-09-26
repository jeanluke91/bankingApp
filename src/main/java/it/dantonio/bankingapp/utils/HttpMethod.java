package it.dantonio.bankingapp.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HttpMethod {

    @Value("${auth-schema}")
    private String authSchema;
    @Value("${api-key}")
    private String apiKey;
    @Value("${api-url}")
    private String apiURL;


    public JSONObject doGet(String uri) throws IOException {

        HttpGet request = new HttpGet(apiURL + uri);

        request.addHeader("Auth-Schema", authSchema);
        request.addHeader("Api-Key", apiKey);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String retSrc = EntityUtils.toString(entity);
                    JSONObject result = new JSONObject(retSrc).getJSONObject("payload");

                    return result;
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    public JSONObject doPost(String uri, JSONObject payload) throws IOException {

        HttpPost post = new HttpPost(apiURL + uri);

        post.addHeader("Auth-Schema", authSchema);
        post.addHeader("Api-Key", apiKey);
        post.setEntity(new StringEntity(payload.toString()));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String retSrc = EntityUtils.toString(entity);
                    JSONObject result = new JSONObject(retSrc).getJSONObject("payload");

                    return result;
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

















  /*  public JSONObject doGet(String uri) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpEntity entity;
        HttpGet request = new HttpGet(apiURL + uri);

        request.addHeader("Auth-Schema", authSchema);
        request.addHeader("Api-Key", apiKey);

        HttpResponse response = httpClient.execute(request);
        try {
            if (response.getStatusLine().getStatusCode() == 200) {
                entity = response.getEntity();
                if (entity != null) {
                    String retSrc = EntityUtils.toString(entity);
                    JSONObject result = new JSONObject(retSrc).getJSONObject("payload");

                    return result;
                }
            }

        } catch (JSONException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            httpClient.close();
        }
        return null;

    }
*/

    public JSONObject doPost(String uri) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpEntity entity;
        HttpGet request = new HttpGet(apiURL + uri);

        request.addHeader("Auth-Schema", authSchema);
        request.addHeader("Api-Key", apiKey);

        HttpResponse response = httpClient.execute(request);
        try {
            if (response.getStatusLine().getStatusCode() == 200) {
                entity = response.getEntity();
                if (entity != null) {
                    String retSrc = EntityUtils.toString(entity);
                    JSONObject result = new JSONObject(retSrc).getJSONObject("payload");

                    return result;
                }
            }

        } catch (JSONException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            httpClient.close();
        }
        return null;

    }

}
