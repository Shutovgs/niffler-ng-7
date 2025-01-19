package guru.qa.niffler.api;

import guru.qa.niffler.config.Config;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiClient {
    private final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

    public final Retrofit retrofit = new Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Config.getInstance().spendUrl())
            .addConverterFactory(JacksonConverterFactory.create())
            .build();
}
