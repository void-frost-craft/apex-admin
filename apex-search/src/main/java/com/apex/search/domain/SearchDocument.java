package com.apex.search.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 搜索文档
 *
 * @author apex
 */
@Data
@Document(indexName = "apex")
public class SearchDocument implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String title;
    private String content;
    private String type;
    private LocalDateTime createTime;
}
