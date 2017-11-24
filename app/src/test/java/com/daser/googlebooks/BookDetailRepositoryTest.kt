package com.daser.googlebooks

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.daser.googlebooks.data.Book
import com.daser.googlebooks.data.ImageLinks
import com.daser.googlebooks.data.VolumeInfo
import com.daser.googlebooks.datasource.BookDetailRepository
import com.daser.googlebooks.network.GoogleBooksService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*


/**
 * Created by Dorin on 11/24/2017.
 */

@RunWith(MockitoJUnitRunner::class)
class BookDetailRepositoryTest {

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private val mockedService = mock(GoogleBooksService::class.java)

    private val testBookId = "book_id_xyz"
    private val testBookTitle = "book_name_xyz"
    private val testBookDescription = "This is an extensive description of the book"
    private val testBookImagePath = "https://asmallorange.com/assets/img/logo/logo.png"
    private val testBookPageCount = 200
    private val testBookAuthors = Arrays.asList("Author 1", "Author 2")
    private val testBook = Book(testBookId,
            VolumeInfo(testBookTitle, testBookAuthors, testBookDescription, testBookPageCount,
                    ImageLinks(testBookImagePath)))

    private val repository: BookDetailRepository = BookDetailRepository(mockedService,
            Schedulers.trampoline(), Schedulers.trampoline())

    @Mock lateinit var observer: Observer<Book>


    @Test
    fun testGetBookDetail() {

        // when
        createDummyBookDetail()

        // given
        val liveDataBook = repository.getBookDetail(testBookId)
        liveDataBook.observeForever(observer)

        // then
        val resBook: Book? = liveDataBook.value
        assertNotNull(resBook)
        if (resBook != null) {
            assertTrue(testBookId == resBook.id)
            assertTrue(testBookTitle == resBook.volumeInfo.title)
            assertTrue(testBookImagePath == resBook.volumeInfo.imageLinks.small)
        }
    }

    private fun createDummyBookDetail() {
        `when`(mockedService.getBookDetailById(anyString()))
                .thenReturn(Single.just(testBook))
    }
}