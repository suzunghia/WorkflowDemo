package com.workflow.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.workflow.demo.models.Member;

public interface MemberRepository extends CrudRepository<Member, Long> {

    public List<Member> findByName (String name); 
}
