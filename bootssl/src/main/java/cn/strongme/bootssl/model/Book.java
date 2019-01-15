package cn.strongme.bootssl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 * @author 阿水
 * @date 2019-01-02 20:53.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "strong", type = "books")
public class Book {

    @Id
    private String id;

    private String title;

    private String author;

    private String releaseDate;

}
