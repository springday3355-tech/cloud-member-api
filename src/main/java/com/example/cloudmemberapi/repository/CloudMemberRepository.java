package com.example.cloudmemberapi.repository;

import com.example.cloudmemberapi.entity.CloudMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CloudMemberRepository extends JpaRepository
        <CloudMember, Long>{
}
