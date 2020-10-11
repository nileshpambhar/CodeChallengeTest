package com.mobiquitytest.demo.ui.fragment

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    lateinit var mContext: Context
    lateinit var progressDialog: ProgressDialog
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = ProgressDialog(mContext)
        progressDialog.isIndeterminate = true
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
    }

    fun showLoader() {
        if (!activity?.isFinishing!! && !progressDialog.isShowing) {
            progressDialog.show()
        }
    }

    fun hideLoader() {
        if (!activity?.isFinishing!! && progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }
}