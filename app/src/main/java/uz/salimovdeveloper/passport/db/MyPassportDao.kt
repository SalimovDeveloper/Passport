package uz.salimovdeveloper.passport.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.salimovdeveloper.passport.models.User

@Dao
interface MyPassportDao {

    @Insert
    fun addPassport(user: User)

    @Query("select * from user")
    fun getPassport():Array<User>
}