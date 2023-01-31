package com.example.projection.view.components.standardlist

import androidx.lifecycle.LiveData

interface StandardListViewModel {
    val dataSource: LiveData<List<StandardListItemViewModel>>
}
