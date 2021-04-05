package com.sametcetinkaya.foodbookapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sametcetinkaya.foodbookapp.model.Food
import com.sametcetinkaya.foodbookapp.servis.FoodAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FoodListViewModel : ViewModel() {
    val foods = MutableLiveData<List<Food>>()
    val foodErrorMessage = MutableLiveData<Boolean>()
    val foodDownload = MutableLiveData<Boolean>()

    private val foodApiService = FoodAPIService()
    private val disposable = CompositeDisposable()

    fun refreshData() {
        verileriInternettenAl()
    }

    private fun verileriInternettenAl(){
        foodDownload.value = true

        //IO, Default, UI
        disposable.add(
                foodApiService.getData()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object  : DisposableSingleObserver<List<Food>>(){
                            override fun onSuccess(t: List<Food>) {
                                //Başarılı olursa
                                foods.value = t
                                foodErrorMessage.value = false
                                foodDownload.value = false
                            }

                            override fun onError(e: Throwable) {
                                //Hata alırsak
                                foodErrorMessage.value = false
                                foodDownload.value = false
                                e.printStackTrace()
                            }

                        })
        )
    }

}