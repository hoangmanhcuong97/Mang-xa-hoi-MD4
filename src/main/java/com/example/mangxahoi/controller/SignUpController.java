package com.example.mangxahoi.controller;

import com.example.mangxahoi.dto.LoginAccount;
import com.example.mangxahoi.model.Account;
import com.example.mangxahoi.model.AppRole;
import com.example.mangxahoi.model.Image;
import com.example.mangxahoi.model.VerifiAccount;
import com.example.mangxahoi.service.account.IServiceAccount;
import com.example.mangxahoi.service.approle.IServiceAppRole;
import com.example.mangxahoi.service.image.IServiceImage;
import com.example.mangxahoi.service.jwt.JwtService;
import com.example.mangxahoi.service.post.IServicePost;
import com.example.mangxahoi.service.verify.IVerifiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/account")
public class SignUpController {
    @Autowired
    IServiceAccount serviceAccount;
    @Autowired
    IServiceAppRole serviceAppRole;
    @Autowired
    IServiceImage serviceImage;
    @Autowired
    IServicePost servicePost;
    @Autowired
    JwtService jwtService;
    @Autowired
    IVerifiService verifiService;
    @Autowired
    JavaMailSender javaMailSender;

    @PostMapping("/signup")
    public ResponseEntity<String> createAcc(@Valid @RequestBody Account account) {
        String message = "";
        AppRole role = serviceAppRole.findById(2L).get();
        Image image = serviceImage.findById(1L).get();
        account.setAvatar(image);
        account.setRole(role);
        account.setEnable(false);
        if (serviceAccount.add(account)) {
            String str = "GP Coder";
            Account acc = serviceAccount.loadUserByEmail(account.getEmail());
            String token = Base64.getEncoder().encodeToString(account.getEmail().getBytes());
            VerifiAccount verifiAccount = new VerifiAccount();
            verifiAccount.setIdAcc(acc.getId());
            verifiAccount.setToken(token);
            VerifiAccount newVerifi = verifiService.add(verifiAccount);
            SimpleMailMessage sendmail = new SimpleMailMessage();
            sendmail.setTo(account.getEmail());
            sendmail.setSubject("Bấm vào link bên dưới để xác thực email!");
            sendmail.setText("https://vilo-vn.herokuapp.com/account/verification/" + newVerifi.getId() + "/" + acc.getId() + "?token=" + token);
            javaMailSender.send(sendmail);
            message = "Check email to verification!";
        } else message = "account exited!";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request, @RequestBody Account account) {
        String result = "";
        HttpStatus httpStatus = null;
        try {
            if (serviceAccount.checkLogin(account)) {
                result = jwtService.generateTokenLogin(account.getEmail());
                Account account1 = serviceAccount.loadUserByEmail(account.getEmail());
                LoginAccount loginAccount = new LoginAccount();
                loginAccount.setId(account1.getId());
                loginAccount.setFullName(account1.getFullName());
                loginAccount.setAvatar(account1.getAvatar());
                loginAccount.setToken(result);
                return new ResponseEntity(loginAccount, HttpStatus.OK);
            } else {
                result = "Wrong email or password or not verification";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception ex) {
            result = "Server Error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(result, httpStatus);
    }
}
