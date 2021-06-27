package com.freshcart.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Component
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_status")
    private Integer orderStatus;

    @Column(name = "address")
    private String address;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "pay_date")
    private Date payDate;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public Order() {
    }


    public Order(
            Integer id, Integer orderStatus,
            String address, Integer userId,
            Date payDate, Date createdAt, Date updatedAt
    ) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.address = address;
        this.userId = userId;
        this.payDate = payDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    @Override
    public String toString() {
        return "Order [ " +
                "id=" + id +
                ", orderStatus=" + orderStatus +
                ", addressId=" + address +
                ", userId=" + userId +
                ", payDate=" + payDate +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                " ] ";
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String  getAddress() {
        return address;
    }

    public void setAddress(String  addressId) {
        this.address = addressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
