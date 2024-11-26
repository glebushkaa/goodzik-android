package com.uni.fine.data.mapper

import com.uni.fine.database.entity.CheckEntity
import com.uni.fine.database.entity.CheckWithIssuesAndMatches
import com.uni.fine.database.entity.IssueEntity
import com.uni.fine.database.entity.MatchEntity
import com.uni.fine.model.Check
import com.uni.fine.model.CheckInfo
import com.uni.fine.model.IssueType
import com.uni.fine.model.Plagiarism
import com.uni.fine.network.model.response.CheckInfoResponse
import com.uni.fine.network.model.response.CreatedCheckResponse
import com.uni.fine.network.model.response.MatchResponse
import com.uni.fine.ui.core.extension.toLocalDate
import com.uni.fine.ui.core.extension.toPlagiarismProbability
import kotlin.math.roundToInt

fun CheckWithIssuesAndMatches.toDomain(): CheckInfo {
    val issues = issues.map { it.toDomain() }
    return CheckInfo(
        id = check.id,
        title = check.title,
        prompt = check.prompt,
        summary = check.summary,
        createdAt = check.createdAt.toLocalDate(),
        issues = issues,
        plagiarism = matches.map { it.toDomain() },
        aiScore = check.aiScore
    )
}

fun MatchEntity.toDomain(): Plagiarism {
    return Plagiarism(
        id = id,
        url = source,
        probability = score.toPlagiarismProbability()
    )
}

fun MatchResponse.toEntity(checkId: String): MatchEntity {
    return MatchEntity(
        id = id,
        checkId = checkId,
        source = url,
        score = score
    )
}

fun CheckEntity.toDomain(): Check {
    return Check(
        id = id,
        title = title,
        summary = summary,
        createdAt = createdAt.toLocalDate()
    )
}

fun IssueEntity.toDomain(): CheckInfo.Issue {
    return CheckInfo.Issue(
        id = id,
        type = type,
        text = text,
        message = message,
        suggestion = suggestion,
        start = start,
        end = end
    )
}

fun CheckInfoResponse.toEntity(): CheckEntity {
    return CheckEntity(
        id = id,
        title = title,
        summary = summary,
        createdAt = createdAt,
        prompt = "",
        aiScore = 0
    )
}

fun CreatedCheckResponse.toEntity(): CheckEntity {
    return CheckEntity(
        id = id,
        title = title,
        prompt = prompt,
        summary = summary,
        createdAt = createdAt,
        aiScore = aiScore.roundToInt()
    )
}

fun CreatedCheckResponse.Issue.toEntity(checkId: String): IssueEntity {
    return IssueEntity(
        id = id,
        checkId = checkId,
        type = type.toDomain(),
        text = text,
        message = message,
        suggestion = suggestion,
        start = start,
        end = end
    )
}

fun CreatedCheckResponse.Issue.IssueType.toDomain(): IssueType {
    return when (this) {
        CreatedCheckResponse.Issue.IssueType.Grammar -> IssueType.Grammar
        CreatedCheckResponse.Issue.IssueType.Style -> IssueType.Style
        CreatedCheckResponse.Issue.IssueType.Vocabulary -> IssueType.Vocabulary
        CreatedCheckResponse.Issue.IssueType.Tone -> IssueType.Tone
    }
}