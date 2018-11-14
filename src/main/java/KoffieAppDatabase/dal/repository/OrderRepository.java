package KoffieAppDatabase.dal.repository;

import KoffieAppDatabase.models.SQLOrder;

public class OrderRepository extends AbstractRepository<SQLOrder, Integer> {
    @Override
    public Class<SQLOrder> getDomainClass() {
        return SQLOrder.class;
    }
}
