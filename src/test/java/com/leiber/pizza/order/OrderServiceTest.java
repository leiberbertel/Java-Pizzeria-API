package com.leiber.pizza.order;

import com.leiber.pizza.persistence.entity.OrderEntity;
import com.leiber.pizza.persistence.projection.OrderSummary;
import com.leiber.pizza.persistence.repository.OrderRepository;
import com.leiber.pizza.services.OrderService;
import com.leiber.pizza.services.dto.RamdonOrderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Clase encargada de las pruebas unitarias para el service de Order
 *
 * @author Leiber Brtel
 */
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SITE = "S";
    @InjectMocks
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderSummary orderSummary;
    private List<OrderEntity> orders;

    @BeforeEach
    public void setup() {
        OrderEntity order = new OrderEntity();
        order.setIdCustomer("1");
        order.setTotal(22.0);

        orders = new ArrayList<>();
        orders.add(order);
    }

    @Test
    public void testGetAll_Success() {
        when(orderRepository.findAll()).thenReturn(orders);

        List<OrderEntity> result = orderService.getAll();

        assertEquals(result.size(), 1);
        assertNotNull(result);
        assertEquals(result.get(0).getIdCustomer(), "1");
        assertEquals(result.get(0).getTotal(), 22.0);
    }

    @Test
    public void testGetTodayOrders_Success() {
        LocalDateTime today = LocalDate.now().atTime(0, 0);
        when(orderRepository.findAllByDateAfter(today)).thenReturn(orders);

        List<OrderEntity> result = orderService.getTodayOrders();

        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getIdCustomer(), "1");
        assertEquals(result.get(0).getTotal(), 22.0);
    }

    @Test
    public void testGetOutsideOrders_Success() {
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        when(orderRepository.findAllByMethodIn(methods)).thenReturn(orders);

        List<OrderEntity> result = orderService.getOutsideOrders();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(result.get(0).getIdCustomer(), "1");
        assertEquals(result.get(0).getTotal(), 22.0);
    }

    @Test
    public void testGetOnSideOrders_Success() {
        List<String> methods = List.of(ON_SITE);
        when(orderRepository.findAllByMethodIn(methods)).thenReturn(orders);

        List<OrderEntity> result = orderService.getOnSideOrders();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(result.get(0).getIdCustomer(), "1");
        assertEquals(result.get(0).getTotal(), 22.0);
    }

    @Test
    public void testGetCustomerOrder_Success() {
        when(orderRepository.findCustomerOrders("1")).thenReturn(orders);
        List<OrderEntity> result = orderService.getCustomerOrder("1");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(result.get(0).getIdCustomer(), "1");
        assertEquals(result.get(0).getTotal(), 22.0);
    }

    @Test
    public void testGetSummary_Success() {
        when(orderRepository.findSummary(1)).thenReturn(orderSummary);

        OrderSummary result = orderService.getSummary(1);

        assertEquals(orderSummary, result);
    }

    @Test
    public void testSaveRandomOrder_Success() {
        RamdonOrderDto ramdonOrderDto = new RamdonOrderDto();
        ramdonOrderDto.setIdCustomer("1");
        ramdonOrderDto.setMethod("D");

        when(orderRepository.saveRandomOrder("1", DELIVERY)).thenReturn(true);

        boolean result = orderService.saveRandomOrder(ramdonOrderDto);

        assertTrue(result);
    }
}
