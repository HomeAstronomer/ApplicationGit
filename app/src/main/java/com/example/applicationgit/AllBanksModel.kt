package com.example.applicationgit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

sealed interface BanksUIState{
    var banksDataList : List<AllBanksDataClass>

    data class ToUiState(
        override var banksDataList: List<AllBanksDataClass>
    ):BanksUIState
}

private  data class MainViewModelState(var banksDataList: List<AllBanksDataClass>){
    fun toUiState():BanksUIState=BanksUIState.ToUiState(
        banksDataList=banksDataList
    )
}
class MainViewModel(): ViewModel() {
    private  val viewModelState= MutableStateFlow(
        MainViewModelState(
            banksDataList = listOf()
        )
    )

    val uiState=viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        getBanksDataList()
    }

    private fun getBanksDataList() {
        var banks=listOf(AllBanksDataClass(R.drawable.bank_icon,"HDFC"),
            AllBanksDataClass(R.drawable.bank_icon,"HDFC"),
            AllBanksDataClass(R.drawable.bank_icon,"HDFC"),
            AllBanksDataClass(R.drawable.bank_icon,"HDFC"),
            AllBanksDataClass(R.drawable.bank_icon,"HDFC")
        )
        viewModelState.update { it.copy(banksDataList = banks) }
    }


}