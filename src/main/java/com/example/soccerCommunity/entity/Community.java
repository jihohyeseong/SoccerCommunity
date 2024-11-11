package com.example.soccerCommunity.entity;

import com.example.soccerCommunity.dto.CommunityDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    private LocalDateTime createdAt; // 생성일자

    private LocalDateTime updatedAt; // 수정일자

    private Long viewsCount; // 조회수

    private Long likesCount; // 좋아요 수

    private Long commentsCount; // 댓글 수

    public static CommunityDto toDto(Community community) {

        return new CommunityDto(
                community.getId(),
                community.getNickname(),
                community.getTitle(),
                community.getContent(),
                community.getCreatedAt(),
                community.getUpdatedAt(),
                community.getViewsCount(),
                community.getLikesCount(),
                community.getCommentsCount()
        );
    }
}
