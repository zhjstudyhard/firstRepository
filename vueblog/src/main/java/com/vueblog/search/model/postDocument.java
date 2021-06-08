package com.vueblog.search.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-17-09-18
 */
@Data
@Document(indexName = "post", createIndex = true)
public class postDocument implements Serializable {
    @Id
    private Integer id;

    @Field(type = FieldType.Text, searchAnalyzer = "ik_smart", analyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Integer)
    private Integer authorId;

    @Field(type = FieldType.Keyword)
    private String authorName;

    private Integer categoryId;
    @Field(type = FieldType.Keyword)
    private String categoryName;

    private Integer level;
    private Boolean recomment;

    private Integer commentCount;
    private Integer viewCount;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date created;


}
