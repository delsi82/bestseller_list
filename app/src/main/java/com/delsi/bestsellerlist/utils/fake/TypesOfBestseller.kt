package com.delsi.bestsellerlist.utils.fake

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.delsi.bestsellerlist.data.vo.BestsellerType

class TypesOfBestseller : PreviewParameterProvider<List<BestsellerType>?> {
    override val values: Sequence<List<BestsellerType>?>
        get() = sequenceOf(
            listOf(
                BestsellerType(
                    displayName = "test",
                    listNameEncoded = "",
                    newestPublishedDate = "",
                    oldestPublishedDate = "",
                    updated = ""
                ),
                BestsellerType(
                    displayName = "test2",
                    updated = "",
                    oldestPublishedDate = "",
                    newestPublishedDate = "",
                    listNameEncoded = ""
                )
            )
        )
}