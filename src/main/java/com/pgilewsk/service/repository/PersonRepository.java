package com.pgilewsk.service.repository;


import com.pgilewsk.service.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
}

//@FieldDefaults(level = AccessLevel.PRIVATE)
//class PersonRepositoryImpl implements PersonRepository {
//
//    Map<Long, Person> store = new ConcurrentHashMap<>();
//
//    @Override
//    public void save(Person person) {
//        store.put(person.getId(), person);
//    }
//
//    @Override
//    public Optional<Person> findById(Long id) {
//        return Optional.ofNullable(store.get(id));
//    }
//
//    @Override
//    public List<Person> findAll() {
//        return new ArrayList<>(store.values());
//    }
//}
