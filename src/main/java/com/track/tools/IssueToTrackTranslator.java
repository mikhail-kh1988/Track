package com.track.tools;

import com.track.dto.output.*;
import com.track.entity.issue.*;

import java.util.ArrayList;
import java.util.List;

public class IssueToTrackTranslator {

    public static Track getTrack(Issue issue){

        Track track = new Track();
        List<Activity> activityList = new ArrayList<>();
        List<Note> noteList = new ArrayList<>();
        List<File> fileList = new ArrayList<>();
        List<Track> trackList = new ArrayList<>();
        List<PlaningTime> planingList = new ArrayList<>();

        for (IssueComment ci:issue.getIssueComment()){
            Note note = new Note();
            note.setBody(ci.getComments().getBody());
            note.setLogin(ci.getComments().getUser().getLogin());
            note.setFullName(ci.getComments().getUser().getName());
            note.setResponse(ci.getComments().isResponse());
            note.setCreateDate(ci.getComments().getCreateDate());

            noteList.add(note);
        }

        // add comments into track json
        track.setNotes(noteList);

        for (IssueAction issueAction: issue.getIssueActions()){
            Activity activity = new Activity();
            activity.setAction(issueAction.getAction().getDescribe());
            activity.setCreateDate(issueAction.getAction().getCreateDate());

            activityList.add(activity);
        }

        // add activity into track json
        track.setActivities(activityList);

        for (IssueTimeCost issueTimeCost: issue.getIssueTimeCosts()){
            PlaningTime planingTime = new PlaningTime();
            planingTime.setUserFio(issueTimeCost.getTimeCost().getCreateBy().getName());
            planingTime.setTime("--fklsjd--");
            planingTime.setComment(issueTimeCost.getTimeCost().getComment());
            planingTime.setDateStart(issueTimeCost.getTimeCost().getDateStart());
            planingTime.setDateStop(issueTimeCost.getTimeCost().getDateStop());

            planingList.add(planingTime);
        }

        // add time cost int trac json
        track.setPlaningTimes(planingList);

        for (IssueAttachment ia: issue.getIssueAttachments()){
            File file = new File();
            file.setName(ia.getAttachment().getName());
            file.setCreateDate(ia.getAttachment().getCreateDate());
            file.setPath(ia.getAttachment().getPath());

            fileList.add(file);
        }

        // add attachments into track json
        track.setFiles(fileList);

        // add bind track into json

        for (IssueBind bi : issue.getIssuesListBind()) {

            Track trackBind = new Track();

            trackBind = IssueToTrackTranslator.getTrack(bi.getChildIssue());

            trackList.add(trackBind);
        }

        track.setBindTrack(trackList);

        // -----------------
        track.setExternalId(issue.getExternalId());
        track.setTrackName(issue.getTrackName());
        track.setShortDescription(issue.getShortDescription());
        track.setDescription(issue.getDescriptionBody());
        track.setPriority(issue.getPriority());
        switch (issue.getPriority()){
            case (1) :
                track.setPriorityName("CRITICAL");
                break;
            case (2) :
                track.setPriorityName("HIGH");
                break;
            case (3) :
                track.setPriorityName("MEDIUM");
                break;
            case (4) :
                track.setPriorityName("LOW");
                break;
            default:
                track.setProjectName("UNKNOWN");
        }

        if (issue.getStatus() != null) {
            track.setStatus(issue.getStatus().getName());
            track.setStatusId(issue.getStatus().getId());
        }
        if (issue.getProject() != null) {
            track.setProjectName(issue.getProject().getName());
            track.setProjectId(issue.getProject().getId());
        }
        track.setCreateDate(issue.getCreateDate());
        track.setLastChangeDate(issue.getLastChangeDate());
        track.setStartDate(issue.getStartDate());
        track.setEndDate(issue.getEndDate());
        track.setLose(issue.isLose());
        track.setClosed(issue.isClosed());
        track.setParent(issue.isParent());
        if (issue.getCategory() != null) {
            track.setCategoryName(issue.getCategory().getName());
            track.setCategoryId(issue.getCategory().getId());
        }
        if(issue.getAssign() != null) {
            track.setAssignName(issue.getAssign().getName());
            track.setAssignId(issue.getAssign().getId());
        }
        if (issue.getAssignGroup() != null) {
            track.setAssignGroupName(issue.getAssignGroup().getName());
            track.setAssignGroupId(issue.getAssignGroup().getId());
        }
        track.setVersion(issue.getVersion());
        track.setState("issue.getState()");
        track.setTimeCostName("---");
        track.setPlaningTimeCost(issue.getPlaningTimeCost());
        track.setActualTimeCost(issue.getActualTimeCost());

        return track;
    }

}
