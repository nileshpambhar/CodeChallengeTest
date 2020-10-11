package com.mobiquitytest.demo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mobiquitytest.MyApp
import com.mobiquitytest.demo.R
import com.mobiquitytest.demo.data.room.entity.CityEntity
import com.mobiquitytest.demo.ui.activity.MainActivity
import com.mobiquitytest.demo.ui.adapter.CityListAdapter
import com.mobiquitytest.demo.utils.Constants.KEY_CITY
import com.mobiquitytest.demo.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Nilesh Pambhar
 */
class HomeFragment : BaseFragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var cityListAdapter: CityListAdapter
    private var mCities: List<CityEntity> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel.cityList.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                GlobalScope.launch {
                    homeViewModel.insertDefaultCity()
                }
            }
            mCities = it
            cityListAdapter.setCities(mCities)
        })
        cityListAdapter =
            CityListAdapter(MyApp.instance, object : CityListAdapter.OnAdapterClickHandler {
                override fun onItemClick(position: Int) {
                    openCityDetail(mCities[position])
                }

                override fun onRemoveClick(position: Int) {
                    GlobalScope.launch {
                        homeViewModel.deleteCity(mCities[position])
                        GlobalScope.launch(Dispatchers.Main) {
                            Toast.makeText(
                                mContext,
                                getString(R.string.bookmark_remove_success),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        root.rv_city.adapter = cityListAdapter
        return root
    }

    /**
     * open city detail when click on location
     */
    fun openCityDetail(city: CityEntity) {
        val bundle = Bundle()
        bundle.apply {
            putParcelable(KEY_CITY, city)
        }
        val fragment = CityDetailFragment()
        fragment.arguments = bundle
        (mContext as MainActivity).replaceFragment(fragment)
        (mContext as MainActivity).hideFabAndEnableBackButton()
    }
}
