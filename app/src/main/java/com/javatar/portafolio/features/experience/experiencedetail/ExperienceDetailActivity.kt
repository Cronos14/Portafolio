package com.javatar.portafolio.features.experience.experiencedetail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.javatar.portafolio.R
import com.javatar.portafolio.adapters.TextAdapter
import com.javatar.portafolio.models.ExperienceDetail
import com.javatar.portafolio.utils.BUNDLE_EXPERIENCE_DETAIL
import com.javatar.portafolio.utils.Utils
import kotlinx.android.synthetic.main.activity_detail_experience.*
import kotlinx.android.synthetic.main.toolbar.*

class ExperienceDetailActivity : AppCompatActivity() {

    private var experienceDetail:ExperienceDetail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_experience)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        experienceDetail = intent?.extras?.getSerializable(BUNDLE_EXPERIENCE_DETAIL) as? ExperienceDetail

        initViews()

        initRecycler()

    }

    private fun initViews(){
        Utils.loadImageWithPicasso(experienceDetail?.icon,img_icon)
        tv_title.text = experienceDetail?.title
        tv_description.text = experienceDetail?.description
    }

    private fun initRecycler(){
        recycler_features.layoutManager = LinearLayoutManager(this)
        recycler_features.setHasFixedSize(true)
        recycler_features.adapter = TextAdapter(experienceDetail?.aptitude)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)

    }

}
