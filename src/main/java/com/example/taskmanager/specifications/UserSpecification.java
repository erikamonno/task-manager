package com.example.taskmanager.specifications;

import com.example.taskmanager.entities.User;
import com.example.taskmanager.filters.UserFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
@Data
public class UserSpecification implements Specification<User> {

    private final UserFilter filter;
    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return Specification.allOf(
                nameEqual(),
                surnameEqual(),
                descriptionEqual(),
                emailEqual(),
                birthDateEqual()
        ).toPredicate(root, query, criteriaBuilder);
    }

    public Specification<User> nameEqual() {
        return (root, query, criteriaBuilder) -> {
            if(filter.getName()==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("name"), filter.getName());
            }
        };
    }

    public Specification<User> surnameEqual() {
        return (root, query, criteriaBuilder) -> {
            if(filter.getSurname()==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("surname"), filter.getSurname());
            }
        };
    }

    public Specification<User> descriptionEqual() {
        return (root, query, criteriaBuilder) -> {
            if(filter.getDescription()==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("description"), filter.getDescription());
            }
        };
    }

    public Specification<User> emailEqual() {
        return (root, query, criteriaBuilder) -> {
            if(filter.getEmail()==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("email"), filter.getEmail());
            }
        };
    }

    public Specification<User> birthDateEqual() {
        return (root, query, criteriaBuilder) -> {
            if(filter.getBirthDate()==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("birthDate"), filter.getBirthDate());
            }
        };
    }
}
