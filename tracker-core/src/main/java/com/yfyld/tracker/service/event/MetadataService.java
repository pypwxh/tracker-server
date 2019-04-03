package com.yfyld.tracker.service.event;



import com.yfyld.tracker.dto.GetMetadataListParam;
import com.yfyld.tracker.model.Metadata;

import java.util.List;

public interface MetadataService {
    int addMetadata(Metadata metadata);
    List<Metadata> getMetadataList(GetMetadataListParam params);
    Metadata getMetadataInfo(Long metadataId);
}

