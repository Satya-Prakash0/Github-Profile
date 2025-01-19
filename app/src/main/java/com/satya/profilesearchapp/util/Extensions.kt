package com.satya.profilesearchapp.util

import android.view.View
import android.view.animation.Animation
import android.view.animation.LayoutAnimationController
import androidx.recyclerview.widget.RecyclerView
import com.satya.profilesearchapp.data.model.Item
import com.satya.profilesearchapp.data.db.RepoEntity
import com.satya.profilesearchapp.domain.model.RepoUiModel

/**
 * Extension function to convert [Item] to [RepoEntity].
 *
 * @return [RepoEntity] containing repo details mapped from [Item].
 */
fun Item.toEntity(): RepoEntity {
    return RepoEntity(
        id = id,
        name = name,
        repoURL = owner.repoURL,
        profileURL = owner.profileURL
    )
}

/**
 * Extension function to convert [RepoEntity] to [RepoUiModel].
 *
 * @return [RepoUiModel] containing repo details mapped from [RepoEntity].
 */
fun RepoEntity.toUiModel(): RepoUiModel {
    return RepoUiModel(
        id = id,
        name = name,
        repoUrl = repoURL,
        profileUrl = profileURL
    )
}


/**
 * Fades in a view by making it visible and applying a fade-in animation.
 *
 * @param duration Duration of the animation in milliseconds. Default is 300ms.
 */
fun View.fadeIn(duration: Long = 300) {
    visibility = View.VISIBLE
    startAnimation(AnimationUtils.getFadeInAnimation(duration))
}

/**
 * Fades out a view by applying a fade-out animation and changing its visibility afterward.
 *
 * @param duration Duration of the animation in milliseconds. Default is 300ms.
 * @param gone If true, sets the view's visibility to [View.GONE] after the animation ends.
 *             If false, sets it to [View.INVISIBLE]. Default is true.
 */
fun View.fadeOut(duration: Long = 300, gone: Boolean = true) {
    startAnimation(AnimationUtils.getFadeOutAnimation(duration).apply {
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                visibility = if (gone) View.GONE else View.INVISIBLE
            }
        })
    })
}

/**
 * Adds a slide-in animation to RecyclerView items during their layout display.
 */
fun RecyclerView.addItemAnimations() {
    layoutAnimation = LayoutAnimationController(
        AnimationUtils.getSlideInAnimation()
    ).apply {
        delay = 0.1f
        order = LayoutAnimationController.ORDER_NORMAL
    }
}