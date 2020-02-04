package com.javatar.portafolio.utils

import android.widget.ImageView
import com.javatar.portafolio.R
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

interface Utils {

    companion object {
        fun loadImageWithPicasso(url: String?, imageView: ImageView) {
            Picasso.get()
                .load(url)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                    }

                    override fun onError(e: Exception?) {
                        Picasso.get()
                            .load(url)
                            .placeholder(R.drawable.ic_image_24dp)
                            .error(R.drawable.ic_image_24dp)
                            .into(imageView)
                    }
                })
        }
    }
}