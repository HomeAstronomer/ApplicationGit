package com.example.applicationgit


data class AllBanksDataClass(var iconId:Int=R.drawable.bank_icon,
                             var BankName:String=""){}
data class SearchBarDataClass(var inputText:String="",
                              var isTyping:Boolean=false)
