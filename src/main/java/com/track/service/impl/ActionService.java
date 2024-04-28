package com.track.service.impl;

import com.track.entity.issue.Action;
import com.track.entity.issue.IssueAction;
import com.track.repository.ActionRepository;
import com.track.repository.IssueActionRepository;
import com.track.repository.IssueRepository;
import com.track.service.IActionService;
import com.track.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ActionService implements IActionService {
    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private IssueActionRepository issueActionRepository;

    @Autowired
    private IUserService userService;


    @Override
    public void addAction(String message, Long userId, String issueExId) {
        Action action = new Action();
        IssueAction issueAction = new IssueAction();

        action.setDescribe(message);
        action.setCreateDate(LocalDateTime.now());
        action.setCreateBy(userService.findUserById(userId));

        actionRepository.save(action);

        issueAction.setAction(action);
        issueAction.setIssues(issueRepository.findByExternalId(issueExId));
        issueAction.setCreateDate(LocalDateTime.now());

        issueActionRepository.save(issueAction);

    }
}
