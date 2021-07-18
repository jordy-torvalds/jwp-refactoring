package kitchenpos.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;



@Service
@ActiveProfiles("test")
public class PersistenceDatabaseCleanup implements DatabaseCleanup, InitializingBean{
//    @PersistenceContext
//    private EntityManager entityManager;

//    private List<String> tableNames;

    @Override
    public void afterPropertiesSet() {
//        tableNames = entityManager.getMetamodel().getEntities().stream()
//                .filter(e -> e.getJavaType().getAnnotation(Entity.class) != null)
//                .map(e -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName()))
//                .collect(Collectors.toList());
    }

    @Transactional
    public void execute() {
        System.out.println("PersistenceDatabaseCleanup execute");
//        entityManager.flush();
//        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
//
//        for (String tableName : tableNames) {
//            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
//            entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate();
//        }
//
//        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}
