package com.example.projection.view.component.standardlist

import androidx.lifecycle.LiveData

interface StandardListViewModel {
    val dataSource: LiveData<List<StandardListItemViewModel>>
}
