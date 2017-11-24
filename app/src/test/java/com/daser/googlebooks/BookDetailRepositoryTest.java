package com.daser.googlebooks;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.daser.googlebooks.data.Book;
import com.daser.googlebooks.data.ImageLinks;
import com.daser.googlebooks.data.VolumeInfo;
import com.daser.googlebooks.datasource.BookDetailRepository;
import com.daser.googlebooks.network.GoogleBooksService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Dorin on 11/24/2017.
 */

@RunWith(MockitoJUnitRunner.class)
class BookDetailRepositoryTest
{
    private final GoogleBooksService mockedService = mock(GoogleBooksService.class);

    private String testBookId = "book_id_xyz";
    private String testBookTitle = "book_name_xyz";
    private String testBookDescription = "This is an extensive description of the book";
    private String testBookImagePath = "https://asmallorange.com/assets/img/logo/logo.png";
    private int testBookPageCount = 200;
    private List<String> testBookAuthors = Arrays.asList("Author 1", "Author 2");
    private Book testBook = new Book(testBookId,
            new VolumeInfo(testBookTitle, testBookAuthors, testBookDescription, testBookPageCount,
                    new ImageLinks(testBookImagePath)));

    private BookDetailRepository repository;


    @Before
    public void setUp()
    {
        repository = new BookDetailRepository(mockedService);
    }


    @Test
    public void testGetBookDetail()
    {
        // when
        createDummyBoobDetail();
        Observer<Book> observer = new Observer<Book>()
        {
            @Override
            public void onChanged(@Nullable Book book)
            {
                assertNotNull(book);
                assertTrue(testBookId.equals(book.getId()));
                assertTrue(testBookTitle.equals(book.getVolumeInfo().getTitle()));
                assertTrue(testBookImagePath.equals(book.getVolumeInfo().getImageLinks().getSmall()));
                // etc
            }
        };

        // given
        repository.getBookDetail(testBookId).observeForever(observer);

        // then // check is in the observer
    }

    private void createDummyBoobDetail()
    {
        when(mockedService.getBookDetailById(anyString()))
            .thenReturn(Single.just(testBook));
    }
}