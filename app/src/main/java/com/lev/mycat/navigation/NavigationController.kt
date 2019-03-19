package com.lev.mycat.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.lev.mycat.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationController
@Inject
constructor() {

    private lateinit var navigationController: NavController

    fun setUpController(activity: AppCompatActivity) {
        navigationController =
            Navigation.findNavController(activity, R.id.nav_host_fragment)
    }

    fun navigate(actionId: Int) = navigationController.navigate(actionId)

    fun navigateUp() = navigationController.navigateUp()
}