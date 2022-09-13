package com.example.applicationgit

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
class AllBanksModel(): ViewModel() {
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
        var banks=listOf(
            AllBanksDataClass(R.drawable.hdfc,"HDFC"),
            AllBanksDataClass(R.drawable.kotak_bank,"Kotak Mahindra Bank"),
            AllBanksDataClass(R.drawable.sbi,"State Bank of India(SBI)"),
            AllBanksDataClass(R.drawable.pnb,"Punjab National Bank"),
            AllBanksDataClass(R.drawable.icici,"ICICI"),
            AllBanksDataClass(R.drawable.hdfc,"HDFC"),
            AllBanksDataClass(R.drawable.kotak_bank,"Kotak Mahindra Bank"),
            AllBanksDataClass(R.drawable.sbi,"State Bank of India(SBI)"),
            AllBanksDataClass(R.drawable.pnb,"Punjab National Bank"),
            AllBanksDataClass(R.drawable.icici,"ICICI")
        )
        viewModelState.update { it.copy(banksDataList = banks) }
    }


}