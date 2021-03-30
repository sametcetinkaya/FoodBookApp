package com.sametcetinkaya.foodbookapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sametcetinkaya.foodbookapp.R
import com.sametcetinkaya.foodbookapp.viewmodel.FoodDetailViewModel
import kotlinx.android.synthetic.main.fragment_food_detail.*


class FoodDetailFragment : Fragment() {

    private lateinit var viewModel : FoodDetailViewModel
    private var besinId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FoodDetailViewModel::class.java)
        viewModel.roomVerisiniAl()

        arguments?.let{
            besinId = FoodDetailFragmentArgs.fromBundle(it).besinId
            println(besinId)
        }

        observeLiveData()

    }

    fun observeLiveData () {
        viewModel.foodLiveData.observe(viewLifecycleOwner, Observer { besin->
            besin?.let {
                foodName.text = it.foodName
                foodCal.text = it.foodCal
                foodCarbohydrate.text = it.foodCarbohydrate
                foodFat.text = it.foodFat
                foodProtein.text = it.foodProtein
            }
        })
    }
}