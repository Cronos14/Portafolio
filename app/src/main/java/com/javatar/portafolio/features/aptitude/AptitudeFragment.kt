package com.javatar.portafolio.features.aptitude

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.javatar.portafolio.R
import com.javatar.portafolio.adapters.TextAdapter
import com.javatar.portafolio.commons.fragments.BaseFragment
import com.javatar.portafolio.models.Aptitude
import kotlinx.android.synthetic.main.fragment_recycler_general.*

class AptitudeFragment : BaseFragment<AptitudeViewModel>() {

    var aptitudeList:ArrayList<Aptitude> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        observeDataAptitude()

        viewModel.loadAptitude()
    }

    private fun initRecycler(){
        val manager = GridLayoutManager(activity,3)

        recycler.layoutManager = manager
        recycler.setHasFixedSize(true)

        val adapter = TextAdapter(aptitudeList)

        recycler.adapter = adapter



        manager.spanSizeLookup = (object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return if(adapter.isHeader(position)) manager.spanCount else 1
            }
        })
    }

    private fun observeDataAptitude(){
        viewModel.aptitudeLiveData?.observe(this, Observer<List<Aptitude>> { aptitudeAll ->
            aptitudeAll?.apply {
                aptitudeList.clear()
                aptitudeList.addAll(aptitudeAll)
                recycler?.adapter?.notifyDataSetChanged()
            }
        })
    }

    override fun initCustomViewModel() = ViewModelProviders.of(this, AptitudeViewModelFactory()).get(AptitudeViewModel::class.java)

    override fun getViewInflater(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = inflater.inflate(R.layout.fragment_general, container, false)

    override fun reload() {
        viewModel.loadAptitude()
    }
}