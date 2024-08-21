package spring.boot.servicephones.query.controller;


import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.servicephones.query.model.GetFixPhoneResponeModel;
import spring.boot.servicephones.query.query.GetAllFixPhoneQuery;
import spring.boot.servicephones.query.query.GetFixPhoneQuery;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/phonefix")
public class QueryFixPhoneController {


    @Autowired
    private QueryGateway queryGateway;


    @GetMapping("/getAllFixPhone")
    public ResponseEntity<?> getAllFixPhone (){
        GetAllFixPhoneQuery getAllFixPhoneQuery = new GetAllFixPhoneQuery();
        List<GetFixPhoneResponeModel> getFixPhoneResponeModels = new ArrayList<>();
        getFixPhoneResponeModels = queryGateway
                .query(getAllFixPhoneQuery
                        , ResponseTypes.multipleInstancesOf(GetFixPhoneResponeModel.class))
                .join();
        return ResponseEntity.ok(getFixPhoneResponeModels);
    }





    @GetMapping("/getFixPhone/{id}")
    public ResponseEntity<?> getFixPhone (@PathVariable String id){
        GetFixPhoneQuery getAllFixPhoneQuery = new GetFixPhoneQuery();
        getAllFixPhoneQuery.setId(id);
        GetFixPhoneResponeModel getFixPhoneResponeModels = new GetFixPhoneResponeModel();
        getFixPhoneResponeModels = queryGateway
                .query(getAllFixPhoneQuery
                        , ResponseTypes.instanceOf(GetFixPhoneResponeModel.class))
                .join();
        return ResponseEntity.ok(getFixPhoneResponeModels);
    }


}
