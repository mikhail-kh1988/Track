package com.track.service.impl;

import com.track.entity.pages.ChildPage;
import com.track.entity.pages.Page;
import com.track.entity.pages.PageChildPage;
import com.track.repository.ChildPageRepository;
import com.track.repository.PageChildPageRepository;
import com.track.repository.PageRepository;
import com.track.service.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PageService implements IPageService {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private ChildPageRepository childPageRepository;

    @Autowired
    private PageChildPageRepository pageChildPageRepository;

    @Override
    public Page findByIdPage(long id) {
        return pageRepository.findById(id).get();
    }

    @Override
    public Page findByExternalId(String externalId) {
        return null;
    }

    @Override
    public List<Page> findByProjectId(long projectId) {
        return pageRepository.findByProjectId(projectId);
    }

    @Override
    public List<Page> getAllPages() {
        List<Page> pageList = new ArrayList<>();

        for (Page page: pageRepository.findAll())
            pageList.add(page);

        return pageList;
    }

    @Override
    public void createNewPage(Page page) {
        pageRepository.save(page);
    }

    @Override
    public void createChildPage(Long pageid, ChildPage childPage) {
        Page page = pageRepository.findById(pageid).get();
        PageChildPage pageChildPage = new PageChildPage();

        pageChildPage.setPages(page);
        pageChildPage.setChildPages(childPage);
        pageChildPage.setCreateDate(LocalDateTime.now());

        pageChildPageRepository.save(pageChildPage);

    }

    @Override
    public void setStar(long pageId) {

        Page page = pageRepository.findById(pageId).get();

        page.setStared(true);

        pageRepository.save(page);

    }
}
