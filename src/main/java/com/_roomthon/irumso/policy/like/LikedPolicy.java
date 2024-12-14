package com._roomthon.irumso.policy.like;

import com._roomthon.irumso.policy.supportPolicy.SupportPolicy;
import com._roomthon.irumso.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "liked_policy")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikedPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liked_log_id", updatable = false, nullable = false) // 기존 열 이름과 매핑
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "support_policy_id", nullable = false)
    private SupportPolicy supportPolicy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public LikedPolicy(SupportPolicy supportPolicy, User user) {
        this.supportPolicy = supportPolicy;
        this.user = user;
    }
}