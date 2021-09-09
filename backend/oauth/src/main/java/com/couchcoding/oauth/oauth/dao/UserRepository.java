package com.couchcoding.oauth.oauth.dao;

import com.couchcoding.oauth.oauth.entity.CustomUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, String> {
}
