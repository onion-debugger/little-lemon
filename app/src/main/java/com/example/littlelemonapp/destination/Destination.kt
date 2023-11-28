package com.example.littlelemonapp.destination

import com.example.littlelemonapp.R
import com.example.littlelemonapp.util.HOME
import com.example.littlelemonapp.util.ONBOARDING
import com.example.littlelemonapp.util.PROFILE

interface Destination {
    val route: String
    val title: Int
}

object Home: Destination {
    override val route: String = HOME
    override val title: Int = R.string.home
}

object Profile: Destination {
    override val route: String = PROFILE
    override val title: Int = R.string.profile
}

object Onboarding: Destination {
    override val route: String = ONBOARDING
    override val title: Int = R.string.onboarding
}