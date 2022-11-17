package uz.salimovdeveloper.passport.adapters

import android.view.View
import uz.salimovdeveloper.passport.models.User

interface RvClick {
    fun itemClick(user: User, view: View)
}