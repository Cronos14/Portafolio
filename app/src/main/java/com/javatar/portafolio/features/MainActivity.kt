package com.javatar.portafolio.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.javatar.portafolio.R
import com.javatar.portafolio.adapters.ViewPagerAdapter
import com.javatar.portafolio.features.aptitude.AptitudeFragment
import com.javatar.portafolio.features.experience.ExperienceFragment
import com.javatar.portafolio.features.projects.ProjectFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        initViewPager()
        initTabsIcons()
    }

    private fun initViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ExperienceFragment(), "Experiencia")
        adapter.addFragment(AptitudeFragment(), "Aptitudes")
        adapter.addFragment(ProjectFragment(), "Proyectos")
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = adapter.count
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun initTabsIcons() {
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_experience_24dp)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_aptitude_24dp)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_projects_24dp)
    }
}
