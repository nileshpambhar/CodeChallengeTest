package com.mobiquitytest.demo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mobiquitytest.demo.R
import com.mobiquitytest.demo.data.room.entity.CityEntity
import com.mobiquitytest.demo.databinding.FragmentCityBinding
import com.mobiquitytest.demo.utils.Constants.KEY_CITY
import com.mobiquitytest.demo.viewmodels.CityViewModel

/**
 * Created by Nilesh Pambhar
 */
class CityDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentCityBinding
    private lateinit var cityViewModel: CityViewModel
    private lateinit var city: CityEntity
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cityViewModel = ViewModelProvider(this).get(CityViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_city,
            container,
            false
        )
        arguments.let {
            city = it?.getParcelable(KEY_CITY)!!
        }
        getCityDetail()
        return binding.root
    }

    /**
     * getCityDetail, API call to get detail - Nilesh Pambhar
     */
    private fun getCityDetail() {
        showLoader()
        cityViewModel.getTodayWeather(city.latitude, city.longitude)
        cityViewModel.status?.observe(viewLifecycleOwner, Observer {
            when (it) {
                1 -> {
                    hideLoader()
                    //cityViewModel.todayWeatherResponse
                    binding.data = cityViewModel.todayWeatherResponse
                }
                0 -> {
                    hideLoader()
                }
                else -> {
                    hideLoader()
                }
            }
        })
    }
}
