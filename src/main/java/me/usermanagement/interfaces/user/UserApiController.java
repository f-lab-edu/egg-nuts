package me.usermanagement.interfaces.user;

import lombok.extern.slf4j.Slf4j;
import me.usermanagement.application.UserFacade;
import me.usermanagement.domain.model.user.UserCommand;
import me.usermanagement.domain.model.user.UserInfo;
import me.usermanagement.common.response.messages.normal.NormalCodeDetailEnum;
import me.usermanagement.common.response.messages.normal.NormalMessage;
import me.usermanagement.common.response.Response;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
public class UserApiController {
    private final UserFacade userFacade;

    @PostMapping
    @ApiModelProperty(value = "회원가입")
    public ResponseEntity registerUser(@RequestBody @Valid UserDto.RegisterUser request) {
        log.info("request = {}", request);   // TODO - AOP 를 활용하여.. API 의 request, response 를 logging 한다
        UserCommand userCommand = request.toCommand();
        UserInfo userInfo = userFacade.registerUser(userCommand);
        UserDto.Response response = new UserDto.Response(userInfo);
        return responseMaker(NormalMessage.JOIN_OK, response);
    }

    @PatchMapping(value = "/{userId}")
    @ApiModelProperty(value = "사용자 상태 변경")
    public ResponseEntity modifyUserStatus(
            @PathVariable String userId,
            @RequestBody @Valid UserDto.ModifyUserStatus request
    ) {
        log.info("request = {}", request);
        UserCommand userCommand = request.toCommand(userId);
        UserInfo userInfo = userFacade.modifyUserStatus(userId, userCommand);
        UserDto.Response response = new UserDto.Response(userInfo);
        return responseMaker(NormalMessage.STATUS_CHANGE_OK, response);
    }

    @ApiModelProperty(value = "사용자 조회")
    @GetMapping(value = "/{userId}")
    public ResponseEntity userSearchById(@PathVariable @Valid String userId) {
        log.info("userId = {}", userId);
        UserInfo userInfo = userFacade.userSearchById(userId);
        UserDto.Response response = new UserDto.Response(userInfo);
        return responseMaker(NormalMessage.STATUS_CHANGE_OK, response);
    }

    // TODO - 제거하고 Response 안에 static 메서드로 옮겨보기
    private ResponseEntity responseMaker(NormalMessage normalMessage, Object result) {
        NormalCodeDetailEnum normalCodeDetailEnum = NormalCodeDetailEnum.getResponse(normalMessage);
        Response response = new Response.
                ResponseBuilder()
                .statusCode(normalCodeDetailEnum.getStatusCode())
                .responseText(normalCodeDetailEnum.getResponseText())
                .result(result)
                .toCommand();
        return new ResponseEntity<>(response, HttpStatus.resolve(normalCodeDetailEnum.getStatusCode()));
    }
}
