package mapper;

import entity.Source;
import entity.SubSource;
import entity.SubTarget;
import entity.Target;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author payno
 * @date 2020/3/25 09:34
 * @description
 */
@Mapper
public interface SourceMapper {
    public static SourceMapper INSTANCE = Mappers.getMapper(SourceMapper.class);

    @Mapping(source = "totalCount", target = "count")
    @Mapping(source = "subSource", target = "subTarget")
    Target from(Source source);

    default SubTarget from(SubSource subSource) {
        if (subSource == null) {
            return null;
        }
        SubTarget subTarget = new SubTarget();
        subTarget.setResult(!subSource.getDeleted().equals(0));
        subTarget.setName(subSource.getName()==null?"":subSource.getName()+subSource.getName());
        return subTarget;
    }
}
