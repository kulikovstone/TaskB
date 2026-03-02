package com.mkulikov.taskb.group_api.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(groupEntity: GroupEntity)

    @Update
    suspend fun update(groupEntity: GroupEntity)

    @Delete
    suspend fun delete(groupEntity: GroupEntity)

    @Query("SELECT * FROM `groups`")
    fun getGroups(): Flow<List<GroupEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM `groups` WHERE name = :name COLLATE NOCASE)")
    suspend fun groupExists(name: String): Boolean
}