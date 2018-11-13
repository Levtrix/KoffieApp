package dal.repository;

import models.SQLDrink;

public class DrinkRepository extends AbstractRepository<SQLDrink, Integer> {
    @Override
    public Class<SQLDrink> getDomainClass() {
        return SQLDrink.class;
    }
}
