package com.quiz.refactoring.user.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="user")
class User(
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   var id:Long?=null,
   var userId: String,
   var userPw: String,
   @CreationTimestamp
   var regTime: LocalDateTime?=LocalDateTime.now()
)