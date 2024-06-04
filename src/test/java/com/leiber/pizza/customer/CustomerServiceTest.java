package com.leiber.pizza.customer;

import com.leiber.pizza.persistence.entity.CustomerEntity;
import com.leiber.pizza.persistence.repository.CustomerRepository;
import com.leiber.pizza.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Clase encargada de las pruebas unitarias para el service de Customer
 * @author Leiber Brtel
 */
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    private CustomerEntity customerEntity;

    @BeforeEach
    public void setup() {
        customerEntity = new CustomerEntity();
        customerEntity.setPhoneNumber("12345678");
    }

    @Test
    public void testFindByPhone_Success() {
        String phoneNumber = "12345678";
        when(customerRepository.findByPhoneNumberJPQL(phoneNumber)).thenReturn(customerEntity);

        CustomerEntity result = customerService.findByPhone(phoneNumber);

        assertNotNull(result);
        assertEquals(phoneNumber, result.getPhoneNumber());
        verify(customerRepository, times(1)).findByPhoneNumberJPQL(phoneNumber);
    }

    @Test
    void testFindByPhone_NotFound() {
        String phone = "1234567890";
        when(customerRepository.findByPhoneNumberJPQL(phone)).thenReturn(null);

        CustomerEntity result = customerService.findByPhone(phone);

        assertNull(result);
        verify(customerRepository, times(1)).findByPhoneNumberJPQL(phone);
    }
}
