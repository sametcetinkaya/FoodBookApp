package com.sametcetinkaya.foodbookapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sametcetinkaya.foodbookapp.R
import com.sametcetinkaya.foodbookapp.adapter.FoodRecyclerAdapter
import com.sametcetinkaya.foodbookapp.viewmodel.FoodListViewModel
import kotlinx.android.synthetic.main.fragment_food_list.*


class FoodListFragment : Fragment() {

    private lateinit var  viewModel : FoodListViewModel
    private val recyclerFoodAdapter = FoodRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FoodListViewModel::class.java)
        viewModel.refreshData()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerFoodAdapter

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.foods.observe(viewLifecycleOwner, Observer { foods->
            foods?.let {
                recyclerView.visibility = View.VISIBLE
                recyclerFoodAdapter.foodListUpdate(foods)
            }
        })
        viewModel.foodErrorMessage.observe(viewLifecycleOwner, Observer { hata ->
            hata?.let {
                if(it){
                    besinHataMesaji.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                else{
                    besinHataMesaji.visibility = View.GONE
                }
            }
        })
        viewModel.foodDownload.observe(viewLifecycleOwner, Observer { yukleniyor->
            yukleniyor?.let {
                if(it){
                    recyclerView.visibility = View.GONE
                    besinHataMesaji.visibility = View.GONE
                    besinYukleniyor.visibility = View.VISIBLE
                }
                else {
                    besinYukleniyor.visibility = View.GONE
                }
            }
        })
    }

}