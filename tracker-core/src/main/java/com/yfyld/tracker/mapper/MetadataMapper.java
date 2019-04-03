package com.yfyld.tracker.mapper;

import com.yfyld.tracker.dto.GetMetadataListParam;
import com.yfyld.tracker.model.Metadata;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MetadataMapper {
    int addMetadata(Metadata metadata);
    List<Metadata> getMetadataList(GetMetadataListParam params);
    Metadata getMetadataInfo(Long metadataId);
}
