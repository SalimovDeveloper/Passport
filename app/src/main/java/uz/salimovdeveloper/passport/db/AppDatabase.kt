package uz.salimovdeveloper.passport.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.salimovdeveloper.passport.models.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun myPassportDao(): MyPassportDao

    companion object {
        private var appDatabase: AppDatabase? = null

        @Synchronized

        fun getInstance(context: Context) : AppDatabase{
            if (appDatabase == null){
                appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "Passport_room")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return appDatabase!!
        }
    }
}