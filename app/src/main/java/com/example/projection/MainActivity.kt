package com.example.projection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.projection.model.ProjectPreview
import com.example.projection.ui.theme.ProjectionTheme
import com.example.projection.view.ProjectList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProjectionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ProjectList(SampleData.projectPreviewSample)
                }
            }
        }
    }

    object SampleData {
        // A list of sample project preview data
        val projectPreviewSample = listOf(
            ProjectPreview(
                "[GB] GMK Fleuriste | The set is Live! Jan 25-Feb 25",
                "Jebus"
            ),
            ProjectPreview(
                "[GB] GMK Yugo | Completed",
                "Konstantin"
            ),
            ProjectPreview(
                "[GB] PLX | 60% Keyboard (PRE-ORDERS OPEN)",
                "peacdesign"
            ),
            ProjectPreview(
                "[GB] Historia Artisan Box | 98% SHIPPED!!!",
                "yasintahir"
            ),
            ProjectPreview(
                "[GB] Pizza65 R2 | The Made In Italy minimalistic 65% is back | Production",
                "PizzaKeyboards"
            ),
            ProjectPreview(
                "[GB] Lavender KBD67Lite",
                "adi"
            ),
            ProjectPreview(
                "[GB] Nazaré 1-60 W1 by Nazaré Engineering",
                "Invezting"
            ),
            ProjectPreview(
                "[GB] MW Alpenglow",
                "crystal"
            ),
            ProjectPreview(
                "[GB] ePbt Be The One - Over. Waiting for ePbt",
                "zekth"
            ),
            ProjectPreview(
                "[GB] GMK Classic Blue - Shipping!",
                "ARSLOCK"
            ),
            ProjectPreview(
                "[GB] ePBT Superstar | Live 2/12 -3/12",
                "marshmellohs"
            ),
            ProjectPreview(
                "[GB] HEAVY METAL KEYBOARDS [SHIPPING]",
                "PlastikSchnittstelle"
            )
        )
    }
}