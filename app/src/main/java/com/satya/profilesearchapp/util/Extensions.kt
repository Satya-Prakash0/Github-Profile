package com.satya.profilesearchapp.util

import com.satya.profilesearchapp.data.model.Item
import com.satya.profilesearchapp.data.db.RepoEntity
import com.satya.profilesearchapp.domain.model.RepoUiModel

/**
 * Extension function to convert [Item] to [RepoEntity].
 *
 * @return [RepoEntity] containing repo details mapped from [Item].
 */
fun Item.toEntity(): RepoEntity {
    return RepoEntity(
        id = id,
        name = name,
        repoURL = owner.repoURL,
        profileURL = owner.profileURL
    )
}

/**
 * Extension function to convert [RepoEntity] to [RepoUiModel].
 *
 * @return [RepoUiModel] containing repo details mapped from [RepoEntity].
 */
fun RepoEntity.toUiModel(): RepoUiModel {
    return RepoUiModel(
        id = id,
        name = name,
        repoUrl = repoURL,
        profileUrl = profileURL
    )
}

