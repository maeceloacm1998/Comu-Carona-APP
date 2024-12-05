package com.app.comu_carona.utils

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.navigation.NavBackStackEntry

object AnimatedUtils {
    private const val ANIMATED_DELAY = 200

    /**
     * Returns a fade in transition with a slide from the left.
     * @return the fade in transition with a slide from the left.
     */
    fun animatedTransitionFadeIn(): (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) {
        return {
            fadeIn(animationSpec = tween(ANIMATED_DELAY)) + slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(durationMillis = ANIMATED_DELAY),
            )
        }
    }

    /**
     * Returns a fade out transition with a slide to the right.
     * @return the fade out transition with a slide to the right.
     */
    fun animatedTransitionFadeOut(): (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) {
        return {
            fadeOut(animationSpec = tween(ANIMATED_DELAY)) + slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(durationMillis = ANIMATED_DELAY),
            )
        }
    }

    /**
     * Returns a fade in transition with a slide from the left and a fade out transition with a slide to the right.
     * @return the fade in transition with a slide from the left and a fade out transition with a slide to the right.
     */
    fun <S> animatedTransitionPage(): AnimatedContentTransitionScope<S>.() -> ContentTransform = {
        slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(durationMillis = 200),
        ).togetherWith(
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(durationMillis = 200),
            )
        )
    }
}