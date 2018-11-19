package com.ecommonproj.barebone.data.service;

import com.ecommonproj.barebone.data.model.ContentData;
import com.ecommonproj.barebone.data.repository.ContentDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContentDataService {
    private ContentDataRepository contentDataRepository;

    @Autowired
    public ContentDataService(ContentDataRepository contentDataRepository) {
        this.contentDataRepository = contentDataRepository;
    }

    public Page<ContentData> getAllContent(Pageable pageable){
        return contentDataRepository.findAll(pageable);
    }

    public void save(ContentData contentData) {
        contentDataRepository.save(contentData);
    }
}
