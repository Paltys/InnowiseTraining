//package dao;
//
//
//import entity.PhoneEntity;
//import java.io.Serializable;
//import java.util.List;
//import java.util.Optional;
//
//public class PhoneDao extends AbstractDao<PhoneEntity>{
//    @Override
//    public Serializable create(PhoneEntity obj) {
//        return super.create(obj);
//    }
//
//    @Override
//    public void delete(PhoneEntity obj) {
//        super.delete(obj);
//    }
//
//    @Override
//    public void update(PhoneEntity obj) {
//        super.update(obj);
//    }
//
//    @Override
//    public List<PhoneEntity> getAll() {
//        List<PhoneEntity> listPhone =getSession().createQuery("FROM phone").list();
//        return listPhone;
//    }
//
//    @Override
//    public Optional<PhoneEntity> getById(long id) {
//        PhoneEntity phoneEntity = getSession().get(PhoneEntity.class, id);
//        return Optional.ofNullable(phoneEntity);
//    }
//}
