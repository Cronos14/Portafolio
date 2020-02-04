package com.javatar.portafolio.features.projects.projectdetail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.javatar.portafolio.R
import com.javatar.portafolio.adapters.FeatureAdapter
import com.javatar.portafolio.adapters.GalleryImageAdapter
import com.javatar.portafolio.models.ProjectDetail
import com.javatar.portafolio.utils.BUNDLE_PROJECT_DETAIL
import com.javatar.portafolio.utils.Utils
import kotlinx.android.synthetic.main.activity_detail_project.*
import kotlinx.android.synthetic.main.toolbar.*

class ProjectDetailActivity : AppCompatActivity() {

    private var projectDetail: ProjectDetail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_project)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        projectDetail = intent?.extras?.getSerializable(BUNDLE_PROJECT_DETAIL) as? ProjectDetail

        initViews()

        initRecyclerFeatures()

        initRecyclerImages()

    }

    private fun initViews() {
        Utils.loadImageWithPicasso(projectDetail?.smallIcon, img_small_icon)
        Utils.loadImageWithPicasso(projectDetail?.icon, img_icon)
        tv_title.text = projectDetail?.title
        tv_description.text = projectDetail?.description
    }

    private fun initRecyclerFeatures() {
        recycler_features.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        recycler_features.setHasFixedSize(true)
        recycler_features.adapter = FeatureAdapter(projectDetail?.features)
    }

    private fun initRecyclerImages() {
        recycler_images.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        recycler_images.setHasFixedSize(true)
        recycler_images.adapter = GalleryImageAdapter(projectDetail?.images)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)

    }

}
