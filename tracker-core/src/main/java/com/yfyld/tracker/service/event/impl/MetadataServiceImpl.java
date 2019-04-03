package com.yfyld.tracker.service.event.impl;

import com.github.pagehelper.PageHelper;
import com.yfyld.tracker.dto.GetMetadataListParam;
import com.yfyld.tracker.mapper.MetadataMapper;
import com.yfyld.tracker.model.Metadata;
import com.yfyld.tracker.service.event.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "MetadataService")
public class MetadataServiceImpl implements MetadataService {
    @Autowired
    private MetadataMapper metadataMapper;

    @Override
    public int addMetadata(Metadata metadata) {
        return metadataMapper.addMetadata(metadata);
    }

    @Override
    public List<Metadata> getMetadataList(GetMetadataListParam params) {
        PageHelper.startPage(params.getPage(), params.getPageSize());
        return metadataMapper.getMetadataList(params);
    }

    @Override
    public Metadata getMetadataInfo(Long metadataId) {
        return metadataMapper.getMetadataInfo(metadataId);
    }
}
