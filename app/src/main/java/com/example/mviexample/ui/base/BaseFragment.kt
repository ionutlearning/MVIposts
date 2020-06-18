package com.example.mviexample.ui.base

import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.mviexample.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected fun showErrorMessageDialog(callback: () -> Unit) {
        val dialog = AlertDialog.Builder(requireContext())
            .setMessage(resources.getString(R.string.general_error_message))
            .setPositiveButton(resources.getString(R.string.retry), null)
            .setCancelable(false)
            .show()

        val button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        button.setOnClickListener {
            callback.invoke()
            dialog.dismiss()
        }
    }
}