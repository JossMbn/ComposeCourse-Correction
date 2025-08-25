package com.formation.composeformation.ui.day_2.account.model

enum class AccountDay2ContentView {
    Loading, Content
}

data class AccountDay2State(
    val contentView: AccountDay2ContentView = AccountDay2ContentView.Loading
)
