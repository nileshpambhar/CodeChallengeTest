package com.mobiquitytest.demo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobiquitytest.demo.R
import kotlinx.android.synthetic.main.fragment_help.view.*

/**
 * Created by Nilesh Pambhar
 */
class HelpFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_help, container, false)
        root.web_help.loadUrl("file:///android_asset/help.html")
        return root
    }

}
