package com.sametcetinkaya.foodbookapp.servis

import com.sametcetinkaya.foodbookapp.model.Food
import io.reactivex.Single
import retrofit2.http.GET

interface BesinAPI {

    //GET, POST
    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json
    //BASE_URL ->https://raw.githubusercontent.com/
    //atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json
    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getBesin() : Single<List<Food>>


}