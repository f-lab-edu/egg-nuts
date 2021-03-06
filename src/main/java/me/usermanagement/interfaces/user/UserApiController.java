package me.usermanagement.interfaces.user;

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

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
public class UserApiController {

    private final UserFacade userFacade;

    @PostMapping
    @ApiModelProperty(value = "회원가입")
    public ResponseEntity registerUser(@RequestBody @Valid UserDto.RegisterUser request) {
        System.out.println(request);
        UserCommand userCommand = request.builder();
        UserInfo userInfo = userFacade.registerUser(userCommand);
        UserDto.Response response = new UserDto.Response(userInfo);
        return responseMaker(NormalMessage.JOIN_OK, response);
    }


    @PatchMapping(value="/{user_id}")
    @ApiModelProperty(value = "사용자 상태 변경")
    public ResponseEntity modifyUserStatus(@PathVariable String user_id, @RequestBody @Valid UserDto.modifyUserStatus request) {
        UserCommand userCommand = request.builder();
        UserInfo userInfo = userFacade.modifyUserStatus(user_id,userCommand);
        UserDto.Response response = new UserDto.Response(userInfo);
        return responseMaker(NormalMessage.STATUS_CHANGE_OK, response);
    }


    @ApiModelProperty(value = "사용자 조회")
    @GetMapping(value = "/{user_id}")
    public ResponseEntity userSearchById(@PathVariable @Valid String user_id) {
        UserInfo userInfo = userFacade.userSearchById(user_id);
        UserDto.Response response = new UserDto.Response(userInfo);
        return responseMaker(NormalMessage.STATUS_CHANGE_OK, response);
    }

    private ResponseEntity responseMaker(NormalMessage normalMessage, Object result) {
        NormalCodeDetailEnum normalCodeDetailEnum = NormalCodeDetailEnum.getResponse(normalMessage);
        Response response = new Response.
                ResponseBuilder()
                .statusCode(normalCodeDetailEnum.getStatusCode())
                .responseText(normalCodeDetailEnum.getResponseText())
                .result(result)
                .builder();
        return new ResponseEntity<>(response, HttpStatus.resolve(normalCodeDetailEnum.getStatusCode()));
    }
}
