package com.example.applicationgit

import android.graphics.drawable.VectorDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applicationgit.R.drawable.search_icon
import com.example.applicationgit.ui.theme.ApplicationGitTheme
import com.example.applicationgit.ui.theme.LightGrey
import com.example.applicationgit.ui.theme.Teal200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationGitTheme {
                val popularBankList:List<String> = listOf("State Bank Of India","HDFC Bank","ICICI Bank","Kotak MAhindra Bank Limited","Jio Payments Bank","Punjab National Bank")
                val bankList:List<String> = listOf("HDFC","Kotak Mahindra Bank Limited","Abhyuday Bank","Adarsh Co-operative Bank Limited","ICICI Bank","Airtel Payments Bank","HDFC","Kotak Mahindra Bank Limited","Abhyuday Bank","Adarsh Co-operative Bank Limited","ICICI Bank","Airtel Payments Bank","HDFC","Kotak Mahindra Bank Limited","Abhyuday Bank","Adarsh Co-operative Bank Limited","ICICI Bank","Airtel Payments Bank")
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(7972600028,bankList,popularBankList)
                }
            }
        }
    }
}

@Composable
fun Greeting(mobile: Long,Bank:List<String>,popularBankList:List<String>) {
    Surface() {

        Column(modifier = Modifier
            .fillMaxSize()
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
            Text(text = "Select a bank account linked to \n+91 $mobile",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start=10.dp))

            Text(text = "Select a bank to create a UPI account for you",
                fontSize = 22.sp,
                modifier = Modifier.padding(start=10.dp))
            SearchBar()
            Text(text = "Popular banks",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start=10.dp))
            popularBanks(popularBankList)
            Text(text = "All Banks",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp, top = 5.dp))
            allBanks(Bank)




        }

        
    }

}
@Composable
fun SearchBar(

){
    var text by remember { mutableStateOf("Search Bank") }

    TextField(value = text,
        onValueChange = { text = it },
        Modifier
            .fillMaxWidth()

            .padding(10.dp)
            .clip(CircleShape)
            .background(color = LightGrey)
            .clickable(enabled = true, onClickLabel = null, onClick = {text=""}),
        leadingIcon = {Icon(painter = painterResource(id =R.drawable.search_icon ) ,
            contentDescription = "Search Icon") },

    )
}

@Composable
fun popularBanks(popularBankList: List<String>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.padding(5.dp)) {
            banksImageWithText(1223,popularBankList.get(0))
            banksImageWithText(1223,popularBankList.get(1))
            banksImageWithText(1223,popularBankList.get(2))
        }
        Row(modifier = Modifier.padding(5.dp)) {
            banksImageWithText(1223,popularBankList.get(3))
            banksImageWithText(1223,popularBankList.get(4))
            banksImageWithText(1223,popularBankList.get(5))
        }
    }

}

@Composable
fun banksImageWithText(resourceId:Int, Bank:String) {
    IconButton(onClick = { /* doSomething() */ }, modifier = Modifier.padding(horizontal = 5.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.bank_icon ),
                contentDescription = "Localized description",
                modifier = Modifier
                    .clip(RectangleShape)
                    .size(50.dp)

            )
            Text(text = Bank,Modifier.width(120.dp), textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun allBanks(bankName:List<String>) {
    LazyColumn(
        Modifier
            .padding(start = 10.dp)
            .fillMaxWidth().fillMaxHeight())
    {
        items(items=bankName) {bankNames->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.bank_icon),
                    modifier = Modifier
                        .size(40.dp)
                        .padding(5.dp),
                    contentDescription = "All Banks Icon"
                )
                Text(text = bankNames, fontSize = 20.sp, modifier = Modifier.padding(5.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ApplicationGitTheme {
        Greeting(7972600028, listOf("HDFC","Kotak Mahindra Bank Limited","Abhyuday Bank","Adarsh Co-operative Bank Limited","ICICI Bank","Airtel Payments Bank"),listOf("HDFC","Kotak Mahindra Bank Limited","Abhyuday Bank","Adarsh Co-operative Bank Limited","ICICI Bank","Airtel Payments Bank","HDFC","Kotak Mahindra Bank Limited","Abhyuday Bank","Adarsh Co-operative Bank Limited","ICICI Bank","Airtel Payments Bank","HDFC","Kotak Mahindra Bank Limited","Abhyuday Bank","Adarsh Co-operative Bank Limited","ICICI Bank","Airtel Payments Bank"))
    }
}
@Preview(showBackground = true)
@Composable
fun PopularBankPreview() {
    ApplicationGitTheme {
        banksImageWithText(1234,"HDFC")
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview(){
    ApplicationGitTheme {
        SearchBar()
    }
}