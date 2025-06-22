package itis.animediary.data.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "comment_on_forum")
public class CommentOnForumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cof_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anime", nullable = false)
    private AnimeEntity anime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"user\"", nullable = false)
    private UserEntity user;

    @Builder.Default
    @Column(name = "comment_date", nullable = false, columnDefinition = "timestamp with time zone default now()")
    private Date commentDate = new Date();

    @Column(name = "comment_text", nullable = false, columnDefinition = "text")
    private String commentText;
}

