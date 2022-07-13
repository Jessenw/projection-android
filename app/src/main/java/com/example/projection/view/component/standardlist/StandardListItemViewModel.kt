package com.example.projection.view.component.standardlist

interface StandardListItemViewModel {
    val title: String

    fun tapped()
}

class SimpleStandardListItemViewModel(override val title: String): StandardListItemViewModel {
    override fun tapped() {}
}
