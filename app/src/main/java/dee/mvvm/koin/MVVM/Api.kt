package dee.mvvm.koin.MVVM

import retrofit2.http.GET


interface Api {
    //for Assests values
    @GET("assets")
  suspend fun AssestsApi(
    ): Assests
}