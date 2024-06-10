package com.sivalabs.bookstore.orders.domain;

import com.sivalabs.bookstore.orders.domain.models.OrderStatus;
import com.sivalabs.bookstore.orders.domain.models.OrderSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query(
            """
        select new com.sivalabs.bookstore.orders.domain.models.OrderSummary(o.orderNumber, o.status)
        from OrderEntity o
        where o.userName = :userName
        """)
    Page<OrderSummary> findByUserName(String userName, Pageable pageable);

    @Query(
            """
        select distinct o
        from OrderEntity o left join fetch o.items
        where o.userName = :userName and o.orderNumber = :orderNumber
        """)
    Optional<OrderEntity> findByUserNameAndOrderNumber(String userName, String orderNumber);


    @Modifying
    @Query("update OrderEntity o set o.status = :status where o.orderNumber = :orderNumber")
    void updateOrderStatus(String orderNumber, OrderStatus status);
}
