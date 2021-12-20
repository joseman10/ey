package com.bank.cl.ey.domain.usecase;

import com.bank.cl.ey.adapter.controller.model.inbound.UserInfo;
import com.bank.cl.ey.adapter.controller.model.outbound.UserDTO;
import com.bank.cl.ey.config.enums.EResponses;
import com.bank.cl.ey.config.exceptions.BusinessException;
import com.bank.cl.ey.config.jwt.JwtTokenProvider;
import com.bank.cl.ey.domain.model.entity.Phone;
import com.bank.cl.ey.domain.model.entity.User;
import com.bank.cl.ey.domain.model.entity.enums.AppUserRole;
import com.bank.cl.ey.domain.model.entity.enums.UserState;
import com.bank.cl.ey.domain.port.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserRegisterBankUCImpl implements UserCreateUC{
    private final UserService userService;
    private static final String REGX_DOMAIN_CL = "^(.+)@(domain.cl)$";
    private static String RGX_PASS = "^(?=.*\\d){2}(?=.*[a-z])(?=.*[A-Z]).{4,}$";
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    private void throwIfEmailInvalid(final String email) {
        Pattern pattern = Pattern.compile(REGX_DOMAIN_CL);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            throw new BusinessException(EResponses.EMAIL_INVALID);
        }
    }

    private void throwIfUserExist(final String email){
        User user =userService.findUserByEmail(email);
        if(Objects.nonNull(user)) {
            throw new BusinessException(EResponses.EMAIL_EXIST);
        }
    }

    private void throwIfPassRules(final String pass){
        Pattern pattern = Pattern.compile(RGX_PASS);
        Matcher matcher = pattern.matcher(pass);
        if(!matcher.matches()){
            throw new BusinessException(EResponses.PASS_INVALID);
        }
    }

    private List<Phone> buildPhones(final UserInfo userInfo) {
        return userInfo.getPhones().stream().map(p-> Phone.builder()
                .citycode(p.getCitycode())
                .number(p.getNumber())
                .contrycode(p.getContrycode())
                .build()).collect(Collectors.toList());
    }
    @Override
    public UserDTO register(UserInfo userInfo) {
        throwIfEmailInvalid(userInfo.getEmail());
        throwIfUserExist(userInfo.getEmail());
        throwIfPassRules(userInfo.getPassword());

        User user = new User();

        user.setUsername(userInfo.getEmail());
        user.setName(userInfo.getName());
        user.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        String token = jwtTokenProvider.createToken(userInfo.getEmail(),
                new ArrayList<>(Arrays.asList(AppUserRole.ROLE_CLIENT)));
        user.setToken(token);
        user.setStatus(UserState.ACTIVE);
        LocalDateTime now = LocalDateTime.now();
        user.setCreated(now);
        user.setModified(now);
        user.setLastLogin(now);
        user.setPhoneList(buildPhones(userInfo));
        return buildUserDTO(userService.save(user), userInfo);
    }

    private UserDTO buildUserDTO(User user, UserInfo userInfo) {
        return UserDTO.builder()
                .id(user.getUserId())
                .email(user.getUsername())
                .name(user.getName())
                .token(user.getToken())
                .phones(userInfo.getPhones())
                .registered(user.getCreated())
                .updated(user.getModified())
                .lastLogin(user.getLastLogin())
                .state(user.getStatus().name())
                .build();
    }
}
