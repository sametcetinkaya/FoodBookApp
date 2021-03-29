package com.sametcetinkaya.foodbookapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sametcetinkaya.foodbookapp.model.Food

class FoodListViewModel : ViewModel() {
    val foods = MutableLiveData<List<Food>>()
    val foodErrorMessage = MutableLiveData<Boolean>()
    val foodDownload = MutableLiveData<Boolean>()

    fun refreshData() {
        val muz = Food("Muz","100","10","1","www.test.com")
        val cilek = Food("Cilek","200","20","10","www.test.com")
        val elma = Food("Elma","300","30","3","www.test.com")

        val foodList = arrayListOf<Food>(muz,cilek,elma)

        foods.value = foodList
        foodErrorMessage.value = false
        foodDownload.value = false
    }
}