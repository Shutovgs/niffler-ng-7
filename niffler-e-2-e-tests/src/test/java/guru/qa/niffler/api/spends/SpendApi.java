package guru.qa.niffler.api.spends;

import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import java.util.List;

public interface SpendApi {

  @POST("internal/spends/add")
  Call<SpendJson> addSpend(@Body SpendJson spend);

  @PATCH("internal/spends/edit")
  Call<SpendJson> editSpend(@Body SpendJson spend);

  @GET("internal/spends/{id}")
  Call<SpendJson> getSpend(@Path("id") String id,
                           @Query("username") String username);

  @GET("internal/spends/all")
  Call<List<SpendJson>> getSpends(@Query("username") String username,
                                  @Query("filterCurrency") CurrencyValues filterCurrency,
                                  @Query("from") String from,
                                  @Query("to") String to);

  @DELETE("internal/spends/remove")
  Call<Void> deleteSpend(@Query("username") String username,
                         @Query("ids") List<String> ids);
}
