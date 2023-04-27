package com.denisvieira05.githubusersearch.data.local.favoriteduser

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUser.tableName

@Dao
interface FavoritedUserDAO {

    @Query("SELECT * FROM $tableName")
    suspend fun getAllFavoritedUsers(): List<FavoritedUserEntity>

    @Query("DELETE FROM $tableName WHERE remoteId = :remoteId")
    suspend fun removeFavoritedUser(remoteId: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAsFavoritedUser(favoritedUser: FavoritedUserEntity)

    @Query("SELECT * FROM $tableName WHERE remoteId = :remoteId LIMIT 1")
    suspend fun findByRemoteId(remoteId: Long): FavoritedUserEntity
}