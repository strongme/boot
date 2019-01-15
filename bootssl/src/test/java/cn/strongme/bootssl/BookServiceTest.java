package cn.strongme.bootssl;

import cn.strongme.bootssl.model.Book;
import cn.strongme.bootssl.service.BookService;
import io.github.benas.randombeans.api.EnhancedRandom;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.elasticsearch.client.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2019-01-02 21:19.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootsslApplication.class)
@Slf4j
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Before
    public void before() {
        elasticsearchTemplate.deleteIndex(Book.class);
        elasticsearchTemplate.createIndex(Book.class);
        elasticsearchTemplate.putMapping(Book.class);
        elasticsearchTemplate.refresh(Book.class);
    }

    @Test
    public void testEsSetting() {
        Client client = elasticsearchTemplate.getClient();
        client.settings().getAsGroups().forEach((s, settings) -> {
            log.info("key: {}, value: {}", s, settings);
        });
    }

    @Test
    public void testRandomBook() {
        IntStream.range(0, 10).forEach(count -> {
            Book book = EnhancedRandom.random(Book.class, "id");
            bookService.save(book);
        });
        assertThat(Lists.newArrayList(bookService.findAll()).size()).isEqualTo(10);

    }

    @Test
    public void testSave() {

        Book book = new Book("1001", "Elasticsarch Basics", "Rambabu Posa", "23-FEB-2017");
        Book testBook = bookService.save(book);

        assertThat(testBook).isNotNull();
        assertThat(testBook.getTitle()).isEqualTo(book.getTitle());
        assertThat(testBook.getAuthor()).isEqualTo(book.getAuthor());
        assertThat(testBook.getReleaseDate()).isEqualTo(book.getReleaseDate());

    }

    @Test
    public void testFindOne() {
        Book book = new Book("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
        bookService.save(book);

        Optional<Book> testBook = bookService.findOne(book.getId());

        assertThat(testBook.isPresent()).isEqualTo(true);
        assertThat(testBook.get()).isNotNull();
        assertThat(testBook.get().getTitle()).isEqualTo(book.getTitle());
        assertThat(testBook.get().getAuthor()).isEqualTo(book.getAuthor());
        assertThat(testBook.get().getReleaseDate()).isEqualTo(book.getReleaseDate());

    }

    @Test
    public void testFindByTitle() {
        Book book = new Book("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
        bookService.save(book);

        List<Book> byTitle = bookService.findByTitle(book.getTitle());
        assertThat(byTitle.size()).isEqualTo(1);

    }

    @Test
    public void testFindByAuthor() {

        List<Book> bookList = new ArrayList<>();

        bookList.add(new Book("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017"));
        bookList.add(new Book("1002", "Apache Lucene Basics", "Rambabu Posa", "13-MAR-2017"));
        bookList.add(new Book("1003", "Apache Solr Basics", "Rambabu Posa", "21-MAR-2017"));
        bookList.add(new Book("1007", "Spring Data + ElasticSearch", "Rambabu Posa", "01-APR-2017"));
        bookList.add(new Book("1008", "Spring Boot + MongoDB", "Mkyong", "25-FEB-2017"));

        for (Book book : bookList) {
            bookService.save(book);
        }


        Page<Book> byAuthor = bookService.findByAuthor("Rambabu Posa", PageRequest.of(0, 10));
        assertThat(byAuthor.getTotalElements()).isEqualTo(4);

        Page<Book> byAuthor2 = bookService.findByAuthor("Mkyong", PageRequest.of(0, 10));
        assertThat(byAuthor2.getTotalElements()).isEqualTo(1);

    }

    @Test
    public void testDelete() {
        Book book = new Book("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
        bookService.save(book);
        bookService.delete(book);
        Optional<Book> testBook = bookService.findOne(book.getId());
        assertThat(testBook.isPresent()).isEqualTo(false);
        testBook.ifPresent(book1 -> assertThat(book1).isNotNull());
    }


}
