package com.javatar.portafolio.commons.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.javatar.portafolio.commons.viewmodels.BaseViewModel
import kotlinx.android.synthetic.main.error_general.view.*
import kotlinx.android.synthetic.main.fragment_general.*

open abstract class BaseFragment<T : BaseViewModel> : Fragment() {

    lateinit var viewModel: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = getViewInflater(inflater, container, savedInstanceState)

        view.btn_reintentar.setOnClickListener {
            reload()
        }

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = initCustomViewModel()

        observeSuccess()
        observeError()
        observeShowProgress()
        observeHideProgress()
    }

    open fun observeSuccess() {
        viewModel.successLiveData?.observe(this, Observer<Any> { any ->
            any?.apply {
                layout_error.visibility = View.GONE
                layout_process.visibility = View.VISIBLE
            }
        })
    }

    open fun observeError() {
        viewModel.errorLiveData?.observe(this, Observer<Throwable> { throwable ->
            throwable?.apply {
                layout_error.visibility = View.VISIBLE
                layout_process.visibility = View.GONE
            }
        })
    }

    open fun observeShowProgress() {
        viewModel.showProgressLiveData?.observe(this, Observer<Any> { any ->
            any?.apply {
                layout_progress.visibility = View.VISIBLE
            }
        })
    }

    open fun observeHideProgress() {
        viewModel.showProgressLiveData?.observe(this, Observer<Any> { any ->
            any?.apply {
                layout_progress.visibility = View.GONE
            }
        })
    }

    abstract fun initCustomViewModel(): T

    abstract fun getViewInflater(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View

    abstract fun reload()
}