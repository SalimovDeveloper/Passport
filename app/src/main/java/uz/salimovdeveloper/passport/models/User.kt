package uz.salimovdeveloper.passport.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class User(

    @PrimaryKey(autoGenerate = true)
    var id:Int,

    var name:String,
    var surname:String,
    var brith_day:String,
    var seriya:String,
    var imagePath:String
//    var tugilgan_yili:String,
//    var berilgan_sana:String,
//    var tugash_sana:String
) : Serializable
