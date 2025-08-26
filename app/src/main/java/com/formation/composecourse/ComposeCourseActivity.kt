package com.formation.composecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.formation.composecourse.designsystem.theme.ComposeFormationTheme
import com.formation.composecourse.domain.common.model.AppTheme
import com.formation.composecourse.ui.ComposeCourseApp
import com.formation.composecourse.ui.onboarding.OnBoardingRoot
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeCourseActivity : ComponentActivity() {

    val viewModel: ComposeCourseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()

        setContent {
            val preferences by viewModel.preferences.collectAsStateWithLifecycle()

            splashScreen.setKeepOnScreenCondition {
                preferences?.isOnBoardingCompleted == null
            }

            enableEdgeToEdge(
                navigationBarStyle = SystemBarStyle.dark(
                    Color.Transparent.toArgb()
                )
            )

            ComposeFormationTheme(
                theme = preferences?.theme ?: AppTheme.System
            ) {
                if (preferences?.isOnBoardingCompleted ?: false) {
                    ComposeCourseApp()
                } else {
                    OnBoardingRoot()
                }
            }
        }
    }
}
