package com.uni.fine.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.uni.fine.database.entity.CheckEntity
import com.uni.fine.database.entity.CheckWithIssues
import com.uni.fine.database.entity.IssueEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckDao {

    @Query("SELECT * FROM check_entity")
    fun getChecks(): Flow<List<CheckEntity>>

    @Upsert
    suspend fun upsertCheck(entity: CheckEntity)

    @Upsert
    suspend fun upsertIssues(entities: List<IssueEntity>)

    @Query("SELECT * FROM check_entity WHERE id = :id")
    fun getCheckWithIssuesById(id: String): Flow<CheckWithIssues>

    @Upsert
    suspend fun upsertChecks(entities: List<CheckEntity>)

    @Transaction
    suspend fun upsertCheck(
        check: CheckEntity,
        issues: List<IssueEntity>
    ) {
        upsertCheck(check)
        upsertIssues(issues)
    }
}