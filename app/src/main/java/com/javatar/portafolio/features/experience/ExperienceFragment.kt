package com.javatar.portafolio.features.experience

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.javatar.portafolio.R
import com.javatar.portafolio.adapters.ExperienceAdapter
import com.javatar.portafolio.commons.fragments.BaseFragment
import com.javatar.portafolio.features.experience.experiencedetail.ExperienceDetailActivity
import com.javatar.portafolio.models.Experience
import com.javatar.portafolio.utils.BUNDLE_EXPERIENCE_DETAIL
import kotlinx.android.synthetic.main.fragment_recycler_general.*

class ExperienceFragment : BaseFragment<ExperienceViewModel>() {

    var experienceList: ArrayList<Experience> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initRecycler()

        observeDataExperience()

        viewModel.loadExperience()

    }

    private fun initRecycler() {
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.setHasFixedSize(true)
        recycler.adapter = ExperienceAdapter(experienceList, ::clickItemListener)
    }

    private fun clickItemListener(experience: Experience) {
        val intent = Intent(activity, ExperienceDetailActivity::class.java)
        intent.putExtra(BUNDLE_EXPERIENCE_DETAIL, experience.experienceDetail)
        startActivity(intent)
    }

    private fun observeDataExperience() {
        viewModel.experienceLiveData?.observe(this, Observer<List<Experience>> { experienceAll ->
            experienceAll?.apply {
                experienceList.clear()
                experienceList.addAll(experienceAll)
                recycler?.adapter?.notifyDataSetChanged()
            }
        })
    }

    override fun initCustomViewModel() = ViewModelProviders.of(
        this,
        ExperienceViewModelFactory()
    ).get(ExperienceViewModel::class.java)

    override fun getViewInflater(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_general, container, false)

    override fun reload() {
        viewModel.loadExperience()
    }
}