package com.shreyas.nytimes.di.modules

import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module
abstract class PicassoModule {

    companion object {
        @Provides
        internal fun providePicasso(context: Context): Picasso {
            return Picasso.Builder(context).build()
        }
    }
}