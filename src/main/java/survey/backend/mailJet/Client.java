package survey.backend.mailJet;

import lombok.Builder;
import okhttp3.OkHttpClient;

@Builder
public class Client {

    private static final String defaultBaseUrl = "https://api.mailjet.com";

    /**
     * base Url for all API calls
     */
    @Builder.Default
    private String baseUrl = defaultBaseUrl;

    /**
     * Bearer token used for SMS Api calls
     */
    private String bearerAccessToken;

    /**
     * API key to authenticate Email Api calls
     */
    private String apiKey;

    /**
     * API secret key to authenticate Email Api calls
     */
    private String apiSecretKey;

    /**
     * If set to the customly created OkHttp client, mailJet client will use provided client
     * Instead of creating the new one internally
     * use this to add custom calls interceptors/logging/etc
     */
    private OkHttpClient okHttpClient;
}



