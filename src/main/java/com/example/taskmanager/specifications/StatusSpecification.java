package com.example.taskmanager.specifications;

import com.example.taskmanager.entities.Status;
import com.example.taskmanager.filters.StatusFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
@Data
public class StatusSpecification implements Specification<Status> {

    private final StatusFilter filter;

    @Override
    public Predicate toPredicate(Root<Status> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return Specification.allOf(
                nameEqual(),
                descriptionEqual()
        ).toPredicate(root, query, criteriaBuilder);
    }

    public Specification<Status> nameEqual() {
        return (root, query, criteriaBuilder) -> {
            if(filter.getName()==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("name"), filter.getName());
            }
        };
    }

    public Specification<Status> descriptionEqual() {
        return (root, query, criteriaBuilder) -> {
            if(filter.getDescription()==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("description"), filter.getDescription());
            }
        };
    }
}
