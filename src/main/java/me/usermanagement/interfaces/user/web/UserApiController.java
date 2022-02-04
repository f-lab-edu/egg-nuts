package me.usermanagement.interfaces.user.web;

import me.usermanagement.application.UserService;
import me.usermanagement.common.response.messages.normal.NormalCodeDetailEnum;
import me.usermanagement.common.response.messages.normal.NormalMessage;
import me.usermanagement.common.response.Response;
import org.springframework.web.bind.annotation.RestController;
import me.usermanagement.interfaces.user.facade.dto.UserDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
public class UserApiController {

    private final UserService userService;

    @PostMapping
    @ApiModelProperty(value = "회원가입")
    public ResponseEntity userJoin(@RequestBody UserDto userDto) {
        userService.join(userDto);
        return  responseMaker(NormalMessage.JOIN_OK,null);
    }


    @PutMapping
    @ApiModelProperty(value = "사용자 상태 변경", notes = "모든 사용자 정보를 다 넘겨줘야 한다..")
    public ResponseEntity modifyUser(@RequestBody UserDto userDto) {
        userService.modifyUser(userDto);
        return responseMaker(NormalMessage.LEAVE_OK,null);
    }


    @ApiModelProperty(value = "사용자 조회")
    @GetMapping(value = "/{user_id}/account")
    public ResponseEntity userSearchById(@PathVariable String user_id) {
        return responseMaker(NormalMessage.LEAVE_OK,userService.userSearchById(user_id));
    }

    private ResponseEntity responseMaker(NormalMessage normalMessage,Object result){
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
