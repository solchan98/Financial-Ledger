package com.solchan98.financial_ledger.account.domain;

import com.solchan98.financial_ledger.ledger.domain.Ledger;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private  final List<Ledger> ledgerList = new ArrayList<>();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private LocalDateTime createAt;

    private LocalDateTime loginAt;

    @Builder
    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createAt = LocalDateTime.now();
        this.loginAt = LocalDateTime.now();
    }

    public void updateLastLogin() {
        this.loginAt = LocalDateTime.now();
    }
}
