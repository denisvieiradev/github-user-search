package com.denisvieira05.githubusersearch.data.local.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserDAO
import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserEntity

@Database(
    entities = [
        FavoritedUserEntity::class,
    ], version = 1, exportSchema = false
)
abstract class RoomDataBaseFactory : RoomDatabase() {

    abstract fun favoritedUserDAO(): FavoritedUserDAO

}