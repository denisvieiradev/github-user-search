package com.denisvieira05.githubusersearch.ui.modules.homescreen

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import com.denisvieira05.githubusersearch.domain.usecases.GetSuggestedUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSuggestedUsersUseCase: GetSuggestedUsersUseCase,
) : ViewModel() {

    private val _searchTextState = mutableStateOf("")
    var searchText = _searchTextState.value

    private val _suggestedUsers = MutableStateFlow<List<SuggestedUser>?>(null)
    val suggestedUsers = _suggestedUsers.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    fun updateSearchText(text: String) {
        searchText = text
    }

    fun fetchSuggestedUsers() {
        viewModelScope.launch {
            _isLoading.emit(true)
            getSuggestedUsersUseCase().collect { suggestedUsers ->
                _suggestedUsers.emit(suggestedUsers)
                _isLoading.emit(false)
            }
        }
    }
}
