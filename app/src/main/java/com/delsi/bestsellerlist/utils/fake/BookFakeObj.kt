package com.delsi.bestsellerlist.utils.fake

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.delsi.bestsellerlist.data.vo.Book

class BookFakeObj : PreviewParameterProvider<Book> {
    override val values: Sequence<Book>
        get() = sequenceOf(
            Book(
                title = "BILLY SUMMERS",
                ageGroup = "",
                amazonProductUrl = "",
                articleChapterLink = "",
                asterisk = 0,
                author = "Stephen King",
                bookImage = "https://storage.googleapis.com/du-prd/books/images/9781982173616.jpg",
                bookImageHeight = 0,
                bookImageWidth = 0,
                bookReviewLink = "",
                bookUri = "",
                buyLinks = null,
                contributor = "",
                contributorNote = "",
                dagger = 0,
                description = "A killer for hire who only takes out bad guys seeks redemption as he does one final job.",
                firstChapterLink = "",
                isbns = null,
                price = "",
                primaryIsbn10 = "",
                primaryIsbn13 = "",
                publisher = "",
                rank = 0,
                rankLastWeek = 0,
                sundayReviewLink = "",
                weeksOnList = 0
            )
        )
}