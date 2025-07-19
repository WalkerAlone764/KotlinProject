package org.example.project

import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.android_logo
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import kotlinproject.composeapp.generated.resources.kotlin_logo
import kotlinproject.composeapp.generated.resources.www_jetbrains_com_kotlin_multiplatform_
import org.jetbrains.compose.resources.DrawableResource

data class TechStackData(
    val id: DrawableResource
)

val techStackDataset = listOf(

    TechStackData(
        id = Res.drawable.kotlin_logo
    ),
    TechStackData(
      id = Res.drawable.android_logo
    ),
    TechStackData(
        id = Res.drawable.compose_multiplatform
    ),
    TechStackData(
        id = Res.drawable.www_jetbrains_com_kotlin_multiplatform_
    )
)