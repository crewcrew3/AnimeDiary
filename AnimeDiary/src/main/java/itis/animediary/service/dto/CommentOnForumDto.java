package itis.animediary.service.dto;

import itis.animediary.service.dto.anime.AnimeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentOnForumDto {
    private long id;
    private AnimeDto anime;
    private UserDto user;
    private Date commentDate;
    private String commentText;
}
