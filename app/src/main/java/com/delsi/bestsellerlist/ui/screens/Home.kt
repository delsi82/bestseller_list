package com.delsi.bestsellerlist.ui.screens

// for a 'val' variable

// for a `var` variable also add

// or just
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.delsi.bestsellerlist.R
import com.delsi.bestsellerlist.data.Status
import com.delsi.bestsellerlist.data.vo.BestsellerType
import com.delsi.bestsellerlist.data.vo.Book
import com.delsi.bestsellerlist.ui.viewmodels.BooksViewModel
import com.delsi.bestsellerlist.utils.fake.BookFakeObj
import com.delsi.bestsellerlist.utils.fake.TypesOfBestseller

@Composable
fun Home(navController: NavController, viewModel: BooksViewModel = hiltViewModel()) {
    var items: List<BestsellerType>? = null
    var books : List<Book>? = null

    viewModel.getTypesOfBestseller()

    viewModel.typeOfBooks.collectAsState().value.let { resource ->
        when (resource.status) {
            Status.LOADING -> {
            }
            Status.ERROR -> Toast.makeText(
                LocalContext.current,
                resource.message,
                Toast.LENGTH_LONG
            )
            Status.SUCCESS -> {
                items = resource.data
                items?.let {
                    viewModel.getBooks(it.first())
                }
            }
        }

    }

    viewModel.books.collectAsState().value.let { resource ->
        when (resource.status) {
            Status.LOADING -> {
            }
            Status.ERROR -> Toast.makeText(
                LocalContext.current,
                resource.message,
                Toast.LENGTH_LONG
            )
            Status.SUCCESS -> {
                books = resource.data
                print("Trovati ${books?.size} libri")
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(stringResource(id = R.string.app_name))
            }, backgroundColor = MaterialTheme.colors.primary)
        }
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            DropDownBestSeller(items = items) {
                viewModel.getBooks(it)
            }

            BookList(books = books)
        }

    }
}


@Composable
private fun DropDownBestSeller(
    @PreviewParameter(TypesOfBestseller::class) items: List<BestsellerType>?,
    onClick : (BestsellerType) -> Unit
) {
    val textState = remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(value = false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
            .clickable { expanded = true }
    ) {
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            readOnly = true,
            enabled = false,
            label = { Text(text = stringResource(id = R.string.filter_books_type)) },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = android.R.drawable.arrow_down_float),
                    contentDescription = ""
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items?.forEachIndexed { index, bestsellerType ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    textState.value = bestsellerType.displayName ?: "No Title"
                    onClick.invoke(bestsellerType)
                }) {
                    Text(text = bestsellerType.displayName ?: "No Title")
                }
            }
        }
    }


}

@Composable
private fun BookList(books: List<Book>?) {
    
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        books?.let {
            items(it) { book->
                BookCard(book = book)
            }
        }
    }
}

@Preview
@Composable
private fun BookCard(@PreviewParameter(BookFakeObj::class) book: Book) {
    Row(modifier = Modifier
        .padding(all = 8.dp)
        .fillMaxWidth()) {

        Image(
            painter = rememberImagePainter(book.bookImage),
            contentDescription = "",
            modifier = Modifier
                // Set image size to 40 dp
                .size(width = 40.dp, height = 70.dp)
                .border(1.5.dp, Color.Gray)
        )

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = book.title ?: "No title",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.subtitle1
            )
            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = book.description ?: "",
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.body2
            )
        }
    }
}