package com.stone.play.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;
//   @Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public class MapStructMapperConfig {

}
