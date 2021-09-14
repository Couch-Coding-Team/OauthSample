package com.couchcoding.oauth.oauth.domain.user.repository;

import com.couchcoding.oauth.oauth.domain.user.entity.CustomUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, String> {
}
