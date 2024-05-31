package com.track.service;

import com.track.entity.pages.ChildPage;
import com.track.entity.pages.Page;

import java.util.List;

public interface IPageService {

    Page findByIdPage(long id);
    Page findByExternalId(String externalId);
    List<Page> findByProjectId(long projectId);
    List<Page> getAllPages();
    void createNewPage(Page page);
    void createChildPage(Long pageId, ChildPage childPage);
    void setStar(long pageId);
}
