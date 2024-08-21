package spring.boot.servicephones.query.projection;

import lombok.extern.log4j.Log4j2;
import org.axonframework.queryhandling.QueryHandler;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.boot.servicephones.command.model.FixPhone;
import spring.boot.servicephones.command.model.PhoneFixRepository;
import spring.boot.servicephones.query.model.GetFixPhoneResponeModel;
import spring.boot.servicephones.query.query.GetAllFixPhoneQuery;
import spring.boot.servicephones.query.query.GetFixPhoneQuery;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Component
public class FixPhoneProjection {

@Autowired
private  PhoneFixRepository phoneFixRepository;


    @Autowired
    private ModelMapper modelMapper;

    @QueryHandler
    public GetFixPhoneResponeModel handle(GetFixPhoneQuery query){
        log.error(query.getId());
        FixPhone fixPhone = phoneFixRepository.findById(query.getId()).orElseThrow(
                ()->  new RuntimeException("loi khong tim dc id "+query.getId().toString())
        );
        GetFixPhoneResponeModel getFixPhoneResponeModel = modelMapper.map(fixPhone, GetFixPhoneResponeModel.class );
        getFixPhoneResponeModel.setPhoneNumber(fixPhone.getPhoneNumber() );
        getFixPhoneResponeModel.setGmailCustomer(fixPhone.getGmailCustomer());
        return getFixPhoneResponeModel;
    }



    @QueryHandler
    public List<GetFixPhoneResponeModel> handle(GetAllFixPhoneQuery query) {
        List<FixPhone> fixPhoneList = phoneFixRepository.findAll();

        // Thiết lập ModelMapper để bỏ qua thuộc tính id
        PropertyMap<FixPhone, GetFixPhoneResponeModel> fixPhoneMap = new PropertyMap<FixPhone, GetFixPhoneResponeModel>() {
            @Override
            protected void configure() {
//            skip(destination.getId());
            }
        };

        modelMapper.addMappings(fixPhoneMap);

        // Sử dụng ModelMapper để map danh sách các FixPhone thành GetAllFixPhoneResponeModel
        List<GetFixPhoneResponeModel> listPhoneFix = fixPhoneList.stream()
                .map(fixPhone -> modelMapper.map(fixPhone, GetFixPhoneResponeModel.class))
                .collect(Collectors.toList());

        return listPhoneFix;
    }


}
