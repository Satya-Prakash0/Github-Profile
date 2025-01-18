package com.satya.profilesearchapp.util

import com.satya.profilesearchapp.data.model.Item
import com.satya.profilesearchapp.data.db.RepoEntity
import com.satya.profilesearchapp.domain.model.RepoUiModel

fun Item.toEntity(): RepoEntity {
    return RepoEntity(
        id = id,
        name = name,
        repoURL = owner.repoURL
    )
}

fun RepoEntity.toUiModel(): RepoUiModel {
    return RepoUiModel(
        id = id,
        name = name,
        repoUrl = repoURL
    )
}

//fun Item.toUiModel(): RepoUiModel {
//    return RepoUiModel(
//        id = id,
//        name = name,
//        repoUrl = owner.repoURL
//    )
//}

