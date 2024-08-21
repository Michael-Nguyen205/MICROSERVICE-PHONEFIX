package spring.boot.servivecustomer;

import lombok.extern.log4j.Log4j2;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import reactor.core.publisher.Mono;
import spring.boot.servivecustomer.query.controller.CustomerQueryController;
import spring.boot.servivecustomer.query.model.CustomerResponeModel;
import spring.boot.servivecustomer.query.query.GetCustomerDetailsQuery;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@ExtendWith(SpringExtension.class)
//

@AutoConfigureMockMvc
//@TestPropertySource("/test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Log4j2
public class CustomerQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private QueryGateway queryGateway;

    @InjectMocks
    private CustomerQueryController customerQueryController;

    private CustomerResponeModel customerResponse;


//    private String customerId;

    @BeforeEach
    public void setUp() {
        customerResponse = new CustomerResponeModel("John Doe", "123456789", false);
//        customerId = UUID.randomUUID().toString();

//        ReflectionTestUtils.setField(customerQueryController,"queryGateway",queryGateway);
    }
    @Test
    void  getDetailCustomerSuccess() throws Exception {
        String customerId = UUID.randomUUID().toString();
        GetCustomerDetailsQuery query = new GetCustomerDetailsQuery(customerId);

        when(queryGateway.query(query, CustomerResponeModel.class))
                .thenReturn(CompletableFuture.completedFuture(customerResponse));


        Mono<ResponseEntity<CustomerResponeModel>> result = customerQueryController.getDetailCustomer(customerId);
        ResponseEntity<CustomerResponeModel> responseEntity = result.block();


        log.error("customerResponse",customerResponse);

        log.error("responseEntity.getBody()",responseEntity.getBody());

        assertEquals(customerResponse, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer/getDetailCustomer/{id}", customerId)
                 .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("123456789"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.isBan").value(false));
         }


    @Test
    void getDetailCustomerFailure() throws Exception {
        String customerId = UUID.randomUUID().toString();
        GetCustomerDetailsQuery query = new GetCustomerDetailsQuery(customerId);

        when(queryGateway.query(query, CustomerResponeModel.class))
                .thenReturn(CompletableFuture.failedFuture(new RuntimeException("Error")));

//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer/getDetailCustomer/{id}", customerId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andReturn();
//
//        String content = result.getResponse().getContentAsString();
//        System.out.println("Response Content Response ContentResponse ContentResponse ContentResponse ContentResponse ContentResponse Content" +
//                "Response ContentResponse ContentResponse ContentResponse ContentResponse ContentResponse Content" +
//                "Response ContentResponse ContentResponse ContentResponse ContentResponse ContentResponse ContentResponse Content" +
//                "Response ContentResponse ContentResponse ContentResponse ContentResponse ContentResponse Content" +
//                "Response ContentResponse ContentResponse ContentResponse ContentResponse ContentResponse Content" +
//                "Response ContentResponse ContentResponse ContentResponse ContentResponse ContentResponse ContentResponse Content" +
//                ": " + content);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer/getDetailCustomer/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(MockMvcResultMatchers.status().isBadRequest());
//                .andExpect(MockMvcResultMatchers.jsonPath("name").isEmpty())
//                .andExpect(MockMvcResultMatchers.jsonPath("phoneNumber").isEmpty())
//                .andExpect(MockMvcResultMatchers.jsonPath("isBan").isBoolean());
          }
    ;
    }



















//    @Test
//    void getDetailCustomer() {
//        String customerId = UUID.randomUUID().toString();
//        GetCustomerDetailsQuery query = new GetCustomerDetailsQuery(customerId);
//
//        when(queryGateway.query(query, CustomerResponeModel.class))
//                .thenReturn(CompletableFuture.completedFuture(customerResponse));
//
//        Mono<ResponseEntity<CustomerResponeModel>> result = customerQueryController.getDetailCustomer(customerId);
//
//        ResponseEntity<CustomerResponeModel> responseEntity = result.block(); // Blocking to get the result
//
//        assertEquals(customerResponse, responseEntity.getBody());
//        assertEquals(200, responseEntity.getStatusCodeValue());
//    }


