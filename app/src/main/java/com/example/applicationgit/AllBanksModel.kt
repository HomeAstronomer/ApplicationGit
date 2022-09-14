package com.example.applicationgit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

sealed interface BanksUIState{
    var banksDataList : List<AllBanksDataClass>
    var searchBarData:SearchBarDataClass

    data class ToUiState(
        override var banksDataList: List<AllBanksDataClass>,
        override var searchBarData:SearchBarDataClass
    ):BanksUIState
}

private  data class MainViewModelState(var banksDataList: List<AllBanksDataClass>,var searchBarData: SearchBarDataClass){
    fun toUiState():BanksUIState=BanksUIState.ToUiState(
        banksDataList=banksDataList,
        searchBarData = searchBarData
    )
}
class AllBanksModel : ViewModel() {

    private  val viewModelState= MutableStateFlow(
        MainViewModelState(
            banksDataList = listOf(),
            searchBarData = SearchBarDataClass(inputText = "", isTyping = false)
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
        val banks=listOf(
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

    fun searchedList(inputText: String): ArrayList<AllBanksDataClass> {
        val bankList=viewModelState.value.banksDataList
        val searchedList:ArrayList<AllBanksDataClass> = arrayListOf()
        if(inputText.compareTo("")==0){
            for(i in bankList){searchedList.add(i)}
        }else {
            for (i in bankList) {
                if (i.BankName.contains(inputText)) {
                    searchedList.add(i)
                }
            }
        }
        return searchedList
    }
    fun onNameChange(newName:String){
        viewModelState.update { it.copy(searchBarData = SearchBarDataClass(inputText = newName, isTyping = true)) }
    }


}