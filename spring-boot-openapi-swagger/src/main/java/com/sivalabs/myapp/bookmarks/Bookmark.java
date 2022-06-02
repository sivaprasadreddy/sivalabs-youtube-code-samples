package com.sivalabs.myapp.bookmarks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "bookmarks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookmark_id_generator")
    @SequenceGenerator(name = "bookmark_id_generator", sequenceName = "bookmark_id_seq")
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Title can't be empty")
    private String title;

    @Column(nullable = false)
    @NotEmpty(message = "URL can't be empty")
    private String url;

}
