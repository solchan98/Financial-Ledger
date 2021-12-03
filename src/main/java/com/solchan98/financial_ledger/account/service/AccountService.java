package com.solchan98.financial_ledger.account.service;

import com.solchan98.financial_ledger.account.domain.Account;
import com.solchan98.financial_ledger.account.domain.AccountRepository;
import com.solchan98.financial_ledger.account.domain.dto.Login;
import com.solchan98.financial_ledger.account.domain.dto.SignUp;
import com.solchan98.financial_ledger.config.exception.account.*;
import com.solchan98.financial_ledger.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Collections;
import java.util.regex.Pattern;

import static com.solchan98.financial_ledger.config.content.AccountContent.EMAIL_REG_EXT;
import static com.solchan98.financial_ledger.config.content.AccountContent.PASSWORD_REG_EXT;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, String> redisTemplate;

    public void logout(Account account, String accessToken) {
        setBlackListAccessToken(accessToken.substring(7));
        redisTemplate.delete(account.getEmail());
    }

    public Login.RefreshToken getNewRefresh(String accountEmail, String refreshToken) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        String refreshTokenInRedis = values.get(accountEmail);
        if(!refreshToken.equals(refreshTokenInRedis)) {
            throw new RefreshTokenBadRequestException();
        }
        String newAccessToken = jwtTokenProvider.createAccessToken(accountEmail, Collections.singletonList("ROLE_USER"));
        String newRefreshToken = setNewRefreshToken(accountEmail);
        return Login.RefreshToken.getNewTokenResponse(newAccessToken, newRefreshToken);
    }

    @Transactional(readOnly = true)
    public Login.Response login(Login.Request request) {
        Account account = accountRepository.findByEmail(request.getEmail())
                .orElseThrow(LoginBadRequestException::new);
        checkPassword(account, request);
        String accessToken = jwtTokenProvider.createAccessToken(account.getEmail(), Collections.singletonList("ROLE_USER"));
        String refreshToken = jwtTokenProvider.createRefreshToken(account.getEmail(), Collections.singletonList("ROLE_USER"));
        setRefreshInRedis(account, refreshToken);
        return Login.Response.getLoginResponse(account, accessToken, refreshToken);
    }

    @Transactional
    public SignUp.Response signUp(SignUp.Request request) {
        checkSignUpRequestData(request);
        Account account = Account.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())).build();
        return SignUp.Response.getSignUpResponse(accountRepository.save(account));
    }

    private void checkPassword(Account account, Login.Request request) {
        boolean result = passwordEncoder.matches(request.getPassword(), account.getPassword());
        if(!result) {
            throw new LoginBadRequestException();
        }
    }

    private void checkSignUpRequestData(SignUp.Request request) {
        if(!Pattern.matches(EMAIL_REG_EXT, request.getEmail())) {
            throw new InvalidEmailException();
        }
        if(!Pattern.matches(PASSWORD_REG_EXT, request.getPassword())){
            throw new InvalidPasswordException();
        }
        if(accountRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException();
        }
    }

    private void setRefreshInRedis(Account account, String refreshToken) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        long expiration = jwtTokenProvider.getExpiration(refreshToken).getTime() - System.currentTimeMillis();
        values.set(account.getEmail(), refreshToken, Duration.ofMillis(expiration));
    }

    private String setNewRefreshToken(String accountEmail) {
        Account account = accountRepository.findByEmail(accountEmail).orElseThrow(UserNotFoundException::new);
        String newRefreshToken = jwtTokenProvider.createRefreshToken(accountEmail, Collections.singletonList("ROLE_USER"));
        redisTemplate.delete(accountEmail);
        setRefreshInRedis(account, newRefreshToken);
        return newRefreshToken;
    }

    private void setBlackListAccessToken(String accessToken) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        long expiration = jwtTokenProvider.getExpiration(accessToken).getTime() - System.currentTimeMillis();
        if(expiration > 0) {
            values.set(accessToken, "BlackList", Duration.ofMillis(expiration));
        }
    }
}
