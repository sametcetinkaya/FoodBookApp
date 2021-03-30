package com.sametcetinkaya.foodbookapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sametcetinkaya.foodbookapp.model.Food

class FoodDetailViewModel: ViewModel() {

    val foodLiveData = MutableLiveData<Food>()

    fun roomVerisiniAl(){
        val muz = Food("Muz","100","10","1","10","www.test.com")
        foodLiveData.value = muz
    }
}