/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.reloj_lasecuela.presentation

import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.reloj_lasecuela.R
import com.example.reloj_lasecuela.presentation.theme.Reloj_lasecuelaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.ElKirbo)
        textView.setOnClickListener {
            val fadeOutAnimation = AlphaAnimation(1.0f, 0.0f) // Animación de fundido saliente
            fadeOutAnimation.duration = 1000 // Duración del fundido saliente
            fadeOutAnimation.fillAfter = true // Mantén la opacidad final después del fundido

            val fadeInAnimation = AlphaAnimation(0.0f, 1.0f) // Animación de fundido entrante
            fadeInAnimation.duration = 1000 // Duración del fundido entrante
            fadeInAnimation.fillAfter = true // Mantén la opacidad final después del fundido

            fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    // No se necesita implementación
                }

                override fun onAnimationEnd(animation: Animation) {
                    textView.startAnimation(fadeInAnimation) // Inicia el fundido entrante después de que el fundido saliente haya finalizado
                }

                override fun onAnimationRepeat(animation: Animation) {
                    // No se necesita implementación
                }
            })

            textView.startAnimation(fadeOutAnimation) // Inicia el fundido saliente
        }


    }
}

@Composable
fun WearApp(greetingName: String) {
    Reloj_lasecuelaTheme {
        /* If you have enough items in your list, use [ScalingLazyColumn] which is an optimized
         * version of LazyColumn for wear devices with some added features. For more information,
         * see d.android.com/wear/compose.
         */
        Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background),
                verticalArrangement = Arrangement.Center
        ) {
            Greeting(greetingName = greetingName)
        }
    }
}

@Composable
fun Greeting(greetingName: String) {
    Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
            text = stringResource(R.string.hello_world, greetingName)
    )
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp("Preview Android")
}