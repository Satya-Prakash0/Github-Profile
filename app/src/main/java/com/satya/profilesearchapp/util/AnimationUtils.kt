package com.satya.profilesearchapp.util

import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation

/**
 * A utility object for creating reusable animations such as fade-in, fade-out, and slide-in.
 * These animations can be applied to views to enhance user experience.
 */
object AnimationUtils {
    fun getFadeInAnimation(duration: Long = 300): Animation {
        return AlphaAnimation(0f, 1f).apply {
            this.duration = duration
            interpolator = DecelerateInterpolator()
        }
    }

    fun getFadeOutAnimation(duration: Long = 300): Animation {
        return AlphaAnimation(1f, 0f).apply {
            this.duration = duration
            interpolator = AccelerateInterpolator()
        }
    }

    fun getSlideInAnimation(duration: Long = 300): Animation {
        return TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 1f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f
        ).apply {
            this.duration = duration
            interpolator = DecelerateInterpolator()
        }
    }
}