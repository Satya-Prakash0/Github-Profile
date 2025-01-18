package com.satya.profilesearchapp.util

import com.satya.profilesearchapp.data.model.Item
import com.satya.profilesearchapp.data.db.RepoEntity
import com.satya.profilesearchapp.domain.model.RepoUiModel

fun Item.toEntity(): RepoEntity {
    return RepoEntity(
        id = id,
        name = name,  // Use `id` as a placeholder for `ownerId`
        repoURL = owner.repoURL,
//        timestamp = System.currentTimeMillis()
    )
}

fun RepoEntity.toUiModel(): RepoUiModel {
    return RepoUiModel(
        id = id,
        name = name,
//        ownerId = ownerId,  // Keep this if it maps logically to the owner
        repoUrl = repoURL
    )
}

fun Item.toUiModel(): RepoUiModel {
    return RepoUiModel(
        id = id,
        name = name,
//        ownerId = id,  // Use `id` as a placeholder for `ownerId`
        repoUrl = owner.repoURL
    )
}

