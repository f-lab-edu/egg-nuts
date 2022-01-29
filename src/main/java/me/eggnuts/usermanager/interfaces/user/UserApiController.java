package me.eggnuts.usermanager.interfaces.user;

import me.eggnuts.usermanager.application.UserService;
import org.springframework.web.bind.annotation.RestController;
import me.eggnuts.usermanager.domain.model.user.UserDto;
import me.eggnuts.usermanager.interfaces.message.NoErrorCodeDetailEnum;
import me.eggnuts.usermanager.interfaces.message.NoErrorMessage;
import me.eggnuts.usermanager.interfaces.message.Response;
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
        NoErrorCodeDetailEnum noErrorCodeDetailEnum = NoErrorCodeDetailEnum.getResponse(NoErrorMessage.JOIN_OK);
        Response response = new Response.
                ResponseBuilder()
                .statusCode(noErrorCodeDetailEnum.getStatusCode())
                .responseText(noErrorCodeDetailEnum.getResponseText())
                .builder();

        return new ResponseEntity<>(response, HttpStatus.resolve(noErrorCodeDetailEnum.getStatusCode()));
    }


    @PutMapping
    @ApiModelProperty(value = "사용자 상태 변경", notes = "모든 사용자 정보를 다 넘겨줘야 한다..")
    public ResponseEntity modifyUser(@RequestBody UserDto userDto) {

        userService.modifyUser(userDto);
        NoErrorCodeDetailEnum noErrorCodeDetailEnum = NoErrorCodeDetailEnum.getResponse(NoErrorMessage.LEAVE_OK);
        Response response = new Response.
                ResponseBuilder()
                .statusCode(noErrorCodeDetailEnum.getStatusCode())
                .responseText(noErrorCodeDetailEnum.getResponseText())
                .builder();

        return new ResponseEntity<>(response, HttpStatus.resolve(noErrorCodeDetailEnum.getStatusCode()));
    }


    @ApiModelProperty(value = "사용자 조회")
    @GetMapping(value = "/{user_id}/account")
    public ResponseEntity userSearchById(@PathVariable String user_id) {

        UserDto userDto = userService.userSearchById(user_id);
        NoErrorCodeDetailEnum noErrorCodeDetailEnum = NoErrorCodeDetailEnum.getResponse(NoErrorMessage.UNKNOWN);
        Response response = new Response.
                ResponseBuilder()
                .statusCode(noErrorCodeDetailEnum.getStatusCode())
                .responseText(noErrorCodeDetailEnum.getResponseText())
                .result(userDto)
                .builder();

        return new ResponseEntity<>(response, HttpStatus.resolve(noErrorCodeDetailEnum.getStatusCode()));
    }
}
