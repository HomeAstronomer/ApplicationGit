package com.example.applicationgit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.leanback.widget.SearchBar
import com.example.applicationgit.ui.theme.ApplicationGitTheme
import com.example.applicationgit.ui.theme.LightGrey

class MainActivity : ComponentActivity() {
    private  val allBankViewModel:AllBanksModel by viewModels()
    private val searchBarViewModel:SearchBarModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Working!!","")
        setContent {
            ApplicationGitTheme {
                val popularBankList:List<String> = listOf("State Bank Of India","HDFC Bank","ICICI Bank","Kotak MAhindra Bank Limited","Jio Payments Bank","Punjab National Bank")
                val bankList:List<String> = listOf("HDFC","Kotak Mahindra Bank Limited","Abhyuday Bank","Adarsh Co-operative Bank Limited","ICICI Bank","Airtel Payments Bank","HDFC","Kotak Mahindra Bank Limited","Abhyuday Bank","Adarsh Co-operative Bank Limited","ICICI Bank","Airtel Payments Bank","HDFC","Kotak Mahindra Bank Limited","Abhyuday Bank","Adarsh Co-operative Bank Limited","ICICI Bank","Airtel Payments Bank")
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(7972600028,bankList,popularBankList,allBankViewModel,searchBarViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(
    mobile: Long,
    Bank: List<String>,
    popularBankList: List<String>,
    viewModel: AllBanksModel,
    searchBarViewModel: SearchBarModel
    //  BanksModel:AllBanksModel= allBankViewModel()

) {
    val bankState by viewModel.uiState.collectAsState()
    val searchBarState by searchBarViewModel.uiState.collectAsState()


    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(modifier = Modifier

            ) {

            TopAppBar(
                title = { Text("") },
                actions = {
                    // RowScope here, so these icons will be placed horizontally
                    IconButton(onClick = { /* doSomething() */ }) {
                        Row() {
                            Text(text = "Skip")
                        }
                    }
                }
            )
            LazyColumn() {

                items(1) {
                    if(searchBarState.searchBarData.inputText.compareTo("")==0){
                    Text(
                        text = "Select a bank account linked to \n+91 $mobile",
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 10.dp)
                    )

                    Text(
                        text = "Select a bank to create a UPI account for you",
                        fontSize = 22.sp,
                        modifier = Modifier.padding(start = 10.dp)
                    )}
                    SearchBar(searchBarState.searchBarData.inputText, onNameChange = {searchBarViewModel.onNameChange(it)})
                    if(searchBarState.searchBarData.inputText.compareTo("")==0){
                    Text(
                        text = "Popular banks",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    popularBanks(bankState.banksDataList)
                    Text(
                        text = "All Banks",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                    )


                }}
                items(items=bankState.banksDataList){
                    allBanks(bankName = it.BankName, BankIcon = it.iconId)

                }
            }
        }
    }

}
@Composable
fun SearchBar(name:String,onNameChange:(String)-> Unit) {


    TextField(
        value = name,
        onValueChange = onNameChange,

        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(CircleShape)
            .background(color = LightGrey)
            .clickable(enabled = true, onClickLabel = null, onClick = {}),
        placeholder={ Text(text = "Search Banks")},


        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "Search Icon"
            )
        },

        )
}

@Composable
fun popularBanks(popularBankList: List<AllBanksDataClass>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.padding(5.dp)) {
            banksImageWithText(popularBankList.get(0).iconId,popularBankList.get(0).BankName)
            banksImageWithText(popularBankList.get(1).iconId,popularBankList.get(1).BankName)
            banksImageWithText(popularBankList.get(2).iconId,popularBankList.get(2).BankName)
        }
        Row(modifier = Modifier.padding(5.dp)) {
            banksImageWithText(popularBankList.get(3).iconId,popularBankList.get(3).BankName)
            banksImageWithText(popularBankList.get(4).iconId,popularBankList.get(4).BankName)
            banksImageWithText(popularBankList.get(5).iconId,popularBankList.get(5).BankName)
        }
    }

}

@Composable
fun banksImageWithText(resourceId:Int, Bank:String) {
    IconButton(onClick = {  }, modifier = Modifier.padding(horizontal = 5.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = resourceId),
                contentDescription = "Localized description",
                contentScale=ContentScale.Crop,
                modifier = Modifier
                    .clip(RectangleShape)
                    .size(50.dp)

            )
            Text(text = Bank,Modifier.width(120.dp), textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun allBanks(bankName:String,BankIcon:Int) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = BankIcon),
            contentScale= ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .padding(5.dp),
            contentDescription = "All Banks Icon",

        )
        Text(text = bankName, fontSize = 20.sp, modifier = Modifier.padding(5.dp))
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ApplicationGitTheme {
        Greeting(7972600028,
            listOf("HDFC","Kotak Mahindra Bank Limited","Abhyuday Bank","Adarsh Co-operative Bank Limited","ICICI Bank","Airtel Payments Bank"),
            listOf("HDFC","Kotak Mahindra Bank Limited","Abhyuday Bank","Adarsh Co-operative Bank Limited","ICICI Bank","Airtel Payments Bank","HDFC","Kotak Mahindra Bank Limited","Abhyuday Bank","Adarsh Co-operative Bank Limited","ICICI Bank","Airtel Payments Bank","HDFC","Kotak Mahindra Bank Limited","Abhyuday Bank","Adarsh Co-operative Bank Limited","ICICI Bank","Airtel Payments Bank"),
            AllBanksModel(),
            SearchBarModel()
        )
    }
}
@Preview(showBackground = true)
@Composable
fun PopularBankPreview() {
    ApplicationGitTheme {
        banksImageWithText(1234,"HDFC")
    }
}
