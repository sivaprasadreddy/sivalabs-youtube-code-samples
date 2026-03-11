package dev.sivalabs.demo.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface PostRepository extends JpaRepository<Post, Long> {

    //@Query("select p from Post p left join fetch p.comments")
    @EntityGraph(attributePaths = {"comments"})
    List<PostInfo> findAllBy();
}
