package com.track.ui.dto;

import com.track.entity.User;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class AddCommentIssueDto {

    private Long id;
    private User user;
    private LocalDateTime createDate;
    private String body;
    private boolean response;
    private Long commentId;

    private String externalIdIssue;
    private String commentBody;
    private long userId;
    private long recipientUserId;
}
