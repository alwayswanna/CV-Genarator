package a.gleb.cv.mapper;

import a.gleb.cv.model.pdf.PersonalInfo;
import a.gleb.cv.model.request.ContactInfoModel;
import a.gleb.cv.model.request.RequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CustomModelMapper {

    PersonalInfo map(RequestModel request, ContactInfoModel model);

}
