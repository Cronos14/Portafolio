package com.javatar.portafolio.features.projects

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.javatar.portafolio.R
import com.javatar.portafolio.adapters.ProjectAdapter
import com.javatar.portafolio.commons.fragments.BaseFragment
import com.javatar.portafolio.features.projects.projectdetail.ProjectDetailActivity
import com.javatar.portafolio.models.Project
import com.javatar.portafolio.utils.BUNDLE_PROJECT_DETAIL
import kotlinx.android.synthetic.main.fragment_recycler_general.*

class ProjectFragment : BaseFragment<ProjectViewModel>() {

    var projectList: ArrayList<Project> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        observeDataProject()
        viewModel.loadProject()
    }

    private fun initRecycler() {
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.setHasFixedSize(true)
        recycler.adapter = ProjectAdapter(projectList, ::clickItemListener)
    }

    private fun clickItemListener(project: Project) {
        val intent = Intent(activity, ProjectDetailActivity::class.java)
        intent.putExtra(BUNDLE_PROJECT_DETAIL, project.projectDetail)
        startActivity(intent)
    }

    private fun observeDataProject() {
        viewModel.projectLiveData?.observe(this, Observer<List<Project>> { projectAll ->
            projectAll?.apply {
                projectList.clear()
                projectList.addAll(projectAll)
                recycler?.adapter?.notifyDataSetChanged()
            }
        })
    }

    override fun initCustomViewModel() =
        ViewModelProviders.of(this, ProjectViewModelFactory()).get(ProjectViewModel::class.java)

    override fun getViewInflater(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_general, container, false)

    override fun reload() {
        viewModel.loadProject()
    }

}