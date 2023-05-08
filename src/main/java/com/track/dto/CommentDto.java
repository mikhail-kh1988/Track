package com.track.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private String externalIdIssue;
    private String commentBody;
    private long userId;

}
