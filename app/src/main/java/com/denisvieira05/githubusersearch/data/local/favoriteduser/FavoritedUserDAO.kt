package com.denisvieira05.githubusersearch.data.local.favoriteduser

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUser.tableName
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritedUserDAO {

    @Query("SELECT * FROM $tableName")
    fun getAllFavoritedUsers(): Flow<List<FavoritedUserEntity>>

    @Query("DELETE FROM $tableName WHERE remote_id = :remoteId")
    suspend fun removeFavoritedUser(remoteId: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAsFavoritedUser(favoritedUser: FavoritedUserEntity)

    @Query("SELECT * FROM $tableName WHERE remote_id = :remoteId LIMIT 1")
    suspend fun findByRemoteId(remoteId: Long): FavoritedUserEntity
}