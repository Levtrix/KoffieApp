package KoffieAppDal.repository;

import models.Order;

public class OrderRepository extends AbstractRepository<Order, Integer> {
    @Override
    public Class<Order> getDomainClass() {
        return Order.class;
    }
}
